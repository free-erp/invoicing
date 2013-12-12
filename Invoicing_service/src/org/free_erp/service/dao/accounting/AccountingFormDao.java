/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.accounting;

import java.util.List;
import org.free_erp.service.entity.accounting.BankCash;
import org.free_erp.service.entity.accounting.BankCashDetail;
import org.free_erp.service.entity.accounting.BussinessExpense;
import org.free_erp.service.entity.accounting.BussinessExpenseDetail;
import org.free_erp.service.entity.accounting.CommonExpense;
import org.free_erp.service.entity.accounting.CommonExpenseDetail;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.CustomerReceivableAccount;
import org.free_erp.service.entity.accounting.InitialPayable;
import org.free_erp.service.entity.accounting.InitialReceivable;
import org.free_erp.service.entity.accounting.InitialSubject;
import org.free_erp.service.entity.accounting.InitialSubjectDetail;
import org.free_erp.service.entity.accounting.OtherIncome;
import org.free_erp.service.entity.accounting.OtherIncomeDetail;
import org.free_erp.service.entity.accounting.Payable;
import org.free_erp.service.entity.accounting.PayableClearing;
import org.free_erp.service.entity.accounting.PayableClearingDetail;
import org.free_erp.service.entity.accounting.Prepayable;
import org.free_erp.service.entity.accounting.Prereceivable;
import org.free_erp.service.entity.accounting.Receivable;
import org.free_erp.service.entity.accounting.ReceivableClearing;
import org.free_erp.service.entity.accounting.ReceivableClearingDetail;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectAccount;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.vo.AccountQueryVo;

/**
 *
 * @author afa
 */
public interface AccountingFormDao
{
    public List<BankCash> findAllBankCashForms(Company comp);
    public List<BussinessExpense> findAllBussinessExpenseForms(Company comp);
    public List<CommonExpense> findAllCommonExpenseForms(Company comp);
    public List<OtherIncome> findAllOtherIncomeForms(Company comp);
    public List<Prepayable> findAllPrepayableForms(Company comp);
    public List<Prereceivable> findAllPrereceivableForms(Company comp);
    public List<ReceivableClearing> findAllReceivableClearingForms(Company comp);
    public List<PayableClearing> findAllPayableClearingForms(Company comp);
    public List<InitialSubject> findAllInitialSubjectForms(Company comp);
    public List<InitialPayable> findAllInitialPayableForms(Company comp);
    public List<InitialReceivable> findAllInitialReceivableForms(Company comp);

    public List<?> find(String queryString , Object[] args);
    public List<?> find(String queryString);

    public SubjectAccount findSubjectAccount(Subject subject);
    public List<Payable> findAllPayables(Company company);
    public List<Receivable> findAllReceivable(Company company);
    public List<Payable> findPayables(Customer customer);
    public List<Receivable> findReceivable(Customer customer);
    public CustomerPayableAccount getCustomerPayableAccount(Customer customer);
    public CustomerReceivableAccount getCustomerReceivableAccount(Customer customer);
    //public SubjectAccount findAllSubjectAccount(Company subject);
    public List findAccountForms(Class clazz, AccountQueryVo vo);
    public double getCustomerPayableAccount(Company company);//liufei
    public double getCustomerReceivableAccount(Company company);
    public List<SubjectAccount> getSubjectAccount(Company company);
    public List<ReceivableClearingDetail> getReceivableClearingDetails(List<ReceivableClearing> pos);//liufei
    public List<PayableClearingDetail> getPayableClearingDetails(List<PayableClearing> pos);//liufei
    public List<InitialSubjectDetail> getInitialSubjectDetails(List<InitialSubject> pos);//liufei
    public List<BankCashDetail> getBankCashDetails(List<BankCash> pos);//liufei
    public List<OtherIncomeDetail> getOtherIncomeDetails(List<OtherIncome> pos);//liufei
    public List<CommonExpenseDetail> getCommonExpenseDetails(List<CommonExpense> pos);//liufei
    public List<BussinessExpenseDetail> getBussinessExpenseDetails(List<BussinessExpense> pos);//liufei
}
