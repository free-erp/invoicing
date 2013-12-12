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
import org.free_erp.service.entity.base.Company;

/**
 *本Entity记录财务表单审核时候的信息
 * @author afa
 */
public class SubjectDetail implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private int  id;
    private Date formDate;
    private String formNo;
    private String remark;
    private String bussinessType;
	private Subject subject;
    private Double debitMoney=0d;
    private Double creditMoney=0d;
    private Double initialMoney=0d;
    private Double remainMoney=0d; //不存盘
    private Company company;

	// Constructors

	/** default constructor */
	public SubjectDetail() {
	}


	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(Double creditMoney) {
        this.creditMoney = creditMoney;
    }

    public Double getDebitMoney() {
        return debitMoney;
    }

    public void setDebitMoney(Double debitMoney) {
        this.debitMoney = debitMoney;
    }

    public Double getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(Double initialMoney) {
        this.initialMoney = initialMoney;
    }

    public Double getRemainMoney() {
       if (subject.getDebitCredit().trim().equals("贷"))
       {
           return this.initialMoney - this.debitMoney + this.creditMoney;
       }
        //default:
        return this.initialMoney + this.debitMoney - this.creditMoney;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}