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

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.logic.ISystemManageService;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 * Changer liufei
 */
public class CUserManageWindow extends CBaseInfoManageWindow {

    private CUserManageDialog dialog;

    public CUserManageWindow(String title) {
        super(title);
        this.dataSource = this.dataTable.getDataSource();
        this.dataSource.setKeyColumnName("id");
        this.leftPane.setVisible(false);
        this.newTypeButton.setVisible(false);
        this.editTypeButton.setVisible(false);
        this.removeTypeButton.setVisible(false);
        this.importButton.setVisible(false);
        this.printButton.setVisible(false);
        this.stopButton.setVisible(false);
        this.activeButton.setVisible(false);
        this.initTableColumns();
        this.initDatas();
    }

    public void initTableColumns() {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();

        column.setHeaderText("用户名称");
        column.setColumnName("userName");
        column.setWidth(150);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("员工名称");
        column.setColumnName("name");
        column.setWidth(150);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("采购员角色");
        column.setColumnName("purchaseRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("销售员角色");
        column.setColumnName("saleRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓管员角色");
        column.setColumnName("storageRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("财务人员角色");
        column.setColumnName("financeRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("具有审核权限");
        column.setColumnName("checkPermission");
        column.setWidth(80);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("查看价格权限");
        column.setColumnName("pricePermission");
        column.setWidth(80);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);

    }

    @Override
    protected void doAdd() {
        if (this.dialog != null) {
            this.dialog.setVisible(false);
        }
        this.dialog = new CUserManageDialog(this, this.dataSource);
        dialog.newDataRow();
        dialog.setVisible(true);
        this.dataSource.clearTempDataRows();
    }

    @Override
    protected void doEdit() {
       
            if (this.dataTable.getSelectedRow() < 0) {
                MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
                return;
            }
            IDataRow dataRow = this.dataTable.getSelectedDataRow();
            ObjectDataRow oDataRow = (ObjectDataRow) dataRow;
            User user = (User) oDataRow.getUserObject();
            this.dialog = new CUserManageDialog(this, this.dataSource, user);
            //temp

            if (!this.dataTable.getSelectedDataRow().getColumnValue("userName").equals("admin")) {
                this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
                dialog.stafferNField.setEnabled(false);
                dialog.usernameField.setEnabled(false);
//                dialog.enterPswField.setEnabled(false);
//                dialog.validatePswField.setEnabled(false);
                dialog.setVisible(true);
            } else {
                MessageBox.showErrorDialog(this, "无法修改超级管理员权限!\n");
                return;
            }
     

    }

    @Override
    protected void doRemove() {
        if (this.dataTable.getSelectedRow() < 0) {
            MessageBox.showMessageDialog(this, "没有数据行被选中!");
            return;
        }
        if (MessageBox.showQuestionDialog(this, "您确信要删除当前记录吗?") == 0) {
            IDataRow dataRow = this.dataTable.getSelectedDataRow();
            try {
                if (!dataRow.getColumnValue("userName").equals("admin")) {
                    if(!dataRow.getColumnValue("userName").equals(Main.getMainFrame().getUser().getUserName())){
                    this.dataSource.removeDataRow(dataRow);
                    this.dataSource.clearTempDataRows();
                    } else {
                    MessageBox.showErrorDialog(this, "本人无法删除自己!请联系管理员\n");
                    return;
                }
                } else {
                    MessageBox.showErrorDialog(this, "无法删除超级管理员!\n");
                    return;
                }
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "无法删除,数据库有些记录与该用户相关，你可以选择停用!");
                
            }
        }
    }

    protected void initTypes() {
    }

    @Override
    protected void initDatas() {
        ITableModel model = this.dataTable.getTableModel();
        ISystemManageService systemManageService = Main.getServiceManager().getSystemManageService();
        List<User> users = systemManageService.findAllUser(Main.getMainFrame().getCompany());
        for (User user : users) {
            IDataRow dataRow = new ObjectDataRow(user, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
    }
}
