import enums.TileColour;

import java.util.Objects;

public class Tile {

    private final TileColour tileColour;
    private final int number;

    public Tile(TileColour tileColour, int number) {
        this.tileColour = tileColour;
        this.number = number;

        validate();
    }

    private void validate() {
        if (tileColour == null || number < 0 || number > 13){
            throw new IllegalArgumentException();
        }
    }

    public TileColour getColour() {
        return tileColour;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return number == tile.number &&
                tileColour == tile.tileColour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileColour, number);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tileColour=" + tileColour +
                ", number=" + number +
                '}';
    }

    public boolean isWildcard() {
        return number == 0;
    }
}
