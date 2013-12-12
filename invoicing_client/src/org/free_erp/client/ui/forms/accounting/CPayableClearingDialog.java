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

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataDisplayMoneyField;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataMoneyField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.DataTableCellEditor;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseAccountingMainDetailDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.Payable;
import org.free_erp.service.entity.accounting.PayableClearing;
import org.free_erp.service.entity.accounting.PayableClearingDetail;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author afa
 */
public class CPayableClearingDialog  extends CBaseAccountingMainDetailDialog implements ValueChangedListener
{
    private JDataTableComboBox customerField;
    //private JDataField customerField;
    private JDataDatePicker formDateField;
    private JDataField commentsField;
    private JDataTableComboBox paymentTypeField;

    private JDataTableComboBox payAccountField;
    private JDataDisplayMoneyField accountingMoney;

    private JDataNumberField payMoney;
    //private JDataNumberField freeMoney;
    private JDataDisplayMoneyField totalMoney;
//    private JDataDisplayMoneyField leaveMoney;
    //private JDataDisplayMoneyField changeMoney;

	public CPayableClearingDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
        initCs();
	}
	
	public CPayableClearingDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		initCs();        
	}

	private void initCs()
	{
		this.setTitle("付款结算单");
        table.setEditable(true);
        this.setSize(900, 600);
	}

	protected void initColumns()
	{
		ITableColumnModel columnModel = table.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("payable.formNo");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("payable.formDate");
		column.setWidth(80);
        column.setValueType(Date.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("摘要");
		column.setColumnName("remarks");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("应付金额");
		column.setColumnName("payable.payableMoney");
        column.setValueType(Currency.class);
        column.setTotalRowVisible(true);
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("应付余额");
		column.setColumnName("affordRemainMoney");
        column.setValueType(Currency.class);
        column.setTotalRowVisible(true);
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("本次结算金额");
		column.setColumnName("clearingMoney");
        column.setValueType(Currency.class);
        column.setTotalRowVisible(true);
        column.setEditable(true);
		columnModel.addColumn(column);

//        column = new JDataTableColumn();
//		column.setHeaderText("调帐金额");
//		column.setColumnName("ajustMoney");
//        column.setValueType(Currency.class);
//        column.setEditable(true);
//		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(150);
		columnModel.addColumn(column);
	}

	@Override
	public void newDataRow()
	{
        PayableClearing po = new PayableClearing();
		po.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
		dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
		this.dataSource.insertDataRow(dataRow);
		//this.dataSource.last();
	}

	public CPanel getMainPanel()
	{
		CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(900, 120));
		topPanel.setLayout(null);
        idField = new JDataField("number",String.class,this.dataSource);
        idField.setUpdateToDataSource(false);
        idField.setEditable(false);
		formDateField = new JDataDatePicker("formDate", this.dataSource);
//        employeeField = new JDataTableComboBox("employee", Employee.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
//        employeeField.setRequired("经手人", true);
//        departmentField = new JDataComboBox("department", String.class, this.dataSource);
        customerField =  new JDataTableComboBox("customer", Customer.class, this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerTable(), "name");
        customerField.addValueChangedListener(this);
        customerField.setRequired("客户信息", true);
        //customerField =  new JDataField("customer", Customer.class, this.dataSource);
        paymentTypeField = new JDataTableComboBox("affordType", AffordType.class, this.dataSource, Main.getMainFrame().getObjectsPool().getAffordTypeTable(), "name");
        paymentTypeField.setRequired("付款方式", true);
        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);

		int y = 10;
		int x = 80;
        topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        topPanel.addComponent(formDateField, x + 240, y, 100, 20, "发生日期", 60);
        y += ROW_SPAN;
        topPanel.addComponent(customerField, x, y, 340, 20, "往来单位", 60);
        y += ROW_SPAN;
        topPanel.addComponent(employeeField, x, y, 150, 20, "经手人", 60);
        topPanel.addComponent(departmentField, x + 240, y, 100, 20, "部门", 60);
        topPanel.addComponent(paymentTypeField, x + 440, y, 150, 20, "付款方式", 60);
        y += ROW_SPAN;
		topPanel.addComponent(commentsField, x, y, 590, 20, "备注", 60);
		return topPanel;
	}

    protected CPanel getBottomPane()
    {
        CPanel bPanel = new CPanel();
        bPanel.setPreferredSize(new Dimension(700, 50));
		bPanel.setLayout(null);

        payMoney = new JDataNumberField("affordMoney", Double.class, this.dataSource);
        payMoney.setEditable(false);

        //freeMoney = new JDataNumberField();
        payAccountField = new JDataTableComboBox("subject", Subject.class, this.dataSource, Main.getMainFrame().getObjectsPool().getBankSubjectTable(), "name");
        //payAccountField.setRequired("付款账户", true);
        payAccountField.addValueChangedListener(this);
        accountingMoney = new JDataMoneyField("accountMoney", this.dataSource, this.dbSupport);
        totalMoney = new JDataMoneyField("totalMoney", this.dataSource, this.dbSupport);
        //leaveMoney  = new JDataDisplayMoneyField(JDataDisplayMoneyField.COMMON_MONEY, "remainMoney", this.dataSource);
        //changeMoney = new JDataDisplayMoneyField(JDataDisplayMoneyField.COMMON_MONEY, "ajustMoney", this.dataSource);
		int y = 10;
		int x = 80;
//         bPanel.addComponent(totailMoney, x + 700, y, 100, 20, "金额合计", 60);
////        bPanel.addComponent(changeMoney, x + 700, y, 100, 20, "调帐金额", 60);
//        y += ROW_SPAN;
//        bPanel.addComponent(accountingMoney, x, y, 100, 20, "帐上金额", 60);
//        bPanel.addComponent(payAccountField, x + 170, y, 120, 20, "付款帐号", 60);
//        bPanel.addComponent(payMoney, x + 360, y, 100, 20, "付款金额", 60);

        bPanel.addComponent(accountingMoney, x, y, 100, 20, "帐上金额", 60);
        bPanel.addComponent(payAccountField, x + 180, y, 160, 20, "付款帐号", 60);
        bPanel.addComponent(payMoney, x + 410, y, 150, 20, "付款金额", 60);
        bPanel.addComponent(totalMoney, x + 640, y, 150, 20, "金额合计", 60);

//        bPanel.addComponent(payMoney, x + 530, y, 100, 20, "付款金额", 60);
//        bPanel.addComponent(leaveMoney, x + 700, y, 100, 20, "剩余金额", 60);
        return bPanel;
    }

    public void setCustomer(Customer customer)
    {
        this.customerField.setSelectedItem(customer);
        this.customerField.setEditable(false);
        refreshAccountMoney(customer);
        //this.dataSource.getCurrentDataRow().setColumnValue("customer", customer);
    }

    public void setPayables(List<?> pos)
    {
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Object obj:pos)
        {
            Payable formPo = (Payable)obj;
            PayableClearingDetail detail = new PayableClearingDetail();
            detail.setMainObject((PayableClearing)((ObjectDataRow)dataSource.getCurrentDataRow()).getUserObject());
            detail.setPayable(formPo);
            //detail.set(formPo.getReceivableMoney());
            detail.setClearingMoney(formPo.getClearingMoney());
            detail.setAffordRemainMoney(formPo.getRemainMoney());
            detail.setClearingMoney(formPo.getRemainMoney());
            //detail.setPayableRemainMoney(formPo.getRemainMoney());
            dataRows.add(ObjectDataRow.newDataRow(detail, "id", null));
        }
        this.table.getDataSource().insertDataRows(dataRows);
    }

    @Override
    protected Class getDetailClass() {
        return PayableClearingDetail.class;
    }

    protected String getTableKeyName()
    {
        return "payable";
    }

    public void valueChanged(ValueChangedEvent evt) {
        Object source = evt.getSource();
        if (source == this.customerField)
        {
            Object value = evt.getValue();
            refreshAccountMoney(value);
        }
        else if (source == this.payAccountField)
        {
            refreshPayMoney();
        }
    }

    private void refreshPayMoney()
    {
        if (this.payAccountField.getSelectedItem() != null)
        {
            this.payMoney.setEditable(true);
            this.payMoney.setText(String.valueOf(this.totalMoney.getMoney()));
        }
    }


    private void refreshAccountMoney(Object value)
    {
        if (value instanceof Customer)
        {
            Customer customer = (Customer)value;
            CustomerPayableAccount account = Main.getServiceManager().getAccountingService().getCustomerPayableAccount(customer);
            if (account != null)
            {
                this.accountingMoney.setMoney(account.getAccountMoney());
            }
       else
            {
                this.accountingMoney.setMoney(0d);
            }
        }
        else
        {
             this.accountingMoney.setMoney(0d);
        }
    }

    @Override
    protected void updateTotalMoney() {
        IDataSource tableSource = this.table.getDataSource();
        double money=0d;
        for(IDataRow dataRow:tableSource.getDataRows())
        {
            Double rMoney = (Double)dataRow.getColumnValue("clearingMoney");
            if (rMoney != null)
            {
                money += rMoney;
            }
        }
        this.totalMoney.setMoney(money);
        //this.payMoney.setMoney(money);
    }

    private DataTableCellEditor  mainCellEditor;

    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)formDateField.getSelectedItem());
        vo.setSupplier(customerField.getText());
        vo.setChargePerson(employeeField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentsField.getText());
        vo.setAffordType(paymentTypeField.getText());
        vo.setAccountingMoney(accountingMoney.getText());
        vo.setPayAccount(payAccountField.getText());
        vo.setPayMoney(payMoney.getText());
        vo.setTotalMoney(totalMoney.getText());
        vo.setTitle("付款结算单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/PayableClearingDetail.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }

    
}