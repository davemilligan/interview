package com.davemilligan.interview;

import java.util.List;

public class Lift implements Runnable {
	private final ServiceRequestQueue serviceRequestQueue;
	private LiftState state;
	private int currentFloor;
	private int targetFloor;
	public Lift(ServiceRequestQueue serviceRequestQueue) {
		this.serviceRequestQueue = serviceRequestQueue;
		state = LiftState.WAITING;
		currentFloor = 0;
	}

	@Override
	public void run() {
		while(true){
			try {
				synchronized (serviceRequestQueue) {
					// Get a list of related requests.
					System.out.println(Thread.currentThread().getName() + " checking for ServiceRequest");
					List<ServiceRequest> serviceRequestList = serviceRequestQueue.dequeue();
					serviceLiftRequest(serviceRequestList);					
				}
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " interrupted:" + e);
			}
		}
	}
	
	/**
	 * This method will take the request then make another request to the
	 * serviceRequestQueue with an appropriate predicate that will filter any
	 * additional queued requests that the lift could service at the same time,
	 * such as passengers waiting on floors on the way to the current
	 * destination or beyond.
	 * 
	 * The method should set the state of the lift to ASCENDING or DESCENDING as
	 * appropriate.
	 * 
	 * Once complete the state should be reset to WAITING so that the lift can
	 * process other requests.
	 * 
	 * @param serviceRequest
	 * @throws InterruptedException
	 */
	private void serviceLiftRequest(List<ServiceRequest> serviceRequestList) throws InterruptedException {
		if (serviceRequestList == null || serviceRequestList.isEmpty()) {
			System.out.println(Thread.currentThread().getName() + " list empty");
			state = LiftState.WAITING;			
			return;
		}	

		// TODO Find the furthermost origin floor that we have passengers waiting, set
		// that as the targetFloor, for now there is just one request in the list.
		targetFloor = serviceRequestList.get(0).getOrigin();
		
		//  Going up or down
		updateLiftState();

		// Go to furthermost floor.
		move();

		// TODO Sort the requests based on destinations in the direction of travel.
		for (ServiceRequest sr : serviceRequestList) {
			targetFloor = sr.getDestination();
			//  Going up or down
			updateLiftState();
			System.out.println(Thread.currentThread().getName() + " moving, mind the doors.");
			move();
		}
		updateLiftState();
	}

	public void move() throws InterruptedException {
		switch (state) {
		case ASCENDING:
			System.out.println(Thread.currentThread().getName() + " going up");
			for (int i = currentFloor; i < targetFloor; i++) {
				currentFloor++;
				System.out
						.println(Thread.currentThread().getName() + " ^ " + currentFloor + " destination " + targetFloor);
				Thread.sleep(1000);
			}
			System.out.println(Thread.currentThread().getName() + " <> arrived at " + currentFloor);
			Thread.sleep(3000);
			break;
		case DESCENDING:
			System.out.println(Thread.currentThread().getName() + " going down");
			for (int i = currentFloor; i > targetFloor; i--) {
				currentFloor--;
				System.out
						.println(Thread.currentThread().getName() + " v " + currentFloor + " destination " + targetFloor);
				Thread.sleep(1000);
			}
			System.out.println(Thread.currentThread().getName() + " <> arrived at " + currentFloor);
			Thread.sleep(3000);
			break;
		default:
			break;
		}
	}

	private void updateLiftState() {
		state = (currentFloor < targetFloor) ? LiftState.ASCENDING
				: (currentFloor > targetFloor) ? LiftState.DESCENDING : LiftState.WAITING;
	}
}
