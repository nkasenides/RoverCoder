Blockly.JavaScript['move_forward'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['turn_anticlockwise'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['turn_clockwise'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['direction_north'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_south'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_east'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['direction_west'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['scan_color'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_none'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_black'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_blue'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_green'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_yellow'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_red'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_white'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color_brown'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['set_scan_distance'] = function(block) {
  var value_distance = Blockly.JavaScript.valueToCode(block, 'distance', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['get_direction'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['scan'] = function(block) {
  // TODO: Assemble JavaScript into codeElement variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['initialize'] = function(block) {
  var statements_initialize = Blockly.JavaScript.statementToCode(block, 'initialize');
  // TODO: Assemble JavaScript into codeElement variable.
  var code =
      "function initialize() {\n" +
      statements_initialize + "\n" +
      "}" + '\n';
  return code;
};

Blockly.JavaScript['run'] = function(block) {
  var statements_run = Blockly.JavaScript.statementToCode(block, 'run');
  // TODO: Assemble JavaScript into codeElement variable.
  var code =
      "function run() {\n" +
      statements_run + "\n" +
      "}" + '\n';
  return code;
};