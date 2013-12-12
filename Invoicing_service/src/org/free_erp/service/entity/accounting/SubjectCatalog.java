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
 * SubjectCatalog entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class SubjectCatalog implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private String name;
	private String code;
	private Integer parentId;
	private String comments;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
    private BSubjectCatalog mainCatalog;
    private String debitCredit;

	// Constructors

	/** default constructor */
	public SubjectCatalog() {
        this.createDate = new Date();
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BSubjectCatalog getMainCatalog() {
        return mainCatalog;
    }

    public void setMainCatalog(BSubjectCatalog mainCatalog) {
        this.mainCatalog = mainCatalog;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        return ((SubjectCatalog)obj).getId() == id;
    }

    
    private String number;
	private int id;
    

}