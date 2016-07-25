package ru.maria.willey.testing.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemoryCacheTest {

    private Map<Cache, Object> map;
    private MemoryCache memoryCache;

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;

    private Cache cache1;
    private Cache cache2;
    private Cache cache3;
    private Cache cache4;
    private Cache cache5;


    @Before
    public void setUp() throws Exception {

        user1 = new User(1, "Olga", "Ivanova");
        user2 = new User(2, "Ivan", "Petrov");
        user3 = new User(3, "Irina", "Sidorova");
        user4 = new User(4, "Igor", "Kuznetsov");
        user5 = new User(5, "Svetlana", "Petrova");

        map = new ConcurrentSkipListMap<>();

        cache1 = new Cache(user1);
        cache2 = new Cache(user2);
        cache3 = new Cache(user3);
        cache4 = new Cache(user4);
        cache5 = new Cache(user5);

        cache4.setFrequency(15);
        cache5.setFrequency(8);
        cache3.setFrequency(8);
        cache1.setFrequency(6);
        cache2.setFrequency(1);


        map.put(cache1, user1);
        map.put(cache2, user2);
        map.put(cache3, user3);
        map.put(cache4, user4);
        map.put(cache5, user5);


        memoryCache = new MemoryCache(map);


    }

    @Test
    public void getMostFrequent() throws Exception {
        List<Cache> list = Arrays.asList(cache2, cache1, cache3, cache5, cache4);
        Assert.assertEquals(list, memoryCache.geSortFrequent(map));

    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(user1, map.get(cache1));

    }

    @Test
    public void put() throws Exception {
        User user6 = new User(6, "Harry", "Potter");
        Cache cache6 = new Cache(user6);
        memoryCache.put(cache6, user6);
        map.put(cache6, user6);

        Assert.assertEquals(map, memoryCache.getCacheMap());

    }

    @Test
    public void count() throws Exception {
        Assert.assertEquals(5, memoryCache.count());

    }


    @Test
    public void remove() throws Exception {
        memoryCache.remove(cache5);
        Assert.assertEquals(4, memoryCache.count());

    }

    @Test
    public void clear() throws Exception {
        memoryCache.clear();

    }

    @Test
    public void getSortedMap() {
        Map<Cache, Object> freeq = new ConcurrentSkipListMap<>();
        freeq.put(cache4, user4);
        freeq.put(cache5, user5);
        freeq.put(cache3, user3);
        freeq.put(cache1, user1);
        freeq.put(cache2, user2);


        Assert.assertEquals(freeq, memoryCache.getCacheMap());
    }

    @Test
    public void removingOldItems() throws Exception {
        memoryCache.removingOldItems();
        Assert.assertEquals(0, memoryCache.count());


    }

}