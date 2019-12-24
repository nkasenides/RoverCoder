/*

    Implements functionality for virtual Rover Coder training sessions on the HTML5 canvas.

    Author: Nicos Kasenides (nkasenides@uclan.ac.uk)
    Date: 21-Dec-2019

*/

import {Position2D} from "./Position2D.js";
import {Rover} from "./Rover.js";
import {GameGrid} from "./GameGrid.js";
import {Direction} from "./Direction.js";
import {Move} from "./Move.js";
import {GameState} from "./GameState.js";

//Constants:
export const CONTEXT = "2d";
export const DEFAULT_SIZE = 500;
export const NUM_OF_CELLS = 20;
export const UPDATE_DELAY = 1000;

const ROVER_IMAGE_PATH = "images/ev3.png";

export class Game {

    constructor(size) {
        this.size = size;
        this.canvas = document.createElement("canvas"); //Create the HTML canvas element
        document.getElementById("canvasWrapper").appendChild(this.canvas);
        this.canvas.style.backgroundColor = "lightgrey";
        this.context = this.canvas.getContext(CONTEXT); //Get the context of the canvas (2D)
        this.gameState = GameState.INIT;
        this.setup();
    }

    setup() {
        this.currentScale = 1;
        this.gameGrid = new GameGrid(NUM_OF_CELLS);
        this.startPosition = Game.selectRandomStartPosition();
        this.finishPosition = Game.getFinishPosition(this.startPosition);
        this.rover = new Rover(this.startPosition, this.gameGrid);
        this.roverImage = importImage(ROVER_IMAGE_PATH);
        this.cellSize = 0;
    }

    start() {
        this.resize();
        this.initializeScene();
        const game = this;
        this.gameState = GameState.STARTED;
        this.updateSceneInterval = setInterval(this.updateScene, UPDATE_DELAY, game); //Set an interval at which to update the screen
    }

    initializeScene() {
        //TODO REMOVE:
        // console.log(this.startPosition.x + " " + this.startPosition.y);
        // console.log(this.finishPosition.x + " " + this.finishPosition.y);
        // console.log(this.roverImage.width + " " + this.roverImage.height);
    }

    updateScene(game) {

        //Clear scene:
        game.context.clearRect(0, 0, game.canvas.width, game.canvas.height); //Clear the scene

        if (game.gameState === GameState.FINISHED) {
            this.writeText("Game Over", "green", 0, 0);
        }
        else {
            //Draw the grid:
            game.drawGrid();

            //Draw starting/finish positions:
            game.context.fillStyle = "lightgreen";
            game.context.fillRect(game.getCoordinateFromPosition(game.startPosition.x), game.getCoordinateFromPosition(game.startPosition.y), game.cellSize, game.cellSize);
            game.context.fillStyle = "red";
            game.context.fillRect(game.getCoordinateFromPosition(game.finishPosition.x), game.getCoordinateFromPosition(game.finishPosition.y), game.cellSize, game.cellSize);

            //Draw the robot:
            game.drawRover();
        }
    }

    makeMove(move) {
        // console.log(move);
        this.rover.doMove(move);
    }

    /*------------------------ UTIL FUNCTIONS --------------------*/
    resize() {
        const width = window.innerWidth;
        const height = window.innerHeight;
        const scaledSize = Math.min(height, width);
        this.currentScale = DEFAULT_SIZE / scaledSize;
        this.canvas.width = scaledSize;
        this.canvas.height = scaledSize;
        this.cellSize = scaledSize / NUM_OF_CELLS;
        this.roverImage.width = this.cellSize; //TODO NOT SURE IF WORKING
        this.roverImage.height = this.cellSize; //TODO NOT SURE IF WORKING
    }

    getCoordinateFromPosition(position) {
        return position * this.cellSize;
    }

    drawGrid() {
        for (let x = 0; x < NUM_OF_CELLS * this.cellSize; x += this.cellSize) {
            this.context.moveTo(x, 0);
            this.context.lineTo(x, this.canvas.height);
        }

        for (let y = 0; y < NUM_OF_CELLS * this.cellSize; y += this.cellSize) {
            this.context.moveTo(0, y);
            this.context.lineTo(this.canvas.width, y);
        }

        this.context.strokeStyle = 'white';
        this.context.stroke();
    }

    drawRover() {
        let xCoord = 0;
        let yCoord = 0;
        if (this.startPosition.x > 0) {
            xCoord = this.getCoordinateFromPosition(this.rover.position.x);
        }
        if (this.startPosition.y > 0) {
            yCoord = this.getCoordinateFromPosition(this.rover.position.y);
        }
        this.drawRotatedRover(this.roverImage, xCoord, yCoord, this.rover.direction);
    }

    //Kept as generic:
    drawRotatedImage(image, x, y, angle) {
        this.context.save();
        this.context.translate(x, y);
        this.context.rotate(angle * (Math.PI / 180));
        let xOffset = 0;
        let yOffset = 0;
        switch (angle) {
            case 90:
                yOffset = -this.cellSize;
                break;
            case 180:
                yOffset = -this.cellSize;
                xOffset = -this.cellSize;
                break;
            case 270:
                xOffset = -this.cellSize;
                break;
            case 360:
            case 0:
                break;
        }
        this.context.drawImage(this.roverImage, xOffset, yOffset, this.cellSize, this.cellSize);
        this.context.restore();
    }

    drawRotatedRover(image, x, y, direction) {
        switch (direction) {
            case Direction.NORTH:
                this.drawRotatedImage(image, x, y, 0);
                break;
            case Direction.EAST:
                this.drawRotatedImage(image, x, y, 90);
                break;
            case Direction.SOUTH:
                this.drawRotatedImage(image, x, y, 180);
                break;
            case Direction.WEST:
                this.drawRotatedImage(image, x, y, 270);
                break;
        }
    }

    writeText(text, color, size, x, y) {
        const previousStyle = this.context.fillStyle;
        this.context.font = size + "px Arial";
        this.context.fillStyle = color;
        this.context.fillText(text, x, y);
        this.context.fillStyle = previousStyle;
    }

    static selectRandomStartPosition() {
        let xOrY0 = Math.random();
        if (xOrY0 > 0.5) {
            let x = 0;
            let maxIt = Math.random();
            if (maxIt > 0.5) {
                x = NUM_OF_CELLS - 1;
            }
            let y = Math.floor(Math.random() * NUM_OF_CELLS);
            return new Position2D(x, y);
        }
        else {
            let x = Math.floor(Math.random() * NUM_OF_CELLS);
            let y = 0;
            let maxIt = Math.random();
            if (maxIt > 0.5) {
                y = NUM_OF_CELLS - 1;
            }
            return new Position2D(x, y);
        }
    }

    static getFinishPosition(startPosition) {
        return new Position2D(NUM_OF_CELLS - startPosition.x - 1, NUM_OF_CELLS - startPosition.y - 1);
    }

}

function importImage(filepath) {
    let image = new Image();
    image.src = filepath;
    return image;
}