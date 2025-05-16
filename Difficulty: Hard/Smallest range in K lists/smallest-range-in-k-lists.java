//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

public class DriverClass {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int arr[][] = new int[k][n];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) arr[i][j] = sc.nextInt();
            }
            ArrayList<Integer> range = new Solution().findSmallestRange(arr);
            System.out.println(range.get(0) + " " + range.get(1));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    static class Node {
        int val;    // the value from the array
        int row;    // index of the list
        int col;    // index in the list

        Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }
    public ArrayList<Integer> findSmallestRange(int[][] arr) {
        // code here
        int k = arr.length;
        int n = arr[0].length;

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        int max = Integer.MIN_VALUE;
        
        // Initialize minHeap with the first element of each list
        for (int i = 0; i < k; i++) {
            int value = arr[i][0];
            minHeap.offer(new Node(value, i, 0));
            max = Math.max(max, value);
        }

        int start = 0, end = Integer.MAX_VALUE;

        while (minHeap.size() == k) {
            Node node = minHeap.poll();
            int min = node.val;

            if (max - min < end - start) {
                start = min;
                end = max;
            }

            // If there is a next element in the same row, add it to the heap
            if (node.col + 1 < n) {
                int nextVal = arr[node.row][node.col + 1];
                minHeap.offer(new Node(nextVal, node.row, node.col + 1));
                max = Math.max(max, nextVal);
            } else {
                // One of the lists is exhausted, stop
                break;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(start);
        result.add(end);
        return result;
        
    }
}