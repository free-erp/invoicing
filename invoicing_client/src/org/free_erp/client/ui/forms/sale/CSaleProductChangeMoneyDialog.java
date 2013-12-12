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
import org.free_erp.client.ui.util.PermissionUtilities;
import com.jdatabeans.beans.Item;
import com.jdatabeans.beans.data.JDataItemComboBox;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author tzl
 */
public class CSaleProductChangeMoneyDialog extends CSaleProductDialog implements ActionListener, ValueChangedListener {

    private JDataNumberField currentPriceField;
    private JDataItemComboBox price2Field;
    private JDataItemComboBox price3Field;
    private JDataItemComboBox price4Field;
    private JDataItemComboBox price5Field;
    private JDataItemComboBox price1Field;
    protected JDataItemComboBox retailPriceField;
    protected JDataItemComboBox PurchasePriceField;
    private String Price0Name = "价格1";
    private String Price1Name = "价格2";
    private String Price2Name = "价格3";
    private String Price3Name = "价格4";
    private String Price4Name = "价格5";
    private String Price5Name = "价格6";

  

    public CSaleProductChangeMoneyDialog(JDialog parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    public CSaleProductChangeMoneyDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }

    private void refreshDatas() {
        Object obj = this.typeIdField.getSelectedItem();
        if (Main.getMainFrame().getObjectsPool().getPriceName() != null) {

            Price0Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName0();
            Price1Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName1();
            Price2Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName2();
            Price3Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName3();
            Price4Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName4();
            Price5Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName5();

        }
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
            this.retailPriceField.removeAllItems();
           
            this.priceField.removeAllItems();
            List<Item> items = new ArrayList();
             
        IPermissionsService services = Main.getServiceManager().getPermissionsService();
        Permission permission = services.getPermission(Main.getMainFrame().getCompany(), Main.getMainFrame().getUser());
        int per = permission.getPrice();
                items.add(new Item("零售价", product.getRetailPrice()));
               //if(PermissionUtilities.hasPriceTpye2FPermission(per)){
                    items.add(new Item("采购价", product.getStockPrice()));
               //}
            items.add(new Item(Price0Name, product.getPrice0()));
            items.add(new Item(Price1Name, product.getPrice1()));
            items.add(new Item(Price2Name, product.getPrice2()));
            items.add(new Item(Price3Name, product.getPrice3()));
            items.add(new Item(Price4Name, product.getPrice4()));
            items.add(new Item(Price5Name, product.getPrice5()));
            this.priceField.setItems(items);
            price1Field.setItems(items);
            price2Field.setItems(items);
            price3Field.setItems(items);
            price4Field.setItems(items);
            price5Field.setItems(items);
             this.retailPriceField.setItems(items);
             this.PurchasePriceField.setItems(items);
            this.catalogNameField.setText(product.getCatalog().getName());
            this.setPrice(product);
        }
    }

    @Override
    protected void initC() {
        if (Main.getMainFrame().getObjectsPool().getPriceName() != null) {
            Price0Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName0();
            Price1Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName1();
            Price2Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName2();
            Price3Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName3();
            Price4Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName4();
            Price5Name = Main.getMainFrame().getObjectsPool().getPriceName().getPriceName5();

        }
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 260);
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
        priceField = new JDataItemComboBox("whlesale", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时
        priceField.setEditable(true);

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
        price2Field = new JDataItemComboBox("website2", Double.class, this.dataSource);
        price2Field.setEditable(true);
        price3Field = new JDataItemComboBox("website3", Double.class, this.dataSource);
        price3Field.setEditable(true);
        price4Field = new JDataItemComboBox("website4", Double.class, this.dataSource);
        price4Field.setEditable(true);
        price5Field = new JDataItemComboBox("website5", Double.class, this.dataSource);
        price5Field.setEditable(true);
        retailPriceField = new JDataItemComboBox("price", Double.class, this.dataSource);
        retailPriceField.setEditable(true);
        PurchasePriceField = new JDataItemComboBox("purchaseprice", Double.class, this.dataSource);
        PurchasePriceField.setEditable(true);
        price1Field = new JDataItemComboBox("whlesale1", Double.class, this.dataSource);
        price1Field.setEditable(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(650, 250));
        tabbedPane.setBounds(10, 5, 630, 180);
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
        panel.addComponent(retailPriceField, x - 10, y, 80, 20, "零售价", 50);
        panel.addComponent(PurchasePriceField, x + 150, y, 80, 20, "采购价", 50);
        panel.addComponent(priceField, x + 290, y, 80, 20, Price0Name, 50);
        panel.addComponent(price1Field, x + 430, y, 90, 20, Price1Name, 50);
        y += ROW_SPAN;
        panel.addComponent(price2Field, x - 10, y, 80, 20, Price2Name, 50);
        panel.addComponent(price3Field, x + 150, y, 80, 20, Price3Name, 50);
        panel.addComponent(price4Field, x + 290, y, 80, 20, Price4Name, 50);
        panel.addComponent(price5Field, x + 430, y, 90, 20, Price5Name, 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x - 10, y, 530, 20, "备注", 50);
//        y += ROW_SPAN + 10;
//        panel.addComponent(chooseButton, x + 350, y, 80, 25);
//        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("商品信息", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
//        this.currentPriceField.addValueChangedListener(this);
        p.add(chooseButton);
        p.add(closeButton);
        y += ROW_SPAN;
        y += ROW_SPAN;
        y += ROW_SPAN;
        this.chooseButton.setBounds(x + 320, y, 80, 25);
        this.closeButton.setBounds(x + 420, y, 80, 25);
        this.getContentPane().add(p);
        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }
    public void setPrice(Product product){
        IPermissionsService services = Main.getServiceManager().getPermissionsService();
        Permission permission = services.getPermission(Main.getMainFrame().getCompany(), Main.getMainFrame().getUser());
        int per = permission.getPrice();
                this.priceField.setSelectedItem(product.getPrice0());
            this.price1Field.setSelectedItem(product.getPrice1());
            this.price2Field.setSelectedItem(product.getPrice2());
            this.price3Field.setSelectedItem(product.getPrice3());
            this.price4Field.setSelectedItem(product.getPrice4());
            this.price5Field.setSelectedItem(product.getPrice5());
            retailPriceField.setSelectedItem(product.getRetailPrice());
             // if(PermissionUtilities.hasPriceTpye2FPermission(per)){
                              this.PurchasePriceField.setSelectedItem(product.getStockPrice());
              // }else{
               //             this.PurchasePriceField.setSelectedItem("");
              // }

    }

    @Override
    public void valueChanged(ValueChangedEvent evt) {

        if (evt.getSource() == this.typeIdField) {
            this.setStockAmount();
            refreshDatas();
        } else if (evt.getSource() == currentPriceField) {
            if (currentPriceField.getText() != null && !currentPriceField.getText().equals("") && stockField.getText() != null && !stockField.getText().equals("")) {
                try {
                    double currentPrice = Double.valueOf(currentPriceField.getText());
                    double totalMoney = currentPrice * Integer.valueOf(this.stockField.getText());
                    DecimalFormat df = new DecimalFormat("##.00");
                    double taxPrice = Double.parseDouble(df.format(totalMoney));
                    this.totalMoneyField.setText(String.valueOf(taxPrice));
                } catch (Exception ex) {
                    MessageBox.showErrorDialog(this, "数据格式不对！");
                    this.currentPriceField.setText("");
                }
            }
        }
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
//        this.totalMoneyField.setText("");
        this.commentsField.setText("");
    }
}
/*
public class CSaleProductChangeMoneyDialog extends CBaseProductSelectDialog implements ActionListener, ValueChangedListener {

    private JDataNumberField currentPriceField;
    private JDataComboBox price2Field;
    private JDataComboBox price3Field;
    private JDataComboBox price4Field;
    private JDataComboBox price5Field;
    private JDataComboBox price1Field;
  protected JDataComboBox retailPriceField;
    protected JDataComboBox PurchasePriceField;


    public CSaleProductChangeMoneyDialog(Frame parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    public CSaleProductChangeMoneyDialog(JDialog parent, IDataSource dataSource) {
        super(parent, dataSource);
    }

    public CSaleProductChangeMoneyDialog(JDialog parent, IDataSource dataSource, Storage storage) {
        super(parent, dataSource, storage);
    }
     private void refreshDatas() {
      Object obj = this.typeIdField.getSelectedItem();
        if (obj instanceof Product) {
            Product product = (Product) obj;
            this.typeNameField.setText(product.getName());
            this.specField.setText(product.getSpec());
            this.unitField.setText(product.getSmallUnit());
             this.retailPriceField.removeAllItems();
            this.retailPriceField.addItem(product.getRetailPrice());
//            this.priceField.removeAllItems();
            this.priceField.addItem(product.getPrice0());
            this.price1Field.removeAllItems();
            this.price1Field.addItem(product.getPrice1());
            this.price2Field.removeAllItems();
            this.price2Field.addItem(product.getPrice2());
            this.price3Field.removeAllItems();
            this.price3Field.addItem(product.getPrice3());
            this.price4Field.removeAllItems();
            this.price4Field.addItem(product.getPrice4());
            this.price5Field.removeAllItems();
            this.price5Field.addItem(product.getPrice5());
            this.PurchasePriceField.removeAllItems();
            this.PurchasePriceField.addItem(product.getStockPrice());
            this.catalogNameField.setText(product.getCatalog().getName());
        }
    }

    @Override
    protected void initC() {
        this.getContentPane().setLayout(new BorderLayout());
       this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("添加商品");
        this.setSize(650, 260);
        typeIdField = new JDataPanelComboBox("product", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        typeIdField.setRequired(true);
        typeIdField.setDisplayName("商品编号");
        typeNameField = new CField();
        this.typeNameField.setEditable(false);
        catalogNameField = new CField();
        this.catalogNameField.setEditable(false);
        specField = new CField();
        specField.setEditable(false);
        unitField =new CField();
        unitField.setEditable(false);
        shelfField = new JDataComboBox("shelf", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductShelfs());
        priceField = new JDataComboBox("whlesale", Double.class, this.dataSource);//, Main.getMainFrame().getObjectsPool().getCustomers());//零时
        priceField.setEditable(true);

        amountField = new JDataNumberField("amount", Double.class, this.dataSource);
        amountField.setRequired("商品数量",true);
        amountField.setMaxLength(10);
        totalMoneyField = new JDataField("totalMoney", Double.class, this.dataSource);
        totalMoneyField.setEditable(false);
        stockField = new JDataField();
        stockField.setEnabled(false);
        commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
        closeButton = new CButton("取消(&C)");
        chooseButton = new CButton("确定(&O)");
        price2Field = new JDataComboBox("website2", Double.class, this.dataSource);
        price2Field.setEditable(true);
        price3Field = new JDataComboBox("website3", Double.class, this.dataSource);
        price3Field.setEditable(true);
        price4Field = new JDataComboBox("website4", Double.class, this.dataSource);
         price4Field.setEditable(true);
        price5Field = new JDataComboBox("website5", Double.class, this.dataSource);
         price5Field.setEditable(true);
        retailPriceField = new JDataComboBox("price", Double.class, this.dataSource);
         retailPriceField.setEditable(true);
        PurchasePriceField = new JDataComboBox("purchaseprice", Double.class, this.dataSource);
         PurchasePriceField.setEditable(true);
        price1Field = new JDataComboBox("whlesale1", Double.class, this.dataSource);
           price1Field.setEditable(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(tabbedPane);
        p.setPreferredSize(new Dimension(650, 250));
        tabbedPane.setBounds(10, 5, 630, 180);
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
        panel.addComponent(retailPriceField, x - 10, y, 80, 20, "零售价", 50);
        panel.addComponent(PurchasePriceField, x + 150, y, 80, 20, "采购价", 50);
        panel.addComponent(priceField, x + 290, y, 80, 20, "批发价", 50);
        panel.addComponent(price1Field, x + 430, y, 90, 20, "批发价一", 50);
        y += ROW_SPAN;
        panel.addComponent(price2Field, x - 10, y, 80, 20, "批发价二", 50);
        panel.addComponent(price3Field, x + 150, y, 80, 20, "批发价三", 50);
        panel.addComponent(price4Field, x + 290, y, 80, 20, "批发价四", 50);
        panel.addComponent(price5Field, x + 430, y, 90, 20, "批发价五", 50);
        y += ROW_SPAN;
        panel.addComponent(commentsField, x - 10, y, 530, 20, "备注", 50);
//        y += ROW_SPAN + 10;
//        panel.addComponent(chooseButton, x + 350, y, 80, 25);
//        panel.addComponent(closeButton, x + 430, y, 80, 25);

        tabbedPane.addTab("商品信息", panel);
        this.getContentPane().add(p);
        this.chooseButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        typeIdField.addValueChangedListener(this);
//        this.currentPriceField.addValueChangedListener(this);
      p.add(chooseButton);
        p.add(closeButton);
         y += ROW_SPAN;
         y += ROW_SPAN;
         y += ROW_SPAN;
         this.chooseButton.setBounds(x + 320, y, 80, 25);
        this.closeButton.setBounds(x + 420, y, 80, 25);
        this.getContentPane().add(p);
        this.setDefaultButton(this.chooseButton);
        this.setCancelButton(this.closeButton);
    }

   

    @Override
    public void valueChanged(ValueChangedEvent evt) {

        if (evt.getSource() == this.typeIdField) {
            this.setStockAmount();
            refreshDatas();
        } else if (evt.getSource() == currentPriceField) {
            if (currentPriceField.getText() != null && !currentPriceField.getText().equals("") && stockField.getText() != null && !stockField.getText().equals("")) {
                try {
                    double currentPrice = Double.valueOf(currentPriceField.getText());
                    double totalMoney = currentPrice * Integer.valueOf(this.stockField.getText());
                      DecimalFormat df = new DecimalFormat("##.00");
                double taxPrice = Double.parseDouble(df.format(totalMoney));
                    this.totalMoneyField.setText(String.valueOf(taxPrice));
                } catch (Exception ex) {
                    MessageBox.showErrorDialog(this, "数据格式不对！");
                    this.currentPriceField.setText("");
                }
            }
        }
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
//        this.totalMoneyField.setText("");
        this.commentsField.setText("");
    }
}
 */
