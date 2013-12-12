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
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CChooseIndentDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.purchase.PurchaseOrderPo;
import org.free_erp.service.entity.purchase.PurchaseQueryVo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.purchase.IPurchaseService;
import org.free_erp.service.logic.ISystemManageService;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CPurchaseOrderWindow extends CBasePurchaseListWindow
{
    public CPurchaseOrderWindow(String title)
	{
		super(title);
         
             this.chooseButton.setVisible(true);
        this.chooseButton.setText("选择订单");
          
        
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
		column.setHeaderText("业务员");
		column.setColumnName("chargePerson");
		column.setWidth(60);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("部门");
		column.setColumnName("department");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("供应商");
		column.setColumnName("supplier");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("合计金额");
		column.setColumnName("totalMoney");
        column.setTotalRowVisible(true);
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
	}
    private void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
		IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
		List<PurchaseOrderPo> purchaseOrderPos = purchaseService.getAllPurchaseOrderPoForms(Main.getMainFrame().getCompany());
		for(PurchaseOrderPo po:purchaseOrderPos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        this.dataSource.sortById(IDataSource.DESC_SORT);
	}
    public void refreshWindowDatas()
    {
        this.initDatas();
    }
    
    @Override
    protected void doSearch()
    {
        this.allStatusButton.setSelected(true);
        this.doFilter();
		ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        PurchaseQueryVo vo = new PurchaseQueryVo(company);
        Object obj = null;
        if(!this.startDateField.getEditorText().equals(""))
        {
            obj = this.startDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date start_Date = (Date)obj;
                vo.setStartDatePo(start_Date);
            }
        }
        if(!this.endDateField.getEditorText().equals(""))
        {
            obj = this.endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date end_Date = (Date)obj;
                vo.setEndDatePo(end_Date);
            }
        }
        this.dataSource.clear();
        IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
        List<PurchaseOrderPo> purchaseOrderPos = purchaseService.findPurchaseOrderPoForms(vo);
        if(purchaseOrderPos == null || purchaseOrderPos.size() == 0)
        {
            this.clearInfo();
        }
        for(PurchaseOrderPo po:purchaseOrderPos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
    }
    @Override
    protected void removeNumberLog()
    {
        SystemLog systemLog = this.getSystemLog();
        systemLog.setContent("删除采购单-编号:" + this.removeNumber);
        ISystemManageService service = Main.getServiceManager().getSystemManageService();
        service.saveSystemLog(systemLog);
    }
    @Override
    protected CBaseFormDialog getFormDialog()
    {
        if (formDialog == null)
        {
            formDialog =  new CPurchaseOrderDialog(this, this.dataSource, dbSupport);
        }
        return formDialog;
    }
    @Override
    protected CChooseIndentDialog getChooseIndentDialog()
    {
        return new CChoosePurchaseIndentDialog(this, this.dataSource, dbSupport);
    }
    protected String getExcelExportTitle()
    {
        return "采购单";
    }
    @Override
	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        vo.setTitle("采购单信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/purchase/PurchaseIndentReport.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}