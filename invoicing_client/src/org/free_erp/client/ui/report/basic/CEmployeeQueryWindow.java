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
package org.free_erp.client.ui.report.basic;

import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.basic.CEmployeeInfoDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.logic.IEmployeeService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CEmployeeQueryWindow extends CBaseQueryWindow
{
    private JDataComboBox catalogField;
    private JDataTableComboBox numberField;
    private CField nameField;
    private CField shortNameField;
    private CField helpCodeField;
    public CEmployeeQueryWindow(String title)
    {
        super(title);
    }

    
    protected void clearInfo()
    {
        this.catalogField.setSelectedItem("");
        this.numberField.setSelectedItem(null);
        this.nameField.setText("");
        this.shortNameField.setText("");
        this.helpCodeField.setText("");
    }

    protected void select()
    {
        DataBaseQueryVO vo = new DataBaseQueryVO(Main.getMainFrame().getCompany());
        Object obj = null;
        obj = this.catalogField.getSelectedItem();
        if(obj != null && obj instanceof EmployeeCatalog)
        {
            vo.setCatalog(((EmployeeCatalog)obj).getName());
        }
        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Employee)
            {
                vo.setNumber(((Employee)obj).getNumber());
            }
        }
        if(this.nameField.getText() != null && !this.nameField.getText().trim().equals(""))
        {
            vo.setName(this.nameField.getText());
        }
        if(this.shortNameField.getText() != null && !this.shortNameField.getText().trim().equals(""))
        {
            vo.setShortName(this.shortNameField.getText());
        }
        if(this.helpCodeField.getText() != null && !this.helpCodeField.getText().trim().equals(""))
        {
            vo.setCode(this.helpCodeField.getText());
        }
        ITableModel model = this.dataTable.getTableModel();
        IEmployeeService service = Main.getServiceManager().getEmployeeService();
        this.dataSource.clear();
        List<Employee> employees = service.findEmployees(vo);
        for(Employee e : employees)
        {
            IDataRow dataRow = new ObjectDataRow(e, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
        if(employees == null || employees.size() == 0)
        {
            this.clearInfo();
        }
    }

    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,80));
		panel.setLayout(null);
        catalogField = new JDataComboBox(EmployeeCatalog.class,Main.getMainFrame().getObjectsPool().getDepartments());
        catalogField.setSelectedItem("");
        numberField = new JDataTableComboBox("", Employee.class,Main.getMainFrame().getObjectsPool().getEmployeeTable(), "number");
        nameField = new CField();
        shortNameField = new CField();
        helpCodeField = new CField();

        int x = 60;
        int y = 15;
        panel.addComponent(catalogField, x, y, 120, 20, "类别", 50);
        panel.addComponent(numberField, x + 170, y, 100, 20, "职员编号", 50);
        panel.addComponent(nameField, x + 320, y, 120, 20, "职员名称", 50);
        panel.addComponent(shortNameField, x , y + 25, 120, 20, "职员简称", 50);
        panel.addComponent(helpCodeField, x + 170, y + 25, 100, 20, "助记码", 50);
        panel.addComponent(selectButton, x + 440, y + 25, 75, 25);
        panel.addComponent(clearButton, x + 520, y + 25, 75, 25);

        return panel;
    }

    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("部门编号");
		column.setColumnName("catalog.number");//typeid
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("部门");
		column.setColumnName("catalog.name");//typeid
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("姓名");
		column.setColumnName("name");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("性别");
		column.setColumnName("sex");
		column.setWidth(40);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("手机");
		column.setColumnName("mobile");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("住址");
		column.setColumnName("address");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("邮箱");
		column.setColumnName("email");
		column.setWidth(110);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("身份证号");
		column.setColumnName("idCard");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("助记码");
		column.setColumnName("code");
		column.setWidth(60);
		columnModel.addColumn(column);
        
        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("员工信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/EmployeeManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    protected String getExcelExportTitle()
    {
        return "职员信息查询";
    }

    protected void doShowDetail()
    {
        if (this.dataTable.getSelectedRow() < 0)
        {
            return;
        }
        this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
        CEmployeeInfoDialog dialog = new CEmployeeInfoDialog(Main.getMainFrame(), this.dataSource,dbSupport);
        dialog.saveAndNewButton.setEnabled(true);
        dialog.saveButton.setEnabled(true);
        dialog.setVisible(true);
        this.dataSource.clearTempDataRows();
    }
}
