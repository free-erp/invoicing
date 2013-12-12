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

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.LossStorageDetailPo;
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
 *库存商品报损明细查询 Window
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageLossReportWindow extends CBaseQueryWindow implements ActionListener
{
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private JDataPanelComboBox numberField;
    private JDataComboBox sortField;
    private JDataTableComboBox storageField;

    public CStorageLossReportWindow(String title)
    {
        super(title);
    }
     
    @Override
    protected CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setPreferredSize(new Dimension(700, 80));
		topPanel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        beginDateField.setSelectedItem("");
        endDateField.setSelectedItem("");
        numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        //sortField = new JDataComboBox("",ProductCatalog.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());
        storageField = new JDataTableComboBox("", Storage.class, Main.getMainFrame().getObjectsPool().getStorageTable(), "name");

        int x = 60;
        int y = 15;
        topPanel.addComponent(storageField, x, y, 120, 20, "仓库", 50);
        //topPanel.addComponent(sortField, x + 170, y, 120, 20, "商品分类", 50);
        //topPanel.addComponent(numberField, x + 320, y, 120, 20, "商品", 30);
        topPanel.addComponent(numberField, x + 170, y, 120, 20, "商品编号", 50);

        y += 27;
        topPanel.addComponent(beginDateField, x, y, 120, 20, "查询时间", 50);
        topPanel.addComponent(endDateField, x + 170, y, 120, 20, "至", 50);
        topPanel.addComponent(selectButton, x + 440, y, 75, 25);
        topPanel.addComponent(clearButton, x + 520, y, 75, 25);
		return topPanel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
	    JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("单据编号");
		column.setColumnName("mainObject.number");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单据日期");
		column.setColumnName("mainObject.formDate");
        column.setValueType(Date.class);
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库编号");
        column.setColumnName("storage.number");
        column.setWidth(140);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库名称");
        column.setColumnName("storage.name");
        column.setWidth(140);
        columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("保管员");
		column.setColumnName("employee.name");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("商品编号");
		column.setColumnName("product.number");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("商品名称");
		column.setColumnName("product.name");
		column.setWidth(140);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("规格");
		column.setColumnName("product.spec");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("单位");
		column.setColumnName("product.smallUnit");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("数量");
		column.setColumnName("amount");
		column.setWidth(80);
		columnModel.addColumn(column);

    }

    @Override
    protected void select()
    {
        ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        StorageQueryVo vo = new StorageQueryVo(company);
        Object obj = storageField.getSelectedItem();
        if (obj != null && obj instanceof Storage && this.storageField.getText() != null && !this.storageField.getText().equals(""))
        {
            vo.setStorage((Storage)storageField.getSelectedItem());
        }

        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Product)
            {
                vo.setProduct((Product)obj);
            }
        }

        if(!beginDateField.getEditorText().equals(""))
        {
            obj = beginDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDate(startDate);
            }
        }

        if(!endDateField.getEditorText().equals(""))
        {
            obj = endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date endDate = (Date)obj;
                vo.setEndDate(endDate);
            }
        }

        this.dataSource.clear();
        IStorageService storageService = Main.getServiceManager().getStorageService();
        List<LossStorageDetailPo> lossStorageDetailPos = storageService.findLossStorageDetails(vo);
        
        for(LossStorageDetailPo po:lossStorageDetailPos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
        if(lossStorageDetailPos == null || lossStorageDetailPos.size() == 0)
        {
            this.clearInfo();
        }
    }

    @Override
    protected CPanel getTotalPanel()
    {
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(600, 40));
        this.totalAmountField = new CField();
        this.totalAmountField.setEnabled(false);
        bottomPanel.addComponent(totalAmountField, 100, 10, 150, 20, "合计数量", 60);
        return bottomPanel;
    }

    @Override
    protected void clearInfo()
    {
        beginDateField.setSelectedItem("");
        endDateField.setSelectedItem("");
        numberField.setEditorText("");
        //sortField.setSelectedItem("");
        storageField.setText("");
    }

    protected String getExcelExportTitle()
    {
        return "库存商品报损明细";
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("库存商品报损明细报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/rStorageIndentProduct.jasper"),variables, this.dataSource.getDataRows());
        printDialog.setVisible(true);
    }
}
