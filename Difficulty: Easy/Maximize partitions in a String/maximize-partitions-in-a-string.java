//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            String s = sc.next();
            Solution obj = new Solution();
            int ans = obj.maxPartitions(s);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    public int maxPartitions(String s) {
        // code here

        // Map to store the last occurrence of each character
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        
        // Store the last occurrence of each character in the string
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }
        
        int partitions = 0;
        int maxReach = 0;
        int lastCut = -1;
        
        // Traverse the string and make partitions
        for (int i = 0; i < s.length(); i++) {
            maxReach = Math.max(maxReach, lastOccurrence.get(s.charAt(i)));
            
            // If the current index reaches maxReach, we can make a partition
            if (i == maxReach) {
                partitions++;
                lastCut = i;
            }
        }
        
        return partitions;
    }
}