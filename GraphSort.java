import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Fun little sorting algorithm I thought of that converts a list into a graph
 * by creating and edge from every value to every value in the list larger than
 * it and running topological ordering. Runtime is O(n^2) so still useable but
 * the space complexity is much worse than other sorting algorithms. Wouldn't
 * recommend using this but its cool!
 */

class GraphSort {

    public static int[] sort(int[] list) {
        int n = list.length;
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegrees = new int[n];
        // turn list to graph
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>(n));
            for (int j = 0; j < n; j++) {
                if (list[i] < list[j]) {
                    adjList.get(i).add(j);
                    inDegrees[j]++;
                }
            }
        }
        // topological ordering
        Queue<Integer> q = new LinkedList<>();
        List<Integer> solution = new ArrayList<>();

        // add all values with in-degree 0 into queue
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
                solution.add(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i : adjList.get(v)) {
                inDegrees[i]--;
                if (inDegrees[i] == 0) {
                    q.add(i);
                    solution.add(i);
                }
            }
        }

        // convert solution to int array for return
        int[] sol = new int[n];
        for (int i = 0; i < n; i++) {
            sol[i] = list[solution.get(i)];
        }
        return sol;
    }

    public static void main(String[] args) {
        int[] list = { 2, 3, 2, 1, 5, 6, 7, 2, 3, 6, 1, 7 };
        System.out.print("unsorted list: ");
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();

        // sort!
        int[] sorted = sort(list);

        System.out.print("sorted list: ");
        for (int i : sorted) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}