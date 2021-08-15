package com.revtekk.awsome.core.exception.mapper;

/**
 * This functional interface represents an action body which performs
 * actions that may throw an exception, and so, is annotated as throwing it.
 */
@FunctionalInterface
public interface ActionBody
{
    void run() throws Exception;
}
