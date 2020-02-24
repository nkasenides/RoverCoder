Blockly.Blocks['move_forward'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Προχώρα");
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Προχώρα μπροστά");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_north'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Βορράς");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Βορράς.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_south'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Νότος");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Νότος.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_east'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Ανατολή");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Ανατολή.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['direction_west'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Δύση");
        this.setOutput(true, "Direction");
        this.setColour(60);
        this.setTooltip("Δύση.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['scan_color'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Ανίχνευση χρώματος");
        this.setOutput(true, "Color");
        this.setColour(230);
        this.setTooltip("Ανίχνευση χρώματος");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_none'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Κανένα χρώμα");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Κανένα χρώμα.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_black'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Μαύρο");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Μαύρο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_blue'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Μπλέ");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Μπλέ.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_green'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Πράσινο");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Πράσινο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_yellow'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Κίτρινο");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Κίτρινο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_red'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Κόκκινο");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Κόκκινο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_white'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Άσπρο");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Άσπρο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['color_brown'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Καφέ");
        this.setOutput(true, "Color");
        this.setColour(60);
        this.setTooltip("Καφέ.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['get_direction'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Κατεύθυνση");
        this.setOutput(true, "Direction");
        this.setColour(230);
        this.setTooltip("Επιστρέφει την κατεύθυνση (Βορράς, Ανατολή, Δύση, Νότος) στην οποία είναι στραμμένο το Rover.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['get_distance'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Απόσταση");
        this.setOutput(true, "Number");
        this.setColour(230);
        this.setTooltip("Επιστρέφει την απόσταση στην οποία έχει ανιχνευτεί εμπόδιο.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['run'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Εκτέλεση");
        this.appendStatementInput("run")
            .setCheck(null);
        this.setColour(300);
        this.setTooltip("Εκτελεί εντολές για την εξερεύνηση του πλανήτη Άρη.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['move_forward_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Προχώρα");
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
            .appendField("Στρίψε αριστερά");
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Στρίψε αριστερά.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_right'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Στρίψε δεξιά");
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Στρίψε δεξιά.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_right_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Στρίψε δεξιά");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Στρίψε δεξιά.");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turn_left_param'] = {
    init: function() {
        this.appendValueInput("time")
            .setCheck("Number")
            .appendField("Στρίψε αριστερά");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setColour(230);
        this.setTooltip("Στρίψε αριστερά.");
        this.setHelpUrl("");
    }
};