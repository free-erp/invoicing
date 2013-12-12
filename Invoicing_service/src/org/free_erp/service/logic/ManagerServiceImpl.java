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
import org.free_erp.service.dao.base.DaoUtilities;
import org.free_erp.service.dao.system.IManagerDao;
import org.free_erp.service.entity.accounting.DailyReportPo;
import org.free_erp.service.logic.sale.SaleQueryVo;

/**
 *
 * @author TengJianfa 13003311398
 */
public class ManagerServiceImpl implements IManagerService
{
  private DaoUtilities utilities;
private IManagerDao managerDao;

    public IManagerDao getManagerDao() {
        return managerDao;
    }
    public void setManagerDao(IManagerDao managerDao) {
        this.managerDao = managerDao;
    }
    public DaoUtilities getUtilities() {
        return utilities;
    }

    public void setUtilities(DaoUtilities utilities) {
        this.utilities = utilities;
    }

    public List<DailyReportPo> getManagerForm(SaleQueryVo vo) {
     return this.managerDao.getManagerForm(vo);
    }

    public List<DailyReportPo> getCountManagerForm(SaleQueryVo vo) {
     List<DailyReportPo> daily = new ArrayList();
     String field =null;
     List<DailyReportPo>    dr = this.managerDao.getManagerForm(vo);
     for(DailyReportPo d:dr){
            for(DailyReportPo s:daily){
               if(s.getFormDate()==d.getFormDate()){
                   field="formDate";
                List<DailyReportPo> ds = this.managerDao.getManagerForm(vo);
                 s.setCashMoney(ds.get(0).getCashMoney());
                 s.setCost(s.getCost()+d.getCost());
                 s.setGrossProfitMoney(s.getGrossProfitMoney()+d.getGrossProfitMoney());
                 s.setPayableMoney(s.getPayableMoney()+d.getPayableMoney());
                 s.setPaymentMoney(s.getPaymentMoney()+d.getPaymentMoney());
                 s.setPrePayableMoney(s.getPrePayableMoney()+d.getPrePayableMoney());
                 s.setPreReceivableMoney(s.getPreReceivableMoney()+d.getPreReceivableMoney());
                 s.setPurchaseMoney(s.getPurchaseMoney()+d.getPurchaseMoney());
                 s.setReceivableMoney(s.getReceivableMoney()+d.getReceivableMoney());
                 s.setReceiveMoney(s.getReceiveMoney()+d.getReceiveMoney());
                 s.setSaleMoney(s.getSaleMoney()+d.getSaleMoney());
                 s.setStockMoney(ds.get(0).getStockMoney());
                 daily.remove(s);
                 daily.add(s);
               }else{
                   daily.add(d);
               }
            }
     }

    return daily;
    }

    public List<DailyReportPo> getManagerDailyForm(SaleQueryVo vo)
    {
        return this.managerDao.getManagerDailyForm(vo.getCompany(), vo.getStartDatePo());
    }

    public List<DailyReportPo> getManagerMonthForm(SaleQueryVo vo)
    {
        List<DailyReportPo> dailyReportPos = this.managerDao.getManagerDailyForm(vo.getCompany(), vo.getStartDatePo(), vo.getEndDatePo());
        List<DailyReportPo> monthReportPos = new ArrayList<DailyReportPo>();
        for(DailyReportPo po : dailyReportPos)
        {
            DailyReportPo dailyPo = this.getPoInSameDay(monthReportPos, po.getFormDate());
            if(dailyPo != null)
            {
                po.setSaleMoney(dailyPo.getSaleMoney() + po.getSaleMoney());
                po.setReceiveMoney(dailyPo.getReceiveMoney() + po.getReceiveMoney());
                po.setGrossProfitMoney(dailyPo.getGrossProfitMoney() + po.getGrossProfitMoney());
                po.setCost(dailyPo.getCost() + po.getCost());
                po.setPurchaseMoney(dailyPo.getPurchaseMoney() + po.getPurchaseMoney());
                po.setPaymentMoney(dailyPo.getPaymentMoney() + po.getPaymentMoney());
                monthReportPos.remove(dailyPo);
                monthReportPos.add(po);
            }
            else
            {
                monthReportPos.add(po);
            }
        }
        return monthReportPos;
    }

    public List<DailyReportPo> getManagerYearForm(SaleQueryVo vo)
    {
        List<DailyReportPo> dailyReportPos = this.managerDao.getManagerDailyForm(vo.getCompany(), vo.getStartDatePo(), vo.getEndDatePo());
        List<DailyReportPo> yearReportPos = new ArrayList();
        for(DailyReportPo po : dailyReportPos)
        {
            DailyReportPo dailyPo = this.getPoInSameMonth(yearReportPos, po.getFormDate());
            if(dailyPo != null)
            {
                yearReportPos.remove(dailyPo);
                po.setSaleMoney(dailyPo.getSaleMoney() + po.getSaleMoney());
                po.setReceiveMoney(dailyPo.getReceiveMoney() + po.getReceiveMoney());
                po.setGrossProfitMoney(dailyPo.getGrossProfitMoney() + po.getGrossProfitMoney());
                po.setCost(dailyPo.getCost() + po.getCost());
                po.setPurchaseMoney(dailyPo.getPurchaseMoney() + po.getPurchaseMoney());
                po.setPaymentMoney(dailyPo.getPaymentMoney() + po.getPaymentMoney());
                yearReportPos.add(po);
            }
            else
            {
                yearReportPos.add(po);
            }
        }
        return yearReportPos;
    }

    private boolean isInSameDay(Date oneDate, Date otherDate)
    {
        if((oneDate.getYear() == otherDate.getYear()) && (oneDate.getMonth() == otherDate.getMonth()) && (oneDate.getDate() == otherDate.getDate()))
        {
            return true;
        }
        return false;
    }

    private boolean isInSameMonth(Date oneDate, Date otherDate)
    {
        if((oneDate.getYear() == otherDate.getYear()) && (oneDate.getMonth() == otherDate.getMonth()))
        {
            return true;
        }
        return false;
    }

    private DailyReportPo getPoInSameDay(List<DailyReportPo> pos ,Date date)
    {
        if(pos.size() > 0)
        {
            for(DailyReportPo po : pos)
            {
                if(this.isInSameDay(po.getFormDate(), date))
                {
                    return po;
                }
            }
        }
        return null;
    }
    private DailyReportPo getPoInSameMonth(List<DailyReportPo> pos ,Date date)
    {
        if(pos.size() > 0)
        {
            for(DailyReportPo po : pos)
            {
                if(this.isInSameMonth(po.getFormDate(), date))
                {
                    return po;
                }
            }
        }
        return null;
    }
}

