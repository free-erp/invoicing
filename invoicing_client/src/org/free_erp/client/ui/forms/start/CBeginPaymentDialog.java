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

package org.free_erp.client.ui.forms.start;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.DefaultDataSource;
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
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.InitialPayable;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CBeginPaymentDialog extends CBaseFormDialog implements ValueChangedListener
{
    private JDataField idField;
    private JDataDatePicker formDateField;
    private JDataTableComboBox customerField;
    private JDataTableComboBox employeeField;
    private JDataDisplayMoneyField accountingMoney;
    private JDataNumberField affordDateLimitField;
    private JDataTableComboBox clearingTypeField;
    private JDataNumberField moneyField;
    private JDataField commentsField;
    private JDataField departmentField;
    private JDataTableComboBox paymentTypeField;

    public CBeginPaymentDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource, dbSupport);
        this.setTitle("应付期初单");
        this.setSize(930, 230);
            pagePane.setSize(900, 150);
    }
    
    public CBeginPaymentDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, new DefaultDataSource("id"), dbSupport);
		this.setSize(930, 230);
            pagePane.setSize(900, 150);
        this.setTitle("应付期初单");      
	}

    @Override
    protected CPanel getMainPanel() {
        CPanel panel = new CPanel();
		panel.setPreferredSize(new Dimension(700, 200));
		panel.setLayout(null);
        idField = new JDataField("number", String.class, this.dataSource);
        idField.setUpdateToDataSource(false);
        idField.setEditable(false);
        formDateField = new JDataDatePicker("formDate", this.dataSource);
        customerField = new JDataTableComboBox("customer", Customer.class, this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerTable(), "name");
        customerField.setRequired("客户", true);
        customerField.addValueChangedListener(this);
        departmentField = new JDataField("department", String.class, this.dataSource);
        employeeField = new JDataTableComboBox("employee", Employee.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
        employeeField.setRequired("业务员", true);
        this.employeeField.addValueChangedListener(this);
        departmentField.setEditable(false);
        paymentTypeField = new JDataTableComboBox("affordType", AffordType.class, this.dataSource, Main.getMainFrame().getObjectsPool().getAffordTypeTable(), "name");
        paymentTypeField.setRequired("付款方式", true);
        clearingTypeField = new JDataTableComboBox("clearingType", ClearingType.class, this.dataSource, Main.getMainFrame().getObjectsPool().getClearingTypeTable(), "name");
        clearingTypeField.setRequired("结算方式", true);
        affordDateLimitField = new JDataNumberField("affordTerm", Integer.class, this.dataSource);
        affordDateLimitField.addValueChangedListener(this);
        accountingMoney = new JDataMoneyField("accountMoney", this.dataSource, this.dbSupport);
        moneyField = new JDataNumberField("payableMoney", Double.class, this.dataSource);
        moneyField.setRequired("应付金额", true);
        commentsField = new JDataField("comments",String.class,this.dataSource);
        commentsField.setMaxLength(255);
        int y = 10;
		int x = 80;
        panel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        panel.addComponent(formDateField, x + 220, y, 100, 20, "发生日期", 60);
        y += ROW_SPAN;
        panel.addComponent(customerField, x, y, 150, 20, "客户", 60);
        panel.addComponent(clearingTypeField, x + 220, y, 100, 20, "结算方式", 60);
        panel.addComponent(employeeField,  x + 400, y, 100, 20, "业务员", 60);
        panel.addComponent(departmentField, x + 570, y, 100, 20, "部门", 60);

        y += ROW_SPAN;
        panel.addComponent(accountingMoney, x, y, 150, 20, "预付金额", 60);
        panel.addComponent(paymentTypeField, x + 220, y, 100, 20, "付款方式", 60);
        panel.addComponent(moneyField, x + 400, y, 100, 20, "应付金额", 60);
        panel.addComponent(affordDateLimitField, x + 570, y, 88, 20, "付款期限", 60);
        panel.addComponent(new JLabel("天"), x + 658, y, 12, 20);

        y += ROW_SPAN;
		panel.addComponent(commentsField, x, y, 670, 20, "备注", 60);
        return panel;
    }
    @Override
	public void newDataRow()
	{
        InitialPayable po = new InitialPayable();
		po.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
		dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
		this.dataSource.insertDataRow(dataRow);
	}

    public void setVisible(boolean visible)
    {
        if (visible && (idField.getText() == null || idField.getText().trim().equals("")))
        {
            idField.setText("自动编号");
        }
        super.setVisible(visible);
    }

    protected void doCheck()
    {
        if (MessageBox.showQuestionDialog(this, "您确信审核通过当前单据?") == JOptionPane.YES_OPTION) {
			this.save();
			Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
			try {
                Main.getServiceManager().getAccountingService().passAccountingForm(po);
				((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("statusString");
                ((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("status");
				this.getDataSource().refresh();
				this.refresh();
			} catch (RuntimeException ex) {
				MessageBox.showMessageDialog(this, ex.getMessage());
				ex.printStackTrace();
			}
		}
    }

    public void valueChanged(ValueChangedEvent evt) {
        if (evt.getSource() == employeeField)
            {
                Object emp = employeeField.getSelectedItem();
                if (emp instanceof Employee)
                {
                    Employee employee = (Employee)emp;
                    if (employee != null)
                    {
                        departmentField.setText(employee.getCatalog().getName());
                    }
                }
            }
          else if (evt.getSource() == this.customerField)
          {
                Object value = evt.getValue();
                if (value != null)
                {
                    refreshAccountMoney(value);
                }
          }
        if (evt.getSource() == this.affordDateLimitField)
        {
            String strPayDate = this.affordDateLimitField.getText();
            try
            {
                int payDate = Integer.valueOf(strPayDate);
            }
            catch(Exception ex)
            {
                this.affordDateLimitField.setText("30");
            }
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
    protected void doPrint()
    {
        if(!this.doSave())
        {
            return;
        }
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("应付期初单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/BeginPaymentDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }

  
}
