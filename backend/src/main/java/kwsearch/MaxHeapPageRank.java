package kwsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxHeapPageRank {
    PriorityQueue<PageRankEntry> maxHeap;

    MaxHeapPageRank() {
        maxHeap = new PriorityQueue<>((a, b) -> b.rank - a.rank);
    }

    void addPage(WebPage page, int rank) {
        maxHeap.add(new PageRankEntry(page, rank));
    }

    List<WebPage> getTopRankedPages(int n) {
        List<WebPage> topPages = new ArrayList<>();
        for (int i = 0; i < n && !maxHeap.isEmpty(); i++) {
            topPages.add(maxHeap.poll().page);
        }
        return topPages;
    }

    private class PageRankEntry {
        WebPage page;
        int rank;

        PageRankEntry(WebPage page, int rank) {
            this.page = page;
            this.rank = rank;
        }
    }
}
