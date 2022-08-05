package com.demo.insignia.robot.exceptions;

/**
 * Basic Robot Exception.
 *
 * @author Joby
 *
 */
public abstract class RobotException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3406961955007166451L;

	public RobotException(String message) {
        super(message);
    }
}
