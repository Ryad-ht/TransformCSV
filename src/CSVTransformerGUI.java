import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CSVTransformerGUI {

    private static String selectedFilePath;
    private static final JFileChooser fileChooser = new JFileChooser();

    public static void main(String[] args) {
        // Create a JFrame to hold everything
        JFrame frame = new JFrame("CSV Transformer");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Button to select a file
        JButton selectFileButton = new JButton("Select CSV File");
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFilePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected file: " + selectedFilePath);
                }
            }
        });

        // Button to execute transformation
        JButton transformButton = new JButton("Transform File");
        transformButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
                    try {
                        String outputFilePath = selectedFilePath.replace(".csv", "_transformed.csv");
                        new CSVTransformer().transformFrenchToAngloSaxon(selectedFilePath, outputFilePath);
                        JOptionPane.showMessageDialog(null, "File transformed successfully: " + outputFilePath);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error transforming file.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No file selected.");
                }
            }
        });

        // Add components to frame
        frame.add(selectFileButton);
        frame.add(transformButton);

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }
}
