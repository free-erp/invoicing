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
import org.free_erp.service.entity.system.CustomerInvoice;
import org.free_erp.service.entity.system.CustomerPrice;
import org.free_erp.service.entity.system.CustomerType;
import org.free_erp.service.entity.system.EmployeeDegree;
import org.free_erp.service.entity.system.EmployeeDuty;
import org.free_erp.service.entity.system.EmployeeHealth;
import org.free_erp.service.entity.system.EmployeeLevel;
import org.free_erp.service.entity.system.ProductShelf;
import org.free_erp.service.entity.system.ProductTaxrate;
import org.free_erp.service.entity.system.ProductUnit;
import org.free_erp.service.entity.system.StorageType;

/**
 *
 * @author TengJianfa 13003311398
 */
public interface IOptionSetService
{
    public void saveProductUnit(ProductUnit productUnit);
    
    public List<String> getProductUnits(Company company);

    public List<ProductUnit> getListProductUnits(Company company);

    public void saveProductShelf(ProductShelf productShelf);

    public List<String> getProductShelfs(Company company);

    public List<ProductShelf> getListProductShelfs(Company company);

    public void saveProductTaxrate(ProductTaxrate productTaxrate);

    public List<String> getProductTaxrates(Company company);

    public List<ProductTaxrate> getListProductTaxrates(Company company);

    public void saveCustomerType(CustomerType customerType);

    public List<String> getCustomerTypes(Company company);

    public List<CustomerType> getListCustomerTypes(Company company);

    public void saveCustomerPrice(CustomerPrice customerPrice);

    public List<CustomerPrice> getCustomerPrices(Company company);//unused

    public void saveCustomerInvoice(CustomerInvoice customerInvoice);

    public List<CustomerInvoice> getCustomerInvoices(Company company);

    public void saveEmployeeDuty(EmployeeDuty employeeDuty);

    public List<String> getEmployeeDutys(Company company);

    public List<EmployeeDuty> getListEmployeeDutys(Company company);

    public void saveEmployeeDegree(EmployeeDegree employeeDegree);

    public List<String> getEmployeeDegrees(Company company);

    public List<EmployeeDegree> getListEmployeeDegrees(Company company);

    public void saveEmployeeLevel(EmployeeLevel employeeLevel);

    public List<String> getEmployeeLevels(Company company);

    public List<EmployeeLevel> getListEmployeeLevels(Company company);

    public void saveEmployeeHealth(EmployeeHealth employeeHealth);

    public List<String> getEmployeeHealths(Company company);

    public List<EmployeeHealth> getListEmployeeHealths(Company company);

    public void saveStorageType(StorageType storageType);

    public List<String> getStorageTypes(Company company);

    public List<StorageType> getListStorageTypes(Company company);

    public void deleteProductUnit(ProductUnit productUnit);

    public void deleteProductShelf(ProductShelf productShelf);

    public void deleteProductTaxrate(ProductTaxrate productTaxrate);

    public void deleteCustomerType(CustomerType customerType);

    public void deleteCustomerPrice(CustomerPrice customerPrice);

    public void deleteCustomerInvoice(CustomerInvoice customerInvoice);

    public void deleteEmployeeDuty(EmployeeDuty employeeDuty);

    public void deleteEmployeeDegree(EmployeeDegree employeeDegree);

    public void deleteEmployeeLevel(EmployeeLevel employeeLevel);

    public void deleteEmployeeHealth(EmployeeHealth employeeHealth);

    public void deleteStorageType(StorageType storageType);
}
