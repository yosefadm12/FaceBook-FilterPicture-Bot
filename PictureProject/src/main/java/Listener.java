import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    JButton btn;

    @Override
    public void actionPerformed(ActionEvent e) {
        btn = (JButton) e.getSource();
        if (btn.getText().equalsIgnoreCase("Search")) {
            Main.frame.driver = new ChromeDriver();
            String search = Main.frame.searchText.getText();
            Main.frame.getProfilePicture(search);
        }
        if (btn.getText().equalsIgnoreCase("Shift Right")) {
            try {
                Main.frame.colorShiftRight();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (btn.getText().equalsIgnoreCase("Shift Left")) {
            try {
                Main.frame.colorShiftLeft();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (btn.getText().equalsIgnoreCase("Contract")) {
            try {
                Main.frame.contract();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (btn.getText().equalsIgnoreCase("Gray Scale")) {
            try {
                Main.frame.grayscale();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (btn.getText().equalsIgnoreCase("Eliminate Red")) {
            try {
                Main.frame.eliminateRed();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}