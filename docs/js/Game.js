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
export const UPDATE_DELAY = 1000;

const ROVER_IMAGE_PATH = "images/ev3.png";

export class Game {

    constructor(width, height) {
        this.width= width;
        this.height = height;
        this.canvas = document.createElement("canvas"); //Create the HTML canvas element
        document.getElementById("canvasWrapper").appendChild(this.canvas);
        this.canvas.style.backgroundColor = "lightgrey";
        this.context = this.canvas.getContext(CONTEXT); //Get the context of the canvas (2D)
        this.gameState = GameState.INIT;
        this.setup();
    }

    setup() {
        this.currentScale = 1;
        this.gameGrid = new GameGrid(this.width, this.height);
        this.startPosition = this.selectRandomStartPosition();
        this.finishPosition = this.getFinishPosition(this.startPosition);
        this.rover = new Rover(this.startPosition, this.gameGrid);
        this.roverImage = importImage(ROVER_IMAGE_PATH);
        this.cellSizeWidth = 0;
        this.cellSizeHeight = 0;
    }

    start() {
        this.resize();
        this.initializeScene();
        const game = this;
        this.gameState = GameState.STARTED;
        this.updateSceneInterval = setInterval(this.updateScene, UPDATE_DELAY, game); //Set an interval at which to update the screen
    }

    initializeScene() { }

    updateScene(game) {

        //Clear scene:
        game.context.clearRect(0, 0, game.canvas.width, game.canvas.height); //Clear the scene

        if (game.gameState === GameState.FINISHED) {
            this.writeText("Game Over", "green", 0, 0);
        }
        else {
            //Draw the grid:
            game.drawGrid();

            game.rover.moveForward();

            //Draw starting/finish positions:
            game.context.fillStyle = "lightgreen";
            game.context.fillRect(game.getXCoordinateFromPosition(game.startPosition.x), game.getYCoordinateFromPosition(game.startPosition.y), game.cellSizeWidth, game.cellSizeHeight);
            game.context.fillStyle = "red";
            game.context.fillRect(game.getXCoordinateFromPosition(game.finishPosition.x), game.getYCoordinateFromPosition(game.finishPosition.y), game.cellSizeWidth, game.cellSizeHeight);

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
        const scaledSize = Math.min(height, width) - 20;
        this.currentScale = DEFAULT_SIZE / scaledSize;
        this.canvas.width = scaledSize * 1.5;
        this.canvas.height = scaledSize;
        this.cellSizeWidth = scaledSize / this.width * 1.5;
        this.cellSizeHeight = scaledSize / this.height;
        this.roverImage.width = this.cellSizeWidth; //TODO NOT SURE IF WORKING
        this.roverImage.height = this.cellSizeHeight; //TODO NOT SURE IF WORKING
    }

    getXCoordinateFromPosition(position) {
        return position * this.cellSizeWidth;
    }

    getYCoordinateFromPosition(position) {
        return position * this.cellSizeHeight;
    }

    drawGrid() {
        for (let x = 0; x < this.width * this.cellSizeWidth; x += this.cellSizeWidth) {
            this.context.moveTo(x, 0);
            this.context.lineTo(x, this.canvas.height);
        }

        for (let y = 0; y < this.height * this.cellSizeHeight; y += this.cellSizeHeight) {
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
            xCoord = this.getXCoordinateFromPosition(this.rover.position.x);
        }
        if (this.startPosition.y > 0) {
            yCoord = this.getYCoordinateFromPosition(this.rover.position.y);
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
                yOffset = -this.cellSizeHeight;
                break;
            case 180:
                yOffset = -this.cellSizeHeight;
                xOffset = -this.cellSizeWidth;
                break;
            case 270:
                xOffset = -this.cellSizeWidth;
                break;
            case 360:
            case 0:
                break;
        }
        this.context.drawImage(this.roverImage, xOffset, yOffset, this.cellSizeWidth, this.cellSizeHeight);
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

    selectRandomStartPosition() {
        let xOrY0 = Math.random();
        if (xOrY0 > 0.5) {
            let x = 0;
            let maxIt = Math.random();
            if (maxIt > 0.5) {
                x = this.width - 1;
            }
            let y = Math.floor(Math.random() * this.height);
            return new Position2D(x, y);
        }
        else {
            let x = Math.floor(Math.random() * this.width);
            let y = 0;
            let maxIt = Math.random();
            if (maxIt > 0.5) {
                y = this.height - 1;
            }
            return new Position2D(x, y);
        }
    }

    getFinishPosition(startPosition) {
        return new Position2D(this.width - startPosition.x - 1, this.height - startPosition.y - 1);
    }

}

function importImage(filepath) {
    let image = new Image();
    image.src = filepath;
    return image;
}