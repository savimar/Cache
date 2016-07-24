package ru.maria.willey.testing.interfaces;

import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;

/**
 * Created by User on 024 24.07.16.
 */
public interface ICache <K, V>{
    Object get(Cache key) throws CacheException, IOException, ClassNotFoundException;
    void put(Cache key, Object value) throws CacheException, IOException, ClassNotFoundException;
    int count();
    void remove(Cache key) throws CacheException, IOException;
    void clear() throws IOException;

}
