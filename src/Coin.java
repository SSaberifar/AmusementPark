import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Coin {

    private final String color ;
    private final boolean is_special;
    private final boolean is_gold;
    private Player owner ;

    private final ImageIcon image;

    // Constructor
    public Coin ( String color , boolean is_special , ImageIcon image ) {

        this.color = CheckValidColor( color );
        this.is_special = is_special;
        this.is_gold = CheckIsGold( color );
        this.image = CheckValidImage( image );
    }

    // Methods

    static Coin Coingenerator ( String color , boolean is_special , ImageIcon image ) {

        return  new Coin( color , is_special , image );
    }

    static boolean Removecoin ( String color , List<Coin> scoins) {

        for ( int i = 0 ; i < scoins.size() ; i++) {
            if ( scoins.get( i ).getColor().equals( color )) {
                scoins.remove( i );
                return true;
            }
        }
        return false;
    }
    private boolean CheckIsGold ( String color) {
        return color.equals( "gold" );
    }
    private ImageIcon CheckValidImage ( ImageIcon image ) {
            return image;
    }

    private String CheckValidColor ( String color) {

        if( color.equals("black") || color.equals("blue") || color.equals("green") || color.equals("red") || color.equals("white") ) {
            return color;
        }
        else {
            return null;
        }
    }
    // Setter

    private void setOwner( Player owner ) {

        if( owner != null && owner.getPurchasedcards() < 10) {
            this.owner = owner;
        }
        else {
            this.owner = null;
        }
    }

    // Getter

    public boolean getIs_special (){
        return is_special;
    }

    public boolean getIs_gold (){
        return is_gold;
    }

    public  String getColor () {
        return this.color;
    }
    ImageIcon getImage(){
        return this.image;
    }
}
