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

package org.free_erp.client.ui.forms.accounting;

import com.jdatabeans.beans.data.IDbSupport;
import org.free_erp.client.ui.forms.CBaseListWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.accounting.BankCash;
import org.free_erp.service.entity.accounting.BussinessExpense;
import org.free_erp.service.entity.accounting.CommonExpense;
import org.free_erp.service.entity.accounting.InitialPayable;
import org.free_erp.service.entity.accounting.InitialReceivable;
import org.free_erp.service.entity.accounting.InitialSubject;
import org.free_erp.service.entity.accounting.OtherIncome;
import org.free_erp.service.entity.accounting.PayableClearing;
import org.free_erp.service.entity.accounting.Prepayable;
import org.free_erp.service.entity.accounting.Prereceivable;
import org.free_erp.service.entity.accounting.ReceivableClearing;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.ISystemManageService;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public abstract class CBaseAccountListWindow extends CBaseListWindow
{
    public CBaseAccountListWindow(String title)
	{
		super(title);
    }
    
    @Override
    protected void createDbSupport()
	{
		dbSupport = new IDbSupport()
		{
			private IAccountingService accountingService = Main.getServiceManager().getAccountingService();
            ISystemManageService systemManageService = Main.getServiceManager().getSystemManageService();
            public void delete(Object obj)
			{
				if(obj instanceof BankCash)
				{
				   accountingService.deleteBankCashForm((BankCash)obj);
				}
                else if(obj instanceof BussinessExpense)
				{
				   accountingService.deleteBussinessExpenseForm((BussinessExpense)obj);
				}
                else if(obj instanceof CommonExpense)
				{
				   accountingService.deleteCommonExpenseForm((CommonExpense)obj);
				}
                else if(obj instanceof OtherIncome)
				{
				   accountingService.deleteOtherIncomeForm((OtherIncome)obj);
				}
                else if(obj instanceof PayableClearing)
				{
				   accountingService.deletePayableClearingForm((PayableClearing)obj);
				}
                else if(obj instanceof Prepayable)
				{
				   accountingService.deletePrepayableForm((Prepayable)obj);
				}
                else if(obj instanceof Prereceivable)
				{
				   accountingService.deletePrereceivableForm((Prereceivable)obj);
				}
                else if(obj instanceof ReceivableClearing)
				{
				   accountingService.deleteReceivableClearingForm((ReceivableClearing)obj);
				}
                 else if(obj instanceof InitialPayable)
				{
				   accountingService.deleteInitialPayableForm((InitialPayable)obj);
				}
                else if(obj instanceof InitialReceivable)
				{
				   accountingService.deleteInitialReceivableForm((InitialReceivable)obj);
				}
                else if(obj instanceof InitialSubject)
				{
				   accountingService.deleteInitialSubjectForm((InitialSubject)obj);
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void save(Object obj)
			{
                String logContent = "";
                if(obj instanceof FormPo)
                {
                    FormPo p = (FormPo)obj;
                    if(p.getId() == 0)
                    {
                        logContent = "新增" + p.getName() + ":";
                    }
                    else
                    {
                        logContent = "修改" + p.getName() + ":";
                    }
                }
                if(obj instanceof BankCash)
				{
                    BankCash p = (BankCash) obj;
				    accountingService.saveBankCashForm((BankCash)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof BussinessExpense)
				{
                    BussinessExpense p = (BussinessExpense) obj;
				    accountingService.saveBussinessExpenseForm((BussinessExpense)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof CommonExpense)
				{
                    CommonExpense p = (CommonExpense) obj;
				    accountingService.saveCommonExpenseForm((CommonExpense)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof OtherIncome)
				{
                    OtherIncome p = (OtherIncome) obj;
				    accountingService.saveOtherIncomeForm((OtherIncome)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof PayableClearing)
				{
                    PayableClearing p = (PayableClearing) obj;
				    accountingService.savePayableClearingForm((PayableClearing)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof Prepayable)
				{
                    Prepayable p = (Prepayable) obj;
				    accountingService.savePrepayableForm((Prepayable)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof Prereceivable)
				{
                    Prereceivable p = (Prereceivable) obj;
				    accountingService.savePrereceivableForm((Prereceivable)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof ReceivableClearing)
				{
                    ReceivableClearing p = (ReceivableClearing) obj;
				    accountingService.saveReceivableClearingForm((ReceivableClearing)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof InitialPayable)
				{
                    InitialPayable p = (InitialPayable) obj;
				    accountingService.saveInitialPayableForm((InitialPayable)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof InitialReceivable)
				{
                    InitialReceivable p = (InitialReceivable) obj;
				    accountingService.saveInitialReceivableForm((InitialReceivable)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof InitialSubject)
				{
                    InitialSubject p = (InitialSubject) obj;
				    accountingService.saveInitialSubjectForm((InitialSubject)obj);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
                if(obj instanceof FormPo)
                {
                    systemManageService.saveSystemLog(systemLog);
                }
			}

			public void deleteAll(Collection<Object> objs)
			{
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}

			public void saveAll(Collection<Object> objs)
			{
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
}
