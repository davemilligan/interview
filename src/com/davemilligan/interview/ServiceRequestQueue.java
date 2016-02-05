package com.davemilligan.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The ServiceRequestQueue receives a ServiceRequest from the LiftOperator
 * and notifies the first available waiting lift that a new request has been
 * added.
 *
 */
public class ServiceRequestQueue {
	private final int upperBound;
	private final Queue<ServiceRequest> queue;

	public ServiceRequestQueue() {
		upperBound = 2;
		queue = new LinkedList<ServiceRequest>();
	}

	/**
	 * Adds a ServiceRequest to the collection, and calls notifies a single waiting thread.
	 * @param item
	 * @return
	 */
	public boolean enqueue(ServiceRequest item) {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " adding: " + item);
			while (queue.size() == upperBound) {
				try {
					System.out.println(Thread.currentThread().getName() + " found queue size: " + queue.size());
					wait();
				} catch (InterruptedException e) {}
			}
			queue.add(item);
			notifyAll();
			return true;
		}
	}

	/**
	 * Retrieves and removes all items matching a given predicate from the collection.
	 * @param predicate
	 * @return
	 */
	public List<ServiceRequest> findAll(Predicate<ServiceRequest> predicate) {
		synchronized (this) {
			List<ServiceRequest> list = queue.stream().filter(predicate).collect(Collectors.toList());
			queue.removeAll(list);
			notifyAll();
			return list;
		}
	}
	
	/**
	 * Retrieves and a collection of ServiceRequests that can be serviced by a single lift operation.
	 * @return
	 */
	public List<ServiceRequest> dequeue(int currentFloor) {
		synchronized (this) {
			
			//  TODO implement logic to retrieve all ServiceRequests that can be serviced by a single lift operation.
			notifyAll();
			return new ArrayList<>();
		}
	}

	/**
	 * Retrieves and removes the first ServiceRequest in the collection, if the collection is empty returns null.
	 * @return
	 */
	public List<ServiceRequest> dequeue() {
		synchronized (this) {
			List<ServiceRequest> list = new ArrayList<>();
			ServiceRequest request = queue.poll();
			if (request != null)
				list.add(request);
			notifyAll();
			return list;
		}
	}

	/**
	 * Returns the number of items in the collection.
	 * @return
	 */
	public int size() {
		synchronized (queue) {
			notifyAll();
			return queue.size();
		}
	}
}
