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

/**
 * InitialSubjectDetail entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class InitialSubjectDetail implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private int id;
	private Subject subject;
	private InitialSubject mainObject;
	private Double accountMoney = 0d;
	private Double remainMoney = 0d;
	private Double balanceMoney = 0d;
	private String comments;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public InitialSubjectDetail() {
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

    public InitialSubject getMainObject() {
        return mainObject;
    }

    public void setMainObject(InitialSubject mainObject) {
        this.mainObject = mainObject;
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

	public Double getBalanceMoney() {
        return this.remainMoney - this.accountMoney;
		//return this.balanceMoney;
	}

//	public void setBalanceMoney(Double balanceMoney) {
//		this.balanceMoney = balanceMoney;
//	}

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

    public boolean equals(Object obj) {
         if (obj == null || !(obj instanceof InitialSubjectDetail))
        {
            return false;
        }
        return ((InitialSubjectDetail)obj).getId() == id;
    }
}