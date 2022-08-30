import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Frame extends JFrame {

    Listener listener = new Listener();
    JPanel mainPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JTextField searchText;
    JButton search_btn;
    JButton shiftRight;
    JButton shiftLeft;
    JButton contract;
    JButton eliminateRed;
    JButton grayScale;
    WebDriver driver;
    URL url;
    BufferedImage myPicture;
    BufferedImage image;
    JLabel pic;
    JLabel pic2;
    Color color;

    public Frame() {
        //setting up the frame

        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1600, 1000);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        init();

        initListener();
    }


    private void init() {
        //initiating lots of objects

        mainPanel = new Panel(Color.black, 0, 0, 1000, 1000);
        mainPanel.setLayout(null);
        this.setContentPane(mainPanel);

        leftPanel = new Panel(Color.black, 20, 70, 500, 800);
        rightPanel = new Panel(Color.black, 1070, 70, 500, 800);

        searchText = new JTextField();
        searchText.setBounds(700, 200, 100, 50);

        search_btn = new Buttons(new Color(133, 183, 170), "Search", 800, 200, 100, 50);
        shiftRight = new Buttons(new Color(133, 183, 170), "Shift Right", 700, 250, 200, 50);
        shiftLeft = new Buttons(new Color(133, 183, 170), "Shift Left", 700, 300, 200, 50);
        contract = new Buttons(new Color(133, 183, 170), "Contract", 700, 350, 200, 50);
        eliminateRed = new Buttons(new Color(133, 183, 170), "Eliminate Red", 700, 400, 200, 50);
        grayScale = new Buttons(new Color(133, 183, 170), "Gray Scale", 700, 450, 200, 50);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        mainPanel.add(searchText);
        mainPanel.add(search_btn);
        mainPanel.add(shiftRight);
        mainPanel.add(shiftLeft);
        mainPanel.add(contract);
        mainPanel.add(eliminateRed);
        mainPanel.add(grayScale);
        repaint();

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

    }

    private void initListener() {
        //initiating the listener

        search_btn.addActionListener(listener);
        shiftRight.addActionListener(listener);
        shiftLeft.addActionListener(listener);
        contract.addActionListener(listener);
        eliminateRed.addActionListener(listener);
        grayScale.addActionListener(listener);
    }

    public void getProfilePicture(String name) {
        //getting profile picture

        driver.get("https://www.facebook.com/public/" + name);
        try {
            driver.findElement(By.cssSelector("._4bl7._3-90")).click();
        } catch (Exception ignored) {
        }
        try {
            driver.findElement(By.cssSelector(".aglvbi8b.om3e55n1.i8zpp7h3.g4tp4svg")).click();
        } catch (Exception ignored) {
        }

        String imgSrc = driver.findElement(By.tagName("image")).getAttribute("xlink:href");
        setImage(imgSrc);
    }

    private void setImage(String imgSrc) {
        //sets the image on the right and left panels

        rightPanel.removeAll();
        leftPanel.removeAll();
        try {

            this.url = new URL(imgSrc);
            this.myPicture = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pic = null;
        this.pic = new JLabel(new ImageIcon(myPicture));
        this.pic2 = new JLabel(new ImageIcon(myPicture));
        this.pic.setSize(600, 600);
        this.pic2.setSize(400, 600);

        leftPanel.add(pic);
        rightPanel.add(pic2);
        repaint();
        driver.quit();
    }


    public void colorShiftRight() throws Exception {
        if (url != null) {
            image = ImageIO.read(url);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    color = new Color(pixel);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    Color newColor = new Color(g, b, r);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            rightPanel.removeAll();
            pic2 = new JLabel(new ImageIcon(image));
            pic2.setSize(400, 600);
            rightPanel.add(pic2);
            repaint();

        }

    }

    public void colorShiftLeft() throws Exception {
        if (url != null) {
            image = ImageIO.read(url);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    color = new Color(pixel);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    Color newColor = new Color(b, r, g);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            rightPanel.removeAll();
            pic2 = new JLabel(new ImageIcon(image));
            pic2.setSize(400, 600);
            rightPanel.add(pic2);
            repaint();
        }

    }

    public void eliminateRed() throws Exception {
        if (url != null) {
            image = ImageIO.read(url);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    color = new Color(pixel);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    Color newColor = new Color(0, g, b);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            rightPanel.removeAll();
            pic2 = new JLabel(new ImageIcon(image));
            pic2.setSize(400, 600);
            rightPanel.add(pic2);
            repaint();
        }

    }

    public void grayscale() throws Exception {
        if (url != null) {
            image = ImageIO.read(url);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    color = new Color(pixel);
                    int r = (int) (color.getRed() * 0.299);
                    int g = (int) (color.getGreen() * 0.587);
                    int b = (int) (color.getBlue() * 0.114);
                    int total = (r + g + b);
                    Color newColor = new Color(total, total, total);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            rightPanel.removeAll();
            pic2 = new JLabel(new ImageIcon(image));
            pic2.setSize(400, 600);
            rightPanel.add(pic2);
            repaint();
        }

    }

    public void contract() throws Exception {
        if (url != null) {
            image = ImageIO.read(url);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixel = image.getRGB(x, y);
                    color = new Color(pixel);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    Color newColor = new Color(255 - r, 255 - g, 255 - b);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            rightPanel.removeAll();
            pic2 = new JLabel(new ImageIcon(image));
            pic2.setSize(400, 600);
            rightPanel.add(pic2);
            repaint();
        }
    }
}
