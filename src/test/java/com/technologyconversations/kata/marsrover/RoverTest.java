package com.technologyconversations.kata.marsrover;

import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

/*
Source: http://dallashackclub.com/rover

Develop an api that moves a rover around on a grid.
* You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
* - The rover receives a character array of commands.
* - Implement commands that move the rover forward/backward (f,b).
* - Implement commands that turn the rover left/right (l,r).
* - Implement wrapping from one edge of the grid to another. (planets are spheres after all)
* - Implement obstacle detection before each move to a new square.
*   If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.
*/

public class RoverTest {

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);
        assertThat(rover.getCoordinates()).usingRecursiveComparison().isEqualTo(new Coordinates(new Point(1, 9), new Point(2, 9), Direction.NORTH, List.of()));
    }

    @Test
    public void shouldMoveForwardWhenCommandIsF() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveSingleCommand('F');

        assertThat(rover.getCoordinates().getY().getLocation()).isEqualTo(3);
    }

    @Test
    public void shouldMoveBackwardWhenCommandIsB() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveSingleCommand('B');

        assertThat(rover.getCoordinates().getY().getLocation()).isEqualTo(1);
    }

    @Test
    public void shouldTurnLeftWhenCommandIsL() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveSingleCommand('L');

        assertThat(rover.getCoordinates().getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void shouldTurnRightWhenCommandIsR() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveSingleCommand('R');

        assertThat(rover.getCoordinates().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void shouldIgnoreCaseInCommands() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveSingleCommand('r');

        assertThat(rover.getCoordinates().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void shouldThrowExceptionWhenCommandIsUnknown() {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        assertThatThrownBy(() -> rover.receiveSingleCommand('X'))
                .isInstanceOf(Exception.class);
    }

    @Test
    public void shouldBeAbleToReceiveMultipleCommands() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveCommands("RFR");

        assertThat(rover.getCoordinates().getX().getLocation()).isEqualTo(2);
        assertThat(rover.getCoordinates().getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void receiveCommandShouldWrapFromOneEdgeOfTheGridToAnother() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveCommands("LFFF");

        assertThat(rover.getCoordinates().getX().getLocation()).isEqualTo(8);
    }

    @Test
    public void shouldStopWhenObstacleIsFound() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.EAST);

        rover.getCoordinates().setObstacles(List.of(new Obstacle(3, 2)));

        rover.receiveCommands("FFFRF");

        assertThat(rover.getCoordinates().getX().getLocation()).isEqualTo(2);
        assertThat(rover.getCoordinates().getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void positionShouldReturnFormattedCoordinates() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.NORTH);

        rover.receiveCommands("LFFFRFF");

        assertThat(rover.getPosition()).isEqualTo("8 X 4 N");
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        Rover rover = createRover(1, 9, 2, 9, Direction.EAST);

        rover.getCoordinates().setObstacles(List.of(new Obstacle(2, 2)));

        rover.receiveCommands("F");

        assertThat(rover.getPosition()).endsWith(" NOK");
    }

    private Rover createRover(int xLoc, int xMaxLoc, int yLoc, int yMaxLoc, Direction direction) {
        Coordinates roverCoordinates = new Coordinates(new Point(xLoc, xMaxLoc), new Point(yLoc, yMaxLoc), direction, List.of());
        return new Rover(roverCoordinates);
    }

}