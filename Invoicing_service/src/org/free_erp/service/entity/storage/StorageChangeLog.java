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
package org.free_erp.service.entity.storage;

import java.util.Date;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @author TengJianfa 13003311398
 */
public class StorageChangeLog extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
	private Integer id;
    private Company company;
    private Product product;
    private Storage storage;
    private String formNumber;
    private String changeType;
    private Double changedAmount = 0d;
    private Double changedPrice = 0d;
    private String remark;
    private String comments;
    private Date formDate;
    private Double currentAmount = 0d;
    private Double currentPrice = 0d;

    public Double getChangedTotalMoney() {
        return changedPrice * changedAmount;
    }

    public Double getCurrentTotalMoney() {
        return currentPrice * currentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getChangedAmount() {
        return changedAmount;
    }

    public void setChangedAmount(Double changedAmount) {
        this.changedAmount = changedAmount;
    }

    public Double getChangedPrice() {
        return changedPrice;
    }

    public void setChangedPrice(Double changedPrice) {
        this.changedPrice = changedPrice;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

}
