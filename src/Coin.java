import java.awt.*;

public class Coin {

    private final String color ;
    private final boolean is_special;
    private final boolean is_gold;

    private Player owner ;

    private final Image image;

    // Constructor
    public Coin ( String color , boolean is_special , boolean is_gold , Image image ) {

        this.color = CheckValidColor( color );
        this.is_special = is_special;
        this.is_gold = is_gold;
        this.image = CheckValidImage( image );
    }

    // Methods


    private Image CheckValidImage ( Image image ) {
            return image != null ? image : null;
    }

    private String CheckValidColor ( String color) {

        if( color == "black" || color == "blue" || color == "green" || color == "red" || color == "white") {
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
}