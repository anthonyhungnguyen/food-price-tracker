package ca.uwindsor.appliedcomputing.final_project.util;

import ca.uwindsor.appliedcomputing.final_project.dto.PriceConditionItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PriceUtilTest {

    @Test
    void parsePriceQuery_RangeQuery_ReturnsValidConditions() {
        ArrayList<PriceConditionItem> items = PriceUtil.parsePriceQuery("price:10-20");
        
        assertEquals(2, items.size());
        assertEquals(">=", items.get(0).op);
        assertEquals(10.0, items.get(0).value);
        assertEquals("<=", items.get(1).op);
        assertEquals(20.0, items.get(1).value);
    }

    @Test
    void parsePriceQuery_OnlyUpperBound_ReturnsLessThanCondition() {
        ArrayList<PriceConditionItem> items = PriceUtil.parsePriceQuery("price:-20");
        
        assertEquals(1, items.size());
        assertEquals("<=", items.get(0).op);
        assertEquals(20.0, items.get(0).value);
    }

    @Test
    void parsePriceQuery_OnlyLowerBound_ReturnsGreaterThanCondition() {
        ArrayList<PriceConditionItem> items = PriceUtil.parsePriceQuery("price:10-");
        
        assertEquals(1, items.size());
        assertEquals(">=", items.get(0).op);
        assertEquals(10.0, items.get(0).value);
    }

    @Test
    void parsePriceQuery_InvalidQuery_ReturnsEmptyList() {
        ArrayList<PriceConditionItem> items = PriceUtil.parsePriceQuery("invalid");
        assertTrue(items.isEmpty());
    }
}
