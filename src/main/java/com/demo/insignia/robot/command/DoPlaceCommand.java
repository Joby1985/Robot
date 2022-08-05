package com.demo.insignia.robot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.insignia.robot.exceptions.RobotException;
import com.demo.insignia.robot.model.Robot;
import com.demo.insignia.robot.model.RobotPosition;
import com.demo.insignia.robot.model.Table;

/**
 * Command to place the robot on a specific coordinate on the table. (Also
 * validate that the place is within the table
 *
 * @author Joby
 *
 */
public class DoPlaceCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(DoPlaceCommand.class);

    private Robot robot;
    private Table table;
    private RobotPosition position;

    public DoPlaceCommand(Table table, RobotPosition position) {
    	robot = new Robot(position, "Robot "+table.getNumberOfRobots()+1);
    	this.table = table;
    	this.position = position;
	}

    @Override
    public void execute() throws RobotException {
        if (table.isBoundedMove(position)) {
        	table.placeRobot(robot,position);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Robot placed at position :" + position);
            }
        } else {
            LOGGER.error("Cannot place the robot on the table as the coordinates are outside the boundary.");
        }
    }
}
