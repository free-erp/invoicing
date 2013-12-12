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

package org.free_erp.client.ui.forms.basic;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.forms.CCatalogDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ExcelImporter;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.logic.IEmployeeService;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import jxl.read.biff.BiffException;
/**
 *
 * @Changer liufei
 */
public class CEmployeeManageWindow extends CBaseInfoManageWindow
{
	private CEmployeeInfoDialog dialog;
	public CEmployeeManageWindow(String title)
	{
		super(title);
		this.initTableColumns();
		initDatas();
        this.newButton.setText("新增员工(&N)");
        this.removeButton.setText("删除员工(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改员工信息(&M)");
      
	}
     
	protected void initTableColumns()
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
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("姓名");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("name");
		column.setWidth(60);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("性别");
		column.setColumnName("sex");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(40);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("电话");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("手机");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("mobile");
		column.setWidth(100);
		columnModel.addColumn(column);
        
        column = new JDataTableColumn();
		column.setHeaderText("住址");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("address");
		column.setWidth(120);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("邮箱");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("email");
		column.setWidth(110);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("身份证号");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("idCard");
		column.setWidth(100);
		columnModel.addColumn(column);
        
//        column = new JDataTableColumn();
//		column.setHeaderText("助记码");
//		column.setColumnName("code");
//		column.setWidth(60);
//		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("职务");
		column.setColumnName("degree");
		column.setWidth(40);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("职称");
		column.setColumnName("duty");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("学历");
		column.setColumnName("education");
		column.setWidth(60);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("加入日期");
		column.setColumnName("joinDate");
		column.setWidth(80);
        column.setValueType(Date.class);
		columnModel.addColumn(column);



        column = new JDataTableColumn();
		column.setHeaderText("备注");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("comments");
		column.setWidth(200);
		columnModel.addColumn(column);
        }
    @Override
	protected void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
		List<Employee> employees = Main.getMainFrame().getObjectsPool().getAllEmployees();
		for(Employee employee:employees)
		{
			IDataRow dataRow = new ObjectDataRow(employee, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        //Main.getMainFrame().getDataBaseObjectPool().refreshEmployees();
        this.dataSource.sortById(IDataSource.ASC_SORT);
	}
	@Override
	protected void doAdd()
	{
		this.dialog = new CEmployeeInfoDialog(this, this, this.dataSource, dbSupport);
        if (this.dialog.newDataRow())
		{
			dialog.setVisible(true);
		}
		this.dataSource.clearTempDataRows();
	}
	@Override
	protected void doEdit()
	{
      
            if (this.dataTable.getSelectedRow() < 0)
            {
                MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
                return;
            }
			this.dialog = new CEmployeeInfoDialog(this, this, this.dataSource, dbSupport);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
//            if(this.dataTable.getSelectedDataRow().getColumnValue("birthday") == null)
//            {
//                this.dataTable.getSelectedDataRow().setColumnValue("birthday", new Date(80, new Date().getMonth(), new Date().getDate()));
//            }
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
	   
    }
    @Override
    protected void updateDatas()
    {
        Main.getMainFrame().getDataBaseObjectPool().refreshAllEmployees();
        Main.getMainFrame().getDataBaseObjectPool().refreshEmployees();
    }
    
	@Override
	protected void doImport()
	{
		Company company = Main.getMainFrame().getCompany();
        IEmployeeService service = Main.getServiceManager().getEmployeeService();
        try
        {
            JFileChooser chooser = new JFileChooser();
            //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setApproveButtonText("导入");
			chooser.setDialogTitle("选取导入文件");
            chooser.setFileFilter(new FileFilter(){
				@Override
				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(".xls") || f.isDirectory())
					{
						return true;
					}
					return false;
				}
				@Override
				public String getDescription() {
					return "Excel(*.xls)文件";
				}
			}
			);
            int result = chooser.showOpenDialog(null);
            String filePath = "";
            if(result == JFileChooser.APPROVE_OPTION)
            {
                filePath = chooser.getSelectedFile().getPath();
                ExcelImporter excelImporter = new ExcelImporter(filePath);
                int i = 1;
                boolean flag = excelImporter.isStop(i);
                
                while(flag)
                {
                    String typeName = excelImporter.getCellText(0,i,"S");
                    String empName = excelImporter.getCellText(1,i,"S");
                    String shortname = excelImporter.getCellText(2,i,"S");
                    String helpcode = excelImporter.getCellText(3,i,"S");
                    String sex = excelImporter.getCellText(4,i,"S");
                    String idCard = excelImporter.getCellText(5,i,"S");
                    String health = excelImporter.getCellText(6,i,"S");
                    String duty = excelImporter.getCellText(7,i,"S");
                    String degree = excelImporter.getCellText(8,i,"S");
                    String education = excelImporter.getCellText(9,i,"S");
                    String telephone = excelImporter.getCellText(10,i,"S");
                    String phone = excelImporter.getCellText(11,i,"S");
                    String address = excelImporter.getCellText(12,i,"S");
                    String postcode = excelImporter.getCellText(13,i,"S");
                    String email = excelImporter.getCellText(14,i,"S");
                    String bank = excelImporter.getCellText(15,i,"S");
                    String bankAccount = excelImporter.getCellText(16,i,"S");
                    String comments = excelImporter.getCellText(17,i,"S");
                    EmployeeCatalog catalog = new EmployeeCatalog();
                    catalog.setCompany(company);
                    catalog.setName(typeName);
                    catalog.setParentId(-1);
                    Employee employee = new Employee();
                    employee.setCompany(company);
                    if(service.getEmployeeCatalog(company, typeName) == null)
                    {
                        employee.setCatalog(service.saveEmployeeCatalog(catalog));
                    }
                    else
                    {
                        employee.setCatalog(service.getEmployeeCatalog(company, typeName));
                    }
                    employee.setName(empName);
                    employee.setShortName(shortname);
                    employee.setCode(helpcode);
                    employee.setSex(sex);
                    employee.setIdCard(idCard);
                    employee.setHealthStatus(health);
                    employee.setDuty(duty);
                    employee.setDegree(degree);
                    employee.setEducation(education);
                    employee.setPhone(telephone);
                    employee.setMobile(phone);
                    employee.setAddress(address);
                    employee.setPostNumber(postcode);
                    employee.setEmail(email);
                    employee.setBank(bank);
                    employee.setBankAccount(bankAccount);
                    employee.setComments(comments);
                    service.saveEmployee(employee);
                    if(excelImporter.getCellText(0,i+1,"S").equals(""))
                    {
                        flag = false;
                    }
                    i++;
                }
                excelImporter.close();
                Main.getMainFrame().getDataBaseObjectPool().refreshAllEmployees();
                Main.getMainFrame().getDataBaseObjectPool().refreshDepartments();
                this.refreshDatas();
                MessageBox.showMessageDialog(this, String.valueOf(excelImporter.rowSize - 1) + "条数据导入成功！");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CEmployeeManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BiffException ex)
        {
            Logger.getLogger(CEmployeeManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex)
        {
            MessageBox.showErrorDialog(this, "数据有误！");
        }
	}
    @Override
    protected void initTypes()
    {
		List<EmployeeCatalog> catalogs = Main.getMainFrame().getObjectsPool().getDepartments();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        MutableTreeNode root = new DefaultMutableTreeNode("部门分类");
		this.treeModel.setRoot(root);
        this.typeDialog = new CCatalogDialog(this, this.typeDataSource, dbSupport, EmployeeCatalog.class);
    }
    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("员工信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/EmployeeManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
