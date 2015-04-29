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

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * CSPB Master Class
 * @author Ian
 * @brief  Contains utility methods and project level static variables.
 */
public class CSPB {
    
    // Project Level Static Variables
    public static ArrayList<FileItem> testFiles;     // List of file items to compare
    public static javax.swing.JTable results;        // Reference to the results Jtable UI element
    public static javax.swing.JProgressBar progress; // Reference to the progress bar UI element
    public static javax.swing.JLabel progressLabel;  // Reference to the progress bar label
    public static DataModel DM;                      // Data model reference (Main UI)
    public static Random RANDOM = new Random();
    
    // Configurable accuracy-related constants
    public static final Integer THRESHOLD = 60;     // Threshold score for alignment
    public static final Integer INDEL = -10;          // Score for insertion/deletion
    public static final Integer MISMATCH = -2;      // Score for mismatched character
    public static final Integer MATCH = 3;          // Score for matched character
    public static final Integer SHRED_SIZE = 30;    // Shred length (in characters)
    public static final Integer FILTER_LEVEL = -1;   // Results under this level of 
                                                     // plagiarism % will not be displayed.
                                                     // [0-100]

    /**
     * reduceItems
     * @brief   Removes unchecked files before comparison begins.
     */
    public static void reduceItems()
    {
        int index = 0;
        ArrayList<FileItem> arr = new ArrayList<>(); // Weird workaround.
        for ( FileItem f : CSPB.testFiles )
        {
            if (DM.getValueAt(index, 0) == Boolean.FALSE)
            {
                arr.add(f);
            }
            index++;
        }
        for (FileItem f : arr)
        {
            CSPB.testFiles.remove(f);
        }
    }
    
   /**
    * align
    * @brief    aligns two strings based on the constant scores defined in CSPB
    * @param s  first String to align
    * @param t  second String to align
    * @return Integer alignment score
    */
    public static Integer align(String s, String t)
    {
        // Create score map for alignment
        int toggle = 1;
        int scoreMap[][] = new int[s.length()+1][t.length()+1];
        // Initialize edges of map
        for (int i = 0; i < s.length(); i++)
        {
            scoreMap[i][0] = 0;
        }
        for (int i = 0; i < t.length(); i++)
        {
            scoreMap[0][i] = 0;
        }
        
        for (int i = 1; i <= s.length(); i++ )
        {
            for (int j = 1; j <= t.length(); j++)
            {
                // Compute scoreMap[i][j]
                if (s.charAt(i-1) == t.charAt(j-1)) // Matching Characters
                {
                    // Match is best option
                    if (scoreMap[i-1][j-1] + MATCH > scoreMap[i-1][j] + INDEL * toggle &&
                        scoreMap[i-1][j-1] + MATCH > scoreMap[i][j-1] + INDEL * toggle)
                    {
                        scoreMap[i][j] = scoreMap[i-1][j-1] + MATCH;
                        toggle = 1;
                    }
                    else if ( scoreMap [i-1][j] > scoreMap[i][j-1] )
                    {
                        scoreMap[i][j] = scoreMap[i-1][j] + INDEL * toggle;
                        toggle = 0;
                    }
                    else
                    {
                        scoreMap[i][j] = scoreMap[i][j-1] + INDEL * toggle;
                        toggle = 0;
                    }
                }   
                else
                {
                    if (scoreMap[i-1][j-1] + MISMATCH > scoreMap[i-1][j] + INDEL * toggle &&
                        scoreMap[i-1][j-1] + MISMATCH > scoreMap[i][j-1] + INDEL * toggle)
                    {
                        scoreMap[i][j] = scoreMap[i-1][j-1] + MISMATCH;
                        toggle = 1;
                    }
                    else if ( scoreMap [i-1][j] > scoreMap[i][j-1] )
                    {
                        scoreMap[i][j] = scoreMap[i-1][j] + INDEL * toggle;
                        toggle = 0;
                    }
                    else
                    {
                        scoreMap[i][j] = scoreMap[i][j-1] + INDEL * toggle;
                        toggle = 0;
                    }
                }
            }
        }
        return scoreMap[s.length()][t.length()];
    }
    
    /**
     * shred
     * @brief breaks a string S into a list of random substrings of size
     *        SHRED_SIZE defined in CSPB
     * @param s
     * @return ArrayList of random substrings.
     */
    public static ArrayList<String> shred(String s)
    {
        // Shred string into a list of 100 random substrings
        ArrayList<String> sL = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            Random r  = CSPB.RANDOM;
            Integer randomIndx = r.nextInt( s.length() - SHRED_SIZE );
            sL.add(s.substring(randomIndx, randomIndx + SHRED_SIZE));
        }
        
        return sL;
    }
    
    /**
     * main
     * Starting point of program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        mainUI mainWindow = new mainUI();
        mainWindow.setVisible(true);
    }
    
}

/**
 * FileItem class
 * @brief   encapsulates the name, handle and data of a text file
 * @author  Ian
 */
class FileItem {
    public String fileName;
    public File fileHandle;
    public String data;
    
    public FileItem(File f) throws IOException{
        fileName = f.getName();
        fileHandle = f;
        
        FileInputStream fin = new FileInputStream(f);
        byte[] buffer = new byte[(int)f.length()];
        new DataInputStream(fin).readFully(buffer);
        String s = new String(buffer, "UTF-8");
        fin.close();
        data = s;
    }
}