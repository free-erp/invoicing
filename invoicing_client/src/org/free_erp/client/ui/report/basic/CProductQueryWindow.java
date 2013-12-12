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
package org.free_erp.client.ui.report.basic;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.basic.CProductInfoDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.logic.IProductService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CProductQueryWindow extends CBaseQueryWindow
{
    private JDataComboBox catalogField;
    private JDataPanelComboBox numberField;
    private CField nameField;
    private CField shortNameField;
    private CField helpCodeField;
    private CField barCodeField;
    public CProductQueryWindow(String title)
    {
        super(title);
    }

    protected int getPermission()
    {
        return 65535;
    }

    protected void clearInfo()
    {
        this.catalogField.setSelectedItem("");
        this.numberField.setSelectedItem("");
        this.nameField.setText("");
        this.shortNameField.setText("");
        this.helpCodeField.setText("");
        this.barCodeField.setText("");
    }

    protected void select()
    {
        DataBaseQueryVO vo = new DataBaseQueryVO(Main.getMainFrame().getCompany());
        Object obj = null;
        obj = this.catalogField.getSelectedItem();
        if(obj != null && obj instanceof ProductCatalog)
        {
            vo.setCatalog(((ProductCatalog)obj).getName());
        }
        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Product)
            {
                vo.setNumber(((Product)obj).getNumber());
            }
        }
        if(this.nameField.getText() != null && !this.nameField.getText().trim().equals(""))
        {
            vo.setName(this.nameField.getText());
        }
        if(this.shortNameField.getText() != null && !this.shortNameField.getText().trim().equals(""))
        {
            vo.setShortName(this.shortNameField.getText());
        }
        if(this.helpCodeField.getText() != null && !this.helpCodeField.getText().trim().equals(""))
        {
            vo.setCode(this.helpCodeField.getText());
        }
        if(this.barCodeField.getText() != null && !this.barCodeField.getText().trim().equals(""))
        {
            vo.setBarCode(this.barCodeField.getText());
        }
        ITableModel model = this.dataTable.getTableModel();
        IProductService service = Main.getServiceManager().getProductService();
        this.dataSource.clear();
        List<Product> products = service.findProducts(vo);
        for(Product p : products)
        {
            IDataRow dataRow = new ObjectDataRow(p, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
        if(products == null || products.size() == 0)
        {
            this.clearInfo();
        }
    }

    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,80));
		panel.setLayout(null);
        catalogField = new JDataComboBox(ProductCatalog.class,Main.getMainFrame().getObjectsPool().getProductCatalogs());
        catalogField.setSelectedItem("");
        numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        nameField = new CField();
        shortNameField = new CField();
        helpCodeField = new CField();
        barCodeField = new CField();
        
        int x = 60;
        int y = 15;
        panel.addComponent(catalogField, x, y, 120, 20, "类别", 50);
        panel.addComponent(numberField, x + 170, y, 100, 20, "商品编号", 50);
        panel.addComponent(nameField, x + 320, y, 120, 20, "商品名称", 50);
        panel.addComponent(shortNameField, x , y + 25, 120, 20, "商品简称", 50);
        panel.addComponent(helpCodeField, x + 170, y + 25, 100, 20, "助记码", 50);
        panel.addComponent(barCodeField, x + 320, y + 25, 120, 20, "条形码", 50);
        panel.addComponent(selectButton, x + 440, y + 25, 75, 25);
        panel.addComponent(clearButton, x + 520, y + 25, 75, 25);

        return panel;
    }

    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("类别编号");
		column.setColumnName("catalog.number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("类别名称");
		column.setColumnName("catalog.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(140);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("规格");
		column.setColumnName("spec");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("采购价");
		column.setColumnName("stockPrice");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("零售价");
		column.setColumnName("retailPrice");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单位");
		column.setColumnName("smallUnit");
		column.setWidth(40);

        columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("生产厂家");
		column.setColumnName("factory");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("产地");
		column.setColumnName("area");
		column.setWidth(40);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("助记码");
		column.setColumnName("code");
		column.setWidth(50);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("商品信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/ProductManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    protected String getExcelExportTitle()
    {
        return "商品信息查询";
    }

    protected void doShowDetail()
    {
        if (this.dataTable.getSelectedRow() < 0)
        {
            return;
        }
        this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
        CProductInfoDialog dialog = new CProductInfoDialog(Main.getMainFrame(), this.dataSource,dbSupport);
        dialog.saveAndNewButton.setEnabled(true);
        dialog.saveButton.setEnabled(true);
        dialog.setVisible(true);
        this.dataSource.clearTempDataRows();
    }
}
