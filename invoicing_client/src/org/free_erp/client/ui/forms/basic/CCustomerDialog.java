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
/**客户管理
 * @author tengzhuolin
 */
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @Changer liufei
 */
public class CCustomerDialog extends CBaseDetailedDialog
{
	private CCustomerManageWindow manageWindow;
	protected JDataField idField;
    protected JDataField nameField;
	protected JDataField shortNameField;
	protected JDataField helpCodeField;
	protected JDataComboBox customerTypeField;
	//private JDataComboBox invoiceTypeField;
	//private JDataComboBox defaultPriceField;
	protected JDataField contactField;
	protected JDataField mobileField;
	protected JDataField idCardField;
	protected JDataField mailField;
	protected JDataField qqField;
	protected JDataField msnField;
	protected JDataField phoneField;
	protected JDataField faxField;
	protected JDataField postField;
	protected JDataField websiteField;
	protected JDataField bankField;
	protected JDataField bankNoField;
    protected JDataField bankOwnerField;
	//private JDataField taxAccountField;
	protected JDataField addressField;
	//private JDataField maxReceiveField;
	//private JDataField maxDateReceiveField;
	//private JDataField maxInvoiceField;
	protected JDataField commentField;
    private IDataRow datass=null;
    protected JDataComboBox typeField;
    protected CustomerCatalog customerCatalog;

	public CCustomerDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
        super(parent, dataSource, support);
		this.initC();
	}
    
	public CCustomerDialog(Frame parent, CCustomerManageWindow manageWindow, IDataSource dataSource, IDbSupport support)
	{
		super(parent,dataSource, support);
		this.manageWindow = manageWindow;
		this.initC();
	}
     private void initC()
	{
         this.setSize(850, 430);
	    this.setTitle("客户信息");
		idField = new JDataField("number", String.class, dataSource);
        nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("名称", true);
        nameField.setMaxLength(30);
	    customerTypeField =  new JDataComboBox("type", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerTypes());
        customerTypeField.setEditable(true);
		contactField =  new JDataField("contactName",String.class,dataSource);
        contactField.setMaxLength(10);
		mobileField =  new JDataField("mobile",String.class,dataSource);
        mobileField.setMaxLength(20);
        idCardField =  new JDataField("idCard",String.class,dataSource);
        idCardField.setMaxLength(18);
		mailField =  new JDataField("email",String.class,dataSource);
        mailField.setMaxLength(60);
		qqField =  new JDataField("qq",String.class,dataSource);
        qqField.setMaxLength(30);
		msnField =  new JDataField("msn",String.class,dataSource);
        msnField.setMaxLength(30);
		phoneField =  new JDataField("phone",String.class,dataSource);
        phoneField.setMaxLength(20);
		faxField =  new JDataField("fax",String.class,dataSource);
        faxField.setMaxLength(20);
		postField =  new JDataField("postcode",String.class,dataSource);
        postField.setMaxLength(6);
        
		websiteField =  new JDataField("website",String.class,dataSource);//
        websiteField.setMaxLength(30);
		bankField =  new JDataField("bank",String.class,dataSource);
        bankField.setMaxLength(32);
		bankNoField =  new JDataField("bankNo",String.class,dataSource);
        bankNoField.setMaxLength(30);
		bankOwnerField =  new JDataField("bankOwner",String.class,dataSource);
        bankOwnerField.setMaxLength(32);
		addressField =  new JDataField("address",String.class,dataSource);//
        addressField.setMaxLength(60);
		//maxReceiveField =  new JDataField("maxReceiveMoney",Integer.class,dataSource);
		//maxDateReceiveField =  new JDataField("maxReceiveDate",Integer.class,dataSource);
		//maxInvoiceField =  new JDataField("maxInvoceMoney",Integer.class,dataSource);
		commentField =  new JDataField("comments",String.class,dataSource);
        commentField.setMaxLength(255);

        typeField =  new JDataComboBox("customerType", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerType());



		//JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		
		p.setPreferredSize(new Dimension(780, 390));

                CPagePane panel = new CPagePane();
		this.getContentPane().add("Center", p);
                p.setLayout(null);
                p.add(panel);
                panel.setBounds(10, 5, 825, 350);

		
		
		int x = 80;
		int y = 10; // row
		panel.addComponent(idField, x + 20, y, 180, 20, "编号(可不填)", 80);
		panel.addComponent(nameField, x + 280, y, 300, 20, "名称", 60);
		//panel.addComponent(shortNameField, x + 400, y, 260, 20, "简称", 40);
		//panel.addComponent(helpCodeField, x + 570, y, 90, 20, "助记码", 60);
        y += ROW_SPAN;
        panel.addComponent(typeField, x + 20, y, 180, 20, "客户(供应商)", 80);
		y += ROW_SPAN;
		panel.addComponent(customerTypeField, x, y, 200, 20, "客户类型", 60);
        panel.addComponent(mobileField, x + 280, y, 300, 20, "手机号", 60);
		//panel.addComponent(defaultPriceField, x + 380, y, 120, 20, "默认价格", 60);
		//panel.addComponent(invoiceTypeField, x + 570, y, 90, 20, "发票类型", 60);
        //panel.addComponent(helpCodeField, x + 400, y, 260, 20, "助记码", 40);
		y += ROW_SPAN;
		panel.addComponent(contactField, x, y, 200, 20, "联系人", 60);
		panel.addComponent(idCardField, x + 280, y, 300, 20, "身份证号", 60);
        y += ROW_SPAN;
		panel.addComponent(mailField, x, y, 200, 20, "邮箱", 60);
		panel.addComponent(qqField, x + 280, y, 300, 20, "QQ", 60);
		y += ROW_SPAN;
		panel.addComponent(phoneField, x, y, 200, 20, "电话", 60);
		panel.addComponent(msnField, x + 280, y, 300, 20, "MSN", 60);
        y += ROW_SPAN;
		panel.addComponent(faxField, x, y, 200, 20, "传真", 60);
		panel.addComponent(websiteField, x + 280, y, 300, 20, "网址", 60);
		y += ROW_SPAN;

        panel.addComponent(postField, x , y, 200, 20, "邮编", 60);
		panel.addComponent(addressField, x + 280, y, 300, 20, "地址", 60);
		y += ROW_SPAN;
		panel.addComponent(bankField, x, y, 200, 20, "开户行", 60);
		panel.addComponent(bankNoField, x + 280, y, 160, 20, "帐号", 60);
		panel.addComponent(bankOwnerField, x + 520, y, 60, 20, "开户人", 60);
		
		y += ROW_SPAN;
		panel.addComponent(commentField, x, y, 580, 20, "备注", 60);
		
		
	}
    @Override
    protected void doRejiggerType()
    {
        if(MessageBox.showQuestionDialog(this, "您确定要更改此客户的类别吗?") == 0)
        {
            rejiggerTypeDialog = new CCustomerRejiggerTypeDialog(this, dataSource);
            rejiggerTypeDialog.setVisible(true);
        }
    }
    @Override
     public boolean doCheckField()
    {
        String customerType = this.customerTypeField.getText();
        if(customerType != null)
        {
            if(customerType.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"客户类型\"输入的内容不能超过30个字符!" );
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
		//this.dataSource
	}
	public boolean newDataRow()
	{
        this.saveButton.setEnabled(true);
        this.saveAndNewButton.setEnabled(true);
	    CustomerCatalog catalog = null;
        if(customerCatalog != null)
        {
            catalog = customerCatalog;
        }
		if (this.manageWindow != null)
		{
			catalog = (CustomerCatalog)this.manageWindow.getCurrentCatalog();
		}
//		if (catalog == null)
//		{
//			MessageBox.showMessageDialog(this, "您没有选择类别,请先选择类别!"); //看需求如何定义
//            return false;
//		}
		Customer customer = new Customer();
		customer.setCatalog(catalog);
        customerCatalog = catalog;
		customer.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(customer, "id", this.dbSupport);
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
        vo.setTitle("客户信息详细报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/CustomerDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }
}
