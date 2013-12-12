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
package org.free_erp.client.ui.forms.start;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CBaseListWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.storage.InitialStoragePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.StorageQueryVo;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IStorageService;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CBeginStorageWindow extends CBaseListWindow
{
	public CBeginStorageWindow(String title)
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
		column.setHeaderText("仓库");
		column.setColumnName("storage.name");
		column.setWidth(80);
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
		column.setHeaderText("保管员");
		column.setColumnName("employee.name");
		column.setWidth(70);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("部门");
		column.setColumnName("department");
		column.setWidth(100);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("合计金额");
		column.setColumnName("totailMoney");
        column.setTotalRowVisible(true);
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
        column.setTotalRowVisible(true);
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
	}
	private void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
		IStorageService storageService = Main.getServiceManager().getStorageService();
		List<InitialStoragePo> initialStoragePos = storageService.getAllInitialStorageForms(Main.getMainFrame().getCompany());
		for(InitialStoragePo po:initialStoragePos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        this.dataSource.sortById(IDataSource.DESC_SORT);
	}
	@Override
     protected CBaseFormDialog getFormDialog()
	{
        if (formDialog == null)
        {
            formDialog =  new CBeginStorageDialog(this, this.dataSource, dbSupport);
        }
        return formDialog;
	}
    protected String getExcelExportTitle()
    {
        return "库存期初";
    }
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        vo.setTitle("库存期初报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/BeginStorage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
    @Override
    protected void doSearch()
    {
        this.allStatusButton.setSelected(true);
        this.doFilter();
        ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        StorageQueryVo vo = new StorageQueryVo(company);
        Object obj = null;
        if(!this.startDateField.getEditorText().equals(""))
        {
            obj = this.startDateField.getDate();
            if(obj != null && obj instanceof Date)
            {
                Date start_Date = (Date)obj;
                vo.setStartDatePo(start_Date);
            }
        }
        if(!this.endDateField.getEditorText().equals(""))
        {
            obj = this.endDateField.getDate();
            if(obj != null && obj instanceof Date)
            {
                Date end_Date = (Date)obj;
                vo.setEndDatePo(end_Date);
            }
        }
        this.dataSource.clear();
        IStorageService storageService = Main.getServiceManager().getStorageService();
        List<InitialStoragePo> initialStoragePos = storageService.findInitialStorageForms(vo);
        if(initialStoragePos == null || initialStoragePos.size() == 0)
        {
            this.clearInfo();
        }
        for(InitialStoragePo po:initialStoragePos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
    }
    
    @Override
    protected void removeNumberLog()
    {
        SystemLog systemLog = this.getSystemLog();
        systemLog.setContent("删除库存期初单-编号:" + this.removeNumber);
        ISystemManageService service = Main.getServiceManager().getSystemManageService();
        service.saveSystemLog(systemLog);
    }
}