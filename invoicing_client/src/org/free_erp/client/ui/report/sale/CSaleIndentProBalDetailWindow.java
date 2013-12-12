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
package org.free_erp.client.ui.report.sale;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CMoneyField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.logic.ISaleService;
import org.free_erp.service.logic.sale.SaleQueryVo;
import java.awt.Dimension;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;

/**
 * 销售订单商品余额明细查询
 *
 * @author tzl
 */
public class CSaleIndentProBalDetailWindow extends CBaseQueryWindow
{
    private JDataComboBox catalogNameField;
    private JDataPanelComboBox numberField;
    private JDataTableComboBox supplierField;
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;

    public CSaleIndentProBalDetailWindow(String title)
    {
        super(title);
    }

    @Override
    protected void clearInfo()
    {
        this.numberField.setEditorText("");
        this.catalogNameField.setSelectedItem("");
        this.beginDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
        this.supplierField.setText("");
    }

    @Override
    protected void select()
    {

          SaleQueryVo vo = new SaleQueryVo(Main.getMainFrame().getCompany());
        vo.setStatus(1);
        if(this.supplierField.getText() != null && !this.supplierField.getText().equals(""))
        {
            vo.setSupplier((Customer)this.supplierField.getSelectedItem());
        }
//        if(this.adminField.getText() != null && !this.adminField.getText().equals(""))
//        {
//            vo.setEmployee((Employee)this.adminField.getSelectedItem());
//        }
        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            vo.setProductNumber(this.numberField.getText().trim());
        }
        Object obj = null;
        if(!beginDateField.getEditorText().equals(""))
        {
            obj = beginDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDatePo(startDate);
            }
        }

        if(!endDateField.getEditorText().equals(""))
        {
            obj = endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date endDate = (Date)obj;
                vo.setEndDatePo(endDate);
            }
        }

        ITableModel model = this.dataTable.getTableModel();
        ISaleService saleService = Main.getServiceManager().getSaleService();
        this.dataSource.clear();
        vo.setWay(1);
        List<SaleDetailPo> SaleOrderPos =saleService.findSaleDetailDetails(vo);
        if(SaleOrderPos == null || SaleOrderPos.size() == 0)
        {
            this.clearInfo();
        }
        for(SaleDetailPo po : SaleOrderPos)
        {
            IDataRow dataRow = new ObjectDataRow(po, "id", null);
            model.insertDataRow(dataRow);
        }
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,80));
		panel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        beginDateField.setSelectedItem("");
        catalogNameField =  new JDataComboBox("", ProductCatalog.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());
        supplierField = new JDataTableComboBox("", Customer.class,this.dataSource,Main.getMainFrame().getObjectsPool().getCustomerTable() , "name");
        numberField = new JDataPanelComboBox("number", String.class, Main.getMainFrame().getProductSelectPanel());
        int x = 60;
        int y = 15;
        panel.addComponent(supplierField, x, y, 120, 20, "客商", 50);
        panel.addComponent(catalogNameField, x + 170, y, 100, 20, "业务员", 50);
        panel.addComponent(numberField, x + 320, y, 100, 20, "单据编号", 50);
        panel.addComponent(beginDateField, x , y + 25, 120, 20, "查询时间", 50);
        panel.addComponent(endDateField, x + 170, y + 25, 100, 20, "至", 50);
        panel.addComponent(selectButton, x + 420, y + 25, 75, 25);
        panel.addComponent(clearButton, x + 500, y + 25, 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {

        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("单据编号");
        column.setColumnName("mainObject.number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单据日期");
        column.setColumnName("mainObject.formDate");
        column.setWidth(80);
        column.setValueType(Date.class);
        columnModel.addColumn(column);

         column = new JDataTableColumn();
//		column.setHeaderText("商品类别");
//        column.setColumnName("catalogName");
//        column.setWidth(120);
//        columnModel.addColumn(column);
//        column = new JDataTableColumn();
        column.setHeaderText("商品编号");
        column.setColumnName("product.number");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("商品名称");
        column.setColumnName("product.name");
        column.setWidth(80);
        columnModel.addColumn(column);
             column = new JDataTableColumn();
        column.setHeaderText("商品规格");
        column.setColumnName("product.spec");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("数量");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("金额");
        column.setColumnName("totalMoney");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
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
}
