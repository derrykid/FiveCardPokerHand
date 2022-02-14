package poker;

import card.*;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class FiveCardPokerHand implements Hand  {

    private final FiveCardHandAnalyzer handAnalyzer;

    private static final int POKER_HAND_SIZE = 5;

    public FiveCardPokerHand(Builder builder) {
        this.handAnalyzer = new FiveCardHandAnalyzer(builder.cards);

    }

    @Override
    public HandAnalyzer getHandAnalyzer() {
        return this.handAnalyzer;
    }

    // java builder pattern
    public static class Builder {

        private SortedSet<Card> cards;

        public Builder() {
            this.cards = new TreeSet<>();
        }

        public Builder addCard(Optional<Card> card) throws IllegalAccessException {
            this.cards.add(card.orElseThrow(IllegalAccessException::new));
            return this;
        }

        public FiveCardPokerHand build() {
            if (this.cards.size() != POKER_HAND_SIZE) {
                throw new RuntimeException("Invalid hand size for hand " + this.cards.toString());
            }
            return new FiveCardPokerHand(this);
        }

    }

}
