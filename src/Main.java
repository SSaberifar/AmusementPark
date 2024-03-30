import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Font font = new Font("tahoma",Font.BOLD,20);
    private  Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize();
    public Main() {

        setSize(1500,800);
        System.out.println(screenSize.width+" " + (screenSize.height-50));
        setTitle("Amusement Park");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initLeftpnl();
        initCenterpnl();
        initRightpnl();
        initToppnl();
        initDownpnl();


        setVisible(true);

    }
    public static void main(String[] args) {
            new Main();
    }

    private void initLeftpnl(){

        JPanel pnlcontainer = new JPanel( new BorderLayout());
        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,5,5));


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
        for(int i = 0; i < 16 ; i++){
            JButton btncard = new JButton();

            btncard.setPreferredSize(new Dimension(80 ,90));
            btncard.setBackground(Color.white);
            btncard.setEnabled(false);

            cardbuttontpnl.add( btncard );
        }

        pnlcontainer.add( cardbuttontpnl , BorderLayout.CENTER );
        pnlcontainer.add( leftgappnl , BorderLayout.WEST);
        pnlcontainer.add( rightgappnl , BorderLayout.EAST);
        add(pnlcontainer , BorderLayout.CENTER);
    }

    private void initRightpnl(){

        int wgap = screenSize.width;
        int hgap = screenSize.height;

        JPanel pnlcontainer = new JPanel( new BorderLayout());
        JPanel cardbuttontpnl = new JPanel(new GridLayout(4,4,5,5));

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
        JPanel toppnl = new JPanel( new BorderLayout() );
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

        // Draw Cards
        for(int i = 0; i < 10; i++) {
            JButton btncoin = new JButton();

            btncoin.setPreferredSize(new Dimension(50 ,50));
            btncoin.setBackground(Color.white);
            btncoin.setEnabled(false);

            rightcoinpnl.add(btncoin);
        }

        // Draw Coins
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
        toppnl.add( centerpnl , BorderLayout.CENTER);
        toppnl.add( rightcoinpnl , BorderLayout.EAST);
        toppnl.add( leftcoinpnl , BorderLayout.WEST);
        add( toppnl , BorderLayout.NORTH);
    }

    private void initDownpnl(){

        JPanel downpnl = new JPanel();

        JButton settingbtn = new JButton("Setting");
        downpnl.add(settingbtn);

        add( downpnl , BorderLayout.PAGE_END);
    }

}