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
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.logic.IStorageService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageChangePriceProductDialog extends CStorageOutProductDialog
{
    private JDataNumberField currentPriceField;
    
    public CStorageChangePriceProductDialog(JDialog parent, IDataSource dataSource, Storage storage)
	{
		super(parent, dataSource, storage);
	}

    @Override
    protected void initC()
	{
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("添加商品");
		this.setSize(650, 270);
		typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
		typeIdField.setRequired(true);
		typeIdField.setDisplayName("商品编号");
		typeNameField = new CField();
        this.typeNameField.setEditable(false);
		catalogNameField = new CField();
		this.catalogNameField.setEditable(false);
		specField = new CField();
		specField.setEditable(false);
		unitField = new CField();
		unitField.setEditable(false);
        stockShelfField = new JDataField("shelf", String.class, this.dataSource);
        stockShelfField.setEnabled(false);
        stockPriceField = new JDataField("oldPrice", Double.class, this.dataSource);
        stockPriceField.setEnabled(false);
        
		currentPriceField = new JDataNumberField("price", Double.class, this.dataSource);
        currentPriceField.setMaxLength(10);
		totalMoneyField = new JDataField("totailMoney", Double.class, this.dataSource);
		totalMoneyField.setEnabled(false);
		stockField = new JDataField("amount", Double.class, this.dataSource);
		stockField.setEnabled(false);
		commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
		closeButton = new CButton("取消(&C)");
		chooseButton = new CButton("确定(&O)");
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(tabbedPane);
		p.setPreferredSize(new Dimension(650, 286));
		tabbedPane.setBounds(10, 5, 630, 230);
		CPanel panel = new CPanel();
		panel.setLayout(null);
		int x = 80;
		int y = 10; // row
		panel.addComponent(typeIdField, x-10, y, 100, 20, "商品编码", 50);
		panel.addComponent(typeNameField, x + 160, y, 200, 20, "商品名称", 60);
		panel.addComponent(catalogNameField, x + 420, y, 100, 20, "商品类别", 50);

		y += ROW_SPAN;
		panel.addComponent(unitField, x-10, y, 100, 20, "单位", 50);
		panel.addComponent(specField, x + 160, y, 200, 20, "规格型号", 60);
		panel.addComponent(stockShelfField, x + 420, y, 100, 20, "货位", 50);
		y += ROW_SPAN;
		panel.addComponent(stockField, x-10, y, 100, 20, "库存数量", 50);
		panel.addComponent(stockPriceField, x + 160, y, 80, 20, "库存单价", 50);
		panel.addComponent(currentPriceField, x + 300, y, 60, 20, "现存单价", 50);
		panel.addComponent(totalMoneyField, x + 420, y, 100, 20, "现存金额", 50);
		y += ROW_SPAN;
		panel.addComponent(commentsField, x-10, y, 530, 20, "备注", 50);
		y += ROW_SPAN + 10;
		panel.addComponent(chooseButton, x + 350, y, 80, 25);
		panel.addComponent(closeButton, x + 430, y, 80, 25);

		tabbedPane.addTab("商品信息", panel);
		this.getContentPane().add(p);
		this.chooseButton.addActionListener(this);
		this.closeButton.addActionListener(this);
		typeIdField.addValueChangedListener(this);
		this.currentPriceField.addValueChangedListener(this);

		this.setDefaultButton(this.chooseButton);
		this.setCancelButton(this.closeButton);
	}

    @Override
	public void valueChanged(ValueChangedEvent evt)
	{
		if (evt.getSource() == this.typeIdField)
		{
            this.setDataValue();
            this.refreshDatas();
		}
        else if(evt.getSource() == currentPriceField)
        {
            if(currentPriceField.getText() != null && !currentPriceField.getText().equals("") && stockField.getText() != null && !stockField.getText().equals(""))
            {
                try
                {
                    double currentPrice = Double.valueOf(currentPriceField.getText());
                    double totalMoney = currentPrice*Double.valueOf(this.stockField.getText());
                    DecimalFormat df = new DecimalFormat("0.##");
                    this.totalMoneyField.setText(df.format(totalMoney));
                }
                catch(Exception ex)
                {
                    MessageBox.showErrorDialog(this, "数据格式不对！" );
                    this.currentPriceField.setText("");
                }
            }
        }
	}

    @Override
    public void doClear()
    {
        this.typeIdField.setEditorText("");
        this.typeNameField.setText("");
        this.catalogNameField.setText("");
        this.unitField.setText("");
        this.specField.setText("");
        this.stockField.setText("");
        this.totalMoneyField.setText("");
        this.commentsField.setText("");
    }

    @Override
    public void refreshArgs()
    {
        this.stockField.setText("");
    }

    @Override
    public void setDataValue()
    {
        if (this.typeIdField.getEditorText() != null && !this.typeIdField.getEditorText().equals(""))
        {
            Object obj = this.typeIdField.getSelectedItem();
            if (obj instanceof Product)
            {
                Product product = (Product)obj;
                IStorageService service = Main.getServiceManager().getStorageService();
                double stockPrice = service.getStorageStockPrice(storage, product);
                String stockShelf = service.getStorageStockShelf(storage, product);
                this.stockPriceField.setText(String.valueOf(stockPrice));
                this.stockShelfField.setText(stockShelf);
            }
        }
        this.setStockAmount();
    }
}
