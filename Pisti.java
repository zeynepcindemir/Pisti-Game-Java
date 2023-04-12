
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Pisti {
    public static int FLOOR = 0;
    public static int PLAYER = 1;
    public static int COMPUTER = 2;
    static ArrayList<String> cardStock = new ArrayList<>(52);   //deck of cards
    static ArrayList<String> computerCards = new ArrayList<>(); //cards that are in computer's hand
    static ArrayList<String> computerStockCards = new ArrayList<>();    //cards that are gained by computer
    static ArrayList<String> playerCards = new ArrayList<>();   //cards that are in player's hand
    static ArrayList<String> playerStockCards = new ArrayList<>();  //cards that are gained by player
    static ArrayList<String> onFloorCards = new ArrayList<>();  //cards that are on the floor
    static ArrayList<String> playedCards = new ArrayList<>();   //cards that are played and known by the computer
    static String[] first3 = new String[3]; //first three close cards on the floor (3 out of 4)
    static boolean flag = false;
    static boolean flag2 = false;
    static int beginningCards = 4;  //card number that are dealt to players
    static int computerScore = 0;
    static int playerScore = 0;
    static int pistiPoint = 10;
    static int rounds = 6;  //number of rounds
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to \"Pisti\" Game");
        System.out.println("<------------------------>");
        createCardStock();
        dealCard(FLOOR);
        writeToOpeningFile("acilis.txt");
        for (int i = 1; i <= rounds; i++) {
            dealCard(PLAYER);
            dealCard(COMPUTER);
            writeToTourFile("tur" + i + ".txt");
            System.out.println("Round " + i + "\n<----->");
            for (int j = 0; j < beginningCards; j++) {
                playCard(1);
                playCard(2);
                //showCurrentLists();   for testing purposes
            }

        }
        gameFinish();
    }

    //method to write to the opening file
    public static void writeToOpeningFile(String name) {
        try {
            FileWriter fw = new FileWriter(name, true);
            StringBuilder s = new StringBuilder();
            for (String str : onFloorCards) {
                if (onFloorCards.indexOf(str) != onFloorCards.size() - 1) {
                    s.append(str).append(", ");
                } else {
                    s.append(str);
                }
            }
            fw.write("The cards on the floor : ");
            fw.write(s.toString());
            fw.write("\nThe top card : ");
            fw.write(onFloorCards.get(onFloorCards.size() - 1));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //helper function of writing method
    public static String writeHelper(ArrayList<String> list) {
        StringBuilder s = new StringBuilder();
        for (String str : list) {
            if (list.indexOf(str) != list.size() - 1) {
                s.append(str).append(", ");
            } else {
                s.append(str);
            }
        }
        return s.toString();
    }

    //method to write to the tour files
    public static void writeToTourFile(String name) {
        try {
            FileWriter fw = new FileWriter(name, true);

            fw.write("Player cards : ");
            fw.write(writeHelper(playerCards));
            fw.write("\nComputer cards : ");
            fw.write(writeHelper(computerCards));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //method to create and shuffle the deck of cards
    private static void createCardStock() {

        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String value : values) {
                cardStock.add(value + " of " + suit);
            }
        }
        Collections.shuffle(cardStock);

    }

    //method to deal cards to each player
    public static void dealCard(int who) throws IOException {

        for (int i = 0; i < beginningCards; i++) {
            if (who == FLOOR) {
                onFloorCards.add(cardStock.get(0));
            } else if (who == COMPUTER) {
                computerCards.add(cardStock.get(0));
            } else if (who == PLAYER) {
                playerCards.add(cardStock.get(0));
            }
            cardStock.remove(0);
        }

        if (!flag2) {   //determine the first three cards in the deck that are closed in the beginning
            first3[0] = onFloorCards.get(0);
            first3[1] = onFloorCards.get(1);
            first3[2] = onFloorCards.get(2);
            flag2 = true;
        }

        if (who == FLOOR) {
            System.out.println("Top of deck card : " + onFloorCards.get(onFloorCards.size() - 1));
        }

        if (who == PLAYER) {
            showPlayerCards(1);
        }

    }

    //method to determine whether two cards have the same values or played card is a Joker
    public static boolean isAMatch(String c1, String c2) {
        if (c1.substring(0, c1.indexOf(" of ")).equals(c2.substring(0, c2.indexOf(" of "))))
            return true;
        else
            return c1.contains("Jack");
    }

    //method to control the game process
    public static void checkGameStatus(String playedCard, int who) {
        String floorCard = "";

        if (onFloorCards.isEmpty()) {
            onFloorCards.add(playedCard);
        } else {
            floorCard = onFloorCards.get(onFloorCards.size() - 1);
            if (isAMatch(playedCard, floorCard)) {
                if (onFloorCards.size() == 1) {     //"pisti" status
                    if (who == PLAYER) {
                        onFloorCards.add(playedCard);
                        playerStockCards.addAll(onFloorCards);
                        playerScore += pistiPoint;

                    } else if (who == COMPUTER) {
                        onFloorCards.add(playedCard);
                        computerStockCards.addAll(onFloorCards);
                        computerScore += pistiPoint;
                    }
                    onFloorCards.clear();
                } else {
                    if (who == COMPUTER) {
                        onFloorCards.add(playedCard);
                        computerStockCards.addAll(onFloorCards);

                    } else {
                        onFloorCards.add(playedCard);
                        playerStockCards.addAll(onFloorCards);
                    }
                    onFloorCards.clear();
                }
            } else {
                onFloorCards.add(playedCard);
            }

        }

    }

    //method to determine the played card by the player
    public static void playCard(int who) {
        int chosenCardIndex = 0;
        String chosenCard = "";

        if (who == PLAYER) {

            if (!playerCards.isEmpty()) {   //getting user input
                System.out.println("Please enter a number to play a card : ");
                chosenCardIndex = sc.nextInt();

                while (playerCards.size() < chosenCardIndex || chosenCardIndex < 1) {
                    System.out.println("This number is not valid. Please enter a valid number : ");
                    chosenCardIndex = sc.nextInt();
                }
            }

            chosenCard = playerCards.get(chosenCardIndex - 1);

            System.out.println();

            checkGameStatus(chosenCard, PLAYER);
            System.out.println("You played " + chosenCard);
            playerCards.remove(chosenCard);

            System.out.print("Your cards : ");
            for (String c : playerCards) {
                if (!c.equals(playerCards.get(playerCards.size() - 1))) {
                    System.out.print(c + ", ");
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        } else if (who == COMPUTER) {
            updatePlayedCards(); // update the playedCards list.
            //my first priority is checking if I have the same value card.
            //my second priority is checking if I have a Joker.
            //my third priority is "the card that I know to be the most common among the cards I have seen so far is the best option for me."
            String bestCard = "";
            if (computerCards.size() == 1) {
                bestCard = computerCards.get(0);
            } else if (!onFloorCards.isEmpty()) {
                //check if there is a matching card (by value) in the computer's hand.
                for (String computerCard : computerCards) {
                    if (computerCard.substring(0, computerCard.indexOf(" of ")).equals(onFloorCards.get(onFloorCards.size() - 1).substring(0, onFloorCards.get(onFloorCards.size() - 1).indexOf(" of ")))) {
                        bestCard = computerCard;
                        break;
                    }
                }
                //if there is no matching card, check if there is a Joker.
                for (String computerCard : computerCards) {
                    if (computerCard.contains("Jack"))
                        bestCard = computerCard;
                }

                //if there is no Joker, select the card that has the highest count (among the cards in playedCards list).
                if (bestCard.equals("")) {
                    int max = 0;
                    for (String computerCard : computerCards) {
                        int count = 0;
                        for (String playedCard : playedCards) {
                            if (computerCard.substring(0, computerCard.indexOf(" of ")).equals(playedCard.substring(0, playedCard.indexOf(" of ")))) {
                                count++;
                            }
                        }
                        if (count > max) {
                            max = count;
                            bestCard = computerCard;
                        }
                    }
                }
            } else {

                //if there are more than one cards in the hand and there is no card on the floor, select the card with the lowest count.
                int min = 0;
                for (String computerCard : computerCards) {
                    int count = 0;
                    for (String playedCard : playedCards) {
                        if (computerCard.substring(0, computerCard.indexOf(" of ")).equals(playedCard.substring(0, playedCard.indexOf(" of ")))) {
                            count++;
                        }
                    }
                    if (min < count) {
                        min = count;
                        bestCard = computerCard;
                    }
                }
            }

            chosenCard = bestCard;

            checkGameStatus(chosenCard, COMPUTER);
            System.out.println("Computer played " + chosenCard + "\n");
            computerCards.remove(chosenCard);
        }

    }

    //helper functions to calculate points method
    static int calcHelper(ArrayList<String> stock, int score) {
        for (String c : stock) {
            if (c.contains("Ace")) {
                score += 1;
            } else if (c.contains("Jack")) {
                score += 1;
            } else if (c.equals("10 of Diamonds")) {
                score += 3;
            } else if (c.equals("2 of Clubs")) {
                score += 2;
            }
        }

        return score;
    }

    //method to calculate total points of the cards of each player
    static void calculatePoints(int who) {
        if (who == PLAYER) {
            playerScore += calcHelper(playerStockCards, playerScore);
        } else if (who == COMPUTER) {
            computerScore += calcHelper(computerStockCards, computerScore);
        }
    }

    //method to control and update playedCards list
    public static void updatePlayedCards() {

        //computer knows the computerStockCards, computerCards, and onFloorCards lists
        for (String c : computerStockCards) {
            if (!playedCards.contains(c)) {
                playedCards.add(c);
            }
        }
        for (String c : computerCards) {
            if (!playedCards.contains(c)) {
                playedCards.add(c);
            }
        }
        for (String c : onFloorCards) {
            if (!playedCards.contains(c)) {
                playedCards.add(c);
            }
        }

        //if the first three cards of the deck that are closed are in the computerStockCards list,
        //this means that we can add all playerStockCards list to the playedCards list.
        //else if the first three cards of the deck that are closed are in the playerStockCards list,
        //this means that we cannot add these three cards to the playedCards list, so we remove them from the list.
        if (!flag) {
            if (computerStockCards.contains(first3[0]) && computerStockCards.contains(first3[1]) && computerStockCards.contains(first3[2])) {
                for (String c : playerStockCards) {
                    if (!playedCards.contains(c)) {
                        playedCards.add(c);
                    }
                }
                flag = true;
            } else {

                for (String c : playerStockCards) {
                    if (!playedCards.contains(c)) {
                        playedCards.add(c);
                    }
                }
                playedCards.remove(first3[0]);
                playedCards.remove(first3[1]);
                playedCards.remove(first3[2]);
            }
        }

    }

    //method to print each player's cards
    public static void showPlayerCards(int who) {
        if (who == PLAYER) {
            System.out.println("Your cards: " + playerCards);
        } else if (who == COMPUTER) {
            System.out.println("Computer cards: " + computerCards);
        }
    }

    //method to print current card list status for testing purposes
    public static void showCurrentLists() {
        System.out.println(Arrays.toString(first3));
        System.out.println(playerCards);
        System.out.println(playerStockCards);
        System.out.println(computerCards);
        System.out.println(computerStockCards);
        System.out.println(playedCards);
    }

    //method to finish the game and show results
    public static void gameFinish() throws IOException {

        calculatePoints(1);
        calculatePoints(2);
        playerScore += (playerStockCards.size() > computerStockCards.size()) ? 3 : 0;
        computerScore += (computerStockCards.size() > playerStockCards.size()) ? 3 : 0;

        System.out.println("Game is finished!!!" + "\n<-------------->");
        System.out.println("Results: " + "\n<-------------->");

        System.out.println("Computer Score = " + computerScore);
        System.out.println("Your Score = " + playerScore);

        if (playerScore < computerScore) {
            System.out.println("Computer won!");
        } else if (computerScore < playerScore) {
            System.out.println("You won!");
        } else {
            System.out.println("Scores are equal : there is a tie!");
        }
    }
}
