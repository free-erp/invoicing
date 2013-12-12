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

package org.free_erp.client.ui.forms.warehouse;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.base.Storage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Administrator
 * @Changer liufei
 */
public class CStorageCheckProductSelectDialog extends CBaseProductSelectDialog implements  ActionListener, ValueChangedListener
{

    private JDataField bookMoneyField;
    private JDataField bookAmountFied;
    private JDataField truthfullyAmountField;
    private JDataField truthfullyMoneyField;
    private JDataField amountDifferenceField;
    private JDataField moneyDifferenceField;

    public CStorageCheckProductSelectDialog(JDialog parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

     public CStorageCheckProductSelectDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }

    public CStorageCheckProductSelectDialog(Frame parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    @Override
    protected void initC() {

        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("商品信息");
        this.setSize(700, 230);
        typeIdField = new JDataPanelComboBox("number", String.class, this.dataSource, Main.getMainFrame().getProductSelectPanel());
        typeIdField.setRequired(true);
        typeIdField.setDisplayName("商品编号");
        typeNameField = new JDataField("name", String.class, this.dataSource);
        this.typeNameField.setEditable(false);
        catalogNameField = new JDataField("catalogName", String.class, this.dataSource);
        this.catalogNameField.setEditable(false);
        specField = new JDataField("spec", String.class, this.dataSource);
        specField.setEditable(false);
        unitField = new JDataField("unit", String.class, this.dataSource);
        unitField.setEditable(false);
        totalMoneyField = new JDataField("totailMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
        stockField.setEditable(false);
        bookAmountFied = new JDataField();
        bookAmountFied.setEditable(false);
        bookMoneyField = new JDataField();
        bookMoneyField.setEditable(false);
        truthfullyMoneyField = new JDataField();
        truthfullyMoneyField.setRequired(true);
		truthfullyMoneyField.setDisplayName("实盘金额");
        truthfullyAmountField = new JDataField("amount", Double.class, this.dataSource);
        truthfullyAmountField.setRequired(true);
		truthfullyAmountField.setDisplayName("实盘数量");
        amountDifferenceField = new JDataField();
        amountDifferenceField.setEditable(false);
        moneyDifferenceField =new JDataField();
        moneyDifferenceField.setEditable(false);
        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("取消(&C)");
        chooseButton = new CButton("确定(&O)");
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(680, 286));
        tabbedPane.setBounds(10, 5, 680, 190);
        CPanel panel = new CPanel();
        panel.setLayout(null);
        int x = 80;
        int y = 10; // row
        panel.addComponent(typeIdField, x-10, y, 100, 20, "商品编码", 50);
        panel.addComponent(typeNameField, x + 160, y, 250, 20, "商品名称", 60);
        panel.addComponent(catalogNameField, x + 480, y, 90, 20, "商品类别", 60);

        y += ROW_SPAN;
        panel.addComponent(unitField, x-10, y, 100, 20, "单位", 50);
        panel.addComponent(bookAmountFied, x + 160, y, 90, 20, "账面数量", 60);
        panel.addComponent(truthfullyAmountField, x + 320, y, 90, 20, "实盘数量", 60);
        panel.addComponent(amountDifferenceField, x + 480, y, 90, 20, "数量差额", 60);
        y += ROW_SPAN;
        panel.addComponent(specField, x-10, y, 100, 20, "规格型号", 50);
        panel.addComponent(bookMoneyField, x + 160, y, 90, 20, "账面金额", 60);
        panel.addComponent(truthfullyMoneyField, x + 320, y, 90, 20, "实盘金额", 60);
        panel.addComponent(moneyDifferenceField, x + 480, y, 90, 20, "金额差额", 60);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x-10, y, 560, 20, "备注", 50);
        y += ROW_SPAN + 10;
        panel.addComponent(chooseButton, x + 350, y, 80, 25);
        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("添加商品", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
        this.truthfullyAmountField.addValueChangedListener(this);
        this.truthfullyMoneyField.addValueChangedListener(this);

    //temp:
//		shelfField.addItem("一号货位");
//		shelfField.addItem("二号货位");
//		shelfField.addItem("三号货位");
//		shelfField.addItem("四号货位");
//		shelfField.addItem("五号货位");
//    this.setDefaultButton(this.chooseButton);
//    	this.setCancelButton(this.closeButton);
    }
    @Override
    public void valueChanged(ValueChangedEvent evt)
	{

		if (evt.getSource() == this.typeIdField)
		{
			refreshDatas();
		} else if (evt.getSource() == this.truthfullyAmountField || evt.getSource() == this.truthfullyMoneyField)
		{
			refreshMoney();
		}

	}

    	private void refreshMoney()
	{
		String p = this.bookAmountFied.getText();
		if (p == null || p.equals(""))
		{
			return;
		}
		String priceString = String.valueOf(p);

		String truthfullyAmount = this.truthfullyAmountField.getText();
		if (truthfullyAmount != null && !truthfullyAmount.trim().equals(""))
		{
			try
			{
				double price = Double.parseDouble(priceString);
				double amount = Double.parseDouble(truthfullyAmount);
				this.amountDifferenceField.setText(String.valueOf(price - amount));

			}
			catch(Exception ex)
			{
				//ex.printStackTrace();
				MessageBox.showErrorDialog(this, "数据格式不对！");
			}
		}
	}
private void refreshDatas()
	{
		Object obj = this.typeIdField.getSelectedItem();
		if (obj instanceof IDataRow)
		{
			IDataRow dataRow = (IDataRow) obj;
			String name = (String) dataRow.getColumnValue("name");
			if (name != null)
			{
				this.typeNameField.setText(name);
			}
			String spec = (String) dataRow.getColumnValue("spec");
			if (spec != null)
			{
				this.specField.setText(spec);
			}
			String unit = (String) dataRow.getColumnValue("smallUnit");
			if (unit != null)
			{
				this.unitField.setText(unit);
			}
           String bookAmount = (String) dataRow.getColumnValue("bookAmount");
			if (bookAmount != null)
			{
				this.bookAmountFied.setText(bookAmount);
			}
            String bookMoney = (String) dataRow.getColumnValue("bookMoney");
			if (bookAmount != null)
			{
				this.bookAmountFied.setText(bookMoney);
			}
			ProductCatalog catalog = (ProductCatalog) dataRow.getColumnValue("catalog");
			if (catalog != null)
			{
				this.catalogNameField.setText(catalog.getName());
			}
			//添加产品id信息
			IDataRow cRow = this.dataSource.getCurrentDataRow();
			cRow.setColumnValue("productId", dataRow.getColumnValue("id"));

		}
	}


}
