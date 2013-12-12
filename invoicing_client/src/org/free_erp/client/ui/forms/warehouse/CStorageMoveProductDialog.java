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
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */

public class CStorageMoveProductDialog extends CStorageOutProductDialog
{
    public CStorageMoveProductDialog(JDialog parent, IDataSource dataSource, Storage storage)
    {
        super(parent, dataSource, storage);
    }
    
    @Override
    protected void initC()
    {
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("�����Ʒ");
        this.setSize(650, 270);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired("��Ʒ���", true);
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
        stockPriceField = new JDataField("price", Double.class, this.dataSource);
        stockPriceField.setEnabled(false);
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("�ƿ�����",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totailMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField("oldAmount", Double.class, this.dataSource);
        stockField.setEnabled(false);

        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("ȡ��(&C)");
        chooseButton = new CButton("ȷ��(&O)");
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
        panel.addComponent(typeIdField, x-10, y, 100, 20, "��Ʒ����", 50);
        panel.addComponent(typeNameField, x + 160, y, 200, 20, "��Ʒ����", 60);
        panel.addComponent(catalogNameField, x + 420, y, 100, 20, "��Ʒ���", 50);

        y += ROW_SPAN;
        panel.addComponent(unitField, x-10, y, 100, 20, "��λ", 50);
        panel.addComponent(specField, x + 160, y, 200, 20, "����ͺ�", 60);
        panel.addComponent(stockShelfField, x + 420, y, 100, 20, "��λ", 50);
        y += ROW_SPAN;
        panel.addComponent(stockField, x-10, y, 100, 20, "�������", 50);
        panel.addComponent(stockPriceField, x + 160, y, 80, 20, "��浥��", 50);
        panel.addComponent(amountField, x + 300, y, 60, 20, "�ƿ�����", 50);
        panel.addComponent(totalMoneyField, x + 420, y, 100, 20, "�ƿ���", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x-10, y, 530, 20, "��ע", 50);
        y += ROW_SPAN + 10;
        panel.addComponent(chooseButton, x + 350, y, 80, 25);
        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("��Ʒ��Ϣ", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        this.typeIdField.addValueChangedListener(this);
        this.amountField.addValueChangedListener(this);

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
        else if (evt.getSource() == this.amountField)
        {
            String strAmount = this.amountField.getText();
            String stockAmount = this.stockField.getText();

            if (strAmount != null && !strAmount.equals("") && stockAmount != null && !stockAmount.equals(""))
            {
                try
                {
                    double amount = Double.valueOf(strAmount);
                    double stockamount = Double.valueOf(stockAmount);

                    if(stockamount == 0)
                    {
                        this.amountField.setText("");
                        return;
                    }
                    if (amount > stockamount)
                    {
                        this.amountField.setText(stockAmount);
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