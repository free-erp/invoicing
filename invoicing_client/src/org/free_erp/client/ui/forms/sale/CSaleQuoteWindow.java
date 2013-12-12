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
import org.free_erp.service.entity.sale.SaleQuotePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.ISaleService;
import java.awt.Image;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 销售报价
 * 滕卓林
 */
public class CSaleQuoteWindow extends CBaseSaleListWindow
{
	public CSaleQuoteWindow(String title)
	{
		super(title);		
		this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");		
		this.initTableColumns();
        initDatas();
	}
	 
    private void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
		ISaleService saleService = Main.getServiceManager().getSaleService();
		List<SaleQuotePo> SaleQuotePos = saleService.getAllQuoteForms(Main.getMainFrame().getCompany());
		for(SaleQuotePo po:SaleQuotePos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        this.dataSource.sortById(IDataSource.DESC_SORT);
	}
    protected String getExcelExportTitle()
    {
        return "商品报价";
    }
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        vo.setTitle("商品报价报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/sale/SaleQuoteReport.jasper"),variables, this.dataSource.getDataRows());
        printDialog.setVisible(true);
    }
	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("状态");
		column.setColumnName("statusString");
		column.setWidth(70);
		columnModel.addColumn(column);
        	column = new JDataTableColumn();
		column.setHeaderText("仓库");
		column.setColumnName("storage");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("number");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
	    column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(100);
        column.setValueType(Date.class);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("业务员");
		column.setColumnName("employee");
		column.setWidth(80);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("部门");
		column.setColumnName("department");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("客户");
		column.setColumnName("customer");
		column.setWidth(150);
		columnModel.addColumn(column);
       column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(150);
		columnModel.addColumn(column);	
	}
	@Override
	protected void doRemove()
	{
		super.doRemove();
	}
	
	@Override
	protected void doExport()
	{
		super.doExport();
	}
    @Override
    protected void doSearch() {
    }
   
   @Override
     protected CBaseFormDialog getFormDialog()
	{
        if (formDialog == null)
        {
            formDialog =  new CSaleQuoteDialog(this, this.dataSource, dbSupport);
        }
        return formDialog;
	}
	
}