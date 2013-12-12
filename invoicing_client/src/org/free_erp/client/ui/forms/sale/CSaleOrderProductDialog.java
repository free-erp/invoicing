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
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.Item;
import com.jdatabeans.beans.data.JDataItemComboBox;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.logic.ISaleService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author tzl
 */
public class CSaleOrderProductDialog extends CSaleProductDialog implements ActionListener, ValueChangedListener{

    private JDataNumberField currentPriceField;
    private JDataNumberField taxPriceField;
    private JDataNumberField taxPriceMoneyField;
    private JDataComboBox taxField;
    private JDataNumberField taxMoneyField;
    private JDataNumberField rateField;
 private String Price0Name = "价格1";
    private String Price1Name = "价格2";
    private String Price2Name = "价格3";
    private String Price3Name = "价格4";
    private String Price4Name = "价格5";
    private String Price5Name = "价格6";
    private Customer customer;
    public CSaleOrderProductDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }
public CSaleOrderProductDialog(JDialog parent, IDataSource dataSource, Storage storage, Customer customer) {

        super(parent, dataSource, storage);
        this.customer = customer;
    }
    @Override
    protected void initC() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 280);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired(true);
        typeIdField.setDisplayName("商品编号");
        typeNameField = new CField();
        this.typeNameField.setEditable(false);
        catalogNameField = new CField();
        this.catalogNameField.setEditable(false);
        specField =new CField();
        specField.setEditable(false);
        unitField = new CField();
        unitField.setEditable(false);
        shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataItemComboBox("price", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时
        priceField.setEditable(true);
        priceField.setRequired("单价", true);//
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量", true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
        stockField.setEnabled(false);
        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("取消(&C)");
        chooseButton = new CButton("确定(&O)");
        taxPriceField = new JDataNumberField("taxPrice", Double.class, this.dataSource);
        taxPriceField.addValueChangedListener(this);
        taxPriceMoneyField = new JDataNumberField("taxPriceMoney", Double.class, this.dataSource);
        taxField = new JDataComboBox("tax", Double.class, this.dataSource);
        taxField.setEditable(true);
        taxMoneyField = new JDataNumberField("taxMoney", Double.class, this.dataSource);
        rateField = new JDataNumberField("rate", Double.class, this.dataSource);
        rateField.setEditable(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(650, 260));
        tabbedPane.setBounds(10, 5, 630, 250);
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
        panel.addComponent(priceField, x - 10, y, 100, 20, "单价", 50);
        panel.addComponent(rateField, x + 160, y, 50, 20, "折扣率", 50);
        panel.addComponent(taxPriceField, x + 290, y, 70, 20, "%    折扣价", 80);
        panel.addComponent(stockField, x + 420, y, 100, 20, "库存", 50);
        y += ROW_SPAN;
        panel.addComponent(amountField, x - 10, y, 100, 20, "数量", 50);
        panel.addComponent(totalMoneyField, x + 160, y, 100, 20, "总金额", 50);
//        panel.addComponent(taxField, x + 290, y, 80, 20, "税率", 50);
//        panel.addComponent(taxMoneyField, x + 430, y, 90, 20, "税额", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x - 10, y, 530, 20, "备注", 50);
        y += ROW_SPAN + 10;
        panel.addComponent(chooseButton, x + 350, y, 80, 25);
        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("商品信息", panel);

//        p.add(chooseButton);
//        p.add(closeButton);
//        y += ROW_SPAN;
//        y += ROW_SPAN;
//        y += ROW_SPAN;
//        this.chooseButton.setBounds(x + 320, y, 80, 25);
//        this.closeButton.setBounds(x + 420, y, 80, 25);
        this.getContentPane().add(p);

        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
//        this.currentPriceField.addValueChangedListener(this);
        rateField.addValueChangedListener(this);
        taxPriceField.addValueChangedListener(this);
        amountField.addValueChangedListener(this);
        taxField.addValueChangedListener(this);
//        this.priceField.addItemListener(this);
          this.priceField.addValueChangedListener(this);
        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

   private void refreshDatas() {
       this.doClear();
        Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
//            this.specField.setText(product.getSpec());
            this.priceField.removeAllItems();
            List<Item> items = new ArrayList();
              if (Main.getMainFrame().getObjectsPool().getPriceName() != null) {

                 Price0Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName0();
                Price1Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName1();
                Price2Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName2();
             Price3Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName3();
                Price4Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName4();
                Price5Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName5();

            }
               if (this.getHistoryPeice() != 0d) {
             items.add(new Item("上次销售价", this.getHistoryPeice()));
                     }
            items.add(new Item(Price0Name, product.getPrice0()));
            items.add(new Item(Price1Name, product.getPrice1()));
            items.add(new Item(Price2Name, product.getPrice2()));
            items.add(new Item(Price3Name, product.getPrice3()));
            items.add(new Item(Price4Name, product.getPrice4()));
            items.add(new Item(Price5Name, product.getPrice5()));
            items.add(new Item("零售价", product.getRetailPrice()));


            this.priceField.setItems(items);
            /*
            this.priceField.addItem(product.getPrice0());
            this.priceField.addItem(product.getPrice1());
            this.priceField.addItem(product.getPrice2());
            this.priceField.addItem(product.getPrice3());
            this.priceField.addItem(product.getPrice4());
            this.priceField.addItem(product.getPrice5());
            this.priceField.addItem(product.getRetailPrice());
             *
             */
            this.catalogNameField.setText(product.getCatalog().getName());
        }
    }
  private Double getHistoryPeice() {
        Double peice = 0d;
        ISaleService saleService = Main.getServiceManager().getSaleService();
        Object obj = this.typeIdField.getSelectedItem();
        if (customer != null) {
            if (obj instanceof Product) {
                Product product = (Product) obj;
                peice = saleService.getSalePriceDaetails(product, customer, Main.getMainFrame().getCompany());

            }
        }
        return peice;
    }
    private void refreshPrice() {
        Object p = this.priceField.getSelectedItem();
        if (p == null || p.equals("")) {
            return;
        }
        String priceString = String.valueOf(p);
        String rateString = this.rateField.getText();
        if (rateString != null && !rateString.trim().equals("")) {
            try {
                double price = Double.parseDouble(priceString);
                double rate = Double.parseDouble(rateString);
          
                if(price > 0){
               double taxPrice = rate / 100 * price;
             
                this.taxPriceField.setText(String.valueOf(taxPrice));
                }else{
                 this.taxPriceField.setText(String.valueOf(0.00));
                }
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }


        }
    }

    private void refreshPrices() {
        Object p = this.priceField.getSelectedItem();
        String priceString = String.valueOf(p);
        String tPrice = taxPriceField.getText();
        try {
            double price = Double.parseDouble(priceString);
            double sPrice = Double.parseDouble(tPrice);
                if(price>0){
              double  s = sPrice / price * 100;
      
               if(s>100.00){
                 MessageBox.showErrorDialog(this, "折扣率不能超过100%！");
                      taxPriceField.setText(String.valueOf(p));
               return;

            }
            this.rateField.setText(String.valueOf(s));
            }else{
             taxPriceField.setText("0.00");
             this.rateField.setText(String.valueOf(100));
            }

        } catch (Exception ex) {

            taxPriceField.setText("");
                return;
        }

    }

    @Override
    public void setDataValue() {
        if (this.storage != null) {
            this.setStockAmount();
        }
    }

    @Override
    public void refreshArgs() {
        this.stockField.setText("");
    //this.shelfField.setSelectedItem(null);
    //this.shelfField.clearItems();
    }

//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        if (e.getSource() == priceField)
//        {
//             Object p = this.priceField.getSelectedItem();
//             if (p != null && !p.equals(""))
//             {
//                 String priceString = String.valueOf(p);
//                 double price = Double.parseDouble(priceString);
//                 if(price < 0)
//                 {
//                     MessageBox.showErrorDialog(this, " 销售价格不能小于0");
//                     return;
//                  }
//                 String strAmount = this.amountField.getText();
//                 if (strAmount != null && !strAmount.equals(""))
//                 {
//                     refreshAmount();
//                 }
//                 this.refreshPrice();
//              }
//        }
//    }

    @Override
    public void valueChanged(ValueChangedEvent evt) {
        if (evt.getSource() == this.typeIdField) {
            this.setDataValue();
            refreshDatas();
        }
        if (evt.getSource() == this.rateField) {
             Double rate= Double.valueOf(rateField.getText());
            if(rate>100){
                 rateField.setText(String.valueOf(100));
            }
            this.refreshPrice();
            refreshAmount();
        }
        if (evt.getSource() == this.amountField) {
            this.refreshAmount();

        }
        if (evt.getSource() == this.priceField) {

            Object p = this.priceField.getSelectedItem();
            if (p != null && !p.equals("")) {
                String priceString = String.valueOf(p);
                double price = Double.parseDouble(priceString);
                if (price < 0) {
                    MessageBox.showErrorDialog(this, " 销售价格小于0");
                    return;
                }
                String strAmount = this.amountField.getText();
                if (strAmount != null && !strAmount.equals("")) {
                        this.refreshPrice();
                    refreshAmount();
                }else{
                    this.refreshPrice();
                }
            
            }
        }
        if (evt.getSource() == this.taxPriceField) {
            this.refreshPrices();
         
            refreshAmount();
        }

    }

    private void refreshAmount() {
        Object p = this.taxPriceField.getText();
        if (p == null || p.equals("")) {
              return;

        }
        String rateString = String.valueOf(p);
        String amountString = this.amountField.getText();
        if (amountString != null && !amountString.trim().equals("")) {
            try {
                double amount = Double.parseDouble(amountString);
                double rate = Double.parseDouble(rateString);
                double Price = rate * amount;
           
                this.totalMoneyField.setText(String.valueOf(Price));
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }
        }
    }
}
/*
public class CSaleOrderProductDialog extends CSaleProductDialog implements ActionListener, ValueChangedListener, ItemListener {

    private JDataNumberField currentPriceField;
    private JDataNumberField taxPriceField;
    private JDataNumberField taxPriceMoneyField;
    private JDataComboBox taxField;
    private JDataNumberField taxMoneyField;
    private JDataNumberField rateField;

    public CSaleOrderProductDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }

    @Override
    protected void initC() {
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 280);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired(true);
        typeIdField.setDisplayName("商品编号");
        typeNameField = new CField();
        this.typeNameField.setEditable(false);
        catalogNameField = new CField();
        this.catalogNameField.setEditable(false);
        specField =new CField();
        specField.setEditable(false);
        unitField = new CField();
        unitField.setEditable(false);
        shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataComboBox("price", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时
        priceField.setEditable(true);
        this.priceField.addItemListener(this);
        priceField.setRequired("单价", true);//
        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量", true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
        stockField.setEnabled(false);
        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("取消(&C)");
        chooseButton = new CButton("确定(&O)");
        taxPriceField = new JDataNumberField("taxPrice", Double.class, this.dataSource);
        taxPriceField.addValueChangedListener(this);
        taxPriceMoneyField = new JDataNumberField("taxPriceMoney", Double.class, this.dataSource);
        taxField = new JDataComboBox("tax", Double.class, this.dataSource);
        taxField.setEditable(true);
        taxMoneyField = new JDataNumberField("taxMoney", Double.class, this.dataSource);
        rateField = new JDataNumberField("rate", Double.class, this.dataSource);
        rateField.setEditable(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(650, 250));
        tabbedPane.setBounds(10, 5, 630, 250);
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
        panel.addComponent(priceField, x - 10, y, 100, 20, "单价", 50);
        panel.addComponent(rateField, x + 160, y, 50, 20, "折扣率", 50);
        panel.addComponent(taxPriceField, x + 290, y, 70, 20, "%    折扣价", 80);
        panel.addComponent(stockField, x + 420, y, 100, 20, "库存", 50);
        y += ROW_SPAN;
        panel.addComponent(amountField, x - 10, y, 100, 20, "数量", 50);
        panel.addComponent(totalMoneyField, x + 160, y, 100, 20, "总金额", 50);
//        panel.addComponent(taxField, x + 290, y, 80, 20, "税率", 50);
//        panel.addComponent(taxMoneyField, x + 430, y, 90, 20, "税额", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x - 10, y, 530, 20, "备注", 50);
        y += ROW_SPAN + 10;
        panel.addComponent(chooseButton, x + 350, y, 80, 25);
        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("商品信息", panel);

//        p.add(chooseButton);
//        p.add(closeButton);
//        y += ROW_SPAN;
//        y += ROW_SPAN;
//        y += ROW_SPAN;
//        this.chooseButton.setBounds(x + 320, y, 80, 25);
//        this.closeButton.setBounds(x + 420, y, 80, 25);
        this.getContentPane().add(p);

        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
//        this.currentPriceField.addValueChangedListener(this);
        rateField.addValueChangedListener(this);
        taxPriceField.addValueChangedListener(this);
        amountField.addValueChangedListener(this);
        taxField.addValueChangedListener(this);
        this.priceField.addItemListener(this);
        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

   private void refreshDatas() {
        Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
//            this.specField.setText(product.getSpec());
//            this.priceField.removeAllItems();
            this.priceField.addItem(product.getPrice0());
            this.priceField.addItem(product.getPrice1());
            this.priceField.addItem(product.getPrice2());
            this.priceField.addItem(product.getPrice3());
            this.priceField.addItem(product.getPrice4());
            this.priceField.addItem(product.getPrice5());
            this.priceField.addItem(product.getRetailPrice());
            this.catalogNameField.setText(product.getCatalog().getName());
        }
    }

    private void refreshPrice() {
        Object p = this.priceField.getSelectedItem();
        if (p == null || p.equals("")) {
            return;
        }
        String priceString = String.valueOf(p);
        String rateString = this.rateField.getText();
        if (rateString != null && !rateString.trim().equals("")) {
            try {
                double price = Double.parseDouble(priceString);
                double rate = Double.parseDouble(rateString);
                DecimalFormat df = new DecimalFormat("##.00");
                rate = Double.parseDouble(df.format(rate));
                if(price > 0){
               double taxPrice = rate / 100 * price;
                taxPrice = Double.parseDouble(df.format(taxPrice));
                this.taxPriceField.setText(String.valueOf(taxPrice));
                }else{
                 this.taxPriceField.setText(String.valueOf(0.00));
                }
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }


        }
    }

    private void refreshPrices() {
        Object p = this.priceField.getSelectedItem();
        String priceString = String.valueOf(p);
        String tPrice = taxPriceField.getText();
        try {
            double price = Double.parseDouble(priceString);
            double sPrice = Double.parseDouble(tPrice);
                if(price>0){
              double  s = sPrice / price * 100;
            DecimalFormat df = new DecimalFormat("##.00");
            s = Double.parseDouble(df.format(s));
               if(s>100.00){
                 MessageBox.showErrorDialog(this, "折扣率不能超过100%！");
                      taxPriceField.setText(String.valueOf(p));
               return;

            }
            this.rateField.setText(String.valueOf(s));
            }else{
             taxPriceField.setText("0.00");
             this.rateField.setText(String.valueOf(100));
            }

        } catch (Exception ex) {
            MessageBox.showErrorDialog(this, "数据格式不对！");
            taxPriceField.setText("");
                return;
        }

    }

    @Override
    public void setDataValue() {
        if (this.storage != null) {
            this.setStockAmount();
        }
    }

    @Override
    public void refreshArgs() {
        this.stockField.setText("");
    //this.shelfField.setSelectedItem(null);
    //this.shelfField.clearItems();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == priceField)
        {
             Object p = this.priceField.getSelectedItem();
             if (p != null && !p.equals(""))
             {
                 String priceString = String.valueOf(p);
                 double price = Double.parseDouble(priceString);
                 if(price < 0)
                 {
                     MessageBox.showErrorDialog(this, " 销售价格不能小于0");
                     return;
                  }
                 String strAmount = this.amountField.getText();
                 if (strAmount != null && !strAmount.equals(""))
                 {
                     refreshAmount();
                 }
                 this.refreshPrice();
              }
        }
    }

    @Override
    public void valueChanged(ValueChangedEvent evt) {
        if (evt.getSource() == this.typeIdField) {
            this.setDataValue();
            refreshDatas();
        }
        if (evt.getSource() == this.rateField) {
             Double rate= Double.valueOf(rateField.getText());
            if(rate>100){
                 rateField.setText(String.valueOf(100));
            }
            this.refreshPrice();
            refreshAmount();
        }
        if (evt.getSource() == this.amountField) {
            this.refreshAmount();

        }
        if (evt.getSource() == this.priceField) {
            this.refreshPrice();
            refreshAmount();
        }
        if (evt.getSource() == this.taxPriceField) {
            this.refreshPrices();
         
            refreshAmount();
        }

    }

    private void refreshAmount() {
        Object p = this.taxPriceField.getText();
        if (p == null || p.equals("")) {
              return;

        }
        String rateString = String.valueOf(p);
        String amountString = this.amountField.getText();
        if (amountString != null && !amountString.trim().equals("")) {
            try {
                double amount = Double.parseDouble(amountString);
                double rate = Double.parseDouble(rateString);
                double Price = rate * amount;
                  DecimalFormat df = new DecimalFormat("##.00");
                double taxPrice = Double.parseDouble(df.format(Price));
                this.totalMoneyField.setText(String.valueOf(taxPrice));
            } catch (Exception ex) {
                MessageBox.showErrorDialog(this, "数据格式不对！");
            }
        }
    }
}
 */

