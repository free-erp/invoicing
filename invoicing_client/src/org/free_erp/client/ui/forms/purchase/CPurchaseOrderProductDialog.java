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
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseOrderProductDialog extends CBasePurchaseProductDialog
{
    private JDataNumberField taxRateField;
    private JDataNumberField priceTaxMoneyField;
    
    public CPurchaseOrderProductDialog(JDialog parent, IDataSource dataSource, Storage storage)
    {
        super(parent, dataSource, storage);
    }

    @Override
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
        priceField.setEditable(true);
        discountField = new JDataNumberField("discount", Double.class, this.dataSource);
        discountPriceField = new JDataNumberField("disprice", Double.class, this.dataSource);
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField("oldAmount", Double.class, this.dataSource);
        stockField.setEnabled(false);
        taxRateField = new JDataNumberField("taxRate", Integer.class, this.dataSource);
        priceTaxMoneyField = new JDataNumberField("priceTaxMoney", Double.class, this.dataSource);

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
//        y += ROW_SPAN;
//        panel.addComponent(taxRateField, x-10, y, 100, 20, "税率", 50);
//        panel.addComponent(priceTaxMoneyField, x + 160, y, 80, 20, "税额", 50);
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
}
