package com.company.arrary;

import java.util.Arrays;

public class CaecarCipher {
    private int rotation = 0;
    protected char[ ] encoder = new char[26];
    protected char[ ] decoder = new char[26];
    public CaecarCipher(int rotation) {
        this.rotation = rotation % 26;
        for (int k=0; k < 26; k++) {
            encoder[k] = (char) ('A' + (k + this.rotation) % 26);
            decoder[k] = (char) ('A' + (k - this.rotation + 26) % 26);
        }
    }

    public String encrypt(String message) {
        char[] temp =  message.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (Character.isUpperCase(temp[i])) {
                char c = (char) (temp[i] + rotation);
                if (c > 'Z') {
                    c -= 26;
                }
                temp[i] = c;
            }
        }
        return String.valueOf(temp);
    }

    public String encryptUsingMap(String message) {
        return transform(message, encoder);
    }

    public String decryptUsingMap(String message) {
        return transform(message, decoder);
    }

    public String transform(String message, char[] coder) {
        char[] temp =  message.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (Character.isUpperCase(temp[i])) {
                temp[i] = coder[temp[i] - 'A'];
            }
        }
        return String.valueOf(temp);
    }

    public String decrypt(String message) {
        char[] temp =  message.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (Character.isUpperCase(temp[i])) {
                char c = (char) (temp[i] - rotation);
                if (c < 'A') {
                    c += 26;
                }
                temp[i] = c;
            }
        }
        return String.valueOf(temp);
    }
    public static void main(String[] args) {
        CaecarCipher caecarCipher = new CaecarCipher(3);
        String orig = "THE EAGLE IS IN PLAY; MEET AT JOE'S.";
        String coded = caecarCipher.encrypt(orig);
        System.out.println(coded);
        System.out.println(caecarCipher.decrypt(coded));

        System.out.println(Arrays.toString(caecarCipher.encoder));
        System.out.println(Arrays.toString(caecarCipher.decoder));

        coded = caecarCipher.encryptUsingMap(orig);
        System.out.println(coded);
        System.out.println(caecarCipher.decryptUsingMap(coded));
    }
}
