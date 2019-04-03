package graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijsktra {

    public int[][] graph;
    public Integer[] dist;
    public List<Node> nodes;
    public boolean[] visited;
    public Integer[] prev;
    public void init(int[][] graph){
        this.graph = graph;
        dist = new Integer[graph.length];
        nodes = new ArrayList<>();
        visited = new boolean[graph.length];
        prev = new Integer[graph.length];
    }

    public void shortestPath(int[][] graph, int start){
        System.out.println(graph.length);
        System.out.println(graph[0].length);
        init(graph);
        for (int i = 0; i < graph.length; i++){
            dist[i] = graph[start][i] == 0 ? Integer.MAX_VALUE : graph[start][i];
            if (graph[start][i] == 0){
                prev[i] = -1;
            }else {
                prev[i] = start;
            }
        }
        System.out.println("最开始："+Arrays.asList(dist));
        nodes.add(new Node(start));
        visited[start] = true;
        for (int i = 0; i < graph.length; i++){
            int currentMinNode = -1;
            int currentMin = Integer.MAX_VALUE;
            for (int j = 0; j < graph.length; j++){
                if (!visited[j] && dist[j] < currentMin){
                    currentMinNode = j;
                    currentMin = dist[j];
                }
            }
            if (currentMinNode != -1){
                nodes.add(new Node(currentMinNode));
                visited[currentMinNode] = true;
            } else {
                System.out.println(Arrays.asList(dist));
                System.out.println(nodes);
                System.out.println("prev："+Arrays.asList(prev));
                return;
            }
            System.out.println("第"+(i+1)+"次循环："+Arrays.asList(dist));
            System.out.println("第"+(i+1)+"次循环："+nodes);
            for (int k = 0; k < graph.length; k++){
                if (!visited[k] &&
                        graph[currentMinNode][k] != 0 &&
                        dist[k] > dist[currentMinNode]+graph[currentMinNode][k]){
                    dist[k] = dist[currentMinNode]+graph[currentMinNode][k];
                    prev[k] = currentMinNode;
                }
            }
        }
        System.out.println(Arrays.asList(dist));
        System.out.println(nodes);

        System.out.println("prev："+prev);
    }

    public void getPath(int[][] graph, int start, int end){
        shortestPath(graph,start);
        getPaths(start, end);
    }

    public void getPaths(int start, int end){
        if (end == start){
            System.out.println(end);
            return;
        }
        if (prev[end] == -1){
            System.out.println("无路径存在");
            return;
        }

        System.out.println(prev[end]);
        getPaths(start, prev[end]);

    }
    public static void main(String[] args) {
        Dijsktra demo = new Dijsktra();
        int[][] graph = {
                {0,0,10,0,30,100},
                {0,0,5,0,0,0},
                {0,0,0,50,0,0},
                {0,0,0,0,0,10},
                {0,0,0,20,0,60},
                {0,0,0,0,0,0}
        };
        demo.getPath(graph, 0,1);
    }
    class Node{
        int num;
        Node(int num){
            this.num = num;
        }
        public boolean equals(Object object){
            return ((Node)object).num == this.num;
        }
        public String toString(){
            return this.num+"";
        }
    }
}
