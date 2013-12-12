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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.accounting.AccountingBaseDao;
import org.free_erp.service.dao.accounting.AccountingFormDao;
import org.free_erp.service.dao.base.DaoUtilities;
import org.free_erp.service.dao.system.IManagerDao;
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
import org.free_erp.service.entity.accounting.IAffordForm;
import org.free_erp.service.entity.accounting.IReceiveForm;
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
import org.free_erp.service.exception.LogicException;
import org.hibernate.Transaction;

/**
 *
 * @author afa
 */
public class AccountingServiceImpl implements IAccountingService{
    private AccountingBaseDao baseDao;
    private AccountingFormDao formDao;
    private DaoUtilities utilities;
    private ISystemManageService systemManageService;
    private IManagerDao managerDao;

    public IManagerDao getManagerDao() {
        return managerDao;
    }

    public void setManagerDao(IManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

    public DaoUtilities getUtilities() {
        return utilities;
    }

    public void setUtilities(DaoUtilities utilities) {
        this.utilities = utilities;
    }

    public AccountingBaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(AccountingBaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public AccountingFormDao getFormDao() {
        return formDao;
    }

    public void setFormDao(AccountingFormDao formDao) {
        this.formDao = formDao;
    }

    public void deleteSubject(Subject subject) {
        try
        {
            this.utilities.deleteObject(subject);
        }
        catch(Exception ex)
        {
            throw new LogicException("该科目存在于其它单据中，不能被删除!");
        }
    }

    public void saveAffordType(AffordType affordType)
    {
        this.utilities.saveObject(affordType);
    }

    public void saveClearingType(ClearingType clearingType)
    {
        this.utilities.saveObject(clearingType);
    }

    public void saveBSubjectCatalog(BSubjectCatalog bSubjectCatalog)
    {
        this.utilities.saveObject(bSubjectCatalog);
    }

    public void saveSubjectCatalog(SubjectCatalog subjectCatalog)
    {
        this.utilities.saveObject(subjectCatalog);
    }

    public List<AffordType> getAllAffordType(Company company) {
        return baseDao.getAllAffordType(company);
    }

    public List<ClearingType> getAllClearingType() {
        return baseDao.getAllClearingType();
    }

    public List<Subject> getAllSubject(Company company) {
        return baseDao.getAllSubject(company);
    }

    public List<SubjectCatalog> getAllSubjectCatalog() {
        return baseDao.getAllSubjectCatalog();
    }

    public SubjectCatalog getSubjectCatalog(int id)
    {
        return baseDao.findSubjectCatalog(id);
    }

    public List<Subject> getBankCashSubject(Company company) {
        SubjectCatalog bankCashCatalog = this.getSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_BANK_CASH);
        return baseDao.getSubject(company, bankCashCatalog);
    }

    public List<Subject> getBussinessExpenseSubject(Company company) {
        SubjectCatalog catalog = this.getSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_BUSSINESS_EXPENSE);
        return baseDao.getSubject(company, catalog);
    }

    public List<Subject> getCommonExpenseSubject(Company company) {
        SubjectCatalog catalog = this.getSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_COMMON_EXPENSE);
        return baseDao.getSubject(company, catalog);
    }

    public List<BSubjectCatalog> getMainSubjectCatalog(Company company) {
        return baseDao.getMainSubjectCatalog(company);
    }

    public List<Subject> getOtherIncomeSubject(Company company) {
        SubjectCatalog catalog = this.getSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_OTHER_INCOME);
        return baseDao.getSubject(company, catalog);
    }

    public Subject getSubject(int id) {
        return baseDao.findSubject(id);
    }

    public void saveSubject(Subject subject) {
        if(subject.getNumber() == null || subject.getNumber().equals(""))
        {
            subject.setNumber(systemManageService.getSubjectNumber(subject.getCompany(), NumberConstants.SUBJECT_NUM,subject.getCatalog().getId()));
        }
        this.utilities.saveObject(subject);
    }

    public void deleteBankCashForm(BankCash po) {
        this.utilities.deleteObject(po);
    }

    public void deleteBussinessExpenseForm(BussinessExpense po) {
        this.utilities.deleteObject(po);
    }

    public void deleteCommonExpenseForm(CommonExpense po) {
        this.utilities.deleteObject(po);
    }

    public void deleteOtherIncomeForm(OtherIncome po) {
        this.utilities.deleteObject(po);
    }

    public void deletePayableClearingForm(PayableClearing po) {
        this.utilities.deleteObject(po);
    }

    public void deletePrepayableForm(Prepayable po) {
        this.utilities.deleteObject(po);
    }

    public void deletePrereceivableForm(Prereceivable po) {
        this.utilities.deleteObject(po);
    }

    public void deleteReceivableClearingForm(ReceivableClearing po) {
        this.utilities.deleteObject(po);
    }

    public List<BankCash> getAllBankCashForms(Company comp) {
        return formDao.findAllBankCashForms(comp);
    }

    public List<BussinessExpense> getAllBussinessExpenseForms(Company comp) {
        return formDao.findAllBussinessExpenseForms(comp);
    }

    public List<CommonExpense> getAllCommonExpenseForms(Company comp) {
        return formDao.findAllCommonExpenseForms(comp);
    }

    public List<OtherIncome> getAllOtherIncomeForms(Company comp) {
        return formDao.findAllOtherIncomeForms(comp);
    }

    public List<PayableClearing> getAllPayableClearingForms(Company comp) {
        return formDao.findAllPayableClearingForms(comp);
    }

    public List<Prepayable> getAllPrepayableForms(Company comp) {
        return formDao.findAllPrepayableForms(comp);
    }

    public List<Prereceivable> getAllPrereceivableForms(Company comp) {
        return formDao.findAllPrereceivableForms(comp);
    }

    public List<ReceivableClearing> getAllReceivableClearingForms(Company comp) {
        return formDao.findAllReceivableClearingForms(comp);
    }

    public DailyReportPo setReceivableClearingDetail(Receivable po)
    {
        DailyReportPo d = this.managerDao.getDailyManagerForm(po.getCompany(), po.getFormNo());
        d.setReceiveMoney(po.getClearingMoney());
        return d;
    }
    
    public DailyReportPo setDailyReportPo(Payable po)
    {
        DailyReportPo d = this.managerDao.getDailyManagerForm(po.getCompany(), po.getFormNo());
        d.setPaymentMoney(po.getClearingMoney());
        return d;
    }

    public void passAccountingForm(Object po) {
        if (po instanceof BankCash)
        {
            this.passBankCashForm((BankCash)po);
        }
        else if (po instanceof BussinessExpense)
        {
            this.passBussinessExpenseForm((BussinessExpense)po);
        }
        else if (po instanceof CommonExpense)
        {
            this.passCommonExpenseForm((CommonExpense)po);
        }
        else if (po instanceof OtherIncome)
        {
            this.passOtherIncomeForm((OtherIncome)po);
        }
        else if (po instanceof PayableClearing)
        {
            this.passPayableClearingForm((PayableClearing)po);
        }
        else if (po instanceof Prepayable)
        {
            this.passPrepayableForm((Prepayable)po);
        }
        else if (po instanceof ReceivableClearing)
        {
            this.passReceivableClearingForm((ReceivableClearing)po);
        }
        else if (po instanceof Prereceivable)
        {
            this.passPrereceivableForm((Prereceivable)po);
        }
        else if (po instanceof InitialPayable)
        {
            this.passInitialPayableForm((InitialPayable)po);
        }
        else if (po instanceof InitialReceivable)
        {
            this.passInitialReceivableForm((InitialReceivable)po);
        }
        else if (po instanceof InitialSubject)
        {
            this.passInitialSubjectForm((InitialSubject)po);
        }
        
    }

    public void discardAccountingForm(Object po) {
        if (po instanceof BankCash)
        {
            this.discardBankCashForm((BankCash)po);
        }
        else if (po instanceof BussinessExpense)
        {
            this.discardBussinessExpenseForm((BussinessExpense)po);
        }
        else if (po instanceof CommonExpense)
        {
            this.discardCommonExpenseForm((CommonExpense)po);
        }
        else if (po instanceof OtherIncome)
        {
            this.discardOtherIncomeForm((OtherIncome)po);
        }
        else if (po instanceof PayableClearing)
        {
            this.discardPayableClearingForm((PayableClearing)po);
        }
        else if (po instanceof Prepayable)
        {
            this.discardPrepayableForm((Prepayable)po);
        }
        else if (po instanceof ReceivableClearing)
        {
            this.discardReceivableClearingForm((ReceivableClearing)po);
        }
        else if (po instanceof Prereceivable)
        {
            this.discardPrereceivableForm((Prereceivable)po);
        }
        else if (po instanceof InitialPayable)
        {
            this.discardInitialPayableForm((InitialPayable)po);
        }
        else if (po instanceof InitialReceivable)
        {
            this.discardInitialReceivableForm((InitialReceivable)po);
        }
        else if (po instanceof InitialSubject)
        {
            this.discardInitialSubjectForm((InitialSubject)po);
        }

    }

    /**
     * 在发生后记录
     */
    private void addPaymentDetailInfo(Date formDate, String formNo, String bussinessInfo, Customer customer, Employee employee, Double accountMoney, Double payableMoney, Double affordMoney, Double clearingMoney, Double remainMoney)
    {
        CustomerPaymentDetail detail = new CustomerPaymentDetail();
        detail.setCustomer(customer);
        detail.setCompany(customer.getCompany());
        detail.setFormDate(formDate);
        detail.setEmployee(employee);
        detail.setFormNo(formNo);
        detail.setBussinessType(bussinessInfo);
        detail.setPayableMoney(payableMoney);
        detail.setAccountMoney(accountMoney);
        detail.setAffordMoney(affordMoney);
        detail.setClearingMoney(clearingMoney);
        detail.setRemainMoney(remainMoney);
        this.utilities.saveObject(detail);
    }

    private void addReceivementDetailInfo(Date formDate, String formNo, String bussinessInfo, Customer customer,Employee employee,  Double accountMoney, Double receivableMoney, Double affordMoney, Double clearingMoney, Double remainMoney)
    {
        CustomerReceivementDetail detail = new CustomerReceivementDetail();
        detail.setCustomer(customer);
        detail.setCompany(customer.getCompany());
        detail.setFormDate(formDate);
        detail.setFormNo(formNo);
        detail.setEmployee(employee);
        detail.setBussinessType(bussinessInfo);
        detail.setAccountMoney(accountMoney);
        detail.setAffordMoney(affordMoney);
        detail.setClearingMoney(clearingMoney);
        detail.setRemainMoney(remainMoney);
        detail.setReceivableMoney(receivableMoney);
        this.utilities.saveObject(detail);
    }

    private void debitSubjectDetail(Date formDate, String formNumber, String bussinessInfo, Subject subject, Double initialMoney, Double changeMoney)
    {
        SubjectDetail detail = new SubjectDetail();
        detail.setCompany(subject.getCompany());
        detail.setSubject(subject);
        detail.setFormDate(formDate);
        detail.setFormNo(formNumber);
        detail.setBussinessType(bussinessInfo);
        detail.setInitialMoney(initialMoney);
        detail.setDebitMoney(changeMoney);
        this.utilities.saveObject(detail);
    }

    private void crebitSubjectDetailInfo(Date formDate, String formNumber, String bussinessInfo, Subject subject, Double initialMoney, Double changeMoney)
    {
        SubjectDetail detail = new SubjectDetail();
        detail.setCompany(subject.getCompany());
        detail.setSubject(subject);
        detail.setFormDate(formDate);
        detail.setFormNo(formNumber);
        detail.setBussinessType(bussinessInfo);
        detail.setInitialMoney(initialMoney);
        detail.setCreditMoney(changeMoney);
        this.utilities.saveObject(detail);
    }
    
    public void passBankCashForm(BankCash po) {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Subject outSubject = po.getSubject();
            SubjectAccount outAccount = this.findSubjectAccount(outSubject);
            if (outAccount == null)
            {
                throw new LogicException("付款科目没有做期初!");
            }
            if (outAccount.getRemainMoney() < po.getTotalMoney())
            {
                throw new LogicException("付款科目余额不足!");
            }
            Set<BankCashDetail> details = po.getDetails();
            for(BankCashDetail detail:details)
            {
                Subject subject = detail.getSubject();
                SubjectAccount account = this.findSubjectAccount(subject);
                if (account == null)
                {
                    account = new SubjectAccount();
                    account.setSubject(subject);                    
                }
                //业务逻辑!
                //account.setre
                this.debitSubjectDetail(po.getFormDate(), po.getNumber(), "转帐", detail.getSubject(), account.getRemainMoney(), detail.getTotalMoney());
                account.setDebitMoney(account.getDebitMoney() + detail.getTotalMoney());
                this.utilities.saveObject(account);
                //end                
            }
            this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), "转帐", po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
            outAccount.setCreditMoney(outAccount.getCreditMoney() + po.getAffordMoney());
            this.utilities.saveObject(outAccount);
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            //do sth else!
            this.utilities.saveObject(po);
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
              transaction.rollback();
              throw ex;
        }
    }

    /**
     * 付款的规则：
        结算方式：
            1. 现结
            2. 赊销
            3. 帐扣

            a.	选择“现结”方式时
            1.当付款帐号为空时，直接用帐上的钱；当帐上的钱不够时，直接产生应付款，帐上的钱不动
            2.当付款帐号不为空时，先付科目上的钱，再扣帐上的钱；如果不够则只产生应付款，科目的钱放到用户帐上去

            b.	选择“赊销”方式时
            1.	当付款帐号为空时，直接产生应付款，不管帐上是否有钱
            2.	当付款帐号不为空，（付款）并且足够支付时，直接支付，不产生应付款; 当钱不够时，将付出的钱算到预付款上去（帐上钱），直接产生应付;注意当帐号上的钱加上付款的钱够的时候，也不直接支付，而是产生应付款，这一点和“现结”不一样

            c.	选择“帐扣”方式时
            1.	 当付款帐号为空时，帐上金额够支付时，帐上金额支付；帐上金额不够支付时， 直接产生全额应付
            2.	当付款帐号不为空，付款+帐上的足够支付，则支付；两者之和不够支付时，产生全额应付
     * @param po
     */

    private void addPayable(IAffordForm po)
    {
        Payable payable = new Payable();
        payable.setCustomer(po.getCustomer());
        payable.setEmployee(po.getEmployee());
        payable.setFormDate(po.getFormDate());
        payable.setPayableMoney(po.getTotalMoney());
        payable.setFormNo(po.getNumber());
        payable.setCompany(po.getCompany());
        payable.setPayableDate(this.caculateDate(po.getFormDate(), po.getAffordTerm()));
        this.utilities.saveObject(payable);
        CustomerPayableAccount account = updateCustomerPayableAccount(po.getCustomer());
        //
        String bussinessType = "经营费用";
        if (po instanceof ReceivableClearing)
        {
            bussinessType = "付款结算";
        }
        this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), account.getAccountMoney(), po.getTotalMoney(), po.getAffordMoney(), po.getClearingMoney(), account.getRemainMoney());

    }

    private void addReceivable(IReceiveForm po)
    {
        Receivable receivable = new Receivable();
        receivable.setCustomer(po.getCustomer());
        receivable.setEmployee(po.getEmployee());
        receivable.setFormDate(po.getFormDate());
        receivable.setReceivableDate(this.caculateDate(po.getFormDate(), po.getAffordTerm()));
        receivable.setReceivableMoney(po.getTotalMoney());
        receivable.setFormNo(po.getNumber());
        receivable.setCompany(po.getCompany());
        this.utilities.saveObject(receivable);
        CustomerReceivableAccount account = updateCustomerReceivableAccount(po.getCustomer());
         String bussinessType = "其它收入";
        if (po instanceof ReceivableClearing)
        {
            bussinessType = "收款结算";
        }
        this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), account.getAccountMoney(), po.getTotalMoney(), po.getReceiveMoney(), po.getClearingMoney(), account.getRemainMoney());

    }

    private void affordSubjectMoney(SubjectAccount account, double money)
    {
        account.setCreditMoney(account.getCreditMoney() + money);
        this.utilities.saveObject(account);
    }

    private void receiveSubjectMoney(SubjectAccount account, double money)
    {
        account.setDebitMoney(account.getDebitMoney() + money);
        this.utilities.saveObject(account);
    }

    /**
     * 在addPayable的地方添加了CustomerPaymentDetail信息，所以不需要再添加，但其它地方需要好好检查，至于accountMoney在之前还是在之后，需要研究业务 
     * @param po
     */
    private void clearAffordForm(IAffordForm po)
    {
        String bussinessType = "经营费用";
        if (po instanceof ReceivableClearing)
        {
            bussinessType = "付款结算";
        }
            Subject outSubject = po.getSubject();
            int clearType = po.getClearingType().getId();
            CustomerPayableAccount customerPayableAccount = this.getCustomerPayableAccount(po.getCustomer());
            if (customerPayableAccount == null)
            {
                customerPayableAccount = new CustomerPayableAccount();
                customerPayableAccount.setCompany(po.getCompany());
                customerPayableAccount.setCustomer(po.getCustomer());
                this.utilities.saveObject(customerPayableAccount);
            }
            if (outSubject == null)//没有设置付款帐号
            {
                if (clearType == ServiceConstants.CLEAR_TYPE_CASH || clearType == ServiceConstants.CLEAR_TYPE_ACCOUNT )
                {
                    if (customerPayableAccount != null &&  customerPayableAccount.getAccountMoney()  + 0.00000001d> po.getTotalMoney())//
                    {
                        double prepayMoney = customerPayableAccount.getAccountMoney();
                        customerPayableAccount.setAccountMoney(prepayMoney - po.getTotalMoney());
                        customerPayableAccount.setClearingMoney(customerPayableAccount.getClearingMoney() + po.getTotalMoney());//???????缺少一张记录表
                        //结算clearing
                        po.setClearingMoney(po.getTotalMoney());

                        //add customer 交易信息
                        this.utilities.saveObject(customerPayableAccount);
                        this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), customerPayableAccount.getAccountMoney(), po.getTotalMoney(), po.getAffordMoney(), po.getClearingMoney(), customerPayableAccount.getRemainMoney());

                    }
                    else
                    {
                        //直接产生应付款
                        if (po instanceof PayableClearing)
                        {
                            throw new LogicException("付款金额不足（帐上金额+付款金额<结算金额）！");
                        }
                        addPayable(po);
                        
                    }
                }
                else if (clearType == ServiceConstants.CLEAR_TYPE_CREDIT)
                {
                    addPayable(po);
                }
            }
            else
            {
                SubjectAccount outAccount = this.findSubjectAccount(outSubject);
                if (outAccount == null)
                {
                    throw new LogicException("付款科目没有做期初!");
                }
                if (outAccount.getRemainMoney() < po.getAffordMoney())
                {
                    throw new LogicException("付款科目余额不足!");
                }
                if (clearType == ServiceConstants.CLEAR_TYPE_CASH || clearType == ServiceConstants.CLEAR_TYPE_ACCOUNT )
                {
                    if (po.getAffordMoney()  + 0.00000001d> po.getTotalMoney())
                    {
                        customerPayableAccount.setClearingMoney(customerPayableAccount.getClearingMoney() + po.getTotalMoney());
                        customerPayableAccount.setAccountMoney(customerPayableAccount.getAccountMoney() + po.getAffordMoney() - po.getTotalMoney());
                        po.setClearingMoney(po.getTotalMoney());
                        this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
                        this.affordSubjectMoney(outAccount, po.getAffordMoney());
                        this.utilities.saveObject(customerPayableAccount);
                        this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), customerPayableAccount.getAccountMoney(), po.getTotalMoney(), po.getAffordMoney(), po.getClearingMoney(), customerPayableAccount.getRemainMoney());

                    }
                    else
                    {
                        if (po.getAffordMoney() + customerPayableAccount.getAccountMoney()  + 0.00000001d> po.getTotalMoney())
                        {
                            customerPayableAccount.setClearingMoney(customerPayableAccount.getClearingMoney() + po.getTotalMoney());
                            double needAccountMoney = po.getTotalMoney() - po.getAffordMoney();
                            customerPayableAccount.setAccountMoney(customerPayableAccount.getAccountMoney() - needAccountMoney);
                            customerPayableAccount.setClearingMoney(customerPayableAccount.getClearingMoney() + po.getTotalMoney());
                            this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
                            this.affordSubjectMoney(outAccount, po.getAffordMoney());
                            po.setClearingMoney(po.getTotalMoney());
                            this.utilities.saveObject(customerPayableAccount);
                            this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), customerPayableAccount.getAccountMoney(), po.getTotalMoney(), po.getAffordMoney(), po.getClearingMoney(), customerPayableAccount.getRemainMoney());

                        }
                        else  //帐上的钱不够
                        {
                            if (po instanceof PayableClearing)
                            {
                            throw new LogicException("付款金额不足（帐上金额+付款金额<结算金额）！");
                            }
                            customerPayableAccount.setAccountMoney(customerPayableAccount.getAccountMoney() + po.getAffordMoney());
                            this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
                            this.affordSubjectMoney(outAccount, po.getAffordMoney());
                            this.addPayable(po);
                            this.utilities.saveObject(customerPayableAccount);
                        }
                    }
                }
                else if (clearType == ServiceConstants.CLEAR_TYPE_CREDIT)
                {
                    if (po.getAffordMoney()  + 0.00000001d> po.getTotalMoney())
                    {
                        customerPayableAccount.setClearingMoney(customerPayableAccount.getClearingMoney() + po.getTotalMoney());
                        customerPayableAccount.setAccountMoney(customerPayableAccount.getAccountMoney() + po.getAffordMoney() - po.getTotalMoney());
                        this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
                        this.affordSubjectMoney(outAccount, po.getAffordMoney());
                        po.setClearingMoney(po.getTotalMoney());
                        this.utilities.saveObject(customerPayableAccount);
                        this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(),po.getEmployee(),  customerPayableAccount.getAccountMoney(), po.getTotalMoney(), po.getAffordMoney(), po.getClearingMoney(), customerPayableAccount.getRemainMoney());

                    }
                    else
                    {
                        customerPayableAccount.setAccountMoney(customerPayableAccount.getAccountMoney() + po.getAffordMoney());
                        this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
                        this.affordSubjectMoney(outAccount, po.getAffordMoney());
                        this.addPayable(po);
                        this.utilities.saveObject(customerPayableAccount);
                    }
                }
          }
    }

    private void clearReceiveForm(IReceiveForm po)
    {
        String bussinessType = "其它收入";
        if (po instanceof ReceivableClearing)
        {
            bussinessType = "收款结算";
        }
            Subject inSubject = po.getSubject();
            int clearType = po.getClearingType().getId();
            CustomerReceivableAccount customerReceivableAccount = this.getCustomerReceivableAccount(po.getCustomer());
            if (customerReceivableAccount == null)
            {
                customerReceivableAccount = new CustomerReceivableAccount();
                customerReceivableAccount.setCompany(po.getCompany());
                customerReceivableAccount.setCustomer(po.getCustomer());
                this.utilities.saveObject(customerReceivableAccount);
            }
            if (inSubject == null)//没有设置收款帐号
            {
                if (clearType == ServiceConstants.CLEAR_TYPE_CASH || clearType == ServiceConstants.CLEAR_TYPE_ACCOUNT )
                {
                    if (customerReceivableAccount != null &&  customerReceivableAccount.getAccountMoney()  + 0.00000001d> po.getTotalMoney())//
                    {
                        double prereceiveMoney = customerReceivableAccount.getAccountMoney();
                        customerReceivableAccount.setAccountMoney(prereceiveMoney - po.getTotalMoney());
                        customerReceivableAccount.setClearingMoney(customerReceivableAccount.getClearingMoney() + po.getTotalMoney());//???????缺少一张记录表
                        po.setClearingMoney(po.getTotalMoney());
                        this.utilities.saveObject(customerReceivableAccount);
                        this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(),  customerReceivableAccount.getAccountMoney(), po.getTotalMoney(), po.getReceiveMoney(), po.getClearingMoney(), customerReceivableAccount.getRemainMoney());

                    }
                    else
                    {
                        //直接产生应付款
                        if (po instanceof ReceivableClearing)
                        {
                            throw new LogicException("客户付款金额不足（帐上金额+收款金额<结算金额）！");
                        }
                        addReceivable(po);
                    }
                }
                else if (clearType == ServiceConstants.CLEAR_TYPE_CREDIT)
                {
                    addReceivable(po);
                }
            }
            else
            {
                SubjectAccount inAccount = this.findSubjectAccount(inSubject);
                if (inAccount == null)
                {
                    inAccount = new SubjectAccount();
                    inAccount.setCompany(po.getCompany());
                    inAccount.setSubject(inSubject);
                    inAccount.setInitialMoney(0d);
                    this.utilities.saveObject(inAccount);
                }
                if (clearType == ServiceConstants.CLEAR_TYPE_CASH || clearType == ServiceConstants.CLEAR_TYPE_ACCOUNT )
                {
                    if (po.getReceiveMoney()  + 0.00000001d> po.getTotalMoney())
                    {
                        customerReceivableAccount.setClearingMoney(customerReceivableAccount.getClearingMoney() + po.getTotalMoney());
                        customerReceivableAccount.setAccountMoney(customerReceivableAccount.getAccountMoney() + po.getReceiveMoney() - po.getTotalMoney());
                        po.setClearingMoney(po.getTotalMoney());
                        this.debitSubjectDetail(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), inAccount.getRemainMoney(), po.getReceiveMoney());
                        this.receiveSubjectMoney(inAccount, po.getReceiveMoney());
                        this.utilities.saveObject(customerReceivableAccount);
                        this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(), po.getEmployee(), customerReceivableAccount.getAccountMoney(), po.getTotalMoney(), po.getReceiveMoney(), po.getClearingMoney(), customerReceivableAccount.getRemainMoney());

                    }
                    else
                    {
                        if (po.getReceiveMoney() + customerReceivableAccount.getAccountMoney() + 0.00000001d > po.getTotalMoney())
                        {
                            customerReceivableAccount.setClearingMoney(customerReceivableAccount.getClearingMoney() + po.getTotalMoney());
                            double needAccountMoney = po.getTotalMoney() - po.getReceiveMoney();
                            customerReceivableAccount.setAccountMoney(customerReceivableAccount.getAccountMoney() - needAccountMoney);
                            customerReceivableAccount.setClearingMoney(customerReceivableAccount.getClearingMoney() + po.getTotalMoney());
                            this.debitSubjectDetail(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), inAccount.getRemainMoney(), po.getReceiveMoney());
                            this.receiveSubjectMoney(inAccount, po.getReceiveMoney());
                            po.setClearingMoney(po.getTotalMoney());
                            this.utilities.saveObject(customerReceivableAccount);
                            this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(),po.getEmployee(),  customerReceivableAccount.getAccountMoney(), po.getTotalMoney(), po.getReceiveMoney(), po.getClearingMoney(), customerReceivableAccount.getRemainMoney());

                        }
                        else  //帐上的钱不够
                        {
                            if (po instanceof ReceivableClearing)
                            {
                                throw new LogicException("客户付款金额不足（帐上金额+收款金额<结算金额）！");
                            }
                            customerReceivableAccount.setAccountMoney(customerReceivableAccount.getAccountMoney() + po.getReceiveMoney());
                            this.debitSubjectDetail(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), inAccount.getRemainMoney(), po.getReceiveMoney());
                            this.receiveSubjectMoney(inAccount, po.getReceiveMoney());
                            this.addReceivable(po);
                            this.utilities.saveObject(customerReceivableAccount);
                        }
                    }
                }
                else if (clearType == ServiceConstants.CLEAR_TYPE_CREDIT)
                {
                    if (po.getReceiveMoney()  + 0.00000001d> po.getTotalMoney())
                    {
                        customerReceivableAccount.setClearingMoney(customerReceivableAccount.getClearingMoney() + po.getTotalMoney());
                        customerReceivableAccount.setAccountMoney(customerReceivableAccount.getAccountMoney() + po.getReceiveMoney() - po.getTotalMoney());
                        this.debitSubjectDetail(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), inAccount.getRemainMoney(), po.getReceiveMoney());
                        this.receiveSubjectMoney(inAccount, po.getReceiveMoney());
                        po.setClearingMoney(po.getTotalMoney());
                        this.utilities.saveObject(customerReceivableAccount);
                        this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), bussinessType, po.getCustomer(),po.getEmployee(),  customerReceivableAccount.getAccountMoney(), po.getTotalMoney(), po.getReceiveMoney(), po.getClearingMoney(), customerReceivableAccount.getRemainMoney());

                    }
                    else
                    {
                        customerReceivableAccount.setAccountMoney(customerReceivableAccount.getAccountMoney() + po.getReceiveMoney());
                        this.debitSubjectDetail(po.getFormDate(), po.getNumber(), bussinessType, po.getSubject(), inAccount.getRemainMoney(), po.getReceiveMoney());
                        this.receiveSubjectMoney(inAccount, po.getReceiveMoney());
                        this.addReceivable(po);
                        this.utilities.saveObject(customerReceivableAccount);
                    }
                }
          }
    }

    public void passBussinessExpenseForm(BussinessExpense po) {
            //subject
            Transaction transaction = this.utilities.beginDaoTransaction();
            try
            {
                clearAffordForm(po);
                //费用明细科目
                Subject outSubject = po.getSubject();
            SubjectAccount outAccount = this.findSubjectAccount(outSubject);

            
            if (outAccount == null)
            {
                 throw new LogicException("付款科目没有做期初!");
            }
            if (outAccount.getRemainMoney() < po.getTotalMoney())
            {
                throw new LogicException("付款科目余额不足!");
            }
                Set<BussinessExpenseDetail> details = po.getDetails();
                for(BussinessExpenseDetail detail:details)
                {
                    Subject subject = detail.getSubject();

                    SubjectAccount account = this.findSubjectAccount(subject);
                    if (account == null)
                    {
                        account = new SubjectAccount();
                        account.setSubject(subject);
                    }
                    //业务逻辑!
                    //account.setre
                    this.debitSubjectDetail(po.getFormDate(), po.getNumber(), "转帐", subject, account.getRemainMoney(), detail.getTotalMoney());
                    account.setDebitMoney(account.getDebitMoney() + detail.getTotalMoney());
                    this.utilities.saveObject(account);
                    //end
                }
                po.setStatus(ServiceConstants.FORMAL_STATUS);
                //do sth else!
                this.utilities.saveObject(po);
                transaction.commit();
            }
            catch(RuntimeException ex)
            {
                transaction.rollback();
                throw ex;
            }
    }

    /**
     * 没有客户信息
     * @param po
     */
    public void passCommonExpenseForm(CommonExpense po) {
        Transaction transaction = this.utilities.beginDaoTransaction();
    
        try
        {
            Subject outSubject = po.getSubject();
            SubjectAccount outAccount = this.findSubjectAccount(outSubject);
            if (outAccount == null)
            {
                throw new LogicException("付款科目没有做期初!");
            }
            if (outAccount.getRemainMoney() < po.getTotalMoney())
            {
                throw new LogicException("付款科目余额不足!");
            }
            Set<CommonExpenseDetail> details = po.getDetails();
            for(CommonExpenseDetail detail:details)
            {
                Subject subject = detail.getSubject();
                SubjectAccount account = this.findSubjectAccount(subject);
                if (account == null)
                {
                    account = new SubjectAccount();
                    account.setSubject(subject);
                }
                //业务逻辑!
                //account.setre
                this.debitSubjectDetail(po.getFormDate(), po.getNumber(), "一般费用", subject, account.getRemainMoney(), detail.getTotalMoney());
                account.setDebitMoney(account.getDebitMoney() + detail.getTotalMoney());
                this.utilities.saveObject(account);
                //end
            }
            this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), "一般费用", po.getSubject(), outAccount.getRemainMoney(), po.getAffordMoney());
            outAccount.setCreditMoney(outAccount.getCreditMoney() + po.getAffordMoney());
            
            this.utilities.saveObject(outAccount);
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            //do sth else!
            this.utilities.saveObject(po);
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
        }
    }
    /**
     * 收款
     * @param po
     */
    public void passOtherIncomeForm(OtherIncome po) {
       Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {

            clearReceiveForm(po);
            //费用明细科目
           Subject outSubject = po.getSubject();
            SubjectAccount outAccount = this.findSubjectAccount(outSubject);
            if (outAccount == null)
            {
                throw new LogicException("付款科目没有做期初!");
            }
            if (outAccount.getRemainMoney() < po.getTotalMoney())
            {
                throw new LogicException("付款科目余额不足!");
            }
            Set<OtherIncomeDetail> details = po.getDetails();
            for(OtherIncomeDetail detail:details)
            {
                Subject subject = detail.getSubject();
                SubjectAccount account = this.findSubjectAccount(subject);
                if (account == null)
                {
                    account = new SubjectAccount();
                    account.setSubject(subject);
                }
                //业务逻辑!
                //account.setre
                this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), "其它收入", subject, account.getRemainMoney(), detail.getTotalMoney());
                account.setCreditMoney(account.getCreditMoney() + detail.getTotalMoney());
                this.utilities.saveObject(account);
                //end
            }
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            //do sth else!
            this.utilities.saveObject(po);
            transaction.commit();
        }
            catch(RuntimeException ex)
            {
                transaction.rollback();
                throw ex;
            }


    }

    public void passPayableClearingForm(PayableClearing po) {
           Transaction transaction = this.utilities.beginDaoTransaction();
           clearAffordForm(po);
           try
           {
                //费用
                Set<PayableClearingDetail> details = po.getDetails();
                for(PayableClearingDetail detail:details)
                {
                    Payable payable = detail.getPayable();
                    if (detail.getClearingMoney() > payable.getRemainMoney())
                    {
                        throw new LogicException("单据:\"" + payable.getFormNo() + "\"的应付款余额少于结算金额");
                    }
                    //payable.setClearingMoney(payable.getClearingMoney() + detail.getClearingMoney());
                    
                    CustomerPayableAccount account = this.getCustomerPayableAccount(po.getCustomer());
                    if(account != null)
                    {
                        if(account.getRemainMoney() < payable.getClearingMoney())
                        {
                            throw new LogicException("单据:\"" + payable.getFormNo() + "\"的未结金额少于结算金额");
                        }
                        account.setRemainMoney(account.getRemainMoney() - payable.getClearingMoney());
                        this.utilities.saveObject(account);
                    }
                    payable.setClearingMoney(payable.getClearingMoney() + detail.getClearingMoney());
                    this.utilities.saveObject(this.setDailyReportPo(payable));
                    this.utilities.saveObject(payable);
                }
                po.setStatus(ServiceConstants.FORMAL_STATUS);
                //do sth else!
                this.utilities.saveObject(po);
           }
           catch(RuntimeException ex)
           {
               transaction.rollback();
               throw ex;              
           }
    }

    public void passPrepayableForm(Prepayable po) 
    {
        //subject上的钱
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Subject subject = po.getSubject();
            SubjectAccount subjectAccount = this.findSubjectAccount(subject);
            if (subjectAccount == null)
            {
                throw new LogicException("付款帐户没有做期初!");
            }
            if (subjectAccount.getRemainMoney() < po.getAffordMoney())
            {
                throw new LogicException("付款帐户余额不够!");
            }
            this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), "采购预付", po.getSubject(), subjectAccount.getRemainMoney(), po.getAffordMoney());
            subjectAccount.setCreditMoney(subjectAccount.getCreditMoney() + po.getAffordMoney());

            //帐户上的钱
            CustomerPayableAccount account = this.getCustomerPayableAccount(po.getCustomer());
            if (account == null)
            {
                account = new CustomerPayableAccount();
                account.setCompany(po.getCompany());
                account.setCustomer(po.getCustomer());
            }
            account.setAccountMoney(account.getAccountMoney() + po.getAffordMoney());

            //subjectAccount
            this.utilities.saveObject(subjectAccount);
            this.utilities.saveObject(account);
            this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), "采购预付", po.getCustomer(),po.getEmployee(),  account.getAccountMoney(), 0d, po.getAffordMoney(), 0d, account.getRemainMoney());

            po.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
        }
    }

    

    public void passPrereceivableForm(Prereceivable po) {
        //subject上的钱
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Subject subject = po.getSubject();
            SubjectAccount subjectAccount = this.findSubjectAccount(subject);
            if (subjectAccount == null)
            {
                subjectAccount = new SubjectAccount();
                subjectAccount.setSubject(subject);
                subjectAccount.setCompany(po.getCompany());
            }
            this.debitSubjectDetail(po.getFormDate(), po.getNumber(), "销售预收", po.getSubject(), subjectAccount.getRemainMoney(), po.getReceiveMoney());
            subjectAccount.setDebitMoney(subjectAccount.getDebitMoney() + po.getReceiveMoney());
            CustomerReceivableAccount account = this.getCustomerReceivableAccount(po.getCustomer());
            //customer receivable account
             if (account == null)
            {
                account = new CustomerReceivableAccount();
                account.setCompany(po.getCompany());
                account.setCustomer(po.getCustomer());
            }
            account.setAccountMoney(account.getAccountMoney() + po.getReceiveMoney());

            this.utilities.saveObject(account);
            this.utilities.saveObject(subjectAccount);
            this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), "销售预收", po.getCustomer(),po.getEmployee(),  account.getAccountMoney(), 0d, po.getReceiveMoney(), 0d, account.getRemainMoney());

            po.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
            }

    }

    public void passReceivableClearingForm(ReceivableClearing po) {        
                    //subject
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            clearReceiveForm(po);
            //费用明细科目
            Set<ReceivableClearingDetail> details = po.getDetails();
            for(ReceivableClearingDetail detail:details)
            {
               Receivable receivable = detail.getReceivable();
               if (detail.getClearingMoney() > receivable.getRemainMoney())
               {
                        throw new LogicException("单据:\"" + receivable.getFormNo() + "\"的应收款余额少于结算金额");
                }
                //receivable.setClearingMoney(receivable.getClearingMoney() + detail.getClearingMoney());
                
                CustomerReceivableAccount account = this.getCustomerReceivableAccount(po.getCustomer());
                if(account != null)
                {
                    if(account.getRemainMoney() < receivable.getClearingMoney())
                    {
                        throw new LogicException("单据:\"" + receivable.getFormNo() + "\"的未结金额少于结算金额");
                    }
                    account.setRemainMoney(account.getRemainMoney() - receivable.getClearingMoney());
                    this.utilities.saveObject(account);
                }
                receivable.setClearingMoney(receivable.getClearingMoney() + detail.getClearingMoney());
                this.utilities.saveObject(this.setReceivableClearingDetail(receivable));
                this.utilities.saveObject(receivable);
            }
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            //do sth else!
            this.utilities.saveObject(po);
            transaction.commit();
        }
            catch(RuntimeException ex)
            {
                transaction.rollback();
                throw ex;
            }
    }

    public void passInitialPayableForm(InitialPayable po) {
         Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Payable payable = new Payable();
            payable.setEmployee(po.getEmployee());
            payable.setPayableMoney(po.getPayableMoney());
            payable.setCompany(po.getCompany());
            payable.setCustomer(po.getCustomer());
            payable.setFormNo(po.getNumber());
            payable.setFormDate(po.getFormDate());
            payable.setPayableDate(this.caculateDate(po.getFormDate(), po.getAffordTerm()));
            CustomerPayableAccount account = this.getCustomerPayableAccount(po.getCustomer());
            if (account == null)
            {
                account = new CustomerPayableAccount();
                account.setCompany(po.getCompany());
                account.setCustomer(po.getCustomer());
            }
            account.setPayableMoney(account.getPayableMoney() + po.getPayableMoney());
            //do sth else!
            this.utilities.saveObject(payable);
            this.utilities.saveObject(account);
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);
            account = this.updateCustomerPayableAccount(po.getCustomer());
            this.addPaymentDetailInfo(po.getFormDate(), po.getNumber(), "应付期初", po.getCustomer(), po.getEmployee(), account.getAccountMoney(), po.getPayableMoney(), 0d, 0d, account.getRemainMoney());
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
        }
    }

    public void passInitialReceivableForm(InitialReceivable po) {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Receivable receivable = new Receivable();
            receivable.setReceivableMoney(po.getReceivableMoney());
            receivable.setCompany(po.getCompany());
            receivable.setCustomer(po.getCustomer());
            receivable.setEmployee(po.getEmployee());
            receivable.setFormDate(po.getFormDate());
            receivable.setFormNo(po.getNumber());
            receivable.setReceivableDate(this.caculateDate(po.getFormDate(), po.getAffordTerm()));
            CustomerReceivableAccount account = this.getCustomerReceivableAccount(po.getCustomer());
            if (account == null)
            {
                account = new CustomerReceivableAccount();
                account.setCompany(po.getCompany());
                account.setCustomer(po.getCustomer());
            }
            account.setReceivableMoney(account.getReceivableMoney() + po.getReceivableMoney());
            this.utilities.saveObject(receivable);
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);
            account = this.updateCustomerReceivableAccount(po.getCustomer());
            this.addReceivementDetailInfo(po.getFormDate(), po.getNumber(), "销售预收", po.getCustomer(), po.getEmployee(), account.getAccountMoney(), po.getReceivableMoney(), 0d, 0d, account.getRemainMoney());

            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
        }
    }

    public void passInitialSubjectForm(InitialSubject po) {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Set<InitialSubjectDetail> details = po.getDetails();
            for(InitialSubjectDetail detail:details)
            {
                Subject subject = detail.getSubject();
                SubjectAccount account = this.findSubjectAccount(subject);
                if (account == null)
                {
                    account = new SubjectAccount();
                    account.setSubject(subject);
                }
                //业务逻辑!
                this.crebitSubjectDetailInfo(po.getFormDate(), po.getNumber(), "科目期初", detail.getSubject(), detail.getRemainMoney(), 0d);
                account.setInitialMoney(detail.getRemainMoney());
                account.setCreditMoney(0d);
                account.setDebitMoney(0d);
                this.utilities.saveObject(account);
                //end
            }
            po.setStatus(ServiceConstants.FORMAL_STATUS);
            //do sth else!
            this.utilities.saveObject(po);
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
                transaction.rollback();
                throw ex;
        }
    }



    public void discardBankCashForm(BankCash po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardBussinessExpenseForm(BussinessExpense po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardCommonExpenseForm(CommonExpense po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardInitialPayableForm(InitialPayable po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardInitialReceivableForm(InitialReceivable po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardInitialSubjectForm(InitialSubject po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardOtherIncomeForm(OtherIncome po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardPayableClearingForm(PayableClearing po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardPrepayableForm(Prepayable po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardPrereceivableForm(Prereceivable po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }

    public void discardReceivableClearingForm(ReceivableClearing po) {
        po.setStatus(ServiceConstants.DISCARD_STATUS);
        this.utilities.saveObject(po);
    }


    public void saveBankCashForm(BankCash po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.CASH_BANK_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void saveBussinessExpenseForm(BussinessExpense po) {

        if( po.getSubject()==null){
            
         throw new LogicException("付款账号为空!");
       }
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.KEEP_FARE_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void saveCommonExpenseForm(CommonExpense po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {

            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.GENAL_FARE_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void saveOtherIncomeForm(OtherIncome po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.OTHER_INCOME_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void savePayableClearingForm(PayableClearing po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PAY_BALANCE_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void savePrepayableForm(Prepayable po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PURCHASE_BEFORE_PAY_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void savePrereceivableForm(Prereceivable po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SALE_BEFORE_INCOME_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void saveReceivableClearingForm(ReceivableClearing po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            po.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.GATHER_BALANCE_NUM));
        }
        this.utilities.saveObject(po);
    }

    public void deleteInitialPayableForm(InitialPayable po) {
        this.utilities.deleteObject(po);
    }

    public void deleteInitialReceivableForm(InitialReceivable po) {
        this.utilities.deleteObject(po);
    }

    public void deleteInitialSubjectForm(InitialSubject po) {
        this.utilities.deleteObject(po);
    }

    public List<InitialPayable> findAllInitialPayableForms(Company comp) {
        return this.formDao.findAllInitialPayableForms(comp);
    }

    public List<InitialReceivable> findAllInitialReceivableForms(Company comp) {
        return this.formDao.findAllInitialReceivableForms(comp);
    }

    public List<InitialSubject> findAllInitialSubjectForms(Company comp) {
        return this.formDao.findAllInitialSubjectForms(comp);
    }

    public void saveInitialPayableForm(InitialPayable po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PAY_INIT_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void saveInitialReceivableForm(InitialReceivable po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.RECEIVE_INIT_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void saveInitialSubjectForm(InitialSubject po) {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SUBJECT_INIT_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void saveCustomerPayableAccount(CustomerPayableAccount po) {
        this.utilities.saveObject(po);
    }

    public void saveCustomerReceivableAccount(CustomerReceivableAccount po) {
        this.utilities.saveObject(po);
    }

    public SubjectAccount findSubjectAccount(Subject subject) {
        return this.formDao.findSubjectAccount(subject);
    }
    /**
     * remain money在平常的使用中不要记录
     * @param po
     */
    public void saveSubjectAccount(SubjectAccount po) {
       this.utilities.saveObject(po);
    }

    public List<Payable> findAllPayables(Company company) {
        return formDao.findAllPayables(company);
    }

    public List<Receivable> findAllReceivable(Company company) {
        return formDao.findAllReceivable(company);
    }

    public List<Payable> findPayables(Customer customer) {
        return formDao.findPayables(customer);
    }

    public List<Receivable> findReceivable(Customer customer) {
        return formDao.findReceivable(customer);
    }

    public CustomerPayableAccount getCustomerPayableAccount(Customer customer) {
        return formDao.getCustomerPayableAccount(customer);
    }

    public CustomerReceivableAccount getCustomerReceivableAccount(Customer customer) {
        return formDao.getCustomerReceivableAccount(customer);
    }

    public void addPurchaseBackReceivable(PurchaseBackPo po)
    {
        Receivable p = new Receivable();
        p.setFormDate(po.getFormDate());
        p.setFormNo(po.getNumber());
        p.setCustomer(po.getSupplier());
        p.setEmployee(po.getChargePerson());
        p.setReceivableMoney(po.getTotalMoney());
        p.setReceivableDate(this.caculateDate(po.getFormDate(), po.getPayTerm()));
        p.setCompany(po.getCompany());
        this.utilities.saveObject(p);
        updateCustomerReceivableAccount(po.getSupplier());
    }

    public CustomerPayableAccount updateCustomerPayableAccount(Customer customer)
    {
        List<Payable> pos = this.findPayables(customer);
        CustomerPayableAccount account = this.getCustomerPayableAccount(customer);
        if (account == null)
        {
            account = new CustomerPayableAccount();
            account.setCompany(customer.getCompany());
            account.setCustomer(customer);
        }
        account.setPayableMoney(0d);
        account.setRemainMoney(0d);
        for(Payable po:pos) //只修改未结单的
        {
            if (po.getStatus() == ServiceConstants.UNFINISHED)
            {
                account.setPayableMoney(account.getPayableMoney() + po.getPayableMoney());
                account.setRemainMoney(account.getRemainMoney() + po.getRemainMoney());
            }
        }
        this.utilities.saveObject(account);
        return account;
    }

    public CustomerReceivableAccount updateCustomerReceivableAccount(Customer customer)
    {
        List<Receivable> pos = this.findReceivable(customer);
        CustomerReceivableAccount account = this.getCustomerReceivableAccount(customer);
        if (account == null)
        {
            account = new CustomerReceivableAccount();
            account.setCompany(customer.getCompany());
            account.setCustomer(customer);
        }
        account.setReceivableMoney(0d);
        account.setRemainMoney(0d);
        for(Receivable po:pos) //只修改未结单的
        {
            if (po.getStatus() == ServiceConstants.UNFINISHED)
            {
                account.setReceivableMoney(account.getReceivableMoney() + po.getReceivableMoney());
                //account.setClearingMoney(account.getClearingMoney() + po.getClearingMoney());
                account.setRemainMoney(account.getRemainMoney() + po.getRemainMoney());
            }
        }
        this.utilities.saveObject(account);
        return account;
    }

    public void addPurchasePayable(PurchaseOrderPo po)
    {
        Payable p = new Payable();
        p.setFormDate(po.getFormDate());
        p.setFormNo(po.getNumber());
        p.setPayableDate(this.caculateDate(po.getFormDate(), po.getPayTerm()));
        p.setCustomer(po.getSupplier());
        p.setEmployee(po.getChargePerson());
        p.setPayableMoney(po.getTotalMoney());
        p.setCompany(po.getCompany());
        this.utilities.saveObject(p);
        updateCustomerPayableAccount(po.getSupplier());
    }

    private Date caculateDate(Date date, int nextDays)
    {
        Double time = 1000d * 60 * nextDays * 24 * 60 + date.getTime();
        return new Date(time.longValue());
    }

    public void addSaleBackPayable(SaleOrderBackPo po)
    {
        Payable p = new Payable();
        p.setFormDate(po.getFormDate());
        p.setFormNo(po.getNumber());
        p.setPayableDate(this.caculateDate(po.getFormDate(), po.getPayTerm()));
        p.setCustomer(po.getCustomer());
        p.setEmployee(po.getEmployee());
        p.setPayableMoney(po.getTotalMoney());
        p.setCompany(po.getCompany());
        this.utilities.saveObject(p);
        updateCustomerPayableAccount(po.getCustomer());
    }

    public void addSaleReceivable(SalePo po)
    {
        Receivable p = new Receivable();
        p.setFormDate(po.getFormDate());
        p.setFormNo(po.getNumber());
        p.setCustomer(po.getCustomer());
        p.setEmployee(po.getEmployee());
        p.setReceivableMoney(po.getTotalMoney());
        p.setCompany(po.getCompany());
        p.setReceivableDate(this.caculateDate(po.getFormDate(), po.getPayTerm()));
        this.utilities.saveObject(p);
        updateCustomerReceivableAccount(po.getCustomer());
    }

    public double getSubjectAccountMoney(Subject subject)
    {
        SubjectAccount account = this.findSubjectAccount(subject);
        if (account == null)
        {
            return 0d;
        }
        return account.getRemainMoney();
    }

    public double getCustomerPayableAccountMoney(Customer customer)
    {
        CustomerPayableAccount account = this.getCustomerPayableAccount(customer);
        if (account == null)
        {
            return 0d;
        }
        return account.getAccountMoney();
    }

    public double getCustomerReceivableAccountMoney(Customer customer)
    {
        CustomerReceivableAccount account = this.getCustomerReceivableAccount(customer);
        if (account == null)
        {
            return 0d;
        }
        return account.getAccountMoney();
    }

    private String constructSubjectQueryString(Date startDate, Date endDate, Subject subject, String qString, Vector argVector)
    {
            String queryString = qString;
            if (subject != null)
            {
                queryString += " and po.subject=?";
                argVector.add(subject);
            }
            if (startDate != null)
            {
                queryString += " and po.formDate >= ?";
                argVector.add(startDate);
            }
            if (endDate != null)
            {
                queryString += " and po.formDate <= ?";
                argVector.add(endDate);
            }
            return queryString;
    }

    public void deleteSubjectdetail(SubjectDetail subjectDetail) {
        this.utilities.deleteObject(subjectDetail);
    }

    public List<SubjectDetail> findSubjectDetails(Date startDate, Date endDate, Subject subject, Company company, int subjectType)
    {
        String queryString = "from SubjectDetail as po where po.company=?";
        Vector argVector = new Vector();
        argVector.add(company);
        queryString = this.constructSubjectQueryString(startDate, endDate, subject, queryString, argVector);
        List<SubjectDetail> pos = (List<SubjectDetail>)this.utilities.find(queryString, argVector.toArray());
        if (subjectType < 0)
        {
            return pos;
        }
        List<SubjectDetail> rPos = new ArrayList<SubjectDetail>();
        switch(subjectType)
        {
            case ServiceConstants.SUBJECT_CATALOG_BANK_CASH:
                for(SubjectDetail detail:pos)
                {
                    if (detail.getSubject().getCatalog().getId() == ServiceConstants.SUBJECT_CATALOG_BANK_CASH)
                    {
                        rPos.add(detail);
                    }
                }
                break;
            case ServiceConstants.SUBJECT_CATALOG_BUSSINESS_EXPENSE:
                for(SubjectDetail detail:pos)
                {
                    if (detail.getSubject().getCatalog().getId() == ServiceConstants.SUBJECT_CATALOG_BUSSINESS_EXPENSE)
                    {
                        rPos.add(detail);
                    }
                }
                break;
             case ServiceConstants.SUBJECT_CATALOG_COMMON_EXPENSE:
                for(SubjectDetail detail:pos)
                {
                    if (detail.getSubject().getCatalog().getId() == ServiceConstants.SUBJECT_CATALOG_COMMON_EXPENSE)
                    {
                        rPos.add(detail);
                    }
                }
                break;
             case ServiceConstants.SUBJECT_CATALOG_OTHER_INCOME:
                for(SubjectDetail detail:pos)
                {
                    if (detail.getSubject().getCatalog().getId() == ServiceConstants.SUBJECT_CATALOG_OTHER_INCOME)
                    {
                        rPos.add(detail);
                    }
                }
                break;
            default:
                return pos;
        }
        return rPos;
    }

    private String constructFormQueryString(Date startDate, Date endDate, Customer customer, Employee employee, String qString, Vector argVector)
    {
            String queryString = qString;
            if (customer != null)
            {
                queryString += " and po.customer=?";
                argVector.add(customer);
            }
            if (startDate != null)
            {
                queryString += " and po.formDate >= ?";
                argVector.add(startDate);
            }
            if (endDate != null)
            {
                queryString += " and po.formDate <= ?";
                argVector.add(endDate);
            }
            if (employee != null)
            {
                queryString += " and po.employee =?";
                argVector.add(employee);
            }

            return queryString;
    }

    public List<Payable> findPayable(Date startDate, Date endDate, Customer customer, Employee employee, Company company) {
        String queryString = "from Payable as po where po.company=?";
        Vector argVector = new Vector();
        argVector.add(company);
        queryString = this.constructFormQueryString(startDate, endDate, customer, employee, queryString, argVector);
        return  (List<Payable>)this.utilities.find(queryString, argVector.toArray());

    }

    public List<Receivable> findReceivable(Date startDate, Date endDate, Customer customer, Employee employee, Company company) {
        String queryString = "from Receivable as po where po.company=?";
        Vector argVector = new Vector();
        argVector.add(company);
        queryString = this.constructFormQueryString(startDate, endDate, customer, employee, queryString, argVector);
        return  (List<Receivable>)this.utilities.find(queryString, argVector.toArray());

    }

    public void saveSubjectdetail(SubjectDetail subjectDetail) {
        this.utilities.saveObject(subjectDetail);
    }



    private String constructCustomerQueryString(Date startDate, Date endDate, Customer customer, String qString, Vector argVector)
    {
            String queryString = qString;
            if (customer != null)
            {
                queryString += " and po.customer=?";
                argVector.add(customer);
            }
            if (startDate != null)
            {
                queryString += " and po.formDate >= ?";
                argVector.add(startDate);
            }
            if (endDate != null)
            {
                queryString += " and po.formDate <= ?";
                argVector.add(endDate);
            }
            return queryString;
    }

    public List<CustomerPaymentDetail> findCustomerPaymentDetails(Date startDate, Date endDate, Customer customer, Company company) {
        String queryString = "from CustomerPaymentDetail as po where po.company=?";
        Vector argVector = new Vector();
        argVector.add(company);
        queryString = this.constructCustomerQueryString(startDate, endDate, customer, queryString, argVector);
        return  (List<CustomerPaymentDetail>)this.utilities.find(queryString, argVector.toArray());
        
    }

    public List<CustomerReceivementDetail> findCustomerReceivementDetails(Date startDate, Date endDate, Customer customer, Company company) {
        String queryString = "from CustomerReceivementDetail as po where po.company=?";
        Vector argVector = new Vector();
        argVector.add(company);
        queryString = this.constructCustomerQueryString(startDate, endDate, customer, queryString, argVector);
        return  (List<CustomerReceivementDetail>)this.utilities.find(queryString, argVector.toArray());
      }

    //********liufei********
    public List<InitialSubject> findInitialSubjectForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(InitialSubject.class, vo);
    }
    public List<InitialPayable> findInitialPayableForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(InitialPayable.class, vo);
    }
    public List<InitialReceivable> findInitialReceivableForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(InitialReceivable.class, vo);
    }

    public List<PayableClearing> findPayableClearingForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(PayableClearing.class, vo);
    }
    
    public List<ReceivableClearing> findReceivableClearingForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(ReceivableClearing.class, vo);
    }

    public List<Prereceivable> findPrereceivableForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(Prereceivable.class, vo);
    }

    public List<Prepayable> findPrepayableForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(Prepayable.class, vo);
    }

    public List<BussinessExpense> findBussinessExpenseForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(BussinessExpense.class, vo);
    }

    public List<CommonExpense> findCommonExpenseForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(CommonExpense.class, vo);
    }

    public List<OtherIncome> findOtherIncomeForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(OtherIncome.class, vo);
    }

    public List<BankCash> findBankCashForms(AccountQueryVo vo)
    {
        return this.formDao.findAccountForms(BankCash.class, vo);
    }
    //***********************
    public CashTotalMoneyDetailsVO getCashTotalMoney(Company company)//liufei
    {
        double totalMoney = 0d;
        double payableMoney = 0d;
        List<Payable> payables = this.formDao.findAllPayables(company);
        for(Payable p : payables)
        {
            payableMoney += p.getPayableMoney();
        }
        double receivableMoney = 0d;
        List<Receivable> receivables = this.formDao.findAllReceivable(company);
        for(Receivable p : receivables)
        {
            receivableMoney += p.getReceivableMoney();
        }
        double cashMoney = 0d;
        List<SubjectAccount> accounts = this.formDao.getSubjectAccount(company);
        for(SubjectAccount sa : accounts)
        {
            cashMoney += sa.getRemainMoney();
        }
        totalMoney = cashMoney + this.formDao.getCustomerPayableAccount(company) - this.formDao.getCustomerReceivableAccount(company) + receivableMoney - payableMoney;
        CashTotalMoneyDetailsVO detailsVO = new CashTotalMoneyDetailsVO();
        detailsVO.setCashMoney(totalMoney);
        detailsVO.setPayableMoney(payableMoney);
        detailsVO.setPrePayableMoney(this.formDao.getCustomerPayableAccount(company));
        detailsVO.setReceivableMoney(receivableMoney);
        detailsVO.setPreReceivableMoney(this.formDao.getCustomerReceivableAccount(company));
        return detailsVO;
    }

    public List<SubjectDetail> findSubjectDetails(Company company)//liufei
    {
        return this.baseDao.findSubjectDetails(company);
    }

    public List<CustomerReceivementDetail> getCustomerReceivementDetails(Company company)
    {
        return this.baseDao.getCustomerReceivementDetails(company);
    }

    public List<CustomerReceivableAccount> getCustomerReceivableAccounts(Company company)
    {
        return this.baseDao.getCustomerReceivableAccounts(company);
    }

    public List<CustomerPaymentDetail> getCustomerPaymentDetails(Company company)
    {
        return this.baseDao.getCustomerPaymentDetails(company);
    }

    public List<CustomerPayableAccount> getCustomerPayableAccounts(Company company)
    {
        return this.baseDao.getCustomerPayableAccounts(company);
    }

    public List<ReceivableClearingDetail> getReceivableClearingDetails(List<ReceivableClearing> pos)
    {
        return this.formDao.getReceivableClearingDetails(pos);
    }

    public List<PayableClearingDetail> getPayableClearingDetails(List<PayableClearing> pos)
    {
        return this.formDao.getPayableClearingDetails(pos);
    }

    public List<InitialSubjectDetail> getInitialSubjectDetails(List<InitialSubject> pos)
    {
        return this.formDao.getInitialSubjectDetails(pos);
    }

    public List<BankCashDetail> getBankCashDetails(List<BankCash> pos)
    {
        return this.formDao.getBankCashDetails(pos);
    }

    public List<OtherIncomeDetail> getOtherIncomeDetails(List<OtherIncome> pos)
    {
        return this.formDao.getOtherIncomeDetails(pos);
    }

    public List<CommonExpenseDetail> getCommonExpenseDetails(List<CommonExpense> pos)
    {
        return this.formDao.getCommonExpenseDetails(pos);
    }

    public List<BussinessExpenseDetail> getBussinessExpenseDetails(List<BussinessExpense> pos)
    {
        return this.formDao.getBussinessExpenseDetails(pos);
    }

    public List<SubjectAccount> getSubjectAccounts(Company company)
    {
        return this.baseDao.getSubjectAccounts(company);
    }

    public void savePayable(Payable po)
    {
        this.utilities.saveObject(po);
    }

    public void saveRereceivable(Receivable po)
    {
        this.utilities.saveObject(po);
    }

    public void saveDailyReportPo(DailyReportPo po)
    {
        this.utilities.saveObject(po);
    }
}
