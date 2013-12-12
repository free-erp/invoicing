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

package org.free_erp.client.ui.forms.warehouse;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseSelectDetailDialog;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageMoveDetailDialog extends CBaseSelectDetailDialog
{
    protected JDataField idField;
    protected JDataField adminField;
	protected JDataField departmentField;
    protected JDataField storageField;
    protected JDataDatePicker inDateField;
	protected JDataField storageOutField;
	protected JDataField commentField;
    
    public CStorageMoveDetailDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
        this.setTitle("�ֿ��ƿ���ϸ");
	}

    @Override
    protected CPanel getMainPanel()
    {
        CPanel topPanel = new CPanel();
		topPanel.setPreferredSize(new Dimension(500, 100));
		topPanel.setLayout(null);
        idField = new JDataField("number",String.class,this.dataSource);
        idField.setEditable(false);
        adminField = new JDataField("employee", Employee.class,this.dataSource);
        adminField.setEditable(false);
        departmentField = new JDataField("department", String.class,this.dataSource);
        departmentField.setEditable(false);
		storageField = new JDataField("storage", Storage.class, this.dataSource);
        storageField.setEditable(false);
        storageOutField = new JDataField("outStorage",Storage.class, this.dataSource);
        storageOutField.setEditable(false);
		inDateField = new JDataDatePicker("formDate", this.dataSource);
        inDateField.setEnabled(false);
		commentField = new JDataField("comments", String.class, this.dataSource);
        commentField.setEditable(false);

        int y = 10;
		int x = 80;

		topPanel.addComponent(idField, x, y, 150, 20, "���ݱ��", 60);
		topPanel.addComponent(storageOutField, x + 220, y, 150, 20, "�Ƴ���", 60);
		topPanel.addComponent(storageField, x + 450, y, 150, 20, "�����", 60);
		y += ROW_SPAN;
		topPanel.addComponent(inDateField, x, y, 150, 20, "��������", 60);
		topPanel.addComponent(adminField, x + 220, y, 150, 20, "¼��Ա", 60);
		topPanel.addComponent(departmentField, x + 450, y, 150, 20, "����", 60);

		y += ROW_SPAN;

		topPanel.addComponent(commentField, x, y, 590, 20, "��ע", 60);

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
		column.setHeaderText("��浥��");
		column.setColumnName("price");
		column.setWidth(80);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�������");
		column.setColumnName("oldAmount");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ƿ�����");
		column.setColumnName("amount");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("�ƿ���");
		column.setColumnName("totailMoney");
		column.setWidth(100);
        column.setValueType(Currency.class);
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
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        vo.setTitle("��Ʒ�ƿⵥ����");
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setChargePerson(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setOutStorage(storageOutField.getText());
        vo.setInStorage(storageField.getText());
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageMove_Report.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
}