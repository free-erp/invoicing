/*
 * Copyright 2013, TengJianfa , and other individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.free_erp.client.ui.util;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 *
 * @author afa
 */
public class ExcelImporter
{
	private Workbook book;
	private Sheet sheet;
    public int rowSize;
	public ExcelImporter(String fileName) throws IOException,BiffException
	{
		book = Workbook.getWorkbook(new File(fileName));
		sheet = book.getSheet(0);
        this.rowSize = sheet.getRows();
	}

	public ExcelImporter(File file) throws IOException,BiffException
	{
		book = Workbook.getWorkbook(file);
		sheet = book.getSheet(0);
        this.rowSize = sheet.getRows();
	}

	public String getCellText(int column, int row ,String dataType)
	{
        if(row < this.rowSize)
        {
            Cell cell = sheet.getCell(column, row);
            if (cell == null)
            {
                if(dataType.equals("S"))
                {
                    return "";
                }
                else
                {
                    return "0";
                }
            }
            CellType type = cell.getType();
            if (type == CellType.EMPTY || type == CellType.ERROR)
            {
                if(dataType.equals("S"))
                {
                    return "";
                }
                else
                {
                    return "0";
                }
            }
            return cell.getContents();
        }
		if(dataType.equals("S"))
        {
            return "";
        }
        else
        {
            return "0";
        }
	}

	public boolean isEmpty(int column, int row)
	{
		Cell cell = sheet.getCell(column, row);
		if (cell == null)
		{
			return true;
		}
		CellType type = cell.getType();
		if (type == CellType.EMPTY || type == CellType.ERROR)
		{
			return true;
		}

		return false;
	}

    public boolean isEmptyRow(int columnSize, int row)
	{
        boolean flag = false;

        return flag;
    }

    public boolean isStop(int row)
    {
        if(row < this.rowSize)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

	public void close()
	{
		book.close();
	}
}
