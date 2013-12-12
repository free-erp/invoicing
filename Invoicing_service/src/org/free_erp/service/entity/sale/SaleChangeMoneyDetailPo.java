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

import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.base.Product;

/**
 *
 * @author Administrator
 */
public class SaleChangeMoneyDetailPo extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
     private Integer id;
 private String extColumn1;
 private String extColumn2;
 private String extColumn3;
 private String number;
 private String name;
 private String catalogName;
 private String spec;
 private String unit;
 private Double oldPrice;
 private Double price;
 private Double oldWholesaleprice;
 private Double whlesale;
 private Double whlesale1;
 private Double oldWebsite2;
 private Double website2;
 private Double oldWebsite3;
 private Double website3;
 private Double oldWebsite4;
 private Double website4;
 private Double oldWebsite5;
 private Double website5;
 private Double oldPurchaseprice;
 private Double purchaseprice;
private  Double totalMoney;
 private String SName;
 private Double amount;
 private Double tmoney;
 private String comments;
 	private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    private SaleChangeMoneyPo mainObject;

    public String getName() {
        return name;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getExtColumn1() {
        return extColumn1;
    }

    public void setExtColumn1(String extColumn1) {
        this.extColumn1 = extColumn1;
    }

    public String getExtColumn2() {
        return extColumn2;
    }

    public void setExtColumn2(String extColumn2) {
        this.extColumn2 = extColumn2;
    }

    public String getExtColumn3() {
        return extColumn3;
    }

    public void setExtColumn3(String extColumn3) {
        this.extColumn3 = extColumn3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SaleChangeMoneyPo getMainObject() {
        return mainObject;
    }

    public void setMainObject(SaleChangeMoneyPo mainObject) {
        this.mainObject = mainObject;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getOldPurchaseprice() {
        return oldPurchaseprice;
    }

    public void setOldPurchaseprice(Double oldPurchaseprice) {
        this.oldPurchaseprice = oldPurchaseprice;
    }

    public Double getOldWebsite2() {
        return oldWebsite2;
    }

    public void setOldWebsite2(Double oldWebsite2) {
        this.oldWebsite2 = oldWebsite2;
    }

    public Double getOldWebsite3() {
        return oldWebsite3;
    }

    public void setOldWebsite3(Double oldWebsite3) {
        this.oldWebsite3 = oldWebsite3;
    }

    public Double getOldWebsite4() {
        return oldWebsite4;
    }

    public void setOldWebsite4(Double oldWebsite4) {
        this.oldWebsite4 = oldWebsite4;
    }

    public Double getOldWebsite5() {
        return oldWebsite5;
    }

    public void setOldWebsite5(Double oldWebsite5) {
        this.oldWebsite5 = oldWebsite5;
    }

    public Double getOldWholesaleprice() {
        return oldWholesaleprice;
    }

    public void setOldWholesaleprice(Double oldWholesaleprice) {
        this.oldWholesaleprice = oldWholesaleprice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getTmoney() {
        return tmoney;
    }

    public void setTmoney(Double tmoney) {
        this.tmoney = tmoney;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWebsite2() {
        return website2;
    }

    public void setWebsite2(Double website2) {
        this.website2 = website2;
    }

    public Double getWebsite3() {
        return website3;
    }

    public void setWebsite3(Double website3) {
        this.website3 = website3;
    }

    public Double getWebsite4() {
        return website4;
    }

    public void setWebsite4(Double website4) {
        this.website4 = website4;
    }

    public Double getWebsite5() {
        return website5;
    }

    public void setWebsite5(Double website5) {
        this.website5 = website5;
    }

    public Double getWhlesale() {
        return whlesale;
    }

    public void setWhlesale(Double whlesale) {
        this.whlesale = whlesale;
    }

    public Double getWhlesale1() {
        return whlesale1;
    }

    public void setWhlesale1(Double whlesale1) {
        this.whlesale1 = whlesale1;
    }


}



