package com.demo.insignia.robot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Co-ordinate representation.
 *
 * @author Joby
 *
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private int xPos = 0;
    private int yPos = 0;
}
