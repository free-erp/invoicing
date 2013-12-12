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
import org.free_erp.service.dao.system.IPermissionsDao;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Role;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.Permission;

/**
 *
 * @author Administrator
 */
public class PermissionsServiceImpl implements IPermissionsService {

    private IPermissionsDao dao;

    public IPermissionsDao getDao() {
        return dao;
    }

    public void setDao(IPermissionsDao dao) {
        this.dao = dao;
    }

    public void savePermission(Role role) {
        this.dao.savePermission(role);
    }


    public List<Role> getRole(Company company, String roleName) {
        return this.dao.findRole(company, roleName);
    }

    public void saveUser(User user) {
        this.dao.saveUser(user);
    }

    public List<User> getUser(Company company) {
        return this.dao.findUser(company);
    }

    public void removeUser(User user) {
        Permission permission = new Permission();
        //List<Permission> list = getPermission(user.getCompany(), user.getId());
        permission = this.getPermission(user.getCompany(), user);//list.get(0);
        this.dao.deletePermission(permission);
        this.dao.deleteUser(user);
    }

    public List<Role> getRole(Company company) {
        return this.dao.findRole(company);
    }

    public List<User> getName(Company company, String name) {

        return this.dao.findUser(company, name);

    }

    public Permission getPermission(Company company,  User user) {
        List<Permission> permissions = this.dao.findPermission(company, user);
        if (permissions != null && permissions.size() > 0)
        {
            return permissions.get(0);
        }
        return null;
    }

    public List<Permission> getPermissions(Company company)//liufei
    {
        return this.dao.getPermissions(company);
    }

    public void savePermission(Permission permission) {
        this.dao.savePermission(permission);
    }

    public void removePermission(Permission permission) {
        this.dao.deletePermission(permission);
    }

    public int getUsertNumber(Company company) {
        return this.dao.findUsertNumber(company);
    }
}
