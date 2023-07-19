package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.nagarro.remotelearning.classes.HomemadePriorityQueue;
import com.nagarro.remotelearning.exceptions.MaxCapacityReachedException;

class AppTest {
    HomemadePriorityQueue<Integer> myIntPriorityQueue;

    @BeforeEach
    public void init() {
        myIntPriorityQueue = new HomemadePriorityQueue<>();
    }

    @Test
    public void testMaxCapacityReachedException() {
        assertThrows(MaxCapacityReachedException.class, () -> {
            HomemadePriorityQueue<Integer> myPriorityQueue = new HomemadePriorityQueue<>(3);
            myPriorityQueue.insert(1);
            myPriorityQueue.insert(2);
            myPriorityQueue.insert(3);
            myPriorityQueue.insert(4);
        });
    }

    @Test
    public void testDefaultMaxCapacity() {
        for (int i = 0; i < 10000; i++) {
            myIntPriorityQueue.insert(i);
        }

        assertThrows(MaxCapacityReachedException.class, () -> {
            myIntPriorityQueue.insert(10001);
        });
    }

    @Test
    public void testSizeIncrementWhenInserting() {
        myIntPriorityQueue.insert(123);
        assertEquals(1, myIntPriorityQueue.size());
    }

    @Test
    public void testSizeDecrementWhenRemoving() {
        for (int i = 0; i < 10; i++) {
            myIntPriorityQueue.insert(i);
        }

        myIntPriorityQueue.remove();
        assertEquals(9, myIntPriorityQueue.size());
    }

    @Test
    public void testClearMethod() {
        for (int i = 0; i < 10; i++) {
            myIntPriorityQueue.insert(i);
        }

        myIntPriorityQueue.clear();
        assertEquals(0, myIntPriorityQueue.size());
    }

    @Test
    public void testIsEmptyMethod() {
        assertTrue(myIntPriorityQueue.isEmpty());

        myIntPriorityQueue.insert(1);
        assertTrue(!myIntPriorityQueue.isEmpty());
    }

    @Test
    public void testRemovingWhenQueueIsEmpty() {
        assertThrows(NullPointerException.class, () -> {
            myIntPriorityQueue.remove();
        });
    }

    @Test
    public void testIfLargestElemIsHead() {
        for (int i = 0; i < 10; i++) {
            myIntPriorityQueue.insert(i);
        }

        assertEquals(9, myIntPriorityQueue.head());
    }

    @Test
    public void testForHeadIfQueueIsEmpty() {
        assertEquals(null, myIntPriorityQueue.head());
    }

    @ParameterizedTest(name = "{index} => pq1 = {0}, pq2 = {1}, expected = {2}")
    @MethodSource("provideTwoQueuesForComparison")
    public void testComparisonOfTwoQueues(HomemadePriorityQueue<Integer> pq1, HomemadePriorityQueue<Integer> pq2,
            int expected) {
        assertEquals(expected, pq1.compareTo(pq2));
    }

    private static Stream<Arguments> provideTwoQueuesForComparison() {
        HomemadePriorityQueue<Integer> pq1 = new HomemadePriorityQueue<>();
        HomemadePriorityQueue<Integer> pq2 = new HomemadePriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            pq1.insert(i);
            pq2.insert(i);
        }

        return Stream.of(
                Arguments.of(pq1, pq2, 0),
                Arguments.of(new HomemadePriorityQueue<>(), pq2, -1),
                Arguments.of(pq1, new HomemadePriorityQueue<>(), 1),
                Arguments.of(pq1, new HomemadePriorityQueue<>(pq1), 0),
                Arguments.of(new HomemadePriorityQueue<>(), new HomemadePriorityQueue<>(), 0));

    }

    @Test
    public void testWithJavaUtilsQueue() {
        Queue<Integer> javaUtilsQueue = new PriorityQueue<>(Collections.reverseOrder());
        Random random = new Random();
        int randomNb;

        for (int i = 0; i < 10; i++) {
            randomNb = random.nextInt(100);
            myIntPriorityQueue.insert(randomNb);
            javaUtilsQueue.add(randomNb);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(javaUtilsQueue.remove(), myIntPriorityQueue.remove());
        }
    }

    @Test
    public void testSortList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 0, 4, 3, 7));
        list = myIntPriorityQueue.sortList(list);

        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) >= list.get(i + 1));
        }
    }

    @Test
    public void testComparisonOfTwoStringQueues() {
        HomemadePriorityQueue<String> myStringPriorityQueue = new HomemadePriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            myStringPriorityQueue.insert("a" + i);
        }

        HomemadePriorityQueue<String> myStringPriorityQueue2 = new HomemadePriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            myStringPriorityQueue2.insert("a" + i);
        }

        assertTrue(myStringPriorityQueue.compareTo(myStringPriorityQueue2) == 0);

        myStringPriorityQueue2.insert("a10");
        assertTrue(myStringPriorityQueue.compareTo(myStringPriorityQueue2) < 0);
    }
}