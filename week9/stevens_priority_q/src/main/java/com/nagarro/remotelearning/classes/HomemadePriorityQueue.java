package com.nagarro.remotelearning.classes;

import com.nagarro.remotelearning.exceptions.MaxCapacityReachedException;

import java.util.Arrays;
import java.util.List;

public class HomemadePriorityQueue<E extends Comparable<E>> implements Comparable<E> {
	private E[] heap;
	private final int MAX_CAPACITY;
	private int size;

	public HomemadePriorityQueue() {
		MAX_CAPACITY = 10000;
		heap = (E[]) new Comparable[3]; // TODO warning: Type safety: Unchecked cast from Comparable[] to E[]
		size = 0;
	}

	public HomemadePriorityQueue(int maxCapacity) {
		this.MAX_CAPACITY = maxCapacity;
		heap = (E[]) new Comparable[3];
		size = 0;
	}

	public HomemadePriorityQueue(HomemadePriorityQueue<E> anotherQueue) {
		heap = Arrays.copyOf(anotherQueue.heap, anotherQueue.heap.length);
		MAX_CAPACITY = anotherQueue.MAX_CAPACITY;
		size = anotherQueue.size;
	}

	public void insert(E item) {
		if (size == MAX_CAPACITY) {
			throw new MaxCapacityReachedException("Queue has reached maximum capacity");
		}
		if (size == heap.length - 1) {
			doubleCapacityOfHeap();
		}

		size++;
		int indexOfNewItem = size - 1;
		// (index-1)/2 is the parent so the item is being compared with its parent and if true places the parent one level below.
		while (indexOfNewItem > 0 && item.compareTo(heap[(indexOfNewItem - 1) / 2]) > 0) { // < 0 for prioritizing the smallest
			heap[indexOfNewItem] = heap[(indexOfNewItem - 1) / 2];
			indexOfNewItem = (indexOfNewItem - 1) / 2;
		}

		heap[indexOfNewItem] = item;
	}

	public E remove() {
		if (isEmpty()) {
			throw new NullPointerException("the queue is empty");
		}

		E root = heap[0];
		// swap head(largest) for last elem
		heap[0] = heap[size - 1];
		// remove the largest.
		heap[size - 1] = null;
		size--;
		heapify(0);

		// return the new largest
		return root;
	}

	public void clear() {
		size = 0;
		heap = (E[]) new Comparable[10];
	}

	public E head() {
		if (isEmpty()) {
			return null;
		}
		return heap[0];
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	@Override
	public int compareTo(E item) {
		return this.toString().compareTo(item.toString());
	}

	public int compareTo(HomemadePriorityQueue<E> other) {
		HomemadePriorityQueue<E> tempHeap = new HomemadePriorityQueue<>(this);
		HomemadePriorityQueue<E> tempOther = new HomemadePriorityQueue<>(other);

		if (tempHeap.size() > tempOther.size()) {
			return 1;
		} else if (tempHeap.size() < tempOther.size()) {
			return -1;
		} else {
			final int origSize = tempHeap.size();

			compareElemsOf(tempHeap, tempOther, origSize);
		}

		return 0;
	}

	public List<E> sortList(List<E> inputList) {
		List<E> outputList = new java.util.ArrayList<>();
		HomemadePriorityQueue<E> pq = new HomemadePriorityQueue<>();

		for (E elem : inputList) {
			pq.insert(elem);
		}

		// while removing each head elem from pq, add them into the outputList
		while (!pq.isEmpty()) {
			outputList.add(pq.remove());
		}

		return outputList;
	}

	private int compareElemsOf(HomemadePriorityQueue<E> tempHeap, HomemadePriorityQueue<E> tempOther,
			int origSize) {
		for (int i = 0; i < origSize; i++) {
			if (tempHeap.head().compareTo(tempOther.head()) > 0) {
				return 1;
			} else if (tempHeap.head().compareTo(tempOther.head()) < 0) {
				return -1;
			}
			try {
				tempHeap.remove();
				tempOther.remove();
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}

		return 0;
	}

	private void heapify(int i) {
		// left part of the tree
		int indexOfLeft = 2 * i + 1;
		// right part of the tree
		int indexOfRight = 2 * i + 2;
		int indexOfLargest = i;

		// have the indexOfLargest take the index of the largest value
		if (indexOfLeft < size && heap[indexOfLeft].compareTo(heap[indexOfLargest]) > 0) { // < 0 for prioritizing the smallest
			indexOfLargest = indexOfLeft;
		}
		if (indexOfRight < size && heap[indexOfRight].compareTo(heap[indexOfLargest]) > 0) { // same as above
			indexOfLargest = indexOfRight;
		}
		// if there is a new largest swap and do the heapify on the new largest
		if (indexOfLargest != i) {
			E temp = heap[i];
			heap[i] = heap[indexOfLargest];
			heap[indexOfLargest] = temp;
			heapify(indexOfLargest);
		}
	}

	private void doubleCapacityOfHeap() {
		heap = java.util.Arrays.copyOf(heap, heap.length * 2);
	}
}
