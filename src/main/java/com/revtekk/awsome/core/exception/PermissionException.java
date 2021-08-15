package com.revtekk.awsome.core.exception;

/**
 * Permission-related exception.
 *
 * This exception is thrown whenever there is a lack of permissions to perform
 * the requested AWS action.
 */
public final class PermissionException extends InternalException
{
    public PermissionException(String message) {
        super(message, false);
    }

    public PermissionException(String message, Throwable source) {
        super(message, source, false);
    }
}
