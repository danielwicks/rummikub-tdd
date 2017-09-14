import enums.TileColour;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TileDealer {

    public static final int LOWEST_TILE = 1;
    public static final int HIGHEST_TILE = 13;

    public List<Tile> deal() {

        List<Tile> tiles;

        tiles = Arrays.stream(TileColour.values())
                .flatMap(tileColour -> createAllTilesForColour(tileColour).stream())
                .collect(toList());

        addWildcards(tiles);

        return tiles;
    }

    private void addWildcards(List<Tile> tiles) {
        tiles.add(createTile(TileColour.BLACK, 0));
        tiles.add(createTile(TileColour.YELLOW, 0));
    }

    private List<Tile> createAllTilesForColour(TileColour tileColour) {
        return Stream
                .concat(getTileStreamForAllNumbersWithColour(tileColour), getTileStreamForAllNumbersWithColour(tileColour))
                .collect(toList());
    }

    private Stream<Tile> getTileStreamForAllNumbersWithColour(TileColour tileColour) {
        return Stream.iterate(LOWEST_TILE, number -> number + 1)
                .limit(HIGHEST_TILE)
                .map(number -> createTile(tileColour, number));
    }

    private Tile createTile(TileColour tileColour, int number) {
        return new Tile(tileColour, number);
    }
}
