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
package org.free_erp.client.ui.forms;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.ValueChangedEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.Item;
import com.jdatabeans.beans.data.JDataItemComboBox;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */

public class CBaseProductSelectDialog extends JDataDialog implements ActionListener, ValueChangedListener, ItemListener
{
    protected JDataPanelComboBox typeIdField;
    protected CField typeNameField;
    protected CField catalogNameField;
    protected CField specField;
    protected CField unitField;
    protected JDataComboBox shelfField;
    protected JDataItemComboBox priceField;
    protected JDataNumberField amountField;
    protected JDataField totalMoneyField;
    protected JDataField commentsField;
    protected JDataField stockField;
    protected CButton closeButton;
    protected CButton chooseButton;
    protected boolean saved = false;
    protected Storage storage;
    protected boolean isEditing = false;

    public CBaseProductSelectDialog(JDialog parent, IDataSource dataSource, Storage storage)
    {
        super(parent, dataSource);
        initC();
        this.storage = storage;
        this.setDataValue();
        this.doClear();
    }
    
   

    public CBaseProductSelectDialog(Frame parent, IDataSource dataSource)
    {
        super(parent, dataSource);
        initC();
        this.setDataValue();
        this.doClear();
    }

    public CBaseProductSelectDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent, dataSource);
        initC();
        this.setDataValue();
        this.doClear();
    }

    public void valueChanged(ValueChangedEvent evt)
    {
        if (evt.getSource() == this.typeIdField)
        {
            this.setDataValue();
            refreshDatas();
        } 
        else if (evt.getSource() == this.amountField)
        {
            String strAmount = this.amountField.getText();
            if (strAmount != null && !strAmount.equals(""))
            {
                try
                {
                    double amount = Double.valueOf(strAmount);
                    
                } 
                catch (Exception ex)
                {
                    MessageBox.showErrorDialog(this, "数据格式不对！" + ex.getMessage());
                    this.amountField.setText("");
                }
            }
            refreshMoney();
        }
    }

    public void refreshArgs()
    {
        this.stockField.setText("");
        //this.shelfField.setSelectedItem(null);
        //this.shelfField.clearItems();
        this.amountField.setText("");
        this.priceField.setSelectedItem(null);
        this.priceField.clearItems();
    }

    private void refreshMoney() {
        Object p = this.priceField.getSelectedItem();
        if (p == null || p.equals("")) {
            return;
        }
        String priceString = String.valueOf(p);
        String amountString = this.amountField.getText();
        if (amountString != null && !amountString.trim().equals("")) {
            try {
                double price = Double.parseDouble(priceString);
                double amount = Double.parseDouble(amountString);
                DecimalFormat df = new DecimalFormat("0.##");
                this.totalMoneyField.setText(df.format(price * amount));

            } catch (Exception ex) {
                
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }
        }
    }

    private void refreshDatas() {
        Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
            this.specField.setText(product.getSpec());
            if (!this.isEditing)
            {
                this.priceField.removeAllItems();
                //this.priceField.addItem(product.getStockPrice());
                this.priceField.addItem(new Item("库存价格", product.getStockPrice()));
            }
            this.catalogNameField.setText(product.getCatalog().getName());
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.closeButton) {
            doCancel();
        } else if (source == this.chooseButton) {
            doChoose();
        }
    }

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
        shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        shelfField.setEditable(true);
        priceField = new JDataItemComboBox("price", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时
        priceField.setEditable(true);
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totailMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
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
        panel.addComponent(shelfField, x + 420, y, 100, 20, "货位", 50);
        y += ROW_SPAN;
        panel.addComponent(stockField, x-10, y, 100, 20, "库存", 50);
        panel.addComponent(priceField, x + 160, y, 80, 20, "单价", 50);
        panel.addComponent(amountField, x + 300, y, 60, 20, "数量", 40);
        panel.addComponent(totalMoneyField, x + 420, y, 100, 20, "总金额", 50);
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
        this.priceField.addItemListener(this);
        this.amountField.addValueChangedListener(this);

        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

    public void doCancel() {
        setVisible(false);
        this.saved = false;
    }

    public boolean doCheckMaxMin()
    {
        return true;
    }

    public void doChoose()
    {
        if(typeIdField.getText().equals(""))
        {
            MessageBox.showErrorDialog(this, "商品编号不能为空！");
            return;
        }
        if(this.amountField != null)
        {
            String strAmount = this.amountField.getText();
            if (strAmount != null && !strAmount.equals(""))
            {
                try
                {
                    double amount = Double.valueOf(strAmount);
                    if (amount == 0)
                    {
                        MessageBox.showErrorDialog(this, " 商品数量不能为0");
                        this.amountField.setText("");
                        return;
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.showErrorDialog(this, "数据格式不对！" + ex.getMessage());
                    this.amountField.setText("");
                }
            }
        }
        
        if (!this.isEditing)
        {
            if (this.isProductExist())
            {
                return;
            }
        }
        try 
        {
            if(this.doCheckMaxMin())
            {
                this.updateDataSourceFromBeans();
            }
            else
            {
                return;
            }
        } catch (FieldRequiredException ex) {
            MessageBox.showErrorDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"没有正确填写或选择!");
            return;
        }
        //this.save();
        setVisible(false);
        this.saved = true;
    }

    public boolean isSaved()
    {
        return this.saved;
    }

    public void setIsEditing(boolean editing)
    {
        this.isEditing = editing;
        if (this.isEditing)
        {
            this.typeIdField.setEnabled(false);
            this.typeNameField.setEnabled(false);
            this.catalogNameField.setEnabled(false);
        } 
        else
        {
            this.doClear();
            this.typeIdField.setEnabled(true);
            this.typeNameField.setEnabled(true);
            this.catalogNameField.setEnabled(true);
        }
    }

    public void doClear()
    {
        //this.typeIdField.setEditorText("");
        this.typeNameField.setText("");
        this.catalogNameField.setText("");
        this.unitField.setText("");
        this.specField.setText("");
        this.shelfField.setText("");
//        this.priceField.setSelectedItem(null);
//        this.stockField.setText("");
//        this.amountField.setText("");
//        this.totalMoneyField.setText("");
//        this.commentsField.setText("");
    }

    public boolean isProductExist()
    {
        Product p = (Product)this.typeIdField.getSelectedItem();
        if (this.dataSource.containsColumnValue("product", p)) {
                MessageBox.showErrorDialog(this, "商品已经存在!");
                return true;
            }
        
        return false;
    }

    public void setStockAmount()
    {
        if (this.typeIdField.getEditorText() != null && !this.typeIdField.getEditorText().equals(""))
        {
            Object p = this.typeIdField.getSelectedItem();
            if (p instanceof Product)
            {
                double stockAmonut = Main.getServiceManager().getStorageService().getStorageProductAmount(this.storage, (Product)p);
                this.stockField.setText(String.valueOf(stockAmonut));
            }
        }
    }

    public void setDataValue()
    {
        this.setStockAmount();
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == priceField)
        {
            String strAmount = this.amountField.getText();
            if (strAmount != null && !strAmount.equals(""))
            {
                refreshMoney();
            }
        }
    }
}

