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

import org.free_erp.service.entity.ErpObject;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;

/**
 * AbstractCheckStorageDetail entity provides the base persistence definition of
 * the CheckStorageDetail entity.
 * 
 * @author TengJianfa mobile: 086-13003311398
 */
public class CheckStorageDetailPo extends ErpObject  implements java.io.Serializable {
    private static final long serialVersionUID = 1l;
    private Integer id;
    private Double price=0d;;
    private Double oldAmount=0d;;
    private Double oldMoney=0d; ;
    private Double amount=0d;;
    private Double disAmount=0d;;
    private Double disMoney=0d;;
    private String shelf;
    private String comments;
	private Storage storage;
    private Product product;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }
    
    public void setTotailMoney(Double m)
    {
        //不要实现
    }
    public String getCatalogName()
    {
        return this.product.getCatalog().getName();
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
	
    /**
	 * 统一
	 */
	private CheckStoragePo mainObject;
	public CheckStoragePo getMainObject()
	{
		return mainObject;
	}
	public void setMainObject(CheckStoragePo mainObject)
	{
        this.storage = mainObject.getStorage();
        this.employee = mainObject.getEmployee();
		this.mainObject = mainObject;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
        this.refreshDisMoney();
        this.refreshDisAmount();
    }

    public Double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(Double oldAmount) {
        this.oldAmount = oldAmount;
        this.refreshOldMoney();
        this.refreshDisMoney();
        this.refreshDisAmount();
    }

    private void refreshOldMoney()
    {
        if (this.price != null && this.oldAmount != null)
        {
            this.oldMoney = this.price * this.oldAmount;
        }
        else
        {
            this.oldMoney = 0d;
        }
    }

    private void refreshDisMoney()
    {
        if (this.price != null && this.oldAmount != null && this.amount != null)
        {
            this.disMoney = this.price * (this.amount - this.oldAmount);
        }
        else
        {
            this.disMoney = 0d;
        }
    }

    private void refreshDisAmount()
    {
        if (this.oldAmount != null && this.amount != null)
        {
            this.disAmount = this.amount - this.oldAmount;
        }
        else
        {
            this.disAmount = 0d;
        }
    }

    public Double getTotailMoney() {
        return this.price * this.amount;
    }

   public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.refreshOldMoney();
        this.refreshDisMoney();
    }

    public Double getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney() {
    }

    public Double getDisAmount() {
        return disAmount;
    }

    public Double getDisMoney() {
        return disMoney;
    }

    public String getShelf() {
        return this.shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}