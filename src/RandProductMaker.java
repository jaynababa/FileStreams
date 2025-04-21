import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker {
    static int records = 0;

    public static void main(String[] args){

        JFrame frame = new JFrame("RandProductMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        // Product input fields
        JTextField nameField = new JTextField(35);
        JTextField descriptionField = new JTextField(75);
        JTextField idField = new JTextField(6);
        JTextField costField = new JTextField(10);
        JTextField recordCountField = new JTextField(5);
        recordCountField.setEditable(false);

        // Add button
        JButton addButton = new JButton("Add Product");

        // Action when the button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                String id = idField.getText();
                double cost = Double.parseDouble(costField.getText());

                // Create a new product
                Product product = new Product(name, description, id, cost);

                // Write product to RandomAccessFile
                try (RandomAccessFile x = new RandomAccessFile("products.dat", "rw")) {
                    x.seek(x.length());  // Move to the end of the file
                    x.writeUTF(product.getId());
                    x.writeUTF(product.getName());
                    x.writeUTF(product.getDescription());
                    x.writeDouble(product.getCost());
                    records++;
                    recordCountField.setText(String.valueOf(records));

                    // Clear the fields after adding the product
                    nameField.setText("");
                    descriptionField.setText("");
                    idField.setText("");
                    costField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Adding components to frame
        frame.add(new JLabel("Product Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Description:"));
        frame.add(descriptionField);
        frame.add(new JLabel("ID:"));
        frame.add(idField);
        frame.add(new JLabel("Cost:"));
        frame.add(costField);
        frame.add(new JLabel("Record Count:"));
        frame.add(recordCountField);
        frame.add(addButton);

        // Display frame
        frame.setVisible(true);
    }
}
