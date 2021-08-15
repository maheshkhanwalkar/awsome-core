package com.revtekk.awsome.core.exception;

/**
 * Internal exception.
 *
 * This exception is a "catch-all" exception thrown when there is some internal SDK or
 * connection error which causes the requested AWS action to fail.
 *
 * This exception maintains a "retry" eligibility, essentially indicating whether the
 * underlying cause is transient and so a retry is advisable.
 */
public class InternalException extends Exception
{
    private final boolean retry;

    public InternalException(String message, boolean retry) {
        super(message);
        this.retry = retry;
    }

    public InternalException(String message, Throwable source, boolean retry) {
        super(message, source);
        this.retry = retry;
    }

    /**
     * Return whether the exception root-cause was transient and so the action
     * can be retried and potentially succeed now.
     *
     * @return true if retryable, false otherwise
     */
    public final boolean canRetry() {
        return retry;
    }
}
