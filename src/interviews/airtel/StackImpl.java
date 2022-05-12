package interviews.airtel;

/**
 * Implement stack without using collection
 */
interface Stack<T> {
    void push(T data);

    T pop();

    T peek();
}

class StackImpl<T> implements Stack<T> {

    private Entry<T> top = null;

    public void push(T data) {
        Entry<T> newEntry = new Entry<T>();
        newEntry.setData(data);
        newEntry.setPrevious(top);
        top = newEntry;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        T data = top.getData();
        top = top.getPrevious();

        return data;
    }

    public T peek() {
        if (top == null) {
            return null;
        }

        return top.getData();
    }

    static class Entry<T> {
        private Entry<T> previous;
        private T data;

        public Entry<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Entry<T> previous) {
            this.previous = previous;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}

class Test {

    public static void main(String[] args) {
        Stack<Integer> stack = new StackImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.print(stack.pop());
    }

}
