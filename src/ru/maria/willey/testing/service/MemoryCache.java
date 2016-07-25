package ru.maria.willey.testing.service;

import ru.maria.willey.testing.interfaces.ICache;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * Created by User on 024 24.07.16.
 */
public class MemoryCache<K, V> implements ICache<Cache, Object> {
    private Map<Cache, Object> cacheMap;


    public MemoryCache() {
        cacheMap = new ConcurrentSkipListMap<>();

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
        if (count() <= 16) {//TODO removing
            cacheMap.put(key, value);
            incrementMap(key);
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

    public List getMostFrequent(Map<Cache, Object> map) {
        List<Cache> list = new ArrayList<>();
        for (Map.Entry<Cache, Object> entry : map.entrySet()) {
            list.add(entry.getKey());


        }
       /* List temp = cacheMap
                .entrySet()
                .parallelStream()
//                .max((entry1, entry2) -> entry1.getKey().getFrequency())
                .collect(Collectors.groupingByConcurrent(Cache::getFrequency));
*/
               /* .map(entry -> entry.setValue(entry.getKey().getFrequency()));*/
        Comparator<Cache> cmp = Comparator.comparingInt((Cache c) -> c.getFrequency()).thenComparingInt((Cache c) -> c.getFrequency());
        Collections.sort(list, cmp);
        return list;

    }



   /* public Cache getBigFrequencyElement() {
       Map<Cache, Integer> map = cacheMap
                .entrySet()
                .parallelStream()
                .map(entry -> entry.setValue(entry.getKey().getFrequency()))
               .



return
*/

        /*.get();
        .max((o1, o2) -> (o1.getKey().getFrequency()).compare(o2.getKey().getFrequency()))
        .get();*/

//}


    /*public int getItemFrequency(Cache key) throws CacheException {
        return frequencyMap.get(key);
    }

    public Map.Entry<Cache, Integer> getLowFrequencyElement() {
        return frequencyMap.entrySet()
                .stream()
                .min((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .get();
    }
*/

  /*  private int getItemFrequency(Map.Entry<Cache, Object> entry) {
        return entry.getKey().getFrequency();
    }
*/
}
