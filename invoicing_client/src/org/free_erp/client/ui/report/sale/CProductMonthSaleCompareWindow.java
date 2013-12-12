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
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.service.entity.base.ProductCatalog;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.BorderFactory;

/**
 * 商品月销售比较表
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CProductMonthSaleCompareWindow extends CBaseQueryWindow
{
    private JDataComboBox catalogNameField;
    private JDataField yearField;

    public CProductMonthSaleCompareWindow(String title)
    {
        super(title);
    }

    @Override
    protected void clearInfo()
    {
        this.yearField.setText("");
        this.catalogNameField.setSelectedItem("");
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
        yearField = new JDataField();
        catalogNameField =  new JDataComboBox("", ProductCatalog.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());

        int x = 60;
        int y = 10;

        panel.addComponent(yearField, x, y, 120, 20, "年份", 40);
        panel.addComponent(catalogNameField, x + 170, y, 100, 20, "分类", 50);
        panel.addComponent(selectButton, x + 340, y, 75, 25);
        panel.addComponent(clearButton, x + 420, y, 75, 25);

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
