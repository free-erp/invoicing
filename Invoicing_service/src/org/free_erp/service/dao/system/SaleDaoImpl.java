/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.free_erp.service.dao.system;

import java.util.Date;
import java.util.List;
import java.util.Vector;
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
import org.free_erp.service.logic.ISystemManageService;
import org.free_erp.service.logic.sale.SaleQueryVo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Administrator
 */
public class SaleDaoImpl extends HibernateDaoSupport implements ISaleDao {

    private ISystemManageService systemManageService;

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }


    public List<SalePo> getAllSaleDetailForms(Company company) {
        return (List<SalePo>)this.getHibernateTemplate().find("from SalePo as sale where sale.company=?", new Object[]{company});
    }

  public List<SalePo> getAllSaleDetailForms(Company company,SaleOrderPo order) {
        return this.getHibernateTemplate().find("from SalePo as sale where sale.company=? and sale.order=?", new Object[]{company,order});
    }

  public List<SalePo> getAllSaleDetailForms(Company company,int status) {
        return this.getHibernateTemplate().find("from SalePo as sale where sale.company=? and sale.status=?", new Object[]{company,status});
    }

    public List<SalePo> findSaleDetailForms(SaleQueryVo vo) {
        return (List<SalePo>)this.findPurchaseForms(SalePo.class, vo);
    }

    public List<SaleDetailPo> getAllDraftSaleDetailForms(Company company) {
        return (List<SaleDetailPo>)this.getHibernateTemplate().find("from SaleDetailPo where company=?", new Object[]{company});
    }

    public List<SaleDetailPo> findSaleDetailDetails(SaleQueryVo vo) {

        return (List<SaleDetailPo>)this.findPurchaseDetailForms(SaleDetailPo.class, vo);
    }


    public List<SaleChangeMoneyPo> getAllSaleChangeMoneyForms(Company company) {
        return (List<SaleChangeMoneyPo>)this.getHibernateTemplate().find("from SaleChangeMoneyPo where company=?", new Object[]{company});
    }

    public List<SaleChangeMoneyPo> findSaleChangeMoneyForms(SaleQueryVo vo) {
        return (List<SaleChangeMoneyPo>)this.findPurchaseForms(SaleChangeMoneyPo.class, vo);
    }

    public List<SaleChangeMoneyDetailPo> getAllDraftSaleChangeMoneyForms(Company company) {
        return (List<SaleChangeMoneyDetailPo>)this.getHibernateTemplate().find("from SaleChangeMoneyDetailPo as po where po.company=?", new Object[]{company});
    }

    public List<SaleChangeMoneyDetailPo> findSaleChangeMoneyDetails(SaleQueryVo vo) {
        return (List<SaleChangeMoneyDetailPo> )this.findPurchaseDetailForms(SaleChangeMoneyDetailPo.class, vo);
    }

    public List<SalechaseOrderPo> getAllSaleChangeOrderForms(Company company) {
        return (List<SalechaseOrderPo>)this.getHibernateTemplate().find("from SalechaseOrderPo as po where po.company=?", new Object[]{company});
    }

    public List<SalechaseOrderPo> findSaleChangeOrderForms(SaleQueryVo vo) {
        return (List<SalechaseOrderPo>)this.findPurchaseForms(SalechaseOrderPo.class, vo);
    }

    public List<SalechaseOrderDetailPo> getAllDraftSaleChangeOrderyForms(Company company) {
        return (List<SalechaseOrderDetailPo>)this.getHibernateTemplate().find("from SalechaseOrderDetailPo where company=?", new Object[]{company});
    }

    public List<SalechaseOrderDetailPo> findSaleChangeOrderDetails(SaleQueryVo vo) {
        return (List<SalechaseOrderDetailPo>)this.findPurchaseDetailForms(SalechaseOrderDetailPo.class, vo);
    }

    public List<SaleOrderBackPo> findSaleOrderBackForms(SaleQueryVo vo) {
        return (List<SaleOrderBackPo>)this.findPurchaseForms(SalechaseOrderDetailPo.class, vo);
    }

    public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company) {
        return (List<SaleOrderBackPo>)this.getHibernateTemplate().find("from SaleOrderBackPo where company=?", new Object[]{company});
    }
   public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company,SalePo sale) {
        return this.getHibernateTemplate().find("from SaleOrderBackPo where company=? and sale=? and status=?", new Object[]{company,sale,1});
    }
    public List<SaleOrderBackDetailPo> findSaleOrderBackDetails(SaleQueryVo vo) {
        return (List<SaleOrderBackDetailPo>)this.findPurchaseDetailForms(SaleOrderBackDetailPo.class, vo);
    }

    public List<SaleOrderBackDetailPo> getAllDraftSaleOrderBackForms(Company company) {
        return (List<SaleOrderBackDetailPo>)this.getHibernateTemplate().find("from SaleOrderBackDetailPo where company=?", new Object[]{company});
    }

  
    public List<SaleOrderPo> findSaleOrderForms(SaleQueryVo vo) {
        return (List<SaleOrderPo>)this.findPurchaseForms(SaleOrderPo.class, vo);
    }

    public List<SaleOrderPo> getAllSaleOrderForms(Company company) {
        return (List<SaleOrderPo>)this.getHibernateTemplate().find("from SaleOrderPo where company=?", new Object[]{company});
    }
    
    public List<SaleOrderDetailPo> findSaleOrderDetails(SaleQueryVo vo) {
        return (List<SaleOrderDetailPo>)this.findPurchaseDetailForms(SaleOrderDetailPo.class, vo);
    }
  
    public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Company company) {

        return (List<SaleOrderDetailPo>)this.getHibernateTemplate().find("from SaleOrderDetailPo where company=?", new Object[]{company});
    }

    public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Product product) {
        return this.getHibernateTemplate().find("from SaleOrderDetailPo where product=?", new Object[]{product});
    }

  private List findPurchaseForms(Class clazz, SaleQueryVo vo)
	{
		String className = clazz.getName();
		String sql = "from " + className + " as form where";
		Vector objs = new Vector();
        if(vo.getStatus() != null)
        {
            if(!sql.endsWith("where"))
            {
                sql += " and ";
            }
            sql += " form.status =? ";
            objs.add(vo.getStatus());
        }
		if (vo.getStorage() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.storage=? ";
			objs.add(vo.getStorage());
		}
		if (vo.getCompany() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.company=? ";
			objs.add(vo.getCompany());
		}
		if (vo.getStartDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.formDate>=? ";
			objs.add(vo.getStartDate());
		}
		if (vo.getEndDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.formDate <=? ";
			objs.add(vo.getEndDate());
		}

        if (vo.getStartDatePo() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate>=? ";
			objs.add(vo.getStartDatePo());
		}
		if (vo.getEndDatePo() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.formDate <=? ";
			objs.add(vo.getEndDatePo());
		}

		if (vo.getProductNumber() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.number =? ";
			objs.add(vo.getProductNumber());
		}

		if (vo.getCatalogName() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.catalogName like ? ";
			objs.add("%" + vo.getCatalogName() + "%");
		}
		if (vo.getProductName() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.name like ? ";
			objs.add("%" + vo.getProductName() + "%");
		}
        if (vo.getSupplier() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.customer=? ";
			objs.add(vo.getSupplier());
		}
        if (vo.getEmployee() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.employee=? ";
			objs.add(vo.getEmployee());
		}
		//debug:
		return this.getHibernateTemplate().find(sql, objs.toArray());
	}

    private List findPurchaseDetailForms(Class clazz, SaleQueryVo vo)
	{
		String className = clazz.getName();
		String sql = "from " + className + " as form where";
		Vector objs = new Vector();
		if (vo.getStorage() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.storage=? ";
			objs.add(vo.getStorage());
		}
		if (vo.getCompany() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.company=? ";
			objs.add(vo.getCompany());
		}
		if (vo.getStartDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.formDate>=? ";
			objs.add(vo.getStartDate());
		}
		if (vo.getEndDate() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.formDate <=? ";
			objs.add(vo.getEndDate());
		}

		if (vo.getProductNumber() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.number =? ";
			objs.add(vo.getProductNumber());
		}

		if (vo.getCatalogName() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.catalogName like ? ";
			objs.add("%" + vo.getCatalogName() + "%");
		}
		if (vo.getProductName() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.name like ? ";
			objs.add("%" + vo.getProductName() + "%");
		}
        if (vo.getEmployee() != null)//user058ÐÞ¸Ä
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.employee=? ";
			objs.add(vo.getEmployee());
		}
          if (vo.getSupplier() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.mainObject.customer=? ";
			objs.add(vo.getSupplier());
		}
         if (vo.getProduct() != null)
		{
			if (!sql.endsWith("where"))
			{
				sql += " and ";
			}
			sql += " form.product=? ";
			objs.add(vo.getProduct());
		}
		//debug:
		return this.getHibernateTemplate().find(sql, objs.toArray());
	}

    public List<SaleQuotePo> findSaleQuoteForms(SaleQueryVo vo) {
        return this.findPurchaseForms(SaleQuotePo.class, vo);
    }

    public List<SaleQuotePo> getAllQuoteForms(Company company) {
        return this.getHibernateTemplate().find("from SaleQuotePo where company=?", new Object[]{company});
    }

    public List<SaleQuoteDetailPo> findQuoteDetails(SaleQueryVo vo) {
        return this.findPurchaseDetailForms(SaleQuoteDetailPo.class, vo);
    }

    public List<SaleQuoteDetailPo> getAllDraftQuotederForms(Company company) {
        return this.getHibernateTemplate().find("from SaleQuoteDetailPo where company=?", new Object[]{company});
    }

    

    public List<SaleQuotePo> findSaleDiscountForms(SaleQueryVo vo) {
        return this.findPurchaseForms(SaleQuotePo.class, vo);
    }

    public List<SaleQuotePo> getAllSaleDiscountForms(Company company) {
        return this.getHibernateTemplate().find("from SaleDiscountPo where company=?", new Object[]{company});
    }

    public List<SaleQuoteDetailPo> findSaleDiscountDetails(SaleQueryVo vo) {
        return this.findPurchaseForms(SaleQuoteDetailPo.class, vo);
    }

    public List<SaleQuoteDetailPo> getAllSaleDiscountDetailPoForms(Company company) {
        return this.getHibernateTemplate().find("from SaleDiscountDetailPo where company=?", new Object[]{company});
    }

 
    public String numberDate() {
        String strDate = "";
        Date date = new Date();
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        strDate = String.valueOf(year) + (month > 9 ? String.valueOf(month) : "0" + String.valueOf(month)) + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day));
        return strDate;
    }

    public List<SaleOrderPo> getSaleOrderForms(Company company, int y) {
        return this.getHibernateTemplate().find("from SaleOrderPo where company=? and status=?", new Object[]{company, y});
    }

    public List<SaleProductPo> getSaleProductForms(Company company, int productId) {
        return this.getHibernateTemplate().find("from SaleProductPo where company=? and productId=?", new Object[]{company, productId});
    }

    public List<SaleProductPo> getSaleProductForms(SaleQueryVo vo) {
        return this.findPurchaseForms(SaleProductPo.class, vo);
    }


    public List<SaleChangeMoneyDetailPo> getSaleChangeMoneyDetailPo(SaleQueryVo vo){
     return this.findPurchaseDetailForms(SaleChangeMoneyDetailPo.class, vo);
    }
   public StorageProductPo findStorageProduct(Storage storage, Product productId)
	{
		List<StorageProductPo> sps = this.getHibernateTemplate().find("from StorageProductPo as s where s.storage =? and s.product=?", new Object[]{storage, productId});
		if (sps != null && sps.size() > 0)
		{
			return sps.get(0);
		}
		return null;
	}

    public List<SaleOrderPo> getAllSaleOrderForms(Company company, int status) {
      return this.getHibernateTemplate().find("from SaleOrderPo as saleOrderPo where saleOrderPo.company=? and saleOrderPo.status=?", new Object[]{company,status});
    }

    public List<SalePo> getSalePriceDaetails(Customer customer, Company company) {
             return this.getHibernateTemplate().find("from SalePo as sale where sale.company=? and sale.customer=? ORDER BY sale.id", new Object[]{company,customer});
    }
}
