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

        column.setHeaderText("�û�����");
        column.setColumnName("userName");
        column.setWidth(150);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("Ա������");
        column.setColumnName("name");
        column.setWidth(150);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�ɹ�Ա��ɫ");
        column.setColumnName("purchaseRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("����Ա��ɫ");
        column.setColumnName("saleRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�ֹ�Ա��ɫ");
        column.setColumnName("storageRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������Ա��ɫ");
        column.setColumnName("financeRole");
        column.setWidth(100);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�������Ȩ��");
        column.setColumnName("checkPermission");
        column.setWidth(80);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�鿴�۸�Ȩ��");
        column.setColumnName("pricePermission");
        column.setWidth(80);
        column.setValueType(Boolean.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("ͣ��");
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
                MessageBox.showErrorDialog(this, "û���κ������б�ѡ��!");
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
                MessageBox.showErrorDialog(this, "�޷��޸ĳ�������ԱȨ��!\n");
                return;
            }
     

    }

    @Override
    protected void doRemove() {
        if (this.dataTable.getSelectedRow() < 0) {
            MessageBox.showMessageDialog(this, "û�������б�ѡ��!");
            return;
        }
        if (MessageBox.showQuestionDialog(this, "��ȷ��Ҫɾ����ǰ��¼��?") == 0) {
            IDataRow dataRow = this.dataTable.getSelectedDataRow();
            try {
                if (!dataRow.getColumnValue("userName").equals("admin")) {
                    if(!dataRow.getColumnValue("userName").equals(Main.getMainFrame().getUser().getUserName())){
                    this.dataSource.removeDataRow(dataRow);
                    this.dataSource.clearTempDataRows();
                    } else {
                    MessageBox.showErrorDialog(this, "�����޷�ɾ���Լ�!����ϵ����Ա\n");
                    return;
                }
                } else {
                    MessageBox.showErrorDialog(this, "�޷�ɾ����������Ա!\n");
                    return;
                }
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "�޷�ɾ��,���ݿ���Щ��¼����û���أ������ѡ��ͣ��!");
                
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
