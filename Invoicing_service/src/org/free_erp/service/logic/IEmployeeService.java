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
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.EmployeeCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

/**
 * @author TengJianfa
 *
 */
public interface IEmployeeService
{
	public List<Employee> getEmployees(Company company);

    public List<Employee> getUsableEmployees(Company company);//liufei

    public List<String> getStringUsableEmployees(Company company);//test

	public List<EmployeeCatalog> getCatalogs(Company company);

	public void deleteEmployee(Employee employee);

	public void deleteCatalog(EmployeeCatalog catalog);

	public void saveEmployee(Employee employee);

	public void saveCatalog(EmployeeCatalog catalog);

    public EmployeeCatalog saveEmployeeCatalog(EmployeeCatalog catalog);//liufei

    public void insertEmployeeCatalog(Company company,String number,String name);//liufei

    public int getEmployeeCatalogID(Company company,String number);//liufei

    public EmployeeCatalog getEmployeeCatalog(Company company,String name);//liufei

    public EmployeeCatalog findEmployeeCatalog(int id);//liufei

    public Employee getEmployee(Company company, String name);

    public boolean isExitEmployeeCatalog(Company company, String catalogNumber);//liufei

    public Employee getEmployee(int employeeID);

    public List<Employee> findEmployees(DataBaseQueryVO vo);
}
