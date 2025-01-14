package ca.uwindsor.appliedcomputing.final_project.service;

import ca.uwindsor.appliedcomputing.final_project.dto.KeywordSearchData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchFrequencyServiceTest {

    @Test
    void testSearchPattern() {
        String text = "hello world hello there hello";
        String pattern = "hello";
        int count = SearchFrequencyService.searchPattern(text, pattern);
        assertEquals(3, count);
    }

    @Test
    void testPerformSearchQueries() {
        String query = "test";
        KeywordSearchData result = SearchFrequencyService.performSearchQueries(query);
        assertNotNull(result);
        assertEquals(query, result.getKeyword());
        assertEquals(1, result.getCount());
        assertNotNull(result.getSearchTime());
    }

    @Test
    void testTopSearchQueries() {
        // Perform multiple searches
        SearchFrequencyService.performSearchQueries("apple");
        SearchFrequencyService.performSearchQueries("apple");
        SearchFrequencyService.performSearchQueries("banana");
        
        List<KeywordSearchData> topQueries = SearchFrequencyService.topSearchQueries(2);
        assertNotNull(topQueries);
        assertTrue(topQueries.size() <= 2);
        assertEquals("apple", topQueries.get(0).getKeyword());
        assertEquals(2, topQueries.get(0).getCount());
    }
}
