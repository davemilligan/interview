package com.davemilligan.interview;

public class Building {
	private final int numFloors;
	private final int numLifts;

	public static void main(String[] args) {
		new Building();
	}

	public Building() {
		numFloors = 25;
		numLifts = 1;
		Thread.currentThread().setName("Lift Operator");
		LiftOperator lo = new LiftOperator(numFloors, numLifts);
		lo.run();
	}
}
