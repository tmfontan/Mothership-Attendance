/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author tylerfontana
 */
public class CustomJFileChooser extends JFrame {

    public File fileToSave;
    public JFileChooser fileChooser;
    
    public String nameAndPath = "";
    public String fileName = "";
    public String[] fileNameSplit;
    public String errorMessage = "";
    
    public int[] shark = {0,0,0};
    public int fileNumber = 1;
    
    ArrayList<String> exportType;
            
    public CustomJFileChooser(ArrayList<String> value) {        
        exportType = value;
    }
    
    public String getNameAndPath() {
        return nameAndPath;
    }
    
    public void createJFileChooserPanel() {

        try {
        
            fileChooser = new JFileChooser()
            {

                @Override
                public void approveSelection()
                {   
                    fileToSave = fileChooser.getSelectedFile();
                    fileName = fileToSave.getName();

                    if (fileToSave.exists())
                    {
                        errorMessage = "<html>A file named <strong>" + fileName + "</strong> already exists.<br>Do you want to replace it?</html>";
                        //errorMessage = "A file named " + fileName + " already exists. Do you want to replace it?";
                        int response = JOptionPane.showConfirmDialog(null, errorMessage, "File Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            //nameAndPath = fileToSave.getAbsolutePath() + exportType.get(1);

                            if (fileName.contains(exportType.get(1))) {
                                nameAndPath = fileToSave.getAbsolutePath();
                            }
                            else {
                                nameAndPath = fileToSave.getAbsolutePath() + exportType.get(1);
                            }

                            super.approveSelection();
                        }
                        else if (response == JOptionPane.NO_OPTION) {
                            System.out.println("Jackal 2");
                            //nameAndPath = "";
                            //super.cancelSelection();
                        }
                    }
                    else {
                        nameAndPath = fileToSave.getAbsolutePath() + exportType.get(1);
                        super.approveSelection();
                    }
                }

                @Override
                public void cancelSelection() {
                    super.cancelSelection();
                }
            };

            fileChooser.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("File Not Replaced");
                }
            });

            fileChooser.setDialogTitle("Specify a File Name and Save Location");
            fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            fileChooser.setFileHidingEnabled(true);

            int exportTypeSize = exportType.size();

            FileNameExtensionFilter filter = null;

            if (exportTypeSize == 2) {
                filter = new FileNameExtensionFilter(exportType.get(0), exportType.get(1));
            }
            else if (exportTypeSize == 3) {
                filter = new FileNameExtensionFilter(exportType.get(0), exportType.get(1), exportType.get(2));
            }
            else if (exportTypeSize == 4) {
                filter = new FileNameExtensionFilter(exportType.get(0), exportType.get(1), exportType.get(2), exportType.get(3));
            }

            fileChooser.setFileFilter(filter);
            fileChooser.addChoosableFileFilter(filter);

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                System.out.println("Jackal 5");

                dispose();
            }
            else if (userSelection == JFileChooser.CANCEL_OPTION) {
                System.out.println("Jackal 3");
                //dispose();
            }
            else {
                System.out.println("Jackal 4");
            }
        } 
        catch (NullPointerException e) { 
            System.out.println("JFileSaver Exception Caught.");
        }
    }
}
