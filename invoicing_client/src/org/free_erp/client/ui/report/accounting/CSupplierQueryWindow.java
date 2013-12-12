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

package org.free_erp.client.ui.report.accounting;

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.CustomerPaymentDetail;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.logic.IAccountingService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��Ӧ��������ˮ��ѯ
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CSupplierQueryWindow extends  CCustomerQueryWindow
{
    public CSupplierQueryWindow(String title)
    {
        super(title);
    }

    protected void select()
    {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        //�漰���ֽ����еĵ���
        List<CustomerPaymentDetail> vos = service.findCustomerPaymentDetails(this.beginDateField.getDate(), this.endDateField.getDate(), (Customer)this.customerField.getSelectedItem(), Main.getMainFrame().getCompany());
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        if(vos == null || vos.size() == 0)
        {
            this.clearInfo();
        }
        for(CustomerPaymentDetail vo:vos)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(dataRows);
    }

    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("��ˮ��");
		column.setColumnName("id");
		column.setWidth(50);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("�ͻ����");
		column.setColumnName("customer.number");
		column.setWidth(70);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("�ͻ�����");
		column.setColumnName("customer.name");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("��������");
		column.setColumnName("formDate");
		column.setWidth(80);
		column.setValueType(Date.class);
        columnModel.addColumn(column);


        column = new JDataTableColumn();
        column.setHeaderText("���ݱ��");
        column.setColumnName("formNo");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("ҵ������");
        column.setColumnName("bussinessType");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("ҵ��Ա");
        column.setColumnName("employee.name");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("Ӧ�����");
        column.setColumnName("receivableMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������");
        column.setColumnName("affordMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������");
        column.setColumnName("clearingMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("���Ͻ��");
        column.setColumnName("clearingMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("Ӧ�����");
        column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
    }

    protected String getExcelExportTitle()
    {
        return "��Ӧ��������ˮ��ѯ";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("��Ӧ��������ˮ��ѯ����");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rSupplierQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
