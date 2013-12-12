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
package org.free_erp.service.entity.vo;


public class CashTotalMoneyDetailsVO
{
    private Double cashMoney = 0d;
    private Double prePayableMoney = 0d;
    private Double preReceivableMoney = 0d;
    private Double receivableMoney = 0d;
    private Double payableMoney = 0d;

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Double getPayableMoney() {
        return payableMoney;
    }

    public void setPayableMoney(Double payableMoney) {
        this.payableMoney = payableMoney;
    }

    public Double getPrePayableMoney() {
        return prePayableMoney;
    }

    public void setPrePayableMoney(Double prePayableMoney) {
        this.prePayableMoney = prePayableMoney;
    }

    public Double getPreReceivableMoney() {
        return preReceivableMoney;
    }

    public void setPreReceivableMoney(Double preReceivableMoney) {
        this.preReceivableMoney = preReceivableMoney;
    }

    public Double getReceivableMoney() {
        return receivableMoney;
    }

    public void setReceivableMoney(Double receivableMoney) {
        this.receivableMoney = receivableMoney;
    }

    
}
