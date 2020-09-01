package model;

import com.nkasenides.mmog.model.TileWorldGenerator;
import com.nkasenides.mmog.model.world.TileWorld;
import com.raylabz.opensimplex.NoiseDetail;
import com.raylabz.opensimplex.OpenSimplexNoise;
import com.raylabz.opensimplex.Range;
import com.raylabz.opensimplex.RangedValue;

public class RCWorldGenerator extends TileWorldGenerator<RCChunk, RCCell> {

    private final OpenSimplexNoise noiseGenerator = new OpenSimplexNoise(tileWorld.getSeed());

    public RCWorldGenerator(TileWorld tileWorld) {
        super(tileWorld, RCChunk.class);
        noiseGenerator.setNoiseDetail(NoiseDetail.EIGHTH);
        noiseGenerator.setFeatureSize(20);
        noiseGenerator.setPower(2);
    }

    public RCCell generateCell(int row, int col) {
        final RangedValue value = noiseGenerator.getNoise2D(row, col);
        double heightValue = value.getValue(new Range(-255, 255));
        RCCellType cellType = null;
        if (heightValue > 255) {
            cellType = RCCellType.ROCK;
            heightValue = 255;
        }
        else if (heightValue > 200) {
            cellType = RCCellType.ROCK;
        }
        else if (heightValue > 50) {
            cellType = RCCellType.SAND;
        }
        else if (heightValue > 0) {
            cellType = RCCellType.GRAVEL;
        }
        else if (heightValue > -255) {
            cellType = RCCellType.QUICKSAND;
        }
        else if (heightValue < -255) {
            cellType = RCCellType.QUICKSAND;
            heightValue = -255;
        }

        return new RCCell(row, col, cellType, (int) heightValue);
    }

}
