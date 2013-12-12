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
package org.free_erp.client.ui.forms.system;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.main.Main;
/**
 *
 * @author Administrator
 */
public class CRoleManageWindow extends CBaseInfoManageWindow
{
        private CRoleManageDialog  dialog;
	public CRoleManageWindow(String title)
	{
		super(title);
                this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
		this.leftPane.setVisible(false);
		this.newTypeButton.setVisible(false);
		this.editTypeButton.setVisible(false);
		this.removeTypeButton.setVisible(false);
		this.importButton.setVisible(false);
		this.initTableColumns();

	}

	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("id");
		column.setWidth(70);
		columnModel.addColumn(column);
		
		column = new JDataTableColumn();
		column.setHeaderText("角色名称");
		column.setColumnName("name");
		column.setWidth(70);
		columnModel.addColumn(column);
				
		column = new JDataTableColumn();
		column.setHeaderText("权限简介");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);
		
              column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
                column.setValueType(Boolean.class);
		columnModel.addColumn(column);
	}


    protected void initTypes() {
      
    }
	protected void doAdd()
	{
            // TODO Auto-generated method stub
		if (this.dialog == null)
		{
			this.dialog = new CRoleManageDialog(this,dataSource);
		}
		//this.dataSource.newDataRow();
        dialog.newDataRow();
		dialog.setVisible(true);
		this.dataSource.clearTempDataRows();
	}

	protected void doEdit()
	{

	}

	protected void doRemove()
	{
		// TODO Auto-generated method stub
    }
	protected void doAddType()
	{

	}

	protected void doEditType()
	{

	}



	protected void doImport()
	{

	}



}
