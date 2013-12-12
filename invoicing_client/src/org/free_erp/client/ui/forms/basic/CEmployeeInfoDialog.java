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

package org.free_erp.client.ui.forms.basic;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/*
 *
 * Changer: liufei
 */
public class CEmployeeInfoDialog extends CBaseDetailedDialog
{
	private CEmployeeManageWindow manageWindow;
	protected JDataField idField;
    private JDataField nameField;
	private JDataField shortNameField;
	private JDataField helpCodeField;
	private JDataComboBox sexField;
	private JDataDatePicker birthdayField;
	private JDataComboBox healthField;
	private JDataField idCardField;
	private JDataComboBox degreeField;
	private JDataComboBox levelField;
	private JDataComboBox educationField;
	private JDataDatePicker joinDateField;
	//private JDataField maxInvoiceField;
	//private JDataField minDiscountField;
	private JDataField phoneField;
	private JDataField mobileField;
	private JDataField addressField;
	private JDataField postField;
	private JDataField mailField;
	private JDataField bankField;
	private JDataField bankAccountField;
	private JDataField commentField;
    private IDataRow datass=null;
    private EmployeeCatalog employeeCatalog;

	public CEmployeeInfoDialog(Frame parent, IDataSource dataSource)
	{
		super(parent, dataSource);
		initC();
	}

	public CEmployeeInfoDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
		this.initC();
	}
    
	public CEmployeeInfoDialog(Frame parent, CEmployeeManageWindow manageWindow, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
		this.manageWindow = manageWindow;
		this.initC();
	}

	private void initC()
	{
		this.setTitle("员工信息");
		//this.setSize(780, 300);
		idField = new JDataField("number", String.class, dataSource);
        //idField.setEditable(false);

		nameField = new JDataField("name",  String.class,dataSource);
        nameField.setRequired("姓名", true);
        nameField.setMaxLength(30);
        
//		shortNameField = new JDataField("shortName" , String.class,dataSource);
//        shortNameField.setMaxLength(30);
//
//		helpCodeField = new JDataField("code",  String.class,dataSource);
//        helpCodeField.setMaxLength(10);

		sexField = new JDataComboBox("sex",  String.class, dataSource, Main.getMainFrame().getObjectsPool().getSexs());
		
		birthdayField = new JDataDatePicker("birthday", this.dataSource);
        
		healthField = new JDataComboBox("healthStatus", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeHealths());
        healthField.setEditable(true);

		idCardField  = new JDataField("idCard", String.class, dataSource);
        idCardField.setMaxLength(18);

		levelField = new JDataComboBox("degree", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeDegrees());
        levelField.setEditable(true);

		degreeField = new JDataComboBox("duty", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeDutys());
        degreeField.setEditable(true);

		educationField = new JDataComboBox("education", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeLevels());
        educationField.setEditable(true);

		joinDateField = new JDataDatePicker("joinDate", this.dataSource);
		//maxInvoiceField = new JDataField("maxInvoceMoney",String.class, dataSource);
		//minDiscountField = new JDataField("minDisaccount",String.class, dataSource);
		phoneField = new JDataField("phone",String.class, dataSource);
        phoneField.setMaxLength(20);

		mobileField = new JDataField("mobile",String.class, dataSource);
        mobileField.setMaxLength(20);

		addressField = new JDataField("address",String.class, dataSource);
        addressField.setMaxLength(60);

		postField = new JDataField("postNumber",String.class,dataSource);
        postField.setMaxLength(6);
        
		mailField = new JDataField("email",String.class,dataSource);
        mailField.setMaxLength(60);
        
		bankField = new JDataField("bank",String.class,dataSource);
        bankField.setMaxLength(30);

		bankAccountField = new JDataField("bankAccount",String.class, dataSource);
        bankAccountField.setMaxLength(24);
        
		commentField = new JDataField("comments",String.class, dataSource);
        commentField.setMaxLength(255);

		
		
//		p.setLayout(null);
//		p.add(tabbedPane);
//		p.setPreferredSize(new Dimension(800, 246));
//		tabbedPane.setBounds(10, 5, 750, 240);
                JPanel p = new JPanel();
                CPagePane panel = new CPagePane();
		this.getContentPane().add("Center", p);
                p.setLayout(null);
                p.add(panel);
                panel.setBounds(10, 10, 825, 320);
		//CPanel panel = new CPanel();
		//panel.setLayout(null);

		int x = 80;
		int y = 10; // row
		panel.addComponent(idField, x+30, y, 120, 20, "编号(可不填)", 80);
		panel.addComponent(nameField, x + 200, y, 150, 20, "姓    名", 50);
        panel.addComponent(sexField, x + 400, y, 150, 20, "性    别", 50);
//		panel.addComponent(shortNameField, x + 380, y, 90, 20, "简    称", 50);
//		panel.addComponent(helpCodeField, x + 530, y, 100, 20, "助 记 码", 50);

		y += ROW_SPAN;
        panel.addComponent(idCardField, x, y, 150, 20, "身份证号", 50);
		panel.addComponent(healthField, x + 200, y, 150, 20, "健康状况", 50);
        panel.addComponent(birthdayField, x + 400, y, 150, 20, "生    日", 50);

		y += ROW_SPAN;
		panel.addComponent(degreeField, x, y, 100, 20, "职    务", 50);
		panel.addComponent(levelField, x + 150, y,100, 20, "职     称", 50);
		panel.addComponent(educationField, x + 300, y, 100, 20, "学历", 50);
        panel.addComponent(joinDateField, x + 450, y, 100, 20, "加入日期", 50);

		y += ROW_SPAN;
        panel.addComponent(mailField, x, y, 200, 20, "邮    箱", 50);
		//panel.addComponent(maxInvoiceField, x, y, 100, 20, "开票限额", 50);
		//panel.addComponent(minDiscountField, x + 170, y, 140, 20, "最小折扣", 50);
		panel.addComponent(phoneField, x + 250, y, 150, 20, "电    话", 50);
		panel.addComponent(mobileField, x + 450, y, 100, 20, "手    机", 50);

		y += ROW_SPAN;
		panel.addComponent(addressField, x, y, 400, 20, "地    址", 50);
		panel.addComponent(postField, x + 450, y, 100, 20, "邮    编", 50);

		y += ROW_SPAN;
		//panel.addComponent(mailField, x, y, 230, 20, "邮    箱", 50);
		panel.addComponent(bankField, x, y, 200, 20, "开 户 行", 50);
		panel.addComponent(bankAccountField, x + 250, y, 300, 20, "帐    号", 50);

		y += ROW_SPAN;
		panel.addComponent(commentField, x, y, 550, 20, "备    注", 50);

		/*
		y += ROW_SPAN;
		panel.addComponent(customerTypeField, x, y, 100, 20, "编号", 40);
		panel.addComponent(invoiceTypeField, x + 160, y, 100, 20, "名称", 40);
		panel.addComponent(defaultPriceField, x + 320, y, 100, 20, "简称", 40);
		panel.addComponent(contactField, x + 480, y, 60, 20, "助记码", 40);
		*/

		//tabbedPane.addTab("基本档案", panel);
	}

    @Override
    protected void doRejiggerType()
    {
        if(MessageBox.showQuestionDialog(this, "您确定要更改此职员的部门吗?") == 0)
        {
            rejiggerTypeDialog = new CEmployeeRejiggerTypeDialog(this, dataSource);
            rejiggerTypeDialog.setVisible(true);
        }
    }

    @Override
    public boolean doCheckField()
    {
        String health = this.healthField.getText();
        if(health != null)
        {
            if(health.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"健康状况\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        String degree = this.degreeField.getText();
        if(degree != null)
        {
            if(degree.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"职务\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        String level = this.levelField.getText();
        if(level != null)
        {
            if(level.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"职称\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        String education = this.educationField.getText();
        if(education != null)
        {
            if(education.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"文化程度\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        return true;
    }

    @Override
	public void doSaveAndNew()
	{
		// TODO Auto-generated method stub
		if (this.doSave())
		{
			this.newDataRow();
		}
	}

	public boolean newDataRow()
	{
        this.saveAndNewButton.setEnabled(true);
        this.saveButton.setEnabled(true);
        EmployeeCatalog catalog = null;
        if(employeeCatalog != null)
        {
            catalog = employeeCatalog;
        }
	    if (this.manageWindow != null)
	    {
             catalog = (EmployeeCatalog)this.manageWindow.getCurrentCatalog();
	    }
//	    if (catalog == null)
//	    {
//             MessageBox.showMessageDialog(this, "您没有选择类别,请先选择类别!"); //看需求如何定义
//             return false;
//        }
	    Employee employee = new Employee();
	    employee.setCatalog(catalog);
        employeeCatalog = catalog;
	    employee.setCompany(Main.getMainFrame().getCompany());
	    IDataRow dataRow = ObjectDataRow.newDataRow(employee, "id", this.dbSupport);
	    this.dataSource.insertDataRow(dataRow);
	    this.dataSource.last();
	    return true;
	}

    public void doPrint()
    {
        if(!this.doSave())
        {
            return;
        }
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("员工信息详细报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/EmployeeInfoDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }
}

