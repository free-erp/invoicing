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

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.service.entity.base.Employee;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.BorderFactory;

/**
 * 职员销售排行榜
 *
 * @author tzl
 */
public class CEmployeeSaleOrderWindow  extends CBaseQueryWindow
{
    private JDataComboBox departmentField;
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private JDataTableComboBox adminField;
    public CEmployeeSaleOrderWindow(String title)
    {
        super(title);
    }

    @Override
    protected void clearInfo()
    {
        this.beginDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
        this.departmentField.setSelectedItem("");
    }

    @Override
    protected void select()
    {

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
        panel.addComponent(selectButton, x + 400, y, 75, 25);
        panel.addComponent(clearButton, x + 525, y, 75, 25);

        return panel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("单据编号");
        column.setColumnName("number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单据日期");
        column.setColumnName("formDate");
        column.setWidth(80);
        column.setValueType(Date.class);
        columnModel.addColumn(column);
    }

    
}
