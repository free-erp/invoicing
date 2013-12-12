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
package org.free_erp.client.ui.util;

import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.BSubjectCatalog;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectCatalog;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.CustomerType;
import org.free_erp.service.entity.system.EmployeeDegree;
import org.free_erp.service.entity.system.EmployeeDuty;
import org.free_erp.service.entity.system.EmployeeHealth;
import org.free_erp.service.entity.system.EmployeeLevel;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.system.ProductTaxrate;
import org.free_erp.service.entity.system.ProductUnit;
import org.free_erp.service.entity.system.StorageType;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IOptionSetService;
import org.free_erp.service.logic.IPermissionsService;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CInitDatasUtil
{
    public static void initListDatas(Company company)
    {
        IOptionSetService optionSetService = Main.getServiceManager().getOptionSetService();
        ProductUnit productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("只");
        optionSetService.saveProductUnit(productUnit);
        
        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("个");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("条");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("瓶");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("盒");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("箱");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("克");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("千克");
        optionSetService.saveProductUnit(productUnit);

        productUnit = new ProductUnit();
        productUnit.setCompany(company);
        productUnit.setProUnit("吨");
        optionSetService.saveProductUnit(productUnit);

        ProductTaxrate productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(0);
        optionSetService.saveProductTaxrate(productTaxrate);

        productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(4);
        optionSetService.saveProductTaxrate(productTaxrate);

        productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(6);
        optionSetService.saveProductTaxrate(productTaxrate);

        productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(10);
        optionSetService.saveProductTaxrate(productTaxrate);

        productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(13);
        optionSetService.saveProductTaxrate(productTaxrate);

        productTaxrate = new ProductTaxrate();
        productTaxrate.setCompany(company);
        productTaxrate.setProTaxrate(17);
        optionSetService.saveProductTaxrate(productTaxrate);

        CustomerType customerType = new CustomerType();
        customerType.setCompany(company);
        customerType.setCusType("大客户");
        optionSetService.saveCustomerType(customerType);

        customerType = new CustomerType();
        customerType.setCompany(company);
        customerType.setCusType("潜在客户");
        optionSetService.saveCustomerType(customerType);

        EmployeeDuty employeeDuty = new EmployeeDuty();
        employeeDuty.setCompany(company);
        employeeDuty.setEmpDuty("总经理");
        optionSetService.saveEmployeeDuty(employeeDuty);

        employeeDuty = new EmployeeDuty();
        employeeDuty.setCompany(company);
        employeeDuty.setEmpDuty("总裁");
        optionSetService.saveEmployeeDuty(employeeDuty);

        EmployeeDegree employeeDegree = new EmployeeDegree();
        employeeDegree.setCompany(company);
        employeeDegree.setEmpDegree("工程师");
        optionSetService.saveEmployeeDegree(employeeDegree);

        employeeDegree = new EmployeeDegree();
        employeeDegree.setCompany(company);
        employeeDegree.setEmpDegree("助理工程师");
        optionSetService.saveEmployeeDegree(employeeDegree);

        employeeDegree = new EmployeeDegree();
        employeeDegree.setCompany(company);
        employeeDegree.setEmpDegree("高级工程师");
        optionSetService.saveEmployeeDegree(employeeDegree);

        EmployeeLevel employeeLevel = new EmployeeLevel();
        employeeLevel.setCompany(company);
        employeeLevel.setEmpLevel("高中");
        optionSetService.saveEmployeeLevel(employeeLevel);

        employeeLevel = new EmployeeLevel();
        employeeLevel.setCompany(company);
        employeeLevel.setEmpLevel("大专");
        optionSetService.saveEmployeeLevel(employeeLevel);

        employeeLevel = new EmployeeLevel();
        employeeLevel.setCompany(company);
        employeeLevel.setEmpLevel("本科");
        optionSetService.saveEmployeeLevel(employeeLevel);

        employeeLevel = new EmployeeLevel();
        employeeLevel.setCompany(company);
        employeeLevel.setEmpLevel("本科以上");
        optionSetService.saveEmployeeLevel(employeeLevel);

        EmployeeHealth employeeHealth = new EmployeeHealth();
        employeeHealth.setCompany(company);
        employeeHealth.setEmpHealth("健康");
        optionSetService.saveEmployeeHealth(employeeHealth);

        employeeHealth = new EmployeeHealth();
        employeeHealth.setCompany(company);
        employeeHealth.setEmpHealth("残疾");
        optionSetService.saveEmployeeHealth(employeeHealth);
        
        StorageType storageType = new StorageType();
        storageType.setCompany(company);
        storageType.setStoType("成品库");
        optionSetService.saveStorageType(storageType);

        storageType = new StorageType();
        storageType.setCompany(company);
        storageType.setStoType("未成品库");
        optionSetService.saveStorageType(storageType);
    }

    public static void initSubjectDatas(Company company)
    {
        IAccountingService accountingService = Main.getServiceManager().getAccountingService();
        AffordType affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("支票");
        accountingService.saveAffordType(affordType);
        
        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("现金");
        accountingService.saveAffordType(affordType);

        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("信汇");
        accountingService.saveAffordType(affordType);

        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("电汇");
        accountingService.saveAffordType(affordType);

        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("转帐");
        accountingService.saveAffordType(affordType);

        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("邮托");
        accountingService.saveAffordType(affordType);

        affordType = new AffordType();
        affordType.setCompany(company);
        affordType.setName("其它");
        accountingService.saveAffordType(affordType);        
    }

    public static void initPermissionDatas(User user)
    {
        Permission permission = new Permission();
        permission.setUser(user);
        permission.setBaseCustomer(65535);
        permission.setBaseEmployee(65535);
        permission.setBaseProduct(65535);
        permission.setBaseStorage(65535);
        permission.setCgd(65535);
        permission.setCgdd(65535);
        permission.setCgth(65535);
        permission.setCwcgyf(65535);
        permission.setCwfkjs(65535);
        permission.setCwjyfy(65535);
        permission.setCwqtsr(65535);
        permission.setCwskjs(65535);
        permission.setCwxjyh(65535);
        permission.setCwxsyf(65535);
        permission.setCwybfy(65535);
        permission.setInitKcqc(65535);
        permission.setReportCdcx(65535);
        permission.setReportCdcx(65535);
        permission.setReportCdkstj(65535);
        permission.setReportCdspcx(65535);
        permission.setReportCdsptj(65535);
        permission.setReportCdspyecx(65535);
        permission.setReportCdspyetj(65535);
        permission.setReportCdyecx(65535);
        permission.setReportCdywytj(65535);
        permission.setReportCgjgbd(65535);
        permission.setReportCgksmx(65535);
        permission.setReportCgkstj(65535);
        permission.setReportCgmx(65535);
        permission.setReportCgsphz(65535);
        permission.setReportCgtj(65535);
        permission.setReportCgywymx(65535);
        permission.setReportCgywytj(65535);
        permission.setReportKcbjmx(65535);
        permission.setReportKcbs(65535);
        permission.setReportKcbsmx(65535);
        permission.setReportKcby(65535);
        permission.setReportKcbymx(65535);
        permission.setReportKcckmx(65535);
        permission.setReportKccpck(65535);
        permission.setReportKccprk(65535);
        permission.setReportKccpsxx(65535);
        permission.setReportKcpd(65535);
        permission.setReportKcpdmx(65535);
        permission.setReportKcrkmx(65535);
        permission.setReportKcspye(65535);
        permission.setReportKcyk(65535);
        permission.setReportKcykmx(65535);
        permission.setReportXdcx(65535);
        permission.setReportXdkstj(65535);
        permission.setReportXdspcx(65535);
        permission.setReportXdsptj(65535);
        permission.setReportXdspyemx(65535);
        permission.setReportXdyecx(65535);
        permission.setReportXdywytj(65535);
        permission.setReportXsksmx(65535);
        permission.setReportXskstj(65535);
        permission.setReportXsmx(65535);
        permission.setReportXspyetj(65535);
        permission.setReportXstj(65535);
        permission.setReportXsywytj(65535);
        permission.setReportXywymx(65535);
        permission.setStorageCpck(65535);
        permission.setStorageKcbj(65535);
        permission.setStorageKcbs(65535);
        permission.setStorageKcby(65535);
        permission.setStoragePdgl(65535);
        permission.setStorageSprk(65535);
        permission.setStorageSxxz(65535);
        permission.setStorageYkgl(65535);
        permission.setSystemGsxx(65535);
        permission.setSystemJsxx(65535);
        permission.setSystemXgmm(65535);
        permission.setSystemXtrz(65535);
        permission.setSystemYhxx(65535);
        permission.setSystemxlkgl(65535);
        permission.setXsbj(65535);
        permission.setXsd(65535);
        permission.setXsdd(65535);
        permission.setXssptj(65535);
        permission.setXsth(65535);
        IPermissionsService permissionsService = Main.getServiceManager().getPermissionsService();
        permissionsService.savePermission(permission);
    }
}
