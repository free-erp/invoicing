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
package org.free_erp.client.ui.report.basic;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.basic.CCustomerDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.logic.ICustomerService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CCustomersQueryWindow extends CBaseQueryWindow
{
    protected JDataComboBox catalogField;
    protected JDataTableComboBox numberField;
    protected CField nameField;
    protected CField shortNameField;
    protected CField helpCodeField;
    public CCustomersQueryWindow(String title)
    {
        super(title);
    }

    

    protected void clearInfo()
    {
        this.catalogField.setSelectedItem("");
        this.numberField.setSelectedItem(null);
        this.nameField.setText("");
        this.shortNameField.setText("");
        this.helpCodeField.setText("");
    }

    protected void select()
    {
        DataBaseQueryVO vo = new DataBaseQueryVO(Main.getMainFrame().getCompany());
     
        Object obj = null;
        obj = this.catalogField.getSelectedItem();
        if(obj != null && obj instanceof CustomerCatalog)
        {
            vo.setCatalog(((CustomerCatalog)obj).getName());
        }
        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Customer)
            {
                vo.setNumber(((Customer)obj).getNumber());
            }
        }
        if(this.nameField.getText() != null && !this.nameField.getText().trim().equals(""))
        {
            vo.setName(this.nameField.getText());
        }
        if(this.shortNameField.getText() != null && !this.shortNameField.getText().trim().equals(""))
        {
            vo.setShortName(this.shortNameField.getText());
        }
        if(this.helpCodeField.getText() != null && !this.helpCodeField.getText().trim().equals(""))
        {
            vo.setCode(this.helpCodeField.getText());
        }
        ITableModel model = this.dataTable.getTableModel();
        ICustomerService service = Main.getServiceManager().getCustomerService();
        this.dataSource.clear();
        List<Customer> custs = service.findCustomers(vo);
        List<Customer> customers = new ArrayList();
        for(Customer c : custs)
        {
//            if(c.getCustomerType() == null || c.getCustomerType().equals("客户") || c.getCustomerType().equals("客户及供应商"))
//            {
                customers.add(c);
//            }
        }
        if(customers == null || customers.size() == 0)
        {
            this.clearInfo();
            return;
        }
        for(Customer c : customers)
        {
            IDataRow dataRow = new ObjectDataRow(c, "id",dbSupport);
            model.insertDataRow(dataRow);
        }
    }

    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,80));
		panel.setLayout(null);
        catalogField = new JDataComboBox(CustomerCatalog.class,Main.getMainFrame().getObjectsPool().getCustomerCatalogs());
        catalogField.setSelectedItem("");
        numberField = new JDataTableComboBox("", Customer.class,Main.getMainFrame().getObjectsPool().getCustomerTable(), "number");
        nameField = new CField();
        shortNameField = new CField();
        helpCodeField = new CField();

        int x = 60;
        int y = 15;
        panel.addComponent(catalogField, x, y, 120, 20, "类别", 50);
        panel.addComponent(numberField, x + 170, y, 100, 20, "客户编号", 50);
        panel.addComponent(nameField, x + 320, y, 120, 20, "客户名称", 50);
        panel.addComponent(shortNameField, x , y + 25, 120, 20, "客户简称", 50);
        panel.addComponent(helpCodeField, x + 170, y + 25, 100, 20, "助记码", 50);
        panel.addComponent(selectButton, x + 440, y + 25, 75, 25);
        panel.addComponent(clearButton, x + 520, y + 25, 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("类别编号");
		column.setColumnName("catalog.number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("类别名称");
		column.setColumnName("catalog.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("联系人");
		column.setColumnName("contactName");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("手机");
		column.setColumnName("mobile");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("传真");
		column.setColumnName("fax");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("邮箱");
		column.setColumnName("email");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("助记码");
		column.setColumnName("code");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("客户信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/CustomerManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    protected String getExcelExportTitle()
    {
        return "客户信息查询";
    }

//    protected void doShowDetail()
//    {
//        if (this.dataTable.getSelectedRow() < 0)
//        {
//            return;
//        }
//        this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
//        CCustomerDialog dialog = new CCustomerDialog(Main.getMainFrame(), this.dataSource,dbSupport);
//        dialog.saveAndNewButton.setEnabled(true);
//        dialog.saveButton.setEnabled(true);
//        dialog.setVisible(true);
//        this.dataSource.clearTempDataRows();
//    }
}
