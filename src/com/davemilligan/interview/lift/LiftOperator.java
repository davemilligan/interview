package com.davemilligan.interview.lift;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The LiftOperator simulates the control panel of the lift system, waiting for
 * input from passengers and adding ServiceRequests to the ServiceRequestQueue.
 *
 */
public class LiftOperator {

	private final ServiceRequestQueue serviceRequestQueue;
	private final int numFloors;
	private final int numLifts;
	private final Random random;
	public LiftOperator(int numFloors, int numLifts) {
		this.numFloors = numFloors;
		this.numLifts = numLifts;
		random = new Random();
		serviceRequestQueue = new ServiceRequestQueue();
	}

	public void run() {
		List<Lift> lifts = new ArrayList<>();
		for (int i = 0; i < numLifts; i++) {
			lifts.add(new Lift(serviceRequestQueue));
		}
		for (int i = 0; i < numLifts; i++) {
			Thread t = new Thread(lifts.get(i), "Lift " + (i + 1));
			t.start();
		}

		while (true) {
			ServiceRequest serviceRequest = getPassengerRequest();
			System.out.println(Thread.currentThread().getName() + " sees " + " " + serviceRequest.toString());
			serviceRequestQueue.enqueue(serviceRequest);
		}
	}

	/**
	 * The getPassengerRequest method prompts a waiting passenger for an origin and destination floor.
	 * 
	 * @return
	 */
	private ServiceRequest getPassengerRequest() {
		int origin = random.nextInt(numFloors);
		int destination = -1;
		while ((origin < 0 || origin > numFloors) || (destination < 0 || destination > numFloors) || destination  == origin) {
			boolean goingUp = ((origin == 0) ||(random.nextInt(1) == 1)) ? true : false;
			if (goingUp)
				destination = (random.nextInt(numFloors - origin) + origin);
			else
				destination = (random.nextInt(origin));
		}
		return new ServiceRequest(origin, destination);
	}
}
