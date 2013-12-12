/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.Date;
import java.util.List;
import org.free_erp.service.entity.accounting.DailyReportPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.logic.sale.SaleQueryVo;

/**
 *
 * @author TengJianfa 13003311398
 */
public interface IManagerDao {

    public List<DailyReportPo> getManagerForm(SaleQueryVo vo);
    public DailyReportPo getDailyManagerForm(Company company,String num);
    public List<DailyReportPo> getManagerDailyForm(Company company, Date currentDate);
    public List<DailyReportPo> getManagerDailyForm(Company company, Date beginDate , Date endDate);
}
