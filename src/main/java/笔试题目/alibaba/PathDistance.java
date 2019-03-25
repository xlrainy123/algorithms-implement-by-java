package 笔试题目.alibaba;
import java.util.Scanner;

public class PathDistance {

    static int minpath = Integer.MAX_VALUE;

    static class MyPoint {
        int x, y;
        boolean visited;

        public MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
            this.visited = false;
        }

        public int getLength(MyPoint point) {
            return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
        }
    }

    public static void caculate(MyPoint start, MyPoint[] points, int sum, int count) {
        for (int i = 0; i < points.length; i++) {
            if (points[i].visited){
                continue;
            }
            points[i].visited = true;
            count++;
            sum += start.getLength(points[i]);
            if (count == points.length){
                sum += points[i].getLength(new MyPoint(0,0));  //需要回到起始点
                minpath = Math.min(minpath, sum);
            }
            caculate(points[i], points, sum,count);
            points[i].visited = false;
            count--;
            sum -= start.getLength(points[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());
        MyPoint[] points = new MyPoint[num];
        for (int i = 0; i < num; i++) {
            String[] locations = scanner.nextLine().trim().split(" ");
            points[i] = new MyPoint(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]));
        }
        caculate(new MyPoint(0,0),points,0,0);
        System.out.println(minpath);
    }
}

