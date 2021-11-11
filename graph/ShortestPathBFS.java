package graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBFS {
    public static void main(String[] args) {
        int[][]grid ={  {1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};
        System.out.println(new ShortestPathBFS().shortestPathBinaryMatrix(grid, 3, 4, 4, 0));
    }
    public int shortestPathBinaryMatrix(int[][] grid,int startx,int starty,int endx,int endy) {
        int N = grid.length;
       // if(grid[0][0] == 1 || grid[N - 1][N - 1] == 1){ return -1; }
        int path = 1;
        
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startx,starty));
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                
                Pair curr = queue.poll();
            
                if(!inBounds(curr, N,grid[0].length) || grid[curr.r][curr.c] != 0){ continue; }
                if(curr.r == N - 1 && curr.c == N - 1){ return path; }

                grid[curr.r][curr.c] = 1;

                queue.add(new Pair(curr.r - 1, curr.c));
                queue.add(new Pair(curr.r - 1, curr.c + 1));
                queue.add(new Pair(curr.r, curr.c + 1));
                queue.add(new Pair(curr.r + 1, curr.c + 1));
                queue.add(new Pair(curr.r + 1, curr.c));
                queue.add(new Pair(curr.r + 1, curr.c - 1));
                queue.add(new Pair(curr.r, curr.c - 1));
                queue.add(new Pair(curr.r - 1, curr.c - 1));

            }
            
            path++;
        }
        
        return -1;
    }
    
    private boolean inBounds(Pair curr, int N,int MaxCol){
        return curr.r >= 0 && curr.r < N && curr.c >= 0 && curr.c < MaxCol;
    }
    
    class Pair{
        int r;
        int c;
        Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
