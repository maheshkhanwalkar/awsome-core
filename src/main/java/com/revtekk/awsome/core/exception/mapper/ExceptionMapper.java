package com.revtekk.awsome.core.exception.mapper;

import com.revtekk.awsome.core.exception.InternalException;
import software.amazon.awssdk.core.exception.SdkException;

import java.lang.reflect.InvocationTargetException;

/**
 * Exception mapping driver.
 *
 * Translate exceptions from raw SDK exceptions to awsome library exceptions,
 * which are checked and retain the original exception context.
 */
public final class ExceptionMapper
{
    public static <T, K extends MappingContext<T>> void doHandle(Class<K> clazz, ActionBody body, T data)
            throws InternalException
    {
        try {
            body.run();
        } catch (Exception e) {
            MappingContext<T> ctx;
            try {
                ctx = clazz.getDeclaredConstructor().newInstance();
            }
            catch (InstantiationException | IllegalAccessException |
                    InvocationTargetException | NoSuchMethodException instantiationException) {
                throw new RuntimeException("Invalid mapping context provided -- must have parameterless constructor");
            }

            InternalException equiv = ctx.translate(e, data);

            // Apply default translation, if necessary
            if(equiv == null) {
                boolean retry = false;

                if (e instanceof SdkException) {
                    retry = ((SdkException) e).retryable();
                }

                throw new InternalException("Unknown internal exception occurred", e, retry);
            } else {
                throw equiv;
            }
        }
    }
}
