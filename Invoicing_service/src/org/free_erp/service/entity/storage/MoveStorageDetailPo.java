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
 * AbstractMoveStorageDetail entity provides the base persistence definition of
 * the MoveStorageDetail entity.
 *
 * @author TengJianfa mobile: 086-13003311398
 */

public  class MoveStorageDetailPo extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
	private Integer id;
	private Double price = 0d;
	private Double amount=0d;;
    private Double oldAmount=0d;;
	private String shelf;
	private String comments;
	private Storage storage;
    private Storage outStorage;
    private Product product;
    
    public Employee getEmployee() {
        return this.mainObject.getEmployee();
    }

    public void setTotailMoney(Double m)
    {
        //��Ҫʵ��
    }
    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Storage getOutStorage() {
        return outStorage;
    }

    public void setOutStorage(Storage outStorage) {
        this.outStorage = outStorage;
    }

    public Storage getStorage() {
        return storage;
    }

    public Double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(Double oldAmount) {
        this.oldAmount = oldAmount;
    }
	

     /**
	 * ??
	 */
	private MoveStoragePo mainObject;
	public MoveStoragePo getMainObject()
	{
		return mainObject;
	}
	public void setMainObject(MoveStoragePo mainObject)
	{
        this.storage = mainObject.getStorage();
		this.mainObject = mainObject;
	}

	public Double getPrice()
	{
		return this.price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public Double getAmount()
	{
		return this.amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public String getShelf()
	{
		return this.shelf;
	}

	public void setShelf(String shelf)
	{
		this.shelf = shelf;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotailMoney() {
        return this.price * this.amount;
    }

	public String getComments()
	{
		return this.comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

}