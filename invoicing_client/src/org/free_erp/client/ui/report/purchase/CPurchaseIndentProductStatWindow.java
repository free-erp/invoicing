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
package org.free_erp.client.ui.report.purchase;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CMoneyField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataDatePicker;
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
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.purchase.PurchaseIndentProductPo;
import org.free_erp.service.entity.purchase.PurchaseQueryVo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.purchase.IPurchaseService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 * 采购订单商品统计
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseIndentProductStatWindow extends CBaseQueryWindow
{
    private JDataPanelComboBox numberField;
    private JDataTableComboBox supplierField;
    private JDataTableComboBox adminField;
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;

    public CPurchaseIndentProductStatWindow(String title)
    {
        super(title);
    }
    
    @Override
    protected void clearInfo()
    {
        this.numberField.setSelectedItem("");
        this.beginDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
        this.supplierField.setText("");
        this.adminField.setText("");
    }

    @Override
    protected void select()
    {
        PurchaseQueryVo vo = new PurchaseQueryVo(Main.getMainFrame().getCompany());
        if(this.supplierField.getText() != null && !this.supplierField.getText().equals(""))
        {
            vo.setSupplier((Customer)this.supplierField.getSelectedItem());
        }
        if(this.adminField.getText() != null && !this.adminField.getText().equals(""))
        {
            vo.setChargePerson((Employee)this.adminField.getSelectedItem());
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
        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Product)
            {
                vo.setProduct((Product)obj);
            }
        }

        ITableModel model = this.dataTable.getTableModel();
        IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
        this.dataSource.clear();
        List<PurchaseIndentProductPo> pos = purchaseService.findPurchaseIndentProductStat(vo);
        
        for (PurchaseIndentProductPo po : pos)
        {
            IDataRow dataRow = new ObjectDataRow(po, "id", null);
            model.insertDataRow(dataRow);
        }
        if(pos == null || pos.size() == 0)
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
        numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        supplierField = new JDataTableComboBox("", Customer.class,Main.getMainFrame().getObjectsPool().getCustomerTable() , "name");
        adminField = new JDataTableComboBox("",Employee.class,Main.getMainFrame().getObjectsPool().getEmployeeTable() , "name");
        int x = 60;
        int y = 15;
        panel.addComponent(supplierField, x, y, 120, 20, "供应商", 50);
        panel.addComponent(adminField, x + 170, y, 100, 20, "业务员", 50);
        panel.addComponent(numberField, x + 320, y, 100, 20, "商品编号", 50);
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
        column.setHeaderText("订货数量");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("订货金额");
        column.setColumnName("totalMoney");
        column.setValueType(Currency.class);
        column.setWidth(100);
        columnModel.addColumn(column);
    }

    protected String getExcelExportTitle()
    {
        return "采购订单商品统计";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("采购订单商品统计报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/purchase/rPurchaseIndentProductStats.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
