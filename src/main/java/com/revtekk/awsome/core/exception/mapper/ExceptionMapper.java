package com.revtekk.awsome.core.exception.mapper;

import com.revtekk.awsome.core.exception.InternalException;

/**
 * Exception mapping driver.
 *
 * Translate exceptions from raw SDK exceptions to awsome library exceptions,
 * which are checked and retain the original exception context.
 */
public final class ExceptionMapper
{
    public static <T> void doHandle(MappingContext<T> ctx, ActionBody body, T data) throws InternalException
    {
        try {
            body.run();
        } catch (Exception e) {
            InternalException equiv = ctx.translate(e, data);

            // Apply default translation, if necessary
            if(equiv == null) {
                throw new InternalException("Unknown internal exception occurred", e, false);
            } else {
                throw equiv;
            }
        }
    }
}
