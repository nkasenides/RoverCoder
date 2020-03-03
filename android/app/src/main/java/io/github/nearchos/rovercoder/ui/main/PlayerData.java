package io.github.nearchos.rovercoder.ui.main;

import java.io.Serializable;

/**
 * @author Nearchos
 * Created: 03-Mar-20
 */
public class PlayerData implements Serializable {

    private final String playerName;
    private final String code;
    private final int stepCurrent;
    private final int stepsTotal;

    public PlayerData(String playerName, String code, int stepCurrent, int stepsTotal) {
        this.playerName = playerName;
        this.code = code;
        this.stepCurrent = stepCurrent;
        this.stepsTotal = stepsTotal;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getCode() {
        return code;
    }

    public int getStepCurrent() {
        return stepCurrent;
    }

    public int getStepsTotal() {
        return stepsTotal;
    }

    public boolean isFinished() {
        return stepCurrent >= stepsTotal;
    }

    PlayerData increaseCurrentStep() {
        return new PlayerData(playerName, code, stepCurrent + 1, stepsTotal);
    }

    public static final String DEFAULT_PLAYER_NAME = "__System__";

    public static final String DEFAULT_JAVASCRIPT_CODE =
            "function runJavaScript(rover) {\n" +
                    "  if (rover.distance() < 200) {\n" +
                    "    rover.turnRight(500);\n" +
                    "  } else if (rover.color() == rover.RED) {\n" +
                    "    rover.turnLeft(500);\n" +
                    "  } else {\n" +
                    "    rover.moveForward(100);\n" +
                    "  }\n" +
                    "}";

    public static final int DEFAULT_STEPS_TOTAL = 60;

    public static final PlayerData DEFAULT_PLAYER_DATA = new PlayerData(DEFAULT_PLAYER_NAME, DEFAULT_JAVASCRIPT_CODE, 0, DEFAULT_STEPS_TOTAL);
}