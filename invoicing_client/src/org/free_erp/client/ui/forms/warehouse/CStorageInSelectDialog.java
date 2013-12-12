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

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Administrator
 */
public class CStorageInSelectDialog extends CBaseDetailedDialog {
     private JDataField typeIdField;
    private JDataComboBox typeNameField;
    private JDataComboBox slectNameField;
  private JDataComboBox specificationsField;
private JDataComboBox  unitsField;
private JDataComboBox  cargoSpaceField;
private JDataComboBox  priceField;
private JDataField amountField;
private JDataField  totalPriceField;
private JDataField  remarksField;
private JDataField stockField;

    public CStorageInSelectDialog(Frame parent, IDataSource dataSource)
	{
		super(parent, dataSource);
                this.nextButton.setVisible(false);
                this.firstButton.setVisible(false);
  	        this.previousButton.setVisible(false);
	        this.nextButton.setVisible(false);
	        this.lastButton.setVisible(false);
	        this.printButton.setVisible(false);
	        this.stopButton.setVisible(false);
               
               initC(); 
	}

       
      private void initC()
        {
             this.setTitle("添加商品");
		  this.setSize(700, 260);
                  typeIdField =new JDataField();
                  typeNameField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getProducts());//零时
                  slectNameField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomers());//零时
                  specificationsField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomers());//零时
                  unitsField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomers());//零时
                  cargoSpaceField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomers());//零时
                   priceField=new JDataComboBox("admin",String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getCustomers());//零时
                   amountField=new JDataField();
                   totalPriceField=new JDataField();
                   totalPriceField.setEditable(false);
                  stockField=new JDataField();
                   stockField.setEditable(false);
                   remarksField=new JDataField();
		
                   
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(tabbedPane);
		p.setPreferredSize(new Dimension(700, 286));
		tabbedPane.setBounds(10, 5, 680, 180);
		this.getContentPane().add("Center", p);

		CPanel panel = new CPanel();
		panel.setLayout(null);

		int x = 80;
		int y = 10; // row
		panel.addComponent(typeIdField, x, y, 90, 20, "商品编码", 60);
		panel.addComponent(typeNameField, x + 160, y, 200, 20, "商品名称", 60);
		panel.addComponent(slectNameField, x + 420, y, 100, 20, "商品类别", 50);
	
		y += ROW_SPAN;
		panel.addComponent(unitsField, x, y, 90, 20, "单位", 60);
		panel.addComponent(specificationsField, x + 160, y, 200, 20, "规格型号", 60);
		panel.addComponent(cargoSpaceField, x + 420, y, 100, 20, "货位", 50);
		y += ROW_SPAN;
		panel.addComponent(stockField, x, y, 90, 20, "库存", 60);
		panel.addComponent(priceField, x + 160, y, 80, 20, "单价", 50);
                panel.addComponent(amountField, x + 300, y, 60, 20, "数量", 40);
                panel.addComponent(totalPriceField, x + 420, y, 100, 20, "总金额", 50);
                
		y += ROW_SPAN;
		panel.addComponent(remarksField, x, y, 520, 20, "备注", 60);
             
		tabbedPane.addTab("添加商品", panel);
                

           }
     public void newDataRow()
     {
   
     }
    
    
    
}
