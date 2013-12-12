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

package org.free_erp.client.ui.forms.basic;

import java.util.List;

import javax.swing.JOptionPane;

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.forms.CCatalogDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class CStorageManageWindow  extends CBaseInfoManageWindow
{
	private CStorageInfoDialog dialog;
	private CCatalogDialog dialogAdd;
	public CStorageManageWindow(String title)
	{
		super(title);
		this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
		this.leftPane.setVisible(false);
		this.newTypeButton.setVisible(false);
		this.editTypeButton.setVisible(false);
		this.removeTypeButton.setVisible(false);
		this.importButton.setVisible(false);
		//this.createDbSupport();
		this.initTableColumns();
        this.initDatas();
        this.newButton.setText("新增仓库(&N)");
        this.removeButton.setText("删除仓库(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改仓库信息(&M)");
  
	}
    

	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(80);
		columnModel.addColumn(column);

//		column = new JDataTableColumn();
//		column.setHeaderText("简称");
//		column.setColumnName("shortName");
//		column.setWidth(60);
//		columnModel.addColumn(column);

		/*column = new JDataTableColumn();
		column.setHeaderText("助记符");
		column.setColumnName("code");
		column.setWidth(50);
		columnModel.addColumn(column);*/

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("employee.name");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("联系人");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("contact");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("邮编");
		column.setColumnName("postNumber");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("描述");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("description");
		column.setWidth(180);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
        
        column = new JDataTableColumn();
		column.setHeaderText("备注");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("comments");
		column.setWidth(200);
		columnModel.addColumn(column);


	}

	@Override
	protected void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
		List<Storage> storages = Main.getMainFrame().getObjectsPool().getAllStorages();
		for(Storage storage:storages)
		{
			IDataRow dataRow = new ObjectDataRow(storage, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
        //Main.getMainFrame().getDataBaseObjectPool().refreshAllStorages();
        this.dataSource.sortById(IDataSource.ASC_SORT);
	}
	@Override
	protected void doAdd()
	{
        this.dialog = new CStorageInfoDialog(this, this.dataSource, dbSupport);
        dialog.newDataRow();
		dialog.setVisible(true);
		this.dataSource.clearTempDataRows();
	}
	@Override
	protected void doEdit()
	{
      
            if (this.dataTable.getSelectedRow() < 0)
            {
                MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
                return;
            }
			this.dialog = new CStorageInfoDialog(this, this.dataSource);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
      
    }

    @Override
    protected void updateDatas()
    {
        Main.getMainFrame().getDataBaseObjectPool().refreshAllStorages();
        Main.getMainFrame().getDataBaseObjectPool().refreshStorages();
    }
    
	@Override
	protected void doAddType()
	{

		dialogAdd.setVisible(true);
	}
	@Override
	protected void doEditType()
	{
	}

    @Override
    protected void initTypes() 
    {
    }
    
    @Override
	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("仓库信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
