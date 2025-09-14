import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Notepad extends JFrame implements ActionListener {

    // ----------Components-------------
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, helpMenu;
    JMenuItem newItem, openItem, saveItem, exitItem; // filr menu
    JMenuItem cutItem, copyItem, pasteItem; // edit menu
    JMenuItem aboutItem; // help menu
    
    JComboBox<String> fontBox;
    JComboBox<String> fontSizeBox;
    
 
    
    

    public Notepad() {
    	
    	//-------------------GUI codes--------------------
    	
        // -----Frame setup----
        setTitle("NOTEPAD");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // ------Text Area-------
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // ------Menu bar-------
        menuBar = new JMenuBar();

        // -------File menu--------
        fileMenu = new JMenu("File");
        
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // ---------Edit menu---------
        editMenu = new JMenu("Edit");
        
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        
        //-------------Help menu----------------
        helpMenu = new JMenu("Help");
        
        aboutItem = new JMenuItem("About");
        
        aboutItem.addActionListener(this);
        
        helpMenu.add(aboutItem);
        
        
        //--------------Change fonts-------------------
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        fontBox = new JComboBox<>(fonts);
        
        
        fontBox.addActionListener(e -> {
            String fontName = (String) fontBox.getSelectedItem();
            int fontSize = Integer.parseInt((String) fontSizeBox.getSelectedItem());
            textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
        });

        //--------------Change font sizes-------------------
        String[] sizes = {"8", "10", "12", "14", "16", "18", "20", "24", "28", "32"};
        fontSizeBox = new JComboBox<>(sizes);
        
        fontSizeBox.addActionListener(e -> {
            String fontName = (String) fontBox.getSelectedItem();
            int fontSize = Integer.parseInt((String) fontSizeBox.getSelectedItem());
            textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
        });

        //--------------Change font colors-------------------
        
        JButton colorButton = new JButton("Text Color");
       
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose Text Color", textArea.getForeground());
            if (selectedColor != null) {
                textArea.setForeground(selectedColor);
            }
        });

        // --------Add menus to menu bar-----
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(fontBox);
        menuBar.add(fontSizeBox);
        menuBar.add(colorButton);
        
        setJMenuBar(menuBar);

        setVisible(true);
    }

    // --------------Event handling part/logic-----------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            textArea.setText("");
        } 
        else if (e.getSource() == openItem) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                    textArea.read(br, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file");
                }
            }
        } 
        else if (e.getSource() == saveItem) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()))) {
                    textArea.write(bw);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file");
                }
            }
        } 
        else if (e.getSource() == exitItem) {
            System.exit(0);
        } 
        else if (e.getSource() == cutItem) {
            textArea.cut();
        } 
        else if (e.getSource() == copyItem) {
            textArea.copy();
        } 
        else if (e.getSource() == pasteItem) {
            textArea.paste();
        }
        else if (e.getSource() == aboutItem) {
            JOptionPane.showMessageDialog(this, "Anjana Maitipe [S15469]");
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Notepad(); 
    }  
}




