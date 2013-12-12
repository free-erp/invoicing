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
import java.util.HashSet;
import java.util.Set;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @author Administrator
 */
public class SaleQuotePo extends ErpObject implements FormPo, java.io.Serializable {

    private static final long serialVersionUID = 1l;
    private Integer id;
    private String number;
    private String statusString;
    private String supplier;
    private Date formDate;
    private String chargePerson;
    private String department;
    private Double totalMoney;
    private String comments;
    private Integer status = ServiceConstants.DRAFT_STATUS;
    private Company company;
    private Storage storage;
    private Set<SaleQuoteDetailPo> details = new HashSet<SaleQuoteDetailPo>();
private Customer customer;
    private Employee employee;
    private Integer systemStatus = ServiceConstants.SYSTEM_STATUS;
   private Double   offersMoney;

   public SaleQuotePo()
   {
       this.createDate = new Date();
   }

    public Double getOffersMoney() {
        return offersMoney;
    }

    public void setOffersMoney(Double offersMoney) {
        this.offersMoney = offersMoney;
    }
    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }

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
    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
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

   
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<SaleQuoteDetailPo> getDetails() {
        return details;
    }

    public void setDetails(Set<SaleQuoteDetailPo> details) {
        this.details = details;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.setStatusString(status);
        this.status = status;
    }

    public String getStatusString() {
        return this.statusString;
    }

    public void setStatusString(Integer status) {
        switch (status) {
            case ServiceConstants.DRAFT_STATUS:
                this.statusString = ServiceConstants.DRAFT_STRING;
                break;
            case ServiceConstants.FORMAL_STATUS:
                this.statusString = ServiceConstants.FORMAL_STRING;
                break;
            case ServiceConstants.DISCARD_STATUS:
                this.statusString = ServiceConstants.DISCARD_STRING;
                break;
            default:
                this.statusString = ServiceConstants.UNKNOWN;
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Date getFormDate() {
        return this.formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getName() {
        return "商品报价单";
    }
}
