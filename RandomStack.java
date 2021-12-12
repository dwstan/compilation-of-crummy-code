import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Normal data structure except when you add something to it, it adds at a
 * random index. Furthermore when you get or remove an item, it choose a random
 * index to take from.
 */

class RandomStack<T> {
    private final List<T> content; // yeah i got lazy and just used a list. shhhhhhhh

    public RandomStack() {
        content = new ArrayList<>();
    }

    public RandomStack(T[] array) {
        content = new ArrayList<>(Arrays.asList(array));
    }

    public RandomStack(List<T> list) {
        content = new ArrayList<>(list);
    }

    public RandomStack(int size) {
        content = new ArrayList<>(size);
    }

    public void add(T item) {
        Random r = new Random();
        content.add(r.nextInt(content.size() + 1), item);
    }

    public T get() {
        Random r = new Random();
        return content.get(r.nextInt(content.size()));
    }

    public T remove() {
        Random r = new Random();
        return content.remove(r.nextInt(content.size()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : content) {
            sb.append((item.toString())).append(" ");
        }
        return sb.toString();
    }

    public int size() {
        return content.size();
    }
}

class Test {
    public static void main(String[] args) {
        RandomStack<Integer> stack1 = new RandomStack<>();
        stack1.add(1);
        stack1.add(2);
        stack1.add(3);
        System.out.println("stack1 before: " + stack1.toString());
        System.out.println("stack1 removed: " + stack1.remove());
        System.out.println("stack1 after: " + stack1.toString());

        RandomStack<String> stack2 = new RandomStack<>(new String[] { "a", "b", "c" });
        System.out.println("stack2 before: " + stack2.toString());
        System.out.println("stack2 removed: " + stack2.remove());
        System.out.println("stack2 after: " + stack2.toString());
    }
}