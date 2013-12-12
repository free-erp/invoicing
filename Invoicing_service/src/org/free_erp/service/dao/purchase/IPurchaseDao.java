package org.free_erp.service.dao.purchase;

import java.util.Date;
import java.util.List;
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
/**
 *
 * @author TengJianfa 13003311398
 */
public interface IPurchaseDao
{
    public List<PurchaseIndentPo> getAllPurchaseIndentPoForms(Company company);

    public List<PurchaseIndentPo> getPassPurchaseIndentPoForms(Company company);

    public List<PurchaseChangePo> getAllPurchaseChangePoForms(Company company);

    public List<PurchaseChangePo> findPurchaseChangePoForms(PurchaseQueryVo vo);

    public List<PurchaseIndentPo> findPurchaseIndentPoForms(PurchaseQueryVo vo);

    public List<PurchaseIndentDetailPo> findPurchaseIndentDetails(PurchaseQueryVo vo);

    public List<PurchaseChangeDetailPo> findPurchaseChangeDetails(PurchaseQueryVo vo);
    
    public List<PurchaseOrderPo> getAllPurchaseOrderPoForms(Company company);

    public List<PurchaseOrderPo> getPassPurchaseOrderPoForms(Company company);

    public List<PurchaseOrderPo> findPurchaseOrderPoForms(PurchaseQueryVo vo);

    public List<PurchaseOrderDetailPo> findPurchaseOrderDetails(PurchaseQueryVo vo);
    
    public List<PurchaseBackPo> getAllPurchaseBackPoForms(Company company);

    public List<PurchaseBackPo> findPurchaseBackPoForms(PurchaseQueryVo vo);

    public List<PurchaseBackDetailPo> findPurchaseBackDetails(PurchaseQueryVo vo);

    public double getPurchaseStockAmount(Customer supplier,Storage storage, Product product);

    public List<PurchaseProductPo> findPurchaseProducts(PurchaseQueryVo vo);

    public List<PurchaseIndentProductPo> findPurchaseIndentProducts(PurchaseQueryVo vo);

    public PurchaseProductPo findPurchaseProduct(Storage storage,Customer supplier, int productId,Date date);

    public PurchaseProductPo findPurchaseProduct(Storage storage,Customer supplier, int productId);

    public PurchaseProductPo findPurchaseProduct(Company company, int productId);

    public PurchaseIndentProductPo findPurchaseIndentProduct(Storage storage,Customer supplier, int productId,Date date);

    public PurchaseIndentProductPo findPurchaseIndentProduct(Storage storage,Customer supplier, int productId);

    public PurchaseIndentProductPo findPurchaseIndentProduct(Company company, int productId);

    public List<StorageProductPo> findStorageProductPos(Storage storage, List<Product> products);

    public List<PurchaseProductPo> findPurchaseProductPos(Storage storage,Customer supplier, List<Product> products);

    public PurchaseOrderPo getPurchaseOrderPo(Company company, int indentID);

    public PurchaseIndentPo gePurchaseIndentPo(Company company,String number);

    public PurchaseOrderPo gePurchaseOrderPo(Company company,String number);
}
