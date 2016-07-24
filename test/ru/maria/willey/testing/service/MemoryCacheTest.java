package ru.maria.willey.testing.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by User on 024 24.07.16.
 */
public class MemoryCacheTest {
    Map<Cache, Object> map;
    Map<Cache, Integer> freeq;
    MemoryCache memoryCache;
    User user1;
    User user2;
    User user3;
    Cache cache1;
    Cache cache2;
    Cache cache3;


    @Before
    public void setUp() throws Exception {

        user1 = new User(1, "Olga", "Ivanova");
        user2 = new User(2, "Ivan", "Petrov");
        user3 = new User(3, "Irina", "Sidorova");

        map = new ConcurrentSkipListMap<>();

        cache1 = new Cache(user1);
        cache2 = new Cache(user2);
        cache3 = new Cache(user3);

        map.put(cache1, user1);
        map.put(cache2, user2);
        map.put(cache3, user3);

        memoryCache = new MemoryCache(map);
    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void put() throws Exception {

    }

    @Test
    public void count() throws Exception {

    }

    @Test
    public void getCacheMap() throws Exception {

    }

    @Test
    public void getFrequencyMap() throws Exception {

    }

    @Test
    public void setFrequencyMap() throws Exception {

    }

    @Test
    public void setCacheMap() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void getBigFrequencyElement() throws Exception {
        freeq = new ConcurrentSkipListMap<>();

        freeq.put(cache3, 5);
        freeq.put(cache1, 3);
        freeq.put(cache2, 1);
        freeq.put(cache2, 1);

         memoryCache.put(cache2, user2);

        for (int i = 0; i < 5; i++) {
            memoryCache.get(cache3);
        }
        for (int i = 0; i < 3; i++) {
            memoryCache.get(cache1);
        }


        Assert.assertEquals(freeq, memoryCache.getFrequencyMap());

    }

    @Test
    public void getItemFrequency() throws Exception {

    }

    @Test
    public void getLowFrequencyElement() throws Exception {

    }

}