package com.demo.insignia.robot.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.demo.insignia.robot.exceptions.RobotException;
import com.demo.insignia.robot.model.Coordinates;
import com.demo.insignia.robot.model.Direction;
import com.demo.insignia.robot.model.Robot;
import com.demo.insignia.robot.model.RobotPosition;
import com.demo.insignia.robot.model.Table;


/**
 * Test the Turn command
 *
 * @author Joby
 *
 */
public class DoPlaceCommandTest {
    Table table = null;

    @Before
    public void setup() {
        table = new Table(new Coordinates(0, 0), new Coordinates(0, 10), new Coordinates(5, 0), new Coordinates(5, 10));
    }

    @Test
    public void testPlacing() throws RobotException {
        DoPlaceCommand cmd = new DoPlaceCommand(table, new RobotPosition(0, 0, Direction.NORTH));
        cmd.execute();
        int num = table.getNumberOfRobots();
        Robot robot = table.getRobots().get(num-1);
        Assert.assertEquals(0, robot.getPosition().getXPos());
        Assert.assertEquals(0, robot.getPosition().getYPos());
        Assert.assertEquals(Direction.NORTH, robot.getPosition().getDirection());
    }

    @Test
    public void testPlacingOutOfBounds() throws RobotException {
        DoPlaceCommand cmd = new DoPlaceCommand(table, new RobotPosition(10, 0, Direction.NORTH));
        cmd.execute();

        int num = table.getNumberOfRobots();
        Assert.assertEquals(0, num);
    }
}

