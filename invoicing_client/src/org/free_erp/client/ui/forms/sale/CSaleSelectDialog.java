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



import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.accounting.Payable;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.sale.SaleOrderPo;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.ISaleService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author afa
 */
public class CSaleSelectDialog extends CDialog 
{
//    protected JDataTable mainTable;
    protected JDataTable detailedTable;
    protected IAccountingService service;
    protected CButton okButton = new CButton("确定(&o)");

    public CSaleSelectDialog(Frame parent)
	{
		super(parent);
        initComp();
        service = Main.getServiceManager().getAccountingService();
        initDetailedTableColumns();
//        this.mainTable.addPropertyChangeListener(this);
        initDatas();
        this.setTitle("选择应付款");
	}

    private void initComp()
    {
        this.setSize(800, 500);
        this.setResizable(true);
        this.getContentPane().setLayout(new BorderLayout());
//        this.mainTable = new JDataTable();
        this.detailedTable = new JDataTable();
        this.detailedTable.setEditable(true);
        this.detailedTable.setShowSelectedRow(false);
//        CPanel mainPanel = new CPanel();
        CPanel detailPanel = new CPanel();
//        mainPanel.setPreferredSize(new Dimension(600, 200));
//        detailPanel.setPreferredSize(new Dimension(600, 200));
//        mainPanel.setLayout(new BorderLayout());
//        mainPanel.add("North", new JLabel("待处理列表"));
//        JScrollPane mainSp = new JScrollPane(mainTable);
//        mainPanel.add("Center", mainSp);
        JScrollPane detailedSp = new JScrollPane(detailedTable);
        detailPanel.setLayout(new BorderLayout());
        detailPanel.add("North", new JLabel("待处理信息"));
        detailPanel.add("Center", detailedSp);
//        this.getContentPane().add("North", mainPanel);
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
		column.setColumnName("customer.id");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("customer.name");
		column.setWidth(150);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("客户名称");
		column.setColumnName("customer.name");
		column.setWidth(150);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("customer.name");
		column.setWidth(150);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("帐上金额");
		column.setColumnName("accountMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);


		column = new JDataTableColumn();
		column.setHeaderText("销售金额");
		column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
    }
    protected void initDatas()
    {
         List<IDataRow> dataRows = new ArrayList<IDataRow>();
          ISaleService saleService = Main.getServiceManager().getSaleService();
        List<SaleOrderPo> SaleOrderPos = saleService.getAllSaleOrderForms(Main.getMainFrame().getCompany());
//             CustomerPayableAccount account = service.getCustomerPayableAccount(customer);
             if (SaleOrderPos != null)
             {
                 dataRows.add(ObjectDataRow.newDataRow(SaleOrderPos, "id", null));
             }
         this.detailedTable.getDataSource().insertDataRows(dataRows);
         if (this.detailedTable.getDataSource().getRowCount() > 0)
         {
             this.detailedTable.setSelectedRow(0);
         }
    }
//          ITableModel model = this.detailedTable.getTableModel();
//        ISaleService saleService = Main.getServiceManager().getSaleService();
//        List<SaleOrderPo> SaleOrderPos = saleService.getAllSaleOrderForms(Main.getMainFrame().getCompany());
//        for (SaleOrderPo po : SaleOrderPos) {
//            IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
//            model.insertDataRow(dataRow);
//        }
//        this.dataSource.sortById(IDataSource.DESC_SORT);
//    }
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
