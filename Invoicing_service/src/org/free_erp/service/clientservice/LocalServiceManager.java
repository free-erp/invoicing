package org.free_erp.service.clientservice;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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


public class LocalServiceManager implements IServiceManager {
    //仅仅是为了单机版本的客户端程序用非webservice方式
    private static String installPath;
    private static String argString;
    private static int systemType = 0; //1 stock
    private static String databaseUrl = "";//"jdbc:derby://192.168.1.22:1527/erp";
    public static void setInstallPath(String path)
    {
		installPath = path;
    }
    

    public static String getArgString()
    {
        return argString;
    }

    public static String getInstallPath()
    {
        return installPath;
    }
	public IStorageService getStorageService() {
		return (IStorageService) beanFactory.getBean("storageService");
	}

    public IAccountingService getAccountingService() {
        return (IAccountingService)beanFactory.getBean("accountingService");
    }

	private BeanFactory beanFactory;

	public LocalServiceManager() {
		String fileName = "applicationContext.xml";
        if (systemType == 1)
        {
            fileName = "applicationContext_stock.xml";
        }
        Resource resource = new ClassPathResource(fileName);

		beanFactory = new XmlBeanFactory(resource);
		// init,将耗费时间比较大的操作放在启动画面出来的时候操作
		getSystemManageService();
	}

    public LocalServiceManager(String path, String argStr) {
        installPath = path;
        argString = argStr;
		String fileName = "applicationContext.xml";
        if (systemType == 1)
        {
            fileName = "applicationContext_stock.xml";
        }
        Resource resource = new ClassPathResource(fileName);
		beanFactory = new XmlBeanFactory(resource);
		// init,将耗费时间比较大的操作放在启动画面出来的时候操作
		getSystemManageService();
	}

	// test
	public ISystemManageService getSystemManageService() {
		return (ISystemManageService) beanFactory
				.getBean("systemManageService");
	}

	public IProductService getProductService() {
		return (IProductService) beanFactory.getBean("productService");
	}

	public ICustomerService getCustomerService() {
		return (ICustomerService) beanFactory.getBean("customerService");
	}

	public IEmployeeService getEmployeeService() {
		return (IEmployeeService) beanFactory.getBean("employeeService");
	}

   public IPermissionsService getPermissionsService(){
      return (IPermissionsService) beanFactory.getBean("permissionsService");
   }
	public ICompanyService getCompanyService() {
		return (ICompanyService) beanFactory.getBean("companyService");
	}

    public IOptionSetService getOptionSetService(){
        return (IOptionSetService) beanFactory.getBean("optionSetService");
    }

    public IPurchaseService getPurchaseService(){
        return (IPurchaseService) beanFactory.getBean("purchaseService");
    }

    public ISaleService getSaleService() {
       return (ISaleService) beanFactory.getBean("saleService");
    }

    public IManagerService getManagerService() {
       return (IManagerService) beanFactory.getBean("IManagerService");

    }

    public static void setDatabaseUrl(String url)
    {
        databaseUrl = url;
    }

    public static String getDatabaseUrl()
    {
        return databaseUrl;
    }

    public static int getSystemType()
    {
        return systemType;
    }

    public static void setSystemType(int type)
    {
        systemType = type;
    }

//    public ISaleService getSaleService() {
//        return (ISaleService) beanFactory.getBean("saleService");
//    }
}
