import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Card {

    // Attributes

    private final Coin scoin;
    private final Coin[] rcoins;
    private Player owner;
    private final int cardscore ;
    private boolean is_prize  = false;
    private boolean is_bought = false;
    private boolean is_reserved = false;
    private int id ;

    private ImageIcon image;

    private static int goldcoins = 5;

    // Constructor

    public Card( Coin[] rcoins , int cardscore , ImageIcon image , int id) {

        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = true;
        this.scoin = null;
        this.id = id;
    }
    public Card( Coin scoin , Coin[] rcoins , int cardscore , ImageIcon image , int id) {

        this.scoin = scoin;
        this.rcoins = rcoins;
        this.cardscore = Checkvalidscore( cardscore );
        this.image = Checkvalidimage( image );
        this.is_prize = false;
        this.id = id;

    }

    // Methods

     synchronized void Reserve ( Player player) {

        if ( player.getReservedcard() < 3 ) {

            setPlayer( player );
            this.is_reserved = true;

            // Add Gold Coin
            if ( getGoldcoins() > 0 ){
                player.setScoins( Coin.Coingenerator( "gold" , false , null));
                setgoldnumber( goldcoins - 1);
            }
            player.setReservedcard( player.getReservedcard() +1);

        } else {
            String msg = " You Can't Reserve More Card!";
            JOptionPane.showMessageDialog(null,msg, "Massage",JOptionPane.PLAIN_MESSAGE);

        }
    }

     synchronized void Buy( Player player ) {

        if( CheckValidCoins( player.getBcoins() , player.getScoin(),false ) && player.getPurchasedcards() < 16) {

                CheckValidCoins( player.getBcoins(), player.getScoin() , true );
                player.setCard( this );
                player.setPurchasedcards( player.getPurchasedcards() + 1);
                setPlayer( player );
                this.is_bought = true;

        } else {

            String msg = " You Don't Have Enough Coins!";
            JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }


    private boolean CheckValidCoins ( Coin[] bcoins , Coin[] scoins, boolean remove ) {

        for (int i = 0 ; i < rcoins.length ; i++) {
            boolean found = false;

            // Search In Special Coins
            if ( scoins[0] != null) {
                for ( Coin coin : scoins){
                    if ( coin != null && coin.getColor().equals( rcoins[i].getColor() ) ) {

                        found = true;
                        break;
                        // It's Repetitive Coin
                    }
                    else  {
                        if ( coin != null && coin.getColor().equals( "gold" )) {
                            if ( remove ) {
                                coin = null;
                            }
                            found = true;
                        }
                    }
                }

            } else { // Search In Basic Coins
                if (!is_prize){
                    for ( Coin coin : bcoins) {

                        if ( coin != null && coin.getColor().equals( rcoins[i].getColor() ) ) {

                            // Remove or Put Coin In Slot Mchine
                            if( remove ) {

                                SlotMachine slot = SlotMachine.Findslotnachine( coin.getColor());
                                // Show Error If There Isn't Any Slot Machine
                                if (slot == null){
                                    String msg = " There Is Not Slot Machine !";
                                    JOptionPane.showMessageDialog(null ,msg,"Error",JOptionPane.PLAIN_MESSAGE);
                                }

                                if( slot != null && slot.getnumcoins() < 4) {
                                    slot.setnumcoins( slot.getnumcoins() + 1 );
                                    coin = null;
                                } else {
                                    coin = null;
                                }
                            }

                            found = true;
                            break;
                        }
                    }
                }

            }

            // Return False If Not Found any Coin
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

    private static void setgoldnumber( int number) {

        if ( number >= 0 && number <= 5) {
            goldcoins = number;
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
    public int getId() {
        return this.id;
    }
    public boolean getisreserve(){
        return this.is_reserved;
    }

    public static int getGoldcoins() {
        return goldcoins;
    }
}
