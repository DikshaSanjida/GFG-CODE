//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String str = br.readLine();

            Solution obj = new Solution();
            System.out.println(obj.findSubString(str));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    public int findSubString(String str) {
        // code here
        if (str.length() == 0) return 0;

        // Count all unique characters in the string
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : str.toCharArray()) {
            uniqueChars.add(ch);
        }
        int requiredCharCount = uniqueChars.size();

        // Map to count characters in the current window
        Map<Character, Integer> windowCharCount = new HashMap<>();

        int left = 0, minLen = Integer.MAX_VALUE;
        int count = 0;

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            windowCharCount.put(rightChar, windowCharCount.getOrDefault(rightChar, 0) + 1);

            // Only increase count when character is counted for the first time in window
            if (windowCharCount.get(rightChar) == 1) {
                count++;
            }

            // When all unique characters are in the current window
            while (count == requiredCharCount) {
                minLen = Math.min(minLen, right - left + 1);

                // Try to reduce the window size from the left
                char leftChar = str.charAt(left);
                windowCharCount.put(leftChar, windowCharCount.get(leftChar) - 1);

                if (windowCharCount.get(leftChar) == 0) {
                    count--;
                }
                left++;
            }
        }

        return minLen;
        
    }
}