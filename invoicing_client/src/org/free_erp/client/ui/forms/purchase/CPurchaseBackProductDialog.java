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
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseBackProductDialog extends CBasePurchaseProductDialog
{
    private JDataNumberField taxRateField;
    private JDataNumberField priceTaxMoneyField;
    protected Customer supplier;

    public CPurchaseBackProductDialog(JDialog parent, IDataSource dataSource, Storage storage,Customer supplier)
    {
        super(parent, dataSource, storage);
        this.supplier = supplier;
        this.setPurchaseStockAmount();
    }

    @Override
    protected void initC()
    {
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("�����Ʒ");
        this.setSize(650, 290);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired("��Ʒ���", true);
        typeNameField = new CField();
        //typeNameField.setRequired("��Ʒ���",true);//
        this.typeNameField.setEditable(false);
        catalogNameField = new CField();
        this.catalogNameField.setEditable(false);
        specField = new CField();
        specField.setEditable(false);
        unitField = new CField();
        unitField.setEditable(false);
        //shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataComboBox("price", Double.class, this.dataSource);
        priceField.setEditable(true);
        discountField = new JDataNumberField("discount", Double.class, this.dataSource);
        discountPriceField = new JDataNumberField("disprice", Double.class, this.dataSource);
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("��Ʒ����",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
        stockField.setEnabled(false);
        taxRateField = new JDataNumberField("taxRate", Integer.class, this.dataSource);
        priceTaxMoneyField = new JDataNumberField("priceTaxMoney", Double.class, this.dataSource);

        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("ȡ��(&C)");
        chooseButton = new CButton("ȷ��(&O)");
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.add(chooseButton);
        p.add(closeButton);
        p.setPreferredSize(new Dimension(650, 316));
        tabbedPane.setBounds(10, 5, 630, 210);
        CPanel panel = new CPanel();
        panel.setLayout(null);
        int x = 80;
        int y = 10; // row
        panel.addComponent(typeIdField, x-10, y, 100, 20, "��Ʒ����", 50);
        panel.addComponent(typeNameField, x + 160, y, 200, 20, "��Ʒ����", 60);
        panel.addComponent(catalogNameField, x + 420, y, 100, 20, "��Ʒ���", 50);

        y += ROW_SPAN;
        panel.addComponent(unitField, x-10, y, 100, 20, "��λ", 50);
        panel.addComponent(specField, x + 160, y, 200, 20, "����ͺ�", 60);
        panel.addComponent(priceField, x + 420, y, 100, 20, "����", 50);
        y += ROW_SPAN;
        panel.addComponent(discountField, x-10, y, 85, 20, "�ۿ���", 50);
        panel.addComponent(new JLabel("%"), x + 75, y, 15, 20);
        panel.addComponent(discountPriceField, x + 160, y, 80, 20, "�ۿۼ�", 50);
        panel.addComponent(amountField, x + 300, y, 60, 20, "����", 50);
        panel.addComponent(totalMoneyField, x + 420, y, 100, 20, "�ܽ��", 50);
//        y += ROW_SPAN;
//        panel.addComponent(taxRateField, x-10, y, 100, 20, "˰��", 50);
//        panel.addComponent(priceTaxMoneyField, x + 160, y, 80, 20, "˰��", 50);
//        panel.addComponent(stockField, x + 300, y, 60, 20, "�ɹ�����", 50);
        y += ROW_SPAN;
        panel.addComponent(stockField, x-10, y, 100, 20, "�ɹ�����", 50);//��ʱ
        y += ROW_SPAN;
        panel.addComponent(commentsField, x-10, y, 530, 20, "��ע", 50);

        this.chooseButton.setBounds(x + 390, 230, 80, 25);
        this.closeButton.setBounds(x + 470, 230, 80, 25);

        tabbedPane.addTab("��Ʒ��Ϣ", panel);
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

    public void setPurchaseStockAmount()
    {
        if (this.typeIdField.getSelectedItem() != null)
        {
            Product product = (Product)this.typeIdField.getSelectedItem();
            double stockAmonut = Main.getServiceManager().getPurchaseService().getPurchaseStockAmount(this.supplier, this.storage, product);
            this.stockField.setText(String.valueOf(stockAmonut));
        }
    }

    @Override
    public void valueChanged(ValueChangedEvent evt)
    {
        if (evt.getSource() == this.typeIdField)
        {
            //this.amountField.setText("");
            refreshDatas();
            this.setPurchaseStockAmount();
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
                        MessageBox.showErrorDialog(this, "���ݸ�ʽ���ԣ�");
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
                        DecimalFormat df = new DecimalFormat("0.##");
                        if(disPrice > price)
                        {
                            disPrice = price;
                            this.discountPriceField.setText(priceString);
                        }
                        this.discountField.setText(df.format(disPrice * 100 / price));
                        refreshMoney();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.showErrorDialog(this, "���ݸ�ʽ���ԣ�");
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
            String strStockAmount = this.stockField.getText();
            if (strAmount != null && !strAmount.equals("") && strStockAmount != null && !strStockAmount.equals(""))
            {
                try
                {
                    double amount = Double.valueOf(strAmount);
                    double stockAmount = Double.valueOf(strStockAmount);
                    
                    if(amount > stockAmount && stockAmount != 0)
                    {
                        MessageBox.showErrorDialog(this, " �ɹ���Ʒ������ֻ�С�" + strStockAmount +"����");
                        this.amountField.setText(strStockAmount);
                    }
                    if(amount > stockAmount && stockAmount == 0)
                    {
                        MessageBox.showErrorDialog(this, " �ɹ���Ʒ������Ϊ0");
                        this.amountField.setText("");
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.showErrorDialog(this, "���ݸ�ʽ���ԣ�" + ex.getMessage());
                    this.amountField.setText("");
                }
            }
            refreshMoney();
        }
    }
}
