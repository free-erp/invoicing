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
/**
 * @author tengzhuolin
 */
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
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.ICustomerService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import jxl.read.biff.BiffException;

public class CCustomerManageWindow extends CBaseInfoManageWindow
{

	protected CCustomerDialog dialog;
	public CCustomerManageWindow(String title)
	{
		super(title);
		this.initTableColumns();
		initDatas();
        this.newButton.setText("新增客户(&N)");
        this.removeButton.setText("删除客户(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改客户信息(&M)");
	}
      
	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("类别编号");
		column.setColumnName("catalog.number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("类别名称");
		column.setColumnName("catalog.name");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("联系人");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("contactName");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("手机");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("mobile");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("电话");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("phone");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("传真");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("fax");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("邮箱");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("email");
		column.setWidth(100);
		columnModel.addColumn(column);



        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("开户行");
		column.setColumnName("bank");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("帐号");
		column.setColumnName("bankNo");
		column.setWidth(80);
		columnModel.addColumn(column);


        column = new JDataTableColumn();
		column.setHeaderText("开户人");
		column.setColumnName("bankOwner");
		column.setWidth(80);
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
        List<Customer> customers = Main.getMainFrame().getObjectsPool().getAllCustomers();
		for(Customer customer:customers)
		{
			IDataRow dataRow = new ObjectDataRow(customer, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        //Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
        this.dataSource.sortById(IDataSource.ASC_SORT);
	}

	@Override
	protected void doAdd()
	{
		this.dialog = new CCustomerDialog(this, this, this.dataSource, dbSupport);
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
            this.dialog = new CCustomerDialog(this, this ,this.dataSource, dbSupport);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
       
    }

    @Override
    protected void updateDatas()
    {
        Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
        Main.getMainFrame().getDataBaseObjectPool().refreshCustomers();
    }

	@Override
	protected void doImport()
	{
		Company company = Main.getMainFrame().getCompany();
        ICustomerService service = Main.getServiceManager().getCustomerService();
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
                    String cusName = excelImporter.getCellText(1,i,"S");
                    String shortname = excelImporter.getCellText(2,i,"S");
                    String helpcode = excelImporter.getCellText(3,i,"S");
                    String cusType = excelImporter.getCellText(4,i,"S");
                    String linkman = excelImporter.getCellText(5,i,"S");
                    String phone = excelImporter.getCellText(6,i,"S");
                    String idCard = excelImporter.getCellText(7,i,"S");
                    String email = excelImporter.getCellText(8,i,"S");
                    String qq = excelImporter.getCellText(9,i,"S");
                    String website = excelImporter.getCellText(10,i,"S");
                    String address = excelImporter.getCellText(11,i,"S");
                    String MNS = excelImporter.getCellText(12,i,"S");
                    String telephone = excelImporter.getCellText(13,i,"S");
                    String fax = excelImporter.getCellText(14,i,"S");
                    String postcode = excelImporter.getCellText(15,i,"S");
                    String comments = excelImporter.getCellText(16,i,"S");

                    CustomerCatalog catalog = new CustomerCatalog();
                    catalog.setCompany(company);
                    catalog.setName(typeName);
                    catalog.setParentId(-1);
                    Customer customer = new Customer();
                    customer.setCompany(company);
                    if(service.getCustomerCatalog(company, typeName) == null)
                    {
                        customer.setCatalog(service.saveCustomerCatalog(catalog));
                    }
                    else
                    {
                        customer.setCatalog(service.getCustomerCatalog(company, typeName));
                    }
                    customer.setName(cusName);
                    customer.setShortName(shortname);
                    customer.setCode(helpcode);
                    customer.setType(cusType);
                    customer.setContactName(linkman);
                    customer.setMobile(phone);
                    customer.setIdCard(idCard);
                    customer.setEmail(email);
                    customer.setQq(qq);
                    customer.setWebsite(website);
                    customer.setAddress(address);
                    customer.setMsn(MNS);
                    customer.setPhone(telephone);
                    customer.setFax(fax);
                    customer.setPostcode(postcode);
                    customer.setComments(comments);
                    service.saveCustomer(customer);
                    if(excelImporter.getCellText(0,i+1,"S").equals(""))
                    {
                        flag = false;
                    }

                    i++;
                }
                excelImporter.close();
                Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
                Main.getMainFrame().getDataBaseObjectPool().refreshCustomerCatalogs();
                this.refreshDatas();
                MessageBox.showMessageDialog(this, String.valueOf(excelImporter.rowSize - 1) + "条数据导入成功！");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CCustomerManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BiffException ex)
        {
            Logger.getLogger(CCustomerManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex)
        {
            MessageBox.showErrorDialog(this, "数据有误！");
        }
	}

    @Override
    protected void initTypes() {
		List<CustomerCatalog> catalogs = Main.getMainFrame().getObjectsPool().getCustomerCatalogs();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        //
        MutableTreeNode root = new DefaultMutableTreeNode("客户分类");
		this.treeModel.setRoot(root);
        //
        this.typeDialog = new CCatalogDialog(this, this.typeDataSource, dbSupport, CustomerCatalog.class);
    }

    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("客户信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/CustomerManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
