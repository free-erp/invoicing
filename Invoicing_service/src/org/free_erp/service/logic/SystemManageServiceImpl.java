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
import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.constants.PrefixConstants;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.accounting.AccountingBaseDao;
import org.free_erp.service.dao.base.DaoUtilities;
import org.free_erp.service.dao.system.ISystemManageDao;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.system.CreateNumber;
import org.free_erp.service.entity.system.CustomerType;
import org.free_erp.service.entity.system.EmployeeDegree;
import org.free_erp.service.entity.system.EmployeeDuty;
import org.free_erp.service.entity.system.EmployeeHealth;
import org.free_erp.service.entity.system.EmployeeLevel;
import org.free_erp.service.entity.system.NumberPrefix;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.system.PriceName;
import org.free_erp.service.entity.system.ProductTaxrate;
import org.free_erp.service.entity.system.ProductUnit;
import org.free_erp.service.entity.system.StorageType;
import org.free_erp.service.entity.vo.SystemLogQueryVo;
import org.hibernate.Transaction;

//@WebService(serviceName = "SystemManageService", endpointInterface = "org.free_erp.service.service.ISystemManageService")
/**
 *
 * Changer liufei
 */
public class SystemManageServiceImpl implements ISystemManageService,java.io.Serializable
{
    private DaoUtilities utilities;
    private AccountingBaseDao baseDao;

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
    
	public User getUser(Company company, String userName)
	{
		return this.dao.findUser(company, userName);
	}

	public String getTestString()
	{
		return "测试成功！";
	}

	private ISystemManageDao dao;

	public ISystemManageDao getDao()
	{
		return dao;
	}

	public void setDao(ISystemManageDao dao)
	{
		this.dao = dao;
	}

	public boolean isValidUser(Company company, String userName, String password)
	{
		// TODO Auto-generated method stub
		return this.dao.isValidUser(company, userName, password);

	}

	public boolean isValidUser(int companyId, String userName, String password)
	{
		// TODO Auto-generated method stub
		return this.dao.isValidUser(companyId, userName, password);
	}

    public boolean isExistedUser(int companyId, String name)//liufei
    {
        return this.dao.isExistedUser(companyId, name);
    }

    public List<User> findAllUser(Company company)
    {
        return this.dao.findAllUser(company);
    }

	public Company getCompany(int companyId)
	{
		return this.dao.findCompany(companyId);
	}

    public void saveUser(User user)
    {
        dao.saveUser(user);
    }

    public void upDateUser(User user)
    {
        dao.upDateUser(user);
    }

    public void saveSystemLog(SystemLog systemLog)//liufei
    {
        this.dao.saveSystemLog(systemLog);
    }

    public void deleteSystemLogs(Company company)//liufei
    {
        this.dao.deleteSystemLogs(company);
    }

    public List<SystemLog> findSystemLogs(Company company)//liufei
    {
        return this.dao.findSystemLogs(company);
    }

    public List<SystemLog> findSystemLogs(SystemLogQueryVo vo)//liufei
    {
        return this.dao.findSystemLogs(vo);
    }

    public NumberPrefix getNumberPrefix(Company company)//liufei
    {
        NumberPrefix numberPrefix = this.dao.getNumberPrefix(company);
        if(numberPrefix == null)
        {
            numberPrefix = new NumberPrefix();
            numberPrefix.setCompany(company);

            numberPrefix.setProNum(PrefixConstants.PRO_NUM);
            numberPrefix.setCusNum(PrefixConstants.CUS_NUM);
            numberPrefix.setEmpNum(PrefixConstants.EMP_NUM);
            numberPrefix.setStoNum(PrefixConstants.STO_NUM);

            numberPrefix.setProCatalogNum(PrefixConstants.PRO_CATALOG_NUM);
            numberPrefix.setCusCatalogNum(PrefixConstants.CUS_CATALOG_NUM);
            numberPrefix.setEmpCatalogNum(PrefixConstants.EMP_CATALOG_NUM);

            numberPrefix.setInStoNum(PrefixConstants.IN_STO_NUM);
            numberPrefix.setOutStoNum(PrefixConstants.OUT_STO_NUM);
            numberPrefix.setOutFlowNum(PrefixConstants.OUT_FLOW_NUM);
            numberPrefix.setLossNum(PrefixConstants.LOSS_NUM);
            numberPrefix.setMoveNum(PrefixConstants.MOVE_NUM);
            numberPrefix.setCheckNum(PrefixConstants.CHECK_NUM);
            numberPrefix.setChangeNum(PrefixConstants.CHANGE_NUM);
            numberPrefix.setMinMaxNum(PrefixConstants.MIN_MAX_NUM);
            numberPrefix.setInitStoNum(PrefixConstants.INIT_STO_NUM);

            numberPrefix.setPurchaseIndentNum(PrefixConstants.PURCHASE_INDENT_NUM);
            numberPrefix.setPurchaseOrderNum(PrefixConstants.PURCHASE_ORDER_NUM);
            numberPrefix.setPurchaseBackNum(PrefixConstants.PURCHASE_BACK_NUM);
            
            numberPrefix.setSaleBeforeIncomeNum(PrefixConstants.SALE_BEFORE_INCOME_NUM);
            numberPrefix.setGatherBalanceNum(PrefixConstants.GATHER_BALANCE_NUM);
            numberPrefix.setPurchaseBeforePayNum(PrefixConstants.PURCHASE_BEFORE_PAY_NUM);
            numberPrefix.setPayBalanceNum(PrefixConstants.PAY_BALANCE_NUM);

            numberPrefix.setKeepFareNum(PrefixConstants.KEEP_FARE_NUM);
            numberPrefix.setGenalFareNum(PrefixConstants.GENAL_FARE_NUM);
            numberPrefix.setOtherIncomeNum(PrefixConstants.OTHER_INCOME_NUM);
            numberPrefix.setCashBankNum(PrefixConstants.CASH_BANK_NUM);

            numberPrefix.setProductQuoteNum(PrefixConstants.PRODUCT_QUOTE_NUM);
            numberPrefix.setSaleIndentNum(PrefixConstants.SALE_INDENT_NUM);
            numberPrefix.setSaleOrderNum(PrefixConstants.SALE_ORDER_NUM);
            numberPrefix.setChangePriceNum(PrefixConstants.CHANGE_PRICE_NUM);
            numberPrefix.setSaleBackNum(PrefixConstants.SALE_BACK_NUM);
            numberPrefix.setSubjectInitNum(PrefixConstants.SUBJECT_INIT_NUM);
            numberPrefix.setReceiveInitNum(PrefixConstants.RECEIVE_INIT_NUM);
            numberPrefix.setPayInitNum(PrefixConstants.PAY_INIT_NUM);

            this.saveNumberPrefix(numberPrefix);
        }
        return numberPrefix;
    }

    public void saveNumberPrefix(NumberPrefix numberPrefix)//liufei
    {
        this.dao.saveNumberPrefix(numberPrefix);
    }

    public String getOptionType(Company company, int type)//liufei
    {
        String optionType = "";
        NumberPrefix numberPrefix = this.dao.getNumberPrefix(company);
        if(numberPrefix != null)
        {
            if(type == NumberConstants.PRO_CATALOG_NUM)
            {
                if(numberPrefix.getProCatalogNum() != null)
                {
                    optionType = numberPrefix.getProCatalogNum();
                }
            }
            else if(type == NumberConstants.CUS_CATALOG_NUM)
            {
                if(numberPrefix.getCusCatalogNum() !=null)
                {
                    optionType = numberPrefix.getCusCatalogNum();
                }
            }
            else if(type == NumberConstants.EMP_CATALOG_NUM)
            {
                if(numberPrefix.getEmpCatalogNum() !=null)
                {
                    optionType = numberPrefix.getEmpCatalogNum();
                }
            }
            else if(type == NumberConstants.PRO_NUM)
            {
                if(numberPrefix.getProNum() !=null)
                {
                    optionType = numberPrefix.getProNum();
                }
            }
            else if(type == NumberConstants.CUS_NUM)
            {
                if(numberPrefix.getCusNum() !=null)
                {
                    optionType = numberPrefix.getCusNum();
                }
            }
            else if(type == NumberConstants.EMP_NUM)
            {
                if(numberPrefix.getEmpNum() !=null)
                {
                    optionType = numberPrefix.getEmpNum();
                }
            }
            else if(type == NumberConstants.STO_NUM)
            {
                if(numberPrefix.getStoNum() !=null)
                {
                    optionType = numberPrefix.getStoNum();
                }
            }
            else if(type == NumberConstants.IN_STO_NUM)
            {
                if(numberPrefix.getInStoNum() !=null)
                {
                    optionType = numberPrefix.getInStoNum();
                }
            }
            else if(type == NumberConstants.OUT_STO_NUM)
            {
                if(numberPrefix.getOutStoNum() !=null)
                {
                    optionType = numberPrefix.getOutStoNum();
                }
            }
            else if(type == NumberConstants.OUT_FLOW_NUM)
            {
                if(numberPrefix.getOutFlowNum() !=null)
                {
                    optionType = numberPrefix.getOutFlowNum();
                }
            }
            else if(type == NumberConstants.LOSS_NUM)
            {
                if(numberPrefix.getLossNum() !=null)
                {
                    optionType = numberPrefix.getLossNum();
                }
            }
            else if(type == NumberConstants.MOVE_NUM)
            {
                if(numberPrefix.getMoveNum() !=null)
                {
                    optionType = numberPrefix.getMoveNum();
                }
            }
            else if(type == NumberConstants.CHECK_NUM)
            {
                if(numberPrefix.getCheckNum() !=null)
                {
                    optionType = numberPrefix.getCheckNum();
                }
            }
            else if(type == NumberConstants.CHANGE_NUM)
            {
                if(numberPrefix.getChangeNum() !=null)
                {
                    optionType = numberPrefix.getChangeNum();
                }
            }
            else if(type == NumberConstants.MIN_MAX_NUM)
            {
                if(numberPrefix.getMinMaxNum() !=null)
                {
                    optionType = numberPrefix.getMinMaxNum();
                }
            }
            else if(type == NumberConstants.INIT_STO_NUM)
            {
                if(numberPrefix.getInitStoNum() !=null)
                {
                    optionType = numberPrefix.getInitStoNum();
                }
            }
            else if(type == NumberConstants.PURCHASE_INDENT_NUM)
            {
                if(numberPrefix.getPurchaseIndentNum() !=null)
                {
                    optionType = numberPrefix.getPurchaseIndentNum();
                }
            }
            else if(type == NumberConstants.PURCHASE_ORDER_NUM)
            {
                if(numberPrefix.getPurchaseOrderNum() !=null)
                {
                    optionType = numberPrefix.getPurchaseOrderNum();
                }
            }
            else if(type == NumberConstants.PURCHASE_BACK_NUM)
            {
                if(numberPrefix.getPurchaseBackNum() !=null)
                {
                    optionType = numberPrefix.getPurchaseBackNum();
                }
            }
            else if(type == NumberConstants.SALE_BEFORE_INCOME_NUM)
            {
                if(numberPrefix.getSaleBeforeIncomeNum() !=null)
                {
                    optionType = numberPrefix.getSaleBeforeIncomeNum();
                }
            }
            else if(type == NumberConstants.GATHER_BALANCE_NUM)
            {
                if(numberPrefix.getGatherBalanceNum() !=null)
                {
                    optionType = numberPrefix.getGatherBalanceNum();
                }
            }
            else if(type == NumberConstants.PURCHASE_BEFORE_PAY_NUM)
            {
                if(numberPrefix.getPurchaseBeforePayNum() !=null)
                {
                    optionType = numberPrefix.getPurchaseBeforePayNum();
                }
            }
            else if(type == NumberConstants.PAY_BALANCE_NUM)
            {
                if(numberPrefix.getPayBalanceNum() !=null)
                {
                    optionType = numberPrefix.getPayBalanceNum();
                }
            }
            else if(type == NumberConstants.KEEP_FARE_NUM)
            {
                if(numberPrefix.getKeepFareNum() !=null)
                {
                    optionType = numberPrefix.getKeepFareNum();
                }
            }
            else if(type == NumberConstants.GENAL_FARE_NUM)
            {
                if(numberPrefix.getGenalFareNum() !=null)
                {
                    optionType = numberPrefix.getGenalFareNum();
                }
            }
            else if(type == NumberConstants.OTHER_INCOME_NUM)
            {
                if(numberPrefix.getOtherIncomeNum() !=null)
                {
                    optionType = numberPrefix.getOtherIncomeNum();
                }
            }
            else if(type == NumberConstants.CASH_BANK_NUM)
            {
                if(numberPrefix.getCashBankNum() !=null)
                {
                    optionType = numberPrefix.getCashBankNum();
                }
            }
            else if(type == NumberConstants.SUBJECT_NUM)
            {
                if(numberPrefix.getSubjectNum() !=null)
                {
                    optionType = numberPrefix.getSubjectNum();
                }
            }
            else if(type == NumberConstants.PRODUCT_QUOTE_NUM)
            {
                if(numberPrefix.getProductQuoteNum() !=null)
                {
                    optionType = numberPrefix.getProductQuoteNum();
                }
            }
            else if(type == NumberConstants.SALE_INDENT_NUM)
            {
                if(numberPrefix.getSaleIndentNum() !=null)
                {
                    optionType = numberPrefix.getSaleIndentNum();
                }
            }
            else if(type == NumberConstants.SALE_ORDER_NUM)
            {
                if(numberPrefix.getSaleOrderNum() !=null)
                {
                    optionType = numberPrefix.getSaleOrderNum();
                }
            }
            else if(type == NumberConstants.CHANGE_PRICE_NUM)
            {
                if(numberPrefix.getChangePriceNum() !=null)
                {
                    optionType = numberPrefix.getChangePriceNum();
                }
            }
            else if(type == NumberConstants.SALE_BACK_NUM)
            {
                if(numberPrefix.getSaleBackNum() !=null)
                {
                    optionType = numberPrefix.getSaleBackNum();
                }
            }
            else if(type == NumberConstants.SUBJECT_INIT_NUM)
            {
                if(numberPrefix.getSubjectInitNum() !=null)
                {
                    optionType = numberPrefix.getSubjectInitNum();
                }
            }
            else if(type == NumberConstants.RECEIVE_INIT_NUM)
            {
                if(numberPrefix.getReceiveInitNum() !=null)
                {
                    optionType = numberPrefix.getReceiveInitNum();
                }
            }
            else if(type == NumberConstants.PAY_INIT_NUM)
            {
                if(numberPrefix.getPayInitNum() !=null)
                {
                    optionType = numberPrefix.getPayInitNum();
                }
            }
        }
        else
        {
            if(type == NumberConstants.PRO_CATALOG_NUM)
            {
                optionType = PrefixConstants.PRO_CATALOG_NUM;
            }
            else if(type == NumberConstants.CUS_CATALOG_NUM)
            {
                optionType = PrefixConstants.CUS_CATALOG_NUM;
            }
            else if(type == NumberConstants.EMP_CATALOG_NUM)
            {
                optionType = PrefixConstants.EMP_CATALOG_NUM;
            }
            else if(type == NumberConstants.PRO_NUM)
            {
                optionType = PrefixConstants.PRO_NUM;
            }
            else if(type == NumberConstants.CUS_NUM)
            {
                optionType = PrefixConstants.CUS_NUM;
            }
            else if(type == NumberConstants.EMP_NUM)
            {
                optionType = PrefixConstants.EMP_NUM;
            }
            else if(type == NumberConstants.STO_NUM)
            {
                optionType = PrefixConstants.STO_NUM;
            }
            else if(type == NumberConstants.IN_STO_NUM)
            {
                optionType = PrefixConstants.IN_STO_NUM;
            }
            else if(type == NumberConstants.OUT_STO_NUM)
            {
                optionType = PrefixConstants.OUT_STO_NUM;
            }
            else if(type == NumberConstants.OUT_FLOW_NUM)
            {
                optionType = PrefixConstants.OUT_FLOW_NUM;
            }
            else if(type == NumberConstants.LOSS_NUM)
            {
                optionType = PrefixConstants.LOSS_NUM;
            }
            else if(type == NumberConstants.MOVE_NUM)
            {
                optionType = PrefixConstants.MOVE_NUM;
            }
            else if(type == NumberConstants.CHECK_NUM)
            {
                optionType = PrefixConstants.CHECK_NUM;
            }
            else if(type == NumberConstants.CHANGE_NUM)
            {
                optionType = PrefixConstants.CHANGE_NUM;
            }
            else if(type == NumberConstants.MIN_MAX_NUM)
            {
                optionType = PrefixConstants.MIN_MAX_NUM;
            }
            else if(type == NumberConstants.INIT_STO_NUM)
            {
                optionType = PrefixConstants.INIT_STO_NUM;
            }
            else if(type == NumberConstants.PURCHASE_INDENT_NUM)
            {
                optionType = PrefixConstants.PURCHASE_INDENT_NUM;
            }
            else if(type == NumberConstants.PURCHASE_ORDER_NUM)
            {
                optionType = PrefixConstants.PURCHASE_ORDER_NUM;
            }
            else if(type == NumberConstants.PURCHASE_BACK_NUM)
            {
                optionType = PrefixConstants.PURCHASE_BACK_NUM;
            }
            else if(type == NumberConstants.SALE_BEFORE_INCOME_NUM)
            {
                optionType = PrefixConstants.SALE_BEFORE_INCOME_NUM;
            }
            else if(type == NumberConstants.GATHER_BALANCE_NUM)
            {
                optionType = PrefixConstants.GATHER_BALANCE_NUM;
            }
            else if(type == NumberConstants.PURCHASE_BEFORE_PAY_NUM)
            {
                optionType = PrefixConstants.PURCHASE_BEFORE_PAY_NUM;
            }
            else if(type == NumberConstants.PAY_BALANCE_NUM)
            {
                optionType = PrefixConstants.PAY_BALANCE_NUM;
            }
            else if(type == NumberConstants.KEEP_FARE_NUM)
            {
                optionType = PrefixConstants.KEEP_FARE_NUM;
            }
            else if(type == NumberConstants.GENAL_FARE_NUM)
            {
                optionType = PrefixConstants.GENAL_FARE_NUM;
            }
            else if(type == NumberConstants.OTHER_INCOME_NUM)
            {
                optionType = PrefixConstants.OTHER_INCOME_NUM;
            }
            else if(type == NumberConstants.CASH_BANK_NUM)
            {
                optionType = PrefixConstants.CASH_BANK_NUM;
            }
            else if(type == NumberConstants.SUBJECT_NUM)
            {
                optionType = PrefixConstants.SUBJECT_NUM;
            }
            else if(type == NumberConstants.PRODUCT_QUOTE_NUM)
            {
                optionType = PrefixConstants.PRODUCT_QUOTE_NUM;
            }
            else if(type == NumberConstants.SALE_INDENT_NUM)
            {
                optionType = PrefixConstants.SALE_INDENT_NUM;
            }
            else if(type == NumberConstants.SALE_ORDER_NUM)
            {
                optionType = PrefixConstants.SALE_ORDER_NUM;
            }
            else if(type == NumberConstants.CHANGE_PRICE_NUM)
            {
                optionType = PrefixConstants.CHANGE_PRICE_NUM;
            }
            else if(type == NumberConstants.SALE_BACK_NUM)
            {
                optionType = PrefixConstants.SALE_BACK_NUM;
            }///
            else if(type == NumberConstants.SUBJECT_INIT_NUM)
            {
                optionType = PrefixConstants.SUBJECT_INIT_NUM;
            }
            else if(type == NumberConstants.RECEIVE_INIT_NUM)
            {
                optionType = PrefixConstants.RECEIVE_INIT_NUM;
            }
            else if(type == NumberConstants.PAY_INIT_NUM)
            {
                optionType = PrefixConstants.PAY_INIT_NUM;
            }
        }
        return optionType;
    }

    public void saveAutoNumber(CreateNumber createNumber)//liufei
    {
        this.dao.saveAutoNumber(createNumber);
    }

    public String getAutoNumber(Company company, int type)//liufei
    {
        String strNumber = "1";
        int number = 1;
        CreateNumber createNumber = this.dao.getAutoNumber(company);
        if(createNumber == null)
        {
            createNumber = new CreateNumber();
            if(type == NumberConstants.PRO_CATALOG_NUM)
            {
                createNumber.setProCatalogNum(2);
            }
            else
            {
                createNumber.setProCatalogNum(1);
            }
            if(type == NumberConstants.CUS_CATALOG_NUM)
            {
                createNumber.setCusCatalogNum(2);
            }
            else
            {
                createNumber.setCusCatalogNum(1);
            }
            if(type == NumberConstants.EMP_CATALOG_NUM)
            {
                createNumber.setEmpCatalogNum(2);
            }
            else
            {
                createNumber.setEmpCatalogNum(1);
            }
            if(type == NumberConstants.PRO_NUM)
            {
                createNumber.setProNum(2);
            }
            else
            {
                createNumber.setProNum(1);
            }
            if(type == NumberConstants.CUS_NUM)
            {
                createNumber.setCusNum(2);
            }
            else
            {
                createNumber.setCusNum(1);
            }
            if(type == NumberConstants.EMP_NUM)
            {
                createNumber.setEmpNum(2);
            }
            else
            {
                createNumber.setEmpNum(1);
            }
            if(type == NumberConstants.STO_NUM)
            {
                createNumber.setStoNum(2);
            }
            else
            {
                createNumber.setStoNum(1);
            }
            if(type == NumberConstants.IN_STO_NUM)
            {
                createNumber.setInStoNum(2);
            }
            else
            {
                createNumber.setInStoNum(1);
            }
            if(type == NumberConstants.OUT_STO_NUM)
            {
                createNumber.setOutStoNum(2);
            }
            else
            {
                createNumber.setOutStoNum(1);
            }
            if(type == NumberConstants.OUT_FLOW_NUM)
            {
                createNumber.setOutFlowNum(2);
            }
            else
            {
                createNumber.setOutFlowNum(1);
            }
            if(type == NumberConstants.LOSS_NUM)
            {
                createNumber.setLossNum(2);
            }
            else
            {
                createNumber.setLossNum(1);
            }
            if(type == NumberConstants.MOVE_NUM)
            {
                createNumber.setMoveNum(2);
            }
            else
            {
                createNumber.setMoveNum(1);
            }
            if(type == NumberConstants.CHECK_NUM)
            {
                createNumber.setCheckNum(2);
            }
            else
            {
                createNumber.setCheckNum(1);
            }
            if(type == NumberConstants.CHANGE_NUM)
            {
                createNumber.setChangeNum(2);
            }
            else
            {
                createNumber.setChangeNum(1);
            }
            if(type == NumberConstants.MIN_MAX_NUM)
            {
                createNumber.setMinMaxNum(2);
            }
            else
            {
                createNumber.setMinMaxNum(1);
            }
            if(type == NumberConstants.INIT_STO_NUM)
            {
                createNumber.setInitStoNum(2);
            }
            else
            {
                createNumber.setInitStoNum(1);
            }
            if(type == NumberConstants.PURCHASE_INDENT_NUM)
            {
                createNumber.setPurchaseIndentNum(2);
            }
            else
            {
                createNumber.setPurchaseIndentNum(1);
            }
            if(type == NumberConstants.PURCHASE_ORDER_NUM)
            {
                createNumber.setPurchaseOrderNum(2);
            }
            else
            {
                createNumber.setPurchaseOrderNum(1);
            }
            if(type == NumberConstants.PURCHASE_BACK_NUM)
            {
                createNumber.setPurchaseBackNum(2);
            }
            else
            {
                createNumber.setPurchaseBackNum(1);
            }
            if(type == NumberConstants.SALE_BEFORE_INCOME_NUM)
            {
                createNumber.setSaleBeforeIncomeNum(2);
            }
            else
            {
                createNumber.setSaleBeforeIncomeNum(1);
            }
            if(type == NumberConstants.GATHER_BALANCE_NUM)
            {
                createNumber.setGatherBalanceNum(2);
            }
            else
            {
                createNumber.setGatherBalanceNum(1);
            }
            if(type == NumberConstants.PURCHASE_BEFORE_PAY_NUM)
            {
                createNumber.setPurchaseBeforePayNum(2);
            }
            else
            {
                createNumber.setPurchaseBeforePayNum(1);
            }
            if(type == NumberConstants.PAY_BALANCE_NUM)
            {
                createNumber.setPayBalanceNum(2);
            }
            else
            {
                createNumber.setPayBalanceNum(1);
            }
            if(type == NumberConstants.KEEP_FARE_NUM)
            {
                createNumber.setKeepFareNum(2);
            }
            else
            {
                createNumber.setKeepFareNum(1);
            }
            if(type == NumberConstants.GENAL_FARE_NUM)
            {
                createNumber.setGenalFareNum(2);
            }
            else
            {
                createNumber.setGenalFareNum(1);
            }
            if(type == NumberConstants.OTHER_INCOME_NUM)
            {
                createNumber.setOtherIncomeNum(2);
            }
            else
            {
                createNumber.setOtherIncomeNum(1);
            }
            if(type == NumberConstants.CASH_BANK_NUM)
            {
                createNumber.setCashBankNum(2);
            }
            else
            {
                createNumber.setCashBankNum(1);
            }
            if(type == NumberConstants.SUBJECT_NUM)
            {
                createNumber.setSubjectNum(2);
            }
            else
            {
                createNumber.setSubjectNum(1);
            }
            if(type == NumberConstants.PRODUCT_QUOTE_NUM)
            {
                createNumber.setProductQuoteNum(2);
            }
            else
            {
                createNumber.setProductQuoteNum(1);
            }
            if(type == NumberConstants.SALE_INDENT_NUM)
            {
                createNumber.setSaleIndentNum(2);
            }
            else
            {
                createNumber.setSaleIndentNum(1);
            }
            if(type == NumberConstants.SALE_ORDER_NUM)
            {
                createNumber.setSaleOrderNum(2);
            }
            else
            {
                createNumber.setSaleOrderNum(1);
            }
            if(type == NumberConstants.CHANGE_PRICE_NUM)
            {
                createNumber.setChangePriceNum(2);
            }
            else
            {
                createNumber.setChangePriceNum(1);
            }
            if(type == NumberConstants.SALE_BACK_NUM)
            {
                createNumber.setSaleBackNum(2);
            }
            else
            {
                createNumber.setSaleBackNum(1);
            }////
            if(type == NumberConstants.SUBJECT_INIT_NUM)
            {
                createNumber.setSubjectInitNum(2);
            }
            else
            {
                createNumber.setSubjectInitNum(1);
            }
            if(type == NumberConstants.RECEIVE_INIT_NUM)
            {
                createNumber.setReceiveInitNum(2);
            }
            else
            {
                createNumber.setReceiveInitNum(1);
            }
            if(type == NumberConstants.PAY_INIT_NUM)
            {
                createNumber.setPayInitNum(2);
            }
            else
            {
                createNumber.setPayInitNum(1);
            }
            createNumber.setCompany(company);
            this.saveAutoNumber(createNumber);
        }
        else
        {
            if(type == NumberConstants.PRO_CATALOG_NUM)
            {
                if(createNumber.getProCatalogNum() != null)
                {
                    strNumber = createNumber.getProCatalogNum().toString();
                    number = createNumber.getProCatalogNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setProCatalogNum(number);
            }
            else if(type == NumberConstants.CUS_CATALOG_NUM)
            {
                if(createNumber.getCusCatalogNum() != null)
                {
                    strNumber = createNumber.getCusCatalogNum().toString();
                    number = createNumber.getCusCatalogNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setCusCatalogNum(number);
            }
            else if(type == NumberConstants.EMP_CATALOG_NUM)
            {
                if(createNumber.getEmpCatalogNum() != null)
                {
                    strNumber = createNumber.getEmpCatalogNum().toString();
                    number = createNumber.getEmpCatalogNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setEmpCatalogNum(number);
            }
            else if(type == NumberConstants.PRO_NUM)
            {
                if(createNumber.getProNum() != null)
                {
                    strNumber = createNumber.getProNum().toString();
                    number = createNumber.getProNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setProNum(number);
            }
            else if(type == NumberConstants.CUS_NUM)
            {
                if(createNumber.getCusNum() != null)
                {
                    strNumber = createNumber.getCusNum().toString();
                    number = createNumber.getCusNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setCusNum(number);
            }
            else if(type == NumberConstants.EMP_NUM)
            {
                if(createNumber.getEmpNum() != null)
                {
                    strNumber = createNumber.getEmpNum().toString();
                    number = createNumber.getEmpNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setEmpNum(number);
            }
            else if(type == NumberConstants.STO_NUM)
            {
                if(createNumber.getStoNum() != null)
                {
                    strNumber = createNumber.getStoNum().toString();
                    number = createNumber.getStoNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setStoNum(number);
            }
            else if(type == NumberConstants.IN_STO_NUM)
            {
                if(createNumber.getInStoNum() != null)
                {
                    strNumber = createNumber.getInStoNum().toString();
                    number = createNumber.getInStoNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setInStoNum(number);
            }
            else if(type == NumberConstants.OUT_STO_NUM)
            {
                if(createNumber.getOutStoNum() != null)
                {
                    strNumber = createNumber.getOutStoNum().toString();
                    number = createNumber.getOutStoNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setOutStoNum(number);
            }
            else if(type == NumberConstants.OUT_FLOW_NUM)
            {
                if(createNumber.getOutFlowNum() != null)
                {
                    strNumber = createNumber.getOutFlowNum().toString();
                    number = createNumber.getOutFlowNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setOutFlowNum(number);
            }
            else if(type == NumberConstants.LOSS_NUM)
            {
                if(createNumber.getLossNum() != null)
                {
                    strNumber = createNumber.getLossNum().toString();
                    number = createNumber.getLossNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setLossNum(number);
            }
            else if(type == NumberConstants.MOVE_NUM)
            {
                if(createNumber.getMoveNum() != null)
                {
                    strNumber = createNumber.getMoveNum().toString();
                    number = createNumber.getMoveNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setMoveNum(number);
            }
            else if(type == NumberConstants.CHECK_NUM)
            {
                if(createNumber.getCheckNum() != null)
                {
                    strNumber = createNumber.getCheckNum().toString();
                    number = createNumber.getCheckNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setCheckNum(number);
            }
            else if(type == NumberConstants.CHANGE_NUM)
            {
                if(createNumber.getChangeNum() != null)
                {
                    strNumber = createNumber.getChangeNum().toString();
                    number = createNumber.getChangeNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setChangeNum(number);
            }
            else if(type == NumberConstants.MIN_MAX_NUM)
            {
                if(createNumber.getMinMaxNum() != null)
                {
                    strNumber = createNumber.getMinMaxNum().toString();
                    number = createNumber.getMinMaxNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setMinMaxNum(number);
            }
            else if(type == NumberConstants.INIT_STO_NUM)
            {
                if(createNumber.getInitStoNum() != null)
                {
                    strNumber = createNumber.getInitStoNum().toString();
                    number = createNumber.getInitStoNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setInitStoNum(number);
            }
            else if(type == NumberConstants.PURCHASE_INDENT_NUM)
            {
                if(createNumber.getPurchaseIndentNum() != null)
                {
                    strNumber = createNumber.getPurchaseIndentNum().toString();
                    number = createNumber.getPurchaseIndentNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPurchaseIndentNum(number);
            }
            else if(type == NumberConstants.PURCHASE_ORDER_NUM)
            {
                if(createNumber.getPurchaseOrderNum() != null)
                {
                    strNumber = createNumber.getPurchaseOrderNum().toString();
                    number = createNumber.getPurchaseOrderNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPurchaseOrderNum(number);
            }
            else if(type == NumberConstants.PURCHASE_BACK_NUM)
            {
                if(createNumber.getPurchaseBackNum() != null)
                {
                    strNumber = createNumber.getPurchaseBackNum().toString();
                    number = createNumber.getPurchaseBackNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPurchaseBackNum(number);
            }
            else if(type == NumberConstants.SALE_BEFORE_INCOME_NUM)
            {
                if(createNumber.getSaleBeforeIncomeNum() != null)
                {
                    strNumber = createNumber.getSaleBeforeIncomeNum().toString();
                    number = createNumber.getSaleBeforeIncomeNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSaleBeforeIncomeNum(number);
            }
            else if(type == NumberConstants.GATHER_BALANCE_NUM)
            {
                if(createNumber.getGatherBalanceNum() != null)
                {
                    strNumber = createNumber.getGatherBalanceNum().toString();
                    number = createNumber.getGatherBalanceNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setGatherBalanceNum(number);
            }
            else if(type == NumberConstants.PURCHASE_BEFORE_PAY_NUM)
            {
                if(createNumber.getPurchaseBeforePayNum() != null)
                {
                    strNumber = createNumber.getPurchaseBeforePayNum().toString();
                    number = createNumber.getPurchaseBeforePayNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPurchaseBeforePayNum(number);
            }
            else if(type == NumberConstants.PAY_BALANCE_NUM)
            {
                if(createNumber.getPayBalanceNum() != null)
                {
                    strNumber = createNumber.getPayBalanceNum().toString();
                    number = createNumber.getPayBalanceNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPayBalanceNum(number);
            }
            else if(type == NumberConstants.KEEP_FARE_NUM)
            {
                if(createNumber.getKeepFareNum() != null)
                {
                    strNumber = createNumber.getKeepFareNum().toString();
                    number = createNumber.getKeepFareNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setKeepFareNum(number);
            }
            else if(type == NumberConstants.GENAL_FARE_NUM)
            {
                if(createNumber.getGenalFareNum() != null)
                {
                    strNumber = createNumber.getGenalFareNum().toString();
                    number = createNumber.getGenalFareNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setGenalFareNum(number);
            }
            else if(type == NumberConstants.OTHER_INCOME_NUM)
            {
                if(createNumber.getOtherIncomeNum() != null)
                {
                    strNumber = createNumber.getOtherIncomeNum().toString();
                    number = createNumber.getOtherIncomeNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setOtherIncomeNum(number);
            }
            else if(type == NumberConstants.CASH_BANK_NUM)
            {
                if(createNumber.getCashBankNum() != null)
                {
                    strNumber = createNumber.getCashBankNum().toString();
                    number = createNumber.getCashBankNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setCashBankNum(number);
            }
            else if(type == NumberConstants.SUBJECT_NUM)
            {
                if(createNumber.getSubjectNum() != null)
                {
                    strNumber = createNumber.getSubjectNum().toString();
                    number = createNumber.getSubjectNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSubjectNum(number);
            }/////
            else if(type == NumberConstants.PRODUCT_QUOTE_NUM)
            {
                if(createNumber.getProductQuoteNum() != null)
                {
                    strNumber = createNumber.getProductQuoteNum().toString();
                    number = createNumber.getProductQuoteNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setProductQuoteNum(number);
            }
            else if(type == NumberConstants.SALE_INDENT_NUM)
            {
                if(createNumber.getSaleIndentNum() != null)
                {
                    strNumber = createNumber.getSaleIndentNum().toString();
                    number = createNumber.getSaleIndentNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSaleIndentNum(number);
            }
            else if(type == NumberConstants.SALE_ORDER_NUM)
            {
                if(createNumber.getSaleOrderNum() != null)
                {
                    strNumber = createNumber.getSaleOrderNum().toString();
                    number = createNumber.getSaleOrderNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSaleOrderNum(number);
            }
            else if(type == NumberConstants.CHANGE_PRICE_NUM)
            {
                if(createNumber.getChangePriceNum() != null)
                {
                    strNumber = createNumber.getChangePriceNum().toString();
                    number = createNumber.getChangePriceNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setChangePriceNum(number);
            }
            else if(type == NumberConstants.SALE_BACK_NUM)
            {
                if(createNumber.getSaleBackNum() != null)
                {
                    strNumber = createNumber.getSaleBackNum().toString();
                    number = createNumber.getSaleBackNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSaleBackNum(number);
            }
            else if(type == NumberConstants.SUBJECT_INIT_NUM)
            {
                if(createNumber.getSubjectInitNum() != null)
                {
                    strNumber = createNumber.getSubjectInitNum().toString();
                    number = createNumber.getSubjectInitNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setSubjectInitNum(number);
            }
            else if(type == NumberConstants.RECEIVE_INIT_NUM)
            {
                if(createNumber.getReceiveInitNum() != null)
                {
                    strNumber = createNumber.getReceiveInitNum().toString();
                    number = createNumber.getReceiveInitNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setReceiveInitNum(number);
            }
            else if(type == NumberConstants.PAY_INIT_NUM)
            {
                if(createNumber.getPayInitNum() != null)
                {
                    strNumber = createNumber.getPayInitNum().toString();
                    number = createNumber.getPayInitNum() + 1;
                }
                else
                {
                    number = 2;
                }
                createNumber.setPayInitNum(number);
            }
            this.saveAutoNumber(createNumber);
        }
        if(strNumber.length() == 1)
        {
            strNumber = "000" + strNumber;
        }
        else if(strNumber.length() == 2)
        {
            strNumber = "00" + strNumber;
        }
        else if(strNumber.length() == 3)
        {
            strNumber = "0" + strNumber;
        }
        return strNumber;
    }

    public List<SystemLog> findLatestLogs(int id) {
        return this.dao.findLatestLogs(id);
    }

    public String getAutomaticNumber(Company company, int numType)
    {
        return this.getOptionType(company, numType) + this.numberDate() + this.getAutoNumber(company, numType);
    }

    public String getSubjectNumber(Company company, int numType, int catageID)
    {
        String subjectNumber = "";
        int number = 1;
        CreateNumber createNumber = this.dao.getAutoNumber(company);
        if(createNumber == null)
        {
            createNumber = new CreateNumber();
            if(numType == NumberConstants.SUBJECT_NUM)
            {
                createNumber.setSubjectNum(2);
            }
            createNumber.setCompany(company);
            this.saveAutoNumber(createNumber);
        }
        else
        {
            if(numType == NumberConstants.SUBJECT_NUM)
            {
                if(createNumber.getSubjectNum() != null)
                {
                    number = createNumber.getSubjectNum();
                    createNumber.setSubjectNum(number + 1);
                }
                else
                {
                    createNumber.setSubjectNum(2);
                }
            }
            this.saveAutoNumber(createNumber);
        }
        number +=  catageID*100;
        subjectNumber = String.valueOf(number);
        return subjectNumber;
    }

    public String numberDate()
    {
        String strDate = "";
        Date date = new Date();
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        strDate = String.valueOf(year) + (month > 9 ? String.valueOf(month) :"0"+String.valueOf(month)) + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day));
        return strDate;
    }

    public void addCompanyAccount(Company company,User user)//liufei
    {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            ProductUnit productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("只");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("个");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("条");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("瓶");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("盒");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("箱");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("克");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("千克");
            this.utilities.saveObject(productUnit);

            productUnit = new ProductUnit();
            productUnit.setCompany(company);
            productUnit.setProUnit("吨");
            this.utilities.saveObject(productUnit);

            ProductTaxrate productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(0);
            this.utilities.saveObject(productTaxrate);

            productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(4);
            this.utilities.saveObject(productTaxrate);

            productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(6);
            this.utilities.saveObject(productTaxrate);

            productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(10);
            this.utilities.saveObject(productTaxrate);

            productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(13);
            this.utilities.saveObject(productTaxrate);

            productTaxrate = new ProductTaxrate();
            productTaxrate.setCompany(company);
            productTaxrate.setProTaxrate(17);
            this.utilities.saveObject(productTaxrate);

            CustomerType customerType = new CustomerType();
            customerType.setCompany(company);
            customerType.setCusType("大客户");
            this.utilities.saveObject(customerType);

            customerType = new CustomerType();
            customerType.setCompany(company);
            customerType.setCusType("潜在客户");
            this.utilities.saveObject(customerType);

            EmployeeDuty employeeDuty = new EmployeeDuty();
            employeeDuty.setCompany(company);
            employeeDuty.setEmpDuty("总经理");
            this.utilities.saveObject(employeeDuty);

            employeeDuty = new EmployeeDuty();
            employeeDuty.setCompany(company);
            employeeDuty.setEmpDuty("总裁");
            this.utilities.saveObject(employeeDuty);

            EmployeeDegree employeeDegree = new EmployeeDegree();
            employeeDegree.setCompany(company);
            employeeDegree.setEmpDegree("工程师");
            this.utilities.saveObject(employeeDegree);

            employeeDegree = new EmployeeDegree();
            employeeDegree.setCompany(company);
            employeeDegree.setEmpDegree("助理工程师");
            this.utilities.saveObject(employeeDegree);

            employeeDegree = new EmployeeDegree();
            employeeDegree.setCompany(company);
            employeeDegree.setEmpDegree("高级工程师");
            this.utilities.saveObject(employeeDegree);

            EmployeeLevel employeeLevel = new EmployeeLevel();
            employeeLevel.setCompany(company);
            employeeLevel.setEmpLevel("高中");
            this.utilities.saveObject(employeeLevel);

            employeeLevel = new EmployeeLevel();
            employeeLevel.setCompany(company);
            employeeLevel.setEmpLevel("大专");
            this.utilities.saveObject(employeeLevel);

            employeeLevel = new EmployeeLevel();
            employeeLevel.setCompany(company);
            employeeLevel.setEmpLevel("本科");
            this.utilities.saveObject(employeeLevel);

            employeeLevel = new EmployeeLevel();
            employeeLevel.setCompany(company);
            employeeLevel.setEmpLevel("本科以上");
            this.utilities.saveObject(employeeLevel);

            EmployeeHealth employeeHealth = new EmployeeHealth();
            employeeHealth.setCompany(company);
            employeeHealth.setEmpHealth("健康");
            this.utilities.saveObject(employeeHealth);

            employeeHealth = new EmployeeHealth();
            employeeHealth.setCompany(company);
            employeeHealth.setEmpHealth("残疾");
            this.utilities.saveObject(employeeHealth);

            StorageType storageType = new StorageType();
            storageType.setCompany(company);
            storageType.setStoType("成品库");
            this.utilities.saveObject(storageType);

            storageType = new StorageType();
            storageType.setCompany(company);
            storageType.setStoType("未成品库");
            this.utilities.saveObject(storageType);

            AffordType affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("支票");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("现金");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("信汇");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("电汇");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("转帐");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("邮托");
            this.utilities.saveObject(affordType);

            affordType = new AffordType();
            affordType.setCompany(company);
            affordType.setName("其它");
            this.utilities.saveObject(affordType);
            
            Subject subject = new Subject();
            subject.setCatalog(this.baseDao.findSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_BANK_CASH));
            subject.setMainSubjectCatalog(this.baseDao.findSubjectCatalog(ServiceConstants.SUBJECT_CATALOG_BANK_CASH).getMainCatalog());
            subject.setNumber("111");
            subject.setName("现金");
            subject.setShortName("现金");
            subject.setCode("xj");
            subject.setDebitCredit("借");
            subject.setCompany(company);
            this.utilities.saveObject(subject);
            
            Permission permission = new Permission();
            permission.setUser(user);
//            permission.setBaseCustomer(65535);
//            permission.setBaseSupplier(65535);
//            permission.setBaseEmployee(65535);
//            permission.setBaseProduct(65535);
//            permission.setBaseStorage(65535);
//            permission.setCgd(65535);
//            permission.setCgdd(65535);
//            permission.setCgth(65535);
//            permission.setCwcgyf(65535);
//            permission.setCwfkjs(65535);
//            permission.setCwjyfy(65535);
//            permission.setCwqtsr(65535);
//            permission.setCwskjs(65535);
//            permission.setCwxjyh(65535);
//            permission.setCwxsyf(65535);
//            permission.setCwybfy(65535);
//            permission.setInitKcqc(65535);
//            permission.setReportCdcx(65535);
//            permission.setReportCdcx(65535);
//            permission.setReportCdkstj(65535);
//            permission.setReportCdspcx(65535);
//            permission.setReportCdsptj(65535);
//            permission.setReportCdspyecx(65535);
//            permission.setReportCdspyetj(65535);
//            permission.setReportCdyecx(65535);
//            permission.setReportCdywytj(65535);
//            permission.setReportCgjgbd(65535);
//            permission.setReportCgksmx(65535);
//            permission.setReportCgkstj(65535);
//            permission.setReportCgmx(65535);
//            permission.setReportCgsphz(65535);
//            permission.setReportCgtj(65535);
//            permission.setReportCgywymx(65535);
//            permission.setReportCgywytj(65535);
//            permission.setReportKcbjmx(65535);
//            permission.setReportKcbs(65535);
//            permission.setReportKcbsmx(65535);
//            permission.setReportKcby(65535);
//            permission.setReportKcbymx(65535);
//            permission.setReportKcckmx(65535);
//            permission.setReportKccpck(65535);
//            permission.setReportKccprk(65535);
//            permission.setReportKccpsxx(65535);
//            permission.setReportKcpd(65535);
//            permission.setReportKcpdmx(65535);
//            permission.setReportKcrkmx(65535);
//            permission.setReportKcspye(65535);
//            permission.setReportKcyk(65535);
//            permission.setReportKcykmx(65535);
//            permission.setReportXdcx(65535);
//            permission.setReportXdkstj(65535);
//            permission.setReportXdspcx(65535);
//            permission.setReportXdsptj(65535);
//            permission.setReportXdspyemx(65535);
//            permission.setReportXdyecx(65535);
//            permission.setReportXdywytj(65535);
//            permission.setReportXsksmx(65535);
//            permission.setReportXskstj(65535);
//            permission.setReportXsmx(65535);
//            permission.setReportXspyetj(65535);
//            permission.setReportXstj(65535);
//            permission.setReportXsywytj(65535);
//            permission.setReportXywymx(65535);
//            permission.setStorageCpck(65535);
//            permission.setStorageKcbj(65535);
//            permission.setStorageKcbs(65535);
//            permission.setStorageKcby(65535);
//            permission.setStoragePdgl(65535);
//            permission.setStorageSprk(65535);
//            permission.setStorageSxxz(65535);
//            permission.setStorageYkgl(65535);
//            permission.setSystemGsxx(65535);
//            permission.setSystemJsxx(65535);
//            permission.setSystemXgmm(65535);
//            permission.setSystemXtrz(65535);
//            permission.setSystemYhxx(65535);
//            permission.setSystemxlkgl(65535);
//            permission.setXsbj(65535);
//            permission.setXsd(65535);
//            permission.setXsdd(65535);
//            permission.setXssptj(65535);
//            permission.setXsth(65535);
//            permission.setPrice(8384512);
//            this.utilities.saveObject(permission);
            transaction.commit();
        }
        catch(Exception ex)
        {
            transaction.rollback();
            ex.printStackTrace();
        }
        
    }
    public boolean checkRegInfo(Company company)
    {
        return false;
    }

    public void savePriceName(PriceName priceName)
    {
        this.utilities.saveObject(priceName);
    }

    public PriceName getPriceName(Company company)
    {
        PriceName priceName = this.dao.getPriceName(company);
        if(priceName != null)
        {
            return priceName;
        }
        priceName = new PriceName();
        priceName.setCompany(company);
        priceName.setPriceName0("价格1");
        priceName.setPriceName1("价格2");
        priceName.setPriceName2("价格3");
        priceName.setPriceName3("价格4");
        priceName.setPriceName4("价格5");
        priceName.setPriceName5("价格6");
        return priceName;
    }

    public List<PriceName> getPriceNames(Company company)
    {
        return this.dao.getPriceNames(company);
    }

    public List<NumberPrefix> getNumberPrefixs(Company company)
    {
        return this.dao.getNumberPrefixs(company);
    }

    public List<CreateNumber> getAutoNumbers(Company company)
    {
        return this.dao.getAutoNumbers(company);
    }
}
