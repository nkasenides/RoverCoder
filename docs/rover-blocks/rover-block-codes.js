// Blockly.JavaScript['move_forward'] = function(block) {
//     return 'rover.moveForward();\nreturn;\n';
// };
//
// Blockly.JavaScript['direction_north'] = function(block) {
//     var code = 'Direction.NORTH';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };
//
// Blockly.JavaScript['direction_south'] = function(block) {
//     var code = 'Direction.SOUTH';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };
//
// Blockly.JavaScript['direction_east'] = function(block) {
//     var code = 'Direction.EAST';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };
//
// Blockly.JavaScript['direction_west'] = function(block) {
//     var code = 'Direction.WEST';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };

Blockly.JavaScript['scan_color'] = function(block) {
    var code = 'rover.color()';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

// Blockly.JavaScript['color_none'] = function(block) {
//     var code = 'EV3Color.NONE';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };

// Blockly.JavaScript['color_black'] = function(block) {
//     var code = 'EV3Color.BLACK';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };

Blockly.JavaScript['color_blue'] = function(block) {
    var code = 'rover.BLUE';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_green'] = function(block) {
    var code = 'rover.GREEN';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_yellow'] = function(block) {
    var code = 'rover.YELLOW';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_red'] = function(block) {
    var code = 'rover.RED';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

// Blockly.JavaScript['color_white'] = function(block) {
//     var code = 'EV3Color.WHITE';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };
//
// Blockly.JavaScript['color_brown'] = function(block) {
//     var code = 'EV3Color.BROWN;';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };

// Blockly.JavaScript['get_direction'] = function(block) {
//     var code = 'rover.getDirection()';
//     return [code, Blockly.JavaScript.ORDER_NONE];
// };

Blockly.JavaScript['get_distance'] = function(block) {
    var code = 'rover.distance()';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['run'] = function(block) {
    var statements_run = Blockly.JavaScript.statementToCode(block, 'run');
    return 'function runJavaScript(rover) {\n'
        + statements_run +
        "}\n\n";
};

Blockly.JavaScript['move_forward_param'] = function(block) {
    var value_time = Blockly.JavaScript.valueToCode(block, 'time', Blockly.JavaScript.ORDER_ATOMIC);
    return 'rover.moveForward(' + value_time + ');\nreturn;\n';
};

// Blockly.JavaScript['turn_left'] = function(block) {
//     return 'rover.turnLeft();\nreturn;\n';
// };
//
// Blockly.JavaScript['turn_right'] = function(block) {
//     return 'rover.turnRight();\nreturn;\n';
// };

Blockly.JavaScript['turn_right_param'] = function(block) {
    var value_time = Blockly.JavaScript.valueToCode(block, 'time', Blockly.JavaScript.ORDER_ATOMIC);
    return 'rover.turnRight(' + value_time + ');\nreturn;\n';
};

Blockly.JavaScript['turn_left_param'] = function(block) {
    var value_time = Blockly.JavaScript.valueToCode(block, 'time', Blockly.JavaScript.ORDER_ATOMIC);
    return 'rover.turnLeft(' + value_time + ');\nreturn;\n';
};