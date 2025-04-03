//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int mat[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) mat[i][j] = sc.nextInt();
            }
            Solution obj = new Solution();
            int ans = obj.orangesRotting(mat);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    public int orangesRotting(int[][] mat) {
        // Code here
        if (mat == null || mat.length == 0) return -1;

        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        int timeElapsed = 0;

        // Step 1: Add all rotten oranges to the queue and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 2) {
                    queue.offer(new int[]{i, j});  // Add rotten orange to the queue
                } else if (mat[i][j] == 1) {
                    freshCount++;  // Count fresh oranges
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (freshCount == 0) return 0;

        // Step 2: Define the 4 possible directions (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Step 3: BFS traversal to rot adjacent fresh oranges
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int r = current[0], c = current[1];

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    // Check if the adjacent cell is valid and has a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && mat[newRow][newCol] == 1) {
                        mat[newRow][newCol] = 2;  // Rot the fresh orange
                        queue.offer(new int[]{newRow, newCol});  // Add it to the queue
                        freshCount--;  // Reduce fresh orange count
                        rotted = true;
                    }
                }
            }

            // Increase time only if we rotted at least one orange in this round
            if (rotted) timeElapsed++;
        }

        // Step 4: If there are still fresh oranges left, return -1
        return (freshCount == 0) ? timeElapsed : -1;
    }
}