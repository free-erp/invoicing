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
/*移库管理
 * @author tengzhuolin
 */
import java.awt.Dimension;
import java.awt.Frame;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseStorageMainDetailDialog;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.MoveStorageDetailPo;
import org.free_erp.service.entity.storage.MoveStoragePo;
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
public class CStorageMoveDialog extends CBaseStorageMainDetailDialog
{
	protected JDataDatePicker inDateField;
	protected JDataTableComboBox storageOutField;
	protected JDataField commentField;
    
	public CStorageMoveDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
		initCs();
	}
	public CStorageMoveDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		initCs();
	}
	private void initCs()
	{
		this.setTitle("移库单");
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
		column.setHeaderText("库存单价");
		column.setColumnName("price");
		column.setWidth(80);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("库存数量");
        column.setTotalRowVisible(true);
		column.setColumnName("oldAmount");
		column.setWidth(80);
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("移库数量");
        column.setTotalRowVisible(true);
		column.setColumnName("amount");
		column.setWidth(80);
        column.setTotalRowVisible(true);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("移库金额");
        column.setTotalRowVisible(true);
		column.setColumnName("totailMoney");
		column.setWidth(100);
        column.setTotalRowVisible(true);
        column.setValueType(Currency.class);
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
		MoveStoragePo po = new MoveStoragePo();
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
//        idField.setEditable(false);
        //idField.setRequired(true);
        //idField.setDisplayName("编号");
		//idField.setEditable(false);
		//storageField = new JDataComboBox("storage",Storage.class, this.dataSource,Main.getMainFrame().getObjectsPool().getStorages());
		storageField.setRequired(true);
		storageField.setDisplayName("移入仓库");
		storageOutField = new JDataTableComboBox("outStorage", Storage.class, this.dataSource, Main.getMainFrame().getObjectsPool().getStorageOutTable(),"name");
		storageOutField.setRequired(true);
		storageOutField.setDisplayName("移出仓库");
		//adminField = new JDataComboBox("chargePerson",String.class, this.dataSource,Main.getMainFrame().getObjectsPool().getEmployees());
		//departmentField = new JDataComboBox("department", String.class,this.dataSource, Main.getMainFrame().getObjectsPool().getDepartments());
		inDateField = new JDataDatePicker("formDate", this.dataSource);
		commentField = new JDataField("comments",String.class,this.dataSource);
        commentField.setMaxLength(255);
		int y = 10;
		int x = 80;
		topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
		topPanel.addComponent(storageOutField, x + 220, y, 150, 20, "移出库", 60);
		topPanel.addComponent(storageField, x + 450, y, 150, 20, "移入库", 60);
		y += ROW_SPAN;
		topPanel.addComponent(inDateField, x, y, 150, 20, "发生日期", 60);
		topPanel.addComponent(adminField, x + 220, y, 150, 20, "保管员", 60);
		topPanel.addComponent(departmentField, x + 450, y, 150, 20, "部门", 60);
		y += ROW_SPAN;
		topPanel.addComponent(commentField, x, y, 590, 20, "备注", 60);
		return topPanel;
	}
	protected Class getDetailClass()
	{
		return MoveStorageDetailPo.class;
	}
    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        vo.setTitle("商品移库单报表");
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setChargePerson(adminField.getSelectedItem().toString());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageOutField.getSelectedItem().toString());
        vo.setInStorage(storageField.getSelectedItem().toString());
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageMoveDetail.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
    @Override
    public CBaseProductSelectDialog getNewProductSelectDialog()
    {
        return new CStorageMoveProductDialog(this, this.table.getDataSource(), this.getOutStorage());
    }
    protected Storage getOutStorage()
    {
        Storage outStorage = (Storage)this.storageOutField.getSelectedItem();
        return outStorage;
    }
    @Override
    protected void doAdd() {
        if (!checkIsSameStorage())
        {
            super.doAdd();
        }
        else
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "移入移出库不能为空,并且不能为同一个仓库!");
        }
        
    }
    @Override
    protected boolean doSave()
    {
        if(!checkIsSameStorage())
        {
            return super.doSave();
        }
        else
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "移入移出库不能为空,并且不能为同一个仓库!");
            return false;
        }
    }
    private boolean checkIsSameStorage()
    {
        Storage inStorage = (Storage)this.storageField.getSelectedItem();
        Storage outStorage = (Storage)this.storageOutField.getSelectedItem();
        //temp
        if (inStorage == null || outStorage == null)
        {
            return true;
        }
        else  if (inStorage == outStorage || inStorage.equals(outStorage))
        {
            return true;
        }
        return false;
    }
    @Override
    protected void doEdit() {
        if (!checkIsSameStorage())
        {
            super.doEdit();
        }
        else
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "移入移出库不能为空,并且不能为同一个仓库!");
        }
    }   
}
