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

/**
 * Subject entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class Subject implements java.io.Serializable {

    private static final long serialVersionUID = 1l;
	private SubjectCatalog catalog;
	private BSubjectCatalog mainSubjectCatalog;
	private String name;
	private String shortName;
	private String debitCredit;
	private String code;
	private String comments;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
    private int status = ServiceConstants.SUBJECT_COMMON_STATUS;
	

	// Constructors

	/** default constructor */
	public Subject() {
        this.createDate = new Date();
	}

    public SubjectCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(SubjectCatalog catalog) {
        this.catalog = catalog;
    }

	

    public BSubjectCatalog getMainSubjectCatalog() {
        return mainSubjectCatalog;
    }

    public void setMainSubjectCatalog(BSubjectCatalog mainSubjectCatalog) {
        this.mainSubjectCatalog = mainSubjectCatalog;
    }

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return this.getCatalog().getName() + "." + name;
	}

	

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

	

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public boolean equals(Object obj) {
         if (obj == null || !(obj instanceof Subject))
        {
            return false;
        }
        return ((Subject)obj).getId() == id;
    }

    @Override
    public String toString() {
        return  number;//name;//"[" + number + "]" + name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    private String number;
	private int id;
    private Company company;

}