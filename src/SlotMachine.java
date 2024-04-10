import javax.swing.*;

public class SlotMachine {

    private final String type;
    private int numcoins = 4;
    private boolean isfull ;
    private final ImageIcon image;

    // Constructor
    public SlotMachine ( String type , ImageIcon image) {
        this.type = settype( type );
        this.image = image;

    }

    // Methods

     SlotMachine Findslotnachine ( String type) {

        for ( SlotMachine slot : Main.slots) {
            if ( slot.getType().equals( type )){
                return slot;
            }
        }
        return null;
    }

    private void Receiveonecoin ( Player player) {
        if( isfull ) {
            if( player.Coincounter() < 10) {
                player.setbcoins( Coingenerator( this.type, false,null));
            }
        }
    }

    Coin Coingenerator ( String color , boolean is_special , ImageIcon image ) {

        return  new Coin( color , is_special , image );
    }
    // Setter
    private String settype(String type) {
        if ( type.equals("white") || type.equals("black") || type.equals("red") || type.equals("blue") || type.equals("green") ){
            return type;
        } else {
            return null;
        }
    }

     void setnumcoins ( int count) {
        if( count > 0 && count < 5) {
            this.numcoins = count;
        } else {
            this.numcoins = 0;
        }
    }
    // Getter
    public String getType() {
        return type;
    }

    public boolean getisfull() {
        return this.isfull;
    }

    public int getnumcoins() {
        return this.numcoins;
    }
}
