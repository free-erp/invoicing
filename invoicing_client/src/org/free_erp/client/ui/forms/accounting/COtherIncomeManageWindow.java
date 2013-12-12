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


package org.free_erp.client.ui.forms.accounting;

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.OtherIncome;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.AccountQueryVo;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author afa
 */
public class COtherIncomeManageWindow extends CBaseAccountListWindow
{
	public COtherIncomeManageWindow(String title)
	{
		super(title);
		this.initTableColumns();
		this.initDatas();
	}
     


	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("状态");
		column.setColumnName("statusString");
		column.setWidth(50);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
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

		column = new JDataTableColumn();
		column.setHeaderText("往来单位");
		column.setColumnName("customer.name");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("合计金额");
		column.setColumnName("totalMoney");
		column.setValueType(Currency.class);
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
	}

	private void initDatas()
	{
        IAccountingService service = Main.getServiceManager().getAccountingService();
		List<OtherIncome> pos = service.getAllOtherIncomeForms(Main.getMainFrame().getCompany());
		List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(OtherIncome po:pos)
		{
			dataRows.add(new ObjectDataRow(po, "id", dbSupport));
		}
        this.dataTable.getDataSource().insertDataRows(dataRows);
        this.dataSource.sortById(IDataSource.DESC_SORT);
	}

    @Override
    protected void doSearch()
    {
        this.allStatusButton.setSelected(true);
        this.doFilter();
		ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        AccountQueryVo vo = new AccountQueryVo();
        vo.setCompany(company);
        Object obj = null;
        if(!this.startDateField.getEditorText().equals(""))
        {
            obj = this.startDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDate(startDate);
            }
        }

        if(!this.endDateField.getEditorText().equals(""))
        {
            obj = this.endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date endDate = (Date)obj;
                vo.setEndDate(endDate);
            }
        }

        this.dataSource.clear();
        IAccountingService service = Main.getServiceManager().getAccountingService();
        List<OtherIncome> pos = service.findOtherIncomeForms(vo);
        if(pos == null || pos.size() == 0)
        {
            this.clearInfo();
        }
        for(OtherIncome po:pos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
    }

    @Override
     protected CBaseFormDialog getFormDialog()
	{
        if (formDialog == null)
        {
            formDialog =  new COtherIncomeDialog(this, this.dataSource, dbSupport);
        }
        return formDialog;
	}

    @Override
    protected void removeNumberLog()
    {

    }

    protected String getExcelExportTitle()
    {
        return "其它收入";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("其它收入报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/BussinessExpenseManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}