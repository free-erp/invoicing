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

import java.util.Date;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class DateUtilities
{
    public String doDate(Object obj)
    {
        String date = "";
        int year = ((Date)obj).getYear() + 1900;
        int month = ((Date)obj).getMonth() + 1;
        int day = ((Date)obj).getDate();
        date = String.valueOf(year) + "-" + (month > 9 ? String.valueOf(month) :"0"+String.valueOf(month)) + "-" + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day));
        return date;
    }

    public static String numberDate()
    {
        String strDate = "";
        Date date = new Date();
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        strDate = String.valueOf(year) + (month > 9 ? String.valueOf(month) :"0"+String.valueOf(month)) + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day));
        return strDate;
    }

    public static Date caculateDate(Date date, int nextDays)
    {
        Double time = 1000d * 60 * nextDays * 24 * 60 + date.getTime();
        return new Date(time.longValue());
    }

    public static String getYearMonthDate(Date date)
    {
        return String.valueOf(date.getYear() + 1900) + "-" + String.valueOf(date.getMonth() + 1);
    }

    public static int getMonthLastDate(Date date)
    {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        if(year%4 == 0 && year%100!=0)
        {
            if(month == 2)
            {
                return 29;
            }
            if(month == 4 || month == 6 || month == 9 || month == 11)
            {
                return 30;
            }
            else
            {
                return 31;
            }
        }
        else
        {
            if(month == 2)
            {
                return 28;
            }
            if(month == 4 || month == 6 || month == 9 || month == 11)
            {
                return 30;
            }
            else
            {
                return 31;
            }
        }
    }
}
