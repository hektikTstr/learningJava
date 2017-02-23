package com.company.list;

import org.testng.annotations.Test;

import java.util.*;

public class Exercise {

    @Test
    public void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // allowed by autoboxing
        java.util.List listArr = (java.util.List) Arrays.asList(arr);
        Collections.shuffle(listArr);
    }

}
