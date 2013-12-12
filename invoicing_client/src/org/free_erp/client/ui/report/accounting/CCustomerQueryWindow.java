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
import com.jdatabeans.beans.CTableComboBox;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.CustomerReceivementDetail;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.logic.IAccountingService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 * 客商往来流水查询
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CCustomerQueryWindow extends CBaseQueryWindow
{
    protected CTableComboBox customerField;
    protected JDataDatePicker beginDateField;
    protected JDataDatePicker endDateField;

    public CCustomerQueryWindow(String title)
    {
        super(title);
    }

    @Override
    protected void clearInfo()
    {
        this.beginDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
        this.customerField.setText("");
    }

    @Override
    protected void select()
    {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        //涉及到现金银行的单据
        List<CustomerReceivementDetail> vos = service.findCustomerReceivementDetails(this.beginDateField.getDate(), this.endDateField.getDate(), (Customer)this.customerField.getSelectedItem(), Main.getMainFrame().getCompany());
        if(vos == null || vos.size() == 0)
        {
            this.clearInfo();
        }
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(CustomerReceivementDetail vo:vos)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
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
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        beginDateField.setSelectedItem("");
        customerField = new CTableComboBox(Main.getMainFrame().getObjectsPool().getCustomerTable() , "name");

        int x = 60;
        int y = 10;
        panel.addComponent(customerField, x, y, 120, 20, "客商", 50);
        panel.addComponent(beginDateField, x + 170 , y , 100, 20, "查询时间", 50);
        panel.addComponent(endDateField, x + 290, y , 100, 20, "至", 20);
        panel.addComponent(selectButton, x + 420, y , 75, 25);
        panel.addComponent(clearButton, x + 500, y , 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("流水号");
		column.setColumnName("id");
		column.setWidth(50);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("客户编号");
		column.setColumnName("customer.number");
		column.setWidth(70);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("客户名称");
		column.setColumnName("customer.name");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(80);
		column.setValueType(Date.class);
        columnModel.addColumn(column);


        column = new JDataTableColumn();
        column.setHeaderText("单据编号");
        column.setColumnName("formNo");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("业务类型");
        column.setColumnName("bussinessType");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("业务员");
        column.setColumnName("employee.name");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("应收金额");
        column.setColumnName("receivableMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("付款金额");
        column.setColumnName("affordMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("结算金额");
        column.setColumnName("clearingMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("帐上金额");
        column.setColumnName("clearingMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("应收余额");
        column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
    }

    protected String getExcelExportTitle()
    {
        return "客商往来流水查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("客商往来流水查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rSupplierQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    
}
