package com.demo.insignia.robot.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.insignia.robot.exceptions.InvalidInputException;
import com.demo.insignia.robot.exceptions.RobotException;
import com.demo.insignia.robot.model.Direction;
import com.demo.insignia.robot.model.Robot;
import com.demo.insignia.robot.model.RobotPosition;
import com.demo.insignia.robot.model.Table;

/**
 * Command factory that is responsible for creating appropriate command as per
 * input parsed.
 *
 * @author Joby
 *
 */
public class CommandFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    private static String PLACING = "PLACE";
    // Make the pattern generic so as to validate for numbers, and Directions
    private static Pattern placingParams = Pattern.compile("(PLACE) +(.*),(.*),([A-Za-z-]+)");
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String MOVE = "MOVE";
    public static final String REPORT = "REPORT";

    /**
     * Get the command to be executed corresponding to the input command.
     *
     * @param table
     * @param input
     * @return
     * @throws RobotException
     */
    public static Command getCommand(Table table, String input) throws RobotException {
        input = input.toUpperCase();

        Command commandToReturn = null;

        if (input.startsWith(PLACING)) {
            Matcher m = placingParams.matcher(input);
            if (m.matches()) {
                Direction direction = null;
                int xPos, yPos = 0;
                try {
                    String strDirection = m.group(4);
                    direction = Direction.valueOf(strDirection);
                } catch (IllegalArgumentException ex) {
                    throw new InvalidInputException("Invalid Input. Direction should be one of NORTH|EAST|SOUTH|WEST");
                }
                try {
                    xPos = Integer.parseInt(m.group(2));
                    yPos = Integer.parseInt(m.group(3));
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid Input on the co-ordinates. Invalid number.");
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Placing at xPos: {}, yPos: {}", xPos, yPos);
                }
                commandToReturn = new DoPlaceCommand(table, new RobotPosition(xPos, yPos, direction));
                System.out.println();
            }
        } else {
        	int numberOfRobots = table.getNumberOfRobots();
        	Robot activeRobot = numberOfRobots > 0 ? table.getRobots().get(numberOfRobots-1) : null;
            // Ensure that any commands before PLACE is ignored, except for REPORT.
            switch (input) {
            case LEFT:
                break;
            case RIGHT:
                break;
            case MOVE:
                break;
            case REPORT:
                break;
            default:
                throw new InvalidInputException(
                        "Invalid Command. Accepted commands are PLACE, MOVE, LEFT, RIGHT, REPORT.");
            }
        }
        return commandToReturn;
    }
}