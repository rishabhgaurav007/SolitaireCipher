package assignment2;

public class SolitaireCipher {
    public Deck key;

    public SolitaireCipher(Deck key) {
        this.key = new Deck(key); // deep copy of the deck
    }

    /* 
     * TODO: Generates a keystream of the given size
     */
    public int[] getKeystream(int size) {
        /**** ADD CODE HERE ****/
        int[] keystream = new int[size];
        
        return null;
    }

    /* 
     * TODO: Encodes the input message using the algorithm described in the pdf.
     */
    public String encode(String msg) {
        /**** ADD CODE HERE ****/
        return null;
    }

    /* 
     * TODO: Decodes the input message using the algorithm described in the pdf.
     */
    public String decode(String msg) {
        /**** ADD CODE HERE ****/
        return null;
    }

}