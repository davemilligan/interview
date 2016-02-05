package com.davemilligan.interview.lift;

/**
 * This class will store a service request
 *
 */
public class ServiceRequest {
	private int origin;
	private int destination;

	/**
	 * The Service request will have come from a passenger waiting on a floor,
	 * in which case the destination will not be known till the passenger
	 * enters. The request will be updated when the passenger enters, and
	 * presses a button simulated on the command line.
	 * 
	 * @param origin
	 * @param direction
	 */
	public ServiceRequest(int origin, int destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public Direction getDirection() {
		return (origin < destination) ? Direction.ASCENDING : Direction.DESCENDING;
	}
	
	@Override
	public String toString() {
		return String.format("Passenger waiting at floor %s, " + getDirection() + " to floor %s", origin, destination);
	}

}
