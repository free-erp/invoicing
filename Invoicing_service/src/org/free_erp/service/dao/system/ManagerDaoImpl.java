/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.free_erp.service.dao.system;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.free_erp.service.entity.accounting.DailyReportPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.logic.sale.SaleQueryVo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author TengJianfa 13003311398
 */
public class ManagerDaoImpl extends HibernateDaoSupport implements IManagerDao {

    public List<DailyReportPo> getManagerForm(SaleQueryVo vo) {
        return this.findPurchaseForms(DailyReportPo.class, vo);
    }

    private List findPurchaseForms(Class clazz, SaleQueryVo vo) {

        String className = clazz.getName();
        String sql = "from " + className + " as form where";
        Vector objs = new Vector();
        if (vo.getStatus() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.operationType =? ";
            objs.add(vo.getStatus());
        }

        if (vo.getCompany() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.company=? ";
            objs.add(vo.getCompany());
        }
        if (vo.getStartDatePo() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.formDate>=? ";
            objs.add(vo.getStartDatePo());
        }
        if (vo.getEndDatePo() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.formDate <=? ";
            objs.add(vo.getEndDatePo());
        }

        if (vo.getProductNumber() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.number =? ";
            objs.add(vo.getProductNumber());
        }
        if (vo.getSupplier() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.customer=? ";
            objs.add(vo.getSupplier());
        }
        if (vo.getField() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " ORDER BY ? ";
            objs.add(vo.getField());
        }
        //debug:
        return this.getHibernateTemplate().find(sql, objs.toArray());
    }

    public DailyReportPo getDailyManagerForm(Company company, String num) {
        List<DailyReportPo> sd = this.getHibernateTemplate().find("from DailyReportPo as type where type.company =? and type.formNumber = ?", new Object[]{company, num});

        return sd.get(0);
    }

    public List<DailyReportPo> getManagerDailyForm(Company company, Date currentDate) {
        return (List<DailyReportPo>) this.getHibernateTemplate().find("from DailyReportPo as type where type.company =? and type.formDate =?", new Object[]{company, currentDate});
    }

    public List<DailyReportPo> getManagerDailyForm(Company company, Date beginDate, Date endDate) {
        return (List<DailyReportPo>) this.getHibernateTemplate().find("from DailyReportPo as type where type.company =? and type.formDate >=? and type.formDate <=? ", new Object[]{company, beginDate, endDate});
    }
}
