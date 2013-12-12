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

package org.free_erp.client.ui.forms.sale;


import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.jdatabeans.beans.data.IDbSupport;
import org.free_erp.client.ui.forms.CBaseListWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.sale.SaleChangeMoneyPo;
import org.free_erp.service.entity.sale.SaleOrderBackPo;
import org.free_erp.service.entity.sale.SaleOrderPo;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.entity.sale.SaleQuotePo;
import org.free_erp.service.entity.sale.SalechaseOrderPo;
import org.free_erp.service.logic.ISaleService;
import org.free_erp.service.logic.ISystemManageService;
import java.util.Collection;
/*
 * Changer: liufei
 */
public abstract class CBaseSaleListWindow extends CBaseListWindow  implements ActionListener
{
	public CBaseSaleListWindow(String title)
	{
		super(title);
    }

    @Override
	public void createDbSupport()
	{
		dbSupport = new IDbSupport()
		{
			private ISaleService saleService = Main.getServiceManager().getSaleService();
            ISystemManageService systemManageService = Main.getServiceManager().getSystemManageService();
			public void delete(Object obj)
			{
				if (obj instanceof SaleOrderPo)
				{
					SaleOrderPo p = (SaleOrderPo) obj;
					this.saleService.deleteOrderForm(p);
				}
               else if (obj instanceof SalePo)
				{
					SalePo p = (SalePo) obj;
					this.saleService.deleteSaleDetailForm(p);
				}
               else if (obj instanceof SaleOrderBackPo)
				{
					SaleOrderBackPo p = (SaleOrderBackPo) obj;
					this.saleService.deleteSaleOrderBackForm(p);
				}
            else  if (obj instanceof SaleQuotePo)
				{
					SaleQuotePo p = (SaleQuotePo) obj;
					this.saleService.deleteQuoteForm(p);
				}
             else  if (obj instanceof SalechaseOrderPo)
				{
					SalechaseOrderPo p = (SalechaseOrderPo) obj;
					this.saleService.deleteChangeOrderForm(p);
				}
             else  if (obj instanceof SaleChangeMoneyPo)
				{
					SaleChangeMoneyPo p = (SaleChangeMoneyPo) obj;
					this.saleService.deleteSaleChangeMoneyForm(p);
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
				if (obj instanceof SaleOrderPo)
				{
					SaleOrderPo p = (SaleOrderPo) obj;
					this.saleService.saveSaleOrderForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if (obj instanceof SalePo)
				{
					SalePo p = (SalePo) obj;
					this.saleService.saveSaleDetailForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if (obj instanceof SaleOrderBackPo)
				{
					SaleOrderBackPo p = (SaleOrderBackPo) obj;
					this.saleService.saveSaleOrderBackForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if (obj instanceof SaleQuotePo)
				{
					SaleQuotePo p = (SaleQuotePo) obj;
					this.saleService.saveSaleQuoteForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if (obj instanceof SalechaseOrderPo)
				{
					SalechaseOrderPo p = (SalechaseOrderPo) obj;
					this.saleService.saveSaleChangeOrderForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
                else if (obj instanceof SaleChangeMoneyPo)
				{
					SaleChangeMoneyPo p = (SaleChangeMoneyPo) obj;
					this.saleService.saveSaleChangeMoneyForm(p);
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

