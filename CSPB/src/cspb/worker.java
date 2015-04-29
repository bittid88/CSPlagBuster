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

import java.io.IOException;
import java.util.List;

/**
 * @brief  worker for the resultsUI frame
 * @author Ian
 */
public class worker extends javax.swing.SwingWorker<Integer, Integer>{
    
    /**
     * @brief  Perform background task (Local Alignment of testing files)
     * @return Integer 0 on success
     * @throws IOException File Not Found or Read Failure
     */
    @Override
    public Integer doInBackground() throws IOException 
    {
        // Cull unneccary files
        CSPB.reduceItems();
        // Set up table model
        ResultsModel rm = new ResultsModel();
        CSPB.results.setModel(rm);

        Integer progress = 0;
        // Do plagiarism comparison
        for (FileItem f : CSPB.testFiles)
        {
            /**
             * Each string of file data is shredded into 100 shreds of constant
             * length. These shreds are then aligned against each other file
             * and scored. High scoring alignment indicates plagiarism. If the
             * alignment score of a shred is above the threshold set in CSPB,
             * add a point to the plagiarism chance. Overall plagiarism chance
             * is the number of shreds that were scored above the threshold.
             */
            List<String> shreds = CSPB.shred(f.data);
            
            for (FileItem g : CSPB.testFiles)
            {   
                progress++;
                if (!f.equals(g))   // Don't compare a file to itself.
                {
                    Integer plagChance = 0;
                    double avgScore = 0.0;
                    for ( String shred : shreds )
                    {
                        Integer alignScore = CSPB.align(shred, g.data);
                        if (alignScore > CSPB.THRESHOLD )
                        {
                            plagChance++;
                        }
                        avgScore += alignScore;
                    }
                    // DEBUG
                    System.out.println("Average alignment of " + f.fileName
                            + " and " + g.fileName + ": " + avgScore/100);
                    // Update Model
                    if( plagChance > CSPB.FILTER_LEVEL )
                    {
                        rm.addRow(f.fileName, g.fileName, plagChance/10, 0);
                        CSPB.results.setEditingRow( CSPB.results.getEditingRow() +1 );
                    }
                    // Update progress bar
                    Integer i = CSPB.testFiles.size() * CSPB.testFiles.size();
                    publish(progress*100 / i );
                }
            }
        }
        return 0;
    }
    
    /**
     * @brief Progress bar helper function
     * @param chunks last updates from publish
     */
    public void process(List<Integer> chunks){
        int i = chunks.get(chunks.size()-1);
        CSPB.progress.setValue(i);
    }
    
    /**
     * @brief On completion of background task, hide the progress bar.
     */
    protected void done(){
        // Remove progress bar
        CSPB.progress.setVisible(false);
        CSPB.progressLabel.setVisible(false);
    }
}
