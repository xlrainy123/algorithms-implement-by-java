package graph;

import java.util.*;

public class SimplestDijsktra implements PathSearcher{

     class Node{
        int number;
        int x;
        int y;
        int cost;
        Node parent;
        Node (int x, int y, int number){
            this.number = number;
            this.x = x;
            this.y = y;
        }
        Node (int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "[x="+x+",y="+y+",cost="+cost+"], "+graph[x][y];
        }

        @Override
        public boolean equals(Object o){
            Node node = (Node)o;
            return node.x == x && node.y == y;
        }

        @Override
        public int hashCode(){
            return 7*x+31*y;
        }

        public int caculateCostWith(Node node){
            return node.number;
//            return Math.abs(x-node.x) + Math.abs(y-node.y);
        }
    }

    public final int[][] neighbors = {{1,0},{-1,0},{0,1},{0,-1}};
    public final int[][] graph;

    public SimplestDijsktra(int[][] graph){
        this.graph = graph;
        this.visited = new boolean[graph.length][graph[0].length];
    }

    public int heuristic(Node o1, Node o2){
        return Math.abs(o1.x-o2.x) + Math.abs(o1.y-o2.y);
    }

    @Override
    public void search(int start, int end){
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        Map<Node, Integer> costs = new HashMap<>();
        Node startNode = getStart(start);
        Node endNode = getEnd(end);
        queue.offer(startNode);
        costs.put(startNode, 0);
        visited[startNode.x][startNode.y] = true;
        while (!queue.isEmpty()){
            Node pNode = queue.poll();
            if (pNode.number == end){
                printPaths(pNode);
                return;
            }
            visited[pNode.x][pNode.y] = true;
            for (int i = 0; i < 4; i++){
                int nextX = pNode.x + neighbors[i][0];
                int nextY = pNode.y + neighbors[i][1];
                if (isOutOfBounds(nextX, nextY) || visited[nextX][nextY] || isBarrier(nextX,nextY)){
                    continue;
                }
                Node next = new Node(nextX, nextY, graph[nextX][nextY]);
                int newCost =  costs.get(pNode) + pNode.caculateCostWith(next);
                if (!costs.containsKey(next) || newCost < costs.get(next)){
                    next.cost = newCost + heuristic(endNode,next);
                    next.parent = pNode;
                    costs.put(next, newCost + heuristic(endNode,next));
                    queue.offer(next);
                }
            }
        }
        System.out.println("无路径存在");
    }

    public boolean contains(Map<Node, Integer> costs, int x, int y){
        for (Map.Entry<Node, Integer> entrys : costs.entrySet()){
            Node node = entrys.getKey();
            if (node.x == x && node.y == y){
                return true;
            }
        }
        return false;
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

    public Node getEnd(int end){
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph[0].length; j++){
                if (graph[i][j] == end) {
                    return new Node(i, j, end);
                }
            }
        }
        return null;
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

    public final int barrier = 0;
    public boolean[][] visited;


    public static void main(String[] args) {
        int[][] graph = {
                {2,0,2,4},
                {2,1,2,20},
                {2,0,2,2},
                {2,2,2,3}
        };
        SimplestDijsktra dijsktra = new SimplestDijsktra(graph);
        dijsktra.search(1,3);
    }

    public void test(){
        Node node1 = new Node(1,1,2);
        Node node2 = new Node(1,1,3);
        Node node3 = new Node(2,1,2);
        Map<Node, Integer> costs = new HashMap<>();
        costs.put(node1,1);
        costs.put(node2,1);
        costs.put(node3,1);
        System.out.println(costs);

        node1.cost = 10;
        costs.put(node1,111);
        System.out.println(costs);

    }
}
