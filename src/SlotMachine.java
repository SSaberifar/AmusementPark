import javax.swing.*;
import java.awt.*;

public class SlotMachine {

    private final String type;
    private int numcoins = 4;
    private boolean isfull ;
    private final ImageIcon image;
    private int id ;

    // Constructor
    public SlotMachine ( int id ,String type , ImageIcon image) {
        setLable(id);
        this.type = settype( type );
        this.image = image;

    }

    // Methods

     static SlotMachine Findslotnachine ( String type) {

        for ( SlotMachine slot : Main.slots) {
            if ( slot.getType().equals( type )){
                return slot;
            }
        }
        return null;
    }

     int Receivetwocoin ( Player player) {

        if( getnumcoins() == 4 ) {
            if( player.Coincounter() < 9) {
                player.setbcoins( Coingenerator( this.type, false,getimagebycolor(this.type)));
                player.setbcoins( Coingenerator( this.type, false,getimagebycolor(this.type)));

                setnumcoins( getnumcoins()- 2 );

                return 0;
            } else {
                // Show Massage Your Coin Is Full
                String msg = " You cannot receive more coins, Flip a coin and try again ";
                JOptionPane.showMessageDialog(null,msg,"Error", JOptionPane.PLAIN_MESSAGE);
                return 1;
            }
        } else {

            String msg = " Slot machine isn't full, Try later!";
            JOptionPane.showMessageDialog( null,msg, " Massage ",JOptionPane.PLAIN_MESSAGE);
            return 2;
        }
    }

      int Receiveonecoin ( Player player) {

        if ( getnumcoins() > 0) {
            if ( player.Coincounter() < 10) {
                player.setbcoins( Coingenerator( this.type ,false,getimagebycolor(this.type)));

                setnumcoins( getnumcoins() -1);

                String msg= " True";
                JOptionPane.showMessageDialog(null,msg,"title",JOptionPane.PLAIN_MESSAGE);
                return 0;
            }else {
                // Show Massage Your Coin Is Full
                return 1;
            }
        } else {
            // Show Massage Slot Machine Is Empty
            return 2;
        }
    }


    Coin Coingenerator ( String color , boolean is_special , ImageIcon image ) {

        return  new Coin( color , is_special , image );
    }

    // Setter
    void setLable(int id) {
        this.id = id;
    }
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
    public int getId(){
        return this.id;
    }
    public String getType() {
        return type;
    }

    public boolean getisfull() {
        return this.isfull;
    }

    public int getnumcoins() {
        return this.numcoins;
    }

    ImageIcon getImage () {
        return this.image;
    }

    private ImageIcon getimagebycolor (String color) {


        if ( color.equals("black")){
            System.out.println( "black");
            return new ImageIcon( Main.images.get(48).getAbsolutePath());
        } else if (color.equals("blue")) {
            System.out.println( "blue");
            return new ImageIcon( Main.images.get(49).getAbsolutePath());
        } else if (color.equals("green")) {
            return new ImageIcon( Main.images.get(51).getAbsolutePath());
        } else if (color.equals("red")) {
            return new ImageIcon( Main.images.get(52).getAbsolutePath());
        } else if (color.equals("white")) {
            return new ImageIcon( Main.images.get(53).getAbsolutePath());
        } else {
            return new ImageIcon( Main.images.get(55).getAbsolutePath());
        }
    }
}
