package io.github.nearchos.rovercoder;

import robutev3.core.Color;

/**
 * @author Nearchos
 * Created: 13-Feb-20
 */
public interface RoverCoderInterface {

    // constants used inside JavaScript
    Color RED = Color.RED;
    Color GREEN = Color.GREEN;
    Color BLUE = Color.BLUE;
    Color YELLOW = Color.YELLOW;

    void moveForward(final int ms);
    void moveBackward(final int ms);
    void turnLeft(final int ms);
    void turnRight(final int ms);
    int distance();
    Color color();
}
