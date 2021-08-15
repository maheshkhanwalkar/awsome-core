package com.revtekk.awsome.core.exception.mapper;

import com.revtekk.awsome.core.exception.InternalException;

/**
 * Mapping translation context.
 *
 * This interface defines a translation signature, which allows class
 * implementations to define how to map one exception to another.
 */
@FunctionalInterface
public interface MappingContext<T>
{
    /**
     * Translate an exception into an internal exception
     * @param orig original exception
     * @param data associated data
     * @return the new internal exception representation
     */
    InternalException translate(Exception orig, T data);
}
