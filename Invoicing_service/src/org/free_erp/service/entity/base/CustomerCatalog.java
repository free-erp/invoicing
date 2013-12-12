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
package org.free_erp.service.entity.base;

import java.util.Date;
import org.free_erp.service.entity.ErpObject;

/**
 * CustomerCatalog entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class CustomerCatalog extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
	// Fields
	private int id;
	private Company company;
	private String number;
	private Integer parentId;
	private String name;
	private String shortName;
	private String code;
	private String comments;
    private String customerType;
	// Constructors

	/** default constructor */
	public CustomerCatalog()
	{
        this.createDate = new Date();
	}

	public Integer getParentId()
	{
		return this.parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public String getComments()
	{
		return this.comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		if (obj instanceof CustomerCatalog)
		{
			return ((CustomerCatalog)obj).getId() == this.id;
		}
		return false;
	}
	@Override
	public String toString()
	{
		String nameString = "δ����";
        if (this.name != null)
        {
            nameString = this.name;
        }
        String numberString = "δ���";
        if (this.number != null)
        {
            numberString = this.number;
        }
		return nameString + "[" + numberString + "]";
	}



	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return Integer.valueOf(id).hashCode();
	}


}