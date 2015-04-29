/*
 * The MIT License
 *
 * Copyright 2015 Ian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cspb;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainUI class
 * @brief  Main user interface of the program. Contains buttons to select
 *         directory and run test.
 * @author Ian
 */
public class mainUI extends javax.swing.JFrame {

    private File workingDirectory;
    
    /**
     * Creates new form mainUI
     */
    public mainUI() {
        initComponents();
    }
    
    /**
     * @brief: refresh the table on the mainUI window after changing the working
     *         directory
     */
    public void refresh(){
        ArrayList<FileItem> files = CSPB.testFiles;
        String[] colNames = {"Selected","File Name"};
        DataModel dm = new DataModel(files.size(), 2, colNames);
        Integer i = 0;
        
        for (FileItem f : files)
        {
            dm.setValueAt(new Boolean(true), i, 0);
            dm.setValueAt(f.fileName, i, 1);
            i++;
        }
        this.fileTbl.setModel(dm);
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPnl = new javax.swing.JPanel();
        selectFolderBtn = new javax.swing.JButton();
        runTestBtn = new javax.swing.JButton();
        Pnl = new javax.swing.JPanel();
        filePnl = new javax.swing.JScrollPane();
        fileTbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        selectFolderBtn.setText("Select Folder");
        selectFolderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectFolderBtnMouseClicked(evt);
            }
        });

        runTestBtn.setText("Run Test");
        runTestBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runTestBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PnlLayout = new javax.swing.GroupLayout(Pnl);
        Pnl.setLayout(PnlLayout);
        PnlLayout.setHorizontalGroup(
            PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PnlLayout.setVerticalGroup(
            PnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout controlPnlLayout = new javax.swing.GroupLayout(controlPnl);
        controlPnl.setLayout(controlPnlLayout);
        controlPnlLayout.setHorizontalGroup(
            controlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runTestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPnlLayout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(selectFolderBtn)))
                .addContainerGap())
        );
        controlPnlLayout.setVerticalGroup(
            controlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectFolderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(runTestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        filePnl.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        filePnl.setFocusCycleRoot(true);

        fileTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Selected", "Filename"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fileTbl.getTableHeader().setReorderingAllowed(false);
        filePnl.setViewportView(fileTbl);
        if (fileTbl.getColumnModel().getColumnCount() > 0) {
            fileTbl.getColumnModel().getColumn(0).setResizable(false);
            fileTbl.getColumnModel().getColumn(0).setPreferredWidth(50);
            fileTbl.getColumnModel().getColumn(1).setResizable(false);
            fileTbl.getColumnModel().getColumn(1).setPreferredWidth(350);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filePnl, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filePnl, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Event: OnClick for Select Folder button of Main UI
     * Desc: Open a File Chooser for the user to select present working
     *       directory. Once the working directory has been selected,
     *       create an ArrayList of all non-directory files in the directory.
     * 
     * @param evt unused
     */
    private void selectFolderBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFolderBtnMouseClicked
        if (workingDirectory == null)
        {
            workingDirectory = new File(".");
        }
        JFileChooser selectFolderUI = new JFileChooser();
        selectFolderUI.setCurrentDirectory(workingDirectory);
        selectFolderUI.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = selectFolderUI.showOpenDialog(mainUI.this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            ArrayList<FileItem> fileList = new ArrayList<>();
            workingDirectory = selectFolderUI.getSelectedFile();
            ArrayList<File> files = 
               new ArrayList<>(Arrays.asList(workingDirectory.listFiles()));
            
            for (File f : files)
            {
                if (!f.isDirectory())
                {
                    FileItem fi = null;
                    try {
                        fi = new FileItem(f);
                    } catch (IOException ex) {
                        Logger.getLogger(mainUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fileList.add(fi);
                }
            }
            
            CSPB.testFiles = fileList;
            mainUI.this.refresh();
        }   
    }//GEN-LAST:event_selectFolderBtnMouseClicked

    /**
     * @brief Launches a plagiarism test using the currently selected files.
     * @param evt unused
     */
    private void runTestBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runTestBtnMouseClicked
        CSPB.DM = (DataModel) fileTbl.getModel();
        resultsUI result = new resultsUI();
        result.setVisible(true);
    }//GEN-LAST:event_runTestBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pnl;
    private javax.swing.JPanel controlPnl;
    private javax.swing.JScrollPane filePnl;
    private javax.swing.JTable fileTbl;
    private javax.swing.JButton runTestBtn;
    private javax.swing.JButton selectFolderBtn;
    // End of variables declaration//GEN-END:variables

    
}
