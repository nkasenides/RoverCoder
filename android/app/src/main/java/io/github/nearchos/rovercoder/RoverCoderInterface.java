package io.github.nearchos.rovercoder;

/**
 * @author Nearchos
 * Created: 13-Feb-20
 */
public interface RoverCoderInterface {

    void moveForward(final int ms);
    void moveBackward(final int ms);
    void turnLeft(final int ms);
    void turnRight(final int ms);
    int distance();
}
