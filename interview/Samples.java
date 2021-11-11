package interview;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Samples {
    public static void main(String[] args) {
        long[] dp = new long[151];
        for (int i = 0; i < 151; i++)
            dp[i] = 0;
        System.out.println(Calendar.getInstance().getTime());
        System.out.println("The 0th fibonacci number is: " + fib(4, dp));
        System.out.println("The 5th fibonacci number is: " + fib(5, dp));
        System.out.println("The 150th fibonacci number is: " + fib(150, dp));
        System.out.println(Calendar.getInstance().getTime());
        int limit = (int) Math.round(Math.sqrt(10));
        int square_nums[] = new int[limit];
        for (int i = 1; i <= limit; ++i) {
            square_nums[i - 1] = i * i;
        }
        System.out.println();
        for (int i = 1; i <= limit; ++i) {

        }
    }

    static long fib(int n, long[] dp) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (dp[n] != 0)
            return dp[n];
        dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
        return dp[n];
    }

    public boolean validPath(int n, int[][] edges, int start, int end) {
        boolean visited[] = new boolean[n];
        LinkedList<Integer> adj[] = new LinkedList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new LinkedList();

        }
        for (int j = 0; j < edges.length; j++)
            adj[edges[j][0]].add(edges[j][1]);
        return dfs(start, end, visited, adj);
    }

    boolean dfs(int start, int end, boolean visited[], LinkedList<Integer> adjList[]) {
        if (start == end)
            return true;
        visited[start] = true;
        LinkedList<Integer> linkedList = adjList[start];
        for (int adj : linkedList) {
            if (visited[adj])
                continue;
            return dfs(adj, end, visited, adjList);
        }
        return false;
    }
}
