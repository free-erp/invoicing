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
 * ReceivableClearing entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class ReceivableClearing implements FormPo, java.io.Serializable, IReceiveForm {

	// Fields
    private static final long serialVersionUID = 1l;
	private Employee employee;
	private Subject subject;
	private AffordType affordType;
	private Customer customer;
	private Date formDate;
	private String department;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
	private Double accountMoney = 0d;
	private Double receiveMoney = 0d;
	//private Double freeMoney = 0d;
	private Double remainMoney = 0d;
    private Double clearingMoney = 0d;
	//private Double ajustMoney = 0d;
    private Double totalMoney = 0d;
	private String comments;
	// Constructors

	/** default constructor */
	public ReceivableClearing() {
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

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    
    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(Double remainMoney) {
        this.remainMoney = remainMoney;
    }

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
    public Company getCompany() {
        return company;
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

    public Set<ReceivableClearingDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<ReceivableClearingDetail> details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
     @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        ReceivableClearing detail = (ReceivableClearing)obj;
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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    //只为程序设置用，不存盘
    public ClearingType getClearingType() {
       return ClearingType.getDefaultClearingType();
    }

     public void setAffordTerm(Integer affordTerm) {
        this.affordTerm = affordTerm;
    }

    public Integer getAffordTerm() {
        return this.affordTerm;
    }
    private Integer affordTerm = 30;

    public String getName() {
        return "收款结算单";
    }
    

    private String statusString = ServiceConstants.DRAFT_STRING;
    private Integer status = ServiceConstants.DRAFT_STATUS;

    private Set<ReceivableClearingDetail> details = new HashSet<ReceivableClearingDetail>();

    private String number;
	private Integer id;
    private Company company;

}