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

package org.free_erp.client.ui.forms.purchase;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
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
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CBasePurchaseProductDialog extends JDataDialog implements ActionListener, ValueChangedListener, ItemListener
{
    protected JDataPanelComboBox typeIdField;
    protected CField typeNameField;
    protected CField catalogNameField;
    protected CField specField;
    protected CField unitField;
    protected JDataComboBox shelfField;
    protected JDataComboBox priceField;
    protected JDataNumberField amountField;
    protected JDataField totalMoneyField;
    protected JDataField commentsField;
    protected JDataField stockField;
    protected JDataNumberField discountField;
    protected JDataNumberField discountPriceField;
    protected CButton closeButton;
    protected CButton chooseButton;
    protected boolean saved = false;
    protected Storage storage;
    protected boolean isEditing = false;

    public CBasePurchaseProductDialog(JDialog parent, IDataSource dataSource, Storage storage)
    {
        super(parent, dataSource);
        initC();
        this.storage = storage;
        this.doClear();
    }

    protected void initC()
    {
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 260);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired("商品编号", true);
        typeNameField = new CField();
        //typeNameField.setRequired("商品编号",true);//
        this.typeNameField.setEditable(false);
        catalogNameField = new CField();
        this.catalogNameField.setEditable(false);
        specField = new CField();
        specField.setEditable(false);
        unitField = new CField();
        unitField.setEditable(false);
        //shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataComboBox("price", Double.class, this.dataSource);
        this.priceField.setEditable(true);
        discountField = new JDataNumberField("discount", Double.class, this.dataSource);
        discountPriceField = new JDataNumberField("disprice", Double.class, this.dataSource);
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField("oldAmount", Double.class, this.dataSource);
        stockField.setEnabled(false);

        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("取消(&C)");
        chooseButton = new CButton("确定(&O)");
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.add(chooseButton);
        p.add(closeButton);
        p.setPreferredSize(new Dimension(650, 230));
        tabbedPane.setBounds(10, 5, 630, 180);
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
        panel.addComponent(priceField, x + 420, y, 100, 20, "单价", 50);
        y += ROW_SPAN;
        panel.addComponent(discountField, x-10, y, 85, 20, "折扣率", 50);
        panel.addComponent(new JLabel("%"), x + 75, y, 15, 20);
        panel.addComponent(discountPriceField, x + 160, y, 80, 20, "折扣价", 50);
        panel.addComponent(amountField, x + 300, y, 60, 20, "数量", 40);
        panel.addComponent(totalMoneyField, x + 420, y, 100, 20, "总金额", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x-10, y, 530, 20, "备注", 50);
        
        this.chooseButton.setBounds(x + 390, 200, 80, 25);
        this.closeButton.setBounds(x + 470, 200, 80, 25);

        tabbedPane.addTab("商品信息", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        this.typeIdField.addValueChangedListener(this);
        this.priceField.addItemListener(this);
        this.amountField.addValueChangedListener(this);
        this.discountField.addValueChangedListener(this);
        this.discountPriceField.addValueChangedListener(this);

        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == this.closeButton)
        {
            doCancel();
        }
        else if (source == this.chooseButton)
        {
            doChoose();
        }
    }

    public void valueChanged(ValueChangedEvent evt)
    {
        if (evt.getSource() == this.typeIdField)
        {
            refreshDatas();
        }
        else if(evt.getSource() == this.discountField)
        {
            Object p = this.priceField.getSelectedItem();
            if (p != null && !p.equals(""))
            {
                String strDisAmount = discountField.getText();
                String priceString = String.valueOf(p);
                if(strDisAmount != null && !strDisAmount.trim().equals(""))
                {
                    try
                    {
                        double price = Double.parseDouble(priceString);
                        double disAmount = Double.valueOf(strDisAmount);
                        if(disAmount > 100)
                        {
                            this.discountField.setText("100");
                            disAmount = 100;
                        }
                        DecimalFormat df = new DecimalFormat("0.##");
                        this.discountPriceField.setText(df.format(price * disAmount /100));
                        refreshMoney();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.showErrorDialog(this, "数据格式不对！");
                    }
                }
                else
                {
                    this.discountField.setText("100");
                    this.discountPriceField.setText(priceString);
                }
            }
        }
        else if(evt.getSource() == this.discountPriceField)
        {
            Object p = this.priceField.getSelectedItem();
            if (p != null && !p.equals(""))
            {
                String strDisPrice = this.discountPriceField.getText();
                String priceString = String.valueOf(p);
                if(strDisPrice != null && !strDisPrice.trim().equals(""))
                {
                    try
                    {
                        double price = Double.parseDouble(priceString);
                        double disPrice = Double.valueOf(strDisPrice);
                        if(disPrice > price)
                        {
                            disPrice = price;
                            this.discountPriceField.setText(priceString);
                        }
                        if(price == 0d)
                        {
                            this.discountField.setText("100");
                            this.discountPriceField.setText("0");
                        }
                        else
                        {
                            DecimalFormat df = new DecimalFormat("0.##");
                            this.discountField.setText(df.format(disPrice * 100 / price));
                        }
                        refreshMoney();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.showErrorDialog(this, "数据格式不对！");
                    }
                }
                else
                {
                    this.discountField.setText("100");
                    this.discountPriceField.setText(priceString);
                }
            }
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
                    MessageBox.showErrorDialog(this, "数据格式不对！");
                    this.amountField.setText("");
                }
            }
            refreshMoney();
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == priceField)
        {
            String strAmount = this.amountField.getText();
            String strDiscount = this.discountField.getText();
            Object p = this.priceField.getSelectedItem();
            if (p != null && !p.equals("") && strDiscount != null && !strDiscount.equals(""))
            {
                String priceString = String.valueOf(p);
                try
                {
                    double price = Double.parseDouble(priceString);
                    double discount = Double.valueOf(strDiscount);
                    DecimalFormat df = new DecimalFormat("0.##");
                    this.discountPriceField.setText(df.format(price * discount /100));
                    if(price == 0)
                    {
                        this.discountField.setText("100");
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.showErrorDialog(this, "数据格式不对！333");
                }
            }
            if (strAmount != null && !strAmount.equals("") && strDiscount != null && !strDiscount.equals(""))
            {
                refreshMoney();
            }
        }
    }

    public void refreshArgs()
    {
        this.stockField.setText("");
        this.priceField.setSelectedItem(null);
        this.priceField.clearItems();
//        this.discountField.setText("");
//        this.discountPriceField.setText("");
    }

    public void refreshMoney()
    {
        Object p = this.priceField.getSelectedItem();
        if (p == null || p.equals(""))
        {
            return;
        }
        String priceString = String.valueOf(p);

        String amountString = this.amountField.getText();
        String discountStr = this.discountField.getText();
        if (amountString != null && !amountString.trim().equals("") && discountStr != null && !discountStr.trim().equals(""))
        {
            try
            {
                double price = Double.parseDouble(priceString);
                double amount = Double.parseDouble(amountString);
                double discount = Double.parseDouble(discountStr);
                DecimalFormat df = new DecimalFormat("0.##");
                this.totalMoneyField.setText(df.format(price * amount * discount/100));
            }
            catch (Exception ex)
            {
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }
        }
    }

    public void refreshDatas()
    {
        Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
            this.specField.setText(product.getSpec());
            if (!this.isEditing)
            {
                Object p = this.priceField.getSelectedItem();
                if (p == null || p.equals(""))
                {
                    this.priceField.removeAllItems();
                    this.priceField.addItem(product.getStockPrice());
                }
            }
            this.catalogNameField.setText(product.getCatalog().getName());
        }
    }

    public void doCancel()
    {
        setVisible(false);
        this.saved = false;
    }

    public void doChoose()
    {
        if(typeIdField.getText().equals(""))
        {
            MessageBox.showErrorDialog(this, "商品编号不能为空！");
            return;
        }
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
        if (!this.isEditing)
        {
            if (this.isProductExist())
            {
                return;
            }
        }
        try
        {
            this.updateDataSourceFromBeans();
        } 
        catch (FieldRequiredException ex)
        {
            MessageBox.showErrorDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"没有正确填写或选择!");
            return;
        }
        //this.save();
        setVisible(false);
        this.saved = true;
    }

    public boolean isSaved() {
        return this.saved;
    }

    public void setIsEditing(boolean editing) {
        this.isEditing = editing;
        if (this.isEditing) {
            this.typeIdField.setEnabled(false);
            this.typeNameField.setEnabled(false);
            this.catalogNameField.setEnabled(false);
        } else {
            this.doClear();
            this.typeIdField.setEnabled(true);
            this.typeNameField.setEnabled(true);
            this.catalogNameField.setEnabled(true);
        }
    }

    public void doClear()
    {
//        this.typeIdField.setEditorText("");
        this.typeNameField.setText("");
        this.catalogNameField.setText("");
        this.unitField.setText("");
        this.specField.setText("");
//        this.priceField.setText("");
//        this.stockField.setText("");
//        this.discountField.setText("");
//        this.discountPriceField.setText("");
//        this.totalMoneyField.setText("");
//        this.commentsField.setText("");
    }

    public boolean isProductExist()
    {
        Product p = (Product)this.typeIdField.getSelectedItem();
        if (this.dataSource.containsColumnValue("product", p))
        {
            MessageBox.showErrorDialog(this, "商品已经存在!");
            return true;
        }
        return false;
    }
}
