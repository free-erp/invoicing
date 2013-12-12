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

import java.util.Date;
import java.util.List;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.BSubjectCatalog;
import org.free_erp.service.entity.accounting.BankCash;
import org.free_erp.service.entity.accounting.BankCashDetail;
import org.free_erp.service.entity.accounting.BussinessExpense;
import org.free_erp.service.entity.accounting.BussinessExpenseDetail;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.accounting.CommonExpense;
import org.free_erp.service.entity.accounting.CommonExpenseDetail;
import org.free_erp.service.entity.accounting.CustomerPayableAccount;
import org.free_erp.service.entity.accounting.CustomerPaymentDetail;
import org.free_erp.service.entity.accounting.CustomerReceivableAccount;
import org.free_erp.service.entity.accounting.CustomerReceivementDetail;
import org.free_erp.service.entity.accounting.DailyReportPo;
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
import org.free_erp.service.entity.accounting.SubjectCatalog;
import org.free_erp.service.entity.accounting.SubjectDetail;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.purchase.PurchaseBackPo;
import org.free_erp.service.entity.purchase.PurchaseOrderPo;
import org.free_erp.service.entity.sale.SaleOrderBackPo;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.entity.vo.AccountQueryVo;
import org.free_erp.service.entity.vo.CashTotalMoneyDetailsVO;

/**
 *
 * @author afa
 */
public interface IAccountingService
{
    public void saveDailyReportPo(DailyReportPo po);
    public void saveAffordType(AffordType affordType);
    public void saveClearingType(ClearingType clearingType);
    public void saveBSubjectCatalog(BSubjectCatalog bSubjectCatalog);
    public void saveSubjectCatalog(SubjectCatalog subjectCatalog);
    public List<AffordType> getAllAffordType(Company company);
    public List<ClearingType> getAllClearingType();
    public List<SubjectCatalog> getAllSubjectCatalog();
    public List<BSubjectCatalog> getMainSubjectCatalog(Company company);
    public List<Subject> getAllSubject(Company company);
    public List<Subject> getBussinessExpenseSubject(Company company);
    public List<Subject> getOtherIncomeSubject(Company company);
    public List<Subject> getCommonExpenseSubject(Company company);
    public List<Subject> getBankCashSubject(Company company);

    public CustomerPayableAccount getCustomerPayableAccount(Customer customer);
    public CustomerReceivableAccount getCustomerReceivableAccount(Customer customer);

    public void saveSubjectdetail(SubjectDetail subjectDetail);
    public void deleteSubjectdetail(SubjectDetail subjectDetail);
    
    public void saveSubject(Subject subject);
    public void deleteSubject(Subject subject);
    public Subject getSubject(int id);
    public void saveBankCashForm(BankCash po);
    public void saveBussinessExpenseForm(BussinessExpense po);
    public void saveCommonExpenseForm(CommonExpense po);
    public void saveOtherIncomeForm(OtherIncome po);
    public void savePayable(Payable po);
    public void saveRereceivable(Receivable po);
    public void savePrepayableForm(Prepayable po);
    public void savePrereceivableForm(Prereceivable po);
    public void saveReceivableClearingForm(ReceivableClearing po);
    public void savePayableClearingForm(PayableClearing po);
    public void deleteBankCashForm(BankCash po);
    public void deleteBussinessExpenseForm(BussinessExpense po);
    public void deleteCommonExpenseForm(CommonExpense po);
    public void deleteOtherIncomeForm(OtherIncome po);
    public void deletePrepayableForm(Prepayable po);
    public void deletePrereceivableForm(Prereceivable po);
    public void deleteReceivableClearingForm(ReceivableClearing po);
    public void deletePayableClearingForm(PayableClearing po);

    public void passBankCashForm(BankCash po);
    public void passBussinessExpenseForm(BussinessExpense po);
    public void passCommonExpenseForm(CommonExpense po);
    public void passOtherIncomeForm(OtherIncome po);
    public void passPrepayableForm(Prepayable po);
    public void passPrereceivableForm(Prereceivable po);
    public void passReceivableClearingForm(ReceivableClearing po);
    public void passPayableClearingForm(PayableClearing po);
    public void passInitialSubjectForm(InitialSubject po);
    public void passInitialPayableForm(InitialPayable po);
    public void passInitialReceivableForm(InitialReceivable po);
    public void passAccountingForm(Object po);
    
    public void discardBankCashForm(BankCash po);
    public void discardBussinessExpenseForm(BussinessExpense po);
    public void discardCommonExpenseForm(CommonExpense po);
    public void discardOtherIncomeForm(OtherIncome po);
    public void discardPrepayableForm(Prepayable po);
    public void discardPrereceivableForm(Prereceivable po);
    public void discardReceivableClearingForm(ReceivableClearing po);
    public void discardPayableClearingForm(PayableClearing po);
    public void discardInitialSubjectForm(InitialSubject po);
    public void discardInitialPayableForm(InitialPayable po);
    public void discardInitialReceivableForm(InitialReceivable po);
    public void discardAccountingForm(Object po);

    



    public List<BankCash> getAllBankCashForms(Company comp);
    public List<BussinessExpense> getAllBussinessExpenseForms(Company comp);
    public List<CommonExpense> getAllCommonExpenseForms(Company comp);
    public List<OtherIncome> getAllOtherIncomeForms(Company comp);
    public List<Prepayable> getAllPrepayableForms(Company comp);
    public List<Prereceivable> getAllPrereceivableForms(Company comp);
    public List<ReceivableClearing> getAllReceivableClearingForms(Company comp);
    public List<PayableClearing> getAllPayableClearingForms(Company comp);

    public void saveInitialSubjectForm(InitialSubject po);
    public void saveInitialPayableForm(InitialPayable po);
    public void saveInitialReceivableForm(InitialReceivable po);
    
    public void saveCustomerPayableAccount(CustomerPayableAccount po);
    public void saveCustomerReceivableAccount(CustomerReceivableAccount po);
    public void saveSubjectAccount(SubjectAccount po);
    public SubjectAccount findSubjectAccount(Subject subject);

    public List<InitialSubject> findAllInitialSubjectForms(Company comp);
    public List<InitialPayable> findAllInitialPayableForms(Company comp);
    public List<InitialReceivable> findAllInitialReceivableForms(Company comp);

    public List<Payable> findPayables(Customer customer);
    public List<Receivable> findReceivable(Customer customer);
    public List<Payable> findAllPayables(Company company);
    public List<Receivable> findAllReceivable(Company company);
    public List<Receivable> findReceivable(Date startDate, Date endDate, Customer customer, Employee employee, Company company);
    public List<Payable> findPayable(Date startDate, Date endDate, Customer customer, Employee employee, Company company);


    public void deleteInitialSubjectForm(InitialSubject po);
    public void deleteInitialPayableForm(InitialPayable po);
    public void deleteInitialReceivableForm(InitialReceivable po);

    public void addPurchasePayable(PurchaseOrderPo po);
    public void addPurchaseBackReceivable(PurchaseBackPo po);

    public void addSaleReceivable(SalePo po);
    public void addSaleBackPayable(SaleOrderBackPo po);

    public double getSubjectAccountMoney(Subject subject);

    public double getCustomerPayableAccountMoney(Customer customer);

    public double getCustomerReceivableAccountMoney(Customer customer);

    //report:
    public List<SubjectDetail> findSubjectDetails(Date startDate, Date endDate, Subject subject, Company company, int subjectType);
    public List<CustomerPaymentDetail> findCustomerPaymentDetails(Date startDate, Date endDate, Customer customer, Company company);
    public List<CustomerReceivementDetail> findCustomerReceivementDetails(Date startDate, Date endDate, Customer customer, Company company);
    //********liufei********
    public List<InitialSubject> findInitialSubjectForms(AccountQueryVo vo);
    public List<InitialPayable> findInitialPayableForms(AccountQueryVo vo);
    public List<InitialReceivable> findInitialReceivableForms(AccountQueryVo vo);

    public List<PayableClearing> findPayableClearingForms(AccountQueryVo vo);
    public List<ReceivableClearing> findReceivableClearingForms(AccountQueryVo vo);

    public List<Prereceivable> findPrereceivableForms(AccountQueryVo vo);
    public List<Prepayable> findPrepayableForms(AccountQueryVo vo);
    
    public List<BussinessExpense> findBussinessExpenseForms(AccountQueryVo vo);
    public List<CommonExpense> findCommonExpenseForms(AccountQueryVo vo);
    public List<OtherIncome> findOtherIncomeForms(AccountQueryVo vo);
    public List<BankCash> findBankCashForms(AccountQueryVo vo);
    //***********************
    public CashTotalMoneyDetailsVO getCashTotalMoney(Company company);
    public List<SubjectDetail> findSubjectDetails(Company company);//liufei
    public List<CustomerReceivementDetail> getCustomerReceivementDetails(Company company);//liufei
    public List<CustomerReceivableAccount> getCustomerReceivableAccounts(Company company);//liufei
    public List<CustomerPaymentDetail> getCustomerPaymentDetails(Company company);//liufei
    public List<CustomerPayableAccount> getCustomerPayableAccounts(Company company);//liufei
    public List<ReceivableClearingDetail> getReceivableClearingDetails(List<ReceivableClearing> pos);//liufei
    public List<PayableClearingDetail> getPayableClearingDetails(List<PayableClearing> pos);//liufei
    public List<InitialSubjectDetail> getInitialSubjectDetails(List<InitialSubject> pos);//liufei
    public List<BankCashDetail> getBankCashDetails(List<BankCash> pos);//liufei
    public List<OtherIncomeDetail> getOtherIncomeDetails(List<OtherIncome> pos);//liufei
    public List<CommonExpenseDetail> getCommonExpenseDetails(List<CommonExpense> pos);//liufei
    public List<BussinessExpenseDetail> getBussinessExpenseDetails(List<BussinessExpense> pos);//liufei
    public List<SubjectAccount> getSubjectAccounts(Company company);//liufei
}
