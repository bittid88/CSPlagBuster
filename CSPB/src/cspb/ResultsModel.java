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

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * ResultsModel class
 * @brief   Represents file pairings and their associated plagiarism rating
 *          on the resultsUI GUI element.
 * @author  Ian
 */
public class ResultsModel extends AbstractTableModel {
    
    ArrayList<RowItem> Data;
    String ColNames[];
    
    ResultsModel(){
        Data = new ArrayList<>();
        ColNames = new String[3];
        ColNames[0] = "File Checked";
        ColNames[1] = "File Compared";
        ColNames[2] = "Plagiarism Score [0 - 10]";
    }
    
    @Override
    public String getColumnName(int col)
    {
        return ColNames[col];
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }
    
    @Override
    public int getRowCount(){
        return Data.size();
    }
    
    @Override
    public boolean isCellEditable( int x, int y )
    {
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int x)
    {
        if (x < 2)
        {
            return String.class;
        }
        else
        {
            return Integer.class;
        }
    }
    
    @Override
    public Object getValueAt(int x, int y)
    {
        if (y == 0)
        {
            return Data.get(x).fileName1(); 
        }
        else if (y == 1)
        {
            return Data.get(x).fileName2();
        }
        else
        {
            return Data.get(x).plagChance();
        }
    }
    
    public void addRow(String fn1, String fn2, int pc, int row)
    {        

        Data.add( new RowItem(fn1,fn2,pc) );
        fireTableRowsInserted(row, row);
    }
    
}


/**
 * RowItem class
 * @brief  Data storage class for a single row in the ResultsModel table model.
 * @author Ian
 */
class RowItem{
    private String fileName1;
    private String fileName2;
    private Integer plagChance;
    
    public String fileName1()
    {
        return fileName1;
    }
    public String fileName2()
    {
        return fileName2;
    }
    public Integer plagChance()
    {
        return plagChance;
    }
    public RowItem(String fn1, String fn2, int pc)
    {
        fileName1 = fn1;
        fileName2 = fn2;
        plagChance = pc;
    }
}
