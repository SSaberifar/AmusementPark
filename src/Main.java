import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.Key;
import java.util.*;
import java.util.List;


public class Main extends JFrame {

    // Set Attribute
    private Font font = new Font("tahoma",Font.BOLD,25);
    private Font numfont = new Font( "tahoma" , Font.BOLD , 50);
    private  Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize();

    // DataBase Path
    // Don't Touch This
    private final String cardpath = "Document\\images\\";

    // Define Windows
    private final JFrame shopWindow = new JFrame();

    private final JFrame receivecoinwindow = new JFrame();
    private final JFrame buycardwindow = new JFrame();
    private final JFrame welecomeWindow = new JFrame();

    // JPanels
    private JPanel coinpnl = new JPanel();
    private JPanel shoppnl = new JPanel();
    private JPanel rightcards = new JPanel();
    private JPanel leftcards = new JPanel();
    private JPanel rightreserve = new JPanel();
    private JPanel leftreserve = new JPanel();
    private JPanel downpnl = new JPanel( );
    private  JPanel toppnl = new JPanel();
    private JPanel rightpnl = new JPanel();
    private JPanel pnlleft = new JPanel( new BorderLayout() );
    private JPanel pnlcenter = new JPanel( new BorderLayout() );
    private JPanel pnlright = new JPanel( new BorderLayout() );
    private JPanel pnldown = new JPanel( new BorderLayout() );
    private JPanel pnltop = new JPanel( new BorderLayout() );

    // JButtons
    JButton[] coins = new JButton[10];
    JButton[] cardbtn = new JButton[16];
    JButton[] pcards1 = new JButton[16];
    JButton[] pcards2 = new JButton[16];
    JButton[] reservecard1 = new JButton[3];
    JButton[] reservecard2 = new JButton[3];


    JButton[] slot_machine = new JButton[5];

    static List<File> images = new ArrayList<>();

    Card[] cards = new Card[48];
    static SlotMachine[] slots = new SlotMachine[5];

    Player player1 = new Player();
    Player player2 = new Player();
    private boolean turn = true; // true : player 1 , false : player 2

    public Main() {

        setSize(1500,800);
        setTitle("Amusement Park");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground( new Color(255, 243, 199));
        setResizable(false);

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


        pnlleft.revalidate();
        pnlleft.repaint();

        leftcards = new JPanel(new GridLayout(4,4,10,5));

        leftcards.setBorder( BorderFactory.createEmptyBorder(5,5,50,5));

        // Draw Player2 Cards
        leftcards = Updateplayercards( leftcards , player2);

        pnlleft.add( leftcards , BorderLayout.EAST );

        add(pnlleft , BorderLayout.WEST);
    }

    public void initCenterpnl() {

        pnlcenter.removeAll();
        pnlcenter.revalidate();

        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,5,5));
        JPanel leftgappnl = new JPanel();
        JPanel rightgappnl = new JPanel();

        cardbuttontpnl.setBorder( BorderFactory.createEmptyBorder(5,5,50,5));
        leftgappnl.setPreferredSize( new Dimension(150,1));
        rightgappnl.setPreferredSize( new Dimension(150,1));

        // Draw Shop Cards
        for(int i = 0 ; i < cardbtn.length ; i++) {
            cardbtn[i] = new JButton();

            cardbtn[i].setPreferredSize( new Dimension(80,90));
            cardbtn[i].setBackground( new Color(255, 243, 199) );
            cardbtn[i].setIcon( RandomImagesetter( i,cardbtn[i] ) );

            cardbuttontpnl.add(cardbtn[i]);
        }
        cardbtn[3].setVisible(false);


        // Mouse Listener
        MouseListener mouselis = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton btn = (JButton) e.getSource();

                if ( SwingUtilities.isLeftMouseButton(e)) {

                     Findcardforbutton( btn ).Buy( getplayer() );
                     JOptionPane.showMessageDialog(null,"buy","title",JOptionPane.PLAIN_MESSAGE);
                     updatecoinpnl(rightcards ,player1);
                     updatecoinpnl(leftcards ,player2);
                     Turnchanger();
                }
                if ( SwingUtilities.isRightMouseButton(e) ) {

                    Findcardforbutton( btn ).Reserve( getplayer() );
                    JOptionPane.showMessageDialog(null,"reserve","title",JOptionPane.PLAIN_MESSAGE);
                    updatecoinpnl(rightcards ,player1);
                    updatecoinpnl(leftcards ,player2);
                    Turnchanger();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        // Add Mouse Listener
        for ( JButton card : cardbtn) {
                card.addMouseListener( mouselis );
        }

        // Add Panels To Center Panel
        pnlcenter.add( cardbuttontpnl , BorderLayout.CENTER );
        pnlcenter.add( leftgappnl , BorderLayout.WEST);
        pnlcenter.add( rightgappnl , BorderLayout.EAST);

        add(pnlcenter , BorderLayout.CENTER);

    }

    private void initRightpnl(){


        pnlright.revalidate();
        pnlright.repaint();

        pnlright = new JPanel( new BorderLayout());
        rightcards = new JPanel(new GridLayout(4,4,10,5));

        // Draw Player1 Cards
        rightcards = Updateplayercards(rightcards , player1);

        pnlright.add(rightcards , BorderLayout.WEST);

        add(pnlright , BorderLayout.EAST);
    }

    private void initToppnl() {

        pnltop.removeAll();
        pnltop.revalidate();


        pnltop = new JPanel( new BorderLayout() );
        JPanel rightcoinpnl = new JPanel(new GridLayout(2,5,5,5));
        JPanel leftcoinpnl = new JPanel( new GridLayout(2,5,5,5));
        JPanel centerpnl = new JPanel();
        JPanel gappnl = new JPanel();

        leftcoinpnl.setBorder( BorderFactory.createEmptyBorder(5,10,0,5));
        rightcoinpnl.setBorder( BorderFactory.createEmptyBorder(5,5,0,10));

        // Draw Score2 And Scoin2 Button
        JLabel score2 = new JLabel(""+player2.getScore());
        score2.setFont(numfont);
        score2.setPreferredSize( new Dimension(100,100 ));
        score2.setFocusable(false);
        score2.setBackground(Color.white);
        score2.setForeground(Color.blue);
        centerpnl.add( score2);

        JLabel scoin2 = new JLabel(""+player2.getscoinnumber());
        scoin2.setPreferredSize( new Dimension(100,100));
        scoin2.setFont(numfont);
        scoin2.setFocusable(false);
        scoin2.setBackground(new Color(255, 255, 153));
        scoin2.setForeground(new Color(255, 215, 0));
        centerpnl.add( scoin2);

        // Draw Setting Button
        JButton settingbtn = new JButton("Turn");
        settingbtn.setFont(font);
        settingbtn.setPreferredSize( new Dimension(200 ,70));
        settingbtn.setFocusable(false);
        settingbtn.setBackground(new Color(254, 199, 180));
        settingbtn.setForeground(Color.black);
        centerpnl.add( settingbtn , BorderLayout.CENTER);

        // Draw Score1 And Scoin1 Button
        JLabel score1 = new JLabel(""+player1.getScore());
        score1.setFont(numfont);
        score1.setPreferredSize( new Dimension(100,100 ));
        score1.setFocusable(false);
        score1.setBackground(Color.white);
        score1.setForeground(Color.red);
        centerpnl.add( score1);

        JLabel scoin1 = new JLabel(""+player2.getscoinnumber());
        scoin1.setPreferredSize( new Dimension(50,50));
        scoin1.setFont(numfont);
        scoin1.setFocusable(false);
        scoin1.setBackground(new Color(255, 255, 153));
        scoin1.setForeground(new Color(255, 215, 0));
        centerpnl.add( scoin1);

        // Draw  Right Coins
        for(int i = 0; i < 10 && player1.getBcoins()[i] != null; i++) {
            JButton btncoin = new JButton();

            btncoin.setPreferredSize( new Dimension(50 ,50) );
            btncoin.setBackground(Color.white);
//            remove( btncoin );
//            btncoin.setOpaque(false);
//            btncoin.setContentAreaFilled(false);
//            btncoin.setBorderPainted(false);

            if ( player1.getBcoins()[i] != null) {
                btncoin.setIcon( player1.getBcoins()[i].getImage() );
            } else {
                btncoin.setIcon( defualimage("white"));
            }

            btncoin.setEnabled(true);


            rightcoinpnl.add(btncoin);
            rightcoinpnl.revalidate();
            rightcoinpnl.repaint();
        }

        // Draw Left Coins
        for(int i = 0; i < 10 && player2.getBcoins()[i] != null ; i++) {
            JButton btncoin = new JButton();

            btncoin.setPreferredSize(new Dimension(50 ,50));
            btncoin.setBackground(Color.white);
            btncoin.setEnabled(true);
            if ( player2.getBcoins()[i] != null) {
                btncoin.setIcon( player1.getBcoins()[i].getImage() );
            } else {
                btncoin.setIcon( defualimage("white"));
            }

            leftcoinpnl.add(btncoin);
        }


        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( e.getActionCommand().equals( settingbtn.getText() ) ) {
                    Turnchanger();
                }
            }
        };

        settingbtn.addActionListener(actionlis);

        pnltop.add( centerpnl , BorderLayout.CENTER);
        pnltop.add( rightcoinpnl , BorderLayout.EAST);
        pnltop.add( leftcoinpnl , BorderLayout.WEST);

        add( pnltop , BorderLayout.NORTH);
    }

    private void initDownpnl() {

        pnldown.removeAll();
        pnldown.revalidate();


        pnldown = new JPanel(new BorderLayout());
        rightreserve = new JPanel( new GridLayout(1,3,10,5) );
        leftreserve = new JPanel( new GridLayout(1,3,10,5) );
        JPanel buttonpnl = new JPanel();

        // Set Gap
        rightreserve.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));
        leftreserve.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));
        buttonpnl.setBorder( BorderFactory.createEmptyBorder(5,10,10,10));

        // Draw Turn Light Button2
        JButton turnl2 = new JButton();
        turnl2.setPreferredSize( new Dimension(50,50 ));
        turnl2.setFocusable(false);
        turnl2.setBackground(Color.white);
        buttonpnl.add( turnl2 );

        // Draw Shop Button
        JButton shopbtn = new JButton("Coin");
        shopbtn.setPreferredSize( new Dimension( 200 , 80) );
        shopbtn.setBackground( new Color(252 , 129, 158) );
        shopbtn.setFocusable(false);
        shopbtn.setFont(font);
        shopbtn.setActionCommand("shopbtn");
        buttonpnl.add( shopbtn );

        // Draw Turn Light Button1
        JButton turnl1 = new JButton();
        turnl1.setPreferredSize( new Dimension(50,50 ));
        turnl1.setFocusable(false);
        turnl1.setBackground(Color.white);
        buttonpnl.add( turnl1 );

        // Draw Reserved Cards

        // Draw Right Reserved Cards
        for ( int i = 0 ; i < 3 ; i++){
            reservecard1[i] = new JButton();

            reservecard1[i].setPreferredSize( new Dimension(80,90));
            reservecard1[i].setBackground(Color.white);

            if ( player1.Cardcounter() > 0 && player1.getreservelist()[i] != null ) {
                reservecard1[i].setIcon( player1.getreservelist()[i].getImage() );
            }

            rightreserve.add( reservecard1[i]);
        }
        // Draw Left Reserved Cards
        for ( int i = 0 ; i < 3 ; i++){
            reservecard2[i] = new JButton();

            reservecard2[i].setPreferredSize( new Dimension(80,90));
            reservecard2[i].setBackground(Color.white);

            leftreserve.add( reservecard2[i]);
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

        // Add Panels To Mainpanel
        pnldown.add( rightreserve , BorderLayout.EAST);
        pnldown.add( leftreserve , BorderLayout.WEST);
        pnldown.add( buttonpnl , BorderLayout.CENTER);

        add( pnldown , BorderLayout.SOUTH);
    }

    private void Shopwindow() {

        coinpnl.removeAll();
        coinpnl.revalidate();
        coinpnl.repaint();

        coinpnl = new JPanel();

        shoppnl.revalidate();
        shoppnl.repaint();

        shopWindow.setSize(610 ,220);
        shopWindow.setLocationRelativeTo(null);

        coinpnl = new JPanel( new GridLayout(1,10,10,20));
        JPanel slotpnl= new JPanel( new GridLayout(1,5,10,20));
        JPanel shoppnl = new JPanel( );
        JPanel gappnl = new JPanel( );


        gappnl.setPreferredSize( new Dimension(100,10));

        // Draw Player Coins

//        for(int i = 0 ; i < 10 && player1.getBcoins()[i] != null; i++){
//            coins[i] = new JButton();
//
//            coins[i].setPreferredSize( new Dimension(50,50));
//            coins[i].setBackground( new Color(255, 243, 199) );
//            coins[i].setActionCommand( "c" + i );
//
//            if ( player1.getBcoins()[i] != null ){
//
//                coins[i].setIcon( player1.getBcoins()[i].getImage());
//            }
//
//            coins[i].setVisible(true);
//
//            coinpnl.add(coins[i] );
//        }
        updatecoinpnl(coinpnl,getplayer());

        // Draw Slot machine
        for(int i = 0 ; i < 5 ; i++) {
            slot_machine[i] = new JButton();

            slot_machine[i].setPreferredSize( new Dimension( 110,110) );
            slot_machine[i].setBackground( Color.white );
            slot_machine[i].setIcon( slots[i].getImage() );
            slot_machine[i].setActionCommand(""+ slots[i].getId());
            slot_machine[i].setVisible(true);

            slotpnl.add( slot_machine[i] );
        }

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton slot = (JButton) e.getSource();
                if ( SwingUtilities.isLeftMouseButton(e)){
                    FindSlotForButton( slot ).Receiveonecoin( getplayer() );
                    updatecoinpnl(coinpnl , getplayer());
                    receivecoinwindow.dispose();
                    shopWindow.dispose();
                    updatescreen();

                } if ( SwingUtilities.isRightMouseButton(e)){
                    FindSlotForButton( slot ).Receivetwocoin( getplayer() );
                    updatecoinpnl(coinpnl , getplayer());
                    receivecoinwindow.dispose();
                    shopWindow.dispose();
                    updatescreen();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        // Add MouseListener
//        for ( JButton coin : coins) {
//
//            coin.addActionListener( actionlis );
//        }
        for ( JButton slot : slot_machine) {

            slot.addMouseListener( mouseListener );
        }


        shoppnl.add( coinpnl , BorderLayout.NORTH);
        shoppnl.add( slotpnl , BorderLayout.CENTER );


        shopWindow.add( shoppnl , BorderLayout.CENTER );

        shopWindow.setVisible(true);


    }

    private void ReceiveWindow( SlotMachine slot) {

        receivecoinwindow.setSize( new Dimension(340,100));
        receivecoinwindow.setLocationRelativeTo(null);
        receivecoinwindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        receivecoinwindow.setUndecorated(true);

        JPanel pnlcontainer = new JPanel();
        JPanel centerpnl = new JPanel( );

        // Draw Receive 2 Coin Buttons
        JButton rtwocoin = new JButton("2 Coin");
        rtwocoin.setPreferredSize( new Dimension(150 ,80));
        rtwocoin.setBackground( new Color(250, 112, 112));
        rtwocoin.setFocusable(false);
        rtwocoin.setFont(font);
        rtwocoin.setActionCommand("two");
        centerpnl.add( rtwocoin );

        // Draw Receive 1 Coin Buttons
        JButton ronecoin = new JButton("1 Coin");
        ronecoin.setPreferredSize( new Dimension(150 ,80));
        ronecoin.setBackground( new Color(91, 188, 255));
        ronecoin.setFocusable(false);
        ronecoin.setFont(font);
        ronecoin.setActionCommand("one");
        centerpnl.add( ronecoin );

        // Add Action Listener
        ActionListener actionlis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getActionCommand().equals( ronecoin.getActionCommand()) ) {
                    slot.Receiveonecoin(player1);
                    updatecoinpnl(coinpnl , player1);
                    receivecoinwindow.dispose();
                    //Shopwindow();
                    updatescreen();
                    System.out.println("Yes");

                }
                if( e.getActionCommand().equals( rtwocoin.getActionCommand()) ) {
                    slot.Receivetwocoin( player1);
                    sortcoins(player1.getBcoins());
                    updatecoinpnl( coinpnl , player1);
                    receivecoinwindow.dispose();
                    //Shopwindow();
                    shopWindow.dispose();
                    updatescreen();
                    System.out.println("Yes");
                }
            }
        };

        ronecoin.addActionListener(actionlis);
        rtwocoin.addActionListener(actionlis);

        receivecoinwindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                coinpnl.revalidate();
                coinpnl.repaint();
            }
        });

        pnlcontainer.add( centerpnl , BorderLayout.CENTER);

        receivecoinwindow.add( pnlcontainer);


        receivecoinwindow.setVisible(true);
    }

    private void Turnchanger(){
        if (turn){
            turn = false;
        } else {
            turn = true;
        }
    }

    private Player getplayer() {
        return turn ? player1 : player2;
    }

    private ImageIcon RandomImagesetter(int i , JButton btn) {

        Random rand = new Random();
        ImageIcon icon ;
        int number ;
        try{
            if( i >= 0 && i <= 2){

                number = rand.nextInt(3);

                btn.setActionCommand( ""+cards[number].getId() );
                return cards[ number ].getImage();

            } else if ( i >= 4 && i <= 7) {

                number = rand.nextInt( 15) +3;

                btn.setActionCommand( ""+cards[number].getId() );
                return cards[ number ].getImage();

            } else if ( i >= 8 && i<= 11) {

                number = rand.nextInt( 15)+18;

                btn.setActionCommand( ""+cards[ number ].getId() );
                return cards[ number ].getImage();

            } else if ( i>= 12 && i <= 15) {
                number = rand.nextInt( 15)+33;

                btn.setActionCommand( ""+ cards[ number ].getId() );
                return cards[ number ].getImage();

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
            slots[4-i] = new SlotMachine( 4-i ,
                    images.get( images.size()-i-1 ).getName().split("\\.")[0] ,
                    new ImageIcon( images.get( images.size()-i-1).getAbsolutePath())
            );


            System.out.println(images.get( images.size()-i-1 ).getName().split("\\.")[0]);
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

                        cards[ index ] = Cardgenerator( index , Integer.parseInt(parts[1]) ,
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

    private Card Cardgenerator ( int id ,int score , int numberofcoin1 , String firstcolor , int numberbofcoin2 ,String secondcolor , String scoin , ImageIcon image ) {

        Coin[] rcoins = new Coin[ numberofcoin1 + numberbofcoin2 ];
        if ( !scoin.isEmpty()) {

            int i =0 ;
            for ( i = 0 ; i < numberofcoin1 ; i++ ) {
                rcoins[i] = new Coin( firstcolor , false , null);
            }
            for (int j = 0 ; j< numberbofcoin2 ; j++) {
                rcoins[j+i] = new Coin( secondcolor , false ,null);
            }
            Coin specialcoin = new Coin( scoin , true , null);

            return new Card( specialcoin , rcoins , score , image ,id);
        } else { // Prize_claw Card

            int i = 0 ;
            for ( i = 0 ; i < numberofcoin1 ; i++ ) {
                rcoins[i] = new Coin( firstcolor , true , null);
            }
            for (int j = 0 ; j< numberbofcoin2 ; j++) {
                rcoins[j+i] = new Coin( secondcolor , true ,null);
            }

            return new Card( rcoins , score , image ,id);
        }
    }

    // Remove Null Reference Elements From Lists
    static <T> void UpdateLists ( List<T> list) {

        if ( list == null) {
            throw  new IllegalArgumentException("List cannot be null");
        }
        Iterator<T> iterator = list.iterator();
        while ( iterator.hasNext()) {
            if( iterator.next() == null){
                iterator.remove();
            }
        }
    }
    private ImageIcon defualimage( String color) {
        if (color.equals("white")){
            return new ImageIcon( images.get(55).getAbsolutePath());
        }else {
            return  new ImageIcon( images.get(54).getAbsolutePath());
        }
    }

    private void updater(){

        initToppnl();
    }


    private void updatecoinpnl( JPanel pnl , Player player) {


        for(int i = 0 ; i < 10 ; i++) {

            coins[i] = new JButton();

            coins[i].setPreferredSize(new Dimension(50, 50));
            coins[i].setBackground(new Color(255, 243, 199));
            coins[i].setActionCommand("c" + i);

            if (player.getBcoins()[i] != null ) {

                coins[i].setIcon(player.getBcoins()[i].getImage());
            }


            coins[i].setVisible(true);

            coinpnl.add(coins[i]);
        }


    }

    private void Updateplaercoin ( ) {


    }

    private JPanel Updateplayercards( JPanel pnl , Player player) {

        pnl.removeAll();

        pnl = new JPanel(new GridLayout(4,4,10,5));
        pnl.setBorder( BorderFactory.createEmptyBorder(5,5,50,5));

        // Draw Player Cards
        for(int i = 0; i < 16 ; i++){
            JButton card = new JButton();

            card.setPreferredSize(new Dimension(80 ,90));
            card.setBackground(Color.white);
            card.setEnabled(true);

            if ( player.Cardcounter() > 0 && player.getCards()[i] != null) {
                card.setIcon( player.getCards()[i].getImage() );
            } else {
                card.setIcon( defualimage("white") );
            }

            pnl.add( card );

        }


        return  pnl;
    }

    private void Updateresevedcards( Player player) {


        // Draw Right Reserved Cards
        for ( int i = 0 ; i < 3 ; i++){
            reservecard1[i] = new JButton();

            reservecard1[i].setPreferredSize( new Dimension(80,90));
            reservecard1[i].setBackground(Color.white);

            rightreserve.add( reservecard1[i]);
        }
        // Draw Left Reserved Cards
        for ( int i = 0 ; i < 3 ; i++){
            reservecard2[i] = new JButton();

            reservecard2[i].setPreferredSize( new Dimension(80,90));
            reservecard2[i].setBackground(Color.white);

            leftreserve.add( reservecard2[i]);
        }

    }

    private void updatescreen(){
        initToppnl();
        initLeftpnl();
        initCenterpnl();
        initRightpnl();
        initDownpnl();
    }
    public static void sortcoins( Coin[] arr) {
        int nonNullIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            // If the current element is non-null
            if (arr[i] != null) {
                // Swap the current element with the element at nonNullIndex
                Coin temp = arr[i];
                arr[i] = arr[nonNullIndex];
                arr[nonNullIndex] = temp;
                // Move the nonNullIndex to the next position
                nonNullIndex++;
            }
        }
    }

    private Card Findcardforbutton( JButton btn) {

        int id = Integer.parseInt( btn.getActionCommand() );

        for (Card card : cards) {
            if ( card.getId() == id) {
                return card;
            }
        }

        String msg = " Card Not Found In Button / FindCard";
        JOptionPane.showMessageDialog( null,msg,"Error",JOptionPane.PLAIN_MESSAGE);
        return cards[0];
    }
    private SlotMachine FindSlotForButton( JButton btn) {
        int id = Integer.parseInt( btn.getActionCommand());

        for (SlotMachine slot : slots) {
            if (slot.getId() == id) {
                return slot;
            }
        }

        String msg = " Slot Not Found In Button / FindSlot";
        JOptionPane.showMessageDialog( null,msg,"Error",JOptionPane.PLAIN_MESSAGE);
        return slots[0];
    }
}