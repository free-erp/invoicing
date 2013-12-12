/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.base;

import java.util.List;
import org.hibernate.Transaction;

/**
 *
 * @author afa
 */
public interface DaoUtilities
{
    public Transaction beginDaoTransaction();
    public void saveObject(Object po);
    public void deleteObject(Object po);
    public Object findObject(Class<?> clz, int id);
    public List<?> findObjects(String sql, Object[] params);
    public List<?> find(String queryString , Object[] args);
    public List<?> find(String queryString);
}
