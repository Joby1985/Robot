package com.demo.insignia.robot.model;

import lombok.Getter;

/**
 * Model representing Robot position
 *
 * @author Joby
 *
 */
@Getter
public class RobotPosition extends Coordinates{
	private Direction direction;

	public RobotPosition(int xPos, int yPos, Direction direction) {
		super(xPos, yPos);
		this.direction = direction;
	}
	
}
