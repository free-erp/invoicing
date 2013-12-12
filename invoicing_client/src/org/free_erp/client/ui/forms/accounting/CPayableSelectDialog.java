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

package org.free_erp.client.ui.forms.accounting;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.DataRowRolledEvent;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.Payable;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.logic.IAccountingService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author afa
 */
public class CPayableSelectDialog extends CDialog implements PropertyChangeListener
{
    protected JDataTable mainTable;
    protected JDataTable detailedTable;
    protected IAccountingService service;
    protected CButton okButton = new CButton("确定(&o)");
    
    public CPayableSelectDialog(Frame parent)
	{
		super(parent);
        initComp();
        service = Main.getServiceManager().getAccountingService();
        initMainTableColumns();
        initDetailedTableColumns();
        this.mainTable.addPropertyChangeListener(this);
        initDatas();
        this.setTitle("选择应付款");
	}
    
    private void initComp()
    {
        this.setSize(800, 500);
        this.setResizable(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.mainTable = new JDataTable();
        this.detailedTable = new JDataTable();
        this.detailedTable.setEditable(true);
        this.detailedTable.setShowSelectedRow(false);
        CPanel mainPanel = new CPanel();
        CPanel detailPanel = new CPanel();
        mainPanel.setPreferredSize(new Dimension(600, 200));
        detailPanel.setPreferredSize(new Dimension(600, 200));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("North", new JLabel("待处理列表"));
        JScrollPane mainSp = new JScrollPane(mainTable);
        mainPanel.add("Center", mainSp);
        JScrollPane detailedSp = new JScrollPane(detailedTable);
        detailPanel.setLayout(new BorderLayout());
        detailPanel.add("North", new JLabel("待处理信息"));
        detailPanel.add("Center", detailedSp);
        this.getContentPane().add("North", mainPanel);
        this.getContentPane().add("Center", detailPanel);
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(800, 50));
        bottomPanel.addComponent(okButton, 680, 10, 80, 25);
        this.setDefaultButton(okButton);
        this.setCancelButton(okButton);
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                setVisible(false);
            }
        });
        detailPanel.add("South", bottomPanel);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("selectedRow"))
        {
            this.refreshDetailRows();
        }
    }
    protected void initMainTableColumns()
    {
        ITableColumnModel columnModel = mainTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("客户编号");
		column.setColumnName("customer.id");
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
    protected void initDetailedTableColumns()
    {
        ITableColumnModel columnModel = detailedTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("选择");
		column.setColumnName("checked");
		column.setWidth(50);
        //column.setAlignmentX(JLabel.LEFT);
        column.setValueType(Boolean.class);
        column.setEditable(true);
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

        column = new JDataTableColumn();
		column.setHeaderText("本次结算金额");
		column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
    }

    


    protected void initDatas()
    {
         List<Payable> pos = service.findAllPayables(Main.getMainFrame().getCompany());
         Set<Customer> customers = new HashSet<Customer>();
         List<IDataRow> detailDataRows = new ArrayList<IDataRow>();
         for(Payable po:pos)
         {
             Customer c = po.getCustomer();
             if (po.getStatus() == ServiceConstants.UNFINISHED)
             {
                 if (!customers.contains(c))
                 {
                    customers.add(c);
                 }
                 //detailDataRows.add(ObjectDataRow.newDataRow(new PayableExt(po), "id", null));
                 detailDataRows.add(ObjectDataRow.newDataRow(po, "id", null));

             }
         }
         this.detailedTable.getDataSource().insertDataRows(detailDataRows);
         
         List<IDataRow> dataRows = new ArrayList<IDataRow>();
         for(Customer customer:customers)
         {
             CustomerPayableAccount account = service.getCustomerPayableAccount(customer);
             if (account != null)
             {
                 dataRows.add(ObjectDataRow.newDataRow(account, "id", null));
             }
         }
         this.mainTable.getDataSource().insertDataRows(dataRows);
         if (this.mainTable.getDataSource().getRowCount() > 0)
         {
             this.mainTable.setSelectedRow(0);
         }
    }

    private void refreshDetailRows()
    {
        IDataSource dataSource = this.detailedTable.getDataSource();
        Customer customer = this.getCustomer();
        //dataSource.setMutipleEqualFilter("customer", new Object[]{customer});
        dataSource.setFiltered(true);
        dataSource.setFilter("customer==?", new Customer[]{customer});
        /*
        IDataSource dataSource = this.detailedTable.getDataSource();
        dataSource.clear();
        Customer customer = this.getCustomer();
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        if (customer != null)
        {
            List<Payable> pos = service.findPayables(customer);
            for(Payable payable:pos)
            {
                if (payable.getStatus() == ServiceConstants.UNFINISHED)
                {
                    dataRows.add(ObjectDataRow.newDataRow(payable, "id", null));
                }
            }
        }
        dataSource.insertDataRows(dataRows);
         */
    }

    public Customer getCustomer()
    {
        IDataRow dataRow = this.mainTable.getSelectedDataRow();//getDataSource().getCurrentDataRow();
        if (dataRow != null)
        {
            return (Customer)dataRow.getColumnValue("customer");
        }
        return null;
    }

    public List<?> getDetailObjects()
    {
        List<IDataRow> dataRows = this.detailedTable.getDataSource().getDataRows();
        List pos = new ArrayList();
//        Customer customer = this.getCustomer();
        for(IDataRow dataRow:dataRows)
        {
            Object obj = dataRow.getColumnValue("checked");
            if (obj != null)
            {
                if ((Boolean)obj)
                {
                    Object p = ((ObjectDataRow)dataRow).getUserObject();
//                    if (dataRow.getColumnValue("customer") == customer)
//                    {
                        pos.add(p);
//                    }
                }
            }
        }
        return pos;
    }

}
