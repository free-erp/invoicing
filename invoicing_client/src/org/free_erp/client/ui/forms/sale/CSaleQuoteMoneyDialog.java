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
package org.free_erp.client.ui.forms.sale;

/**
 *
 * @author Administrator
 */
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.data.JDataItemComboBox;
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
 * @author tzl
 */
public class CSaleQuoteMoneyDialog extends CBaseProductSelectDialog implements ActionListener, ValueChangedListener {

    private JDataNumberField currentPriceField;
    private JDataComboBox taxPriceField;
    private JDataComboBox taxField;
       private JDataComboBox price1Field;


    public CSaleQuoteMoneyDialog(Frame parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    public CSaleQuoteMoneyDialog(JDialog parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    public CSaleQuoteMoneyDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }

    @Override
    protected void initC() {
        this.getContentPane().setLayout(new BorderLayout());
       this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 220);
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
        shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataItemComboBox("price", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时

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
        price1Field = new JDataComboBox("price", Double.class, this.dataSource);
        price1Field.setEditable(true);
        taxPriceField = new JDataComboBox("taxPrice", Double.class, this.dataSource);
        taxPriceField.setEditable(true);
        taxField = new JDataComboBox("tax", Double.class, this.dataSource);
        taxField.setEditable(true);
        

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(650, 210));
        tabbedPane.setBounds(10, 5, 630, 230);
        CPanel panel = new CPanel();
        panel.setLayout(null);
        int x = 80;
        int y = 10; // row
        panel.addComponent(typeIdField, x - 10, y, 100, 20, "商品编码", 50);
        panel.addComponent(typeNameField, x + 160, y, 200, 20, "商品名称", 60);
        panel.addComponent(catalogNameField, x + 420, y, 100, 20, "商品类别", 50);

        y += ROW_SPAN;
        panel.addComponent(unitField, x - 10, y, 100, 20, "单位", 50);
        panel.addComponent(specField, x + 160, y, 200, 20, "规格型号", 60);
        panel.addComponent(shelfField, x + 420, y, 100, 20, "货位", 50);
     y += ROW_SPAN;
        panel.addComponent(price1Field, x - 10, y, 100, 20, "单价", 50);
        panel.addComponent(taxField, x + 160, y, 200, 20, "税率", 60);
        panel.addComponent(taxPriceField, x + 420, y, 100, 20, "含税价", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x - 10, y, 530, 20, "备注", 50);
        y += ROW_SPAN + 10;
        panel.addComponent(chooseButton, x + 350, y, 80, 25);
        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("商品信息", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
//        this.currentPriceField.addValueChangedListener(this);

        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

    private void refreshDatas() {
        Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof IDataRow) {
            IDataRow dataRow = (IDataRow) obj;
            String name = (String) dataRow.getColumnValue("name");
            if (name != null) {
                this.typeNameField.setText(name);
            }
            String spec = (String) dataRow.getColumnValue("spec");
            if (spec != null) {
                this.specField.setText(spec);
            }
            String unit = (String) dataRow.getColumnValue("smallUnit");
            if (unit != null) {
                this.unitField.setText(unit);
            }
            ProductCatalog catalog = (ProductCatalog) dataRow.getColumnValue("catalog");
            if (catalog != null) {
                this.catalogNameField.setText(catalog.getName());
            }
            //添加产品id信息
            IDataRow cRow = this.dataSource.getCurrentDataRow();
            cRow.setColumnValue("productId", dataRow.getColumnValue("id"));

        }
    }

    @Override
    public void valueChanged(ValueChangedEvent evt) {

        if (evt.getSource() == this.typeIdField) {
            this.setStockAmount();
//            this.setStockPrice();
//            this.setStockTotalMoney();
            refreshDatas();
        } else if (evt.getSource() == currentPriceField) {
            if (currentPriceField.getText() != null && !currentPriceField.getText().equals("") && stockField.getText() != null && !stockField.getText().equals("")) {
                try {
                    double currentPrice = Double.valueOf(currentPriceField.getText());
                    double totalMoney = currentPrice * Integer.valueOf(this.stockField.getText());
                    this.totalMoneyField.setText(String.valueOf(totalMoney));
                } catch (Exception ex) {
                    MessageBox.showErrorDialog(this, "数据格式不对！");
                    this.currentPriceField.setText("");
                }
            }
        }
    }

//    private void setStockPrice() {
//        if (this.typeIdField.getEditorText() != null && !this.typeIdField.getEditorText().equals("")) {
//            double stockPrice = Main.getServiceManager().getStorageService().getStorageStockPrice(Main.getMainFrame().getCompany(), this.typeIdField.getEditorText(), this.storage);
//            this.priceField.setText(String.valueOf(stockPrice));
//        }
//    }

//    private void setStockTotalMoney() {
//        if (this.typeIdField.getEditorText() != null && !this.typeIdField.getEditorText().equals("")) {
//            double stockTotalMoney = Main.getServiceManager().getStorageService().getStorageStockPrice(storage, product)getStorageTotalMoney(Main.getMainFrame().getCompany(), this.typeIdField.getEditorText(), this.storage);
//            this.totalMoneyField.setText(String.valueOf(stockTotalMoney));
//        }
//    }

    @Override
    public void setDataValue() {
        this.setStockAmount();
//        this.setStockPrice();
    }

    @Override
    public void refreshArgs() {
        this.stockField.setText("");
    //this.shelfField.setSelectedItem(null);
    //this.shelfField.clearItems();
    }

    @Override
    public void doClear() {
        this.typeIdField.setEditorText("");
        this.typeNameField.setText("");
        this.catalogNameField.setText("");
        this.unitField.setText("");
        this.specField.setText("");
        this.shelfField.setText("");
        this.stockField.setText("");
//        this.currentPriceField.setText("");
        this.totalMoneyField.setText("");
        this.commentsField.setText("");
    }
}
