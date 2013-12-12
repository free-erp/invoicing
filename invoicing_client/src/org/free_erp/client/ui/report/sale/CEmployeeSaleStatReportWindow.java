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
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.ISaleService;
import org.free_erp.service.logic.sale.SaleQueryVo;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 * 职员销售统计表
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CEmployeeSaleStatReportWindow extends CBaseQueryWindow
{
    private JDataComboBox departmentField;
    private JDataTableComboBox adminField;
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;

    public CEmployeeSaleStatReportWindow(String title)
    {
        super(title);
        //initComps();
    }
    
    @Override
    protected void clearInfo()
    {
        this.departmentField.setSelectedItem("");
        this.beginDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
        this.adminField.setText("");
    }

    @Override
    protected void select()
    {

          SaleQueryVo vo = new SaleQueryVo(Main.getMainFrame().getCompany());
        vo.setStatus(1);
//        if(this.supplierField.getText() != null && !this.supplierField.getText().equals(""))
//        {
//            vo.setSupplier((Customer)this.supplierField.getSelectedItem());
//        }
        if(this.adminField.getText() != null && !this.adminField.getText().equals(""))
        {
            vo.setEmployee((Employee)this.adminField.getSelectedItem());
        }
//        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
//        {
//            vo.setProductNumber(this.numberField.getText().trim());
//        }
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
//        vo.setWay(1);
        List<SaleDetailPo> SaleOrderPos =saleService.findSaleEmployeeProductDaetails(vo);
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
		panel.setPreferredSize(new Dimension(700,40));
		panel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        beginDateField.setSelectedItem("");
        departmentField = new JDataComboBox("department", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getDepartments());
        adminField = new JDataTableComboBox("",Employee.class,this.dataSource,Main.getMainFrame().getObjectsPool().getEmployeeTable() , "name");

        int x = 50;
        int y = 10;

//        panel.addComponent(departmentField, x, y, 120, 20, "部门", 40);
        panel.addComponent(adminField, x, y, 100, 20, "职员", 50);
        panel.addComponent(beginDateField, x + 170, y, 100, 20, "查询时间", 50);
        panel.addComponent(endDateField, x + 320, y, 100, 20, "至", 20);
        panel.addComponent(selectButton, x + 420, y, 75, 25);
        panel.addComponent(clearButton, x + 545, y, 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("业务员编号");
        column.setColumnName("employee.number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("业务员名称");
        column.setColumnName("employee.name");
        column.setWidth(80);
        column.setValueType(Date.class);
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

//         column = new JDataTableColumn();
//        column.setHeaderText("销售金额");
//        column.setColumnName("formDate");
//        column.setWidth(80);
//        column.setValueType(Date.class);
//        columnModel.addColumn(column);

//        column = new JDataTableColumn();
//        column.setHeaderText("销退金额");
//        column.setColumnName("formDate");
//        column.setWidth(80);
//        column.setValueType(Date.class);
//        columnModel.addColumn(column);
    }

    protected String getExcelExportTitle()
    {
        return "职员销售统计";
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("职员销售统计报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/sale/rEmployeeSaleStat.jasper"),variables, this.dataSource.getDataRows());

        printDialog.setVisible(true);
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
