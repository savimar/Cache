package ru.maria.willey.testing.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by User on 024 24.07.16.
 */
public class ComparatorMap implements Comparator {
    Map base;

    public ComparatorMap(Map base) {
        this.base = base;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if ((Integer) base.get(o1) < (Integer) base.get(o2)) {
            return 1;
        } else if ((Integer) base.get(o1) == (Integer) base.get(o2)) {
            return 1;
        } else {
            return -1;
        }
    }
}

