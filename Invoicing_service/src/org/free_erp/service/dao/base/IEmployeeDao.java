/**
 * IEmployeeDao.java erp Dec 26, 2008
 * This file is part of JZH project Created by TengJianfa
 * Copyrights Wuxi Jzh software Cop. All rights reserved!
 */
package org.free_erp.service.dao.base;

import java.util.List;

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

/**
 * @author TengJianfa
 *
 */
public interface IEmployeeDao
{
	public List<Employee> findEmployees(Company company);

    public List<Employee> findUsableEmployees(Company company);//liufei

	public List<EmployeeCatalog> findEmployeeCatalogs(Company company);

	public void deleteEmployee(Employee employee);

	public void deleteEmployeeCatalog(EmployeeCatalog catalog);

	public void saveEmployee(Employee employee);

	public void saveEmployeeCatalog(EmployeeCatalog catalog);

    public void insertEmployeeCatalog(Company company,String number,String name);//liufei

    public int getEmployeeCatalogID(Company company,String number);//liufei

    public EmployeeCatalog getEmployeeCatalog(Company company,String name);//liufei

    public EmployeeCatalog findEmployeeCatalog(int id);//liufei

    public List<Employee> findEmployees(Company company, String name);

    public boolean isExitEmployeeCatalog(Company company, String catalogNumber);//liufei

    public Employee getEmployee(int employeeID);

    public List<Employee> findEmployees(DataBaseQueryVO vo);
}
