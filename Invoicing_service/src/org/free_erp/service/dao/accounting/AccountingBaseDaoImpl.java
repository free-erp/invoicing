/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.accounting;

import java.util.List;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.BSubjectCatalog;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.CustomerPaymentDetail;
import org.free_erp.service.entity.accounting.CustomerReceivableAccount;
import org.free_erp.service.entity.accounting.CustomerReceivementDetail;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectAccount;
import org.free_erp.service.entity.accounting.SubjectCatalog;
import org.free_erp.service.entity.accounting.SubjectDetail;
import org.free_erp.service.entity.base.Company;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author afa
 */
public class AccountingBaseDaoImpl extends HibernateDaoSupport implements AccountingBaseDao{

    public void deleteSubject(Subject subject) {
        this.getHibernateTemplate().delete(subject);
    }

    public Subject findSubject(int id) {
        return (Subject)this.getHibernateTemplate().get(Subject.class, id);
    }

    public SubjectCatalog findSubjectCatalog(int id)
    {
        return (SubjectCatalog)this.getHibernateTemplate().get(SubjectCatalog.class, id);
    }

    public List<AffordType> getAllAffordType(Company company) {
        return (List<AffordType>) this.getHibernateTemplate().find("from AffordType as type where type.company =?", new Object[]{company});
    }

    public List<ClearingType> getAllClearingType() {
        return (List<ClearingType>) this.getHibernateTemplate().find("from ClearingType");
    }

    public List<Subject> getAllSubject(Company company) {
            return (List<Subject>) this.getHibernateTemplate().find("from Subject as po where po.company =?", new Object[]{company});
    }

//    public List<SubjectCatalog> getAllSubjectCatalog(Company company) {
//             return (List<SubjectCatalog>) this.getHibernateTemplate().find("from SubjectCatalog as po where po.company =?", new Object[]{company});
//    }

    public List<SubjectCatalog> getAllSubjectCatalog() {
             return (List<SubjectCatalog>) this.getHibernateTemplate().find("from SubjectCatalog");
    }

    public List<BSubjectCatalog> getMainSubjectCatalog(Company company) {
        return (List<BSubjectCatalog>) this.getHibernateTemplate().find("from BSubjectCatalog ");

    }

    public List<Subject> getSubject(Company company, SubjectCatalog catalog) {
        return (List<Subject>) this.getHibernateTemplate().find("from Subject as po where po.company =? and po.catalog =?", new Object[]{company, catalog});

    }

    public List<SubjectDetail> findSubjectDetails(Company company)//liufei
    {
        return (List<SubjectDetail>)this.getHibernateTemplate().find("from SubjectDetail as po where po.company =?", new Object[]{company});
    }

    public List<CustomerReceivementDetail> getCustomerReceivementDetails(Company company)
    {
        return (List<CustomerReceivementDetail>)this.getHibernateTemplate().find("from CustomerReceivementDetail as po where po.company =?", new Object[]{company});
    }

    public List<CustomerReceivableAccount> getCustomerReceivableAccounts(Company company)
    {
        return (List<CustomerReceivableAccount>)this.getHibernateTemplate().find("from CustomerReceivableAccount as po where po.company =?", new Object[]{company});
    }

    public List<CustomerPaymentDetail> getCustomerPaymentDetails(Company company)
    {
        return (List<CustomerPaymentDetail>)this.getHibernateTemplate().find("from CustomerPaymentDetail as po where po.company =?", new Object[]{company});
    }

    public List<CustomerPayableAccount> getCustomerPayableAccounts(Company company)
    {
        return (List<CustomerPayableAccount>)this.getHibernateTemplate().find("from CustomerPayableAccount as po where po.company =?", new Object[]{company});
    }

    public List<SubjectAccount> getSubjectAccounts(Company company)
    {
        return (List<SubjectAccount>)this.getHibernateTemplate().find("from SubjectAccount as po where po.company =?", new Object[]{company});
    }
}
