package com.davemilligan.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.davemilligan.collection.exception.ConsumerNotPresentException;

/**
 * This class implements a thread-safe bounded queue for objects that implement
 * the Criterial interface.
 * 
 * Consumers request objects from the queue based on some criteria. If a
 * suitable object is not on the queue then the consumer is blocked till one is
 * available. If more than one consumer is waiting on the same criteria then the
 * consumer that arrived first is given priority.
 * 
 * Producers add objects to the queue, waiting if the queue is full.
 * 
 * @author Dave Milligan
 *
 * @param <T>
 */
public class CriterialBoundedQueue<T extends Criterial> {
	private final int upperBound;
	private final LinkedList<T> queue;
	private final Map<CriteriaPredicable, Queue<Thread>> priorityConsumerQueueMap;
	private final Set<String> classSet;

	public CriterialBoundedQueue(int upperBound) {
		this.upperBound = upperBound;
		classSet = new HashSet<>();
		queue = new LinkedList<>();
		priorityConsumerQueueMap = new HashMap<>();
	}

	public void enqueue(T item) throws ConsumerNotPresentException {
		if (!consumerExists(item))
			throw new ConsumerNotPresentException(item);

		synchronized (queue) {
			while (queue.size() == upperBound) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.add(item);
			queue.notifyAll();
		}
	}

	public T dequeue(CriteriaPredicable predicable) {
		synchronized (queue) {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			initializeConsumerQueue(predicable);

			while (numQueuedConsumers(predicable) > 0 && !currentThreadHeadsQueue(predicable)) {
				if (!isConsumerQueued(predicable)) {
					addConsumerToQueue(predicable);
					setHeadHighestpriority(predicable);
				}
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (queue.size() == 0 || (predicable.predicate().test(queue.peek()) == false)) {
				if (!isConsumerQueued(predicable))
					addConsumerToQueue(predicable);
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			removeCurrentThread(predicable);
			T item = queue.remove();
			queue.notifyAll();
			return item;
		}
	}

	private boolean currentThreadHeadsQueue(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			if (Thread.currentThread().equals(priorityConsumerQueueMap.get(predicate).peek()))
				return true;
			return false;
		}
	}

	private void removeCurrentThread(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			priorityConsumerQueueMap.get(predicate).removeIf(t -> Thread.currentThread().equals(t));
		}
	}

	private int numQueuedConsumers(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			return priorityConsumerQueueMap.get(predicate).size();
		}
	}

	private void addConsumerToQueue(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			priorityConsumerQueueMap.get(predicate).add(Thread.currentThread());
		}
	}

	private boolean isConsumerQueued(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			return (priorityConsumerQueueMap.get(predicate).contains(Thread.currentThread()));
		}
	}

	private void setHeadHighestpriority(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			if (priorityConsumerQueueMap.get(predicate).peek() != null)
				priorityConsumerQueueMap.get(predicate).peek().setPriority(Thread.MAX_PRIORITY);
		}
	}

	private void initializeConsumerQueue(CriteriaPredicable predicate) {
		synchronized (priorityConsumerQueueMap) {
			if (!priorityConsumerQueueMap.keySet().contains(predicate))
				priorityConsumerQueueMap.put(predicate, new LinkedList<Thread>());
		}
	}

	private boolean consumerExists(T item) {
		synchronized (priorityConsumerQueueMap) {
			synchronized (classSet) {
				if (classSet.contains(item.getClass().getName())) {
					return true;
				}

				boolean exists = priorityConsumerQueueMap.keySet().stream().anyMatch(p -> p.predicate().test(item));
				if (exists) {
					classSet.add(item.getClass().getName());
				}
				return exists;
			}
		}
	}
}
