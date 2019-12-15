package ru.sbt.homework04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableImplTest {
    @Test
    void test() {
        HashTable<Integer, String> hashTable = new HashTableImpl<>();
        int max = 10_000;

        //adding elements
        for (int i = 0; i < max; i++) {
            assertEquals(i, hashTable.size());
            hashTable.put(i, string(i));
        }

        //checking existence of elements
        for (int i = 0; i < max; i++) {
            assertTrue(hashTable.containsKey(i));
            assertTrue(hashTable.containsValue(string(i)));
            assertEquals(string(i), hashTable.get(i));
        }

        //deleting half of elements
        for (int i = 0; i < max / 2; i++) {
            assertEquals(max - i, hashTable.size());
            assertEquals(string(i), hashTable.remove(i));
        }

        //checking existence of elements
        assertEquals(hashTable.size(), max / 2);
        for (int i = 0; i < max; i++) {
            if (i < max / 2) {
                assertFalse(hashTable.containsKey(i));
                assertFalse(hashTable.containsValue(string(i)));
            } else {
                assertTrue(hashTable.containsKey(i));
                assertTrue(hashTable.containsValue(string(i)));
            }
        }
        assertEquals(max / 2, hashTable.size());
    }

    String string(int i) {
        return String.valueOf(i);
    }
}