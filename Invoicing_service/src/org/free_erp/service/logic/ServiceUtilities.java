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
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.FormPo;
/**
 *
 * @author afa
 */
public class ServiceUtilities
{
//	public static void addDateInfo(Object po)
//	{
//		try
//		{
//			Method setMethod = po.getClass().getMethod("setCreateDate", new Class[]{Date.class});
//			if (setMethod != null)
//			{
//				Method getMethod = po.getClass().getMethod("getCreateDate", new Class[]{Date.class});
//				Date oldDate = (Date)getMethod.invoke(po, new Object[]{});
//				if (oldDate == null)
//				{
//					setMethod.invoke(po, new Object[]{new Date()});
//				}
//			}
//			Method modifyMethod=  po.getClass().getMethod("setModifyDate", new Class[]{Date.class});
//			if (modifyMethod != null)
//			{
//				modifyMethod.invoke(po, new Object[]{new Date()});
//			}
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
	
	public static void addDateInfo(ErpObject obj)
	{
		if (obj.getCreateDate() == null)
		{
			obj.setCreateDate(new Date());
		}
		obj.setModifyDate(new Date());
		if (obj instanceof FormPo)
		{
			FormPo p = (FormPo)obj;
			if(p.getFormDate() == null)
			{
				p.setFormDate(new Date());
			}
		}
	}

}
