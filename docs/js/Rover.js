import {Direction} from "./Direction.js";
import {Move} from "./Move.js";
import {Position2D} from "./Position2D.js";

export class Rover {

    constructor(position, gameGrid) {
        this.position = new Position2D(position.x, position.y);
        this.gameGrid = gameGrid;
        this.direction = Direction.NORTH;
    }

    doMove(move) {
        switch (move) {
            case Move.MOVE_FORWARD:
                this.moveForward();
                break;
            case Move.TURN_ANTICLOCKWISE:
                this.turnAntiClockwise();
                break;
            case Move.TURN_CLOCKWISE:
                this.turnClockwise();
                break;
            default:
                console.error("Invalid move '" + move + "'.");
        }
    }

    turnClockwise() {
        switch (this.direction) {
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
        switch (this.direction) {
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
        switch (this.direction) {
            case Direction.NORTH:
                if (this.position.y - 1 >= 0) {
                    this.position.y = this.position.y - 1;
                }
                break;
            case Direction.SOUTH:
                if (this.position.y + 1 < this.gameGrid.size) {
                    this.position.y = this.position.y + 1;
                }
                break;
            case Direction.WEST:
                if (this.position.x - 1 >= 0) {
                    this.position.x = this.position.x - 1;
                }
                break;
            case Direction.EAST:
                if (this.position.x + 1 < this.gameGrid.size) {
                    this.position.x = this.position.x + 1;
                }
                break;
        }
    }

}