public class CoinChange {
    public static int changeCoin(int[] coins, int amount, int index, String str) {
        int count = 0;
        if (amount == 0) {
            System.out.println(str);
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        for (int i = index; i < coins.length; i++) {
            count += changeCoin(coins, amount - coins[i], i, str + coins[i] + ",");
        }
        return count;
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 10, 25};
        System.out.println(changeCoin(coins, 10, 0, ""));
    }
}