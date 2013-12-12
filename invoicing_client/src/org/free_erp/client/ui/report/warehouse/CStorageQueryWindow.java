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
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.free_erp.client.ui.report.warehouse;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CMoneyField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.StorageProductPo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.StorageQueryVo;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IStorageService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *库存商品余额
 * @author tengzhuolin
 * @Changer liufei
 */
public class CStorageQueryWindow extends CBaseQueryWindow {

    private JDataPanelComboBox numberField;
    private JDataTableComboBox storageField;
    private CField nameField;
    private JDataComboBox catalogNameField;


    public CStorageQueryWindow(String title) {
        super(title);
    }
    

    protected void clearInfo() {
        this.numberField.setEditorText("");
        //this.nameField.setText("");
        this.storageField.setText("");
        //this.catalogNameField.setSelectedItem("");
    }

    protected void initColumns() {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("仓库编号");
        column.setColumnName("storage.number");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库名称");
        column.setColumnName("storage.name");
        column.setWidth(80);
        columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("商品编号");
        column.setColumnName("product.number");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("商品名称");
        column.setColumnName("product.name");
        column.setWidth(140);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("规格");
        column.setColumnName("product.spec");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单价");
        column.setColumnName("price");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("数量");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("金额");
        column.setColumnName("totailMoney");
        column.setValueType(Currency.class);
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("货位");
        column.setColumnName("shelf");
        column.setWidth(80);
        columnModel.addColumn(column);        
    }

    public void select() {

        StorageQueryVo vo = new StorageQueryVo(Main.getMainFrame().getCompany());
        Object obj = storageField.getSelectedItem();
        if (obj != null && obj instanceof Storage && this.storageField.getText() != null && !this.storageField.getText().equals(""))
        {
            vo.setStorage((Storage)storageField.getSelectedItem());
        }

        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Product)
            {
                vo.setProduct((Product)obj);
            }
        }

        ITableModel model = this.dataTable.getTableModel();
        this.dataSource.clear();
        IStorageService storageService = Main.getServiceManager().getStorageService();

        List<StorageProductPo> vso = storageService.findStorageProductForms(vo);
        for (StorageProductPo po : vso)
        {
            IDataRow dataRow = new ObjectDataRow(po, "id", null);
            model.insertDataRow(dataRow);
        }
        if(vso == null || vso.size() == 0)
        {
            this.clearInfo();
        }
    }

    @Override
    protected CPanel getTotalPanel()
    {
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(600, 40));
        this.totalAmountField = new CField();
        this.totalMoneyField = new CMoneyField();
        this.totalAmountField.setEditable(false);
        bottomPanel.addComponent(totalAmountField, 100, 10, 150, 20, "合计数量", 60);
        bottomPanel.addComponent(totalMoneyField, 320, 10, 150, 20, "合计金额", 60);
        return bottomPanel;
    }

    @Override
    protected CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setPreferredSize(new Dimension(700, 50));
        topPanel.setLayout(null);
        numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        storageField = new JDataTableComboBox("", Storage.class, Main.getMainFrame().getObjectsPool().getStorageTable(), "name");
        
        //nameField = new CField();
        //catalogNameField = new JDataComboBox("", ProductCatalog.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());
        int x = 35;
        int y = 15;
        topPanel.addComponent(storageField, x, y, 150, 20, "仓库", 30);
        topPanel.addComponent(numberField, x + 200, y, 100, 20, "商品编号", 50);
        //topPanel.addComponent(nameField, x + 350, y, 100, 20, "商品名称", 50);
        //topPanel.addComponent(catalogNameField, x + 500, y, 100, 20, "商品类别", 50);
        topPanel.addComponent(selectButton, x + 605, y, 75, 25);
        topPanel.addComponent(clearButton, x + 685, y, 75, 25);
        return topPanel;
    }

    protected String getExcelExportTitle()
    {
        return "库存商品余额查询";
    }

    protected void doPrint()
    {
        Map variables=new HashMap();
        variables.put("JCOMPANY", Main.getMainFrame().getCompany().getName());
        ReportVO vo =new ReportVO();
        vo.setTitle("库存商品余额查询报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog =new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/rStorageQuery.jasper"), variables,this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

}
