import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card[] Cards = new Card[16];
    private Coin[] bcoins = new Coin[10];

    private List<Coin> scoins = new ArrayList<>();
    private int score = 0;
    private int Purchasedcards = 0;

    // constructor
    public Player( ){
        this.score = 0 ;
        this.Purchasedcards = 0;
    }

    // Methods

    void setbcoins( Coin coin) {

        this.bcoins[ Coincounter() ] = coin;
    }
    int Coincounter ( ){

        int count = 0;
        for ( Coin coin : this.bcoins) {
            if ( coin != null) {
                count++;
            }
        }
        return count;
    }
    // Setter

    private void setCoins( Coin coin , int i) {

        if ( coin != null && i >= 0 && i <= 10) {
            this.bcoins[i] = coin;
        }
    }

    private void setScore(int s) {
        if( s >= 0 && s <= 15) {
            this.score = s;
        }
    }

    void setPurchasedcards( int c) {
        if ( c >=0 && c <= 16) {
            this.Purchasedcards = c;
        }
    }

    void setCard (Card card) {
        if ( getPurchasedcards() > 0 && getPurchasedcards() < 17) {
            this.Cards[ getPurchasedcards() -1 ] = card;
        }

    }
    // Getter

    public int getScore() {
        return this.score;
    }

    public int getPurchasedcards() {
        return this.Purchasedcards;
    }
}
