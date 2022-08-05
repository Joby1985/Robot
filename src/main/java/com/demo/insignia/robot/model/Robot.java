package com.demo.insignia.robot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model representing Robot class
 *
 * @author Joby
 *
 */
@AllArgsConstructor
@Data
public class Robot {
    private RobotPosition position;

    private String name;
}
