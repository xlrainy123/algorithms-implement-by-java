package stack;

public class Stack extends AbstractLineList{

    int top;

    public Stack(){
        data = new int[initial_size];
        top = -1;
        size = 0;
    }
    public void push(int val){

        if (top < initial_size-1){
            data[++top] = val;
            size++;
            return ;
        }
        if (size < 11) {
            int[] tmp = new int[2 * data.length];
            int index = 0;
            for (int num : data) {
                tmp[index] = data[index];
                index++;
            }
            data = tmp;
            data[++top] = val;
            size++;
            return;
        }
        throw new OutOfMemoryError("元素个数超过10个");
    }

    public int pop(){
        if (top > -1){
            size--;
            return data[top--];
        }else {
            throw new NullPointerException("stack is null");
        }
    }

    public boolean isEmpty(){
        return top == -1 ? true : false;
    }

    @Override
    public String toString(){

        if (isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++){
            sb.append(data[i]+",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        return sb.toString();
    }
}
