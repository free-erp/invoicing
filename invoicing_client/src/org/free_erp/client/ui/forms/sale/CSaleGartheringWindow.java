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

package org.free_erp.client.ui.forms.sale;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CBaseListWindow;
import org.free_erp.client.ui.main.Main;
public class CSaleGartheringWindow extends CBaseListWindow
{
	public CSaleGartheringWindow(String title)
	{
		super(title);		
		this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");		
		this.initTableColumns();		
	}
	
	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("状态");
		column.setColumnName("status");
		column.setWidth(70);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("id");
		column.setWidth(70);
		columnModel.addColumn(column);
				
		column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("indate");
		column.setWidth(100);
		columnModel.addColumn(column);		
		
		column = new JDataTableColumn();
		column.setHeaderText("客户");
		column.setColumnName("admin");
		column.setWidth(80);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("客户名称");
		column.setColumnName("moneys");
		column.setWidth(80);
		columnModel.addColumn(column);	
		
		column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("admin");
		column.setWidth(80);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("业务员名称");
		column.setColumnName("moneys");
		column.setWidth(80);
		columnModel.addColumn(column);	
		
		column = new JDataTableColumn();
		column.setHeaderText("付款方式");
		column.setColumnName("admin");
		column.setWidth(80);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("付款金额");
		column.setColumnName("admin");
		column.setWidth(80);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("moneys");
		column.setWidth(80);
		columnModel.addColumn(column);	
		
	}
    @Override
    protected void doSearch() {
    }
    @Override
     protected CBaseFormDialog getFormDialog()
	{
        if (formDialog == null)
        {
            formDialog =  new CSaleOrderBackDialog(this, this.dataSource, dbSupport);
        }
        return formDialog;
	}
    
	
	
}