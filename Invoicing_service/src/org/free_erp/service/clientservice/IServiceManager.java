package org.free_erp.service.clientservice;

import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.ICompanyService;
import org.free_erp.service.logic.ICustomerService;
import org.free_erp.service.logic.IEmployeeService;
import org.free_erp.service.logic.IManagerService;
import org.free_erp.service.logic.IOptionSetService;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IProductService;
import org.free_erp.service.logic.ISaleService;
import org.free_erp.service.logic.IStorageService;
import org.free_erp.service.logic.ISystemManageService;
import org.free_erp.service.logic.purchase.IPurchaseService;

public interface IServiceManager {

    public ISaleService getSaleService();

    public ISystemManageService getSystemManageService();

    public IPermissionsService getPermissionsService();

    public IProductService getProductService();

    public IManagerService getManagerService();

    public ICustomerService getCustomerService();

    public IEmployeeService getEmployeeService();

    public ICompanyService getCompanyService();

    public IStorageService getStorageService();

    public IOptionSetService getOptionSetService();

    public IPurchaseService getPurchaseService();//

    public IAccountingService getAccountingService();
}
