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
package org.free_erp.service.logic;


import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.sale.SaleChangeMoneyDetailPo;
import org.free_erp.service.entity.sale.SaleChangeMoneyPo;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.entity.sale.SaleOrderBackDetailPo;
import org.free_erp.service.entity.sale.SaleOrderBackPo;
import org.free_erp.service.entity.sale.SaleOrderDetailPo;
import org.free_erp.service.entity.sale.SaleOrderPo;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.entity.sale.SaleProductPo;
import org.free_erp.service.entity.sale.SaleProductStatPo;
import org.free_erp.service.entity.sale.SaleQuoteDetailPo;
import org.free_erp.service.entity.sale.SaleQuotePo;
import org.free_erp.service.entity.sale.SalechaseOrderDetailPo;
import org.free_erp.service.entity.sale.SalechaseOrderPo;
import org.free_erp.service.exception.InitialLackedException;
import org.free_erp.service.logic.sale.SaleQueryVo;

/**
 *
 * @author Administrator
 */
public interface ISaleService {
    public void saveSaleDetailForm(SalePo po);
    public void deleteSaleDetailForm(SalePo po);
    public List<SalePo> getAllSaleDetailForms(Company company);
    public List<SalePo> findSaleDetailForms(SaleQueryVo vo);
    public List<SaleDetailPo> getAllDraftSaleDetailForms(Company company);
	public List<SaleDetailPo> findSaleDetailDetails(SaleQueryVo vo);

    public void saveSaleChangeMoneyForm(SaleChangeMoneyPo po);
    public void deleteSaleChangeMoneyForm(SaleChangeMoneyPo po);
    public List<SaleChangeMoneyPo> getAllSaleChangeMoneyForms(Company company);
    public List<SaleChangeMoneyPo> findSaleChangeMoneyForms(SaleQueryVo vo);
    public List<SaleChangeMoneyDetailPo> getAllDraftSaleChangeMoneyForms(Company company);
	public List<SaleChangeMoneyDetailPo> findSaleChangeMoneyDetails(SaleQueryVo vo);

    public void saveSaleChangeOrderForm(SalechaseOrderPo po);
    public void deleteChangeOrderForm(SalechaseOrderPo po);
    public List<SalechaseOrderPo> getAllSaleChangeOrderForms(Company company);
    public List<SalechaseOrderPo> findSaleChangeOrderForms(SaleQueryVo vo);
    public List<SalechaseOrderDetailPo> getAllDraftSaleChangeOrderyForms(Company company);
	public List<SalechaseOrderDetailPo> findSaleChangeOrderDetails(SaleQueryVo vo);

     public void saveSaleOrderBackForm(SaleOrderBackPo po);
    public void deleteSaleOrderBackForm(SaleOrderBackPo po);
    public List<SaleOrderBackPo> findSaleOrderBackForms(SaleQueryVo vo);
    public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company);
    public List<SaleOrderBackDetailPo> findSaleOrderBackDetails(SaleQueryVo vo);
    public List<SaleOrderBackDetailPo> getAllDraftSaleOrderBackForms(Company company);

    public void saveSaleOrderForm(SaleOrderPo po);
    public void deleteOrderForm(SaleOrderPo po);
    public List<SaleOrderPo> findSaleOrderForms(SaleQueryVo vo);
    public List<SaleOrderPo> getAllSaleOrderForms(Company company);
    public List<SaleOrderDetailPo> findSaleOrderDetails(SaleQueryVo vo);
    public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Company company);

     public void saveSaleQuoteForm(SaleQuotePo po);
    public void deleteQuoteForm(SaleQuotePo po);
    public List<SaleQuotePo> findSaleQuoteForms(SaleQueryVo vo);
    public List<SaleQuotePo> getAllQuoteForms(Company company);
    public List<SaleQuoteDetailPo> findQuoteDetails(SaleQueryVo vo);
    public List<SaleQuoteDetailPo> getAllDraftQuotederForms(Company company);

    public void saveSaleDiscountForm(SaleQuotePo po);
    public void deleteSaleDiscountForm(SaleQuotePo po);
    public List<SaleQuotePo> findSaleDiscountForms(SaleQueryVo vo);
    public List<SaleQuotePo> getAllSaleDiscountForms(Company company);
    public List<SaleQuoteDetailPo> findSaleDiscountDetails(SaleQueryVo vo);
    public List<SaleQuoteDetailPo> getAllSaleDiscountDetailPoForms(Company company);
     public void passCommonSaleForm(Object po) throws InitialLackedException;

     public List<SaleProductPo> getSaleProductForms(SaleQueryVo vo);
 public List<SaleOrderDetailPo> findSaleBalance(SaleQueryVo vo);
   public List<SaleOrderDetailPo> findSaleOrderProductDaetails(SaleQueryVo vo);
   public List<SaleOrderDetailPo> findSaleOrderEmployeeProductDaetails(SaleQueryVo vo);
    public List<SaleOrderDetailPo> findSaleOrderCustomerProductDaetails(SaleQueryVo vo);

   public List<SaleDetailPo> findSaleProductDaetails(SaleQueryVo vo);
   public List<SaleDetailPo> findSaleEmployeeProductDaetails(SaleQueryVo vo);
    public List<SaleDetailPo> findSaleCustomerProductDaetails(SaleQueryVo vo);
    public List<SaleProductStatPo> findSaleProductBalanceDaetails(SaleQueryVo vo);
    public  List<SaleOrderPo>  getSaleOrderNotSaleForms(Company company);
    public List<SalePo> getAllSaleDetailForms(Company company,int status);
    public boolean commonSale(SalePo po);

    public Double getSalePriceDaetails(Product product,Customer customer,Company company);

       
}
