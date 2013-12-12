/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Role;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.Permission;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Administrator
 */
public class PermissionsDaoImpl extends HibernateDaoSupport  implements IPermissionsDao{

  
   public List<User> findUser(Company company)
	{
		// TODO Auto-generated method stub
		List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company=?", new Object[]{company});
		if (users == null || users.size() == 0)
		{
			return null;
		}
		return users;
	}

    public void savePermission(Role role)
    {
       this.getHibernateTemplate().saveOrUpdate(role);
       
    }

    public List<Role> findRole(Company company, String roleName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveUser(User user) {
        this.getHibernateTemplate().saveOrUpdate(user);
    }

    public List<Role> findRole(Company company) {
      	List<Role> role = (List<Role>)this.getHibernateTemplate().find("from Role as role where role.company=?", new Object[]{company});
		if (role == null || role.size() == 0)
		{
			return null;
		}
		return role;
    }

    public List<User> findUser(Company company, String name) {
 
        return (List<User>)this.getHibernateTemplate().find("from User as user where user.company=? and user.userName=?", new Object[]{company,name});
		
    }

    public void deleteUser(User user) {
        this.getHibernateTemplate().delete(user);
    }

    public List<Permission> findPermission(Company company, User user) {
		//return (List<Permission>)this.getHibernateTemplate().find("from Permission as Permission where Permission.company=? and user_id=?", new Object[]{company,id});

        return (List<Permission>)this.getHibernateTemplate().find("from Permission as permission where permission.user=?", new Object[]{user});

    }

    public List<Permission> getPermissions(Company company)//liufei
    {
        List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company=? ", new Object[]{company});
        List<Permission> permissions = new ArrayList();
        for(User user : users)
        {
            List<Permission> ps = (List<Permission>)this.getHibernateTemplate().find("from Permission as permission where permission.user=?", new Object[]{user});
            for(Permission p : ps)
            {
                permissions.add(p);
            }
        }
        return permissions;
    }
    public void savePermission(Permission permission) {
        this.getHibernateTemplate().saveOrUpdate(permission);
    }

    public void deletePermission(Permission permission) {
        this.getHibernateTemplate().delete(permission);
    }

    public int findUsertNumber(Company company) {
      int number=101;
        List<User> users = (List<User>)this.getHibernateTemplate().find("from User as user where user.company=? order by user.number desc ", new Object[]{company});
       if(users.size()>0)
       {
          number = Integer.valueOf(users.get(0).getNumber())+1;

       }
        return number;
    }
}
