package berechnung.pi2;

/**
 * Text genereted by Simple GUI Extension for BlueJ
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GuiProject extends JFrame {

    int pause, clear, dw, sw;

    private JMenuBar menuBar;
    private JButton bpause;
    private JButton buttonclear;
    private JLabel label1;
    private JLabel label3;
    private JLabel label5;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel lins;
    private JLabel lout;
    private JLabel lpi;
    private JTextField textdelay;
    private JTextField textstepsize;

    //Constructor 
    public GuiProject(int d, int s) {

        dw = d;
        sw = s;

        pause = 0;
        clear = 0;

        this.setTitle("GUI_project");
        this.setSize(500, 250);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 250));
        contentPane.setBackground(new Color(240, 254, 255));

        bpause = new JButton();
        bpause.setBounds(138, 185, 90, 35);
        bpause.setBackground(new Color(214, 217, 223));
        bpause.setForeground(new Color(0, 0, 0));
        bpause.setEnabled(true);
        bpause.setFont(new Font("SansSerif", 0, 20));
        bpause.setText("Pause");
        bpause.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        bpause.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Pauseclick(evt);
            }
        });

        buttonclear = new JButton();
        buttonclear.setBounds(5, 184, 90, 35);
        buttonclear.setBackground(new Color(214, 217, 223));
        buttonclear.setForeground(new Color(0, 0, 0));
        buttonclear.setEnabled(true);
        buttonclear.setFont(new Font("SansSerif", 0, 20));
        buttonclear.setText("Clear");
        buttonclear.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        buttonclear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Clearclick(evt);
            }
        });

        label1 = new JLabel();
        label1.setBounds(5, 5, 90, 35);
        label1.setBackground(new Color(214, 217, 223));
        label1.setForeground(new Color(0, 0, 0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif", 0, 20));
        label1.setText("Inside:");
        label1.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(245, 6, 90, 35);
        label3.setBackground(new Color(214, 217, 223));
        label3.setForeground(new Color(0, 0, 0));
        label3.setEnabled(true);
        label3.setFont(new Font("SansSerif", 0, 20));
        label3.setText("Outside:");
        label3.setVisible(true);

        label5 = new JLabel();
        label5.setBounds(5, 43, 100, 35);
        label5.setBackground(new Color(214, 217, 223));
        label5.setForeground(new Color(0, 0, 0));
        label5.setEnabled(true);
        label5.setFont(new Font("SansSerif", 0, 30));
        label5.setText("==> Pi:");
        label5.setVisible(true);

        label7 = new JLabel();
        label7.setBounds(5, 99, 100, 35);
        label7.setBackground(new Color(214, 217, 223));
        label7.setForeground(new Color(0, 0, 0));
        label7.setEnabled(true);
        label7.setFont(new Font("SansSerif", 0, 20));
        label7.setText("Delay(ms):");
        label7.setVisible(true);

        label8 = new JLabel();
        label8.setBounds(138, 101, 100, 35);
        label8.setBackground(new Color(214, 217, 223));
        label8.setForeground(new Color(0, 0, 0));
        label8.setEnabled(true);
        label8.setFont(new Font("SansSerif", 0, 20));
        label8.setText("Stepsize:");
        label8.setVisible(true);

        label9 = new JLabel();
        label9.setBounds(365, 210, 130, 35);
        label9.setBackground(new Color(214, 217, 223));
        label9.setForeground(new Color(0, 0, 0));
        label9.setEnabled(true);
        label9.setFont(new Font("sansserif", 0, 12));
        label9.setText("Made by:Panther2804");
        label9.setVisible(true);

        lins = new JLabel();
        lins.setBounds(67, 6, 170, 32);
        lins.setBackground(new Color(214, 217, 223));
        lins.setForeground(new Color(0, 0, 0));
        lins.setEnabled(true);
        lins.setFont(new Font("SansSerif", 0, 20));
        lins.setText("label");
        lins.setVisible(true);

        lout = new JLabel();
        lout.setBounds(319, 7, 170, 35);
        lout.setBackground(new Color(214, 217, 223));
        lout.setForeground(new Color(0, 0, 0));
        lout.setEnabled(true);
        lout.setFont(new Font("SansSerif", 0, 20));
        lout.setText("label");
        lout.setVisible(true);

        lpi = new JLabel();
        lpi.setBounds(104, 44, 350, 35);
        lpi.setBackground(new Color(214, 217, 223));
        lpi.setForeground(new Color(0, 0, 0));
        lpi.setEnabled(true);
        lpi.setFont(new Font("SansSerif", 0, 30));
        lpi.setText("label");
        lpi.setVisible(true);

        textdelay = new JTextField();
        textdelay.setBounds(5, 135, 90, 35);
        textdelay.setBackground(new Color(255, 255, 255));
        textdelay.setForeground(new Color(0, 0, 0));
        textdelay.setEnabled(true);
        textdelay.setFont(new Font("sansserif", 0, 12));
        textdelay.setText("" + dw);
        textdelay.setVisible(true);

        textstepsize = new JTextField();
        textstepsize.setBounds(140, 134, 90, 35);
        textstepsize.setBackground(new Color(255, 255, 255));
        textstepsize.setForeground(new Color(0, 0, 0));
        textstepsize.setEnabled(true);
        textstepsize.setFont(new Font("sansserif", 0, 12));
        textstepsize.setText("" + sw);
        textstepsize.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(bpause);
        contentPane.add(buttonclear);
        contentPane.add(label1);
        contentPane.add(label3);
        contentPane.add(label5);
        contentPane.add(label7);
        contentPane.add(label8);
        contentPane.add(label9);
        contentPane.add(lins);
        contentPane.add(lout);
        contentPane.add(lpi);
        contentPane.add(textdelay);
        contentPane.add(textstepsize);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for bpause
    private void Pauseclick(MouseEvent evt) {
       if(pause == 0) {
           pause = 1;
       }
       else {
           pause = 0;
       }
    }

    //Method mouseClicked for buttonclear
    private void Clearclick(MouseEvent evt) {
        clear = 1;
    }

    void setin(double a) {
        lins.setText("" + a);
    }

    void setout(double a) {
        lout.setText("" + a);
    }

    void setpi(double a) {
        lpi.setText("" + a);
    }

    int textd() {
        int f = Integer.parseInt(textdelay.getText());
        if (f < 1) {
            return 1;
        } else {
            return f;
        }
    }

    int texts() {
        int f = Integer.parseInt(textstepsize.getText());
        if (pause == 1) {
            return 0;
        } else if (f > 1) {
            return f;
        }
        else {
                return 1;
                }
    }

    //method for generate menu
    public void generateMenu() {
        menuBar = new JMenuBar();
    }

    public static void main(String[] args) {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiProject(100, 100);
            }
        });
    }

}
