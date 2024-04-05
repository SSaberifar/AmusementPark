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
    private Image image;

    // Constructor

    public Card( Coin[] rcoins , int cardscore , Image image) {

        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = true;
        this.scoin = null;

    }
    public Card( Coin scoin , Coin[] rcoins , int cardscore , Image image) {

        this.scoin = scoin;
        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = false;

    }

    // Methods

    private boolean Buy( Coin[] playercoins , Player player) {

        if( CheckValidCoins( playercoins ) ){

        }
    }

    private boolean CheckValidCoins ( Coin[] coins) {

        for( Coin coin : coins) {

        }
    }

    private int Checkvalidscore( int cardscore ) {
        return  cardscore >= 0 ? cardscore : 0;
    }

    private Image Checkvalidimage ( Image image) {
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

    private void setImage ( Image image) {

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

    public Image getImage() {
        return this.image != null ? this.image : null;
    }
}
