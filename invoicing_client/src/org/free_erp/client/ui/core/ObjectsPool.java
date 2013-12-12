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

package org.free_erp.client.ui.core;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.system.CustomerInvoice;
import org.free_erp.service.entity.system.CustomerPrice;
import org.free_erp.service.entity.system.PriceName;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author afa
 * @Changer liufei
 */

public class ObjectsPool {
	private List<Product> products;
    private List<Product> usableProducts;//liufei
	private List<Customer> customers;
    private List<Customer> suppliers;
	private List<Employee> employees;
    private List<String> stremployees;//
	private List<Storage> storages;
	private List<Customer> allCustomers;//liufei
    private List<Customer> allSuppliers;
	private List<Employee> allEmployees;//liufei
	private List<Storage> allStorages;//liufei
    private List<CustomerCatalog> customerCatalogs;//liufei
    private List<CustomerCatalog> supplierCatalogs;//liufei
	private List<EmployeeCatalog> departments;
    private List<ProductCatalog> productCatalogs;
    private List<String> productUnits;//liufei
    private List<String> productShelfs;//liufei
    private List<String> productTaxrates;//liufei
    private List<String> customerTypes;//liufei
    private List<CustomerPrice> customerPrices;//liufei
    private List<CustomerInvoice> customerInvoices;//liufei
    private List<String> employeeDutys;//liufei
    private List<String> employeeDegrees;//liufei
    private List<String> employeeLevels;//liufei
    private List<String> employeeHealths;//liufei
    private List<String> storageTypes;//liufei
    private List<Subject> subjects;//liufei
    private PriceName priceName;

    private boolean priceNameChanged = true;
	private boolean productChanged = true;
    private boolean usableProductChanged = true;//liufei
	private boolean employeeChanged = true;
    private boolean stremployeeChanged = true;//test
	private boolean customerChanged = true;
    private boolean supplierChanged = true;
	private boolean storageChanged = true;
    private boolean storageOutChanged = true;
	private boolean allEmployeeChanged = true;//liufei
	private boolean allCustomerChanged = true;//liufei
    private boolean allSupplierChanged = true;
	private boolean allStorageChanged = true;//liufei
    private boolean customerCatalogChanged =true;//liufei
    private boolean supplierCatalogChanged =true;//liufei
	private boolean departmentChanged = true;
    private boolean productCatalogChanged =true;
    private boolean productUnitChanged =true;//liufei
    private boolean productShelfChanged =true;//liufei
    private boolean productTaxrateChanged =true;//liufei
    private boolean customerTypeChanged =true;//liufei
    private boolean customerPriceChanged =true;//liufei
    private boolean customerInvoiceChanged =true;//liufei
    private boolean employeeDutyChanged =true;//liufei
    private boolean employeeDegreeChanged =true;//liufei
    private boolean employeeLevelChanged =true;//liufei
    private boolean employeeHealthChanged =true;//liufei
    private boolean storageTypeChanged =true;//liufei
    private boolean subjectChanged = true;//liufei
	private Company company;


    private JDataTable productTable;
    private JDataTable employeeTable;
    private JDataTable storageTable;
    private JDataTable allStorageTable;
    private JDataTable storageOutTable;
    private JDataTable customerTable;

    private JDataTable affordTypeTable;
    private JDataTable clearingTypeTable;

    private List<AffordType> affordTypes;
    private List<ClearingType> clearingTypes;

    private boolean affordTypeChanged = true;;
    private boolean clearingTypeChanged = true;;

    private List<Subject> bankSubjects;
    private List<Subject> bussinessExpenseSubjects;
    private List<Subject> commonExpenseSubjects;
    private List<Subject> otherIncomeSubjects;


    private JDataTable bussinessExpenseSubjectTable;
    private JDataTable commonExpenseSubjectTable;
    private JDataTable otherIncomeSubjectTable;
    private JDataTable bankSubjectTable;

    private boolean bankSubjectTypeChanged = true;
    private boolean commonExpenseTypeChanged = true;
    private boolean otherIncomeTypeChanged = true;
    private boolean bussinessExpenseTypeChanged = true;



	public ObjectsPool(Company company)
	{
		this.company = company;
	}

    public PriceName getPriceName()
    {
        if(this.priceNameChanged)
        {
             priceName = Main.getServiceManager().getSystemManageService().getPriceName(company);
        }
        return priceName;
    }

	public List<Product> getProducts()
	{

		if (this.productChanged)
		{
			products = Main.getServiceManager().getProductService().getProducts(company);
			this.productChanged = false;
		}
		return products;
	}

    public List<Subject> getBankSubjects()
    {
        if (this.bankSubjectTypeChanged)
		{
			this.bankSubjects = Main.getServiceManager().getAccountingService().getBankCashSubject(company);
			this.bankSubjectTypeChanged = false;
            if (this.bankSubjectTable != null)
            {
                this.bankSubjectTable.getDataSource().clear();
            }
		}
		return this.bankSubjects;
    }

    public JDataTable getClonedBankSubjectTable()
    {
        JDataTable subjectTable = new JDataTable();
            ITableColumnModel columnModel = subjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        IDataSource dataSource = (IDataSource)subjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBankSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return subjectTable;
    }

    public JDataTable getBankSubjectTable()
    {
        if (this.bankSubjectTable == null || this.bankSubjectTypeChanged)
        {
            this.bankSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.bankSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.bankSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBankSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return bankSubjectTable;
    }

    public List<Product> getUsableProducts()
	{
		if (this.usableProductChanged)
		{
			usableProducts = Main.getServiceManager().getProductService().getUsableProducts(company);
			this.usableProductChanged = false;
            if (this.productTable != null)
            {
                this.productTable.getDataSource().clear();
            }
		}
		return usableProducts;
	}

    public JDataTable getAffordTypeTable() {
        if (this.affordTypeTable == null)
        {
            this.affordTypeTable = new JDataTable();
            ITableColumnModel columnModel = this.affordTypeTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
            column.setHeaderText("名称");
            column.setColumnName("name");
            column.setWidth(360);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        }
        IDataSource dataSource = (IDataSource)this.affordTypeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<AffordType> types = this.getAffordTypes();
            for(AffordType type:types)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(type, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return affordTypeTable;
    }

    public List<AffordType> getAffordTypes() {
        if (this.affordTypeChanged)
		{
			this.affordTypes = Main.getServiceManager().getAccountingService().getAllAffordType(company);
			this.affordTypeChanged = false;
            if (this.affordTypeTable != null)
            {
                this.affordTypeTable.getDataSource().clear();
            }
		}
		return affordTypes;
    }

    public JDataTable getClearingTypeTable() {
        if (this.clearingTypeTable == null)
        {
            this.clearingTypeTable = new JDataTable();
            ITableColumnModel columnModel = this.clearingTypeTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
            column.setHeaderText("名称");
            column.setColumnName("name");
            column.setWidth(360);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        }
        IDataSource dataSource = (IDataSource)this.clearingTypeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<ClearingType> types = this.getClearingTypes();
            for(ClearingType type:types)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(type, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return clearingTypeTable;
    }

    public List<ClearingType> getClearingTypes() {
        if (this.clearingTypeChanged)
		{
			this.clearingTypes = Main.getServiceManager().getAccountingService().getAllClearingType();
			this.clearingTypeChanged = false;
            if (this.clearingTypeTable != null)
            {
                this.clearingTypeTable.getDataSource().clear();
            }
		}
        return clearingTypes;
    }

    public JDataTable getBussinessExpenseSubjectTable() {

        if (this.bussinessExpenseSubjectTable == null || this.bussinessExpenseTypeChanged)
        {
            this.bussinessExpenseSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.bussinessExpenseSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.bussinessExpenseSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBussinessExpenseSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return bussinessExpenseSubjectTable;
    }

    public List<Subject> getBussinessExpenseSubjects() {
        if (this.bussinessExpenseTypeChanged)
		{
			this.bussinessExpenseSubjects = Main.getServiceManager().getAccountingService().getBussinessExpenseSubject(company);
			this.bussinessExpenseTypeChanged = false;
            if (this.bussinessExpenseSubjectTable != null)
            {
                this.bussinessExpenseSubjectTable.getDataSource().clear();
            }
		}
		return bussinessExpenseSubjects;
    }

    public JDataTable getCommonExpenseSubjectTable() {

        if (this.commonExpenseSubjectTable == null || this.commonExpenseTypeChanged)
        {
            this.commonExpenseSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.commonExpenseSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.commonExpenseSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getCommonExpenseSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return commonExpenseSubjectTable;
    }

    public List<Subject> getCommonExpenseSubjects() {
        if (this.commonExpenseTypeChanged)
		{
			this.commonExpenseSubjects = Main.getServiceManager().getAccountingService().getCommonExpenseSubject(company);
			this.commonExpenseTypeChanged = false;
            if (this.commonExpenseSubjectTable != null)
            {
                this.commonExpenseSubjectTable.getDataSource().clear();
            }
		}
        return commonExpenseSubjects;
    }

    public JDataTable getOtherIncomeSubjectTable() {

        if (this.otherIncomeSubjectTable == null || this.otherIncomeTypeChanged)
        {
            this.otherIncomeSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.otherIncomeSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.otherIncomeSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getOtherIncomeSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return otherIncomeSubjectTable;
    }

    public List<Subject> getOtherIncomeSubjects() {
        if (this.otherIncomeTypeChanged)
		{
			this.otherIncomeSubjects = Main.getServiceManager().getAccountingService().getOtherIncomeSubject(company);
			this.otherIncomeTypeChanged = false;
            if (this.otherIncomeSubjectTable != null)
            {
                this.otherIncomeSubjectTable.getDataSource().clear();
            }
		}
        return otherIncomeSubjects;
    }

    public List<Employee> getAllEmployees()
	{
		if (this.allEmployeeChanged)
		{
			this.allEmployees = Main.getServiceManager().getEmployeeService().getEmployees(company);
			this.allEmployeeChanged = false;
		}
		return this.allEmployees;
	}

    public JDataTable getCustomerTable() {
//        if (this.customerTable == null || this.customerChanged)
//        {
            this.customerTable = new JDataTable();
            ITableColumnModel columnModel = this.customerTable.getTableColumnModel();
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
            column.setHeaderText("地址");
            column.setColumnName("address");
            column.setWidth(120);
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
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("电话");
            column.setColumnName("phone");
            column.setWidth(80);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("传真");
            column.setColumnName("fax");
            column.setWidth(80);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("邮箱");
            column.setColumnName("email");
            column.setWidth(100);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.customerTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Customer> customers = this.getCustomers();
            for(Customer customer:customers)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(customer, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return customerTable;
    }

    public JDataTable getSupplierTable() {
//        if (this.customerTable == null || this.customerChanged)
//        {
            this.customerTable = new JDataTable();
            ITableColumnModel columnModel = this.customerTable.getTableColumnModel();
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

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.customerTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Customer> customers = this.getSuppliers();
            for(Customer customer:customers)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(customer, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return customerTable;
    }

    public JDataTable getEmployeeTable() {
//        if (this.employeeTable == null || this.employeeChanged)
//        {
            this.employeeTable = new JDataTable();
            ITableColumnModel columnModel = this.employeeTable.getTableColumnModel();
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

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.employeeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Employee> emps = this.getEmployees();
            for(Employee employee:emps)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(employee, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return employeeTable;
    }



    public JDataTable getProductsTable() {
         if (this.productTable == null || this.usableProductChanged)
        {
            this.productTable = new JDataTable();
            ITableColumnModel columnModel = this.productTable.getTableColumnModel();
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
            column.setWidth(140);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("规格");
            column.setColumnName("spec");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("采购价");
            column.setColumnName("stockPrice");
            column.setValueType(Currency.class);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("零售价");
            column.setColumnName("retailPrice");
            column.setValueType(Currency.class);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("单位");
            column.setColumnName("smallUnit");
            column.setWidth(40);

            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("生产厂家");
            column.setColumnName("factory");
            column.setWidth(120);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("产地");
            column.setColumnName("area");
            column.setWidth(40);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(50);
//            columnModel.addColumn(column);
        }
        IDataSource dataSource = (IDataSource)this.productTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Product> prods = this.getUsableProducts();
            for(Product product:prods)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(product, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return productTable;
    }



    public JDataTable getStorageTable() {
//         if (this.storageTable == null || this.storageChanged)
//        {
            this.storageTable = new JDataTable();
            ITableColumnModel columnModel = this.storageTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);

		/*column = new JDataTableColumn();
		column.setHeaderText("助记符");
		column.setColumnName("code");
		column.setWidth(50);
		columnModel.addColumn(column);*/

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.storageTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getStorages();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return storageTable;
    }

    public JDataTable getAllStorageTable() {
//         if (this.allStorageTable == null || this.storageChanged)
//        {
            this.allStorageTable = new JDataTable();
            ITableColumnModel columnModel = this.allStorageTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);

		/*column = new JDataTableColumn();
		column.setHeaderText("助记符");
		column.setColumnName("code");
		column.setWidth(50);
		columnModel.addColumn(column);*/

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.allStorageTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getAllStorages();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return allStorageTable;
    }

    public JDataTable getStorageOutTable() {
         if (this.storageOutTable == null || this.storageOutChanged)
        {
            this.storageOutTable = new JDataTable();
            ITableColumnModel columnModel = this.storageOutTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);

		/*column = new JDataTableColumn();
		column.setHeaderText("助记符");
		column.setColumnName("code");
		column.setWidth(50);
		columnModel.addColumn(column);*/

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
        }
        IDataSource dataSource = (IDataSource)this.storageOutTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getStoragesOut();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return storageOutTable;
    }

	public List<Employee> getEmployees()
	{
		if (this.employeeChanged)
		{
			this.employees = Main.getServiceManager().getEmployeeService().getUsableEmployees(company);
			this.employeeChanged = false;
            if (this.employeeTable != null)
            {
                this.employeeTable.getDataSource().clear();
            }
		}
		return this.employees;
	}

    public List<String> getStringEmployees()
	{
		if (this.stremployeeChanged)
		{
			this.stremployees = Main.getServiceManager().getEmployeeService().getStringUsableEmployees(company);
			this.stremployeeChanged = false;
            if (this.employeeTable != null)
            {
                this.employeeTable.getDataSource().clear();
            }
		}
		return this.stremployees;
	}

	public List<EmployeeCatalog> getDepartments()
	{
		if (this.departmentChanged)
		{
			this.departments = Main.getServiceManager().getEmployeeService().getCatalogs(company);
			this.departmentChanged = false;
		}
		return this.departments;
	}

    public List<ProductCatalog> getProductCatalogs()
	{
		if (this.productCatalogChanged)
		{
			this.productCatalogs = Main.getServiceManager().getProductService().getCatalogs(company);
			this.productCatalogChanged = false;
		}
		return this.productCatalogs;
	}

    public List<CustomerCatalog> getCustomerCatalogs()
	{
		if (this.customerCatalogChanged)
		{
            this.customerCatalogs = new ArrayList();
            List<CustomerCatalog> cusCatalogs = Main.getServiceManager().getCustomerService().getCatalogs(company);
            for(CustomerCatalog cc : cusCatalogs)
            {
                if(cc.getCustomerType() == null || cc.getCustomerType().equals("1"))
                {
                    this.customerCatalogs.add(cc);
                }
            }
			this.customerCatalogChanged = false;
		}
		return this.customerCatalogs;
	}

    public List<CustomerCatalog> getSupplierCatalogs()
	{
		if (this.supplierCatalogChanged)
		{
            this.supplierCatalogs = new ArrayList();
            List<CustomerCatalog> cusCatalogs = Main.getServiceManager().getCustomerService().getCatalogs(company);
            for(CustomerCatalog cc : cusCatalogs)
            {
                if(cc.getCustomerType() == null || cc.getCustomerType().equals("2"))
                {
                    this.supplierCatalogs.add(cc);
                }
            }
			this.supplierCatalogChanged = false;
		}
		return this.supplierCatalogs;
	}

    public List<Customer> getAllCustomers()
	{
		if (this.allCustomerChanged)
		{
            this.allCustomers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("供应商"))
                {
                    this.allCustomers.add(c);
                }
            }
			this.allCustomerChanged = false;

		}
		return this.allCustomers;
	}

    public List<Customer> getAllSuppliers()
	{
		if (this.allSupplierChanged)
		{
            this.allSuppliers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                String type = c.getCustomerType();
                if (type == null)
                {
                    continue;
                }
                if(type.equals("供应商") || type.equals("客户及供应商"))
                {
                    this.allSuppliers.add(c);
                }
            }
			this.allSupplierChanged = false;
		}
		return this.allSuppliers;
	}

	public List<Customer> getCustomers()
	{
		if (this.customerChanged)
		{
            this.customers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("供应商"))
                {
                    this.customers.add(c);
                }
            }
			this.customerChanged = false;
            if (this.customerTable != null)
            {
                this.customerTable.getDataSource().clear();
            }
		}
		return this.customers;
	}

    public List<Customer> getSuppliers()
	{
		if (this.supplierChanged)
		{
            this.suppliers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("客户"))
                {
                    this.suppliers.add(c);
                }
            }
			this.supplierChanged = false;
            if (this.customerTable != null)
            {
                this.customerTable.getDataSource().clear();
            }
		}
		return this.suppliers;
	}

    public List<Storage> getAllStorages()
	{
		if (this.allStorageChanged)
		{
			this.allStorages = Main.getServiceManager().getStorageService().getStorages(company);
			this.allStorageChanged = false;
		}
		return this.allStorages;
	}

	public List<Storage> getStorages()
	{
		if (this.storageChanged)
		{
			this.storages = Main.getServiceManager().getStorageService().getUsableStorages(company);
			this.storageChanged = false;
            if (this.storageTable != null)
            {
                this.storageTable.getDataSource().clear();
            }
		}
		return this.storages;
	}

    public List<Storage> getStoragesOut()
	{
		if (this.storageOutChanged)
		{
			this.storages = Main.getServiceManager().getStorageService().getUsableStorages(company);
			this.storageOutChanged = false;
            if (this.storageOutTable != null)
            {
                this.storageOutTable.getDataSource().clear();
            }
		}
		return this.storages;
	}

    public List<Subject> getSubjects()
    {
        if(this.subjectChanged)
        {
            this.subjects = Main.getServiceManager().getAccountingService().getAllSubject(company);
            this.subjectChanged = false;
        }
        return this.subjects;
    }

    public List<String> getProductUnits()
    {
        if(this.productUnitChanged)
        {
            this.productUnits = Main.getServiceManager().getOptionSetService().getProductUnits(company);
            this.productUnitChanged = false;
        }
        return this.productUnits;
    }

    public List<String> getProductShelfs()
    {
        if(this.productShelfChanged)
        {
            this.productShelfs = Main.getServiceManager().getOptionSetService().getProductShelfs(company);
            this.productShelfChanged = false;
        }
        return this.productShelfs;
    }

    public List<String> getProductTaxrates()
    {
        if(this.productTaxrateChanged)
        {
            this.productTaxrates = Main.getServiceManager().getOptionSetService().getProductTaxrates(company);
            this.productTaxrateChanged = false;
        }
        return this.productTaxrates;
    }

    public List<String> getCustomerTypes()
    {
        if(this.customerTypeChanged)
        {
            this.customerTypes = Main.getServiceManager().getOptionSetService().getCustomerTypes(company);
            this.customerTypeChanged = false;
        }
        return this.customerTypes;
    }

    public List<CustomerPrice> getCustomerPrices()
    {
        if(this.customerPriceChanged)
        {
            this.customerPrices = Main.getServiceManager().getOptionSetService().getCustomerPrices(company);
        }
        return this.customerPrices;
    }

    public List<CustomerInvoice> getCustomerInvoices()
    {
        if(this.customerInvoiceChanged)
        {
            this.customerInvoices = Main.getServiceManager().getOptionSetService().getCustomerInvoices(company);
        }
        return this.customerInvoices;
    }

    public List<String> getEmployeeDutys()
    {
        if(this.employeeDutyChanged)
        {
            this.employeeDutys = Main.getServiceManager().getOptionSetService().getEmployeeDutys(company);
            this.employeeDutyChanged = false;
        }
        return this.employeeDutys;
    }

    public List<String> getEmployeeDegrees()
    {
        if(this.employeeDegreeChanged)
        {
            this.employeeDegrees = Main.getServiceManager().getOptionSetService().getEmployeeDegrees(company);
            this.employeeDegreeChanged = false;
        }
        return this.employeeDegrees;
    }

    public List<String> getEmployeeLevels()
    {
        if(this.employeeLevelChanged)
        {
            this.employeeLevels = Main.getServiceManager().getOptionSetService().getEmployeeLevels(company);
            this.employeeLevelChanged = false;
        }
        return this.employeeLevels;
    }

    public List<String> getEmployeeHealths()
    {
        if(this.employeeHealthChanged)
        {
            this.employeeHealths = Main.getServiceManager().getOptionSetService().getEmployeeHealths(company);
            this.employeeHealthChanged = false;
        }
        return this.employeeHealths;
    }

    public List<String> getStorageTypes()
    {
        if(this.storageTypeChanged)
        {
            this.storageTypes = Main.getServiceManager().getOptionSetService().getStorageTypes(company);
            this.storageTypeChanged = false;
        }
        return this.storageTypes;
    }

    public List<String> getSexs()
    {
        List<String> list = new ArrayList();
        list.add("男");
        list.add("女");
        return list;
    }

    public List<String> getCustomerType()
    {
        List<String> list = new ArrayList();
        list.add("客户");
        list.add("客户及供应商");
        return list;
    }

    public List<String> getSupplierType()
    {
        List<String> list = new ArrayList();
        list.add("供应商");
        list.add("客户及供应商");
        return list;
    }

	public void refreshProducts()
	{
		this.productChanged = true;
	}

    public void refreshUsableProducts()
	{
		this.usableProductChanged = true;
	}

	public void refreshCustomers()
	{
		this.customerChanged = true;
        this.supplierChanged = true;
	}

	public void refreshEmployees()
	{
		this.employeeChanged = true;
        this.stremployeeChanged = true;
	}
	public void refreshStorages()
	{
		this.storageChanged = true;
	}

    public void refreshStoragesOut()
	{
		this.storageOutChanged = true;
	}

	public void refreshAllCustomers()
	{
		this.allCustomerChanged = true;
        this.allSupplierChanged = true;
	}

	public void refreshAllEmployees()
	{
		this.allEmployeeChanged = true;
	}
	public void refreshAllStorages()
	{
		this.allStorageChanged = true;
	}

    public void refreshProductCatalogs()
	{
		this.productCatalogChanged = true;
	}

    public void refreshCustomerCatalogs()
	{
		this.customerCatalogChanged = true;
        this.supplierCatalogChanged = true;
	}

	public void refreshDepartments()
	{
		this.departmentChanged = true;
	}

	public void refreshProductUnits()
	{
		this.productUnitChanged = true;
	}

    public void refreshProductShelfs()
	{
		this.productShelfChanged = true;
	}


    public void refreshProductTaxrates()
	{
		this.productTaxrateChanged = true;
	}

    public void refreshCustomerTypes()
	{
		this.customerTypeChanged = true;
	}

    public void refreshCustomerPrices()
	{
		this.customerPriceChanged = true;
	}

    public void refreshCustomerInvoices()
	{
		this.customerInvoiceChanged = true;
	}

    public void refreshEmployeeDutys()
	{
		this.employeeDutyChanged = true;
	}

    public void refreshEmployeeDegrees()
	{
		this.employeeDegreeChanged = true;
	}

    public void refreshEmployeeLevels()
	{
		this.employeeLevelChanged = true;
	}

    public void refreshEmployeeHealths()
	{
		this.employeeHealthChanged = true;
	}

    public void refreshStorageTypes()
	{
		this.storageTypeChanged = true;
	}

    public void refreshSubjects()
	{
		this.subjectChanged = true;
	}

    public void refreshBankSubjectType()
	{
		this.bankSubjectTypeChanged = true;
	}

    public void refreshCommonExpenseType()
	{
		this.commonExpenseTypeChanged = true;
	}

    public void refreshOtherIncomeType()
	{
		this.otherIncomeTypeChanged = true;
	}

    public void refreshBussinessExpenseType()
	{
		this.bussinessExpenseTypeChanged = true;
	}

    public void refreshPriceName()
	{
		this.priceNameChanged = true;
	}

    public void dispose()
    {
        if (this.allCustomers != null)
        {
           this.allCustomers.clear();
        }
        if (this.allEmployees != null)
        {
            this.allEmployees.clear();
        }
        if (this.allStorages != null)
        {
            this.allStorages.clear();
        }
        if (this.customerCatalogs != null)
        {
            this.customerCatalogs.clear();
        }
        if (this.customers != null)
        {
            this.customers.clear();
        }
        if (this.employees != null)
        {
            this.employees.clear();
        }
        if (this.products != null)
        {
            this.products.clear();
        }
        if(this.subjects != null)
        {
            this.subjects.clear();
        }
    }

}
/*
public class ObjectsPool {
	private List<Product> products;
    private List<Product> usableProducts;//liufei
	private List<Customer> customers;
    private List<Customer> suppliers;
	private List<Employee> employees;
    private List<String> stremployees;//
	private List<Storage> storages;
	private List<Customer> allCustomers;//liufei
    private List<Customer> allSuppliers;
	private List<Employee> allEmployees;//liufei
	private List<Storage> allStorages;//liufei
    private List<CustomerCatalog> customerCatalogs;//liufei
    private List<CustomerCatalog> supplierCatalogs;//liufei
	private List<EmployeeCatalog> departments;
    private List<ProductCatalog> productCatalogs;
    private List<String> productUnits;//liufei
    private List<String> productShelfs;//liufei
    private List<String> productTaxrates;//liufei
    private List<String> customerTypes;//liufei
    private List<CustomerPrice> customerPrices;//liufei
    private List<CustomerInvoice> customerInvoices;//liufei
    private List<String> employeeDutys;//liufei
    private List<String> employeeDegrees;//liufei
    private List<String> employeeLevels;//liufei
    private List<String> employeeHealths;//liufei
    private List<String> storageTypes;//liufei
    private List<Subject> subjects;//liufei
    private PriceName priceName;

    private boolean priceNameChanged = true;
	private boolean productChanged = true;
    private boolean usableProductChanged = true;//liufei
	private boolean employeeChanged = true;
    private boolean stremployeeChanged = true;//test
	private boolean customerChanged = true;
    private boolean supplierChanged = true;
	private boolean storageChanged = true;
    private boolean storageOutChanged = true;
	private boolean allEmployeeChanged = true;//liufei
	private boolean allCustomerChanged = true;//liufei
    private boolean allSupplierChanged = true;
	private boolean allStorageChanged = true;//liufei
    private boolean customerCatalogChanged =true;//liufei
    private boolean supplierCatalogChanged =true;//liufei
	private boolean departmentChanged = true;
    private boolean productCatalogChanged =true;
    private boolean productUnitChanged =true;//liufei
    private boolean productShelfChanged =true;//liufei
    private boolean productTaxrateChanged =true;//liufei
    private boolean customerTypeChanged =true;//liufei
    private boolean customerPriceChanged =true;//liufei
    private boolean customerInvoiceChanged =true;//liufei
    private boolean employeeDutyChanged =true;//liufei
    private boolean employeeDegreeChanged =true;//liufei
    private boolean employeeLevelChanged =true;//liufei
    private boolean employeeHealthChanged =true;//liufei
    private boolean storageTypeChanged =true;//liufei
    private boolean subjectChanged = true;//liufei
	private Company company;


    private JDataTable productTable;
    private JDataTable employeeTable;
    private JDataTable storageTable;
    private JDataTable allStorageTable;
    private JDataTable storageOutTable;
    private JDataTable customerTable;

    private JDataTable affordTypeTable;
    private JDataTable clearingTypeTable;

    private List<AffordType> affordTypes;
    private List<ClearingType> clearingTypes;

    private boolean affordTypeChanged = true;;
    private boolean clearingTypeChanged = true;;

    private List<Subject> bankSubjects;
    private List<Subject> bussinessExpenseSubjects;
    private List<Subject> commonExpenseSubjects;
    private List<Subject> otherIncomeSubjects;


    private JDataTable bussinessExpenseSubjectTable;
    private JDataTable commonExpenseSubjectTable;
    private JDataTable otherIncomeSubjectTable;
    private JDataTable bankSubjectTable;

    private boolean bankSubjectTypeChanged = true;
    private boolean commonExpenseTypeChanged = true;
    private boolean otherIncomeTypeChanged = true;
    private boolean bussinessExpenseTypeChanged = true;



	public ObjectsPool(Company company)
	{
		this.company = company;
	}

    public PriceName getPriceName()
    {
        if(this.priceNameChanged)
        {
             priceName = Main.getServiceManager().getSystemManageService().getPriceName(company);
        }
        return priceName;
    }

	public List<Product> getProducts()
	{

		if (this.productChanged)
		{
			products = Main.getServiceManager().getProductService().getProducts(company);
			this.productChanged = false;
		}
		return products;
	}

    public List<Subject> getBankSubjects()
    {
        if (this.bankSubjectTypeChanged)
		{
			this.bankSubjects = Main.getServiceManager().getAccountingService().getBankCashSubject(company);
			this.bankSubjectTypeChanged = false;
            if (this.bankSubjectTable != null)
            {
                this.bankSubjectTable.getDataSource().clear();
            }
		}
		return this.bankSubjects;
    }

    public JDataTable getClonedBankSubjectTable()
    {
        JDataTable subjectTable = new JDataTable();
            ITableColumnModel columnModel = subjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        IDataSource dataSource = (IDataSource)subjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBankSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return subjectTable;
    }

    public JDataTable getBankSubjectTable()
    {
        if (this.bankSubjectTable == null || this.bankSubjectTypeChanged)
        {
            this.bankSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.bankSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.bankSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBankSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return bankSubjectTable;
    }

    public List<Product> getUsableProducts()
	{
		if (this.usableProductChanged)
		{
			usableProducts = Main.getServiceManager().getProductService().getUsableProducts(company);
			this.usableProductChanged = false;
            if (this.productTable != null)
            {
                this.productTable.getDataSource().clear();
            }
		}
		return usableProducts;
	}

    public JDataTable getAffordTypeTable() {
        if (this.affordTypeTable == null)
        {
            this.affordTypeTable = new JDataTable();
            ITableColumnModel columnModel = this.affordTypeTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
            column.setHeaderText("名称");
            column.setColumnName("name");
            column.setWidth(360);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        }
        IDataSource dataSource = (IDataSource)this.affordTypeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<AffordType> types = this.getAffordTypes();
            for(AffordType type:types)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(type, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return affordTypeTable;
    }

    public List<AffordType> getAffordTypes() {
        if (this.affordTypeChanged)
		{
			this.affordTypes = Main.getServiceManager().getAccountingService().getAllAffordType(company);
			this.affordTypeChanged = false;
            if (this.affordTypeTable != null)
            {
                this.affordTypeTable.getDataSource().clear();
            }
		}
		return affordTypes;
    }

    public JDataTable getClearingTypeTable() {
        if (this.clearingTypeTable == null)
        {
            this.clearingTypeTable = new JDataTable();
            ITableColumnModel columnModel = this.clearingTypeTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
            column.setHeaderText("名称");
            column.setColumnName("name");
            column.setWidth(360);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

        }
        IDataSource dataSource = (IDataSource)this.clearingTypeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<ClearingType> types = this.getClearingTypes();
            for(ClearingType type:types)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(type, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return clearingTypeTable;
    }

    public List<ClearingType> getClearingTypes() {
        if (this.clearingTypeChanged)
		{
			this.clearingTypes = Main.getServiceManager().getAccountingService().getAllClearingType();
			this.clearingTypeChanged = false;
            if (this.clearingTypeTable != null)
            {
                this.clearingTypeTable.getDataSource().clear();
            }
		}
        return clearingTypes;
    }

    public JDataTable getBussinessExpenseSubjectTable() {

        if (this.bussinessExpenseSubjectTable == null || this.bussinessExpenseTypeChanged)
        {
            this.bussinessExpenseSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.bussinessExpenseSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.bussinessExpenseSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getBussinessExpenseSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return bussinessExpenseSubjectTable;
    }

    public List<Subject> getBussinessExpenseSubjects() {
        if (this.bussinessExpenseTypeChanged)
		{
			this.bussinessExpenseSubjects = Main.getServiceManager().getAccountingService().getBussinessExpenseSubject(company);
			this.bussinessExpenseTypeChanged = false;
            if (this.bussinessExpenseSubjectTable != null)
            {
                this.bussinessExpenseSubjectTable.getDataSource().clear();
            }
		}
		return bussinessExpenseSubjects;
    }

    public JDataTable getCommonExpenseSubjectTable() {

        if (this.commonExpenseSubjectTable == null || this.commonExpenseTypeChanged)
        {
            this.commonExpenseSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.commonExpenseSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.commonExpenseSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getCommonExpenseSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return commonExpenseSubjectTable;
    }

    public List<Subject> getCommonExpenseSubjects() {
        if (this.commonExpenseTypeChanged)
		{
			this.commonExpenseSubjects = Main.getServiceManager().getAccountingService().getCommonExpenseSubject(company);
			this.commonExpenseTypeChanged = false;
            if (this.commonExpenseSubjectTable != null)
            {
                this.commonExpenseSubjectTable.getDataSource().clear();
            }
		}
        return commonExpenseSubjects;
    }

    public JDataTable getOtherIncomeSubjectTable() {

        if (this.otherIncomeSubjectTable == null || this.otherIncomeTypeChanged)
        {
            this.otherIncomeSubjectTable = new JDataTable();
            ITableColumnModel columnModel = this.otherIncomeSubjectTable.getTableColumnModel();
            JDataTableColumn column = new JDataTableColumn();
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
            column.setWidth(120);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("借贷");
            column.setColumnName("debitCredit");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("财务科目类别");
            column.setColumnName("mainSubjectCatalog.name");
            column.setWidth(80);
            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("全称");
            column.setColumnName("fullName");
            column.setWidth(180);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);


        }
        IDataSource dataSource = (IDataSource)this.otherIncomeSubjectTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Subject> subjects = this.getOtherIncomeSubjects();
            for(Subject subject:subjects)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return otherIncomeSubjectTable;
    }

    public List<Subject> getOtherIncomeSubjects() {
        if (this.otherIncomeTypeChanged)
		{
			this.otherIncomeSubjects = Main.getServiceManager().getAccountingService().getOtherIncomeSubject(company);
			this.otherIncomeTypeChanged = false;
            if (this.otherIncomeSubjectTable != null)
            {
                this.otherIncomeSubjectTable.getDataSource().clear();
            }
		}
        return otherIncomeSubjects;
    }

    public List<Employee> getAllEmployees()
	{
		if (this.allEmployeeChanged)
		{
			this.allEmployees = Main.getServiceManager().getEmployeeService().getEmployees(company);
			this.allEmployeeChanged = false;
		}
		return this.allEmployees;
	}

    public JDataTable getCustomerTable() {
//        if (this.customerTable == null || this.customerChanged)
//        {
            this.customerTable = new JDataTable();
            ITableColumnModel columnModel = this.customerTable.getTableColumnModel();
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
            column.setHeaderText("地址");
            column.setColumnName("address");
            column.setWidth(120);
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
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("电话");
            column.setColumnName("phone");
            column.setWidth(80);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("传真");
            column.setColumnName("fax");
            column.setWidth(80);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("邮箱");
            column.setColumnName("email");
            column.setWidth(100);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.customerTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Customer> customers = this.getCustomers();
            for(Customer customer:customers)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(customer, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return customerTable;
    }

    public JDataTable getSupplierTable() {
//        if (this.customerTable == null || this.customerChanged)
//        {
            this.customerTable = new JDataTable();
            ITableColumnModel columnModel = this.customerTable.getTableColumnModel();
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

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.customerTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Customer> customers = this.getSuppliers();
            for(Customer customer:customers)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(customer, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return customerTable;
    }

    public JDataTable getEmployeeTable() {
//        if (this.employeeTable == null || this.employeeChanged)
//        {
            this.employeeTable = new JDataTable();
            ITableColumnModel columnModel = this.employeeTable.getTableColumnModel();
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

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(60);
//            columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.employeeTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Employee> emps = this.getEmployees();
            for(Employee employee:emps)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(employee, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return employeeTable;
    }



    public JDataTable getProductsTable() {
         if (this.productTable == null || this.usableProductChanged)
        {
            this.productTable = new JDataTable();
            ITableColumnModel columnModel = this.productTable.getTableColumnModel();
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
            column.setWidth(140);
            column.setAlignmentX(JLabel.LEFT);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("规格");
            column.setColumnName("spec");
            column.setWidth(80);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("采购价");
            column.setColumnName("stockPrice");
            column.setValueType(Currency.class);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("零售价");
            column.setColumnName("retailPrice");
            column.setValueType(Currency.class);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("单位");
            column.setColumnName("smallUnit");
            column.setWidth(40);

            columnModel.addColumn(column);
            column = new JDataTableColumn();
            column.setHeaderText("生产厂家");
            column.setColumnName("factory");
            column.setWidth(120);
            columnModel.addColumn(column);

            column = new JDataTableColumn();
            column.setHeaderText("产地");
            column.setColumnName("area");
            column.setWidth(40);
            columnModel.addColumn(column);

//            column = new JDataTableColumn();
//            column.setHeaderText("助记码");
//            column.setColumnName("code");
//            column.setWidth(50);
//            columnModel.addColumn(column);
        }
        IDataSource dataSource = (IDataSource)this.productTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Product> prods = this.getUsableProducts();
            for(Product product:prods)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(product, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return productTable;
    }



    public JDataTable getStorageTable() {
//         if (this.storageTable == null || this.storageChanged)
//        {
            this.storageTable = new JDataTable();
            ITableColumnModel columnModel = this.storageTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);

		

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.storageTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getStorages();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return storageTable;
    }

    public JDataTable getAllStorageTable() {
//         if (this.allStorageTable == null || this.storageChanged)
//        {
            this.allStorageTable = new JDataTable();
            ITableColumnModel columnModel = this.allStorageTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);

		

		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
//        }
        IDataSource dataSource = (IDataSource)this.allStorageTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getAllStorages();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return allStorageTable;
    }

    public JDataTable getStorageOutTable() {
         if (this.storageOutTable == null || this.storageOutChanged)
        {
            this.storageOutTable = new JDataTable();
            ITableColumnModel columnModel = this.storageOutTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(120);
                column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("简称");
		column.setColumnName("shortName");
		column.setWidth(60);
		columnModel.addColumn(column);



		column = new JDataTableColumn();
		column.setHeaderText("类型");
		column.setColumnName("type");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("负责人");
		column.setColumnName("employee");
		column.setWidth(60);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("电话");
		column.setColumnName("phone");
		column.setWidth(100);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("地址");
		column.setColumnName("address");
		column.setWidth(180);
		columnModel.addColumn(column);
        }
        IDataSource dataSource = (IDataSource)this.storageOutTable.getTableModel();
        if (dataSource.getRowCount() <= 0)
        {
            List<IDataRow> dataRows = new ArrayList<IDataRow>();
            List<Storage> stors = this.getStoragesOut();
            for(Storage storage:stors)
            {
                IDataRow dataRow = ObjectDataRow.newDataRow(storage, "id", null);
                dataRows.add(dataRow);
            }
            dataSource.insertDataRows(dataRows);
        }
        return storageOutTable;
    }

	public List<Employee> getEmployees()
	{
		if (this.employeeChanged)
		{
			this.employees = Main.getServiceManager().getEmployeeService().getUsableEmployees(company);
			this.employeeChanged = false;
            if (this.employeeTable != null)
            {
                this.employeeTable.getDataSource().clear();
            }
		}
		return this.employees;
	}

    public List<String> getStringEmployees()
	{
		if (this.stremployeeChanged)
		{
			this.stremployees = Main.getServiceManager().getEmployeeService().getStringUsableEmployees(company);
			this.stremployeeChanged = false;
            if (this.employeeTable != null)
            {
                this.employeeTable.getDataSource().clear();
            }
		}
		return this.stremployees;
	}

	public List<EmployeeCatalog> getDepartments()
	{
		if (this.departmentChanged)
		{
			this.departments = Main.getServiceManager().getEmployeeService().getCatalogs(company);
			this.departmentChanged = false;
		}
		return this.departments;
	}

    public List<ProductCatalog> getProductCatalogs()
	{
		if (this.productCatalogChanged)
		{
			this.productCatalogs = Main.getServiceManager().getProductService().getCatalogs(company);
			this.productCatalogChanged = false;
		}
		return this.productCatalogs;
	}

    public List<CustomerCatalog> getCustomerCatalogs()
	{
		if (this.customerCatalogChanged)
		{
            this.customerCatalogs = new ArrayList();
            List<CustomerCatalog> cusCatalogs = Main.getServiceManager().getCustomerService().getCatalogs(company);
            for(CustomerCatalog cc : cusCatalogs)
            {
                if(cc.getCustomerType() == null || cc.getCustomerType().equals("1"))
                {
                    this.customerCatalogs.add(cc);
                }
            }
			this.customerCatalogChanged = false;
		}
		return this.customerCatalogs;
	}

    public List<CustomerCatalog> getSupplierCatalogs()
	{
		if (this.supplierCatalogChanged)
		{
            this.supplierCatalogs = new ArrayList();
            List<CustomerCatalog> cusCatalogs = Main.getServiceManager().getCustomerService().getCatalogs(company);
            for(CustomerCatalog cc : cusCatalogs)
            {
                if(cc.getCustomerType() == null || cc.getCustomerType().equals("2"))
                {
                    this.supplierCatalogs.add(cc);
                }
            }
			this.supplierCatalogChanged = false;
		}
		return this.supplierCatalogs;
	}

    public List<Customer> getAllCustomers()
	{
		if (this.allCustomerChanged)
		{
            this.allCustomers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("供应商"))
                {
                    this.allCustomers.add(c);
                }
            }
			this.allCustomerChanged = false;

		}
		return this.allCustomers;
	}

    public List<Customer> getAllSuppliers()
	{
		if (this.allSupplierChanged)
		{
            this.allSuppliers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("客户"))
                {
                    this.allSuppliers.add(c);
                }
            }
			this.allSupplierChanged = false;
		}
		return this.allSuppliers;
	}

	public List<Customer> getCustomers()
	{
		if (this.customerChanged)
		{
            this.customers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("供应商"))
                {
                    this.customers.add(c);
                }
            }
			this.customerChanged = false;
            if (this.customerTable != null)
            {
                this.customerTable.getDataSource().clear();
            }
		}
		return this.customers;
	}

    public List<Customer> getSuppliers()
	{
		if (this.supplierChanged)
		{
            this.suppliers = new ArrayList();
            List<Customer> custs = Main.getServiceManager().getCustomerService().getCustomers(company);
            for(Customer c : custs)
            {
                if(c.getCustomerType() == null || !c.getCustomerType().equals("客户"))
                {
                    this.suppliers.add(c);
                }
            }
			this.supplierChanged = false;
            if (this.customerTable != null)
            {
                this.customerTable.getDataSource().clear();
            }
		}
		return this.suppliers;
	}

    public List<Storage> getAllStorages()
	{
		if (this.allStorageChanged)
		{
			this.allStorages = Main.getServiceManager().getStorageService().getStorages(company);
			this.allStorageChanged = false;
		}
		return this.allStorages;
	}

	public List<Storage> getStorages()
	{
		if (this.storageChanged)
		{
			this.storages = Main.getServiceManager().getStorageService().getUsableStorages(company);
			this.storageChanged = false;
            if (this.storageTable != null)
            {
                this.storageTable.getDataSource().clear();
            }
		}
		return this.storages;
	}

    public List<Storage> getStoragesOut()
	{
		if (this.storageOutChanged)
		{
			this.storages = Main.getServiceManager().getStorageService().getUsableStorages(company);
			this.storageOutChanged = false;
            if (this.storageOutTable != null)
            {
                this.storageOutTable.getDataSource().clear();
            }
		}
		return this.storages;
	}

    public List<Subject> getSubjects()
    {
        if(this.subjectChanged)
        {
            this.subjects = Main.getServiceManager().getAccountingService().getAllSubject(company);
            this.subjectChanged = false;
        }
        return this.subjects;
    }

    public List<String> getProductUnits()
    {
        if(this.productUnitChanged)
        {
            this.productUnits = Main.getServiceManager().getOptionSetService().getProductUnits(company);
            this.productUnitChanged = false;
        }
        return this.productUnits;
    }

    public List<String> getProductShelfs()
    {
        if(this.productShelfChanged)
        {
            this.productShelfs = Main.getServiceManager().getOptionSetService().getProductShelfs(company);
            this.productShelfChanged = false;
        }
        return this.productShelfs;
    }

    public List<String> getProductTaxrates()
    {
        if(this.productTaxrateChanged)
        {
            this.productTaxrates = Main.getServiceManager().getOptionSetService().getProductTaxrates(company);
            this.productTaxrateChanged = false;
        }
        return this.productTaxrates;
    }

    public List<String> getCustomerTypes()
    {
        if(this.customerTypeChanged)
        {
            this.customerTypes = Main.getServiceManager().getOptionSetService().getCustomerTypes(company);
            this.customerTypeChanged = false;
        }
        return this.customerTypes;
    }

    public List<CustomerPrice> getCustomerPrices()
    {
        if(this.customerPriceChanged)
        {
            this.customerPrices = Main.getServiceManager().getOptionSetService().getCustomerPrices(company);
        }
        return this.customerPrices;
    }

    public List<CustomerInvoice> getCustomerInvoices()
    {
        if(this.customerInvoiceChanged)
        {
            this.customerInvoices = Main.getServiceManager().getOptionSetService().getCustomerInvoices(company);
        }
        return this.customerInvoices;
    }

    public List<String> getEmployeeDutys()
    {
        if(this.employeeDutyChanged)
        {
            this.employeeDutys = Main.getServiceManager().getOptionSetService().getEmployeeDutys(company);
            this.employeeDutyChanged = false;
        }
        return this.employeeDutys;
    }

    public List<String> getEmployeeDegrees()
    {
        if(this.employeeDegreeChanged)
        {
            this.employeeDegrees = Main.getServiceManager().getOptionSetService().getEmployeeDegrees(company);
            this.employeeDegreeChanged = false;
        }
        return this.employeeDegrees;
    }

    public List<String> getEmployeeLevels()
    {
        if(this.employeeLevelChanged)
        {
            this.employeeLevels = Main.getServiceManager().getOptionSetService().getEmployeeLevels(company);
            this.employeeLevelChanged = false;
        }
        return this.employeeLevels;
    }

    public List<String> getEmployeeHealths()
    {
        if(this.employeeHealthChanged)
        {
            this.employeeHealths = Main.getServiceManager().getOptionSetService().getEmployeeHealths(company);
            this.employeeHealthChanged = false;
        }
        return this.employeeHealths;
    }

    public List<String> getStorageTypes()
    {
        if(this.storageTypeChanged)
        {
            this.storageTypes = Main.getServiceManager().getOptionSetService().getStorageTypes(company);
            this.storageTypeChanged = false;
        }
        return this.storageTypes;
    }

    public List<String> getSexs()
    {
        List<String> list = new ArrayList();
        list.add("男");
        list.add("女");
        return list;
    }

    public List<String> getCustomerType()
    {
        List<String> list = new ArrayList();
        list.add("客户");
        list.add("客户及供应商");
        return list;
    }

    public List<String> getSupplierType()
    {
        List<String> list = new ArrayList();
        list.add("供应商");
        list.add("客户及供应商");
        return list;
    }

	public void refreshProducts()
	{
		this.productChanged = true;
	}

    public void refreshUsableProducts()
	{
		this.usableProductChanged = true;
	}

	public void refreshCustomers()
	{
		this.customerChanged = true;
        this.supplierChanged = true;
	}

	public void refreshEmployees()
	{
		this.employeeChanged = true;
        this.stremployeeChanged = true;
	}
	public void refreshStorages()
	{
		this.storageChanged = true;
	}

    public void refreshStoragesOut()
	{
		this.storageOutChanged = true;
	}

	public void refreshAllCustomers()
	{
		this.allCustomerChanged = true;
        this.allSupplierChanged = true;
	}

	public void refreshAllEmployees()
	{
		this.allEmployeeChanged = true;
	}
	public void refreshAllStorages()
	{
		this.allStorageChanged = true;
	}

    public void refreshProductCatalogs()
	{
		this.productCatalogChanged = true;
	}

    public void refreshCustomerCatalogs()
	{
		this.customerCatalogChanged = true;
        this.supplierCatalogChanged = true;
	}

	public void refreshDepartments()
	{
		this.departmentChanged = true;
	}

	public void refreshProductUnits()
	{
		this.productUnitChanged = true;
	}

    public void refreshProductShelfs()
	{
		this.productShelfChanged = true;
	}


    public void refreshProductTaxrates()
	{
		this.productTaxrateChanged = true;
	}

    public void refreshCustomerTypes()
	{
		this.customerTypeChanged = true;
	}

    public void refreshCustomerPrices()
	{
		this.customerPriceChanged = true;
	}

    public void refreshCustomerInvoices()
	{
		this.customerInvoiceChanged = true;
	}

    public void refreshEmployeeDutys()
	{
		this.employeeDutyChanged = true;
	}

    public void refreshEmployeeDegrees()
	{
		this.employeeDegreeChanged = true;
	}

    public void refreshEmployeeLevels()
	{
		this.employeeLevelChanged = true;
	}

    public void refreshEmployeeHealths()
	{
		this.employeeHealthChanged = true;
	}

    public void refreshStorageTypes()
	{
		this.storageTypeChanged = true;
	}

    public void refreshSubjects()
	{
		this.subjectChanged = true;
	}

    public void refreshBankSubjectType()
	{
		this.bankSubjectTypeChanged = true;
	}

    public void refreshCommonExpenseType()
	{
		this.commonExpenseTypeChanged = true;
	}

    public void refreshOtherIncomeType()
	{
		this.otherIncomeTypeChanged = true;
	}

    public void refreshBussinessExpenseType()
	{
		this.bussinessExpenseTypeChanged = true;
	}

    public void refreshPriceName()
	{
		this.priceNameChanged = true;
	}

    public void dispose()
    {
        if (this.allCustomers != null)
        {
           this.allCustomers.clear();
        }
        if (this.allEmployees != null)
        {
            this.allEmployees.clear();
        }
        if (this.allStorages != null)
        {
            this.allStorages.clear();
        }
        if (this.customerCatalogs != null)
        {
            this.customerCatalogs.clear();
        }
        if (this.customers != null)
        {
            this.customers.clear();
        }
        if (this.employees != null)
        {
            this.employees.clear();
        }
        if (this.products != null)
        {
            this.products.clear();
        }
        if(this.subjects != null)
        {
            this.subjects.clear();
        }
    }

}
 */