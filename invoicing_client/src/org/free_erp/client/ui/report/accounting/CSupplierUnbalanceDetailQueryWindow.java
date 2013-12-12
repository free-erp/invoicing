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

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.accounting.Payable;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 供应商未结明细查询
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CSupplierUnbalanceDetailQueryWindow extends CCustomerUnbalanceDetailQueryWindow
{
    public CSupplierUnbalanceDetailQueryWindow(String title)
    {
        super(title);
    }

    @Override
    protected void select()
    {
        List<Payable> pos = Main.getServiceManager().getAccountingService().findPayable(this.beginDateField.getDate(), this.endDateField.getDate(), (Customer)this.customerField.getSelectedItem(), (Employee)this.adminField.getSelectedItem(), Main.getMainFrame().getCompany());
                //Main.getServiceManager().getAccountingService().findAllPayables(Main.getMainFrame().getCompany());
        List<IDataRow> detailDataRows = new ArrayList<IDataRow>();
        if(pos == null || pos.size() == 0)
        {
            this.clearInfo();
        }
        for(Payable po:pos)
        {
             if (po.getStatus() == ServiceConstants.UNFINISHED)
             {
                 detailDataRows.add(ObjectDataRow.newDataRow(po, "id", null));
             }
        }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(detailDataRows);
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = dataTable.getTableColumnModel();
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
		column.setHeaderText("单据编号");
		column.setColumnName("formNo");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(80);
        column.setValueType(Date.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("employee.name");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("应付日期");
		column.setColumnName("payableDate");
        column.setValueType(Date.class);
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("应付金额");
		column.setColumnName("payableMoney");
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
        return "供应商未结明细查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("供应商未结明细查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rSupplierUnbalanceDetail.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    
}
