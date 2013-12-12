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
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;

/**
 * AbstractMinMaxStorageDetail entity provides the base persistence definition
 * of the MinMaxStorageDetail entity.
 *
 * @author TengJianfa mobile: 086-13003311398
 */

public class MinMaxStorageDetailPo extends ErpObject implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
	private Integer id;
	private String shelf;
	private String comments;
	private Double minAmount=0d;;
	private Double maxAmount=0d;;
    private Storage storage;
    private Product product;
    public void setTotailMoney(Double m)
    {
        //不要实现
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
    
    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    /**
	 * 统一
	 */
	private MinMaxStoragePo mainObject;
	public MinMaxStoragePo getMainObject()
	{
		return mainObject;
	}
	public void setMainObject(MinMaxStoragePo mainObject)
	{
        this.storage = mainObject.getStorage();
		this.mainObject = mainObject;        
	}
	public String getShelf()
	{
		return this.shelf;
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public void setShelf(String shelf)
	{
		this.shelf = shelf;
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