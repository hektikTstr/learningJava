package com.company.cc186.arr;

import org.testng.annotations.Test;

public class StringBuilder {
    private int DEFAULT_LENGTH = 8;
    private char[] ch_arr;
    private int size;

    public StringBuilder() {
        ch_arr = new char[DEFAULT_LENGTH];
        size = 0;
    }

    public StringBuilder(String str) {
        ch_arr = new char[DEFAULT_LENGTH + str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch_arr[i] = str.charAt(i);
        }
        size = str.length();
    }

    private void resize(int new_size) {
        if (new_size <= ch_arr.length)
            throw new IllegalArgumentException("Resize size value should not be less than or equal to its original value.");
        char[] new_ch_arr = new char[new_size];
        for (int i = 0; i < ch_arr.length; i++) {
            new_ch_arr[i] = ch_arr[i];
        }
        ch_arr = new_ch_arr;
    }

    public StringBuilder setCharAt(int index, char ch) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("The index is out of the existing string.");
        ch_arr[index] = ch;
        return this;
    }

    public StringBuilder insert(int index, String str) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("The index is out of the existing string.");
        if (str == null)
            throw new IllegalArgumentException("The inserted string is null");
        if (str.isEmpty())
            return this;
        if (str.length() + size > ch_arr.length)
            resize(2 * (ch_arr.length + str.length()));
        for (int i = size - 1; i >= index; i--) {
            ch_arr[i + str.length()] = ch_arr[i];
        }
        for (int i = 0; i < str.length(); i++) {
            ch_arr[i + index] = str.charAt(i);
        }
        size += str.length();
        return this;
    }

    public StringBuilder append(String str) {
        return insert(size, str);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public StringBuilder reverse() {
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            swap(ch_arr, i, j);
        }
        return this;
    }

    public String toString() {
        return new String(ch_arr, 0, size);
    }

    @Test
    public void test() {
        testStringBuilder(new StringBuilder());
        testStringBuilder(new StringBuilder("7777"));
    }

    private void testStringBuilder(StringBuilder stringBuilder) {
        System.out.println(stringBuilder.append("a"));
        System.out.println(stringBuilder.append("cb"));
        System.out.println(stringBuilder.insert(0, "1234567890"));
        System.out.println(stringBuilder.insert(13, "1234567890"));
        System.out.println(stringBuilder.insert(12, "1234567890"));
        System.out.println(stringBuilder.reverse());
    }
}
