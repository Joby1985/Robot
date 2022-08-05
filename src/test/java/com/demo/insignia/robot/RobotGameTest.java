package com.demo.insignia.robot;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.demo.insignia.robot.exceptions.InvalidInputException;
import com.demo.insignia.robot.exceptions.RobotException;
import com.demo.insignia.robot.model.Coordinates;
import com.demo.insignia.robot.model.Direction;
import com.demo.insignia.robot.model.Robot;
import com.demo.insignia.robot.model.RobotPosition;
import com.demo.insignia.robot.model.Table;

/**
*
* @author Joby
*
*/
public class RobotGameTest {
   Table table = null;

   @Before
   public void setup() {
       table = new Table(new Coordinates(0, 0), new Coordinates(0, 10), new Coordinates(5, 0), new Coordinates(5, 10));
   }

   // @Test
   public void testPlayWithinBounds() throws RobotException {

       RobotGame.play(table, Arrays.asList("PLACE 0,0,NORTH", "MOVE", "REPORT"));
       
       int num = table.getNumberOfRobots();
       Assert.assertEquals(1, num);
       Robot robot = table.getRobots().get(0);
       
       RobotPosition position = robot.getPosition();
    		   
       Assert.assertEquals(0, position.getXPos());
       Assert.assertEquals(1, position.getYPos());
       Assert.assertEquals(Direction.NORTH, position.getDirection());

       RobotGame.play(table, Arrays.asList("PLACE 0,0,NORTH", "LEFT", "REPORT"));
       num = table.getNumberOfRobots();
       Assert.assertEquals(2, num);
       robot = table.getRobots().get(1);
       
       position = robot.getPosition();
       Assert.assertEquals(0, position.getXPos());
       Assert.assertEquals(0, position.getYPos());
       Assert.assertEquals(Direction.WEST, position.getDirection());

       RobotGame.play(table, Arrays.asList("PLACE 1,2,EAST", "MOVE", "MOVE", "LEFT", "MOVE", "REPORT"));
       num = table.getNumberOfRobots();
       Assert.assertEquals(3, num);
       robot = table.getRobots().get(2);
       
       position = robot.getPosition();
       
       Assert.assertEquals(3, position.getXPos());
       Assert.assertEquals(3, position.getYPos());
       Assert.assertEquals(Direction.NORTH, position.getDirection());

       RobotGame.play(table, Arrays.asList("PLACE 2,5,SOUTH", "LEFT", "MOVE", "LEFT", "MOVE", "REPORT"));
       num = table.getNumberOfRobots();
       Assert.assertEquals(4, num);
       robot = table.getRobots().get(3);
       position = robot.getPosition();

       Assert.assertEquals(3, position.getXPos());
       Assert.assertEquals(6, position.getYPos());
       Assert.assertEquals(Direction.NORTH, position.getDirection());
   }

   @Test
   public void testInitialPlacementOutOfBounds() throws RobotException {
       RobotGame.play(table, Arrays.asList("PLACE -1,0,NORTH", "MOVE", "REPORT"));
   }

   @Test
   public void testEnsurePlaySafely() throws RobotException {
       RobotGame.play(table,
               Arrays.asList("PLACE 5,9,NORTH", "REPORT"));
       int num = table.getNumberOfRobots();
       Assert.assertNotEquals(0, num);
       Robot robot = table.getRobots().get(num-1);

       RobotPosition position = robot.getPosition();

       Assert.assertEquals(5, position.getXPos());
       Assert.assertEquals(9, position.getYPos());
       Assert.assertEquals(Direction.NORTH, position.getDirection());
   }

   @Test(expected = InvalidInputException.class)
   public void testInvalidCommand() throws RobotException {
       RobotGame.play(table,
               Arrays.asList("PLACE 1,10,NORTH", "MOVE", "LEFT TURN", "MOVE", "RIGHT", "REPORT"));
   }

   @Test(expected = InvalidInputException.class)
   public void testInvalidCoordinateValues() throws RobotException {
       RobotGame.play(table,
               Arrays.asList("PLACE AA,10,NORTH", "MOVE", "LEFT", "MOVE", "RIGHT", "REPORT"));
   }

   @Test(expected = InvalidInputException.class)
   public void testInvalidDirection() throws RobotException {
       RobotGame.play(table,
               Arrays.asList("PLACE 1,10,NORTH-EAST", "MOVE", "LEFT", "MOVE", "RIGHT", "REPORT"));
   }
}
