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
import java.awt.Dimension;
import java.awt.Frame;
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
import org.free_erp.client.ui.forms.CBaseStorageMainDetailDialog;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.storage.OutflowStorageDetailPo;
import org.free_erp.service.entity.storage.OutflowStoragePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @Changer liufei
 */
public class CStorageOutflowDialog extends CBaseStorageMainDetailDialog
{
	protected JDataDatePicker inDateField;
	protected JDataField commentField;
    
	public CStorageOutflowDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
		initCs();
	}
	public CStorageOutflowDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		initCs();
	}
	private void initCs()
	{
		this.setTitle("报溢单");
	}
        
    
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
		column.setHeaderText("帐面数量");
		column.setColumnName("oldAmount");
		column.setWidth(80);
        
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("帐面金额");
		column.setColumnName("oldMoney");
		column.setWidth(100);
        column.setTotalRowVisible(true);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("报溢数量");
		column.setColumnName("amount");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("报溢金额");
        column.setValueType(Currency.class);
		column.setColumnName("totailMoney");
		column.setWidth(100);
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("货位");
		column.setColumnName("shelf");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(150);
		columnModel.addColumn(column);
	}
		@Override
	public void newDataRow()
	{
		OutflowStoragePo po = new OutflowStoragePo();
		po.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
		dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
	}
	public CPanel getMainPanel()
	{
		CPanel topPanel = new CPanel();
		topPanel.setPreferredSize(new Dimension(500, 100));
		topPanel.setLayout(null);
//		idField = new JDataField("number",String.class,this.dataSource);
//	    idField.setEditable(false);
        //idField.setRequired(true);
        //idField.setDisplayName("编号");
		//storageField = new JDataComboBox("storage",Storage.class, this.dataSource,Main.getMainFrame().getObjectsPool().getStorages());
		storageField.setRequired(true);
		storageField.setDisplayName("仓库信息");
		//adminField = new JDataComboBox("chargePerson",String.class, this.dataSource,Main.getMainFrame().getObjectsPool().getEmployees());
		//departmentField = new JDataComboBox("department", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getDepartments());
		inDateField = new JDataDatePicker("formDate", this.dataSource);
		commentField = new JDataField("comments",String.class,this.dataSource);
        commentField.setMaxLength(255);
        
		int y = 10;
		int x = 80;
		topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
		//topPanel.addComponent(customerField, x + 170, y, 180, 20, "客户", 60);
		topPanel.addComponent(storageField, x + 220, y, 180, 20, "仓库", 60);
		topPanel.addComponent(inDateField, x + 490, y, 100, 20, "发生日期", 60);
		y += ROW_SPAN;
		topPanel.addComponent(adminField, x, y, 150, 20, "保管员", 60);
		topPanel.addComponent(departmentField, x + 220, y, 180, 20, "部门", 60);
		//topPanel.addComponent(inDateField, x + 440, y, 100, 20, "供应商", 60);
		y += ROW_SPAN;
		topPanel.addComponent(commentField, x, y, 590, 20, "备注", 60);
		return topPanel;
	}
	protected Class getDetailClass()
	{
		return OutflowStorageDetailPo.class;
	}
    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setChargePerson(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageField.getText());
          vo.setTotalMoney(this.sumField.getText());
        vo.setStrTotalMoney(this.chineseMoneyField.getText());
        vo.setTitle("库存商品报溢报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageOutflowDetail.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
    @Override
    public CBaseProductSelectDialog getNewProductSelectDialog()
    {
        return new CStorageOutflowProductDialog(this, this.table.getDataSource(), this.getStorage());
    }
}
