class Solution {
    static int noOfWays(int m, int n, int x) {
        // code here
        int[][] dp = new int[n + 1][x + 1];

        // Base case: 0 ways to make any sum with 0 dice except sum 0
        dp[0][0] = 1;

        for (int dice = 1; dice <= n; dice++) {
            for (int sum = 1; sum <= x; sum++) {
                dp[dice][sum] = 0;
                for (int face = 1; face <= m && face <= sum; face++) {
                    dp[dice][sum] += dp[dice - 1][sum - face];
                }
            }
        }

        return dp[n][x];
    }
};