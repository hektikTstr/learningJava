package com.company.recursion;

public class PowerComputing {
    public static int indexEffectivePower = 0;
    public static int indexEPower = 0;
    public static double power(double x, int n) {
        indexEPower++;
        if (n == 0) {
            return 1;
        } else {
            return x * power(x, n - 1);
        }
    }

    public static double effectivePower(double x, int n) {
        indexEffectivePower++;
        if (n == 0) {
            return 1;
        } else {
            int mid = n / 2;
            double total = effectivePower(x, mid);
            total *= total;
            if (n % 2 != 0) {
                total *= 2;
            }
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println(power(2, 32));
        System.out.println(effectivePower(2, 32));
        System.out.println(indexEPower);
        System.out.println(indexEffectivePower);
    }
}
