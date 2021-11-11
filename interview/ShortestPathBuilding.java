package interview;

import java.util.*;

public class ShortestPathBuilding {
    class Solution {
        class Building {
            int x;
            int y;

            Building(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int shortestDistance(int[][] grid) {
            // System.out.println(BFS(grid,3,4,4,0));
            // if(1==1)return 0;
            int maxRows = grid.length;
            int maxCols = 0;
            if (maxRows > 0)
                maxCols = grid[0].length;
            List<Building> buildings = getBuildigs(grid);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < maxRows; i++) {
                for (int j = 0; j < maxCols; j++) {
                    if (grid[i][j] == 1 || grid[i][j] == 2)
                        continue;
                    int total = 0;
                    int path = 0;
                    boolean allbuildings = true;
                    for (Building building : buildings) {
                        path = BFS(grid, i, j, building.x, building.y);
                        if (path == -1)
                            allbuildings = false;
                        else
                            total += path;
                    }
                    if (total < min & total != 0 && allbuildings)
                        min = total;
                }
            }
            return (min == Integer.MAX_VALUE ? -1 : min);
        }

        private List<Building> getBuildigs(int[][] grid) {
            int maxRows = grid.length;
            List<Building> buildings = new ArrayList<Building>();
            int maxCols = 0;
            if (maxRows > 0)
                maxCols = grid[0].length;
            for (int i = 0; i < maxRows; i++) {
                for (int j = 0; j < maxCols; j++) {
                    if (grid[i][j] == 1) {
                        buildings.add(new Building(i, j));
                    }
                }
            }
            return buildings;
        }

        private int adjNodes(int row, int col, int maxRows, int maxCols, int[][] grid, Queue<Integer> rowQ,
                Queue<Integer> colQ, boolean[][] visited) {
            int[] dr = { -1, 1, 0, 0 };
            int[] dc = { 0, 0, 1, -1 };
            int nodeCount = 0;
            for (int i = 0; i < 4; i++) {
                int rr = row + dr[i];
                int cc = col + dc[i];
                if (rr < 0 || cc < 0 || rr >= maxRows || cc >= maxCols || visited[rr][cc] || grid[rr][cc] > 1)
                    continue;
                rowQ.add(rr);
                colQ.add(cc);
                visited[rr][cc] = true;
                nodeCount++;
            }
            return nodeCount;
        }

        int BFS(int[][] grid, int row, int col, int endR, int endC) {
            Queue<Integer> rowQ = new LinkedList<Integer>();
            Queue<Integer> colQ = new LinkedList<Integer>();
            int maxRows = grid.length;
            int maxCols = 0;
            if (maxRows > 0)
                maxCols = grid[0].length;
            boolean[][] visited = new boolean[maxRows][maxCols];
            int distance = 0;
            rowQ.add(row);
            colQ.add(col);
            visited[row][col] = true;
            int nodes_in_layer = 1;
            int nodes_in_nextlayer = 0;
            boolean nextLevel = false;
            while (!rowQ.isEmpty()) {
                row = rowQ.poll();
                col = colQ.poll();
                int val = grid[row][col];
                if (val == 0)
                    nodes_in_nextlayer += adjNodes(row, col, maxRows, maxCols, grid, rowQ, colQ, visited);
                nodes_in_layer--;
                if (row == endR && col == endC) {
                    distance++;
                    break;
                }
                if (nodes_in_layer == 0) {
                    if (nextLevel)
                        distance++;
                    nodes_in_layer = nodes_in_nextlayer;
                    nodes_in_nextlayer = 0;
                }
                nextLevel = true;
            }
            if ((row == endR && col == endC))
                return distance;
            else
                return -1;
        }
    }

    class PathDistance {
        int startx;
        int starty;
        int endx;
        int endy;
        int distance;

        PathDistance(int startx, int starty, int endx, int endy, int distance) {
            this.startx = startx;
            this.starty = starty;
            this.endx = endx;
            this.endy = endy;
            this.distance = distance;
        }

        public boolean equals(Object incoming) {
            PathDistance other = (PathDistance) incoming;
            return other.startx == startx && other.endx == endx && other.starty == starty && other.endy == endy;

        }
    }

}
