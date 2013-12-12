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
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;

/**
 * InitialPayable entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class InitialPayable implements FormPo, java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private Integer id;
	private Employee employee;
	private Customer customer;
	private Company company;
	private String number;
	private Date formDate;
	private String department;
	private Integer affordTerm = 30;
	private Double accountMoney = 0d;;
	private Double payableMoney = 0d;;
	private String comments;
	
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
    private ClearingType clearingType;
    private AffordType affordType;

	// Constructors

	/** default constructor */
	public InitialPayable() {
        this.createDate = new Date();
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

	

	public Date getFormDate() {
		return this.formDate;
	}

	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

    public Integer getAffordTerm() {
        return affordTerm;
    }

    public void setAffordTerm(Integer affordTerm) {
        this.affordTerm = affordTerm;
    }

	public Double getAccountMoney() {
		return this.accountMoney;
	}

	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public Double getPayableMoney() {
		return this.payableMoney;
	}

	public void setPayableMoney(Double payableMoney) {
		this.payableMoney = payableMoney;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
    public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer status) {
        this.setStatusString(status);
        this.status = status;
    }

    public String getStatusString()
    {
        return this.statusString;
    }

    public void setStatusString(Integer status) {
        switch(status)
        {
            case ServiceConstants.DRAFT_STATUS:
                this.statusString = ServiceConstants.DRAFT_STRING;
                break;
            case ServiceConstants.FORMAL_STATUS:
                this.statusString = ServiceConstants.FORMAL_STRING;
                break;
            case ServiceConstants.DISCARD_STATUS:
                this.statusString = ServiceConstants.DISCARD_STRING;
                break;
            default:
                this.statusString = ServiceConstants.UNKNOWN;
        }
    }

    public ClearingType getClearingType() {
        return clearingType;
    }

    public void setClearingType(ClearingType clearingType) {
        this.clearingType = clearingType;
    }

    public AffordType getAffordType() {
        return affordType;
    }

    public void setAffordType(AffordType affordType) {
        this.affordType = affordType;
    }
     @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        InitialPayable detail = (InitialPayable)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private String statusString = ServiceConstants.DRAFT_STRING;
    private Integer status = ServiceConstants.DRAFT_STATUS;

    public String getName() {
        return "Ó¦¸¶ÆÚ³õµ¥";
    }

}