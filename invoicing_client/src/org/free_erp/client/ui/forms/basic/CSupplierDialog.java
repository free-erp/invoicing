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

import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.print.CPrintDialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Afa
 */
public class CSupplierDialog extends CCustomerDialog
{
    public CSupplierDialog(Frame parent, CCustomerManageWindow manageWindow, IDataSource dataSource, IDbSupport support)
	{
		super(parent,manageWindow,dataSource, support);
	}

    public CSupplierDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
		super(parent,dataSource, support);
	}

   
    protected void initC()
	{
	    this.setTitle("��Ӧ����Ϣ");
		this.setSize(850, 300);
		idField = new JDataField("number", String.class, dataSource);
        this.idField.setMaxLength(30);
        //idField.setEnabled(false);

		nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("����", true);
        nameField.setMaxLength(30);

		shortNameField = new JDataField("shortName",String.class,dataSource);
        shortNameField.setMaxLength(30);

		helpCodeField = new JDataField("code",String.class,dataSource);
        helpCodeField.setMaxLength(10);

	    customerTypeField =  new JDataComboBox("type", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerTypes());
        customerTypeField.setEditable(true);

        typeField =  new JDataComboBox("customerType", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getSupplierType());

		//invoiceTypeField =  new JDataComboBox("invoceType", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerInvoices());

		//defaultPriceField =  new JDataComboBox("defaultPrice", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomerPrices());

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

        bankField = new JDataField("bank",String.class,dataSource);
        bankField.setMaxLength(64);

		//bankAccountField = new JDataField("bankAccount",String.class, dataSource);
        //bankAccountField.setMaxLength(64);

		websiteField =  new JDataField("website",String.class,dataSource);//
        websiteField.setMaxLength(30);
		//bankField =  new JDataField("mns",String.class,dataSource);
		//bankAccountField =  new JDataField("bankAccount",String.class,dataSource);
		//taxAccountField =  new JDataField("taxAccount",String.class,dataSource);
		addressField =  new JDataField("address",String.class,dataSource);//
        addressField.setMaxLength(60);
		//maxReceiveField =  new JDataField("maxReceiveMoney",Integer.class,dataSource);
		//maxDateReceiveField =  new JDataField("maxReceiveDate",Integer.class,dataSource);
		//maxInvoiceField =  new JDataField("maxInvoceMoney",Integer.class,dataSource);
		commentField =  new JDataField("comments",String.class,dataSource);
        commentField.setMaxLength(255);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new CPanel();
		p.setLayout(null);
		p.add(tabbedPane);
		p.setPreferredSize(new Dimension(780, 240));
		tabbedPane.setBounds(10, 5, 825, 230);
		this.getContentPane().add("Center", p);
		CPanel panel = new CPanel();
		panel.setLayout(null);
		int x = 60;
		int y = 5; // row
		panel.addComponent(idField, x, y, 130, 20, "���", 50);
		panel.addComponent(nameField, x + 180, y, 260, 20, "        ����", 50);
		panel.addComponent(shortNameField, x + 490, y, 260, 20, "        ���", 50);
        //panel.addComponent(typeField, x + 550, y, 110, 20, "�ͻ����", 50);
		//panel.addComponent(helpCodeField, x + 570, y, 90, 20, "������", 60);
		y += ROW_SPAN;
		panel.addComponent(customerTypeField, x, y, 130, 20, "�ͻ�����", 50);
        panel.addComponent(typeField, x + 180, y, 160, 20, "�ͻ����", 50);
        panel.addComponent(postField, x + 390, y, 160, 20, "        �ʱ�", 50);
		//panel.addComponent(mobileField, x + 200, y, 150, 20, "�ֻ���", 50);
		//panel.addComponent(defaultPriceField, x + 380, y, 120, 20, "Ĭ�ϼ۸�", 60);
		//panel.addComponent(invoiceTypeField, x + 570, y, 90, 20, "��Ʊ����", 60);
        //panel.addComponent(helpCodeField, x + 600, y, 150, 20, "    ������", 50);
        panel.addComponent(qqField, x + 600, y, 150, 20, "        QQ", 50);
		y += ROW_SPAN;
		panel.addComponent(contactField, x, y, 130, 20, "��ϵ��", 50);
		panel.addComponent(idCardField, x + 180, y, 160, 20, "���֤��", 50);
		panel.addComponent(mailField, x + 390, y, 360, 20, "        ����", 50);
		//panel.addComponent(qqField, x + 600, y, 150, 20, "        QQ", 50);
		y += ROW_SPAN;
		panel.addComponent(phoneField, x, y, 130, 20, "�绰", 50);
		panel.addComponent(msnField, x + 180, y, 160, 20, "        MSN", 50);
		panel.addComponent(faxField, x + 390, y, 160, 20, "        ����", 50);
        panel.addComponent(mobileField, x + 600, y, 150, 20, "    �ֻ���", 50);
		//panel.addComponent(postField, x + 570, y, 90, 20, "�ʱ�", 40);
        y += ROW_SPAN;
		panel.addComponent(bankField, x, y, 300, 20, "������", 50);
		//panel.addComponent(bankAccountField, x + 350, y, 400, 20, "        �˺�", 50);
		y += ROW_SPAN;
		panel.addComponent(websiteField, x, y, 300, 20, "��ַ", 50);
		panel.addComponent(addressField, x + 350, y, 400, 20, "        ��ַ", 50);
		//y += ROW_SPAN;
		//panel.addComponent(bankField, x, y, 165, 20, "MSN", 60);
		//panel.addComponent(bankAccountField, x + 265, y, 165, 20, "�绰", 80);
		//panel.addComponent(taxAccountField, x + 495, y, 165, 20, "����", 60);
		//y += ROW_SPAN;
		//panel.addComponent(maxReceiveField, x, y, 165, 20, "Ӧ���޶�", 60);
		//panel.addComponent(maxDateReceiveField, x + 265, y, 165, 20, "Ӧ������(��)", 80);
		//panel.addComponent(maxInvoiceField, x + 495, y, 165, 20, "��Ʊ�޶�", 60);
		y += ROW_SPAN;
		panel.addComponent(commentField, x, y, 750, 20, "��ע", 50);
		tabbedPane.addTab("��������", panel);
	}

    protected void doRejiggerType()
    {
//        if(MessageBox.showQuestionDialog(Main.getMainFrame(), "��ȷ��Ҫ���Ĵ˿ͻ��������?") == 0)
//        {
//            rejiggerTypeDialog = new CSupplierRejiggerTypeDialog(this, dataSource);
//            rejiggerTypeDialog.setVisible(true);
//        }
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
        vo.setTitle("��Ӧ����Ϣ��ϸ����");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/jzh/demo/client/report/jaspers/CustomerDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }
}
