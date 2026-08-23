// 2.4 - @SortComparator
package com.dicampus.j2ee.model;

import java.util.Comparator;

public class CancionEntityComparatorByCantante implements Comparator<CancionEntity> {

    @Override
    public int compare(CancionEntity o1, CancionEntity o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        if (o1.getAutor() != null && o2.getAutor() != null) {
            if (o1.getAutor().getId() == o2.getAutor().getId()) {
                return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
            }
            return Long.compare(o1.getAutor().getId(), o2.getAutor().getId());
        }
        return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
    }
}