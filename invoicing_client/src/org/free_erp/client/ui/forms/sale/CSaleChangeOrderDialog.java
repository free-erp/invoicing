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
import org.free_erp.service.entity.sale.SalechaseOrderDetailPo;
import org.free_erp.service.entity.sale.SalechaseOrderPo;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CSaleChangeOrderDialog  extends CBaseSaleMainDetailDialog
{
	protected JDataDatePicker inDateField;
	protected JDataField commentField;

    public CSaleChangeOrderDialog (Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
        this.setTitle("���۶������");
	}
    
    public CSaleChangeOrderDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		this.setTitle("���۶������");
	}

  @Override
    public CSaleProductDialog getNewProductSelectDialog()
    {
        return new CSaleProductDialog(this, this.table.getDataSource(), this.getStorage());
    }

    @Override
    public void newDataRow() {
        SalechaseOrderPo po = new SalechaseOrderPo();
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
		topPanel.setPreferredSize(new Dimension(700, 100));
		topPanel.setLayout(null);
	
		inDateField = new JDataDatePicker("formDate", this.dataSource);
		commentField = new JDataField("comments", String.class, this.dataSource);
        commentField.setMaxLength(255);

		int y = 10;
		int x = 80;
		topPanel.addComponent(idField, x, y, 150, 20, "���ݱ��", 60);
		topPanel.addComponent(supplierField, x + 220, y, 180, 20, "�ͻ�", 60);
		topPanel.addComponent(inDateField, x + 490, y, 100, 20, "��������", 60);

		y += ROW_SPAN;
		topPanel.addComponent(adminField, x, y, 150, 20, "¼����", 60);
		topPanel.addComponent(departmentField, x + 220, y, 180, 20, "����", 60);
		topPanel.addComponent(storageField, x + 490, y, 180, 20, "�ֿ�", 60);

		y += ROW_SPAN;
		topPanel.addComponent(commentField, x, y, 670, 20, "��ע", 60);
		return topPanel;
    }

    @Override
    protected void initColumns()
    {
         ITableColumnModel columnModel = table.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("��Ʒ���");
		column.setColumnName("product.number");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("��Ʒ����");
		column.setColumnName("product.name");
		column.setWidth(150);
		columnModel.addColumn(column);
//
//		column = new JDataTableColumn();
//		column.setHeaderText("��Ʒ���");
//		column.setColumnName("product.catalog.name");
//		column.setWidth(120);
////		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("����ͺ�");
		column.setColumnName("product.spec");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��λ");
		column.setColumnName("product.smallUnit");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("����");
		column.setColumnName("price");
		column.setWidth(80);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ۿ���");
		column.setColumnName("rate");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ۿ۵���");
		column.setColumnName("taxPrice");
		column.setWidth(100);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("����");
		column.setColumnName("amount");
        column.setTotalRowVisible(true);
		column.setWidth(100);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("��λ");
		column.setColumnName("shelf");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("��ע");
		column.setColumnName("comments");
		column.setWidth(150);
		columnModel.addColumn(column);
    }

    @Override
    protected Class getDetailClass() {
        return SalechaseOrderDetailPo.class;
    }
}

