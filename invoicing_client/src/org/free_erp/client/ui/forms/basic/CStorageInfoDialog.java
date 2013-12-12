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

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @Changer liufei
 */
public class CStorageInfoDialog extends CBaseDetailedDialog
{
	protected JDataField idField;
    private JDataField nameField;
	private JDataField shortNameField;
	private JDataField helpCodeField;
	private JDataComboBox typeField;
	private JDataComboBox adminField;
	private JDataField phoneField;
	private JDataComboBox contactField;
	private JDataField addressField;
	private JDataField postField;
	private JDataField descriptionField;
	private JDataField commentField;

	public CStorageInfoDialog(Frame parent, IDataSource dataSource)
	{
		super(parent, dataSource);
        printButton.setVisible(false);
        this.rejiggerTypeButton.setVisible(false);
		initC();
	}

    public CStorageInfoDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
        printButton.setVisible(false);
        this.rejiggerTypeButton.setVisible(false);
		this.initC();
	}

	public void initC()
	{
		this.setTitle("仓库信息");
		this.setSize(850, 400);
		idField = new JDataField("number", String.class, dataSource);
        //idField.setEnabled(false);

		nameField = new JDataField("name",String.class, dataSource);
        nameField.setRequired("仓库名称", true);
        nameField.setMaxLength(30);
        
//		shortNameField = new JDataField("shortName",String.class,dataSource);
//        shortNameField.setMaxLength(30);
//
//		helpCodeField = new JDataField("code",String.class,dataSource);

		typeField = new JDataComboBox("type", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getStorageTypes());
        typeField.setEditable(true);

		adminField = new JDataComboBox("employee",Employee.class, dataSource,Main.getMainFrame().getObjectsPool().getEmployees());
        
		phoneField = new JDataField("phone", String.class,dataSource);
        phoneField.setMaxLength(20);
        
		contactField = new JDataComboBox("contact",String.class, dataSource,Main.getMainFrame().getObjectsPool().getStringEmployees());

		addressField = new JDataField("address", String.class,dataSource);
        addressField.setMaxLength(60);

		postField = new JDataField("postNumber", String.class,dataSource);
        postField.setMaxLength(6);

		descriptionField = new JDataField("description", String.class,dataSource);
        descriptionField.setMaxLength(255);

		commentField = new JDataField("comments", String.class,dataSource);
        commentField.setMaxLength(255);

		//JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		
		//p.setPreferredSize(new Dimension(825, 380));
		
		this.getContentPane().add("Center", p);

                CPagePane panel = new CPagePane();
		        p.add(panel);
                panel.setBounds(10, 5, 825, 320);

		

		int x = 80;
		int y = 10; // row
		panel.addComponent(idField, x+30, y, 170, 20, "编号(可不填)", 80);
		panel.addComponent(nameField, x + 250, y, 200, 20, "名称", 50);
        y += ROW_SPAN;
//		panel.addComponent(shortNameField, x + 320, y, 90, 20, "简称", 50);
	    panel.addComponent(typeField, x, y, 200, 20, "类型", 50);
        panel.addComponent(adminField, x + 250, y, 200, 20, "负责人", 50);

		y += ROW_SPAN;
        panel.addComponent(contactField, x, y, 200, 20, "联系人", 50);
        panel.addComponent(phoneField, x + 250, y, 200, 20, "电话", 50);
        y += ROW_SPAN;
        panel.addComponent(addressField, x, y, 300, 20, "地址", 50);
		panel.addComponent(postField, x + 350, y, 100, 20, "邮编", 50);
		y += ROW_SPAN;
        panel.addComponent(descriptionField, x, y, 450, 20, "描述", 50);

		
		y += ROW_SPAN;
		panel.addComponent(commentField, x, y, 450, 20, "备注", 50);

	}

    @Override
    public boolean doCheckField()
    {
        String type = this.typeField.getText();
        if(type != null)
        {
            if(type.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"类型\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        return true;
    }

	public void doSaveAndNew()
	{
		// TODO Auto-generated method stub
		if (this.doSave())
		{
			this.newDataRow();
		}
		//this.dataSource.
	}
	public void newDataRow()
    {
        this.saveAndNewButton.setEnabled(true);
        this.saveButton.setEnabled(true);
		Storage storage = new Storage();
		storage.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", this.dbSupport);
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
	}
}
