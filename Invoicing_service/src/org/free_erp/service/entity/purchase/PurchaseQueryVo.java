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
package org.free_erp.service.entity.purchase;

import java.util.Date;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PurchaseQueryVo {

    public PurchaseQueryVo(Company company) {
        this.company = company;
    }

    private Storage storage;
    private Date startDate;
    private Date endDate;
    private Date startDatePo;
    private Date endDatePo;
    private Product product;
    private String catalogName;
    private String productName;
    private Customer supplier;
    private Employee chargePerson;
    private Company company;
    private Integer status;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Employee getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(Employee chargePerson) {
        this.chargePerson = chargePerson;
    }

    public Customer getSupplier() {
        return supplier;
    }

    public void setSupplier(Customer supplier) {
        this.supplier = supplier;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDatePo() {
        return endDatePo;
    }

    public void setEndDatePo(Date endDatePo) {
        this.endDatePo = endDatePo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDatePo() {
        return startDatePo;
    }

    public void setStartDatePo(Date startDatePo) {
        this.startDatePo = startDatePo;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
