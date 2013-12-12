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

/**
 *
 * @author afa
 */
public interface AccountingBaseDao {
    public List<AffordType> getAllAffordType(Company company);
    public List<ClearingType> getAllClearingType();
    //public List<SubjectCatalog> getAllSubjectCatalog(Company company);
    public List<SubjectCatalog> getAllSubjectCatalog();
    public List<BSubjectCatalog> getMainSubjectCatalog(Company company);
    public List<Subject> getAllSubject(Company company);
    public List<Subject> getSubject(Company company, SubjectCatalog catalog);
    public SubjectCatalog findSubjectCatalog(int id);  
    public Subject findSubject(int id);
    public List<SubjectDetail> findSubjectDetails(Company company);//liufei
    public List<SubjectAccount> getSubjectAccounts(Company company);//liufei
    public List<CustomerReceivementDetail> getCustomerReceivementDetails(Company company);//liufei
    public List<CustomerReceivableAccount> getCustomerReceivableAccounts(Company company);//liufei
    public List<CustomerPaymentDetail> getCustomerPaymentDetails(Company company);//liufei
    public List<CustomerPayableAccount> getCustomerPayableAccounts(Company company);//liufei
}
