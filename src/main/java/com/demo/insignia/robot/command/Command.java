package com.demo.insignia.robot.command;

import com.demo.insignia.robot.exceptions.RobotException;

/**
 * Abstract command that can represent a command to the robot
 *
 * @author Joby
 *
 */
public interface Command {
    public void execute() throws RobotException;
}
