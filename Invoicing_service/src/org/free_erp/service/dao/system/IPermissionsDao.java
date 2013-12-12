/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Role;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.Permission;

/**
 *
 * @author Administrator
 */
public interface IPermissionsDao {

    public List<User> findUser(Company company);
    public void savePermission(Role  role);
    public List<Role> findRole(Company company,String roleName);
    public void saveUser(User user);
    public List<Role> findRole(Company company);
     public List<User> findUser(Company company,String name);
     public void deleteUser(User user);
     /*********/
     public List<Permission> findPermission(Company company,  User user);
     public List<Permission> getPermissions(Company company);//liufei
     public void savePermission(Permission permission);
      public void deletePermission(Permission permission);
      public int findUsertNumber(Company company);
}
