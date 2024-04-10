import javax.swing.*;
import java.awt.*;

public class Card {

    // Attributes

    private final Coin scoin;
    private final Coin[] rcoins;
    private Player owner;
    private final int cardscore ;
    private final boolean is_prize ;
    private boolean is_bought = false;
    private boolean is_reserved = false;
    private ImageIcon image;

    // Constructor

    public Card( Coin[] rcoins , int cardscore , ImageIcon image) {

        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = true;
        this.scoin = null;

    }
    public Card( Coin scoin , Coin[] rcoins , int cardscore , ImageIcon image) {

        this.scoin = scoin;
        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = false;

    }

    // Methods

    private boolean Buy( Coin[] playercoins , Player player ,SlotMachine slots) {

        if( CheckValidCoins( playercoins , false , slots) && player.getPurchasedcards() < 16) {

                CheckValidCoins( playercoins , true , slots);
                player.setPurchasedcards( player.getPurchasedcards() + 1);
                player.setCard( this );
                setPlayer( player );

                return true;
        } else {

            return false;
        }
    }

    private boolean CheckValidCoins ( Coin[] pcoins , boolean remove , SlotMachine slots) {


        for (int i = 0 ; i < rcoins.length ; i++) {
            boolean found = false;

            for ( Coin coin : pcoins) {

                if ( coin.getColor().equals( rcoins[i].getColor() ) || coin.getIs_gold() ) {

                    // Remove or Put Coin In Slot Mchine
                    if( remove ) {

                        SlotMachine slot = slots.Findslotnachine(coin.getColor());
                        if( slot.getnumcoins() < 4){
                            slots.setnumcoins( slot.getnumcoins() + 1 );
                            coin = null;
                        } else {
                            coin = null;
                        }
                    }

                    found = true;
                    break;
                }
            }
            if (!found ){
                return false;
            }
        }
        return true;
    }

    private int Checkvalidscore( int cardscore ) {
        return  cardscore >= 0 ? cardscore : 0;
    }

    private ImageIcon Checkvalidimage ( ImageIcon image) {
        if ( image != null) {
            return image;
        }else {
            return null;
        }
    }
    // Setter

    private void setPlayer ( Player owner) {

        if( !is_bought && owner != this.owner) {
            this.owner = owner;
            is_bought = true;
        }
        else {
            // cant return flase
        }
    }

    private void setImage ( ImageIcon image) {

        if ( image == null) {
            this.image = image;
        }
    }



    // Getter

    public Coin getScoin() {
        return  this.scoin != null ? this.scoin : null;
    }

    public Coin[] getRcoins() {
        return this.rcoins != null ? this.rcoins : null;
    }

    public int getCardscore() {
        return cardscore;
    }

    public ImageIcon getImage() {
        return this.image != null ? this.image : null;
    }
}
