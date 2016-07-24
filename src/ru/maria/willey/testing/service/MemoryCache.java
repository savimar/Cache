package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.interfaces.IFrequency;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * Created by User on 024 24.07.16.
 */
public class MemoryCache<K, V> implements ICache<Cache, Object>, IFrequency<Cache>{
    private Map<Cache, Object> cacheMap;
    private Map<Cache, Integer> frequencyMap;


    public MemoryCache() {
        cacheMap = new ConcurrentSkipListMap<>();
        frequencyMap = new ConcurrentSkipListMap<>();
    }

    public MemoryCache(Map<Cache, Object> cacheMap) {
        this.cacheMap = cacheMap;
        Map<Cache, Integer> temp = new ConcurrentSkipListMap<>();
        cacheMap.entrySet()
                .forEach(entry -> temp.put(entry.getKey(), Integer.valueOf(0)));
        this.frequencyMap = temp;

    }

    @Override
    public Object get(Cache key) throws CacheException, IOException, ClassNotFoundException {
        incrementMap(key);
        return cacheMap.get(key);
    }

    private void incrementMap(Cache key) {
        frequencyMap.remove(key);
        if(Objects.isNull(frequencyMap.get(key))){
            frequencyMap.put(key, 1);
        }
        else {
            int newVal = frequencyMap.get(key);
            frequencyMap.put(key, ++newVal);
        }
    }

    @Override
    public void put(Cache key, Object value) throws CacheException, IOException, ClassNotFoundException {
        cacheMap.put(key, value);
        incrementMap(key);
    }

    @Override
    public int count() {
        return cacheMap.size();
    }

    public Map<Cache, Object> getCacheMap() {
        return cacheMap;
    }

    public Map<Cache, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public void setFrequencyMap(Map<Cache, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }

    public void setCacheMap(Map<Cache, Object> cacheMap) {
        this.cacheMap = cacheMap;

    }

    @Override
    public void remove(Cache key) throws CacheException, IOException {
        cacheMap.remove(key);
        frequencyMap.remove(key);

    }

    @Override
    public void clear() throws IOException {
        cacheMap.clear();
        frequencyMap.clear();
    }

    @Override
    public Map.Entry<Cache, Integer> getBigFrequencyElement() {
        return frequencyMap.entrySet()
                .stream()
                .max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .get();
    }


    @Override
    public int getItemFrequency(Cache key) throws CacheException {
        return frequencyMap.get(key);
    }

    @Override
    public Map.Entry<Cache, Integer> getLowFrequencyElement() {
        return frequencyMap.entrySet()
                .stream()
                .min((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .get();
    }



}
