//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class Node {
    Node next;
    int val;

    public Node(int data) {
        val = data;
        next = null;
    }
}

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t;
        t = Integer.parseInt(in.readLine());
        while (t-- > 0) {

            Node head, tail;
            String s[] = in.readLine().trim().split(" ");
            int num = Integer.parseInt(s[0]);
            head = new Node(num);
            tail = head;
            for (int i = 1; i < s.length; i++) {
                num = Integer.parseInt(s[i]);
                tail.next = new Node(num);
                tail = tail.next;
            }
            Solution ob = new Solution();
            Node temp = ob.primeList(head);
            while (temp != null) {
                out.print(temp.val + " ");
                temp = temp.next;
            }
            out.println();
            out.println("~");
        }
        out.close();
    }
}
// } Driver Code Ends


// User function Template for Java
/*
class Node{
    Node next;
    int val;
    public Node(int data){
        val=data;
        next=null;
    }
}
*/

class Solution {
    
    static final int MAX = 20000;
    static boolean[] isPrime = new boolean[MAX + 1];

    // Static block to initialize prime numbers using Sieve of Eratosthenes
    static {
        for (int i = 0; i <= MAX; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    Node primeList(Node head) {
        // code here
        Node temp = head;

        while (temp != null) {
            int val = temp.val;

            // If current value is not prime, find the nearest prime
            if (!isPrime[val]) {
                int left = val - 1;
                int right = val + 1;

                while (true) {
                    boolean leftPrime = (left >= 2 && isPrime[left]);
                    boolean rightPrime = (right <= MAX && isPrime[right]);

                    if (leftPrime && rightPrime) {
                        temp.val = left; // Choose smaller one if both at equal distance
                        break;
                    } else if (leftPrime) {
                        temp.val = left;
                        break;
                    } else if (rightPrime) {
                        temp.val = right;
                        break;
                    }

                    left--;
                    right++;
                }
            }

            temp = temp.next;
        }

        return head;
    }
}