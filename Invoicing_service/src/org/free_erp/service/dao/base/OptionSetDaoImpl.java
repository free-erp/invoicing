/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.base;

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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Administrator
 */
public class OptionSetDaoImpl extends HibernateDaoSupport implements IOptionSetDao
{
    public void saveProductUnit(ProductUnit productUnit)
    {
        this.getHibernateTemplate().saveOrUpdate(productUnit);
    }
    
    public List<ProductUnit> findProductUnits(Company company)
    {
        return (List<ProductUnit>) this.getHibernateTemplate().find("from ProductUnit  where company =?", new Object[]{company});
    }

    public void saveProductShelf(ProductShelf productShelf)
    {
        this.getHibernateTemplate().saveOrUpdate(productShelf);
    }

    public List<ProductShelf> findProductShelfs(Company company)
    {
        return (List<ProductShelf>)this.getHibernateTemplate().find("from ProductShelf  where company =? ",new Object[]{company});
    }

    public void saveProductTaxrate(ProductTaxrate productTaxrate)
    {
        this.getHibernateTemplate().saveOrUpdate(productTaxrate);
    }

    public List<ProductTaxrate> findProductTaxrates(Company company)
    {
        return (List<ProductTaxrate>) this.getHibernateTemplate().find("from ProductTaxrate  where company =?", new Object[]{company});
    }

    public void saveCustomerType(CustomerType customerType)
    {
        this.getHibernateTemplate().saveOrUpdate(customerType);
    }

    public List<CustomerType> findCustomerTypes(Company company)
    {
        return (List<CustomerType>) this.getHibernateTemplate().find("from CustomerType  where company =?", new Object[]{company});
    }

    public void saveCustomerPrice(CustomerPrice customerPrice)
    {
        this.getHibernateTemplate().saveOrUpdate(customerPrice);
    }

    public List<CustomerPrice> findCustomerPrices(Company company)
    {
        return (List<CustomerPrice>) this.getHibernateTemplate().find("from CustomerPrice  where company =?", new Object[]{company});
    }

    public void saveCustomerInvoice(CustomerInvoice customerInvoice)
    {
         this.getHibernateTemplate().saveOrUpdate(customerInvoice);
    }

    public List<CustomerInvoice> findCustomerInvoices(Company company)
    {
        return (List<CustomerInvoice>) this.getHibernateTemplate().find("from CustomerInvoice  where company =?", new Object[]{company});
    }

    public void saveEmployeeDuty(EmployeeDuty employeeDuty)
    {
        this.getHibernateTemplate().saveOrUpdate(employeeDuty);
    }

    public List<EmployeeDuty> findEmployeeDutys(Company company)
    {
        return (List<EmployeeDuty>) this.getHibernateTemplate().find("from EmployeeDuty  where company =?", new Object[]{company});
    }

    public void saveEmployeeDegree(EmployeeDegree employeeDegree)
    {
        this.getHibernateTemplate().saveOrUpdate(employeeDegree);
    }

    public List<EmployeeDegree> findEmployeeDegrees(Company company)
    {
        return (List<EmployeeDegree>) this.getHibernateTemplate().find("from EmployeeDegree  where company =?", new Object[]{company});
    }

    public void saveEmployeeLevel(EmployeeLevel employeeLevel)
    {
        this.getHibernateTemplate().saveOrUpdate(employeeLevel);
    }

    public List<EmployeeLevel> findEmployeeLevels(Company company)
    {
        return (List<EmployeeLevel>) this.getHibernateTemplate().find("from EmployeeLevel  where company =?", new Object[]{company});
    }

    public void saveEmployeeHealth(EmployeeHealth employeeHealth)
    {
        this.getHibernateTemplate().saveOrUpdate(employeeHealth);
    }

    public List<EmployeeHealth> findEmployeeHealths(Company company)
    {
        return (List<EmployeeHealth>) this.getHibernateTemplate().find("from EmployeeHealth  where company =?", new Object[]{company});
    }

    public void saveStorageType(StorageType storageType)
    {
        this.getHibernateTemplate().saveOrUpdate(storageType);
    }

    public List<StorageType> findStorageTypes(Company company)
    {
        return (List<StorageType>) this.getHibernateTemplate().find("from StorageType  where company =?", new Object[]{company});
    }

     public void deleteProductUnit(ProductUnit productUnit)
     {
         this.getHibernateTemplate().delete(productUnit);
     }

     public void deleteProductShelf(ProductShelf productShelf)
     {
         this.getHibernateTemplate().delete(productShelf);
     }

    public void deleteProductTaxrate(ProductTaxrate productTaxrate)
    {
        this.getHibernateTemplate().delete(productTaxrate);
    }

    public void deleteCustomerType(CustomerType customerType)
    {
        this.getHibernateTemplate().delete(customerType);
    }

    public void deleteCustomerPrice(CustomerPrice customerPrice)
    {
        this.getHibernateTemplate().delete(customerPrice);
    }

    public void deleteCustomerInvoice(CustomerInvoice customerInvoice)
    {
        this.getHibernateTemplate().delete(customerInvoice);
    }

    public void deleteEmployeeDuty(EmployeeDuty employeeDuty)
    {
        this.getHibernateTemplate().delete(employeeDuty);
    }

    public void deleteEmployeeDegree(EmployeeDegree employeeDegree)
    {
        this.getHibernateTemplate().delete(employeeDegree);
    }

    public void deleteEmployeeLevel(EmployeeLevel employeeLevel)
    {
        this.getHibernateTemplate().delete(employeeLevel);
    }

    public void deleteEmployeeHealth(EmployeeHealth employeeHealth)
    {
        this.getHibernateTemplate().delete(employeeHealth);
    }

    public void deleteStorageType(StorageType storageType)
    {
        this.getHibernateTemplate().delete(storageType);
    }
}
