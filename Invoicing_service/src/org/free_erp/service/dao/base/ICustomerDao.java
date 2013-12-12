package org.free_erp.service.dao.base;

import java.util.List;

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

public interface ICustomerDao
{
	public void saveCustomerCatalog(CustomerCatalog catalog);

    public void insertCustomerCatalog(Company company,String number,String name);//liufei

	public void saveOrUpdateCustomerCatalog(CustomerCatalog catalog);

	//public boolean isCatalogNumberValid(int companyId, String no);

	public void saveCustomer(Customer customer);

	public void saveOrUpdateCustomer(Customer customer);

	public Customer findCustomer(int id);

	public List<Customer> findCustomers(Company company);

    public List<Customer> findSuppliers(Company company);

    public List<Customer> findUsableCustomers(Company company);//liufei

    public List<Customer> findUsableSuppliers(Company company);//liufei

	public CustomerCatalog findCustomerCatalog(int id);//liufei

	public List<CustomerCatalog> findCustomerCatalogs(Company company);

    public List<CustomerCatalog> findSupplierCatalogs(Company company);

	public void deleteCustomerCatalog(CustomerCatalog catalog);

	public void deleteCustomer(Customer customer);

	public int getCustomerCatalogID(Company company,String number);//liufei

    public CustomerCatalog getCustomerCatalog(Company company,String name);//liufei

    public boolean isExitCustomerCatalog(Company company, String catalogNumber);//liufei

    public List<Customer> findCustomers(DataBaseQueryVO vo);
}
