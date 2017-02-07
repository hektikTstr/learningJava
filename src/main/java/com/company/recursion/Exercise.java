package com.company.recursion;

import com.company.fundamental.linkList.SinglyLinkedList;

import java.util.*;

public class Exercise {
    private static int index = 0;
    // R-5.1
    private static int findMax(int[] a, int low, int high) {
        index++;
        if (low >= high) {
            return a[low];
        } else {
            int mid = (low + high) / 2;
            int temp1 = findMax(a, low, mid);
            int temp2 = findMax(a, mid + 1, high);
            return Math.max(temp1, temp2);
        }
    }

    private static double harmonic(int n) {
        if (n == 1) {
            return 1.0;
        } else {
            return 1.0 / n + harmonic(n - 1);
        }
    }

    private static int stringToInt(String str, int index) {
        if (index == str.length() - 1) {
            return str.charAt(index) - '0';
        } else {
            double power = str.length() - index - 1;
            return (int) Math.pow(10.0, power) * (str.charAt(index) - '0') + stringToInt(str, index + 1);
        }
    }

    private static int powerIterative(int x, int y) {
        int res = 1;     // Initialize result
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = res*x;

            // n must be even now
            y = y >> 1; // y = y/2
            x = x * x;  // Change x to x^2
        }
        return res;
    }

    // R-5.10
    private static int twoDimensionalArrSum(int[][] arr, int n) {
        if (n == 1) {
            return arr[0][0];
        } else {
            int temp = 0;
            int total = 0;
            while (temp < n) {
                total += arr[temp][n - 1] + arr[n - 1][temp];
                temp++;
            }
            total -= arr[n - 1][n - 1];
            return total + twoDimensionalArrSum(arr, n - 1);
        }
    }

    // C-5.11
    private static int logarithm(int n) {
        if (n < 2) {
            return 0;
        } else {
            return logarithm(n / 2) + 1;
        }
    }

    // C-5.12
    private static boolean uniqueness(int[] arr, int n, int x) {
        if (n == 1) {
            return true;
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] == x) {
                    return false;
                }
            }
            return uniqueness(arr, n - 1, arr[n - 2]);
        }
    }

    // C-5.13
    private static int production(int m, int n) {
        if (n == 1) {
            return m;
        } else {
            return m + production(m, n - 1);
        }
    }

    // C-5.15
    private static <T> Set<Set> outputAllUniqueSubsets(Set<T> origSet) {
        if (origSet.size() == 0) {
            Set<T> subset = new HashSet<>();
            Set<Set> set = new HashSet<>();
            set.add(subset);
            return set;
        } else {
            T elem = (T) origSet.toArray()[0];
            origSet.remove(elem);
            Set<Set> subset = outputAllUniqueSubsets(origSet);
            Set<Set> newSubset = new HashSet<>();
            for (Set set : subset) {
                Set tempSet = new HashSet(set);
                tempSet.add(elem);
                newSubset.add(set);
                newSubset.add(tempSet);
            }
            return newSubset;
        }
    }

    // C-5.16
    private static void hanoiTower(int n, char start, char aux, char end) {
        if (n == 1) {
            System.out.println("move " + n + " from " + start + " to " + end);
        } else {
            hanoiTower(n - 1, start, end, aux);
            hanoiTower(1, start, aux, end);
            hanoiTower(n - 1, aux, start, end);
        }
    }

    // C-5.17
    private static String reverseString(String s) {
        if (s.length() == 1) {
            return s;
        } else {
            return s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
        }
    }

    // C-5.18
    private static boolean palindrome(String str, int start, int end) {
        if (start > end) {
            return true;
        } else {
            return str.charAt(start) == str.charAt(end)
                    && palindrome(str, start + 1, end - 1);
        }
    }

    // C-5.19
    private static boolean moreVowels(String s, int c){
        if (s.length() == 0)
            return (c>0);
        if(s.charAt(s.length()-1)=='a'
                ||s.charAt(s.length()-1)=='e'
                ||s.charAt(s.length()-1)=='i'
                ||s.charAt(s.length()-1)=='o'
                ||s.charAt(s.length()-1)=='u')
            c++;
        else
            c--;
        return moreVowels(s.substring(0, s.length()-1),c);
    }

    // C-5.20
    private static void evenLeftOddRight(int[] a, int indexLeft, int indexRight) {
        if (indexRight > a.length - 1) {
            return;
        } else {
            int left = a[indexLeft];
            int right = a[indexRight];
            if (left % 2 == 1) {
                if (right % 2 == 1) {
                    evenLeftOddRight(a, indexLeft, indexRight + 1);
                } else {
                    int temp = a[indexLeft];
                    a[indexLeft] = a[indexRight];
                    a[indexRight] = temp;
                    System.out.println(Arrays.toString(a));
                    for (int i = indexLeft + 1; i <= indexRight; i++) {
                        if (a[i] % 2 == 1) {
                            indexLeft = i;
                            break;
                        }
                    }
                    evenLeftOddRight(a, indexLeft, indexRight + 1);
                }
            } else {
                evenLeftOddRight(a, indexLeft + 1, indexRight + 1);
            }
        }
    }

    // C-5.20 prioritized
    private static int[] evenBeforeOdd(int[] arr, int start, int end) {
        if (start == end) {
            return arr;
        } else {
            for (int i = start; i < end; i++) {
                if (arr[i] % 2 != 0) {
                    if (arr[end] % 2 == 0) {
                        swap(arr, i, end);
                        System.out.println(Arrays.toString(arr));
                    }
                    return evenBeforeOdd(arr, i, end - 1);
                }
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    // C-5.21
    private static int[] sort(int[] a, int start, int end, int x) {
        if (start == end) {
            return a;
        } else {
            for (int i = start; i < end; i++) {
                if (a[i] > x) {
                    if (a[end] <= x) {
                        swap(a, i, end);
                        System.out.println(Arrays.toString(a));
                        continue;
                    }
                    return sort(a, i, end - 1, x);
                }
            }
        }
        return a;
    }

    // C-5.22
    private static void selectTwoNumsSumUpEqualTo(int[] a, int start, int target) {
        if (start < a.length - 1) {
            for (int i = start + 1; i < a.length; i++) {
                if (a[i] + a[start] == target) {
                    System.out.println(a[i] + ", " + a[start]);
                    break;
                }
            }
            selectTwoNumsSumUpEqualTo(a, start + 1, target);
        }
    }

    // C-5.23
    private static void selectTwoPrevNumsSumUpEqualTo(int[] a, int start, int x) {
        if (start < x - 1) {
            for (int i = start + 1; i < x; i++) {
                if (a[start] + a[i] == a[x]) {
                    System.out.println(a[i] + ", " + a[start] + " = " + a[x]);
                }
            }
            selectTwoPrevNumsSumUpEqualTo(a, start + 1, x);
        }
    }

    // C-5.25
    private static <T> SinglyLinkedList.Node reverseSingularLinkedList(SinglyLinkedList<T> list, SinglyLinkedList.Node node) {
        if (list.isEmpty() || list.size() == 1) {
            return null;
        }
        if (node.getNext() == null) {
            list.head.setNext(null);
            list.tail = list.head;
            list.head = node;
        } else {
            SinglyLinkedList.Node tempNode = reverseSingularLinkedList(list, node.getNext());
            tempNode.setNext(node);
        }
        return node;
    }
    public static void main(String[] args) {
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.addFirst("yang");
        singlyLinkedList.addFirst("xiao");
        singlyLinkedList.addFirst("zhang");
        reverseSingularLinkedList(singlyLinkedList, singlyLinkedList.head);
//        int[] a = {1, 0, 4, 9, 2, 6, 5, 4, 10, 3};
//        System.out.println(findMax(a, 0, a.length - 1));
//        System.out.println(index);
//        System.out.println(harmonic(3));
//        System.out.println(stringToInt("012340550", 0));
//        System.out.println(powerIterative(2, 3 ));
//        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        System.out.println(twoDimensionalArrSum(a, 3));
//        System.out.println(logarithm(16));
//        System.out.println(production(5, 100));
//        int[] a = {1, 0, 4, 9, 2, 6, 5, 11, 10, 3};
//        int[] a = {1, 2, 3};
//        System.out.println(uniqueness(a, a.length, 3));
//        Set<Integer> a = new HashSet<>();
//        a.add(1);
//        a.add(2);
//        a.add(3);
//        a.add(4);
//        Set<Set> b = outputAllUniqueSubsets(a);
//        System.out.println(b.toString());
        hanoiTower(3, 'a', 'b', 'c');
//        String s = "pots&pans";
//        System.out.println(reverseString(s));
//        String s = "gohangasalamiimalasagnahog";
//        System.out.println(palindrome(s, 0, s.length() - 1));
//        String s = "ouabdgcei";
//        System.out.println(moreVowels(s, 0));
//        int a[] = {88,11,23,77,1,3,5,7,9,11,13,2,4,6,8,10,12,14,16,18};
//        int a[] = {1,8,2,4,4,5};
//        evenLeftOddRight(a, 0, 1);
//        evenBeforeOdd(a, 0, a.length - 1);
//        System.out.println(Arrays.toString(a));
//        sort(a, 0, a.length - 1, 10);
//        int a[] = {1, 2, 7, 3, 5, 7, 6, 4, 8, 0, -1, 12, 9};
//        selectTwoNumsSumUpEqualTo(a, 0, 8);
//        selectTwoPrevNumsSumUpEqualTo(a, 0, 11);
    }
}
