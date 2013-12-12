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

import java.util.Date;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;

/**
 * Payable entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class Payable implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private int id;
	private Employee employee;
	private Customer customer;
	private Company company;
	private String number;
	private String formNo;
	private Date formDate;
	private Date payableDate;
	private Double payableMoney = 0d;
	private Double clearingMoney = 0d;
	//private Double remainMoney = 0d;
    private Date createDate;
	private String createdBy;
	private Date modifyDate;
	private String comments;
	//private Integer status = ServiceConstants.UNFINISHED;
	

	// Constructors

	/** default constructor */
	public Payable() {
        this.createDate = new Date();
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getFormNo() {
		return this.formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public Date getFormDate() {
		return this.formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public Date getPayableDate() {
		return this.payableDate;
	}

	public void setPayableDate(Date payableDate) {
		this.payableDate = payableDate;
	}

	public Double getPayableMoney() {
		return this.payableMoney;
	}

	public void setPayableMoney(Double payableMoney) {
		this.payableMoney = payableMoney;
	}

	public Double getClearingMoney() {
		return this.clearingMoney;
	}

	public void setClearingMoney(Double clearingMoney) {
		this.clearingMoney = clearingMoney;
	}

	public Double getRemainMoney() {
		return this.payableMoney - this.clearingMoney;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getStatus() {
        if (this.payableMoney - this.clearingMoney< 0.00000001d)
        {
            return ServiceConstants.FINISHED;
        }
		return ServiceConstants.UNFINISHED;
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        Payable detail = (Payable)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

   

    //不做持久化处理
    private Boolean checked = true;

}