class Solution {
    static int minDeletions(String s) {
        // code here
        
         int n = s.length();
        int[][] dp = new int[n][n];

        // Every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the table
        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i <= n - cl; i++) {
                int j = i + cl - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (cl == 2) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        int lps = dp[0][n - 1];
        return n - lps; // Minimum deletions
    }

    // Optional main method to test
    public static void main(String[] args) {
        System.out.println(minDeletions("aebcbda"));        // Output: 2
        System.out.println(minDeletions("geeksforgeeks"));  // Output: 8cc
        
    }
}