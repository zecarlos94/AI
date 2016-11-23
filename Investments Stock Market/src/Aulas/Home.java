package Aulas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/**
 * Created by andrepinto on 23/11/16.
 */
public class Home {
    private JButton defineAreaButton;
    private JButton DFAgentButton;

    public void main() {
        defineAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
