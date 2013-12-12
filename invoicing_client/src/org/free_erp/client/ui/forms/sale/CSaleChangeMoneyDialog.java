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
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.SaleReportVo;
import org.free_erp.service.entity.sale.SaleChangeMoneyDetailPo;
import org.free_erp.service.entity.sale.SaleChangeMoneyPo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 
 */
public class CSaleChangeMoneyDialog extends CBaseSaleMainMoneyDialog {


    protected JDataDatePicker inDateField;
    protected JDataField commentField;

    public CSaleChangeMoneyDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
        this.setTitle("商品调价单");
    }
    
    public CSaleChangeMoneyDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		this.setTitle("商品调价单");
	}

    @Override
    public void newDataRow() {
        SaleChangeMoneyPo po = new SaleChangeMoneyPo();
        po.setCompany(Main.getMainFrame().getCompany());
        IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
        dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
    }
    
    @Override
    protected CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(700, 100));
        topPanel.setLayout(null);
       inDateField = new JDataDatePicker("formDate", this.dataSource);
        commentField = new JDataField("comments", String.class, this.dataSource);
        commentField.setMaxLength(255);
        int y = 10;
        int x = 80;

        topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        topPanel.addComponent(supplierField, x + 220, y, 180, 20, "供应商", 60);
        topPanel.addComponent(inDateField, x + 490, y, 100, 20, "发生日期", 60);

        y += ROW_SPAN;
        topPanel.addComponent(adminField, x, y, 150, 20, "业务员", 60);
        topPanel.addComponent(departmentField, x + 220, y, 180, 20, "部门", 60);
        topPanel.addComponent(storageField, x + 490, y, 180, 20, "仓库", 60);

        y += ROW_SPAN;
        topPanel.addComponent(commentField, x, y, 670, 20, "备注", 60);
        return topPanel;
    }
 @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        SaleReportVo vo=new SaleReportVo();
       vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setEmployee(supplierField.getText());
     vo.setCustomer(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageField.getText());
        vo.setTitle("商品调价");
           Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/sale/SaleChangeMoneyDetailReport.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
    @Override
    protected void initColumns() {
        ITableColumnModel columnModel = table.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
        column.setHeaderText("商品编号");
        column.setColumnName("product.number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("商品名称");
        column.setColumnName("product.name");
        column.setWidth(150);
        columnModel.addColumn(column);


        column = new JDataTableColumn();
        column.setHeaderText("规格");
        column.setColumnName("product.spec");
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("零售价");
        column.setColumnName("price");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("采购价");
        column.setColumnName("purchaseprice");
        column.setWidth(80);
              column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("批发价");
        column.setColumnName("whlesale");
        column.setWidth(80);
          column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("批发价一");
        column.setColumnName("whlesale1");
        column.setWidth(80);
          column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("批发价二");
        column.setColumnName("website2");
        column.setWidth(100);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("批发价三");
        column.setColumnName("website3");
        column.setWidth(80);
          column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("批发价四");
        column.setColumnName("website4");
        column.setWidth(80);
          column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("批发价五");
        column.setColumnName("website5");
        column.setWidth(80);
          column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("备注");
        column.setColumnName("comments");
        column.setWidth(150);
        columnModel.addColumn(column);
    }

    @Override
    public CSaleProductChangeMoneyDialog getNewProductSelectDialog()
    {
        return new CSaleProductChangeMoneyDialog(this, this.table.getDataSource(), this.getStorage());
    }

    @Override
    protected Class getDetailClass() {
        return SaleChangeMoneyDetailPo.class;
    }
}
