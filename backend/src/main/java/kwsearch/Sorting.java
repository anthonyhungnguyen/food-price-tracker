package kwsearch;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * The Sorting class provides sorting algorithms for sorting lists of map entries.
 * It includes implementations for quick sort and heap sort.
 */
public class Sorting {

    /**
     * Sorts a list of map entries using the heap sort algorithm.
     *
     * @param entries the list of map entries to be sorted
     */
    public static void heapSort(List<Map.Entry<String, Integer>> entries) {
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        heap.addAll(entries);
        entries.clear();
        while (!heap.isEmpty()) {
            entries.add(heap.poll());
        }
    }
}
