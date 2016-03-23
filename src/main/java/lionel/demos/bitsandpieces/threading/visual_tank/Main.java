package lionel.demos.bitsandpieces.threading.visual_tank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Main extends JApplet {

    private static final long serialVersionUID = 29187331815214117L;
    
    public static void main(String args[]){
        Frame frame = new Frame("VT Frame");
        frame.setLayout(new GridLayout(1, 0));
        frame.setSize(300, 300);
        frame.setVisible(true);
        Main m = new Main();
        frame.add(m);
        m.init();
        m.start();
    }
    
    @Override
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initContainer();
                    createGUI();
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            //e.printStackTrace();
            System.err.println("createGUI didn't complete successfully");
        }
    }

    private void startDrainingAndFilling(JProgressBar tankA, JProgressBar tankB) {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException ie) {
        }
        Quantity sb = new Quantity();
        Thread d = new Drainer(tankA, sb);
        Thread f = new Filler(tankB, sb);
        d.start();
        f.start();
    }

    private void createGUI() {
        Container container = getContentPane();

        JLabel lbl = getDefaultLabel("You are successfully running a Swing Applet!");
        lbl.setBounds(10, 5, 300, 20);
        container.add(lbl, BorderLayout.CENTER);

        JProgressBar inPb = getDefaultProgressBar(40, 35);
        // inPb.setOrientation(SwingConstants.HORIZONTAL);
        container.add(inPb);

        JLabel lblInPb = getDefaultLabel("Tank A");
        lblInPb.setLocation(40, 160);
        container.add(lblInPb);

        JProgressBar outPb = getDefaultProgressBar(240, 35);
        container.add(outPb);

        JLabel lblOutPb = getDefaultLabel("Tank B");
        lblOutPb.setLocation(240, 160);
        container.add(lblOutPb);

        container.validate();
        startDrainingAndFilling(inPb, outPb);
    }

    private void initContainer() {
        setSize(700, 300);
        Container container = getContentPane();
        container.setSize(700, 300);
        container.setBackground(Color.white);
        container.setLayout(null);
    }

    private JLabel getDefaultLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        label.setSize(60, 20);
        return label;
    }

    private JProgressBar getDefaultProgressBar(int x, int y) {
        JProgressBar pb = getDefaultProgressBar();
        pb.setLocation(x, y);
        return pb;
    }

    private JProgressBar getDefaultProgressBar() {
        JProgressBar pb = new JProgressBar(SwingConstants.VERTICAL);
        pb.setSize(40, 120);
        pb.setForeground(Color.BLUE);
        pb.setBackground(Color.LIGHT_GRAY);
        pb.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pb.setValue(0);
        return pb;
    }
}
