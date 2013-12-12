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
package org.free_erp.client.ui.report.accounting;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.CustomerReceivableAccount;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 * 客商应收余额查询
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CCustomerReceiveBalanceQueryWindow extends CBaseQueryWindow
{
    protected JDataTableComboBox customerField;

    public CCustomerReceiveBalanceQueryWindow(String title)
    {
        super(title);
    }

    @Override
    protected void clearInfo()
    {
        this.customerField.setText("");
    }

    @Override
    protected void select()
    {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        if (this.customerField.getSelectedItem() != null)
        {
            Customer customer = (Customer)this.customerField.getSelectedItem();
             CustomerReceivableAccount account = service.getCustomerReceivableAccount(customer);
             if (account == null)
             {
                 account = new CustomerReceivableAccount();
                 account.setCustomer(customer);
             }
             this.dataTable.getDataSource().clear();
             this.dataTable.getDataSource().insertDataRow(ObjectDataRow.newDataRow(account, "id", null));
             return;
        }
         List<Customer> customers = Main.getMainFrame().getObjectsPool().getCustomers();
        if(customers == null || customers.size() == 0)
        {
            this.clearInfo();
        }
         List<IDataRow> dataRows = new ArrayList<IDataRow>();
         for(Customer customer:customers)
         {
             CustomerReceivableAccount account = service.getCustomerReceivableAccount(customer);
             if (account == null)
             {
                 account = new CustomerReceivableAccount();
                 account.setCustomer(customer);
             }
             dataRows.add(ObjectDataRow.newDataRow(account, "id", null));             
         }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(dataRows);

    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,40));
		panel.setLayout(null);
        customerField = new JDataTableComboBox("", Customer.class,Main.getMainFrame().getObjectsPool().getCustomerTable() , "name");
        int x = 60;
        int y = 10;
        panel.addComponent(customerField, x, y, 120, 20, "客商", 30);
        panel.addComponent(selectButton, x + 140, y, 75, 25);
        panel.addComponent(clearButton, x + 220, y, 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("客户编号");
		column.setColumnName("customer.number");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("客户名称");
		column.setColumnName("customer.name");
		column.setWidth(150);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("帐上金额");
		column.setColumnName("accountMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);


		column = new JDataTableColumn();
		column.setHeaderText("未结金额");
		column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
    }

    protected String getExcelExportTitle()
    {
        return "客商应收余额查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("客商应收余额查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rSupplierPayBalanceQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    
}
