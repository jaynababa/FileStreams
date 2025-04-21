import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductSearch {

    public static void main(String[] args) {

        JFrame frame = new JFrame("RandProductSearch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField searchField = new JTextField(35);
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JButton searchButton = new JButton("Search");

        // Action when search button is clicked
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText().trim();
                resultArea.setText("");

                try (RandomAccessFile ranFile = new RandomAccessFile("products.dat", "r")) {
                    String name;
                    String description;
                    String id;
                    double cost;

                    while (ranFile.getFilePointer() < ranFile.length()) {
                        id = ranFile.readUTF();
                        name = ranFile.readUTF();
                        description = ranFile.readUTF();
                        cost = ranFile.readDouble();

                        // Check if the product name contains the search term
                        if (name.toLowerCase().contains(searchName.toLowerCase())) {
                            resultArea.append(String.format("ID: %s, Name: %s, Description: %s, Cost: %.2f\n", id, name, description, cost));
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Adding components to frame
        frame.add(new JLabel("Search Product Name:"));
        frame.add(searchField);
        frame.add(searchButton);
        frame.add(new JScrollPane(resultArea));

        // Display frame
        frame.setVisible(true);
    }
}

