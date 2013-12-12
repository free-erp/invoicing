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
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
/**
 *
 * @author afa
 */
public class ExcelUtilities
{
    private static SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formator2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String title = "";//excel title
	public static void exportTableToXls(JDataTable table, String fileName) throws IOException,WriteException
	{
		exportTableToXls(table, new File(fileName));
	}
	public static void exportTableToXls(JDataTable table, File file) throws IOException,WriteException
	{
		WritableWorkbook book = Workbook.createWorkbook(file);
		WritableSheet sheet = book.createSheet("Sheet1", 0);
        Label titlelabel = new Label(0, 0, title);
        sheet.addCell(titlelabel);
		ITableColumnModel columnModel = table.getTableColumnModel();
		ITableModel model = table.getTableModel();
		Enumeration<JDataTableColumn> tableColumns = columnModel.getTableColumns();
		int r = 1;
		int c = 0;
		while(tableColumns.hasMoreElements())
		{
			JDataTableColumn tableColumn = tableColumns.nextElement();
			Label label = new Label(c, r, tableColumn.getHeaderText());
			//label.setCellFormat(cf)
			sheet.addCell(label);
			sheet.setColumnView(c, tableColumn.getWidth()/7);
			String columnName = tableColumn.getColumnName();
            Class<?> clazz = tableColumn.getValueType();
            for(int i = 2; i <= model.getRowCount() + 1; i++)
			{
				Object v = model.getCellValue(i - 2, columnName);
				if (v != null)
				{
					sheet.addCell(generateCell(c, i, v, clazz));
				}
			}
			c++;
		}
		book.write();
		book.close();
	}

	private static WritableCell generateCell(int column, int row, Object value, Class cls)
	{
        if (cls == Currency.class || cls == Double.class || cls == Integer.class || cls == Float.class || cls == Long.class || cls == Short.class || cls == Byte.class)
		{
			return new jxl.write.Number(column, row, Double.parseDouble(value.toString()));
		}//here add Timellll
        else if (cls == Timestamp.class)
        {
             return new Label(column, row, formator2.format((Date)value));
        }
        else if (cls == Date.class)//short date!
        {
            return new Label(column, row, formator.format((Date)value));
        }
		else
		{
			return new Label(column, row, value.toString());
		}		
	}
}
