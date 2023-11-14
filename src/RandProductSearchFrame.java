import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandProductSearchFrame extends JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel inputPnl;
    JPanel displayPnl;
    JPanel buttonPnl;
    JLabel titleLbl;
    JLabel inputLbl;
    JTextArea displayTextArea;
    JTextField inputTextField;
    JScrollPane scroller;
    JButton searchBtn;
    JButton quitBtn;
    ActionListener quit = new quitListener();
    ActionListener search = new searchListener();

    String input;
    String display;

    RandProductSearchFrame()
    {
        setTitle("Product Search");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        titlePnl = new JPanel();
        inputPnl = new JPanel();
        displayPnl = new JPanel();
        buttonPnl = new JPanel();
        titleLbl = new JLabel("Random Product Search");
        titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 35));
        inputLbl = new JLabel("Product Search: ");
        inputLbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
        inputTextField = new JTextField();
        displayTextArea = new JTextArea(6,50);
        scroller = new JScrollPane(displayTextArea);

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        searchBtn.addActionListener(search);
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        quitBtn.addActionListener(quit);

        add(mainPnl);
        mainPnl.setLayout(new GridLayout(4,1,50,50));
        mainPnl.add(titlePnl);
        titlePnl.add(titleLbl);
        titleLbl.setHorizontalAlignment(JLabel.CENTER);
        mainPnl.add(inputPnl);
        inputPnl.setLayout(new GridLayout(2,1,150,0));
        inputPnl.add(inputLbl);
        inputPnl.add(inputTextField);
        inputTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainPnl.add(displayPnl);
        displayPnl.add(scroller);
        displayTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mainPnl.add(buttonPnl);
        buttonPnl.add(searchBtn);
        buttonPnl.add(quitBtn);
    }

    private class searchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent AE)
        {
            displayTextArea.setText("");
            input = inputTextField.getText();
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + "\\ProductsCreation.txt");
            try
            {
                RandomAccessFile inputFile = new RandomAccessFile(file.toString(), "r");
                while ((display = inputFile.readLine()) != null)
                {
                    if (display.contains(input))
                    {
                        displayTextArea.append(display + "\n");
                    }
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("File not found!");
                ex.printStackTrace();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private class quitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent AE)
        {
            System.exit(0);
        }
    }
}
