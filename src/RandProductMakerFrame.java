import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static java.lang.Double.parseDouble;

public class RandProductMakerFrame extends JFrame {
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel inputPnl;
    JPanel buttonPnl;
    JButton addButton;
    JButton quitButton;
    JLabel titleLbl;
    JLabel nameLbl;
    JLabel descriptionLbl;
    JLabel idLbl;
    JLabel costLbl;
    JLabel countLbl;
    JTextField nameText;
    JTextField descriptionText;
    JTextField idText;
    JTextField costText;
    JTextField countText;
    ActionListener quit = new quitListener();
    ActionListener add = new addListener();

    String name;
    String description;
    String id;
    String outputString;
    double cost;
    int count;

    RandProductMakerFrame() {
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);
        setResizable(false);


        mainPnl = new JPanel();
        titlePnl = new JPanel();
        inputPnl = new JPanel();
        buttonPnl = new JPanel();

        titleLbl = new JLabel("Product Maker");
        titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
        nameLbl = new JLabel("Product Name: ");
        nameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        descriptionLbl = new JLabel("Product Description: ");
        descriptionLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        idLbl = new JLabel("Product ID: ");
        idLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        costLbl = new JLabel("Product Cost: ");
        costLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        countLbl = new JLabel("Products Added: ");
        countLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        nameText = new JTextField();
        nameText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        descriptionText = new JTextField();
        descriptionText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        idText = new JTextField();
        idText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        costText = new JTextField();
        costText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        countText = new JTextField();
        countText.setFont(new Font("Times New Roman", Font.BOLD, 15));

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        quitButton.addActionListener(quit);
        addButton = new JButton("Add");
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addButton.addActionListener(add);

        add(mainPnl);
        mainPnl.setLayout(new GridLayout(3, 1));
        mainPnl.add(titlePnl);
        titlePnl.add(titleLbl);

        mainPnl.add(inputPnl);

        inputPnl.setLayout(new GridLayout(5, 2));
        inputPnl.add(nameLbl);
        nameLbl.setHorizontalAlignment(JLabel.CENTER);
        inputPnl.add(nameText);
        inputPnl.add(descriptionLbl);
        descriptionLbl.setHorizontalAlignment(JLabel.CENTER);
        inputPnl.add(descriptionText);
        inputPnl.add(idLbl);
        idLbl.setHorizontalAlignment(JLabel.CENTER);
        inputPnl.add(idText);
        inputPnl.add(costLbl);
        costLbl.setHorizontalAlignment(JLabel.CENTER);
        inputPnl.add(costText);
        inputPnl.add(countLbl);
        countLbl.setHorizontalAlignment(JLabel.CENTER);
        inputPnl.add(countText);
        countText.setEditable(false);
        countText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        countText.setText(String.valueOf(count));

        mainPnl.add(buttonPnl);
        buttonPnl.add(addButton);
        buttonPnl.add(quitButton);

        }
        private class addListener implements ActionListener {
            public void actionPerformed(ActionEvent AE) {
                if (!(nameText.getText().equals("")) &&
                        !(descriptionText.getText().equals("")) &&
                        !(idText.getText().equals("")) &&
                        !(costText.getText().equals(""))) {
                    if ((nameText.getText().length() <= 35) &&
                            (descriptionText.getText().length() <= 75) &&
                            (idText.getText().length() <= 6)) {
                        name = nameText.getText();
                        description = descriptionText.getText();
                        id = idText.getText();
                        cost = parseDouble(costText.getText());

                        outputString = String.format("\n%-6s %-35s %-75s %.2f", id, name, description, cost);
                        File workingDirectory = new File(System.getProperty("user.dir"));
                        Path file = Paths.get(workingDirectory.getPath() + "\\ProductsCreation.txt");

                        try {
                            RandomAccessFile outputFile = new RandomAccessFile(file.toString(), "rw");
                            outputFile.seek(outputFile.length());
                            outputFile.write(outputString.getBytes());
                            outputFile.close();
                            JOptionPane.showMessageDialog(null, "Product Written to File");
                        } catch (FileNotFoundException e) {
                            System.out.println("File was not found");
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        nameText.setText("");
                        descriptionText.setText("");
                        idText.setText("");
                        costText.setText("");
                        count++;
                        countText.setText(String.valueOf(count));
                    } else {
                        JOptionPane.showMessageDialog(null, "Please ensure that that file inputed correctly fits the following format: \nName: 35 characters \nDescription: 75 characters \nID: 6 characters");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please make sure all fields are filled!");
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