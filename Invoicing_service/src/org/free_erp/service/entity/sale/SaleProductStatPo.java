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
package org.free_erp.service.entity.sale;

import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;

/**
 *
 * @author Administrator
 */
public class SaleProductStatPo {
    private static final long serialVersionUID = 1l;
      private Product product;
      private Double totalMoney;
      private Double amount;
      private Double clinchTotalMoney=0.0;
      private Double clinchAmount=0.0;
      private Double notClinchAmount=0.0;
      private Double notClinchTotalMoney=0.0;
      private Employee employee;
     private Customer customer;



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getClinchAmount() {
        return clinchAmount;
    }

    public void setClinchAmount(Double clinchAmount) {
        this.clinchAmount = clinchAmount;
    }

    public Double getClinchTotalMoney() {
        return clinchTotalMoney;
    }

    public void setClinchTotalMoney(Double clinchTotalMoney) {
        this.clinchTotalMoney = clinchTotalMoney;
    }

    public Double getNotClinchAmount() {
        return notClinchAmount;
    }

    public void setNotClinchAmount(Double notClinchAmount) {
        this.notClinchAmount = notClinchAmount;
    }

    public Double getNotClinchTotalMoney() {
        return notClinchTotalMoney;
    }

    public void setNotClinchTotalMoney(Double notClinchTotalMoney) {
        this.notClinchTotalMoney = notClinchTotalMoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
