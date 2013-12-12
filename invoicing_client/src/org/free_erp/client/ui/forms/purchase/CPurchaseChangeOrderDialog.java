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
package org.free_erp.client.ui.forms.purchase;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.purchase.PurchaseChangeDetailPo;
import org.free_erp.service.entity.purchase.PurchaseIndentPo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseChangeOrderDialog extends CBasePurchaseMainDetailDialog
{
    //临时
    protected JDataField defaultPriceField;

    public CPurchaseChangeOrderDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
        this.setTitle("采购订单变更");
	}

    public CPurchaseChangeOrderDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		this.setTitle("采购订单变更");
	}

    

    @Override
    public void newDataRow()
    {
        PurchaseIndentPo po = new PurchaseIndentPo();
		po.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
		dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel topPanel = new CPanel();
		topPanel.setPreferredSize(new Dimension(700, 140));
		topPanel.setLayout(null);
        //临时
        this.defaultPriceField = new JDataField();

		int y = 10;
		int x = 80;

		topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
		topPanel.addComponent(supplierField, x + 220, y, 180, 20, "供应商", 60);
		topPanel.addComponent(inDateField, x + 490, y, 100, 20, "发生日期", 60);

		y += ROW_SPAN;
		topPanel.addComponent(adminField, x, y, 150, 20, "业务员", 60);
		topPanel.addComponent(departmentField, x + 220, y, 180, 20, "部门", 60);
		topPanel.addComponent(storageField, x + 490, y, 180, 20, "仓库", 60);
        //临时
        //y += ROW_SPAN;
		//topPanel.addComponent(defaultPriceField, x, y, 150, 20, "默认价格", 60);
		//topPanel.addComponent(payFashionField, x + 220, y, 180, 20, "付款方式", 60);
		//topPanel.addComponent(balanceFashionField, x + 490, y, 180, 20, "结算方式", 60);

        y += ROW_SPAN;
        topPanel.addComponent(payDateField, x , y, 138, 20, "付款期限", 60);
        topPanel.addComponent(new JLabel("天"), x + 138 , y, 12, 20);
		y += ROW_SPAN;
		topPanel.addComponent(commentField, x, y, 670, 20, "备注", 60);
           payDateField.addValueChangedListener(this);
		return topPanel;
    }

    @Override
    protected void initColumns()
    {
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
		column.setHeaderText("单价");
		column.setColumnName("price");
		column.setWidth(80);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("折扣率");
		column.setColumnName("discount");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("折扣价");
		column.setColumnName("disprice");
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
		column.setWidth(100);
        column.setValueType(Currency.class);
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(150);
		columnModel.addColumn(column);
    }

    @Override
    protected Class getDetailClass()
    {
        return PurchaseChangeDetailPo.class;
    }

    @Override
    public CBasePurchaseProductDialog getNewProductSelectDialog()
    {
        return new CBasePurchaseProductDialog(this, this.table.getDataSource(), this.getStorage());
    }

    @Override
    protected void setInitValues()
    {
        if (payDateField.getText() == null || payDateField.getText().equals(""))
        {
            payDateField.setText("30");
        }
    }

    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setSupplier(supplierField.getText());
        vo.setChargePerson(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageField.getText());
        vo.setPayTerm(payDateField.getText());
        vo.setTotalMoney(this.sumField.getText());
        vo.setStrTotalMoney(this.chineseMoneyField.getText());
        vo.setOffersMoney(this.offerMoneyField.getText());
        vo.setTitle("采购订单变更明细信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/purchase/PurchaseIndentDetailReport.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
}


