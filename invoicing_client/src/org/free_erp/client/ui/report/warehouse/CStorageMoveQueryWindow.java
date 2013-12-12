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

/**
 *库存商品余额WINDOW
 * @author tengzhuolin
 */
import com.jdatabeans.beans.CMoneyField;
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
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.warehouse.CStorageMoveDetailDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.MoveStoragePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.StorageQueryVo;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IStorageService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *报损
 * @Changer  TENGZHUOLIN
 */
public class CStorageMoveQueryWindow extends CBaseQueryWindow  {

    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private JDataPanelComboBox numberField;
    private JDataTableComboBox storageField;
    private JDataPanelComboBox nameField;
    private JDataComboBox catalogNameField;
    private JDataTableComboBox chargePerson;

    public CStorageMoveQueryWindow(String title)
    {
        super(title);
        initComps();
    }
  
   private void initComps()
   {
        this.setTitle("库存移库查询");
    }

   protected void clearInfo()
   {
       this.numberField.setEditorText("");
       this.storageField.setText("");
       this.beginDateField.setSelectedItem("");
       this.endDateField.setSelectedItem("");
       this.chargePerson.setText("");

    }
   protected void initColumns()
   {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("移出仓库");
		column.setColumnName("outStorage.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("移入仓库");
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
        column.setHeaderText("录入员");
        column.setColumnName("employee.name");
        column.setWidth(60);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("部门");
        column.setColumnName("department");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        
        column = new JDataTableColumn();
        column.setHeaderText("金额");
        column.setColumnName("totailMoney");
        column.setWidth(100);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("备注");
        column.setColumnName("comments");
        column.setWidth(120);
        columnModel.addColumn(column);

    }
    @Override
    protected CPanel getMainPanel() {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700, 60));
		panel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        beginDateField.setSelectedItem("");
        //numberField =  new JDataPanelComboBox("number", String.class, Main.getMainFrame().getProductSelectPanel());
        storageField = new JDataTableComboBox("", Storage.class, Main.getMainFrame().getObjectsPool().getStorageTable(), "name");
        //nameField = new JDataPanelComboBox("", String.class, this.dataSource, Main.getMainFrame().getProductSelectPanel());
        //catalogNameField = new JDataComboBox("",String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getProductCatalogs());
        chargePerson = new JDataTableComboBox("",Employee.class,Main.getMainFrame().getObjectsPool().getEmployeeTable() , "name");
        int x = 40;
        int y = 15;
        panel.addComponent(storageField, x, y, 120, 20, "仓库", 30);
        panel.addComponent(chargePerson, x + 170, y, 100, 20, "业务员", 50);
        panel.addComponent(beginDateField, x + 320, y, 100, 20, "查询时间", 50);
        panel.addComponent(endDateField, x + 440, y, 100, 20, "至", 20);
        panel.addComponent(selectButton, x + 545, y, 75, 25);
        panel.addComponent(clearButton, x + 625, y, 75, 25);
        return panel;
    }

    @Override
    protected void select()
    {
        StorageQueryVo vo = new StorageQueryVo(Main.getMainFrame().getCompany());
        vo.setStatus(1);
        Object obj = storageField.getSelectedItem();
        if (obj != null && obj instanceof Storage && this.storageField.getText() != null && !this.storageField.getText().equals(""))
        {
            vo.setStorage((Storage)storageField.getSelectedItem());
        }

        obj = this.chargePerson.getSelectedItem();
        if(obj != null && obj instanceof Employee && this.chargePerson.getText() != null && !this.chargePerson.getText().equals(""))
        {
            vo.setEmployee((Employee)this.chargePerson.getSelectedItem());
        }
        
        if(!beginDateField.getEditorText().equals(""))
        {
            obj = beginDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDatePo(startDate);
            }
        }

        if(!endDateField.getEditorText().equals(""))
        {
            obj = endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date endDate = (Date)obj;
                vo.setEndDatePo(endDate);
            }
        }

        ITableModel model = this.dataTable.getTableModel();
        IStorageService storageService = Main.getServiceManager().getStorageService();
        this.dataSource.clear();
        List<MoveStoragePo> MoveStoragePos = storageService.findMoveStorageForms(vo);
        
		for(MoveStoragePo po:MoveStoragePos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
        if(MoveStoragePos == null || MoveStoragePos.size() == 0)
        {
            this.clearInfo();
        }
    }

    @Override
    protected CPanel getTotalPanel()
    {
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(600, 40));
        this.totalMoneyField = new CMoneyField();
        bottomPanel.addComponent(totalMoneyField, 100, 10, 150, 20, "合计金额", 60);
        return bottomPanel;
    }

    protected String getExcelExportTitle()
    {
        return "库存商品移库查询";
    }

    protected void doPrint()
    {
        Map variables=new HashMap();
        variables.put("JCOMPANY", Main.getMainFrame().getCompany().getName());
        ReportVO vo =new ReportVO();
        vo.setTitle("库存商品移库查询报表");
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog =new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/StorageReport.jasper"), variables,this.dataSource.getDataRows());
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
        CStorageMoveDetailDialog formDialog = new CStorageMoveDetailDialog(Main.getMainFrame(), this.dataSource, null);
		formDialog.setVisible(true);
    }
}
