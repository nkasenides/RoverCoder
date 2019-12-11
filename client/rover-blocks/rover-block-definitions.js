Blockly.Blocks['move_forward'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Move Forward");
    this.setPreviousStatement(true, null);
    this.setColour(230);
 this.setTooltip("Move forward.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['turn_anticlockwise'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Turn anti-clockwise");
    this.setPreviousStatement(true, null);
    this.setColour(230);
 this.setTooltip("Turn anti-clockwise.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['turn_clockwise'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Turn clockwise");
    this.setPreviousStatement(true, null);
    this.setColour(230);
 this.setTooltip("Turn clockwise.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['direction_north'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Direction.NORTH");
    this.setOutput(true, "Direction");
    this.setColour(60);
 this.setTooltip("Direction North.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['direction_south'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Direction.SOUTH");
    this.setOutput(true, "Direction");
    this.setColour(60);
 this.setTooltip("Direction South.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['direction_east'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Direction.EAST");
    this.setOutput(true, "Direction");
    this.setColour(60);
 this.setTooltip("Direction East.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['direction_west'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Direction.WEST");
    this.setOutput(true, "Direction");
    this.setColour(60);
 this.setTooltip("Direction West.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['scan_color'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Scan color");
    this.setOutput(true, "Color");
    this.setColour(230);
 this.setTooltip("Scan color.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_none'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.NONE");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color None.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_black'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.BLACK");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Black.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_blue'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.BLUE");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Blue.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_green'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.GREEN");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Green.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_yellow'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.YELLOW");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Yellow.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_red'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.RED");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Red.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_white'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.WHITE");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color White.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['color_brown'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Color.BROWN");
    this.setOutput(true, "Color");
    this.setColour(60);
 this.setTooltip("Color Brown.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['set_scan_distance'] = {
  init: function() {
    this.appendValueInput("distance")
        .setCheck("Number")
        .appendField("Set scan distance");
    this.setInputsInline(true);
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(230);
 this.setTooltip("Set the scan distance for the ");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['get_direction'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Get Direction");
    this.setOutput(true, "Direction");
    this.setColour(230);
 this.setTooltip("Returns the direction (North, East, West, South) the rover is facing to.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['scan'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Scan");
    this.setOutput(true, "Boolean");
    this.setColour(230);
 this.setTooltip("Scans for an obstruction within the set scanning distance.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['initialize'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Initialize");
    this.appendStatementInput("initialize")
        .setCheck(null);
    this.setColour(300);
 this.setTooltip("Initialize your rover.");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['run'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Run");
    this.appendStatementInput("run")
        .setCheck(null);
    this.setColour(300);
 this.setTooltip("Run commands to explore Mars.");
 this.setHelpUrl("");
  }
};