import enums.TileColour;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static enums.TileColour.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TileDealerTest {

    public static final int NUMBER_OF_NORMAL_TILES = 104;
    public static final int NUMBER_OF_WILDCARDS = 2;

    private TileDealer tileDealer;

    @Before
    public void setUp() throws Exception {
        tileDealer = new TileDealer();
    }

    @Test
    public void tileDealerDealsTheCorrectNumberOfTiles() throws Exception {
        List<Tile> deal = tileDealer.deal();

        assertThat(deal.size(), is(NUMBER_OF_NORMAL_TILES + NUMBER_OF_WILDCARDS));
    }

    @Test
    public void tileDealerDealsYellowAndBlackWildcards() throws Exception {
        List<Tile> deal = tileDealer.deal();

        long numberOfWildcardNumberTiles = deal.stream()
                .map(Tile::getNumber)
                .filter(number -> number == 0)
                .count();

        Tile blackWildcard = new Tile(BLACK, 0);
        Tile yellowWildcard = new Tile(YELLOW, 0);

        assertThat(deal, hasItems(blackWildcard, yellowWildcard));
        assertThat(numberOfWildcardNumberTiles, is(2L));
    }

    @Test
    public void tileDealerDealsOnlyFourColours() throws Exception {
        List<Tile> deal = tileDealer.deal();
        Set<TileColour> setOfColours = deal.stream()
                .map(Tile::getColour)
                .collect(toSet());

        assertThat(setOfColours.size(), is(4));
        assertThat(setOfColours, hasItems(BLACK, BLACK, YELLOW, RED));
    }

    @Test
    public void tileDealerDealsTilesWithThirteenUniqueNumbersPlusOneWildcardNumber() throws Exception {
        List<Tile> deal = tileDealer.deal();

        long countOfDistinctNumbers = deal.stream()
                .map(Tile::getNumber)
                .distinct()
                .count();

        assertThat(countOfDistinctNumbers, is(13L + 1));
    }

    @Test
    public void tileDealerDeals8OfEachNumber() throws Exception {
        List<Tile> deal = tileDealer.deal();

        Map<Integer, List<Tile>> mapOfNumberToTiles = deal.stream()
                .filter(tile -> tile.getNumber() > 0)
                .collect(groupingBy(Tile::getNumber));

        long countOfEachNumberWithout8Tiles = mapOfNumberToTiles.entrySet()
                .stream()
                .filter(integerListEntry -> integerListEntry.getValue().size() != 8)
                .count();

        assertThat(countOfEachNumberWithout8Tiles, is(0L));
    }
}