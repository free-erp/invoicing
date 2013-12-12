/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.free_erp.service.dao.system;

import java.util.List;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.sale.SaleChangeMoneyDetailPo;
import org.free_erp.service.entity.sale.SaleChangeMoneyPo;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.entity.sale.SaleOrderBackDetailPo;
import org.free_erp.service.entity.sale.SaleOrderBackPo;
import org.free_erp.service.entity.sale.SaleOrderDetailPo;
import org.free_erp.service.entity.sale.SaleOrderPo;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.entity.sale.SaleProductPo;
import org.free_erp.service.entity.sale.SaleQuoteDetailPo;
import org.free_erp.service.entity.sale.SaleQuotePo;
import org.free_erp.service.entity.sale.SalechaseOrderDetailPo;
import org.free_erp.service.entity.sale.SalechaseOrderPo;
import org.free_erp.service.entity.storage.StorageProductPo;
import org.free_erp.service.logic.sale.SaleQueryVo;

/**
 *
 * @author Administrator
 */
public interface ISaleDao {

    public List<SalePo> getAllSaleDetailForms(Company company);
    public List<SalePo> findSaleDetailForms(SaleQueryVo vo);
    public List<SaleDetailPo> getAllDraftSaleDetailForms(Company company);
	public List<SaleDetailPo> findSaleDetailDetails(SaleQueryVo vo);

    public List<SaleChangeMoneyPo> getAllSaleChangeMoneyForms(Company company);
    public List<SaleChangeMoneyPo> findSaleChangeMoneyForms(SaleQueryVo vo);
    public List<SaleChangeMoneyDetailPo> getAllDraftSaleChangeMoneyForms(Company company);
	public List<SaleChangeMoneyDetailPo> findSaleChangeMoneyDetails(SaleQueryVo vo);


    public List<SalechaseOrderPo> getAllSaleChangeOrderForms(Company company);
    public List<SalechaseOrderPo> findSaleChangeOrderForms(SaleQueryVo vo);
    public List<SalechaseOrderDetailPo> getAllDraftSaleChangeOrderyForms(Company company);
	public List<SalechaseOrderDetailPo> findSaleChangeOrderDetails(SaleQueryVo vo);

    public List<SaleOrderBackPo> findSaleOrderBackForms(SaleQueryVo vo);
    public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company);
    public List<SaleOrderBackDetailPo> findSaleOrderBackDetails(SaleQueryVo vo);
    public List<SaleOrderBackDetailPo> getAllDraftSaleOrderBackForms(Company company);



    public List<SaleOrderPo> findSaleOrderForms(SaleQueryVo vo);
    public List<SaleOrderPo> getAllSaleOrderForms(Company company);
    public List<SaleOrderDetailPo> findSaleOrderDetails(SaleQueryVo vo);
    public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Company company);
    public List<SaleOrderPo> getSaleOrderForms(Company company,int y);

  
    public List<SaleQuotePo> findSaleQuoteForms(SaleQueryVo vo);
    public List<SaleQuotePo> getAllQuoteForms(Company company);
    public List<SaleQuoteDetailPo> findQuoteDetails(SaleQueryVo vo);
    public List<SaleQuoteDetailPo> getAllDraftQuotederForms(Company company);

  
    public List<SaleQuotePo> findSaleDiscountForms(SaleQueryVo vo);
    public List<SaleQuotePo> getAllSaleDiscountForms(Company company);
    public List<SaleQuoteDetailPo> findSaleDiscountDetails(SaleQueryVo vo);
    public List<SaleQuoteDetailPo> getAllSaleDiscountDetailPoForms(Company company);

    public List<SaleProductPo> getSaleProductForms(Company company,int productId);
      public List<SaleProductPo> getSaleProductForms(SaleQueryVo vo);
 public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Product product);
    public StorageProductPo findStorageProduct(Storage storage, Product product);
 public List<SalePo> getAllSaleDetailForms(Company company,SaleOrderPo order);
 public List<SalePo> getAllSaleDetailForms(Company company,int status);
  public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company,SalePo sale);
     public List<SaleOrderPo> getAllSaleOrderForms(Company company,int status);
      public List<SalePo> getSalePriceDaetails(Customer customer, Company company);
}
