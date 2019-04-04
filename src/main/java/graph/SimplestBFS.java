package graph;

import java.util.LinkedList;
import java.util.Queue;

public class SimplestBFS implements PathSearcher{

    class Node{
        int number;
        int x;
        int y;
        Node parent;
        Node (int x, int y, int number){
            this.number = number;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "["+x+","+y+"]="+graph[x][y];
        }
    }

    public final int[][] graph;
    public final int barrier = 0;
    public boolean[][] visited;
    public final int[][] neighbors = {{1,0},{-1,0},{0,1},{0,-1}};
    public SimplestBFS(int[][] graph){
        this.graph = graph;
        this.visited = new boolean[graph.length][graph[0].length];
    }

    public Node getStart(int start){
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph[0].length; j++){
                if (graph[i][j] == start) {
                    return new Node(i, j, start);
                }
            }
        }
        return null;
    }

    @Override
    public void search(int start, int end){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = getStart(start);
        if (startNode == null) {
            System.out.println("start point does not exist!");
            System.exit(1);
        }
        //没问题
        queue.offer(startNode);
        visited[startNode.x][startNode.y] = true;
        while (!queue.isEmpty()){
            Node pNode = queue.poll();
            if (pNode.number == end){
                printPaths(pNode);
                break;
            }
            for (int i = 0; i < 4; i++){
                int nextX = pNode.x + neighbors[i][0];
                int nextY = pNode.y + neighbors[i][1];
                if (isOutOfBounds(nextX, nextY) || visited[nextX][nextY] || isBarrier(nextX,nextY)){
                    continue;
                }
                visited[nextX][nextY] = true;
                Node nextNode = new Node(nextX, nextY, graph[nextX][nextY]);
                nextNode.parent = pNode;
                queue.offer(nextNode);
            }
        }
    }

    public void printPaths(Node pNode){
        while (pNode != null){
            System.out.println(pNode);
            pNode = pNode.parent;
        }
    }

    public boolean isOutOfBounds(int x, int y){
        if (x < 0 || x >= graph.length || y < 0 || y >= graph[0].length){
            return true;
        }
        return false;
    }

    public boolean isBarrier(int x, int y){
        if (graph[x][y] == barrier){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] graph = {
                {2,0,2,2},
                {2,1,2,0},
                {2,0,2,2},
                {2,2,2,3}
        };
        SimplestBFS bfs = new SimplestBFS(graph);
        bfs.search(1,3);
    }
}
