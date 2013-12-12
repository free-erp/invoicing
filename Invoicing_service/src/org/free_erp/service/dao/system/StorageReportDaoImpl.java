/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.storage.MoveStoragePo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Administrator
 */
public class StorageReportDaoImpl extends HibernateDaoSupport implements IStorageReportDao{
    public List<MoveStoragePo> findMoveStorage(Company company){
        return (List<MoveStoragePo>)this.getHibernateTemplate().find("from MoveStoragePo as move where move.company=?", new Object[]{company});
    }

}
