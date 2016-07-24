package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * Created by User on 024 24.07.16.
 */
public class MemoryCache<K, V> implements ICache<K, V> {
    private Map<Object, Object> cacheMap;
    private Map<Object, Integer> frequencyMap;


    public MemoryCache() {
        cacheMap = new ConcurrentSkipListMap<>();
        frequencyMap = new ConcurrentSkipListMap<>();
    }

    @Override
    public Object get(Object key) throws CacheException, IOException, ClassNotFoundException {
        frequencyMap.computeIfPresent(key, (k, v) -> v + 1);
        return cacheMap.get(key);
    }

    @Override
    public void put(Object key, Object value) throws CacheException, IOException, ClassNotFoundException {
        cacheMap.put(key, value);
        frequencyMap.put(key, 1);
    }

    @Override
    public int count() {
        return cacheMap.size();
    }

    @Override
    public void remove(Object key) throws CacheException, IOException {
        cacheMap.remove(key);
        frequencyMap.remove(key);
    }

    @Override
    public void clear() throws IOException {
        cacheMap.clear();
        frequencyMap.clear();
    }
}
