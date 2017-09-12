import enums.TileColour;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.fail;

public class TileTest {

    @Test
    public void tileOnlyAcceptsEnumForColour() throws Exception {
        Tile tile = new Tile(TileColour.BLACK, 0);

        assertThat(tile.getColour(), is(TileColour.BLACK));
    }

    @Test
    public void tileAcceptsNumberInConstructor() throws Exception {
        Tile tile = new Tile(TileColour.BLUE, 10);

        assertThat(tile.getNumber(), is(10));
    }

    @Test
    public void tileConstructorThrowsIllegalArgumentException_WhenColourIsNull() throws Exception {
        Tile tile = null;
        try {
            tile = new Tile(null, 5);
            fail("Expected IllegalArgumentException due to null tile colour");
        } catch (IllegalArgumentException e) {
            assertThat(tile, is(nullValue()));
        }
    }

    @Test
    public void tileConstructorThrowsIllegalArgumentException_WhenNumberIsLessThanZero() throws Exception {
        Tile tile = null;
        try {
            tile = new Tile(TileColour.RED, -1);
            fail("Expected IllegalArgumentException due to number <0");
        } catch (IllegalArgumentException e) {
            assertThat(tile, is(nullValue()));
        }
    }

    @Test
    public void tileConstructorThrowsIllegalArgumentException_WhenNumberIsGreaterThan13() throws Exception {
        Tile tile = null;
        try {
            tile = new Tile(TileColour.YELLOW, 14);
            fail("Expected IllegalArgumentException due to number >13");
        } catch (IllegalArgumentException e) {
            assertThat(tile, is(nullValue()));
        }
    }

    @Test
    public void tileAllowsNumber0AsWildcard() throws Exception {
        Tile tile = new Tile(TileColour.YELLOW, 0);

        assertThat(tile, is(notNullValue()));
    }

    @Test
    public void tileAllowsNumber13() throws Exception {
        Tile tile = new Tile(TileColour.YELLOW, 13);

        assertThat(tile, is(notNullValue()));
    }

    @Test
    public void ensureTilesAreEqualWhenTheirColourAndNumberAreTheSame() throws Exception {
        Tile firstBlackOneTile = new Tile(TileColour.BLACK, 1);
        Tile secondBlackOneTile = new Tile(TileColour.BLACK, 1);

        assertThat(firstBlackOneTile, is(secondBlackOneTile));
    }

    @Test
    public void ensureTilesAreNotEqualWhenTheyAreDifferentColours() throws Exception {
        Tile firstBlackTile = new Tile(TileColour.BLACK, 2);
        Tile firstBlueTile = new Tile(TileColour.BLUE, 2);

        assertThat(firstBlackTile, is(not(firstBlueTile)));
    }

    @Test
    public void ensureTilesAreNotEqualWhenOnlyTheirNumbersDiffer() throws Exception {
        Tile firstBlackTile = new Tile(TileColour.BLACK, 2);
        Tile secondBlackTile = new Tile(TileColour.BLACK, 3);

        assertThat(firstBlackTile, is(not(secondBlackTile)));
    }

    @Test
    public void ensureTwoEqualTilesInASetReduceToJustOneTile() throws Exception {
        Tile firstBlackOneTile = new Tile(TileColour.BLACK, 1);
        Tile secondBlackOneTile = new Tile(TileColour.BLACK, 1);

        Set<Tile> tileSet = new HashSet<>();
        tileSet.add(firstBlackOneTile);
        tileSet.add(secondBlackOneTile);

        assertThat(tileSet.size(), is(1));
    }

    @Test
    public void ensureTwoDifferentTilesInASetGivesACountOfTwo() throws Exception {
        Tile firstBlackOneTile = new Tile(TileColour.BLACK, 1);
        Tile secondBlackOneTile = new Tile(TileColour.BLUE, 1);

        Set<Tile> tileSet = new HashSet<>();
        tileSet.add(firstBlackOneTile);
        tileSet.add(secondBlackOneTile);

        assertThat(tileSet.size(), is(2));
    }

    @Test
    public void ensureTileWithNumber0IsAWildcard() throws Exception {
        Tile blackWildcardTile = new Tile(TileColour.BLACK, 0);

        assertThat(blackWildcardTile.isWildcard(), is(true));
    }

    @Test
    public void ensureTileWithNumberGreaterThan0IsNotAWildcard() throws Exception {
        Tile blackNonWildcardTile = new Tile(TileColour.BLACK, 1);
        Tile blackHigherNumberNonWildcardTile = new Tile(TileColour.BLACK, 13);

        assertThat(blackNonWildcardTile.isWildcard(), is(false));
        assertThat(blackHigherNumberNonWildcardTile.isWildcard(), is(false));
    }
}
