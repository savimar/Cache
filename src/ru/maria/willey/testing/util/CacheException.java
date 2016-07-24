package ru.maria.willey.testing.util;

/**
 * Created by User on 024 24.07.16.
 */
public class CacheException extends Exception{
    public CacheException(String exceptionMessage){
        super(exceptionMessage);
    }

    public CacheException(String exceptionMessage, Throwable cause){
        super(exceptionMessage, cause);
    }


}
