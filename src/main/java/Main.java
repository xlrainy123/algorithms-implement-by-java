import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().trim();
        String[] contents = s.substring(1,s.length()-1).split(",");
        for (String s1 : contents){
            System.out.println(s1);
        }
        int[] nums = new int[contents.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(contents[i]);
        }
        for (int i = 0; i < nums.length; i++){
            if (i + nums[i] < 0 || i + nums[i] >= nums.length){
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");
    }
}
