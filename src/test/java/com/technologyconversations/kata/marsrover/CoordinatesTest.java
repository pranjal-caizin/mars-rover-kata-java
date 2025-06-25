package com.technologyconversations.kata.marsrover;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class CoordinatesTest {

    @Test
    public void shouldInitializeWithGivenXPoint() {
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, List.of());
        assertThat(coordinates.getX()).usingRecursiveComparison().isEqualTo(new Point(1, 99));
    }

    @Test
    public void shouldInitializeWithGivenYPoint() {
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, List.of());
        assertThat(coordinates.getY()).usingRecursiveComparison().isEqualTo(new Point(2, 99));
    }

    @Test
    public void shouldInitializeWithGivenDirection() {
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, List.of());
        assertThat(coordinates.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void shouldInitializeWithGivenObstacles() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, obstacles);
        assertThat(coordinates.getObstacles()).hasSameElementsAs(obstacles);
    }

    @Test
    public void moveForwardShouldIncreaseYWhenDirectionIsNorth() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, obstacles);

        Point expected = new Point(3, 99);

        coordinates.moveForward();

        assertThat(coordinates.getY()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.EAST, obstacles);

        Point expected = new Point(2, 99);

        coordinates.moveForward();

        assertThat(coordinates.getX()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.SOUTH, obstacles);

        Point expected = new Point(1, 99);

        coordinates.moveForward();

        assertThat(coordinates.getY()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.WEST, obstacles);

        Point expected = new Point(0, 99);

        coordinates.moveForward();

        assertThat(coordinates.getX()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveForwardShouldNotChangeLocationsWhenObstacleIsFound() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.EAST, obstacles);

        coordinates.setObstacles(List.of(new Obstacle(2, 2)));

        coordinates.move(coordinates.getDirection());

        assertThat(coordinates.getX().getLocation()).isEqualTo(1);
    }

    @Test
    public void moveBackwardShouldDecreaseYWhenDirectionIsNorth() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.NORTH, obstacles);

        Point expected = new Point(1, 99);

        coordinates.moveBackward();

        assertThat(coordinates.getY()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveBackwardShouldDecreaseXWhenDirectionIsEast() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.EAST, obstacles);

        Point expected = new Point(0, 99);

        coordinates.moveBackward();

        assertThat(coordinates.getX()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseYWhenDirectionIsSouth() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.SOUTH, obstacles);

        Point expected = new Point(3, 99);

        coordinates.moveBackward();

        assertThat(coordinates.getY()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void moveBackwardShouldIncreaseXWhenDirectionIsWest() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, Direction.WEST, obstacles);

        Point expected = new Point(2, 99);

        coordinates.moveBackward();

        assertThat(coordinates.getX()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldReturnFormattedCoordinatesAndDirection() {
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Direction direction = Direction.EAST;
        Coordinates coordinates = createCoordinates(1, 99, 2, 99, direction, obstacles);

        String expected = "1 X 2 " + direction.getShortName();
        assertThat(coordinates.toString()).isEqualTo(expected);
    }

    private Coordinates createCoordinates(int xLoc, int xMaxLoc, int yLoc, int yMaxLoc, Direction direction, List<Obstacle> obstacles) {
        return new Coordinates(new Point(xLoc, xMaxLoc), new Point(yLoc, yMaxLoc), direction, obstacles);
    }

}