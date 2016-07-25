package ru.maria.willey.testing.util;
public class CacheException extends Exception{
    public CacheException(String exceptionMessage){
        super(exceptionMessage);
    }

    public CacheException(String exceptionMessage, Throwable cause){
        super(exceptionMessage, cause);
    }


}
