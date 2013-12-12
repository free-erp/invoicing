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
import java.util.HashSet;
import java.util.Set;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;

/**
 * BussinessExpense entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class BussinessExpense implements FormPo, java.io.Serializable, IAffordForm {

	// Fields
    private static final long serialVersionUID = 1l;
	private Employee employee;
	private Subject subject;
	private AffordType affordType;
	private ClearingType clearingType;
	private Customer customer;
	private Date formDate;
	private String department;
	private Double accountMoney = 0d;
	private Double affordMoney = 0d;
	private Double totalMoney = 0d;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
    private String comments;
    /**
     * 在审核时才能确定
     */
    private Double clearingMoney = 0d;

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

    private Integer status = ServiceConstants.DRAFT_STATUS;
    private String statusString = ServiceConstants.DRAFT_STRING;

	// Constructors

	/** default constructor */
	public BussinessExpense() {
        this.createDate = new Date();
	}
    
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public AffordType getAffordType() {
		return this.affordType;
	}

	public void setAffordType(AffordType affordType) {
		this.affordType = affordType;
	}

	public ClearingType getClearingType() {
		return this.clearingType;
	}

	public void setClearingType(ClearingType clearingType) {
		this.clearingType = clearingType;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getAffordMoney() {
        return affordMoney;
    }

    public void setAffordMoney(Double affordMoney) {
        this.affordMoney = affordMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
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

	public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public Set<BussinessExpenseDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<BussinessExpenseDetail> details) {
        this.details = details;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

     @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        BussinessExpense detail = (BussinessExpense)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Double getClearingMoney() {
        return clearingMoney;
    }

    public void setClearingMoney(Double clearingMoney) {
        this.clearingMoney = clearingMoney;
    }
    public void setAffordTerm(Integer affordTerm) {
        this.affordTerm = affordTerm;
    }

    public Integer getAffordTerm() {
        return this.affordTerm;
    }
    public String getName() {
        return "经营费用单";
    }
    private Integer affordTerm = 30;

    private Set<BussinessExpenseDetail> details = new HashSet<BussinessExpenseDetail>();

    private String number;
	private Integer id;
    private Company company;

    



}