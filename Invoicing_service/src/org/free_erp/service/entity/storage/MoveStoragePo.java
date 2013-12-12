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
import java.util.HashSet;
import java.util.Set;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;

/**
 * AbstractMoveStorage entity provides the base persistence definition of the
 * MoveStorage entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */
public class MoveStoragePo extends ErpObject  implements FormPo,java.io.Serializable {

    private static final long serialVersionUID = 1l;
    private Integer id;
    private Date formDate;
    private String number;
    private String department;
    private Double totailMoney=0d;
    private String comments;
    private Integer status= ServiceConstants.DRAFT_STATUS;
    private String statusString;
    private Company company;
    private Storage storage;
    private Storage outStorage; //≤ªsave to db
    private int outStorageId;

    public MoveStoragePo()
    {
        this.createDate = new Date();
    }

    public Storage getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(Storage outStorage) {
        this.outStorage = outStorage;
    }

    public int getOutStorageId() {
        return outStorageId;
    }

    public void setOutStorageId(int outStorageId) {
        this.outStorageId = outStorageId;
    }
    private Set<MoveStorageDetailPo> details = new HashSet<MoveStorageDetailPo>(0);

    public Company getCompany() {
        return company;
    }
	public Set<MoveStorageDetailPo> getDetails() {
		return details;
	}
	public void setDetails(Set<MoveStorageDetailPo> details) {
		this.details = details;
	}

    public void setCompany(Company company) {
        this.company = company;
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
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public Double getTotailMoney() {
        return totailMoney;
    }

    public void setTotailMoney(Double totailMoney) {
        this.totailMoney = totailMoney;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.setStatusString(status);
        this.status = status;
    }

    public String getStatusString()
    {
        return this.statusString;
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

    public String getName() {
        return "ø‚¥Ê“∆ø‚µ•";
    }
}