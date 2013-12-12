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

import java.util.ArrayList;
import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.dao.base.IEmployeeDao;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.exception.LogicException;

public class EmployeeServiceImpl implements IEmployeeService
{
	private IEmployeeDao dao;
	private ICompanyService companyService;
    private ISystemManageService systemManageService;

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

	public ICompanyService getCompanyService()
	{
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService)
	{
		this.companyService = companyService;
	}

	public List<EmployeeCatalog> getCatalogs(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findEmployeeCatalogs(company);
	}


	public List<Employee> getEmployees(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findEmployees(company);
	}

    public List<Employee> getUsableEmployees(Company company)//liufei
    {
        return this.dao.findUsableEmployees(company);
    }

	public IEmployeeDao getDao()
	{
		return dao;
	}

	public void setDao(IEmployeeDao dao)
	{
		this.dao = dao;
	}


	public void deleteCatalog(EmployeeCatalog catalog)
	{
		// TODO Auto-generated method stub
		this.dao.deleteEmployeeCatalog(catalog);
	}


	public void deleteEmployee(Employee employee)
	{
		try
        {
            this.dao.deleteEmployee(employee);
        }
        catch(Exception ex)
        {
            throw new LogicException("该员工存在于其它单据中，不能被删除!");
        }
	}

	public void saveCatalog(EmployeeCatalog catalog)
	{
		// TODO Auto-generated method stub
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.EMP_CATALOG_NUM)+systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.EMP_CATALOG_NUM);
            catalog.setNumber(number);
        }
		this.dao.saveEmployeeCatalog(catalog);
	}

    public EmployeeCatalog saveEmployeeCatalog(EmployeeCatalog catalog)//liufei
    {
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.EMP_CATALOG_NUM)+systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.EMP_CATALOG_NUM);
            catalog.setNumber(number);
        }
		this.dao.saveEmployeeCatalog(catalog);
        return catalog;
    }

    public void insertEmployeeCatalog(Company company,String number,String name)//liufei
    {
        this.dao.insertEmployeeCatalog(company, number, name);
    }

	public void saveCatalog(int companyId, EmployeeCatalog catalog)
	{
		// TODO Auto-generated method stub
		if (catalog.getCompany() == null)
		{
			catalog.setCompany(this.companyService.getCompany(companyId));
		}
		this.saveCatalog(catalog);
	}

	public void saveEmployee(Employee employee)
	{
		// TODO Auto-generated method stub
		if (employee.getCompany() == null)
		{
			throw new RuntimeException("web service中请使用带companyId的方法");
		}
//        if(!this.isExitEmployeeCatalog(employee.getCompany(), employee.getCatalog().getNumber()))
//        {
//            throw new LogicException("该类别不存在或已被删除，请先刷新基础信息，再重试！");
//        }
        if(employee.getNumber() == null || employee.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(employee.getCompany(), NumberConstants.EMP_NUM)+systemManageService.getAutoNumber(employee.getCompany(), NumberConstants.EMP_NUM);
            employee.setNumber(number);
        }
		this.dao.saveEmployee(employee);
	}

	public void saveEmployee(int companyId, Employee employee)
	{
		if (employee.getCompany() == null)
		{
			employee.setCompany(this.companyService.getCompany(companyId));
		}
		this.saveEmployee(employee);
	}

    public int getEmployeeCatalogID(Company company,String number)//liufei
    {
        return this.dao.getEmployeeCatalogID(company,number);
    }

    public EmployeeCatalog getEmployeeCatalog(Company company,String name)//liufei
    {
        return this.dao.getEmployeeCatalog(company, name);
    }

    public EmployeeCatalog findEmployeeCatalog(int id)//liufei
    {
        return this.dao.findEmployeeCatalog(id);
    }

    public Employee getEmployee(Company company, String name)
    {
        List<Employee> employees = this.dao.findEmployees(company, name);
        if (employees.size() > 0) //只取第一个同名的人
        {
            return employees.get(0);
        }
        return null;
    }

    public boolean isExitEmployeeCatalog(Company company, String catalogNumber)//liufei
    {
        return this.dao.isExitEmployeeCatalog(company, catalogNumber);
    }

    public List<String> getStringUsableEmployees(Company company)
    {
        List<Employee> employees = this.dao.findUsableEmployees(company) ;
        List<String> list = new ArrayList();
        for(Employee employee : employees)
        {
            list.add(employee.getName());
        }
        return list;
    }

    public Employee getEmployee(int employeeID)
    {
        return this.dao.getEmployee(employeeID);
    }

    public List<Employee> findEmployees(DataBaseQueryVO vo)
    {
        return this.dao.findEmployees(vo);
    }
}
