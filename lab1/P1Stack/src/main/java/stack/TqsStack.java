package stack;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TqsStack {

    private final ArrayList<String> stack;
    private int max;

    public TqsStack(){
        stack = new ArrayList<>();
    }

    public TqsStack(int max){
        this.max = max;
        stack = new ArrayList<>();
    }

    public int isEmpty() {
        if (stack.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int size() {
        return stack.size();
    }

    public boolean push(String x) {
        if ( max > 0 ) {
            if (stack.size() < max) {
                return stack.add(x);
            } else {
                throw new IllegalStateException();
            }
        } else {
            return stack.add(x);
        }
    }

    public String pop() {
        if (stack.size() == 0) {
            throw new NoSuchElementException();
        } else {
            return stack.remove(stack.size()-1);
        }
    }

    public String peek() {
        if (stack.size() == 0) {
            throw new NoSuchElementException();
        } else {
            return stack.get(stack.size()-1);
        }
    }

}
