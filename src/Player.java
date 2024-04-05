import java.util.List;

public class Player {

    private Card[] Cards = new Card[16];
    private Coin[] coins = new Coin[10];

    private int score = 0;
    private int Purchasedcards = 0;

    // constructor
    public Player( ){
        this.score = 0 ;
        this.Purchasedcards = 0;
    }

    // Methods

    int Coincounter ( )
    // Setter

    private void setCoins( Coin coin , int i) {

        if ( coin != null && i >= 0 && i <= 10) {
            this.coins[i] = coin;
        }
    }

    private void setScore(int s) {
        if( s >=0 && s <= 15) {
            this.score = s;
        }
    }

    void setPurchasedcards( int c) {
        if ( c>=0 ) {
            this.Purchasedcards = c;
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
