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

package org.free_erp.client.ui.forms.purchase;


import org.free_erp.client.ui.forms.CBaseListWindow;
import javax.swing.JOptionPane;

import com.jdatabeans.beans.data.IDbSupport;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.purchase.PurchaseBackPo;
import org.free_erp.service.entity.purchase.PurchaseChangePo;
import org.free_erp.service.entity.purchase.PurchaseIndentPo;
import org.free_erp.service.entity.purchase.PurchaseOrderPo;
import org.free_erp.service.logic.ISystemManageService;
import org.free_erp.service.logic.purchase.IPurchaseService;

import java.util.Collection;
/*
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public abstract class CBasePurchaseListWindow extends CBaseListWindow
{
	public CBasePurchaseListWindow(String title)
	{
		super(title);
    }

    @Override
	protected void createDbSupport()
	{
		dbSupport = new IDbSupport()
		{
            IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
            ISystemManageService systemManageService = Main.getServiceManager().getSystemManageService();
			public void delete(Object obj)
			{
                if(obj instanceof PurchaseIndentPo)
				{
				    PurchaseIndentPo p = (PurchaseIndentPo) obj;
                    this.purchaseService.deletePurchaseIndentPoForm(p);
				}
				else if(obj instanceof PurchaseOrderPo)
				{
				    PurchaseOrderPo p = (PurchaseOrderPo) obj;
                    this.purchaseService.deletePurchaseOrderPoForm(p);
				}
				else if(obj instanceof PurchaseBackPo)
				{
				    PurchaseBackPo p = (PurchaseBackPo) obj;
                    this.purchaseService.deletePurchaseBackPoForm(p);
				}
                else if(obj instanceof PurchaseChangePo)
				{
				    PurchaseChangePo p = (PurchaseChangePo) obj;
                    this.purchaseService.deletePurchaseChangePoForm(p);
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
                if(obj instanceof PurchaseIndentPo)
				{
				    PurchaseIndentPo p = (PurchaseIndentPo) obj;
                    this.purchaseService.savePurchaseIndentPoForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof PurchaseOrderPo)
				{
				    PurchaseOrderPo p = (PurchaseOrderPo) obj;
                    this.purchaseService.savePurchaseOrderPoForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof PurchaseBackPo)
				{
				    PurchaseBackPo p = (PurchaseBackPo) obj;
                    this.purchaseService.savePurchaseBackPoForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if(obj instanceof PurchaseChangePo)
				{
				    PurchaseChangePo p = (PurchaseChangePo) obj;
                    this.purchaseService.savePurchaseChangePoForm(p);
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
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}

			public void saveAll(Collection<Object> objs)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
	
}

