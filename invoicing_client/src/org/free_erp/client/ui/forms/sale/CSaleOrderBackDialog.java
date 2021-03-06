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
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;

import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.service.entity.sale.SaleOrderBackPo;
import org.free_erp.client.ui.util.SaleReportVo;
import org.free_erp.service.entity.sale.SaleOrderBackDetailPo;

import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CSaleOrderBackDialog extends CBaseSaleMainDetailDialog {

    protected JDataField commentField;
    protected JDataField clearingField;
    protected JDataField payField;


    public CSaleOrderBackDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
        this.setTitle("销售退货单");
    }
    
    public CSaleOrderBackDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		this.setTitle("销售退货单");
	}

    @Override
    protected void doPrint() {
        Map variables = new HashMap();
        SaleReportVo vo = new SaleReportVo();
        vo.setNumber(idField.getText());
        vo.setFromDate((Date) inDateField.getSelectedItem());
        vo.setEmployee(supplierField.getText());
        vo.setCustomer(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageField.getText());
        vo.setPayTerm(payTimeField.getText());
        vo.setTotalMoney(this.sumField.getText());
        vo.setStrTotalMoney(this.chineseMoneyField.getText());
         vo.setOffersMoney(this.offersMoneyField.getText());
//        vo.setReceiving(this.receivingField.getText());
//        vo.setReceivingAddress(this.receivingAddressField.getText());
//        vo.setReceivingCode(this.receivingField.getText());
//        vo.setReceivingPhone(this.receivingCodeField.getText());
//        vo.setDelivery(this.deliveryField.getText());
           Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("销售退货单");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/sale/SaleBackDetailReport.jasper"), variables, this.table.getDataSource().getDataRows());
        printDialog.setVisible(true);
    }

    @Override
    public void newDataRow() {
        SaleOrderBackPo po = new SaleOrderBackPo();
        po.setCompany(Main.getMainFrame().getCompany());
        IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
        dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
    }

    @Override
    protected CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(700, 120));
        topPanel.setLayout(null);

        inDateField = new JDataDatePicker("formDate", this.dataSource);
        commentField = new JDataField("comments", String.class, this.dataSource);
        clearingField = new JDataField("clearing", String.class, this.dataSource);
        payField = new JDataField("pay", String.class, this.dataSource);
        payTimeField=new JDataNumberField("payTerm", Integer.class, this.dataSource);
        payTimeField.addValueChangedListener(this);
        commentField.setMaxLength(255);

        int y = 10;
        int x = 80;

        topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        topPanel.addComponent(supplierField, x + 220, y, 180, 20, "客户", 60);
        topPanel.addComponent(inDateField, x + 490, y, 100, 20, "发生日期", 60);

        y += ROW_SPAN;
        topPanel.addComponent(adminField, x, y, 150, 20, "录入人", 60);
        topPanel.addComponent(departmentField, x + 220, y, 180, 20, "部门", 60);
        topPanel.addComponent(storageField, x + 490, y, 180, 20, "仓库", 60);
//        y += ROW_SPAN;
//        topPanel.addComponent(balanceFashionField, x, y, 150, 20, "结算方式", 60);
//        topPanel.addComponent(payFashionField, x + 220, y, 180, 20, "付款方式", 60);
//        topPanel.addComponent(payTimeField, x + 490, y, 180, 20, "付款期限", 60);

        y += ROW_SPAN;
        topPanel.addComponent(payTimeField, x, y, 150, 20, "付款期限", 60);
        topPanel.addComponent(commentField, x + 220, y, 450, 20, "备注", 60);
        return topPanel;
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
        column.setHeaderText("规格型号");
        column.setColumnName("product.spec");
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(60);
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
        column.setTotalRowVisible(true);
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
       column.setHeaderText("金额");
		column.setColumnName("totalMoney");
        column.setWidth(80);
        column.setTotalRowVisible(true);
         column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("备注");
        column.setColumnName("comments");
        column.setWidth(150);
        columnModel.addColumn(column);
    }

    @Override
    public CBaseProductSelectDialog getNewProductSelectDialog()
    {
        return new CSaleOrderProductDialog(this, this.table.getDataSource(), this.getStorage());
    }

    @Override
    protected Class getDetailClass() {
        return SaleOrderBackDetailPo.class;
    }
}



