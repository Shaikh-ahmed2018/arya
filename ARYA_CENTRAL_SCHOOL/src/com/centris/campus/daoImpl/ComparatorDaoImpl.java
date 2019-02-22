package com.centris.campus.daoImpl;

import java.util.Comparator;
import java.util.HashMap;

public class ComparatorDaoImpl implements Comparator<String> {

    HashMap<String, Integer> base;

    public ComparatorDaoImpl(HashMap<String, Integer> base) {
        this.base = base;
    }
    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }


}
