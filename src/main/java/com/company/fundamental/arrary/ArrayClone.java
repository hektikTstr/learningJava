package com.company.fundamental.arrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayClone {
    public class Person implements Cloneable {
        public int age;
        public String[] name;
        public Person(int age, String[] name) {
            this.age = age;
            this.name = name.clone();
        }
        @Override
        public Person clone() {
            return new Person(this.age, this.name);
        }
    }

    public Person main(int i, String[] strings) {
        return new Person(i, strings);
    }

    public static Person[][] deepClone(Person[][] persons) {
        Person[][] persons1 = new Person[persons.length][];
        return persons1;
    }

    public static void main(String[] args) {
        ArrayClone arrayClone = new ArrayClone();
        Person a = arrayClone.main(5, new String[]{"abc", "def"});
        Person b = a.clone();
        b.name = new String[]{"", ""};
        b.name[1] = "123";
        Person[] persons = new Person[] { arrayClone.main(1, new String[]{"abc", "def"}), arrayClone.main(2, new String[]{"abcd", "defg"})};
        Person[] persons1 = persons.clone();
        Person[] persons2 = new Person[persons.length];
        for (int i = 0; i < persons.length; i++) {
            persons2[i] = persons[i].clone();
        }

        int[] c = {1, 2, 3};
        int[] d = c.clone();
        List e = Arrays.asList(c);
//        int[] f = e.toArray(new Object[3]);
//        d = Arrays.asList(c).toArray();
        d = Arrays.copyOf(c, d.length);
        System.arraycopy(c, 0, new int[c.length], 0, c.length);
    }
}
