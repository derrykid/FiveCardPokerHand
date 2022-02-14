import card.*;
import poker.*;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    private static final int NUM_EXPERIMENTS = 1000000;

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Five cards:");

        System.out.println(System.currentTimeMillis());

    }

    private static void runExp1() {
        final long startTime = System.currentTimeMillis();
        final int[] frequencyTable = new int[ClassificationRank.values().length];


        IntStream.range(0, NUM_EXPERIMENTS).mapToObj(i -> new FiveCardPokerHand.Builder()).forEach(builder -> {
            final Deck deck = Deck.newShuffledSingleDeck();
            try {
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            final FiveCardPokerHand hand = builder.build();
            final ClassificationRank classificationRank = hand.getHandAnalyzer().getClassification().getClassificationRank();
            frequencyTable[classificationRank.ordinal()]++;
        });

        System.out.println("Finished experiment with " + NUM_EXPERIMENTS + " iterations in " + (System.currentTimeMillis() - startTime) + " milliseconds");
        System.out.println(Arrays.toString(frequencyTable) + "\n");
    }

    private static void runExp2() {
        final long startTime = System.currentTimeMillis();
        final int[] frequencyTable = new int[3];
        // implement Comparator interface, it's a Comparator<T>
        final PokerHandComparator comparator = new PokerHandComparator();

        IntStream.range(0, NUM_EXPERIMENTS).forEach(i -> {
            final Deck deck = Deck.newShuffledSingleDeck();

            FiveCardPokerHand.Builder builder = new FiveCardPokerHand.Builder();
            try {
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
                builder.addCard(deck.deal());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            final FiveCardPokerHand hand = builder.build();

            FiveCardPokerHand.Builder builder2 = new FiveCardPokerHand.Builder();
            try {
                builder2.addCard(deck.deal());
                builder2.addCard(deck.deal());
                builder2.addCard(deck.deal());
                builder2.addCard(deck.deal());
                builder2.addCard(deck.deal());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            final FiveCardPokerHand hand2 = builder2.build();

            final int comparison = comparator.compare(hand, hand2);

            if (comparison < 0) {
                frequencyTable[0]++;
            } else if (comparison == 0) {
                frequencyTable[1]++;
            } else if (comparison > 0) {
                frequencyTable[2]++;
            } else {
                throw new RuntimeException("WTF");
            }

        });

        System.out.println("Finished experiment with " + NUM_EXPERIMENTS + " iterations in " + (System.currentTimeMillis() - startTime) + " milliseconds");
        System.out.println(Arrays.toString(frequencyTable));
    }
}
