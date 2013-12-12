/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.storage.MoveStoragePo;

/**
 *
 * @author Administrator
 */
public interface IStorageReportDao {
    public List<MoveStoragePo> findMoveStorage(Company company);

}
