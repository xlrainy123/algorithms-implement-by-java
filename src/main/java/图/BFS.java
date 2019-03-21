package 图;

import java.util.LinkedList;
import java.util.Scanner;

public class BFS {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] size = in.nextLine().split(" ");
        int row = Integer.parseInt(size[0]);
        int col = Integer.parseInt(size[1]);
        int[][] graph = new int[row][col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                graph[i][j] = in.nextInt();
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();

    }


    class Node{
        public int x;
        public int y;
        public boolean visited;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
            this.visited = false;
        }
    }


}
