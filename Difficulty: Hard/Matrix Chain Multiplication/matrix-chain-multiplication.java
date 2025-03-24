//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Geeks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int g = 0; g < t; g++) {
            String[] str = (br.readLine()).trim().split(" ");
            int arr[] = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            System.out.println(new Solution().matrixMultiplication(arr));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    static int matrixMultiplication(int arr[]) {
        // code here
        int n=arr.length;
        int dp[][]=new int[n][n];
        
        for(int i=1;i<n;i++){
            dp[i][i]=0;
        }
        
        for(int l=2;l<n;l++){
            for(int i=1;i<n-l+1;i++){
                int j=i+l-1;
                dp[i][j]=Integer.MAX_VALUE;
                
                for(int k=i;k<j;k++){
                    int cost=dp[i][k] + dp[k+1][j] + arr[i-1] * arr[k] * arr[j];
                    
                    if(cost < dp[i][j]){
                        dp[i][j]=cost;
                    }
                }
            }
        }
        
        return dp[1][n-1];
        
    }
}