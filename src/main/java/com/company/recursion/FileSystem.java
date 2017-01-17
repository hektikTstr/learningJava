package com.company.recursion;

import java.io.File;

public class FileSystem {
    public static long diskUsage(File root) {
        long total = root.length();
        if (root.isDirectory()) {
            for (String childName : root.list()) {
                File child = new File(root, childName);
                total += diskUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }

    public static void main(String[] args) {
        diskUsage(new File("/Users/shawn.zhang/dev/GlipAuto/"));
    }
}
