package com.revtekk.awsome.core.region;

import com.revtekk.awsome.core.exception.InternalException;
import com.revtekk.awsome.core.exception.mapper.ExceptionMapper;
import com.revtekk.awsome.core.exception.mapper.MappingContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest
{
    private static final class MappingImpl implements MappingContext<String> {
        @Override
        public InternalException translate(Exception orig, String data)
        {
            if(orig instanceof NullPointerException) {
                return new CustomNullPtr();
            }

            return null;
        }
    }

    private static final class CustomNullPtr extends InternalException {
        public CustomNullPtr() {
            super("Null pointer exception", false);
        }
    }

    @Test
    public void testDefaultTranslate() {
        try {
            ExceptionMapper.doHandle(MappingImpl.class, () -> {
                throw new Exception();
            }, "");
        }
        catch (InternalException e) {
            assertFalse(e.canRetry());
            assertFalse(e instanceof CustomNullPtr);
        }
    }

    @Test
    public void testActualTranslate() {
        try {
            ExceptionMapper.doHandle(MappingImpl.class, () -> {
                throw new NullPointerException();
            }, "");
        }
        catch (InternalException e) {
            assertFalse(e.canRetry());
            assertTrue(e instanceof CustomNullPtr);
        }
    }
}
