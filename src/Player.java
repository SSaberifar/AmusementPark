import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Player {

    private Card[] Cards = new Card[19];

    private Coin[] bcoins = new Coin[10];

    private Coin[] scoins = new Coin[19];
    private int score = 0;
    private int Purchasedcards = 0;
    private int reservedcard = 0;


    // constructor
    public Player( ){
        this.score = 0 ;
        this.Purchasedcards = 0;

        for ( int i =0 ; i < 10 ; i++){
            this.bcoins[i] = null;
        }
        for ( int i =0 ; i < 19 ; i++){
            this.scoins[i] = null;
        }
        for ( int i =0 ; i < 19 ; i++){
            this.Cards[i] = null;
        }

    }

    // Methods

    void setbcoins( Coin coin) {

        if ( Coincounter() < 10) {
            this.bcoins[ Coincounter() ] = coin;
        }
    }
    int Coincounter ( ){

        int count = 0;
        if ( this.bcoins != null) {
            for (Coin coin : this.bcoins) {
                if (coin != null) {
                    count++;
                }
            }
        }
        return count;
    }
    int Scoincounter ( ){

        int count = 0;
        if ( this.scoins != null) {
            for (Coin coin : this.scoins) {
                if (coin != null) {
                    count++;
                }
            }
        }
        return count;
    }

    int Cardcounter ( ){

        int count = 0;
        if ( this.Cards != null) {
            for (Card card : this.Cards) {
                if (card != null) {
                    count++;
                }
            }
        }
        return count;
    }

    Coin FindScoin ( String color) {

        for ( Coin scoin : scoins) {

            if ( scoin.getColor().equals( color )) {
                return scoin;
            }
        }
        return null;
    }

    public Card[] getreservelist() {

        Card[] reservelist= new Card[3];
        int i = 0 ;
        if ( Cardcounter() > 0){
            for (Card reserve : Cards) {
                if ( reserve != null && reserve.getisreserve() ) {
                    reservelist[i] = reserve;
                    i++;
                }
            }
        }
        return reservelist;
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
        if ( c >= 0 && c <= 16) {
            this.Purchasedcards = c;
        }
    }

    void setCard (Card card) {

        if ( getPurchasedcards() > 0 && getPurchasedcards() < 16) {
            this.Cards[ Cardcounter()] = card;
            this.setScore( getScore() + card.getCardscore() );
            setScoins( card.getScoin() );

        } else {
            String msg = " Your Cards Are Full, Can't buy more Card ! ";
            JOptionPane.showMessageDialog( null , msg,"Massage", JOptionPane.PLAIN_MESSAGE );
        }
    }

    void setScoins ( Coin scoin ) {

        if( scoin != null ) {
            this.scoins[ Scoincounter() ] = scoin;
        }
    }

    void setReservedcard ( int count) {
        if (count >=0 && count <= 3) {
            this.reservedcard = count;
        }
    }

    // Getter

    Coin[] getScoin (){

        return this.scoins;
    }
    Coin getScoin ( String color) {
        if ( Scoincounter() > 0 ) {
            return FindScoin( color );
        }
        return null;
    }
    public int getScore() {
        return this.score;
    }

    public int getPurchasedcards() {
        return this.Purchasedcards;
    }

    public Coin[] getBcoins() {
        return this.bcoins;
    }

    public int getReservedcard () {
        return this.reservedcard;
    }

    public Card[] getCards() {
        return this.Cards;
    }

    public int getscoinnumber() {
        int count = 0;
        for ( Coin coin : scoins){
            if (coin != null){
                count++;
            }
        }
        return count;
    }
}
