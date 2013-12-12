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

package org.free_erp.client.ui.forms.basic;
import java.awt.Frame;
import javax.swing.JPanel;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
/*
 *
 * Changer: liufei
 */
public class CProductInfoDialog extends CBaseDetailedDialog
{
	private CProductManageWindow manageWindow;
	protected JDataField idField;
        private JDataField nameField;
	private JDataField aliasField;
	private JDataField helpCodeField;
	private JDataField specField;
	private JDataField lineCodeField;
	private JDataComboBox taxField;
	private JDataField factoryField;
	private JDataField fromField;
	private JDataComboBox littleUnitField;
	/*
	private JDataField middleUnitField;
	private JDataField bigUnitField;
	private JDataField littleUnitAmountField;
	private JDataField middleUnitAmountField;
	private JDataField bigUnitAmountField;
	*/
	private JDataField commentField;
	private JDataNumberField price0Field;
	private JDataNumberField price1Field;
	private JDataNumberField price2Field;
	private JDataNumberField price3Field;
	private JDataNumberField price4Field;
	private JDataNumberField price5Field;
	private JDataNumberField retailPriceField;
	private JDataNumberField stockPriceField;
    private ProductCatalog productCatalog;
	public CProductInfoDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
		this.initCs();
	}
	
	public CProductInfoDialog(Frame parent, CProductManageWindow manageWindow, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
		this.manageWindow = manageWindow;
		this.initCs();
	}
	public void initCs()
	{
		this.setTitle("商品信息");
		//this.setSize(850, 260);
		idField = new JDataField("number",  String.class,dataSource);
        //idField.setEnabled(false);
        
		nameField = new JDataField("name", String.class, dataSource);
		nameField.setRequired("名称", true);
        nameField.setMaxLength(30);
		//helpCodeField = new JDataField("code",  String.class,dataSource);
        //helpCodeField.setMaxLength(10);
		//aliasField = new JDataField("shortname", String.class, dataSource);
        //aliasField.setMaxLength(30);
		specField = new JDataField("spec", String.class, dataSource);
        specField.setMaxLength(30);
		lineCodeField = new JDataField("barCode", String.class, dataSource);
        lineCodeField.setMaxLength(13);
		taxField = new JDataComboBox("taxRate", Long.class,this.dataSource, Main.getMainFrame().getObjectsPool().getProductTaxrates());
		factoryField = new JDataField("factory",  String.class,dataSource);
        factoryField.setMaxLength(120);
		fromField = new JDataField("area",  String.class,dataSource);
        fromField.setMaxLength(30);
		littleUnitField = new JDataComboBox("smallUnit", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getProductUnits());
        littleUnitField.setEditable(true);
		commentField = new JDataField("comments",  String.class,dataSource);
        commentField.setMaxLength(255);
		//JTabbedPane tabbedPane = new JTabbedPane();
         //       tabbedPane.setTabPlacement(JTabbedPane.RIGHT);
		JPanel p = new JPanel();
		p.setLayout(null);
		
		
		//tabbedPane.setBounds(10, 5, 825, 320);
		this.getContentPane().add("Center", p);
		CPanel panel = new CPagePane();
		panel.setLayout(null);
        p.add(panel);
        panel.setBounds(10, 5, 825, 320);
		/*
		this.addComponent(saveAndNewButton, 20, 290, 120, 25);
		this.addComponent(saveButton, 150, 290, 100, 25);
		this.addComponent(previousButton, 270, 290, 100, 25);
		this.addComponent(nextButton, 380, 290, 100, 25);
		this.addComponent(cancelButton, 550, 290, 100, 25);
		*/
		int x = 60;
		int y = 10; // row
		panel.addComponent(idField, x + 40, y, 100, 20, "编号(可不填)", 80);
		panel.addComponent(nameField, x + 200, y, 280, 20, "名  称", 40);
		//panel.addComponent(aliasField, x + 440, y, 100, 20, "简  称", 40);
		//panel.addComponent(helpCodeField, x + 600, y, 60, 20, "助记码", 40);
		y += ROW_SPAN;
		panel.addComponent(littleUnitField, x , y, 140, 20, "单  位", 40);
        panel.addComponent(specField, x+ 200, y, 110, 20, "规  格", 40);
		panel.addComponent(lineCodeField, x + 360, y, 120, 20, "条形码", 40);
		y += ROW_SPAN;
//		panel.addComponent(taxField, x, y, 88, 20, "税  率", 40);
//        panel.addComponent(new JLabel("%"), x + 88, y, 12, 20);
		panel.addComponent(fromField, x, y, 140, 20, "产  地", 40);
		panel.addComponent(factoryField, x + 200, y, 280, 20, "厂  商", 40);
        y += ROW_SPAN;
        this.price0Field = new JDataNumberField("price0", Double.class, dataSource);
        this.price0Field.setMaxLength(10);
		this.price1Field = new JDataNumberField("price1",Double.class,  dataSource);
        this.price1Field.setMaxLength(10);
		this.price2Field = new JDataNumberField("price2",Double.class, dataSource);
        this.price2Field.setMaxLength(10);
		this.price3Field = new JDataNumberField("price3",Double.class, dataSource);
        this.price3Field.setMaxLength(10);
		this.price4Field = new JDataNumberField("price4",Double.class, dataSource);
        this.price4Field.setMaxLength(10);

        this.retailPriceField = new JDataNumberField("retailPrice",Double.class, dataSource);
        this.retailPriceField.setMaxLength(10);
		this.stockPriceField = new JDataNumberField("stockPrice",Double.class, dataSource);
        this.stockPriceField.setMaxLength(10);
       
		panel.addComponent(commentField, x, y, 480, 20, "备  注", 40);

        x = 20;
        y += ROW_SPAN;
         y += ROW_SPAN;
//        panel.addDisplayText("价格信息:", 20, y);
//        y += ROW_SPAN;
        CPanel tempPanel = new CPanel();
        tempPanel.setBorder(BorderFactory.createEtchedBorder());
        panel.addComponent(tempPanel, x, y, 600, 80);
        y = 10;
        x = 100;
        tempPanel.addComponent(stockPriceField, x, y, 100, 20, "采购价", 80);
		tempPanel.addComponent(retailPriceField, x + 190, y, 100, 20, "零售价", 80);
        tempPanel.addComponent(price0Field, x + 380, y, 100, 20, "批发价1", 80);
		y += ROW_SPAN;
		tempPanel.addComponent(price1Field, x , y, 100, 20, "批发价2", 80);
        tempPanel.addComponent(price2Field, x + 190, y, 100, 20, "批发价3", 80);
		tempPanel.addComponent(price3Field, x + 380, y, 100, 20, "批发价4", 80);
		
		
		/*
		y += 2 * ROW_SPAN;
		panel.addComponent(littleUnitField, x, y, 100, 20, "小单位", 40);
		panel.addComponent(littleUnitAmountField, x + 120, y, 80, 20);
		panel.addDisplayText("填写商品的小单位,换算率默认为1", x + 210, y, 300, -1);
		y += ROW_SPAN;
		panel.addComponent(middleUnitField, x, y, 100, 20, "中单位", 40);
		panel.addComponent(middleUnitAmountField, x + 120, y, 80, 20);
		panel.addDisplayText("填写商品的中单位,换算率为最小单位的绝对换算率", x + 210, y, 300, -1);
		y += ROW_SPAN;
		panel.addComponent(bigUnitField, x, y, 100, 20, "大单位", 40);
		panel.addComponent(bigUnitAmountField, x + 120, y, 80, 20);
		panel.addDisplayText("填写商品的大单位,换算率为最小单位的绝对换算率", x + 210, y, 300, -1);
		*/
		
//		tabbedPane.addTab("基本信息", panel);
//		panel = new CPanel();
//		panel.setLayout(null);
//		tabbedPane.addTab("价格信息", panel);
//		x = 90;
//		y = 10;
//		this.price0Field = new JDataNumberField("price0", Double.class, dataSource);
//        this.price0Field.setMaxLength(10);
//		this.price1Field = new JDataNumberField("price1",Double.class,  dataSource);
//        this.price1Field.setMaxLength(10);
//		this.price2Field = new JDataNumberField("price2",Double.class, dataSource);
//        this.price2Field.setMaxLength(10);
//		this.price3Field = new JDataNumberField("price3",Double.class, dataSource);
//        this.price3Field.setMaxLength(10);
//		this.price4Field = new JDataNumberField("price4",Double.class, dataSource);
//        this.price4Field.setMaxLength(10);
//		this.price5Field = new JDataNumberField("price5",Double.class, dataSource);
//        this.price5Field.setMaxLength(10);
//		this.retailPriceField = new JDataNumberField("retailPrice",Double.class, dataSource);
//        this.retailPriceField.setMaxLength(10);
//		this.stockPriceField = new JDataNumberField("stockPrice",Double.class, dataSource);
//        this.stockPriceField.setMaxLength(10);
//		panel.addComponent(stockPriceField, x, y, 100, 20, "采购价", 80);
//		panel.addComponent(retailPriceField, x + 190, y, 100, 20, "零售价", 80);
//		y += ROW_SPAN;
//		panel.addComponent(price0Field, x, y, 100, 20, "批发价1", 80);
//		panel.addComponent(price1Field, x + 190, y, 100, 20, "批发价2", 80);
//		y += ROW_SPAN;
//		panel.addComponent(price2Field, x, y, 100, 20, "批发价3", 80);
//		panel.addComponent(price3Field, x + 190, y, 100, 20, "批发价4", 80);
//		y += ROW_SPAN;
//		panel.addComponent(price4Field, x, y, 100, 20, "批发价5", 80);
//		panel.addComponent(price5Field, x + 190, y, 100, 20, "批发价6", 80);
	}
    @Override
    protected void doRejiggerType()
    {
        if(MessageBox.showQuestionDialog(this, "您确定要更改此商品的类别吗?") == 0)
        {
            rejiggerTypeDialog = new CProductRejiggerTypeDialog(this, dataSource);
            rejiggerTypeDialog.setVisible(true);
        }
    }
    @Override
    public boolean doCheckField()
    {
        String unit = this.littleUnitField.getText();
        if(unit != null)
        {
            if(unit.length() > 30)
            {
                MessageBox.showErrorDialog(this, "\"单位\"输入的内容不能超过30个字符!" );
                return false;
            }
        }
        return true;
    }
	@Override
	public void doSaveAndNew()
	{
		if (this.doSave())
		{
			this.newDataRow();
		}
	}
	public boolean newDataRow()
	{
        this.saveAndNewButton.setEnabled(true);
        this.saveButton.setEnabled(true);
		ProductCatalog catalog = null;
        if(productCatalog != null)
        {
            catalog = productCatalog;
        }
		if (this.manageWindow != null)
		{
			catalog = (ProductCatalog)this.manageWindow.getCurrentCatalog();
		}
//		if (catalog == null)
//		{
//			MessageBox.showMessageDialog(this, "您没有选择类别,请先选择类别!"); //看需求如何定义
//			return false;
//		}
		Product product = new Product();
		product.setCatalog(catalog);
        productCatalog = catalog;
		product.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(product, "id", this.dbSupport);
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
		return true;
	}
    public void doPrint()
    {
        if(!this.doSave())
        {
            return;
        }
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("商品信息详细报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/ProductInfoDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
        printDialog.setVisible(true);
    }
}
