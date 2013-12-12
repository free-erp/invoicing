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
import java.util.Date;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Company;

/**
 *
 * @author Administrator
 */
public class SaleProductPo extends ErpObject implements FormPo, java.io.Serializable {

    private static final long serialVersionUID = 1l;
       private  Integer id;
   private String number;
    private  Integer productId ;
    private String  name;
  private  Double amount=0.0 ;
    private  Double  notAccount=0.0;
    private  Double  totalMoney=0.0;
  private  Double  notTotalMoney=0.0;
   private  Double clinchAccount=0.0;
   private  Double   clinchTotalMoney=0.0;
   private  String   spec;
  private  String    unit;
     private String  catalogName;
 private  Company  company ;
private Date formDate;

public SaleProductPo()
{
    this.createDate = new Date();
}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

 

    public Double getClinchTotalMoney() {
        return clinchTotalMoney;
    }

    public void setClinchTotalMoney(Double clinchTotalMoney) {
        this.clinchTotalMoney = clinchTotalMoney;
    }

    public Double getNotTotalMoney() {
        return notTotalMoney;
    }

    public void setNotTotalMoney(Double notTotalMoney) {
        this.notTotalMoney = notTotalMoney;
    }

    public Double getClinchAccount() {
        return clinchAccount;
    }

    public void setClinchAccount(Double clinchAccount) {
        this.clinchAccount = clinchAccount;
    }

    public Double getNotAccount() {
        return notAccount;
    }

    public void setNotAccount(Double notAccount) {
        this.notAccount = notAccount;
    }



   
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }



    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

  

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

 

    public Date getFormDate() {
      return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate =formDate;
    }

    public String getNumber() {
     return number;
    }

    public String getName() {
       return name;
    }

    public Integer getId() {
      return id;
    }


}
