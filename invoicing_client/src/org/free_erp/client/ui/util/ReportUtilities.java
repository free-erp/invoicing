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
package org.free_erp.client.ui.util;

import org.free_erp.client.ui.main.Main;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class ReportUtilities
{
    public static Map creatParameterMap(ReportVO vo)
    {
        Map map = new HashMap();
        map.put("JCOMPANY", Main.getMainFrame().getCompany().getName());
        map.put("JTITLE", vo.getTitle());
        map.put("JNUMBER",vo.getNumber());
        map.put("JINSTORAGE", vo.getInStorage());
        map.put("JFROMDATE", vo.getFromDate());
        map.put("JCHARGEPERSON",vo.getChargePerson());
        map.put("JDEPARTMENT",vo.getDepartment());
        map.put("JCUSTOMER", vo.getSupplier());
        map.put("JCOMMENTS", vo.getComments());
        map.put("JSTORAGE", vo.getStorageName());
        map.put("JPAYTERM", vo.getPayTerm());
        map.put("JTOTALMONEY", vo.getTotalMoney());
        map.put("JSTOTALMONEY", vo.getStrTotalMoney());
        map.put("JAFFORDTYPE", vo.getAffordType());
        map.put("JCLEARINGTYPE", vo.getClearingType());
        map.put("JACCOUNTINGMONEY", vo.getAccountingMoney());
        map.put("JPAYACCOUNT", vo.getPayAccount());
        map.put("JPAYMONEY", vo.getPayMoney());
        map.put("JIMAGE", vo.getImage());
         map.put("JOFFERSMONEY", vo.getOffersMoney());
        return map;
    }
    public static Map creatParameterMap(SaleReportVo vo)
    {
        Map map = new HashMap();
        map.put("JCOMPANY", Main.getMainFrame().getCompany().getName());
        map.put("JTITLE", vo.getTitle());
        map.put("JNUMBER",vo.getNumber());
        map.put("JOUTSTORAGE",vo.getOutStorage());
        map.put("JINSTORAGE", vo.getInStorage());
        map.put("JFROMDATE", vo.getFromDate());
        map.put("JCHARGEPERSON",vo.getEmployee());
        map.put("JDEPARTMENT",vo.getDepartment());
        map.put("JCUSTOMER", vo.getCustomer());
        map.put("JCOMMENTS", vo.getComments());
        map.put("JSTORAGE", vo.getStorageName());
        map.put("JPAYTERM", vo.getPayTerm());
        map.put("JTOTALMONEY", vo.getTotalMoney());
        map.put("JSTOTALMONEY", vo.getStrTotalMoney());
        map.put("JAFFORDTYPE", vo.getAffordType());
        map.put("JCLEARINGTYPE", vo.getClearingType());
        map.put("JACCOUNTINGMONEY", vo.getAccountingMoney());
        map.put("JPAYACCOUNT", vo.getPayAccount());
        map.put("JPAYMONEY", vo.getPayMoney());
        map.put("JIMAGE", vo.getImage());
        map.put("JRECEIVINGCODE", vo.getReceivingCode());
        map.put("JRECEIVING", vo.getReceiving());
        map.put("JDELIVERY", vo.getDelivery());
        map.put("JRECEIVINGPHONE", vo.getReceivingPhone());
      map.put("JRECEIVINGADDRESS",vo.getReceivingAddress());
      map.put("JRECEIVINGADDRESS",vo.getReceivingAddress());
      map.put("JOFFERSMONEY", vo.getOffersMoney());
        return map;
    }

}
