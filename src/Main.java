import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main extends JFrame {

    // Define Windows
    private final JFrame shopWindow = new JFrame();
    private final JFrame welecomeWindow = new JFrame();

    private Font font = new Font("tahoma",Font.BOLD,20);
    private  Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize();

    private String cardpath = "Document\\images\\";

    JButton[] coins = new JButton[10];

    JButton[] slot_machine = new JButton[5];

    List<File> images = new ArrayList<>();

    Card[] cards = new Card[48];
    static SlotMachine[] slots = new SlotMachine[5];

    Player player1 = new Player();
    Player player2 = new Player();

    public Main() {

        setSize(1500,800);
        setTitle("Amusement Park");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground( new Color(255, 243, 199));

        // Load Image
        ImageLoader();
        SlotMachineLoader();

        initLeftpnl();
        initCenterpnl();
        initRightpnl();
        initToppnl();
        initDownpnl();

        setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater( () -> {
            new Main();
        });

    }

    private void initLeftpnl(){

        JPanel pnlcontainer = new JPanel( new BorderLayout());
        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,10,5));

        cardbuttontpnl.setBorder( BorderFactory.createEmptyBorder(5,5,50,5));

        // Draw Player2 Cards
        for(int i = 0; i < 16 ; i++){
            JButton btncard = new JButton();

            btncard.setPreferredSize(new Dimension(80 ,90));
            btncard.setBackground(Color.white);
            btncard.setEnabled(false);

            cardbuttontpnl.add( btncard );
        }

        pnlcontainer.add( cardbuttontpnl , BorderLayout.EAST );

        add(pnlcontainer , BorderLayout.WEST);
    }

    public void initCenterpnl() {

        JPanel pnlcontainer = new JPanel( new BorderLayout());
        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,5,5));
        JPanel leftgappnl = new JPanel();
        JPanel rightgappnl = new JPanel();

        cardbuttontpnl.setBorder( BorderFactory.createEmptyBorder(5,5,50,5));
        leftgappnl.setPreferredSize( new Dimension(150,1));
        rightgappnl.setPreferredSize( new Dimension(150,1));

        // Draw Shop Cards

        JButton[] cardlist = new JButton[16];

        for(int i = 0 ; i < cardlist.length ; i++) {


            cardlist[i] = new JButton();


            cardlist[i].setPreferredSize( new Dimension(80,90));
            cardlist[i].setBackground( Color.white);
            cardlist[i].setActionCommand("c"+i);


            cardlist[i].setIcon( RandomImagesetter(i));
            cardbuttontpnl.add(cardlist[i]);
        }
        cardlist[3].setVisible(false);

        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch( e.getActionCommand() ){

                    case "c0": ;break;
                    case "c1": ;break;
                    case "c2": ;break;
                    case "c3": ;break;
                    case "c4": ;break;
                    case "c5": ;break;
                    case "c6": ;break;
                    case "c7": ;break;
                    case "c8": ;break;
                    case "c9": ;break;
                    case "c10": ;break;
                    case "c11": ;break;
                    case "c12": ;break;
                    case "c13": ;break;
                    case "c14": ;break;
                    case "c15": ;break;

                    default: ;break;
                }
            }
        };

        // Add ActionListener
        for ( JButton card : cardlist) {

                card.addActionListener( actionlis );
        }

        // Add Panels To PanelContainer
        pnlcontainer.add( cardbuttontpnl , BorderLayout.CENTER );
        pnlcontainer.add( leftgappnl , BorderLayout.WEST);
        pnlcontainer.add( rightgappnl , BorderLayout.EAST);

        add(pnlcontainer , BorderLayout.CENTER);

    }

    private void initRightpnl(){

        int wgap = screenSize.width;
        int hgap = screenSize.height;

        JPanel pnlcontainer = new JPanel( new BorderLayout());
        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,10,5));

        cardbuttontpnl.setBorder( BorderFactory.createEmptyBorder(5,0,50,5));

        // Draw Player1 Cards
        for(int i = 0; i < 16 ; i++){
            JButton btncard = new JButton();

            btncard.setPreferredSize(new Dimension(80 ,90));
            btncard.setBackground(Color.white);
            btncard.setEnabled(false);

            cardbuttontpnl.add( btncard );
        }

        pnlcontainer.add(cardbuttontpnl , BorderLayout.WEST);

        add(pnlcontainer , BorderLayout.EAST);
    }

    private void initToppnl() {
        JPanel pnlcontainer = new JPanel( new BorderLayout() );
        JPanel rightcoinpnl = new JPanel(new GridLayout(2,5,5,5));
        JPanel leftcoinpnl = new JPanel( new GridLayout(2,5,5,5));
        JPanel centerpnl = new JPanel();

        leftcoinpnl.setBorder( BorderFactory.createEmptyBorder(5,10,0,5));
        rightcoinpnl.setBorder( BorderFactory.createEmptyBorder(5,5,0,10));

        // Draw Setting Button
        JButton settingbtn = new JButton("Setting");
        settingbtn.setFont(font);
        settingbtn.setPreferredSize( new Dimension(200 ,70));
        settingbtn.setBackground(Color.gray);
        settingbtn.setForeground(Color.black);
        centerpnl.add( settingbtn );

        // Draw  Right Coins
        for(int i = 0; i < 10; i++) {
            JButton btncoin = new JButton();

            btncoin.setPreferredSize(new Dimension(50 ,50));
            btncoin.setBackground(Color.white);
            btncoin.setEnabled(true);

            rightcoinpnl.add(btncoin);
        }

        // Draw Left Coins
        for(int i = 0; i < 10; i++) {
            JButton btncoin = new JButton();

            btncoin.setPreferredSize(new Dimension(50 ,50));
            btncoin.setBackground(Color.white);
            btncoin.setEnabled(false);

            leftcoinpnl.add(btncoin);
        }


        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( e.getActionCommand().equals( settingbtn.getText() ) ) {
                    settingbtn.setText( "clik here" );
                }
            }
        };

        settingbtn.addActionListener(actionlis);
        pnlcontainer.add( centerpnl , BorderLayout.CENTER);
        pnlcontainer.add( rightcoinpnl , BorderLayout.EAST);
        pnlcontainer.add( leftcoinpnl , BorderLayout.WEST);
        add( pnlcontainer , BorderLayout.NORTH);
    }

    private void initDownpnl() {

        JPanel pnlcontainer = new JPanel(new BorderLayout());
        JPanel rightcardpnl = new JPanel( new GridLayout(1,3,10,5) );
        JPanel leftcardpnl = new JPanel( new GridLayout(1,3,10,5) );
        JPanel buttonpnl = new JPanel();

        // Set Gap
        rightcardpnl.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));
        leftcardpnl.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));
        buttonpnl.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));

        // Draw button
        JButton shopbtn = new JButton("Shop");
        shopbtn.setPreferredSize( new Dimension( 200 , 80) );
        shopbtn.setBackground( new Color(252 , 129, 158) );
        shopbtn.setFont(font);
        shopbtn.setActionCommand("shopbtn");
        buttonpnl.add( shopbtn );


        // Draw Left Reserved Cards

        List<JButton> rcoin = new ArrayList<>();

        for ( int i = 0 ; i < 6 ; i++){
            rcoin.add( new JButton() );

            rcoin.get(i).setPreferredSize( new Dimension(80,90));
            rcoin.get(i).setBackground(Color.white);

            rcoin.get(i).setActionCommand(""+i);
        }



        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if( e.getActionCommand().equals( shopbtn.getActionCommand())){

                    Shopwindow();
                }
            }
        };

        // Add Actionlistener To Buttons
        shopbtn.addActionListener(actionlis);

        // Add Cards To Panels
        for(int i = 0 ; i < 6 ;i++) {

            if(i < 3) leftcardpnl.add( rcoin.get(i) );
            else  rightcardpnl.add( rcoin.get(i) );
        }


        // Add Panels To Mainpanel
        pnlcontainer.add( rightcardpnl , BorderLayout.EAST);
        pnlcontainer.add( leftcardpnl , BorderLayout.WEST);
        pnlcontainer.add( buttonpnl , BorderLayout.CENTER);

        add( pnlcontainer , BorderLayout.SOUTH);
    }

    private void Shopwindow() {


        shopWindow.setSize(610 ,230);
        shopWindow.setLocationRelativeTo(null);

        JPanel cointpnl = new JPanel( new GridLayout(1,10,10,20));
        JPanel slotpnl= new JPanel( new GridLayout(1,5,10,20));
        JPanel centerpnl = new JPanel( );
        JPanel gappnl = new JPanel( );

        gappnl.setPreferredSize( new Dimension(100,10));

        // Draw Player Coins

        for(int i = 0 ; i < 10; i++){
            coins[i] = new JButton();

            coins[i].setPreferredSize( new Dimension(50,50));
            coins[i].setBackground( Color.white );
            coins[i].setActionCommand( "c" + i );
            coins[i].setVisible(true);

            cointpnl.add(coins[i] );
        }

        // Draw Slot machine
        for(int i = 0 ; i < 5 ; i++) {
            slot_machine[i] = new JButton();

            slot_machine[i].setPreferredSize( new Dimension( 110,110) );
            slot_machine[i].setActionCommand( "s" + i );
            slot_machine[i].setBackground( Color.white );
            slot_machine[i].setIcon( new ImageIcon( images.get( 56+i ).getAbsolutePath()) );
            slot_machine[i].setVisible(true);

            slotpnl.add( slot_machine[i] );
        }

        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch ( e.getActionCommand()) {

                    case "c0": ; break;
                    case "c1": ; break;
                    case "c2": ; break;
                    case "c3": ; break;
                    case "c4": ; break;
                    case "c5": ; break;
                    case "c6": ; break;
                    case "c7": ; break;
                    case "c8": ; break;
                    case "c9": ; break;

                    case "s0": ; break;
                    case "s1": ; break;
                    case "s2": ; break;
                    case "s3": ; break;
                    case "s4": ; break;
                    default: ; break;
                }
            }
        };

        // Add ActoinListener
        for ( JButton coin : coins) {

            coin.addActionListener( actionlis );
        }
        for ( JButton slot : slot_machine) {

            slot.addActionListener( actionlis );
        }


        centerpnl.add( cointpnl , BorderLayout.NORTH);
        centerpnl.add( gappnl , BorderLayout.CENTER );
        centerpnl.add( slotpnl , BorderLayout.SOUTH );

        shopWindow.add( centerpnl , BorderLayout.CENTER );

        shopWindow.setVisible(true);

    }


    private ImageIcon RandomImagesetter(int i) {

        Random rand = new Random();
        ImageIcon icon ;

        try{
            if( i >= 0 && i <= 2){

                icon = new ImageIcon( images.get(( rand.nextInt(3) )).getAbsolutePath() );
                return icon;
            } else if ( i >= 4 && i <= 7) {
                icon = new ImageIcon( images.get( rand.nextInt( 15) +3).getAbsolutePath() );
                return icon;

            } else if ( i >= 8 && i<= 11) {
                icon = new ImageIcon( images.get( rand.nextInt( 15)+18 ).getAbsolutePath() );
                return icon;

            } else if ( i>= 12 && i <= 15) {
                icon = new ImageIcon( images.get( rand.nextInt( 15)+33 ).getAbsolutePath() );
                return icon;

            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }

    private void SlotMachineLoader() {

        for (int i = 0 ; i < 5 ; i++) {
            slots[i] = new SlotMachine( images.get( images.size()-i-1 ).getName() ,
                    new ImageIcon( images.get( images.size()-i-1).getAbsolutePath())
            );
        }
    }
    private void ImageLoader() {

        int index = 0;
        for(int n = 0 ; n < 7 ; n++){

            File folder = new File(cardpath + n);

            for ( File image : folder.listFiles()) {

                if ( n < 4) {
                    try {
                        String[] parts = image.getName().split("\\.");

                        cards[ index ] = Cardgenerator( Integer.parseInt(parts[1]) ,
                                Integer.parseInt(parts[2]) ,
                                parts[3] ,
                                Integer.parseInt(parts[4]) ,
                                parts[5],
                                parts[6],
                                new ImageIcon( image.getAbsolutePath() )
                        );

                        index++;
                    }    catch (NumberFormatException e) {

                    }

                }

                images.add(image);
            }
        }
    }

    private Card Cardgenerator ( int score , int numberofcoin1 , String firstcolor , int numberbofcoin2 ,String secondcolor , String scoin , ImageIcon image ) {

        Coin[] rcoins = new Coin[ numberofcoin1 + numberbofcoin2 ];

        Coin specialcoin = new Coin( scoin , true , null);

        for ( int i = 0 ; i < numberofcoin1 + numberbofcoin2 ; i++ ) {
            rcoins[i] = new Coin( firstcolor , false , null);
        }

        return new Card( specialcoin , rcoins , score , image);
    }

    // unable to use
    private Coin Coingenerator ( String color , boolean is_special , ImageIcon image ) {

        return  new Coin( color , is_special , image );
    }
}