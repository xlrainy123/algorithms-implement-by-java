package stack;

import utils.Utils;

public class StackClient {

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);


        stack.push(3);stack.push(4);
        stack.push(5);stack.push(6);
        Utils.println(stack);


        stack.pop();
        Utils.println(stack);
        stack.pop();
        Utils.println(stack);
        stack.pop();
        Utils.println(stack);
        stack.pop();
        System.out.println(stack.size());
        Utils.println(stack);
        stack.pop();
        System.out.println(stack.size());
        Utils.println(stack);
        stack.pop();
        System.out.println(stack.size());
        Utils.println(stack);

        Utils.println(stack.isEmpty());
    }

}
