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
package org.free_erp.client.ui.report.warehouse;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.warehouse.CStorageMinMaxDetailDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.MinMaxStoragePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.StorageQueryVo;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IStorageService;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *库存商品上下限查询 Window
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageMinMaxQueryWindow extends CBaseQueryWindow implements ActionListener
{
    private JDataTableComboBox storageField;
    private JDataComboBox sortField;
    private JDataPanelComboBox numberField;
    private JDataField nameField;

    public CStorageMinMaxQueryWindow(String title)
    {
        super(title);
    }
     

    @Override
    protected void select()
    {
        ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        StorageQueryVo vo = new StorageQueryVo(company);
        vo.setStatus(1);
        Object obj = storageField.getSelectedItem();
        if (obj != null && obj instanceof Storage && this.storageField.getText() != null && !this.storageField.getText().equals(""))
        {
            vo.setStorage((Storage)storageField.getSelectedItem());
        }
        
        this.dataSource.clear();
        IStorageService storageService = Main.getServiceManager().getStorageService();
        List<MinMaxStoragePo> minmaxStoragePos = storageService.findMinMaxStorageForms(vo);
        
        for(MinMaxStoragePo po:minmaxStoragePos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
        if(minmaxStoragePos == null || minmaxStoragePos.size() == 0)
        {
            this.clearInfo();
        }
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel topPanel = new CPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setPreferredSize(new Dimension(700, 40));
		topPanel.setLayout(null);
        storageField = new JDataTableComboBox("", Storage.class, Main.getMainFrame().getObjectsPool().getStorageTable(), "name");
        //sortField = new JDataComboBox("",ProductCatalog.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());
        //numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        //nameField = new JDataField();

        int x = 60;
        int y = 15;
        topPanel.addComponent(storageField, x, y, 120, 20, "仓库", 50);
        //topPanel.addComponent(sortField, x + 170, y, 120, 20, "商品分类", 50);
        //topPanel.addComponent(numberField, x + 340, y, 120, 20, "商品编号", 50);
        //topPanel.addComponent(numberField, x + 170, y, 120, 20, "商品编号", 50);

        //y += 27;
        //topPanel.addComponent(nameField, x, y, 120, 20, "商品名称", 50);;
        topPanel.addComponent(selectButton, x + 460, y, 75, 25);
        topPanel.addComponent(clearButton, x + 540, y, 75, 25);
		return topPanel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
	    JDataTableColumn column = new JDataTableColumn();
	    column.setHeaderText("仓库编号");
        column.setColumnName("storage.number");
        column.setWidth(120);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("仓库名称");
        column.setColumnName("storage.name");
        column.setWidth(80);
        columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("formDate");
		column.setWidth(80);
		column.setValueType(Date.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("number");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("保管员");
		column.setColumnName("employee.name");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("部门");
        column.setColumnName("department");
        column.setWidth(80);
        columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(120);
		columnModel.addColumn(column);
    }

    @Override
    protected void clearInfo()
    {
        storageField.setText("");
        //sortField.setSelectedItem("");
        //numberField.setEditorText("");
        //nameField.setText("");
    }

    protected String getExcelExportTitle()
    {
        return "库存商品上下限";
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        vo.setTitle("库存商品上下限报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageMinReport.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    @Override
    protected void doShowDetail()
    {
        if (this.dataTable.getSelectedRow() < 0)
		{
			MessageBox.showErrorDialog(Main.getMainFrame(), "没有任何数据行被选中!");
			return;
		}
		this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
        CStorageMinMaxDetailDialog formDialog = new CStorageMinMaxDetailDialog(Main.getMainFrame(), this.dataSource, null);
		formDialog.setVisible(true);
    }
}
