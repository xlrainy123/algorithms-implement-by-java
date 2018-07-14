package stack;

import java.util.Stack;

public class MinStack {

    public Stack<Integer> data = new Stack<>();
    public Stack<Integer> mins = new Stack<>();

    public void push(int node) {
        data.push(node);
        if (!mins.isEmpty()){
            int t = mins.peek();
            mins.push(Math.min(node, t));
        }else{
            mins.push(node);
        }
    }

    public int pop() {
        mins.pop();
        return data.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return mins.peek();
    }
}
