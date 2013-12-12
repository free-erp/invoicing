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
package org.free_erp.service.entity.system;

import java.util.Date;
import org.free_erp.service.entity.base.Company;

/**
 *
 * @author TengJianfa 13003311398
 */
public class CustomerInvoice implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private int id;
    private Company company;
    private String createdBy;
	private Date createDate;
	private Date modifyDate;
    private String cusInvoice;

    public CustomerInvoice()
    {
        this.createDate = new Date();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCusInvoice() {
        return cusInvoice;
    }

    public void setCusInvoice(String cusInvoice) {
        this.cusInvoice = cusInvoice;
    }

    @Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		if (obj instanceof CustomerInvoice)
		{
			return ((CustomerInvoice)obj).getId() == this.id;
		}
		return false;
	}

    @Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return Integer.valueOf(id).hashCode();
	}

	@Override
	public String toString()
	{
		return this.cusInvoice;
	}

}
