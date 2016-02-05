package com.davemilligan.interview;

public enum Direction {
	DESCENDING("DOWN"), ASCENDING("UP");
	
    private final String direction;

    private Direction(String direction) {
        this.direction = direction;
    }
    
    public String value() {
    	return direction;
    }
}
