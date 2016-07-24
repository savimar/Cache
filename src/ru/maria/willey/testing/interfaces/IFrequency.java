package ru.maria.willey.testing.interfaces;

import ru.maria.willey.testing.model.Cache;
import ru.maria.willey.testing.util.CacheException;

import java.util.Map;

/**
 * Created by User on 024 24.07.16.
 */
public interface IFrequency<K> {
    Map.Entry<Cache, Integer> getBigFrequencyElement();
    int getItemFrequency(K key) throws CacheException;
    Map.Entry<Cache, Integer> getLowFrequencyElement();

}
