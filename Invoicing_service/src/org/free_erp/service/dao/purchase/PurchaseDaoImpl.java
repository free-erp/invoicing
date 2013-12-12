package org.free_erp.service.dao.purchase;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.purchase.PurchaseBackDetailPo;
import org.free_erp.service.entity.purchase.PurchaseBackPo;
import org.free_erp.service.entity.purchase.PurchaseChangeDetailPo;
import org.free_erp.service.entity.purchase.PurchaseChangePo;
import org.free_erp.service.entity.purchase.PurchaseIndentDetailPo;
import org.free_erp.service.entity.purchase.PurchaseIndentPo;
import org.free_erp.service.entity.purchase.PurchaseIndentProductPo;
import org.free_erp.service.entity.purchase.PurchaseOrderDetailPo;
import org.free_erp.service.entity.purchase.PurchaseOrderPo;
import org.free_erp.service.entity.purchase.PurchaseProductPo;
import org.free_erp.service.entity.purchase.PurchaseQueryVo;
import org.free_erp.service.entity.storage.StorageProductPo;
import org.free_erp.service.logic.ISystemManageService;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PurchaseDaoImpl extends HibernateDaoSupport implements IPurchaseDao {

    private ISystemManageService systemManageService;

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

    public PurchaseOrderPo getPurchaseOrderPo(Company company, int indentID) {
        List<PurchaseOrderPo> purchaseOrderPos = this.getHibernateTemplate().find("from PurchaseOrderPo as s where s.company =? and s.indentPoID =?", new Object[]{company, indentID});
        if (purchaseOrderPos != null && purchaseOrderPos.size() > 0) {
            return purchaseOrderPos.get(0);
        }
        return null;
    }

    public List<PurchaseIndentPo> getAllPurchaseIndentPoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseIndentPo as s where s.company =? ", new Object[]{company});
    }

    public List<PurchaseIndentPo> getPassPurchaseIndentPoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseIndentPo as s where s.company =? and s.status =1 and s.systemStatus =0 ", new Object[]{company});
    }

    public List<PurchaseChangePo> getAllPurchaseChangePoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseChangePo as s where s.company =? ", new Object[]{company});
    }

    public List<PurchaseChangePo> findPurchaseChangePoForms(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseChangePo.class, vo);
    }

    public List<PurchaseIndentPo> findPurchaseIndentPoForms(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseIndentPo.class, vo);
    }

    public List<PurchaseIndentDetailPo> findPurchaseIndentDetails(PurchaseQueryVo vo) {
        return this.findPurchaseDetailForms(PurchaseIndentDetailPo.class, vo);
    }

    public List<PurchaseChangeDetailPo> findPurchaseChangeDetails(PurchaseQueryVo vo) {
        return this.findPurchaseDetailForms(PurchaseChangeDetailPo.class, vo);
    }

    public List<PurchaseOrderPo> getAllPurchaseOrderPoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseOrderPo as s where s.company =? ", new Object[]{company});
    }

    public List<PurchaseOrderPo> getPassPurchaseOrderPoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseOrderPo as s where s.company =? and s.status =1 and s.systemStatus =0 ", new Object[]{company});
    }

    public List<PurchaseOrderPo> findPurchaseOrderPoForms(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseOrderPo.class, vo);
    }

    public List<PurchaseOrderDetailPo> findPurchaseOrderDetails(PurchaseQueryVo vo) {
        return this.findPurchaseDetailForms(PurchaseOrderDetailPo.class, vo);
    }

    public List<PurchaseBackPo> getAllPurchaseBackPoForms(Company company) {
        return this.getHibernateTemplate().find("from PurchaseBackPo as s where s.company =?", new Object[]{company});
    }

    public List<PurchaseBackPo> findPurchaseBackPoForms(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseBackPo.class, vo);
    }

    public List<PurchaseBackDetailPo> findPurchaseBackDetails(PurchaseQueryVo vo) {
        return this.findPurchaseDetailForms(PurchaseBackDetailPo.class, vo);
    }

    public List<StorageProductPo> findStorageProductPos(Storage storage, List<Product> products) {
        String sqlString = "from StorageProductPo as s where s.storage=? and (";
        Object[] args = new Object[products.size() + 1];
        int i = 0;
        args[0] = storage;
        for (Product product : products) {
            if (i > 0) {
                sqlString += " or ";
            }
            sqlString += " s.product=?";
            i++;
            args[i] = product;
        }
        sqlString += ")";
        List<StorageProductPo> sps = this.getHibernateTemplate().find(sqlString, args);
        return sps;
    }

    public PurchaseIndentProductPo findPurchaseIndentProduct(Storage storage, Customer supplier, int productId, Date date) {
        List<PurchaseIndentProductPo> sps = this.getHibernateTemplate().find("from PurchaseIndentProductPo as p where p.storage =? and p.supplier =? and p.productId=? and p.formDate=? ", new Object[]{storage, supplier, productId, date});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public PurchaseIndentProductPo findPurchaseIndentProduct(Storage storage, Customer supplier, int productId) {
        List<PurchaseIndentProductPo> sps = this.getHibernateTemplate().find("from PurchaseIndentProductPo as p where p.storage =? and p.supplier =? and p.productId=?", new Object[]{storage, supplier, productId});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public PurchaseIndentProductPo findPurchaseIndentProduct(Company company, int productId) {
        List<PurchaseIndentProductPo> sps = this.getHibernateTemplate().find("from PurchaseIndentProductPo as p where p.company =? and p.productId=?", new Object[]{company, productId});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public PurchaseProductPo findPurchaseProduct(Storage storage, Customer supplier, int productId, Date date) {
        List<PurchaseProductPo> sps = this.getHibernateTemplate().find("from PurchaseProductPo as p where p.storage =? and p.supplier =? and p.productId=? and p.formDate=? ", new Object[]{storage, supplier, productId, date});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public PurchaseProductPo findPurchaseProduct(Storage storage, Customer supplier, int productId) {
        List<PurchaseProductPo> sps = this.getHibernateTemplate().find("from PurchaseProductPo as p where p.storage =? and p.supplier =? and p.productId=? ", new Object[]{storage, supplier, productId});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public PurchaseProductPo findPurchaseProduct(Company company, int productId) {
        List<PurchaseProductPo> sps = this.getHibernateTemplate().find("from PurchaseProductPo as p where p.company =? and p.productId=? ", new Object[]{company, productId});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

//    public PurchaseProductPo findPurchaseBackProduct(Storage storage,Customer supplier, Product product)
//	{
//		List<PurchaseProductPo> sps = this.getHibernateTemplate().find("from PurchaseProductPo as p where p.storage =? and p.supplier =? and p.product=? ", new Object[]{storage, supplier, product});
//		if (sps != null && sps.size() > 0)
//		{
//			return sps.get(0);
//		}
//		return null;
//	}
    public List<PurchaseProductPo> findPurchaseProductPos(Storage storage, Customer supplier, List<Product> products) {
        String sqlString = "from PurchaseProductPo as p where p.storage =? and p.supplier =? and (";
        Object[] args = new Object[products.size() + 2];
        int i = 1;
        args[0] = storage;
        args[1] = supplier;
        for (Product product : products) {
            if (i > 1) {
                sqlString += " or ";
            }
            sqlString += " p.product=?";
            i++;
            args[i] = product;
        }
        sqlString += ")";
        List<PurchaseProductPo> sps = this.getHibernateTemplate().find(sqlString, args);
        return sps;
    }

    public PurchaseChangePo findPurchaseChangePo(Company company, int indentID) {
        List<PurchaseChangePo> sps = this.getHibernateTemplate().find("from PurchaseChangePo as p where p.company =? and p.indentPoID =? and p.status=0 ", new Object[]{company, indentID});
        if (sps != null && sps.size() > 0) {
            return sps.get(0);
        }
        return null;
    }

    public List<PurchaseProductPo> findPurchaseProducts(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseProductPo.class, vo);
    }

    public List<PurchaseIndentProductPo> findPurchaseIndentProducts(PurchaseQueryVo vo) {
        return this.findPurchaseForms(PurchaseIndentProductPo.class, vo);
    }

    private List findPurchaseForms(Class clazz, PurchaseQueryVo vo) {
        String className = clazz.getName();
        String sql = "from " + className + " as form where";
        Vector objs = new Vector();
        if (vo.getStatus() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.status =? ";
            objs.add(vo.getStatus());
        }
        if (vo.getStorage() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.storage=? ";
            objs.add(vo.getStorage());
        }
        if (vo.getCompany() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.company=? ";
            objs.add(vo.getCompany());
        }
        if (vo.getStartDate() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.formDate>=? ";
            objs.add(vo.getStartDate());
        }
        if (vo.getEndDate() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.formDate <=? ";
            objs.add(vo.getEndDate());
        }

        if (vo.getStartDatePo() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.formDate>=? ";
            objs.add(vo.getStartDatePo());
        }
        if (vo.getEndDatePo() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.formDate <=? ";
            objs.add(vo.getEndDatePo());
        }

        if (vo.getProduct() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.product =? ";
            objs.add(vo.getProduct());
        }

        if (vo.getNumber() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.number like ? ";
            objs.add("%" + vo.getNumber() + "%");
        }
        if (vo.getProductName() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.name like ? ";
            objs.add("%" + vo.getProductName() + "%");
        }
        if (vo.getSupplier() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.supplier=? ";
            objs.add(vo.getSupplier());
        }
        if (vo.getChargePerson() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.chargePerson=? ";
            objs.add(vo.getChargePerson());
        }
        //debug:
        return this.getHibernateTemplate().find(sql, objs.toArray());
    }

    private List findPurchaseDetailForms(Class clazz, PurchaseQueryVo vo) {
        String className = clazz.getName();
        String sql = "from " + className + " as form where";
        Vector objs = new Vector();
        if (vo.getStorage() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.storage=? ";
            objs.add(vo.getStorage());
        }
        if (vo.getCompany() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.company=? ";
            objs.add(vo.getCompany());
        }
        if (vo.getStatus() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.status =? ";
            objs.add(vo.getStatus());
        }
        if (vo.getStartDate() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.formDate>=? ";
            objs.add(vo.getStartDate());
        }
        if (vo.getEndDate() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.formDate <=? ";
            objs.add(vo.getEndDate());
        }

        if (vo.getProduct() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.product =? ";
            objs.add(vo.getProduct());
        }

        if (vo.getNumber() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.number like ? ";
            objs.add("%" + vo.getNumber() + "%");
        }
        if (vo.getSupplier() != null)//user058ÐÞ¸Ä
        {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.supplier=? ";
            objs.add(vo.getSupplier());
        }
        if (vo.getChargePerson() != null) {
            if (!sql.endsWith("where")) {
                sql += " and ";
            }
            sql += " form.mainObject.chargePerson=? ";
            objs.add(vo.getChargePerson());
        }
        //debug:
        return this.getHibernateTemplate().find(sql, objs.toArray());
    }

    public double getPurchaseStockAmount(Customer supplier, Storage storage, Product product) {
        List<PurchaseProductPo> sps = this.getHibernateTemplate().find("from PurchaseProductPo as p where p.supplier =? and p.storage =? and p.product =?", new Object[]{supplier, storage, product});
        int count = 0;
        int backCount = 0;
        for (PurchaseProductPo po : sps) {
            count += po.getAmount();
            backCount += po.getBackAmount();
        }
        return count - backCount;
    }

    public PurchaseIndentPo gePurchaseIndentPo(Company company, String number) {
        List<PurchaseIndentPo> pos = this.getHibernateTemplate().find("from PurchaseIndentPo as s where s.company =? and s.number =? ", new Object[]{company, number});
        if (pos != null && pos.size() > 0) {
            return pos.get(0);
        }
        return null;
    }

    public PurchaseOrderPo gePurchaseOrderPo(Company company, String number) {
        List<PurchaseOrderPo> pos = this.getHibernateTemplate().find("from PurchaseOrderPo as s where s.company =? and s.number =? ", new Object[]{company, number});
        if (pos != null && pos.size() > 0) {
            return pos.get(0);
        }
        return null;
    }
}
