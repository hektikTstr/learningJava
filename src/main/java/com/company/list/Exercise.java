package com.company.list;

import org.testng.annotations.Test;

import java.util.*;

public class Exercise<E> {

    @Test
    public void test() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // allowed by autoboxing
        java.util.List listArr = (java.util.List) Arrays.asList(arr);
        Collections.shuffle(listArr);

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] arrChance = new int[8][8];
        for (int i = 0; i < 1000; i++) {
            shuffle(arr1);
            for (int k = 0; k < arr1.length; k++) {
                arrChance[arr1[k] - 1][k]++;
            }
        }
    }

    // C-7.28
    public static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomInt = random.nextInt(arr.length);
            int temp = arr[randomInt];
            arr[randomInt] = arr[i];
            arr[i] = temp;
        }
    }

    public static <E> void shuffle(E[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomInt = random.nextInt(arr.length);
            E temp = arr[randomInt];
            arr[randomInt] = arr[i];
            arr[i] = temp;
        }
    }

    @Test
    public void test1() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(8));
        }
    }

    // C-7.56
    public static class DataPacket {
        private String msg;
        private int ordinal;

        public DataPacket(String msg, int ordinal) {
            this.msg = msg;
            this.ordinal = ordinal;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getOrdinal() {
            return ordinal;
        }

        public void setOrdinal(int ordinal) {
            this.ordinal = ordinal;
        }
    }

    public static DataPacket[] msgAssembler(String msg) {
        if (msg == null) {
            throw new IllegalArgumentException();
        }
        DataPacket[] dataPackets = new DataPacket[msg.length()];
        for (int i = 0; i < dataPackets.length; i++) {
            dataPackets[i] = new DataPacket(msg.substring(i, i + 1), i);
        }
        return dataPackets;
    }

    @Test
    public void test2() {
        DataPacket[] dataPackets = msgAssembler("Hello! How are you?");
        shuffle(dataPackets);
        System.out.println(msgReassembler(dataPackets));
    }

    // C-7.56
    public static String msgReassembler(DataPacket[] dataPackets) {
        for (int i = 1; i < dataPackets.length; i++) {
            int finalIndex = i;
            DataPacket temp = dataPackets[i];
            for (int k = i - 1; k >= 0; k--) {
                if (temp.getOrdinal() > dataPackets[k].getOrdinal()) {
                    break;
                } else {
                    finalIndex = k;
                    dataPackets[k + 1] = dataPackets[k];
                }
            }
            if (finalIndex != i) {
                dataPackets[finalIndex] = temp;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dataPackets.length; i++) {
            result.append(dataPackets[i].getMsg());
        }
        return result.toString();
    }
}
