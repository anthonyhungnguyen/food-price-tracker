package ca.uwindsor.appliedcomputing.final_project.data_structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuckooHashTableTest {

    private CuckooHashTable<String> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new CuckooHashTable<>(new StringHashFamily(3));
    }

    @Test
    void insert_UniqueItems_ReturnsTrue() {
        assertTrue(hashTable.insert("test1"));
        assertTrue(hashTable.insert("test2"));
        assertEquals(2, hashTable.size());
    }

    @Test
    void insert_DuplicateItem_ReturnsFalse() {
        assertTrue(hashTable.insert("test"));
        assertFalse(hashTable.insert("test"));
        assertEquals(1, hashTable.size());
    }

    @Test
    void contains_ExistingItem_ReturnsTrue() {
        hashTable.insert("test");
        assertTrue(hashTable.contains("test"));
    }

    @Test
    void contains_NonExistingItem_ReturnsFalse() {
        assertFalse(hashTable.contains("nonexistent"));
    }

    @Test
    void getAllKeys_ReturnsAllInsertedItems() {
        hashTable.insert("test1");
        hashTable.insert("test2");
        hashTable.insert("test3");

        List<String> keys = hashTable.getAllKeys();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("test1"));
        assertTrue(keys.contains("test2"));
        assertTrue(keys.contains("test3"));
    }
}
