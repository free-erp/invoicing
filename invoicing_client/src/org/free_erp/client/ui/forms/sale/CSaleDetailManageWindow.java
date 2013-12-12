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
import org.free_erp.client.ui.forms.sale.CSaleSelectDialog;
import java.awt.Frame;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author afa
 */
public class CSaleDetailManageWindow extends CSaleSelectDialog
{
    public CSaleDetailManageWindow(Frame parent)
    {
        super(parent);
        this.setTitle("选择应收款");
    }
//
//    @Override
//    protected void initDatas() {
//         List<Receivable> pos = service.findAllReceivable(Main.getMainFrame().getCompany());
//         Set<Customer> customers = new HashSet<Customer>();
//         List<IDataRow> detailDataRows = new ArrayList<IDataRow>();
//         for(Receivable po:pos)
//         {
//             Customer c = po.getCustomer();
//             if (po.getStatus() == ServiceConstants.UNFINISHED)
//             {
//                 if (!customers.contains(c))
//                 {
//                    customers.add(c);
//                 }
//                 detailDataRows.add(ObjectDataRow.newDataRow(po, "id", null));
//             }
//         }
//         this.detailedTable.getDataSource().insertDataRows(detailDataRows);
//
//         List<IDataRow> dataRows = new ArrayList<IDataRow>();
//         for(Customer customer:customers)
//         {
//             CustomerReceivableAccount account = service.getCustomerReceivableAccount(customer);
//             if (account != null)
//             {
//                 dataRows.add(ObjectDataRow.newDataRow(account, "id", null));
//             }
//         }
//         this.mainTable.getDataSource().insertDataRows(dataRows);
//         if (this.mainTable.getDataSource().getRowCount() > 0)
//         {
//             this.mainTable.setSelectedRow(0);
//         }
//    }

    @Override
    protected void initDetailedTableColumns() {
        ITableColumnModel columnModel = detailedTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("选择");
		column.setColumnName("checked");
		column.setWidth(50);
        column.setValueType(Boolean.class);
        column.setEditable(true);
        //column.setAlignmentX(JLabel.LEFT);
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
		column.setHeaderText("应收日期");
		column.setColumnName("receivableDate");
		column.setWidth(80);
        column.setValueType(Date.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("应收金额");
		column.setColumnName("receivableMoney");
        column.setTotalRowVisible(true);
        column.setValueType(Currency.class);
		column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("未结金额");
		column.setColumnName("remainMoney");
        column.setTotalRowVisible(true);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("本次结算金额");
		column.setColumnName("remainMoney");
        column.setTotalRowVisible(true);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
    }

}
