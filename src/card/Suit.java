package card;

public enum Suit {
    SPADE(4),
    HEART(3),
    DIAMOND(2),
    CLUB(1);

    private final int suitValue;

    Suit(final int suitValue) {
        this.suitValue = suitValue;
    }

    public int getSuitValue() {
        return this.suitValue;
    }

}
