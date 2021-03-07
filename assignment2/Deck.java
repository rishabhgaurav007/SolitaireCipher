package assignment2;

import java.util.Random;

public class Deck {
    public static String[] suitsInOrder = {
        "clubs",
        "diamonds",
        "hearts",
        "spades"
    };
    public static Random gen = new Random();

    public int numOfCards; // contains the total number of cards in the deck
    public Card head; // contains a pointer to the card on the top of the deck
    
    /* 
     * TODO: Initializes a Deck object using the inputs provided
     */
    public Deck(int numOfCardsPerSuit, int numOfSuits) {
        /**** ADD CODE HERE ****/
        // please add try and catch
        for(int i=0;i<numOfSuits;i++){
            for(int j=1;j<=numOfCardsPerSuit;j++){
                this.addCard(this.new PlayingCard(this.suitsInOrder[i],j));
            }
        }
        //adding RJ BJ at last
        this.addCard(this.new Joker("red"));
        this.addCard(this.new Joker("black"));
        //printing the deck
        // Deck.Card temphead =  deck.head;
        // do
        // {
        //     System.out.println(temphead);
        //     temphead = temphead.next;
        // }while(temphead!=deck.head);
    }

    /* 
     * TODO: Implements a copy constructor for Deck using Card.getCopy().
     * This method runs in O(n), where n is the number of cards in d.
     */
    public Deck(Deck d) { 
        /**** ADD CODE HERE ****/
        Deck.Card temphead =  d.head;
        do
        {
              Deck.Card c1= temphead.getCopy();
              this.addCard(c1);
              temphead = temphead.next;
        }while(temphead!=d.head);
    }

    /*
     * For testing purposes we need a default constructor.
     */
    public Deck() {}

    /* 
     * TODO: Adds the specified card at the bottom of the deck. This 
     * method runs in $O(1)$. 
     */
    public void addCard(Card c) {
        /**** ADD CODE HERE ****/
        // null
        numOfCards=numOfCards+1;
        if(head==null){
            head=c;
            head.next = head;
            head.prev = head;
            return;
        }
        Card last = head.prev;
        last.next = c;
        c.prev = last;
        c.next = head;
        head.prev = c;
        
    }

    /*
     * TODO: Shuffles the deck using the algorithm described in the pdf. 
     * This method runs in O(n) and uses O(n) space, where n is the total 
     * number of cards in the deck.
     */
    public void shuffle() {
        /**** ADD CODE HERE 
        for i from n-1 to 1 do
            j <-- random integer such that 0 <= j <= i
            swap a[j] and a[i]
        ****/
        if(this.numOfCards==0)
            return;
        Card[] arrDeck = new Card[this.numOfCards];
        Card temphead = this.head;
        int assignposition=0;
        do{
            arrDeck[assignposition++]=temphead;
            temphead=temphead.next;
        }while(temphead!=head);

        for(int i=this.numOfCards-1;i>=1;i--){
            int j = gen.nextInt(i+1);
            Card temp = arrDeck[i];
            arrDeck[i] = arrDeck[j];
            arrDeck[j] = temp;
        }
        int len = this.numOfCards;
        for(int i=0;i<len;i++){
            arrDeck[i].next = arrDeck[(i+1)%len];
            arrDeck[i].prev = arrDeck[(i-1+len)%len];
        }
        this.head = arrDeck[0];
    }

    /*
     * TODO: Returns a reference to the joker with the specified color in 
     * the deck. This method runs in O(n), where n is the total number of 
     * cards in the deck. 
     */
    public Joker locateJoker(String color) {
        /**** ADD CODE HERE ****/
        Card temphead = head;
        do
        {
            int valueOfCard = temphead.getValue();
            if(valueOfCard==numOfCards || valueOfCard==numOfCards-1){
                if(color.equals(( (Deck.Joker)temphead).getColor()) ){
                    return  (Deck.Joker)temphead;
                }
            }
            temphead = temphead.next;
        }while(temphead!=head);
        return (Deck.Joker)temphead;
    }

    /*
     * TODO: Moved the specified Card, p positions down the deck. You can 
     * assume that the input Card does belong to the deck (hence the deck is
     * not empty). This method runs in O(p).
     */
    public void moveCard(Card c, int p) {
        /**** ADD CODE HERE ****/
        Card temphead = c;
        int valueToLook = p;
        // Card temphead = c;
        while(valueToLook!=0){
            temphead = temphead.next;
            valueToLook--;
        }
        Card prev = c.prev;
        prev.next = c.next;
        c.next.prev = prev;
        c.next = temphead.next;
        temphead.next.prev = c;
        temphead.next = c;
        c.prev = temphead;
    }

    /*
     * TODO: Performs a triple cut on the deck using the two input cards. You 
     * can assume that the input cards belong to the deck and the first one is 
     * nearest to the top of the deck. This method runs in O(1)
     */
    public void tripleCut(Card firstCard, Card secondCard) {
        /**** ADD CODE HERE ****/
        // Card p = firstCard.prev;
        // Card q = this.head;

        // Card r = secondCard.next;
        // Card s = this.head.prev;

        // secondCard.next = q;
        // Card temp = q.prev;
        // q.prev = secondCard; 
               
        //






        Card a1 = this.head;
        Card a2 = firstCard;
        Card b1 = secondCard;
        Card prev = b1.prev;
        Card b2 = a1.prev;
        b2.next = a2.next;
        a2.next.prev = b2;
        prev.next = a1;
        a1.prev = prev;
    }

    /*
     * TODO: Performs a count cut on the deck. Note that if the value of the 
     * bottom card is equal to a multiple of the number of cards in the deck, 
     * then the method should not do anything. This method runs in O(n).
     */
    public void countCut() {
        /**** ADD CODEHERE ****/
    }

    /*
     * TODO: Returns the card that can be found by looking at the value of the 
     * card on the top of the deck, and counting down that many cards. If the 
     * card found is a Joker, then the method returns null, otherwise it returns
     * the Card found. This method runs in O(n).
     */
    public Card lookUpCard() {
        /**** ADD CODE HERE ****/
        Card temphead = head;
        int valueToLook = temphead.getValue();
        while(valueToLook!=0){
            temphead = temphead.next;
            valueToLook--;
        }
        // checking for joker
        int valueOfCard = temphead.getValue();
        if(valueOfCard==numOfCards || valueOfCard==numOfCards-1){
            return null;
        }
        return temphead;
    }

    /*
     * TODO: Uses the Solitaire algorithm to generate one value for the keystream 
     * using this deck. This method runs in O(n).
     */
    public int generateNextKeystreamValue() {
        /**** ADD CODE HERE ****/
        return 0;
    }


    public abstract class Card {
        public Card next;
        public Card prev;

        public abstract Card getCopy();
        public abstract int getValue();

    }

    public class PlayingCard extends Card {
        public String suit;
        public int rank;

        public PlayingCard(String s, int r) {
            this.suit = s.toLowerCase();
            this.rank = r;
        }

        public String toString() {
            String info = "";
            if (this.rank == 1) {
                //info += "Ace";
                info += "A";
            } else if (this.rank > 10) {
                String[] cards = {
                    "Jack",
                    "Queen",
                    "King"
                };
                //info += cards[this.rank - 11];
                info += cards[this.rank - 11].charAt(0);
            } else {
                info += this.rank;
            }
            //info += " of " + this.suit;
            info = (info + this.suit.charAt(0)).toUpperCase();
            return info;
        }

        public PlayingCard getCopy() {
            return new PlayingCard(this.suit, this.rank);
        }
        // extra
        public String getSuit(){
            return this.suit;
        }

        public int getValue() {
            int i;
            for (i = 0; i < suitsInOrder.length; i++) {
                if (this.suit.equals(suitsInOrder[i]))
                    break;
            }

            return this.rank + 13 * i;
        }

    }

    public class Joker extends Card {
        public String redOrBlack;

        public Joker(String c) {
            if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black"))
                throw new IllegalArgumentException("Jokers can only be red or black");

            this.redOrBlack = c.toLowerCase();
        }

        public String toString() {
            //return this.redOrBlack + " Joker";
            return (this.redOrBlack.charAt(0) + "J").toUpperCase();
        }

        public Joker getCopy() {
            return new Joker(this.redOrBlack);
        }

        public int getValue() {
            return numOfCards - 1;
        }

        public String getColor() {
            return this.redOrBlack;
        }
    }

}
