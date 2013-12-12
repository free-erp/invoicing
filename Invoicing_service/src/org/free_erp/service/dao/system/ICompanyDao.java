package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.TestPo;

public interface ICompanyDao
{
	public Company findCompany(int id);
	
	public void saveCompany(Company company);

    public List<Company> findCompanys();//liufei

    public TestPo findTestPo(int id);

    public void saveTestPo(TestPo po);
}
