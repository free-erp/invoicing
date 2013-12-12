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

import org.free_erp.client.ui.forms.CCatalogDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ExcelImporter;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.logic.ICustomerService;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import jxl.read.biff.BiffException;

/**
 *
 * @author Afa
 */
public class CSupplierManageWindow extends CCustomerManageWindow{
    public CSupplierManageWindow(String title)
	{
		super(title);
        this.newButton.setText("新增供应商(&N)");
        this.removeButton.setText("删除供应商(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改供应商信息(&M)");
		
	}
    protected void initMenuItem()
    {
        this.newButton.setText("新增供应商(&N)");
        this.removeButton.setText("删除供应商(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改供应商信息(&M)");
    }

    protected void initDatas()
	{
		ITableModel model = this.dataTable.getTableModel();
        List<Customer> customers = Main.getMainFrame().getObjectsPool().getAllSuppliers();
		for(Customer customer:customers)
		{
			IDataRow dataRow = new ObjectDataRow(customer, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        //Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
        this.dataSource.sortById(IDataSource.ASC_SORT);
	}

    

    protected void setCustomerType()//differentiate customer and supplier
    {
        this.typeDialog.setCustomerType("2");
    }

    protected void initTypes() {
		List<CustomerCatalog> catalogs = Main.getMainFrame().getObjectsPool().getSupplierCatalogs();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        //
        MutableTreeNode root = new DefaultMutableTreeNode("供应商分类");
		this.treeModel.setRoot(root);
        //
        this.typeDialog = new CCatalogDialog(Main.getMainFrame(), this.typeDataSource, dbSupport, CustomerCatalog.class);
    }

    protected void doAdd()
	{
		this.dialog = new CSupplierDialog(this, this, this.dataSource, dbSupport);
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
                MessageBox.showErrorDialog(Main.getMainFrame(), "没有任何数据行被选中!");
                return;
            }
            this.dialog = new CSupplierDialog(this, this ,this.dataSource, dbSupport);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
            this.dataSource.clearTempDataRows();
       
    }

    protected void doImport()
	{
		Company company = Main.getMainFrame().getCompany();
        ICustomerService service = Main.getServiceManager().getCustomerService();
        int column = 0;
        int row = 0;
        List<Customer> customers = new ArrayList();
        try
        {
            JFileChooser chooser = new JFileChooser();
            //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setApproveButtonText("导入");
			chooser.setDialogTitle("选取导入文件");
            chooser.setFileFilter(new FileFilter(){
				
				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(".xls") || f.isDirectory())
					{
						return true;
					}
					return false;
				}
				
				public String getDescription() {
					return "Excel(*.xls)文件";
				}
			}
			);
            int result = chooser.showOpenDialog(Main.getMainFrame());
            String filePath = "";
            if(result == JFileChooser.APPROVE_OPTION)
            {
                filePath = chooser.getSelectedFile().getPath();
                ExcelImporter excelImporter = new ExcelImporter(filePath);
                int i = 1;
                boolean flag = excelImporter.isStop(i);

                while(flag)
                {
                    row = i;
                    column = 1;
                    String typeName = excelImporter.getCellText(0,i,"S");
                    if(typeName.length() > 32)
                    {
                        typeName = typeName.substring(0, 32);
                    }
                    column = 2;
                    String cusNumber = excelImporter.getCellText(1,i,"S");
                    if(cusNumber.length() > 32)
                    {
                        cusNumber = cusNumber.substring(0, 32);
                    }
                    column = 3;
                    String cusName = excelImporter.getCellText(2,i,"S");
                    if(cusName.length() > 32)
                    {
                        cusName = cusName.substring(0, 32);
                    }
                    column = 4;
                    String shortname = excelImporter.getCellText(3,i,"S");
                    if(shortname.length() > 32)
                    {
                        shortname = shortname.substring(0, 32);
                    }
                    column = 5;
                    String helpcode = excelImporter.getCellText(4,i,"S");
                    if(helpcode.length() > 10)
                    {
                        helpcode = helpcode.substring(0, 10);
                    }
                    column = 6;
                    String cusType = excelImporter.getCellText(5,i,"S");
                    if(cusType.length() > 20)
                    {
                        cusType = cusType.substring(0, 20);
                    }
                    column = 7;
                    String linkman = excelImporter.getCellText(6,i,"S");
                    if(linkman.length() > 10)
                    {
                        linkman = linkman.substring(0, 10);
                    }
                    column = 8;
                    String phone = excelImporter.getCellText(7,i,"S");
                    if(phone.length() > 20)
                    {
                        phone = phone.substring(0, 20);
                    }
                    column = 9;
                    String idCard = excelImporter.getCellText(8,i,"S");
                    if(idCard.length() > 18)
                    {
                        idCard = idCard.substring(0, 18);
                    }
                    column =10;
                    String email = excelImporter.getCellText(9,i,"S");
                    if(email.length() > 60)
                    {
                        email = email.substring(0, 60);
                    }
                    column = 11;
                    String qq = excelImporter.getCellText(10,i,"S");
                    if(qq.length() > 32)
                    {
                        qq = qq.substring(0, 32);
                    }
                    column = 12;
                    String website = excelImporter.getCellText(11,i,"S");
                    if(website.length() > 32)
                    {
                        website = website.substring(0, 32);
                    }
                    column = 13;
                    String address = excelImporter.getCellText(12,i,"S");
                    if(address.length() > 60)
                    {
                        address = address.substring(0, 60);
                    }
                    column = 14;
                    String MNS = excelImporter.getCellText(13,i,"S");
                    if(MNS.length() > 32)
                    {
                        MNS = MNS.substring(0, 32);
                    }
                    column = 15;
                    String telephone = excelImporter.getCellText(14,i,"S");
                    if(telephone.length() > 20)
                    {
                        telephone = telephone.substring(0, 20);
                    }
                    column = 16;
                    String fax = excelImporter.getCellText(15,i,"S");
                    if(fax.length() > 20)
                    {
                        fax = fax.substring(0, 20);
                    }
                    column = 17;
                    String postcode = excelImporter.getCellText(16,i,"S");
                    if(postcode.length() > 6)
                    {
                        postcode = postcode.substring(0, 6);
                    }
                    column = 18;
                    String comments = excelImporter.getCellText(17,i,"S");
                    if(comments.length() > 255)
                    {
                        comments = comments.substring(0, 255);
                    }

                    CustomerCatalog catalog = new CustomerCatalog();
                    catalog.setCompany(company);
                    catalog.setCustomerType("2");
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
                    customer.setCustomerType("供应商");
                    if(cusNumber != null && !cusNumber.trim().equals(""))
                    {
                        customer.setNumber(cusNumber);
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
                    //service.saveCustomer(customer);
                    customers.add(customer);
                    if(excelImporter.getCellText(0,i+1,"S").equals(""))
                    {
                        flag = false;
                    }

                    i++;
                }
                excelImporter.close();
                if(customers != null && customers.size() > 0)
                {
                    for(Customer c :customers)
                    {
                        service.saveCustomer(c);
                    }
                }
                else
                {
                    MessageBox.showMessageDialog(Main.getMainFrame(), "0 条数据导入成功！");
                    return;
                }
                Main.getMainFrame().getObjectsPool().refreshAllCustomers();
                Main.getMainFrame().getObjectsPool().refreshCustomers();
                Main.getMainFrame().getObjectsPool().refreshCustomerCatalogs();
                this.refreshDatas();
                MessageBox.showMessageDialog(Main.getMainFrame(), String.valueOf(excelImporter.rowSize - 1) + "条数据导入成功！");
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
            MessageBox.showErrorDialog(Main.getMainFrame(), "第 "+ String.valueOf(row)+" 行,第 " + String.valueOf(column) + " 列数据有误！");
            //ex.printStackTrace();
        }
	}

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("供应商信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/jzh/demo/client/report/jaspers/CustomerManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
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
		column.setWidth(160);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(160);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("客户类别");
		column.setColumnName("customerType");
		column.setWidth(80);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("客户类型");
		column.setColumnName("type");
		column.setWidth(80);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(160);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("联系人");
		column.setColumnName("contactName");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("手机");
		column.setColumnName("mobile");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("身份证号");
		column.setColumnName("idCard");
		column.setWidth(120);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("MNS");
		column.setColumnName("mns");
		column.setWidth(80);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("传真");
		column.setColumnName("fax");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("邮箱");
		column.setColumnName("email");
		column.setWidth(100);
		columnModel.addColumn(column);

//		column = new JDataTableColumn();
//		column.setHeaderText("助记码");
//		column.setColumnName("code");
//		column.setWidth(60);
//		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("邮编");
		column.setColumnName("postcode");
		column.setWidth(80);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("网址");
		column.setColumnName("website");
		column.setWidth(80);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(160);
        column.setVisible(false);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
        tempColumnModel = columnModel;
	}
   

    private ITableColumnModel tempColumnModel = null;
    /*
    protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
//		column.setHeaderText("类别编号");
//		column.setColumnName("catalog.number");
//		column.setWidth(60);
//		columnModel.addColumn(column);
//
//		column = new JDataTableColumn();
//		column.setHeaderText("类别名称");
//		column.setColumnName("catalog.name");
//		column.setWidth(80);
//		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(120);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("联系人");
		column.setColumnName("contactName");
		column.setWidth(60);
		columnModel.addColumn(column);

         column = new JDataTableColumn();
		column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("手机");
		column.setColumnName("mobile");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("传真");
		column.setColumnName("fax");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("邮箱");
		column.setColumnName("email");
		column.setWidth(100);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("开户行");
		column.setColumnName("bank");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("银行帐号");
		column.setColumnName("bankNo");
		column.setWidth(120);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("开户人");
		column.setColumnName("bankOwner");
		column.setWidth(120);
		columnModel.addColumn(column);

//		column = new JDataTableColumn();
//		column.setHeaderText("助记码");
//		column.setColumnName("code");
//		column.setWidth(60);
//		columnModel.addColumn(column);



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
        List<Customer> customers = Main.getMainFrame().getObjectsPool().getAllSuppliers();
		for(Customer customer:customers)
		{
			IDataRow dataRow = new ObjectDataRow(customer, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
        //Main.getMainFrame().getDataBaseObjectPool().refreshAllCustomers();
        this.dataSource.sortById(IDataSource.ASC_SORT);
	}

    @Override
    protected void initTypes() {
		List<CustomerCatalog> catalogs = Main.getMainFrame().getObjectsPool().getSupplierCatalogs();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        //
        MutableTreeNode root = new DefaultMutableTreeNode("供应商分类");
		this.treeModel.setRoot(root);
        //
        this.typeDialog = new CCatalogDialog(this, this.typeDataSource, dbSupport, CustomerCatalog.class);
    }
     */
}
