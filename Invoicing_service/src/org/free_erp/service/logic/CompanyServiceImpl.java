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
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.base.IStorageDao;
import org.free_erp.service.dao.system.ICompanyDao;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.base.TestPo;

public class CompanyServiceImpl implements ICompanyService
{
	private ICompanyDao dao;
	
	private IStorageDao storageDao;

    public TestPo getTestPo() {
        return dao.findTestPo(1);
    }

    public void saveTestPo(TestPo po) {
        dao.saveTestPo(po);
    }


	
      public boolean saveCompany(Company company)
      {
        this.dao.saveCompany(company);
	return true;
      }
      
	public Company getCompany(int id)
	{
		// TODO Auto-generated method stub
		return this.dao.findCompany(id);
	}

	public Company getReservedCompany()
	{
		// TODO Auto-generated method stub
		Company company = this.dao.findCompany(ServiceConstants.RESERVED_COMPANY);
		if (company == null)
		{
			company = new Company();
			company.setId(ServiceConstants.RESERVED_COMPANY);
			company.setName("系统保留的公司,作为某些缺省项目的默认公司");
			this.dao.saveCompany(company);
			return company;
		}
		return company;
	}

	public ICompanyDao getDao()
	{
		return dao;
	}

	public void setDao(ICompanyDao dao)
	{
		this.dao = dao;
	}

	public IStorageDao getStorageDao()
	{
		return storageDao;
	}

	public void setStorageDao(IStorageDao storageDao)
	{
		this.storageDao = storageDao;
	}
	
	public List<Storage> getStorages(Company company)
	{
		return this.storageDao.findStorages(company);
	}
	
	public void saveStorage(Storage storage)
	{
		this.storageDao.saveStorage(storage);
	}
	
	public void deleteStorage(Storage storage)
	{
		this.storageDao.deleteStorage(storage);
	}

    public List<Integer> findCompanyIDs()//liufei
    {
        List<Company> company = this.dao.findCompanys();
        List<Integer> list = new ArrayList();
        if(company != null && company.size() > 0)
        {
            for(int i=0;i<company.size();i++)
            {
                list.add(company.get(i).getId());
            }
            return list;
        }
        return list;
    }

    public List<Company> findCompanys()//liufei
    {
        return this.dao.findCompanys();
    }
}
