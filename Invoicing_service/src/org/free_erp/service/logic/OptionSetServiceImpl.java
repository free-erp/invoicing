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

import java.util.ArrayList;
import java.util.List;
import org.free_erp.service.dao.base.IOptionSetDao;
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
public class OptionSetServiceImpl implements IOptionSetService
{
    IOptionSetDao dao;

    public IOptionSetDao getDao() {
        return dao;
    }

    public void setDao(IOptionSetDao dao) {
        this.dao = dao;
    }
    public void saveProductUnit(ProductUnit productUnit)
    {
        this.dao.saveProductUnit(productUnit);
    }

    public List<ProductUnit> getListProductUnits(Company company)
    {
        return this.dao.findProductUnits(company);
    }

    public List<String> getProductUnits(Company company)
    {
        List<ProductUnit> productUnits = this.dao.findProductUnits(company);
        List<String> list = new ArrayList();
        if(productUnits != null && productUnits.size() > 0)
        {
            for(int i=0;i<productUnits.size();i++)
            {
                list.add(productUnits.get(i).getProUnit());
            }
            return list;
        }
        return list;
    }

    public void saveProductShelf(ProductShelf productShelf)
    {
        this.dao.saveProductShelf(productShelf);
    }

    public List<ProductShelf> getListProductShelfs(Company company)
    {
        return this.dao.findProductShelfs(company);
    }

    public List<String> getProductShelfs(Company company)
    {
        List<ProductShelf> productShelfs = this.dao.findProductShelfs(company);
        List<String> list = new ArrayList();
        if(productShelfs != null && productShelfs.size() > 0)
        {
            for(int i=0;i<productShelfs.size();i++)
            {
                list.add(productShelfs.get(i).getProShelf());
            }
            return list;
        }
        return list;
    }

    public void saveProductTaxrate(ProductTaxrate productTaxrate)
    {
        this.dao.saveProductTaxrate(productTaxrate);
    }

    public List<ProductTaxrate> getListProductTaxrates(Company company)
    {
        return this.dao.findProductTaxrates(company);
    }

    public List<String> getProductTaxrates(Company company)
    {
        List<ProductTaxrate> productTaxrates = this.dao.findProductTaxrates(company);
        List<String> list = new ArrayList();
        if(productTaxrates != null && productTaxrates.size() > 0)
        {
            for(int i=0;i<productTaxrates.size();i++)
            {
                list.add(String.valueOf(productTaxrates.get(i).getProTaxrate()));
            }
            return list;
        }
        return list;
    }

    public void saveCustomerType(CustomerType customerType)
    {
        this.dao.saveCustomerType(customerType);
    }

    public List<CustomerType> getListCustomerTypes(Company company)
    {
        return this.dao.findCustomerTypes(company);
    }

    public List<String> getCustomerTypes(Company company)
    {
        List<CustomerType> customerTypes = this.dao.findCustomerTypes(company);
        List<String> list = new ArrayList();
        if(customerTypes != null && customerTypes.size() > 0)
        {
            for(int i=0;i<customerTypes.size();i++)
            {
                list.add(customerTypes.get(i).getCusType());
            }
            return list;
        }
        return list;
    }

    public void saveCustomerPrice(CustomerPrice customerPrice)
    {
        this.dao.saveCustomerPrice(customerPrice);
    }

    public List<CustomerPrice> getCustomerPrices(Company company)
    {
        return this.dao.findCustomerPrices(company);
    }

    public void saveCustomerInvoice(CustomerInvoice customerInvoice)
    {
        this.dao.saveCustomerInvoice(customerInvoice);
    }

    public List<CustomerInvoice> getCustomerInvoices(Company company)
    {
        return this.dao.findCustomerInvoices(company);
    }

    public void saveEmployeeDuty(EmployeeDuty employeeDuty)
    {
        this.dao.saveEmployeeDuty(employeeDuty);
    }

    public List<EmployeeDuty> getListEmployeeDutys(Company company)
    {
        return this.dao.findEmployeeDutys(company);
    }

    public List<String> getEmployeeDutys(Company company)
    {
        List<EmployeeDuty> eployeeDutys = this.dao.findEmployeeDutys(company);
        List<String> list = new ArrayList();
        if(eployeeDutys != null && eployeeDutys.size() > 0)
        {
            for(int i=0;i<eployeeDutys.size();i++)
            {
                list.add(eployeeDutys.get(i).getEmpDuty());
            }
            return list;
        }
        return list;
    }

    public void saveEmployeeDegree(EmployeeDegree employeeDegree)
    {
        this.dao.saveEmployeeDegree(employeeDegree);
    }

    public List<EmployeeDegree> getListEmployeeDegrees(Company company)
    {
        return this.dao.findEmployeeDegrees(company);
    }

    public List<String> getEmployeeDegrees(Company company)
    {
        List<EmployeeDegree> employeeDegrees = this.dao.findEmployeeDegrees(company);
        List<String> list = new ArrayList();
        if(employeeDegrees != null && employeeDegrees.size() > 0)
        {
            for(int i=0;i<employeeDegrees.size();i++)
            {
                list.add(employeeDegrees.get(i).getEmpDegree());
            }
            return list;
        }
        return list;
    }

    public void saveEmployeeLevel(EmployeeLevel employeeLevel)
    {
        this.dao.saveEmployeeLevel(employeeLevel);
    }

    public List<EmployeeLevel> getListEmployeeLevels(Company company)
    {
        return this.dao.findEmployeeLevels(company);
    }

    public List<String> getEmployeeLevels(Company company)
    {
        List<EmployeeLevel> employeeLevels = this.dao.findEmployeeLevels(company);
        List<String> list = new ArrayList();
        if(employeeLevels != null && employeeLevels.size() > 0)
        {
            for(int i=0;i<employeeLevels.size();i++)
            {
                list.add(employeeLevels.get(i).getEmpLevel());
            }
            return list;
        }
        return list;
    }

    public void saveEmployeeHealth(EmployeeHealth employeeHealth)
    {
        this.dao.saveEmployeeHealth(employeeHealth);
    }

    public List<EmployeeHealth> getListEmployeeHealths(Company company)
    {
        return this.dao.findEmployeeHealths(company);
    }

    public List<String> getEmployeeHealths(Company company)
    {
        List<EmployeeHealth> employeeHealths = this.dao.findEmployeeHealths(company);
        List<String> list = new ArrayList();
        if(employeeHealths != null && employeeHealths.size() > 0)
        {
            for(int i=0;i<employeeHealths.size();i++)
            {
                list.add(employeeHealths.get(i).getEmpHealth());
            }
            return list;
        }
        return list;
    }

    public void saveStorageType(StorageType storageType)
    {
        this.dao.saveStorageType(storageType);
    }

    public List<StorageType> getListStorageTypes(Company company)
    {
        return this.dao.findStorageTypes(company);
    }

    public List<String> getStorageTypes(Company company)
    {
        List<StorageType> storageTypes = this.dao.findStorageTypes(company);
        List<String> list = new ArrayList();
        if(storageTypes != null && storageTypes.size() > 0)
        {
            for(int i=0;i<storageTypes.size();i++)
            {
                list.add(storageTypes.get(i).getStoType());
            }
            return list;
        }
        return list;
    }
    
    public void deleteProductUnit(ProductUnit productUnit)
    {
        this.dao.deleteProductUnit(productUnit);
    }

    public void deleteProductShelf(ProductShelf productShelf)
    {
        this.dao.deleteProductShelf(productShelf);
    }

    public void deleteProductTaxrate(ProductTaxrate productTaxrate)
    {
        this.dao.deleteProductTaxrate(productTaxrate);
    }

    public void deleteCustomerType(CustomerType customerType)
    {
        this.dao.deleteCustomerType(customerType);
    }

    public void deleteCustomerPrice(CustomerPrice customerPrice)
    {
        this.dao.deleteCustomerPrice(customerPrice);
    }

    public void deleteCustomerInvoice(CustomerInvoice customerInvoice)
    {
        this.dao.deleteCustomerInvoice(customerInvoice);
    }

    public void deleteEmployeeDuty(EmployeeDuty employeeDuty)
    {
        this.dao.deleteEmployeeDuty(employeeDuty);
    }

    public void deleteEmployeeDegree(EmployeeDegree employeeDegree)
    {
        this.dao.deleteEmployeeDegree(employeeDegree);
    }

    public void deleteEmployeeLevel(EmployeeLevel employeeLevel)
    {
        this.dao.deleteEmployeeLevel(employeeLevel);
    }

    public void deleteEmployeeHealth(EmployeeHealth employeeHealth)
    {
        this.dao.deleteEmployeeHealth(employeeHealth);
    }

    public void deleteStorageType(StorageType storageType)
    {
        this.dao.deleteStorageType(storageType);
    }
}
