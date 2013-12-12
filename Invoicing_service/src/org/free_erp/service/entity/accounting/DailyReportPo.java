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

import java.text.SimpleDateFormat;
import java.util.Date;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;

/**
 *
 * @author TengJianfa 13003311398
 */
public class DailyReportPo extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private int id;
    private Date formDate;
    private String month;
    private String operationType;
    private String formNumber;
    private Customer customer;
    private Double saleMoney = 0d;
    private Double receiveMoney = 0d;
    private Double grossProfitMoney = 0d;
    private Double cost = 0d;
    private Double purchaseMoney = 0d;
    private Double paymentMoney = 0d;
    private Double cashMoney = 0d;
    private Double stockMoney = 0d;
    private Double prePayableMoney = 0d;
    private Double preReceivableMoney = 0d;
    private Double receivableMoney = 0d;
    private Double payableMoney = 0d;
    private Company company;
    
    public DailyReportPo()
    {
        this.createDate = new Date();
    }

    public String getMonth() {
        return month;
    }

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM");
        this.month = dateFm.format(formDate);
        this.formDate = formDate;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public Double getGrossProfitMoney() {
        return grossProfitMoney;
    }

    public void setGrossProfitMoney(Double grossProfitMoney) {
        this.grossProfitMoney = grossProfitMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getPayableMoney() {
        return payableMoney;
    }

    public void setPayableMoney(Double payableMoney) {
        this.payableMoney = payableMoney;
    }

    public Double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Double paymentMoney) {
        this.paymentMoney = paymentMoney;
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

    public Double getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(Double purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public Double getReceivableMoney() {
        return receivableMoney;
    }

    public void setReceivableMoney(Double receivableMoney) {
        this.receivableMoney = receivableMoney;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(Double stockMoney) {
        this.stockMoney = stockMoney;
    }
    
}
