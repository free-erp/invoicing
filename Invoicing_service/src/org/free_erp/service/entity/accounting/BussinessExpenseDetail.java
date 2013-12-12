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

/**
 * BussinessExpenseDetail entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class BussinessExpenseDetail implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1l;
	private Subject subject;
	private Double totalMoney = 0d;
	private String comments;

	// Constructors

	/** default constructor */
	public BussinessExpenseDetail() {
	}

	// Property accessors

	

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public BussinessExpense getMainObject() {
        return mainObject;
    }

    public void setMainObject(BussinessExpense mainObject) {
        this.mainObject = mainObject;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        BussinessExpenseDetail detail = (BussinessExpenseDetail)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }



    private BussinessExpense mainObject;
	private int id;


}