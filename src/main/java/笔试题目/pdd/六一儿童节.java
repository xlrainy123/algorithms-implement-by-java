package 笔试题目.pdd;
import java.util.*;

public class 六一儿童节 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int num_h = in.nextInt();
        int[] h = new int[num_h];
        boolean[] allocated = new boolean[num_h];
        for (int i = 0; i < num_h; i++){
            h[i] = in.nextInt();
        }

        int num_w = in.nextInt();
        int[] w = new int[num_w];
        for (int i = 0; i < num_w; i++){
            w[i] = in.nextInt();
        }
        Arrays.sort(h);
        Arrays.sort(w);
        int res = 0;
        for (int ever_w : w){
            for (int i = 0; i < num_h; i++){
                if (ever_w < h[0]){
                    continue;
                }
                if (ever_w >= h[i] && !allocated[i]){
                    res++;
                    allocated[i] = true;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
