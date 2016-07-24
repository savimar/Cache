package ru.maria.willey.testing.interfaces;

import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;

/**
 * Created by User on 024 24.07.16.
 */
public interface ICache <K, V>{
    V get(K key) throws CacheException, IOException, ClassNotFoundException;
    void put(K key, V value) throws CacheException, IOException, ClassNotFoundException;
    int count();
    void remove(K key) throws CacheException, IOException;
    void clear() throws IOException;

}
