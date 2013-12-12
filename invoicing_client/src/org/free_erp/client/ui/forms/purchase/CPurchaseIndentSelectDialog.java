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
package org.free_erp.client.ui.forms.purchase;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.purchase.PurchaseIndentPo;
import org.free_erp.service.logic.purchase.IPurchaseService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseIndentSelectDialog extends CDialog implements PropertyChangeListener
{
    protected JDataTable mainTable;
    protected JDataTable detailedTable;
    protected static IDbSupport dbSupport;
    protected IPurchaseService service;
    protected CButton okButton = new CButton("确定(&o)");

    public CPurchaseIndentSelectDialog(Frame parent)
	{
		super(parent);
        initComp();
        service = Main.getServiceManager().getPurchaseService();
        initMainTableColumns();
        initDetailedTableColumns();
        this.mainTable.addPropertyChangeListener(this);
        initDatas();
        this.setTitle("选择采购订单");
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

    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("selectedRow"))
        {
            this.refreshDetailRows();
        }
    }

    protected void initMainTableColumns()
    {
        ITableColumnModel columnModel = mainTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("供应商编号");
		column.setColumnName("id");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("供应商名称");
		column.setColumnName("name");
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
		column.setColumnName("number");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(80);
        column.setValueType(Date.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("付款期限");
		column.setColumnName("payTerm");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("employee.name");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("采购金额");
		column.setColumnName("totailMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
    }

    protected void initDatas()
    {
         List<PurchaseIndentPo> pos = service.getAllPurchaseIndentPoForms(Main.getMainFrame().getCompany());
         Set<Customer> customers = new HashSet<Customer>();
         List<IDataRow> detailDataRows = new ArrayList<IDataRow>();
         for(PurchaseIndentPo po:pos)
         {
             Customer c = po.getSupplier();
             if (po.getStatus() == ServiceConstants.FORMAL_STATUS)
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
//             CustomerPayableAccount account = service.getCustomerPayableAccount(customer);
//             if (account != null)
//             {
//                 dataRows.add(ObjectDataRow.newDataRow(account, "id", null));
//             }
             dataRows.add(ObjectDataRow.newDataRow(customer, "id", dbSupport));
         }
         this.mainTable.getDataSource().insertDataRows(dataRows);
//         if (this.mainTable.getDataSource().getRowCount() > 0)
//         {
//             this.mainTable.setSelectedRow(0);
//         }
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
            return (Customer)dataRow.getColumnValue("supplier");
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

    protected void createDbSupport()
	{
		dbSupport = new IDbSupport()
		{
            private IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
			public void delete(Object obj)
			{
                if(obj instanceof PurchaseIndentPo)
				{
				    PurchaseIndentPo p = (PurchaseIndentPo) obj;
                    this.purchaseService.deletePurchaseIndentPoForm(p);
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void save(Object obj)
			{
                if(obj instanceof PurchaseIndentPo)
				{
				    PurchaseIndentPo p = (PurchaseIndentPo) obj;
                    this.purchaseService.savePurchaseIndentPoForm(p);
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void deleteAll(Collection<Object> objs)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}

			public void saveAll(Collection<Object> objs)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
}
