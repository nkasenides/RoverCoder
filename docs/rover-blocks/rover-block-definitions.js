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

Blockly.Blocks['direction_north'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("North");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Direction North.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_south'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("South");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Direction South.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_east'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("East");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Direction East.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_west'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("West");
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

// Blockly.Blocks['color_none'] = {
//     init: function() {
//         this.appendDummyInput()
//             .appendField("No Color");
//         this.setOutput(true, "Color");
//         this.setColour(60);
//         this.setTooltip("Color None.");
//         this.setHelpUrl("");
//     }
// };
//
// Blockly.Blocks['color_black'] = {
//     init: function() {
//         this.appendDummyInput()
//             .appendField("Black");
//         this.setOutput(true, "Color");
//         this.setColour(60);
//         this.setTooltip("Color Black.");
//         this.setHelpUrl("");
//     }
// };

Blockly.Blocks['color_blue'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Blue");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Color Blue.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_green'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Green");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Color Green.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_yellow'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Yellow");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Color Yellow.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_red'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Red");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Color Red.");
        this.setHelpUrl("");
    }
};

// Blockly.Blocks['color_white'] = {
//     init: function() {
//         this.appendDummyInput()
//             .appendField("White");
//         this.setOutput(true, "Color");
//         this.setColour(60);
//         this.setTooltip("Color White.");
//         this.setHelpUrl("");
//     }
// };
//
// Blockly.Blocks['color_brown'] = {
//     init: function() {
//         this.appendDummyInput()
//             .appendField("Brown");
//         this.setOutput(true, "Color");
//         this.setColour(60);
//         this.setTooltip("Color Brown.");
//         this.setHelpUrl("");
//     }
// };

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

Blockly.Blocks['get_distance'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Get Distance");
        this.setOutput(true, "Number");
        this.setColour(230);
        this.setTooltip("Returns the distance to obstruction.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['initialize'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Initialize");
        this.appendStatementInput("statements")
            .setCheck(null);
        this.setColour(300);
        this.setTooltip("Initialize the Rover.");
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

Blockly.Blocks['move_forward_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Move Forwards");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['move_backward_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Move Backwards");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_left'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Turn left");
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Turn left.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_right'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Turn right");
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Turn right.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_right_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Turn right");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Turn right.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_left_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Turn left");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Turn left.");
        this.setHelpUrl("");
    }
};