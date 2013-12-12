package org.free_erp.service.dao.system;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.ArrayList;
import java.util.Vector;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.CreateNumber;
import org.free_erp.service.entity.system.NumberPrefix;
import org.free_erp.service.entity.system.PriceName;
import org.free_erp.service.entity.vo.SystemLogQueryVo;
/**
 *
 * Changer liufei
 */
public class SystemManageDaoImpl extends HibernateDaoSupport  implements ISystemManageDao,java.io.Serializable
{
	public User findUser(Company company, String userName)
	{
		// TODO Auto-generated method stub
		List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company=? and user.userName=?", new Object[]{company, userName});
		if (users == null || users.size() == 0)
		{
			return null;
		}
		return users.get(0);
	}

	public void deleteUser(User user)
	{
		// TODO Auto-generated method stub

	}

	public Company findCompany(int companyId)
	{
		return (Company)this.getHibernateTemplate().get(Company.class, companyId);
	}

	public User findUser(int userId)
	{
		// TODO Auto-generated method stub
		return (User)this.getHibernateTemplate().get(User.class, userId);
	}

	public boolean isValidUser(Company company, String name, String password)
	{
		// TODO Auto-generated method stub
        if(company == null)
        {
            return false;
        }
		List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company=? and user.userName=? and user.password=?", new Object[]{company, name, password});
		if (users == null || users.size() == 0)
		{
			return false;
		}
		return true;
	}

    public boolean isExistedUser(int companyId, String name)//liufei
    {
        List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company.id=? and user.userName=? ", new Object[]{companyId, name});
		if (users == null || users.size() == 0)
		{
			return false;
		}
		return true;
    }

	public void saveUser(User user)
	{
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(user);
	}

    public void saveSystemLog(SystemLog systemLog)//liufei
    {
        this.getHibernateTemplate().save(systemLog);
    }

    public void deleteSystemLogs(Company company)//liufei
    {
        List<SystemLog> systemLogs = this.getHibernateTemplate().find("from SystemLog where company=? ",new Object[]{company});
        for(SystemLog systemLog : systemLogs)
        {
            this.getHibernateTemplate().delete(systemLog);
        }
    }

    public List<SystemLog> findLatestLogs(int id) {
        if (id  < 0)
        {
            //Date date = new Date();
            //Timestamp date = new Timestamp(System.currentTimeMillis() - 2000);//temp
            List<SystemLog> logs = this.getHibernateTemplate().find("from SystemLog as systemLog order by systemLog.id desc");
            if (logs != null && logs.size() > 0)
            {

                List<SystemLog> l = new ArrayList<SystemLog>();
                l.add(logs.get(0));
                return l;
            }
            return null;
        }
        else
        {
            return this.getHibernateTemplate().find("from SystemLog as systemLog where systemLog.id > ?", new Object[]{id});
         }
     }


    public void upDateUser(User user)
	{
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	public boolean isValidUser(int companyId, String name, String password)
	{
		// TODO Auto-generated method stub
		Company company = (Company)this.getHibernateTemplate().get(Company.class, companyId);
		if (company == null)
		{
			return false;
		}
		return this.isValidUser(company, name, password);
	}

    public List<User> findAllUser(Company company)
    {
        return (List<User>)this.getHibernateTemplate().find("from User as user where user.company =? order by user.number ", new Object[]{company});
    }

    public List<SystemLog> findSystemLogs(Company company)//liufei
    {
        return (List<SystemLog>)this.getHibernateTemplate().find("from SystemLog as s where s.company =? ", new Object[]{company});
    }

    private List findSystemLogForms(Class clazz, SystemLogQueryVo vo)//liufei
	{
		String className = clazz.getName();
		String sql = "from " + className + " as form where";
		Vector objs = new Vector();
        if (vo.getCompany() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.company=? ";
			objs.add(vo.getCompany());
		}

        if (vo.getStartDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate>=? ";//liufei change
			objs.add(vo.getStartDate());
		}
		if (vo.getEndDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate <=? ";//liufei change
			objs.add(vo.getEndDate());
		}
        if (vo.getUserName() != null && !vo.getUserName().equals(""))
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.user.name =? ";//liufei change
			objs.add(vo.getUserName());
		}
		if (vo.getNumber() != null && !vo.getNumber().equals(""))
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.user.number =? ";//liufei change
			objs.add(vo.getNumber());
		}
		return this.getHibernateTemplate().find(sql, objs.toArray());
    }

    public List<SystemLog> findSystemLogs(SystemLogQueryVo vo)//liufei
    {
        return this.findSystemLogForms(SystemLog.class, vo);
    }

    public void saveNumberPrefix(NumberPrefix numberPrefix)//liufei
    {
        this.getHibernateTemplate().saveOrUpdate(numberPrefix);
    }

    public NumberPrefix getNumberPrefix(Company company)//liufei
    {
        List<NumberPrefix> numberPrefixs = this.getNumberPrefixs(company);
        if(numberPrefixs != null && numberPrefixs.size() > 0)
        {
            return numberPrefixs.get(0);
        }
        else
        {
            return null;
        }
    }

    public List<NumberPrefix> getNumberPrefixs(Company company)//liufei
    {
        return (List<NumberPrefix>) this.getHibernateTemplate().find("from NumberPrefix  where company=? ", new Object[]{company});
    }

    public void saveAutoNumber(CreateNumber createNumber)//liufei
    {
        this.getHibernateTemplate().saveOrUpdate(createNumber);
    }

    public CreateNumber getAutoNumber(Company company)//liufei
    {
        List<CreateNumber> createNumbers = this.getAutoNumbers(company);
        if(createNumbers != null && createNumbers.size() > 0)
        {
            return createNumbers.get(0);
        }
        else
        {
            return null;
        }
    }

    public List<CreateNumber> getAutoNumbers(Company company)//liufei
    {
        return (List<CreateNumber>)this.getHibernateTemplate().find("from CreateNumber where company=? ", new Object[]{company});
    }

    public PriceName getPriceName(Company company)
    {
        List<PriceName> priceNames = this.getPriceNames(company);
        if(priceNames != null && priceNames.size() > 0)
        {
            return priceNames.get(0);
        }
        else
        {
            return null;
        }
    }

    public List<PriceName> getPriceNames(Company company)
    {
        return (List<PriceName>)this.getHibernateTemplate().find("from PriceName  where company=? ", new Object[]{company});
    }
}
