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
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.base.TestPo;

public interface ICompanyService
{
	public Company getCompany(int id);
	
	public Company getReservedCompany();
	
	public List<Storage> getStorages(Company company);
	
	public void saveStorage(Storage storage);
	
	public void deleteStorage(Storage storage);

	public boolean saveCompany(Company company);

    public TestPo getTestPo();

    public void saveTestPo(TestPo po);
    
    public List<Integer> findCompanyIDs();//liufei

    public List<Company> findCompanys();//liufei
}