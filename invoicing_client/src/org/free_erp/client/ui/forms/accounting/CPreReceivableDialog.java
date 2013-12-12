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
import org.free_erp.service.entity.accounting.CustomerReceivableAccount;
import org.free_erp.service.entity.accounting.Prereceivable;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author afa
 */
public class CPreReceivableDialog extends CBaseFormDialog implements ValueChangedListener
{
    private JDataField idField;
    private JDataDatePicker formDateField;
    private JDataTableComboBox customerField;
    private JDataTableComboBox employeeField;
    private JDataDisplayMoneyField accountingMoney;
    private JDataTableComboBox receiveAccountField;
    private JDataNumberField moneyField;
    private JDataField commentsField;
    private JDataTableComboBox paymentTypeField;

    public CPreReceivableDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource, dbSupport);
        this.setTitle("销售预收单");
        this.setSize(930, 230);
        pagePane.setSize(900, 150);
    }

    public CPreReceivableDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, new DefaultDataSource("id"), dbSupport);
            this.setTitle("销售预收单");
            this.setSize(930, 230);
            pagePane.setSize(900, 150);
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
        employeeField = new JDataTableComboBox("employee", Employee.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
        employeeField.setRequired("业务员", true);
        paymentTypeField = new JDataTableComboBox("affordType", AffordType.class, this.dataSource, Main.getMainFrame().getObjectsPool().getAffordTypeTable(), "name");
        paymentTypeField.setRequired("付款方式", true);
        accountingMoney = new JDataMoneyField("accountMoney", this.dataSource, this.dbSupport);
        receiveAccountField = new JDataTableComboBox("subject", Subject.class, this.dataSource, Main.getMainFrame().getObjectsPool().getBankSubjectTable(), "name");
        receiveAccountField.setRequired("收款账户", true);
        moneyField = new JDataNumberField("receiveMoney", Double.class, this.dataSource);
        moneyField.setRequired("收款金额", true);
        commentsField = new JDataField("comments",String.class,this.dataSource);
        commentsField.setMaxLength(255);
        int y = 10;
		int x = 80;
        panel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        panel.addComponent(formDateField, x + 220, y, 100, 20, "发生日期", 60);
        y += ROW_SPAN;
        panel.addComponent(customerField, x, y, 150, 20, "客户", 60);
        panel.addComponent(employeeField, x + 220, y, 150, 20, "业务员", 60);
        panel.addComponent(paymentTypeField, x + 440, y, 150, 20, "付款方式", 60);
        y += ROW_SPAN;
        panel.addComponent(accountingMoney, x, y, 150, 20, "账上金额", 60);
        panel.addComponent(receiveAccountField, x + 220, y, 150, 20, "收款账户", 60);
        panel.addComponent(moneyField, x + 440, y, 150, 20, "收款金额", 60);
        y += ROW_SPAN;
		panel.addComponent(commentsField, x, y, 590, 20, "备注", 60);
        return panel;
    }
    @Override
	public void newDataRow()
	{
        Prereceivable po = new Prereceivable();
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
                MessageBox.showMessageDialog(this, "审核通过!");
			} catch (RuntimeException ex) {
				MessageBox.showMessageDialog(this, ex.getMessage());
				ex.printStackTrace();
			}
		}
    }
    public void valueChanged(ValueChangedEvent evt) {
        Object value = evt.getValue();
        if (value != null)
        {
            refreshAccountMoney(value);
        }
    }

    private void refreshAccountMoney(Object value)
    {
        if (value instanceof Customer)
        {
            Customer customer = (Customer)value;
            CustomerReceivableAccount account = Main.getServiceManager().getAccountingService().getCustomerReceivableAccount(customer);
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
        vo.setTitle("销售预收单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/PreReceivableDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }

   
}