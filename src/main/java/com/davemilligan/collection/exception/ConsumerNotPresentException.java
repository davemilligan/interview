package com.davemilligan.collection.exception;

public class ConsumerNotPresentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConsumerNotPresentException(Object item) {
		super("No consumer available for item " + item.getClass().getName() + ",  A consumer must be available before an item can be queued.  Check consumers have a valid Predicable.");
	}
}
