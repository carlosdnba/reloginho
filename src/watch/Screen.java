package watch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen extends JDialog {

    private final JTextField showTime1 = new JTextField();
    private final JTextField showTime2 = new JTextField();

    private final JButton jButton = new JButton("Start");
    private final JButton jButton2 = new JButton("Stop");

    private final Runnable thread1 = () -> {
        while(true) {
            showTime1.setText(new SimpleDateFormat("dd.MM.yyyy hh.mm.ss").format(Calendar.getInstance().getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private final Runnable thread2 = () -> {
        while(true) {
            showTime2.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Thread thread1Time;
    private Thread thread2Time;

    //-------------------------------

    public Screen() {
        setTitle("Timer");
        setSize(new Dimension(240, 240));
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        JLabel threadDesc1 = new JLabel("Thread1");
        threadDesc1.setPreferredSize( new Dimension(200, 25));
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.add(threadDesc1, gridBagConstraints);

        showTime1.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        showTime1.setEditable(false);
        jPanel.add(showTime1, gridBagConstraints);

        JLabel threadDesc2 = new JLabel("Thread2");
        threadDesc2.setPreferredSize( new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(threadDesc2, gridBagConstraints);

        showTime2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        showTime2.setEditable(false);
        jPanel.add(showTime2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy++;
        jPanel.add(jButton, gridBagConstraints);

        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton.addActionListener(e -> {
            thread1Time = new Thread(thread1);
            thread1Time.start();

            thread2Time = new Thread(thread2);
            thread2Time.start();

            jButton.setEnabled(false);
            jButton2.setEnabled(true);
        });

        jButton2.addActionListener(e -> {
            thread1Time.stop();
            thread2Time.stop();
            jButton.setEnabled(true);
            jButton2.setEnabled(false);
        });

        jButton2.setEnabled(false);

        add(jPanel, BorderLayout.WEST);
        setVisible(true);
    }

}
