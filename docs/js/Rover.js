import {Direction} from "./Direction.js";

export class Rover {

    constructor(position, gameGrid) {
        this.position = position;
        this.gameGrid = gameGrid;
        this.direction = Direction.NORTH;
    }

    turnClockwise() {
        switch (direction) {
            case Direction.NORTH:
                this.direction = Direction.EAST;
                break;
            case Direction.EAST:
                this.direction = Direction.SOUTH;
                break;
            case Direction.SOUTH:
                this.direction = Direction.WEST;
                break;
            case Direction.WEST:
                this.direction = Direction.NORTH;
                break;
            default:
                console.error("Cannot turn clockwise, invalid direction '" + direction + "'.");
        }
    }

    turnAntiClockwise() {
        switch (direction) {
            case Direction.NORTH:
                this.direction = Direction.WEST;
                break;
            case Direction.EAST:
                this.direction = Direction.NORTH;
                break;
            case Direction.SOUTH:
                this.direction = Direction.EAST;
                break;
            case Direction.WEST:
                this.direction = Direction.SOUTH;
                break;
            default:
                console.error("Cannot turn anticlockwise, invalid direction '" + direction + "'.");
        }
    }

    moveForward() {
        //TODO
    }

}