package model;

import com.nkasenides.mmog.model.Cell;

public class RCCell extends Cell {

    private RCCellType type;
    private int height;

    public RCCell(int row, int col, RCCellType type, int height) {
        super(row, col);
        this.type = type;
        this.height = height;
    }

    public RCCell(RCCellType type, int height) {
        this.type = type;
        this.height = height;
    }

    public RCCellType getType() {
        return type;
    }

    public void setType(RCCellType type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
