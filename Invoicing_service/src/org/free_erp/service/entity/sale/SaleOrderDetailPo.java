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
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;

/**
 *
 * @author Administrator
 */
public class SaleOrderDetailPo extends ErpObject implements java.io.Serializable{
    private static final long serialVersionUID = 1l;
     private Integer id;
      private Double taxprice;
      private Double tax;
      private Integer priceTax;
      private Double price;
      private Integer discount;
      private Double amount;
      private Double rate=100.00;
      private Double totalMoney;
      private String comments;
    private SaleOrderPo mainObject;
     private Double taxPrice;
       private Double taxPriceMoney;
       private Double taxMoney;
	private Product product;
    private Customer customer;
    private  Employee  employee;

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
    
    public Double getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(Double taxMoney) {
        this.taxMoney = taxMoney;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getTaxPriceMoney() {
        return taxPriceMoney;
    }

    public void setTaxPriceMoney(Double taxPriceMoney) {
        this.taxPriceMoney = taxPriceMoney;
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
        }


        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public SaleOrderPo getMainObject() {
            return mainObject;
        }

 

        public void setMainObject(SaleOrderPo mainObject) {
             this.customer = mainObject.getCustomer();
        this.employee = mainObject.getEmployee();
            this.mainObject = mainObject;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getPriceTax() {
            return priceTax;
        }

        public void setPriceTax(Integer priceTax) {
            this.priceTax = priceTax;
        }


        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }


        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTaxprice() {
            return taxprice;
        }

        public void setTaxprice(Double taxprice) {
            this.taxprice = taxprice;
        }

        public Double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(Double totalMoney) {
            this.totalMoney = totalMoney;
        }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

