/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.accounting;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author afa
 */
public class AccountingFormDaoImpl extends HibernateDaoSupport implements AccountingFormDao{
    public List<BankCash> findAllBankCashForms(Company company) {
        return (List<BankCash>) this.getHibernateTemplate().find("from BankCash as type where type.company =?", new Object[]{company});

    }
    public List<BussinessExpense> findAllBussinessExpenseForms(Company company) {
        return (List<BussinessExpense>) this.getHibernateTemplate().find("from BussinessExpense as type where type.company =?", new Object[]{company});

    }

    public List<CommonExpense> findAllCommonExpenseForms(Company company) {
        return (List<CommonExpense>) this.getHibernateTemplate().find("from CommonExpense as type where type.company =?", new Object[]{company});

    }

    public List<OtherIncome> findAllOtherIncomeForms(Company company) {
        return (List<OtherIncome>) this.getHibernateTemplate().find("from OtherIncome as type where type.company =?", new Object[]{company});

    }

    public List<PayableClearing> findAllPayableClearingForms(Company company) {
       return (List<PayableClearing>) this.getHibernateTemplate().find("from PayableClearing as type where type.company =?", new Object[]{company});

    }

    public List<Prepayable> findAllPrepayableForms(Company company) {
        return (List<Prepayable>) this.getHibernateTemplate().find("from Prepayable as type where type.company =?", new Object[]{company});

    }

    public List<Prereceivable> findAllPrereceivableForms(Company company) {
       return (List<Prereceivable>) this.getHibernateTemplate().find("from Prereceivable as type where type.company =?", new Object[]{company});

    }

    public List<ReceivableClearing> findAllReceivableClearingForms(Company company) {
       return (List<ReceivableClearing>) this.getHibernateTemplate().find("from ReceivableClearing as type where type.company =?", new Object[]{company});

    }

    public List<InitialPayable> findAllInitialPayableForms(Company company) {
        return (List<InitialPayable>) this.getHibernateTemplate().find("from InitialPayable as type where type.company =?", new Object[]{company});

    }

    public List<InitialReceivable> findAllInitialReceivableForms(Company company) {
        return (List<InitialReceivable>) this.getHibernateTemplate().find("from InitialReceivable as type where type.company =?", new Object[]{company});

    }

    public List<InitialSubject> findAllInitialSubjectForms(Company company) {
        return (List<InitialSubject>) this.getHibernateTemplate().find("from InitialSubject as type where type.company =?", new Object[]{company});

    }

    public SubjectAccount findSubjectAccount(Subject subject) {
        List<SubjectAccount> list = (List<SubjectAccount>) this.getHibernateTemplate().find("from SubjectAccount as type where type.subject =?", new Object[]{subject});
        if (list != null && list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    public List<SubjectAccount> getSubjectAccount(Company company)//liufei
    {
        return (List<SubjectAccount>) this.getHibernateTemplate().find("from SubjectAccount as type where type.company =? and type.subject.mainSubjectCatalog.id =1 ", new Object[]{company});
    }

    public List<Payable> findAllPayables(Company company) {
        return (List<Payable>) this.getHibernateTemplate().find("from Payable as type where type.company =?", new Object[]{company});
    }

    public List<Receivable> findAllReceivable(Company company) {
        return (List<Receivable>) this.getHibernateTemplate().find("from Receivable as type where type.company =?", new Object[]{company});
    }

    public List<Payable> findPayables(Customer customer) {
        return (List<Payable>) this.getHibernateTemplate().find("from Payable as type where type.customer =?", new Object[]{customer});
    }

    public List<Receivable> findReceivable(Customer customer) {
        return (List<Receivable>) this.getHibernateTemplate().find("from Receivable as type where type.customer =?", new Object[]{customer});
    }

    public CustomerPayableAccount getCustomerPayableAccount(Customer customer) {
        List<CustomerPayableAccount> pos = (List<CustomerPayableAccount>)this.getHibernateTemplate().find("from CustomerPayableAccount as type where type.customer =?", new Object[]{customer});
        return pos.size() > 0?pos.get(0):null;
    }

    public CustomerReceivableAccount getCustomerReceivableAccount(Customer customer) {
       List<CustomerReceivableAccount> pos = (List<CustomerReceivableAccount>) this.getHibernateTemplate().find("from CustomerReceivableAccount as type where type.customer =?", new Object[]{customer});
        return pos.size() > 0?pos.get(0):null;
    }

    public List<?> find(String queryString, Object[] args) {
        return this.getHibernateTemplate().find(queryString, args);
    }

    public List<?> find(String queryString) {
        return this.getHibernateTemplate().find(queryString);
    }

    public List findAccountForms(Class clazz, AccountQueryVo vo)
	{
		String className = clazz.getName();
		String sql = "from " + className + " as form where";
		Vector objs = new Vector();
		if (vo.getCompany() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.company=? ";
			objs.add(vo.getCompany());
		}
		
        if (vo.getStartDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate>=? ";
			objs.add(vo.getStartDate());
		}
		if (vo.getEndDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate <=? ";
			objs.add(vo.getEndDate());
		}
		//debug:
		return this.getHibernateTemplate().find(sql, objs.toArray());
	}

    public double getCustomerPayableAccount(Company company)
    {
        List<CustomerPayableAccount> pos = (List<CustomerPayableAccount>)this.getHibernateTemplate().find("from CustomerPayableAccount as type where type.company =?", new Object[]{company});
        double accountMoney = 0d;
        for(CustomerPayableAccount po : pos)
        {
            accountMoney += po.getAccountMoney();
        }
        return accountMoney;
    }

    public double getCustomerReceivableAccount(Company company)
    {
        List<CustomerReceivableAccount> pos = (List<CustomerReceivableAccount>) this.getHibernateTemplate().find("from CustomerReceivableAccount as type where type.company =?", new Object[]{company});
        double accountMoney = 0d;
        for(CustomerReceivableAccount po : pos)
        {
            accountMoney += po.getAccountMoney();
        }
        return accountMoney;
    }

    public List<ReceivableClearingDetail> getReceivableClearingDetails(List<ReceivableClearing> pos) 
    {
        List<ReceivableClearingDetail> details = new ArrayList();
        for(ReceivableClearing po : pos)
        {
            List<ReceivableClearingDetail> ds = (List<ReceivableClearingDetail>)this.getHibernateTemplate().find("from ReceivableClearingDetail as detail where detail.mainObject =?", new Object[]{po});
            for(ReceivableClearingDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<PayableClearingDetail> getPayableClearingDetails(List<PayableClearing> pos)
    {
        List<PayableClearingDetail> details = new ArrayList();
        for(PayableClearing po : pos)
        {
            List<PayableClearingDetail> ds = (List<PayableClearingDetail>)this.getHibernateTemplate().find("from PayableClearingDetail as detail where detail.mainObject =?", new Object[]{po});
            for(PayableClearingDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<InitialSubjectDetail> getInitialSubjectDetails(List<InitialSubject> pos)
    {
        List<InitialSubjectDetail> details = new ArrayList();
        for(InitialSubject po : pos)
        {
            List<InitialSubjectDetail> ds = (List<InitialSubjectDetail>)this.getHibernateTemplate().find("from InitialSubjectDetail as detail where detail.mainObject =?", new Object[]{po});
            for(InitialSubjectDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<BankCashDetail> getBankCashDetails(List<BankCash> pos)
    {
        List<BankCashDetail> details = new ArrayList();
        for(BankCash po : pos)
        {
            List<BankCashDetail> ds = (List<BankCashDetail>)this.getHibernateTemplate().find("from BankCashDetail as detail where detail.mainObject =?", new Object[]{po});
            for(BankCashDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<OtherIncomeDetail> getOtherIncomeDetails(List<OtherIncome> pos)
    {
        List<OtherIncomeDetail> details = new ArrayList();
        for(OtherIncome po : pos)
        {
            List<OtherIncomeDetail> ds = (List<OtherIncomeDetail>)this.getHibernateTemplate().find("from OtherIncomeDetail as detail where detail.mainObject =?", new Object[]{po});
            for(OtherIncomeDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<CommonExpenseDetail> getCommonExpenseDetails(List<CommonExpense> pos)
    {
        List<CommonExpenseDetail> details = new ArrayList();
        for(CommonExpense po : pos)
        {
            List<CommonExpenseDetail> ds = (List<CommonExpenseDetail>)this.getHibernateTemplate().find("from CommonExpenseDetail as detail where detail.mainObject =?", new Object[]{po});
            for(CommonExpenseDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }

    public List<BussinessExpenseDetail> getBussinessExpenseDetails(List<BussinessExpense> pos)
    {
        List<BussinessExpenseDetail> details = new ArrayList();
        for(BussinessExpense po : pos)
        {
            List<BussinessExpenseDetail> ds = (List<BussinessExpenseDetail>)this.getHibernateTemplate().find("from BussinessExpenseDetail as detail where detail.mainObject =?", new Object[]{po});
            for(BussinessExpenseDetail d : ds)
            {
                details.add(d);
            }
        }
        return details;
    }
}
