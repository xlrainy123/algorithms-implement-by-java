import java.util.*;

public class Main {


    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int jobNum = in.nextInt();
        int friendNum = in.nextInt();

        Map<Integer, Integer> jobs = new HashMap<>();

        for (int i = 0; i < jobNum; i++){
            int diff = in.nextInt();
            int salary = in.nextInt();
            jobs.put(diff, salary);
        }

        List<Integer> powers = new ArrayList<>();
        for (int i = 0; i < friendNum; i++){
            int power = in.nextInt();
            powers.add(power);
        }

        Main main = new Main();
        main.compute(jobs, powers);

    }

    public void compute(Map<Integer, Integer> map, List<Integer> powers){
        for (int power : powers){
            int maxSalary = Integer.MIN_VALUE;
            for (Map.Entry<Integer, Integer> job : map.entrySet()){
                if (power >= job.getKey()){
                    maxSalary = Math.max(maxSalary, job.getValue());
                }
            }
            System.out.println(maxSalary);
        }
    }
}
