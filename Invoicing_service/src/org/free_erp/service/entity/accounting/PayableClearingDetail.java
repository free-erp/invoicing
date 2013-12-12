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
 * PaymentClearingDetail entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */

public class PayableClearingDetail implements java.io.Serializable {

	// Fields
    private static final long serialVersionUID = 1l;
	private int id;
	private PayableClearing mainObject;
	private String remarks;
	private Double affordableMoney = 0d;
	private Double affordRemainMoney = 0d;
	private Double clearingMoney = 0d;
	private Double ajustMoney = 0d;
	private String comments;
    private Payable payable;

	// Constructors

	/** default constructor */
	public PayableClearingDetail() {
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    public Double getAffordRemainMoney() {
        return affordRemainMoney;
    }

    public void setAffordRemainMoney(Double affordRemainMoney) {
        this.affordRemainMoney = affordRemainMoney;
    }

    public Double getAffordableMoney() {
        return affordableMoney;
    }

    public void setAffordableMoney(Double affordableMoney) {
        this.affordableMoney = affordableMoney;
    }

    public Double getAjustMoney() {
        return ajustMoney;
    }

    public void setAjustMoney(Double ajustMoney) {
        this.ajustMoney = ajustMoney;
    }

    public Double getClearingMoney() {
        return clearingMoney;
    }

    public void setClearingMoney(Double clearingMoney) {
        this.clearingMoney = clearingMoney;
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

    public PayableClearing getMainObject() {
        return mainObject;
    }

    public void setMainObject(PayableClearing mainObject) {
        this.mainObject = mainObject;
    }

    public Payable getPayable() {
        return payable;
    }

    public void setPayable(Payable payable) {
        this.payable = payable;
    }

     @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        PayableClearingDetail detail = (PayableClearingDetail)obj;
        return detail.getId() == id;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}