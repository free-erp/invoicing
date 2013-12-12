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
import java.util.HashSet;
import java.util.Set;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PurchaseBackPo extends ErpObject implements FormPo, java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private Integer id;
    private String number;
	private Customer supplier;
	private Date formDate;
	private Employee chargePerson;
	private String department;
	private Double totalMoney = 0d;
	private String comments;
    private String statusString;
    private Integer status = ServiceConstants.DRAFT_STATUS;
    private Company company;
    private Storage storage;
    private Set<PurchaseBackDetailPo> details = new HashSet<PurchaseBackDetailPo>();
    private String defaultPrice;
    private AffordType payFashion;
    private ClearingType balanceFashion;
    private Integer payTerm = 30;
    private String operationType = "采购退货";
    private Subject subject;
    private PurchaseOrderPo purchaseOrderPo;
    private Integer systemStatus = ServiceConstants.SYSTEM_STATUS;
    private Double offerMoney = 0d;

    public PurchaseBackPo()
    {
        this.createDate = new Date();
    }

    public Double getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(Double offerMoney) {
        this.offerMoney = offerMoney;
    }

    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }

    public PurchaseOrderPo getPurchaseOrderPo() {
        return purchaseOrderPo;
    }

    public void setPurchaseOrderPo(PurchaseOrderPo purchaseOrderPo) {
        this.purchaseOrderPo = purchaseOrderPo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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

    public String getStatusString()
    {
        return this.statusString;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setStatusString(Integer status) {
        switch(status)
        {
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
    
    public Date getFormDate() {
        return this.formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Set<PurchaseBackDetailPo> getDetails() {
        return details;
    }

    public void setDetails(Set<PurchaseBackDetailPo> details) {
        this.details = details;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public ClearingType getBalanceFashion() {
        return balanceFashion;
    }

    public void setBalanceFashion(ClearingType balanceFashion) {
        this.balanceFashion = balanceFashion;
    }

    public AffordType getPayFashion() {
        return payFashion;
    }

    public void setPayFashion(AffordType payFashion) {
        this.payFashion = payFashion;
    }

    public Integer getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(Integer payTerm) {
        this.payTerm = payTerm;
    }

    public String getName()
    {
        return "采购退货单";
    }

}
