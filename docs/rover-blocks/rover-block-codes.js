Blockly.JavaScript['move_forward'] = function (block) {
  //TODO Change the package
    var code = "__retVal = Packages.org.inspirecenter.amazechallenge.algorithms.PlayerMove.MOVE_FORWARD;\n" + //Change return value to MOVE_FORWARD
        "propNames.forEach(function(item, index) { instance.setJavascriptArgument(item, this[item]); } );\n" + //Save variable to map before returning
        "return __retVal;\n"; //return to Java
    return code;
};

Blockly.JavaScript['turn_anticlockwise'] = function (block) {
  //TODO Change the package
  var code = "__retVal = Packages.org.inspirecenter.amazechallenge.algorithms.PlayerMove.TURN_COUNTERCLOCKWISE;\n" + //Change return value to TURN_COUNTERCLOCKWISE
      "propNames.forEach(function(item, index) { instance.setJavascriptArgument(item, this[item]); } );\n" + //Save variable to map before returning
      "return __retVal;\n"; //return to Java
  return code;
};

Blockly.JavaScript['turn_clockwise'] = function (block) {
  //TODO Change the package
  var code = "__retVal = Packages.org.inspirecenter.amazechallenge.algorithms.PlayerMove.TURN_CLOCKWISE;\n" + //Change return value to TURN_CLOCKWISE
      "propNames.forEach(function(item, index) { instance.setJavascriptArgument(item, this[item]); } );\n" + //Save variable to map before returning
      "return __retVal;\n"; //return to Java
  return code;
};

Blockly.JavaScript['direction_north'] = function (block) {
  //TODO Change the package
  var code = 'Packages.org.inspirecenter.amazechallenge.model.Direction.NORTH';
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_south'] = function (block) {
  //TODO Change the package
  var code = 'Packages.org.inspirecenter.amazechallenge.model.Direction.SOUTH';
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_east'] = function (block) {
  //TODO Change the package
  var code = 'Packages.org.inspirecenter.amazechallenge.model.Direction.EAST';
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_west'] = function (block) {
  //TODO Change the package
  var code = 'Packages.org.inspirecenter.amazechallenge.model.Direction.WEST';
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['scan_color'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_none'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_black'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_blue'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_green'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_yellow'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_red'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_white'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_brown'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['set_scan_distance'] = function (block) {
    var value_distance = Blockly.JavaScript.valueToCode(block, 'distance', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...;\n';
    return code;
};

Blockly.JavaScript['get_direction'] = function (block) {
  var code = 'instance.getDirection()';
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['scan'] = function (block) {
    // TODO: Assemble JavaScript into codeElement variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['initialize'] = function (block) {
  var statements_init = Blockly.JavaScript.statementToCode(block, 'initialize');
  var code = "function init(instance) {\n" +
      //Detect the declared variables:
      "  populatePropNames();\n" +
      //Run the user's initialization statements:
      statements_init +
      //Save the values into the map:
      "  propNames.forEach(function(item, index) { instance.setJavascriptArgument(item, this[item]); } );\n" +
      "}/*end init()*/\n\n";
  return code;
};

Blockly.JavaScript['run'] = function (block) {
  var statements_run = Blockly.JavaScript.statementToCode(block, 'run');
  var code = "function run(instance) {\n" +
      //Detect the declared variables:
      "  populatePropNames();\n" +
      //Default return value:
      "  var __retVal = Packages.org.inspirecenter.amazechallenge.algorithms.PlayerMove.NO_MOVE;\n" +
      //Get the values of the variables from the map:
      "  propNames.forEach(function(item, index) {\n" +
      "      var mapValue = instance.getJavascriptArgument(item);\n" +
      //Check if this is a boolean literal
      "      if (mapValue == 'false') this[item] = false;\n" +
      "      else if (mapValue == 'true') this[item] = true;\n" +
      //Check if this is a number literal
      "      else if (!isNaN(mapValue)) this[item] = Number(mapValue); \n" +
      //Check if this is a direction literal
      "      else if (mapValue == 'north') this[item] = Packages.org.inspirecenter.amazechallenge.model.Direction.NORTH;\n" +
      "      else if (mapValue == 'south') this[item] = Packages.org.inspirecenter.amazechallenge.model.Direction.SOUTH;\n" +
      "      else if (mapValue == 'west') this[item] = Packages.org.inspirecenter.amazechallenge.model.Direction.WEST;\n" +
      "      else if (mapValue == 'east') this[item] = Packages.org.inspirecenter.amazechallenge.model.Direction.EAST;\n" +
      //Otherwise it's a string/character literal
      "  });\n" +
      //Include the player's code:
      "\n\n/*---- PLAYER'S CODE ----*/\n\n" + statements_run + "\n\n/*---------------------*/\n\n" +
      //Save all of the values back to the map:
      "  propNames.forEach(function(item, index) { instance.setJavascriptArgument(item, this[item]); } );\n" +
      //If the code does not return yet, return the default value (NO_MOVE).
      "  return __retVal;\n" +

      "}/*end run()*/\n";
  return code;
};