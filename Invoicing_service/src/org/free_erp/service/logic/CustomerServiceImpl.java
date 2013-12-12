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
package org.free_erp.service.logic;

import java.util.List;

import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.dao.base.ICustomerDao;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.exception.LogicException;

public class CustomerServiceImpl implements ICustomerService
{

	private ICustomerDao dao;
    private ISystemManageService systemManageService;

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

	public ICustomerDao getDao()
	{
		return dao;
	}

	public void setDao(ICustomerDao dao)
	{
		this.dao = dao;
	}

	public void deleteCatalog(CustomerCatalog catalog)
	{
		// TODO Auto-generated method stub
		this.dao.deleteCustomerCatalog(catalog);
	}

	public void deleteCustomer(Customer customer)
	{
        try
        {
            this.dao.deleteCustomer(customer);
        }
        catch(Exception ex)
        {
            throw new LogicException("该客户存在于其它单据中，不能被删除!");
        }
	}

	public List<CustomerCatalog> getCatalogs(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findCustomerCatalogs(company);
	}

	public List<Customer> getCustomers(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findCustomers(company);
	}

    public List<Customer> getUsableCustomers(Company company)//liufei
    {
        return this.dao.findUsableCustomers(company);
    }

	public void saveCatalog(CustomerCatalog catalog)
	{
		// TODO Auto-generated method stub
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.CUS_CATALOG_NUM)+systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.CUS_CATALOG_NUM);
		    catalog.setNumber(number);
        }
		this.dao.saveCustomerCatalog(catalog);
	}

    public CustomerCatalog saveCustomerCatalog(CustomerCatalog catalog)//liufei
    {
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.CUS_CATALOG_NUM)+systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.CUS_CATALOG_NUM);
		    catalog.setNumber(number);
        }
		this.dao.saveCustomerCatalog(catalog);
        return catalog;
    }

    public void insertCustomerCatalog(Company company,String number,String name)//liufei
    {
        this.dao.insertCustomerCatalog(company, number, name);
    }

	public void saveCustomer(Customer customer)
	{
		// TODO Auto-generated method stub
        if (customer.getCompany() == null)
		{
			throw new RuntimeException("web service中请使用带companyId的方法");
		}
//        if(!this.isExitCustomerCatalog(customer.getCompany(), customer.getCatalog().getNumber()))
//        {
//            throw new LogicException("该类别不存在或已被删除，请先刷新基础信息，再重试！");
//        }
        if(customer.getNumber() == null || customer.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(customer.getCompany(), NumberConstants.CUS_NUM)+systemManageService.getAutoNumber(customer.getCompany(), NumberConstants.CUS_NUM);
		    customer.setNumber(number);
        }
        this.dao.saveCustomer(customer);
	}

    public int getCustomerCatalogID(Company company,String number)//liufei
    {
        return this.dao.getCustomerCatalogID(company,number);
    }

    public CustomerCatalog getCustomerCatalog(Company company,String name)//liufei
    {
        return this.dao.getCustomerCatalog(company, name);
    }

    public CustomerCatalog findCustomerCatalog(int id)//liufei
    {
        return this.dao.findCustomerCatalog(id);
    }

    public boolean isExitCustomerCatalog(Company company, String catalogNumber)//liufei
    {
        return this.dao.isExitCustomerCatalog(company, catalogNumber);
    }

    public Customer getCustomer(int id)
    {
        return this.dao.findCustomer(id);
    }

    public List<Customer> findCustomers(DataBaseQueryVO vo)
    {
        return this.dao.findCustomers(vo);
    }

    public List<Customer> getSuppliers(Company company)
    {
        return this.dao.findSuppliers(company);
    }

    public List<Customer> getUsableSuppliers(Company company)
    {
        return this.dao.findUsableSuppliers(company);
    }

    public List<CustomerCatalog> getSupplierCatalogs(Company company)
    {
        return this.dao.findSupplierCatalogs(company);
    }
}
