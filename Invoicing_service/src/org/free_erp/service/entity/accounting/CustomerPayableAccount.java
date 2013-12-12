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
package org.free_erp.service.entity.accounting;

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;

/**
 * CustomerPayableAccount entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class CustomerPayableAccount implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
    private Customer customer;
	private Company company;
	private Double accountMoney = 0d;
	private Double remainMoney = 0d;
	private Double clearingMoney = 0d;
    private Double payableMoney = 0d;
	private Integer status = 0;//暂时用不着
    private int id;

	// Constructors

	/** default constructor */
	public CustomerPayableAccount() {
	}

    public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Double getAccountMoney() {
		return this.accountMoney;
	}

	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public Double getRemainMoney() {
		return this.remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Double getClearingMoney() {
		return this.clearingMoney;
	}

	public void setClearingMoney(Double clearingMoney) {
		this.clearingMoney = clearingMoney;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPayableMoney() {
        return payableMoney;
    }

    public void setPayableMoney(Double payableMoney) {
        this.payableMoney = payableMoney;
    }

     @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        CustomerPayableAccount detail = (CustomerPayableAccount)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}