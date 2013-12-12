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
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;

/**
 *
 * @author Administrator
 */
public class SaleOrderBackPo extends ErpObject implements FormPo, java.io.Serializable {
    private static final long serialVersionUID = 1l;
  private Employee employee;
  private Customer  customer;
    private Integer id;
    private String number;
    private String supplier;
    private Date formDate;
    private String chargePerson;
    private String department;
    private Double totalMoney;
    private String comments;
    private String statusString;
    private Integer status = ServiceConstants.DRAFT_STATUS;
    private Company company;
    private Storage storage;
    private Set<SaleOrderBackDetailPo> details = new HashSet<SaleOrderBackDetailPo>();
    private String clearing;
      private String pay;
      private String receiving;
      private String delivery;
      private String receivingPhone;
      private String receivingCode;
      private String receivingAddress;
    private Integer payTerm=30;
   private AffordType payFashion;
    private ClearingType balanceFashion;
   private Integer way;
        private  SaleOrderPo order;
        private SalePo sale;
        private Integer systemStatus = ServiceConstants.SYSTEM_STATUS;
   private Double   offersMoney;

   public SaleOrderBackPo()
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

    public Integer getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(Integer payTerm) {
        this.payTerm = payTerm;
    }

    public SaleOrderPo getOrder() {
        return order;
    }

    public void setOrder(SaleOrderPo order) {
        this.order = order;
    }

    public SalePo getSale() {
        return sale;
    }

    public void setSale(SalePo sale) {
        this.sale = sale;
    }
    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
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

    
      
    public String getClearing() {
        return clearing;
    }

    public void setClearing(String clearing) {
        this.clearing = clearing;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone;
    }

    public String getReceivingCode() {
        return receivingCode;
    }

    public void setReceivingCode(String receivingCode) {
        this.receivingCode = receivingCode;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Set<SaleOrderBackDetailPo> getDetails() {
        return details;
    }

    public void setDetails(Set<SaleOrderBackDetailPo> details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getFormDate() {
        return this.formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getName() {
        return "销售退货单";
    }
}

