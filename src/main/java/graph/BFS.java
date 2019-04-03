package graph;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    public static int startX;
    public static int startY;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] size = in.nextLine().split(" ");
        int row = Integer.parseInt(size[0]);
        int col = Integer.parseInt(size[1]);
        int[][] graph = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                graph[i][j] = in.nextInt();
                if (graph[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (graph[i][j] == 0 || graph[i][j] == 2){
                    continue;
                }
                bfs(startX, startY, i, j, graph);
            }
        }
//        bfs(startX, startY, graph);
    }

    public static void bfs(int startX, int startY, int endX, int endY, int[][] graph) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        Node start = new Node(startX, startY, 0);
        queue.offer(start);
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.x + "," + node.y);
            for (int i = 0; i < dir.length; i++) {
                int nextX = node.x + dir[i][0];
                int nextY = node.y + dir[i][1];
                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                    continue;
                }
                if (graph[nextX][nextY] == 0) { //0 表示不通
                    continue;
                }
                if (nextX == endX && nextY == endY) {
                    System.out.println("最短路劲是：" + (node.step+1));
                    System.out.println("-------------"+endX+","+endY);
                    return;
                }
                if (!visited[nextX][nextY]) {
                    queue.offer(new Node(nextX, nextY, node.step + 1));
                    visited[nextX][nextY] = true;
                }
            }
        }
        System.out.println("不存在最短路径");
    }

    public static void offer(Queue<Node> queue, Node node) {
        queue.offer(node);
    }

    public static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Node {
        public int x;
        public int y;
        public int step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }


}
