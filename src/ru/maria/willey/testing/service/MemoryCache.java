package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * Created by User on 024 24.07.16.
 */
public class MemoryCache<K, V> implements ICache<Cache, Object> {
    private Map<Cache, Object> cacheMap = new ConcurrentSkipListMap<>();


    public MemoryCache() {
    }

    public MemoryCache(Map<Cache, Object> cacheMap) {
        this.cacheMap = cacheMap;

    }

    @Override
    public Object get(Cache key) throws CacheException, IOException, ClassNotFoundException {
        incrementMap(key);
        return cacheMap.get(key);
    }

    private void incrementMap(Cache key) {
        int temp = key.getFrequency();
        key.setFrequency(++temp);
    }

    @Override
    public void put(Cache key, Object value) throws CacheException, IOException, ClassNotFoundException {
        if (count() <= 16) {
            cacheMap.put(key, value);
            incrementMap(key);
        } else {
            removingOldItems();
            put(key, value);
        }
    }

    @Override
    public int count() {
        return cacheMap.size();
    }

    public Map<Cache, Object> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<Cache, Object> cacheMap) {
        this.cacheMap = cacheMap;

    }

    @Override
    public void remove(Cache key) throws CacheException, IOException {
        cacheMap.remove(key);

    }

    @Override
    public void clear() throws IOException {
        cacheMap.clear();

    }

    public synchronized List geSortFrequent(Map<Cache, Object> map) {
        List<Cache> list = new ArrayList<>();
        for (Map.Entry<Cache, Object> entry : map.entrySet()) {
            list.add(entry.getKey());
        }

        Comparator<Cache> cmp = Comparator.comparingInt(Cache::getFrequency).thenComparingInt(Cache::getFrequency);
        Collections.sort(list, cmp);
        return list;

    }

    public synchronized void removingOldItems() throws CacheException, IOException {
        List<Cache> list = geSortFrequent(cacheMap);
        DiskCache diskCache = new DiskCache();
        int oldSize = list.size() - 1;
        for (int i = oldSize; i > oldSize - 3; i--) {
            File file = diskCache.writeToDisk(list.get(i));
            diskCache.deleteFile(file);
            remove(list.get(i));
        }
        remove(list.get(0));
        remove(list.get(1));

    }


}
