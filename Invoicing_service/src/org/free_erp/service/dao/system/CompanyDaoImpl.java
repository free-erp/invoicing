package org.free_erp.service.dao.system;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.TestPo;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompanyDaoImpl extends HibernateDaoSupport  implements ICompanyDao
{

    public TestPo findTestPo(int id) {
        return (TestPo)this.getHibernateTemplate().get(TestPo.class, new Integer(id));
    }

    public void saveTestPo(TestPo po)
    {
        this.getHibernateTemplate().saveOrUpdate(po);
    }


	public Company findCompany(int id)
	{
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        Company company = (Company)session.get(Company.class, new Integer(id));
        try
        {
            company.createImage();
        }
        catch(Exception ex)
        {
            
        }
        t.commit();
        return company;
		//return (Company)this.getHibernateTemplate().get(Company.class, id);
	}
	public void saveCompany(Company company)
	{
        this.getHibernateTemplate().saveOrUpdate(company);
        
	}

    public List<Company> findCompanys()//liufei
    {
        return (List<Company>)this.getHibernateTemplate().find("from Company");
    }
}
