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

/**
 * DataModel class
 * @brief   Represents files in a directory on the mainUI GUI element.
 * @author  Ian
 */
public class DataModel extends javax.swing.table.AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;
    
    
    @Override
    public Class getColumnClass(int col)
    {
        return getValueAt(0, col).getClass();
    }
    
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    
    public DataModel(int rows, int cols, String[] colNames)
    {
        data = new Object[rows][cols];
        columnNames = colNames;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public Object getValueAt(int x, int y)
    {
        return data[x][y];
    }
    
    @Override
    public void setValueAt(Object val, int x, int y)
    {
        data[x][y] = val;
        fireTableCellUpdated(x, y);
    }
    
    public boolean isCellEditable(int row, int col)
    {
        return (col == 0);
    }
}
