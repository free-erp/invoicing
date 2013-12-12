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

/**
 * AbstractInitialStorageDetail entity provides the base persistence definition
 * of the InitialStorageDetail entity.
 *
 * @author TengJianfa mobile: 086-13003311398
 */

public class InitialStorageDetailPo extends ErpObject implements java.io.Serializable
{
	// Fields
    private static final long serialVersionUID = 1l;
	private Integer id;
    private Double price=0d;;
	private Double amount=0d;;
	private String shelf;
	private String comments;
    private InitialStoragePo mainObject;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InitialStoragePo getMainObject() {
        return mainObject;
    }

    public void setMainObject(InitialStoragePo mainObject) {
        this.mainObject = mainObject;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public Double getTotailMoney() {
        return this.price * this.amount;
    }

    
}