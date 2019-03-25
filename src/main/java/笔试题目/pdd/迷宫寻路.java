package 笔试题目.pdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 迷宫寻路 {
    public int minLen = Integer.MAX_VALUE;
    public static void main(String[] args){
        迷宫寻路 solution = new 迷宫寻路();
        char[][] graph = solution.generateGraph();
        solution.search(graph);

    }

    public void search(char[][] graph){
        int m = graph.length;
        int n = graph[0].length;
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        int start_x = 0, start_y = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (graph[i][j] == '2'){
                    start_x = i;
                    start_y = j;
                }
            }
        }
        List<Character> keys = new ArrayList<>();
        dfs(graph, start_x, start_y, visited, keys, 0);
    }

    public void dfs(char[][] graph, int x, int y, boolean[][] visited, List<Character> keys, int currentLen){
        if (x < 0 || y < 0 || x > graph.length || y > graph[0].length || visited[x][y] || graph[x][y] == '0'){
            return;
        }
        if (graph[x][y] == '3'){
            visited[x][y] = true;
            currentLen++;
            minLen = Math.min(currentLen, minLen);
            return;
        }
        if (graph[x][y] == '1'){
            visited[x][y] = true;
            currentLen++;
            dfs(graph, x+1,y,visited,keys,currentLen);
            dfs(graph, x-1,y,visited,keys,currentLen);
            dfs(graph, x,y+1,visited,keys,currentLen);
            dfs(graph, x,y-1,visited,keys,currentLen);
        }
        if (graph[x][y] >= 'a' && graph[x][y] <= 'z'){
            visited[x][y] = true;
            currentLen++;
            keys.add(graph[x][y]);
            dfs(graph, x+1,y,visited,keys,currentLen);
            dfs(graph, x-1,y,visited,keys,currentLen);
            dfs(graph, x,y+1,visited,keys,currentLen);
            dfs(graph, x,y-1,visited,keys,currentLen);
        }
        if (graph[x][y] >= 'A' && graph[x][y] <= 'Z'){
            if ( keys.contains((char)(graph[x][y]+32))){
                visited[x][y] = true;
                currentLen++;
                keys.remove((char)(graph[x][y]+32));
            }
            dfs(graph, x+1,y,visited,keys,currentLen);
            dfs(graph, x-1,y,visited,keys,currentLen);
            dfs(graph, x,y+1,visited,keys,currentLen);
            dfs(graph, x,y-1,visited,keys,currentLen);
        }
    }
    public char[][] generateGraph(){
        Scanner in = new Scanner(System.in);
        String mn = in.nextLine();
        int m = Integer.parseInt(mn.split(" ")[0]);
        int n = Integer.parseInt(mn.split(" ")[1]);
        char[][] graph = new char[m][n];
        System.out.println(m+","+n);
        for (int i = 0; i < m; i++){
            String line = in.nextLine();
            for (int j = 0; j < n; j++){
                graph[i][j] = line.charAt(j);
            }
        }
        return graph;
    }
}
