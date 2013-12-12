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

import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PurchaseOrderDetailPo extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private Integer id;
	private Product product;
	private Double price;
    private Double discount = 100d;
    private Double disprice = 0d;
    private Integer taxRate;
    private Double priceTaxMoney = 0d;
	private Double amount;
	private Double totalMoney = 0d;
	private String comments;
    private PurchaseOrderPo mainObject;
    private String shelf;
    private Customer supplier;
	private Employee chargePerson;

    public String getCatalogName()
    {
        if(this.product != null)
        {
            return this.product.getCatalog().getName();
        }
        return "";
    }
    
    public Employee getChargePerson() {
        return chargePerson;
    }

    public Customer getSupplier() {
        return supplier;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseOrderPo getMainObject() {
        return mainObject;
    }

    public void setMainObject(PurchaseOrderPo mainObject) {
        this.supplier = mainObject.getSupplier();
        this.chargePerson = mainObject.getChargePerson();
        this.mainObject = mainObject;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceTaxMoney() {
        return priceTaxMoney;
    }

    public void setPriceTaxMoney(Double priceTaxMoney) {
        this.priceTaxMoney = priceTaxMoney;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
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

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public Double getDisprice() {
        return disprice;
    }

    public void setDisprice(Double disprice) {
        this.disprice = disprice;
    }
    
}
