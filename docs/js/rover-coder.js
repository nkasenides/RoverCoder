/*

    Implements functionality for virtual Rover Coder training sessions on the HTML5 canvas.

    Author: Nicos Kasenides (nkasenides@uclan.ac.uk)
    Date: 21-Dec-2019

*/

/**
 * SPECTRUM - A simple JavaScript game.
 */

//Constants:
const CANVAS_ID = "canvas";
const CONTEXT = "2d";
const DEFAULT_SIZE = 500;
const NUM_OF_CELLS = 20;
const UPDATE_DELAY = 20;

//Globals:
let canvas;
let context;
let currentScale = 1;
let updateSceneInterval;
let cellSize;
let startPosition;
let finishPosition;


//Images:
const ROVER_IMAGE_PATH = "images/ev3.png";
let roverImage = importImage(ROVER_IMAGE_PATH);

//TODO: Declare your blocks here.

let items = [];

/**
 * Creates and configures the canvas element.
 */
function startGame() {
    canvas = document.createElement(CANVAS_ID); //Create the HTML canvas element
    document.getElementById("canvasWrapper").appendChild(canvas);
    canvas.style.backgroundColor = "lightgrey";
    context = canvas.getContext(CONTEXT); //Get the context of the canvas (2D)
    resize();
    updateSceneInterval = setInterval(updateScene, UPDATE_DELAY); //Set an interval at which to update the screen
    initializeScene(); //Start the scene initialization
}

/**
 * Initializes the objects at the start of the game.
 */
function initializeScene() {
    startPosition = selectRandomStartPosition();
    finishPosition = getFinishPosition(startPosition);
    console.log(startPosition.x + " " + startPosition.y);
    console.log(finishPosition.x + " " + finishPosition.y);
    console.log(roverImage.width + " " + roverImage.height);


}

function updateScene() {

    //--- Step 1 - Clear scene:
    context.clearRect(0, 0, canvas.width, canvas.height); //Clear the scene

    //--- Step 2 - Game logic:

    //--- Step 3 - Re-draw scene:

    //Draw the grid:
    drawGrid();

    //Draw starting/finish positions:
    context.fillStyle = "lightgreen";
    context.fillRect(getCoordinateFromPosition(startPosition.x), getCoordinateFromPosition(startPosition.y), cellSize, cellSize);
    context.fillStyle = "red";
    context.fillRect(getCoordinateFromPosition(finishPosition.x), getCoordinateFromPosition(finishPosition.y), cellSize, cellSize);

    //Draw the robot:
    let xCoord = 0;
    let yCoord = 0;
    if (startPosition.x > 0) {
        xCoord = getCoordinateFromPosition(startPosition.x);
    }
    if (startPosition.y > 0) {
        yCoord = getCoordinateFromPosition(startPosition.y);
    }
    console.log(xCoord + " " + yCoord);
    context.drawImage(roverImage, xCoord, yCoord, cellSize, cellSize);

}

function resize() {
    const width = window.innerWidth;
    const height = window.innerHeight;
    const scaledSize = Math.min(height, width);
    currentScale = DEFAULT_SIZE / scaledSize;
    canvas.width = scaledSize;
    canvas.height = scaledSize;
    cellSize = scaledSize / NUM_OF_CELLS;
}

function importImage(filepath) {
    let image = new Image();
    image.src = filepath;
    return image;
}

function selectRandomStartPosition() {
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

function getFinishPosition(startPosition) {
    return new Position2D(NUM_OF_CELLS - startPosition.x - 1, NUM_OF_CELLS - startPosition.y - 1);
}

function getCoordinateFromPosition(position) {
    return position * cellSize;
}

function writeText(text, color, size, x, y) {
    let previousStyle = context.fillStyle;
    context.font = size + "px Arial";
    context.fillStyle = color;
    context.fillText(text, x, y);
    context.fillStyle = previousStyle;
}

function drawGrid() {
    for (let x = 0; x < NUM_OF_CELLS * cellSize; x += cellSize) {
        context.moveTo(x, 0);
        context.lineTo(x, canvas.height);
    }

    for (let y = 0; y < NUM_OF_CELLS * cellSize; y += cellSize) {
        context.moveTo(0, y);
        context.lineTo(canvas.width, y);
    }

    context.strokeStyle = 'white';
    context.stroke();
}