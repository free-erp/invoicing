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

/**
 *
 * @author TengJianfa 13003311398
 */
public interface IOptionSetDao
{
    public void saveProductUnit(ProductUnit productUnit);
    
    public List<ProductUnit> findProductUnits(Company company);

    public void saveProductShelf(ProductShelf productShelf);

    public List<ProductShelf> findProductShelfs(Company company);

    public void saveProductTaxrate(ProductTaxrate productTaxrate);

    public List<ProductTaxrate> findProductTaxrates(Company company);

    public void saveCustomerType(CustomerType customerType);

    public List<CustomerType> findCustomerTypes(Company company);

    public void saveCustomerPrice(CustomerPrice customerPrice);

    public List<CustomerPrice> findCustomerPrices(Company company);

    public void saveCustomerInvoice(CustomerInvoice customerInvoice);

    public List<CustomerInvoice> findCustomerInvoices(Company company);

    public void saveEmployeeDuty(EmployeeDuty employeeDuty);

    public List<EmployeeDuty> findEmployeeDutys(Company company);

    public void saveEmployeeDegree(EmployeeDegree employeeDegree);

    public List<EmployeeDegree> findEmployeeDegrees(Company company);

    public void saveEmployeeLevel(EmployeeLevel employeeLevel);

    public List<EmployeeLevel> findEmployeeLevels(Company company);

    public void saveEmployeeHealth(EmployeeHealth employeeHealth);

    public List<EmployeeHealth> findEmployeeHealths(Company company);

    public void saveStorageType(StorageType storageType);

    public List<StorageType> findStorageTypes(Company company);

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
