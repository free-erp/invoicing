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

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.CustomerCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

public interface ICustomerService
{
	public void deleteCustomer(Customer customer);

	public void deleteCatalog(CustomerCatalog catalog);

	public void saveCustomer(Customer customer);

	public void saveCatalog(CustomerCatalog catalog);

    public CustomerCatalog saveCustomerCatalog(CustomerCatalog catalog);//liufei

    public void insertCustomerCatalog(Company company,String number,String name);//liufei

	public List<Customer> getCustomers(Company company);

    public List<Customer> getSuppliers(Company company);//liufei

    public List<Customer> getUsableCustomers(Company company);//liufei

    public List<Customer> getUsableSuppliers(Company company);//liufei

	public List<CustomerCatalog> getCatalogs(Company company);

    public List<CustomerCatalog> getSupplierCatalogs(Company company);

	public int getCustomerCatalogID(Company company,String number);//liufei

    public CustomerCatalog getCustomerCatalog(Company company,String name);//liufei

    public CustomerCatalog findCustomerCatalog(int id);//liufei

    public boolean isExitCustomerCatalog(Company company, String catalogNumber);//liufei

    public Customer getCustomer(int id);

    public List<Customer> findCustomers(DataBaseQueryVO vo);

    

}
