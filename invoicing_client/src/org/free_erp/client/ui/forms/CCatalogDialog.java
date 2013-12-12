package org.free_erp.client.ui.forms;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import org.free_erp.client.ui.main.Main;

public class CCatalogDialog extends CBaseDetailedDialog {

	private JDataField idField;
	private JDataField nameField;
	private JDataField shortNameField;
	private JDataField helpCodeField;
	private JDataField remarks;
	/*
	private JDataField typeIdField;
	private JDataField typeNameField;
	private JDataField typeShortNameField;
	 */
	private JTabbedPane tabbedPane = new JTabbedPane();
	private CPanel panel = new CPanel();
	private Class entityClass;

    private String customerType;

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

	public CCatalogDialog(Frame parent, IDataSource dataSource, IDbSupport support, Class entityClass) {

		super(parent, dataSource, support);
		this.entityClass = entityClass;
		this.initC();
        this.firstButton.setVisible(false);
        this.previousButton.setVisible(false);
        this.nextButton.setVisible(false);
        this.lastButton.setVisible(false);
        this.printButton.setVisible(false);
        this.rejiggerTypeButton.setVisible(false);
	}
    
	private void initC() {
		this.setTitle("类别信息");
		this.setSize(700, 230);
		idField = new JDataField("number", String.class, dataSource);
        //this.idField.setEnabled(false);
        //idField.setRequired("类别编号", true);
		nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("类别名称", true);
        nameField.setMaxLength(30);
		shortNameField = new JDataField("shortName", String.class, dataSource);
        shortNameField.setMaxLength(30);
		helpCodeField = new JDataField("code", String.class, dataSource);
        helpCodeField.setMaxLength(10);
		remarks = new JDataField("comments", String.class, dataSource);
        remarks.setMaxLength(255);


		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(tabbedPane);
		p.setPreferredSize(new Dimension(700, 130));
		tabbedPane.setBounds(10, 5, 680, 150);
		this.getContentPane().add("Center", p);


		panel.setLayout(null);

		int x = 80;
		int y = 20; // row
		panel.addComponent(idField, x+20, y, 70, 20, "编号(可不填)", 80);
		panel.addComponent(nameField, x + 160, y, 90, 20, "类别名称", 60);
		panel.addComponent(shortNameField, x + 320, y, 90, 20, "简称", 60);
		panel.addComponent(helpCodeField, x + 480, y, 90, 20, "助记码", 60);
		y += ROW_SPAN + 10;
		panel.addComponent(remarks, x, y, 570, 20, "备注", 60);
		//		y += ROW_SPAN*2;
		//		panel.addComponent(typeIdField, x, y, 90, 20, "上级编号", 60);
		//		panel.addComponent(typeNameField, x + 160, y, 90, 20, "上级名称", 60);
		//		panel.addComponent(typeShortNameField, x + 320, y, 90, 20, "类别编号", 60);
		//	    String type=this.addType();
		tabbedPane.addTab("添加类别", panel);
	}

	public void newDataRow() {
		newDataRow(-1);
	}

    public void newDataRow(int parentId) {
		try {
			Object catalog = this.entityClass.newInstance();
			IDataRow dataRow = ObjectDataRow.newDataRow(catalog, "id", dbSupport);//new ObjectDataRow(catalog, "id", this.dbSupport);
			dataRow.setColumnValue("parentId", parentId);
            dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
			this.dataSource.insertDataRow(dataRow);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void doSaveAndNew() {
		if (this.doSave()) {
			this.newDataRow();
		}
	}

    @Override
    public boolean doSave() {
        /*
         *
         
        if (this.dataSource.containsColumnValue("number", this.idField.getText()))
        {
            MessageBox.showMessageDialog(this, "所定义的编号已经存在,编号不能相同!");
            return false;
        }
         */
        return super.doSave();
    }
	}

