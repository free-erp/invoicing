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
package org.free_erp.service.logic.purchase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.base.DaoUtilities;
import org.free_erp.service.dao.purchase.IPurchaseDao;
import org.free_erp.service.entity.accounting.DailyReportPo;
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
import org.free_erp.service.entity.storage.StorageChangeLog;
import org.free_erp.service.entity.storage.StorageProductPo;
import org.free_erp.service.exception.InitialLackedException;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IStorageService;
import org.free_erp.service.logic.ISystemManageService;
import org.free_erp.service.logic.ServiceUtilities;
import org.hibernate.Transaction;

/**
 *
 * @author TengJianfa 13003311398
 */
public class PurchaseServiceImpl implements IPurchaseService
{
    private IPurchaseDao dao;
    private ISystemManageService systemManageService;
    private IAccountingService accountingService;
    private DaoUtilities utilities;
   private  IStorageService storageService;

    public IAccountingService getAccountingService() {
        return accountingService;
    }

    public IStorageService getStorageService() {
        return storageService;
    }

    public void setStorageService(IStorageService storageService) {
        this.storageService = storageService;
    }

    public void setAccountingService(IAccountingService accountingService) {
        this.accountingService = accountingService;
    }

    public IPurchaseDao getDao() {
        return dao;
    }

    public void setDao(IPurchaseDao dao) {
        this.dao = dao;
    }

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

    public DaoUtilities getUtilities() {
        return utilities;
    }

    public void setUtilities(DaoUtilities utilities) {
        this.utilities = utilities;
    }

    public void savePurchaseIndentProductPo(PurchaseIndentProductPo po)
    {
        this.utilities.saveObject(po);
    }

    public void savePurchaseProductPo(PurchaseProductPo po)
    {
        this.utilities.saveObject(po);
    }

    public void savePurchaseIndentPoForm(PurchaseIndentPo po)
    {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PURCHASE_INDENT_NUM);
            po.setNumber(number);
        }
        ServiceUtilities.addDateInfo(po);
        this.utilities.saveObject(po);
    }

    public void savePurchaseChangePoForm(PurchaseChangePo po)
    {
        ServiceUtilities.addDateInfo(po);
        this.utilities.saveObject(po);
    }

    public void deletePurchaseIndentPoForm(PurchaseIndentPo po)
    {
        this.utilities.deleteObject(po);
    }

    public void deletePurchaseChangePoForm(PurchaseChangePo po)
    {
        this.utilities.deleteObject(po);
    }

    public void passPurchaseIndentPoForm(PurchaseIndentPo po) throws InitialLackedException
    {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Set<PurchaseIndentDetailPo> details = po.getDetails();
            for(PurchaseIndentDetailPo p : details)
            {
                //PurchaseIndentProductPo purchaseIndentProductPo = this.dao.findPurchaseIndentProduct(po.getStorage(), po.getSupplier(), p.getProductId());
                PurchaseIndentProductPo purchaseIndentProductPo = null;
                if(purchaseIndentProductPo == null)
                {
                    purchaseIndentProductPo = new PurchaseIndentProductPo();
                    purchaseIndentProductPo.setFormDate(po.getFormDate());
                    purchaseIndentProductPo.setProduct(p.getProduct());
                    purchaseIndentProductPo.setComments(p.getComments());
                    purchaseIndentProductPo.setCompany(po.getCompany());
                    purchaseIndentProductPo.setPrice(p.getPrice());
                    purchaseIndentProductPo.setShelf(p.getShelf());
                    purchaseIndentProductPo.setStorage(po.getStorage());
                    purchaseIndentProductPo.setAmount(p.getAmount());
                    purchaseIndentProductPo.setTotalMoney(p.getTotalMoney());
                    purchaseIndentProductPo.setSupplier(po.getSupplier());
                    purchaseIndentProductPo.setChargePerson(po.getChargePerson());
                }
                else
                {
                    purchaseIndentProductPo.setPrice((p.getTotalMoney() + purchaseIndentProductPo.getTotalMoney())/p.getAmount() + purchaseIndentProductPo.getAmount());
                    purchaseIndentProductPo.setAmount(p.getAmount() + purchaseIndentProductPo.getAmount());
                    purchaseIndentProductPo.setTotalMoney(p.getTotalMoney() + purchaseIndentProductPo.getTotalMoney());
                }
                
                this.utilities.saveObject(purchaseIndentProductPo);
            }
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
            transaction.rollback();
            throw ex;
        }
        po.setStatus(ServiceConstants.FORMAL_STATUS);
        this.utilities.saveObject(po);
    }

    public void passPurchaseChangePoForm(PurchaseChangePo po) throws InitialLackedException
    {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try
        {
            Set<PurchaseChangeDetailPo> details = po.getDetails();
            PurchaseChangeDetailPo changeDetailPo = new PurchaseChangeDetailPo();
            PurchaseOrderPo purchaseOrderPo = this.dao.getPurchaseOrderPo(po.getCompany(), po.getIndentPoID());
            if(purchaseOrderPo == null)
            {
                return;
            }
            //purchaseOrderPo.setNumber(po.getNumber());
            purchaseOrderPo.setSupplier(po.getSupplier());
            purchaseOrderPo.setFormDate(po.getFormDate());
            purchaseOrderPo.setChargePerson(po.getChargePerson());
            purchaseOrderPo.setDepartment(po.getDepartment());
            purchaseOrderPo.setComments(po.getComments());
            purchaseOrderPo.setCompany(po.getCompany());
            purchaseOrderPo.setStorage(po.getStorage());
            purchaseOrderPo.setPayFashion(po.getPayFashion());
            purchaseOrderPo.setBalanceFashion(po.getBalanceFashion());
            purchaseOrderPo.setTotalMoney(po.getTotalMoney());
            Set<PurchaseOrderDetailPo> orderDetails = new HashSet<PurchaseOrderDetailPo>();

            Iterator it = details.iterator();
            while(it.hasNext())
            {
                changeDetailPo = (PurchaseChangeDetailPo)it.next();
                PurchaseOrderDetailPo orderDetailPo = new PurchaseOrderDetailPo();

                orderDetailPo.setProduct(changeDetailPo.getProduct());
                orderDetailPo.setPrice(changeDetailPo.getPrice());
                orderDetailPo.setDiscount(changeDetailPo.getDiscount());
                orderDetailPo.setDisprice(changeDetailPo.getDisprice());
                orderDetailPo.setAmount(changeDetailPo.getAmount());
                orderDetailPo.setTotalMoney(changeDetailPo.getTotalMoney());
                orderDetailPo.setComments(changeDetailPo.getComments());
                orderDetails.add(orderDetailPo);
            }
            purchaseOrderPo.setDetails(orderDetails);
            this.utilities.saveObject(purchaseOrderPo);
        }
        catch(RuntimeException ex)
        {
            transaction.rollback();
            throw ex;
        }
        po.setStatus(ServiceConstants.FORMAL_STATUS);
        this.utilities.saveObject(po);
    }

    public List<PurchaseIndentPo> getAllPurchaseIndentPoForms(Company company)
    {
        return this.dao.getAllPurchaseIndentPoForms(company);
    }

    public List<PurchaseIndentPo> getPassPurchaseIndentPoForms(Company company)
    {
        return this.dao.getPassPurchaseIndentPoForms(company);
    }

    public List<PurchaseIndentPo> findPurchaseIndentPoForms(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseIndentPoForms(vo);
    }

    public List<PurchaseIndentDetailPo> findPurchaseIndentDetails(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseIndentDetails(vo);
    }

    public List<PurchaseChangeDetailPo> findPurchaseChangeDetails(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseChangeDetails(vo);
    }

    public void savePurchaseOrderPoForm(PurchaseOrderPo po)
    {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PURCHASE_ORDER_NUM);
            po.setNumber(number);
        }
        ServiceUtilities.addDateInfo(po);
        this.utilities.saveObject(po);
    }

    public void deletePurchaseOrderPoForm(PurchaseOrderPo po)
    {
        this.utilities.deleteObject(po);
    }

    private StorageProductPo findStorageProduct( List<StorageProductPo> pos, Product product)
    {
        for(StorageProductPo p:pos)
        {
            if (p.getProduct().equals(product))
            {
                return p;
            }
        }
        return null;
    }

    public void passPurchaseOrderPoForm(PurchaseOrderPo po) throws InitialLackedException
    {
        this.accountingService.addPurchasePayable(po);
        Transaction transaction = this.utilities.beginDaoTransaction();
        //System.out.println("start:"+System.currentTimeMillis());
        try
        {
            Set<PurchaseOrderDetailPo> details = po.getDetails();
            List<Product> products = new ArrayList();
            for(PurchaseOrderDetailPo p : details)
            {
                products.add(p.getProduct());
            }
            List<StorageProductPo> storageProducts = this.dao.findStorageProductPos(po.getStorage(), products);
            for(PurchaseOrderDetailPo p : details)
            {
                //PurchaseProductPo purchaseProduct = this.dao.findPurchaseProduct(po.getStorage(), po.getSupplier(), id);
                PurchaseProductPo purchaseProduct = null;
                StorageProductPo storageProduct = this.findStorageProduct(storageProducts, p.getProduct());
                StorageChangeLog storageChangeLog = new StorageChangeLog();
                storageChangeLog.setCompany(po.getCompany());
                storageChangeLog.setStorage(po.getStorage());
                storageChangeLog.setProduct(p.getProduct());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setChangeType("采购单");
                if (purchaseProduct == null)
                {
                    purchaseProduct = new PurchaseProductPo();
                    purchaseProduct.setFormDate(po.getFormDate());
                    purchaseProduct.setProduct(p.getProduct());
                    purchaseProduct.setComments(p.getComments());
                    purchaseProduct.setCompany(po.getCompany());
                    purchaseProduct.setPrice(p.getPrice());
                    purchaseProduct.setShelf(p.getShelf());
                    purchaseProduct.setStorage(po.getStorage());
                    purchaseProduct.setAmount(p.getAmount());
                    purchaseProduct.setTotalMoney(p.getTotalMoney());
                    purchaseProduct.setSupplier(po.getSupplier());
                    purchaseProduct.setChargePerson(po.getChargePerson());
                }
                else
                {
                    double oldPurAmount = purchaseProduct.getAmount();
                    double totalPurMoney = purchaseProduct.getTotalMoney() + p.getTotalMoney();
                    double newPurPrice = totalPurMoney / (oldPurAmount + p.getAmount());
                    purchaseProduct.setAmount(p.getAmount() + oldPurAmount);
                    purchaseProduct.setPrice(newPurPrice);
                    purchaseProduct.setTotalMoney(totalPurMoney);
                }
                if (storageProduct == null)
                {
                    storageProduct = new StorageProductPo();
                    storageProduct.setProduct(p.getProduct());
                    storageProduct.setCompany(po.getCompany());
                    storageProduct.setPrice(p.getPrice());
                    storageProduct.setShelf(p.getShelf());
                    storageProduct.setStorage(po.getStorage());
                    storageProduct.setAmount(0d);
                }
                storageChangeLog.setCurrentAmount(storageProduct.getAmount());
                storageChangeLog.setCurrentPrice(storageProduct.getPrice());
                //业务部分
                double oldAmount = storageProduct.getAmount();
                //价格的计算方法,当前用平均法
                double totalMoney = storageProduct.getTotailMoney() + p.getTotalMoney();
                double newPrice = totalMoney / (oldAmount + p.getAmount());
                
                storageProduct.setAmount(p.getAmount() + oldAmount);
                storageProduct.setPrice(newPrice);
                storageChangeLog.setChangedAmount(storageProduct.getAmount());
                storageChangeLog.setChangedPrice(storageProduct.getPrice());
                try
                {
                    this.utilities.saveObject(purchaseProduct);
                    this.utilities.saveObject(storageProduct);
                    this.utilities.saveObject(storageChangeLog);
                }
                catch(Exception ex)
                {
                    transaction.rollback();
                    throw new RuntimeException("无法保存!:" + ex.getMessage());
                }
            }
//            PurchaseIndentPo indentPo = po.getPurchaseIndentPo();
//            if(indentPo != null)
//            {
//                indentPo.setSystemStatus(1);
//                this.utilities.saveObject(indentPo);
//            }
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
            transaction.rollback();
            throw ex;
        }
        //System.out.println("end:"+System.currentTimeMillis());
        po.setStatus(ServiceConstants.FORMAL_STATUS);
       this.utilities.saveObject(this.setDailyReportPo(po));//alin
        this.utilities.saveObject(po);
    }

    public List<PurchaseOrderPo> getAllPurchaseOrderPoForms(Company company)
    {
        return this.dao.getAllPurchaseOrderPoForms(company);
    }

    public List<PurchaseOrderPo> getPassPurchaseOrderPoForms(Company company)
    {
        return this.dao.getPassPurchaseOrderPoForms(company);
    }

    public List<PurchaseChangePo> getAllPurchaseChangePoForms(Company company)
    {
        return this.dao.getAllPurchaseChangePoForms(company);
    }

    public List<PurchaseChangePo> findPurchaseChangePoForms(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseChangePoForms(vo);
    }

    public List<PurchaseOrderPo> findPurchaseOrderPoForms(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseOrderPoForms(vo);
    }

    public List<PurchaseOrderDetailPo> findPurchaseOrderDetails(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseOrderDetails(vo);
    }

    public void savePurchaseBackPoForm(PurchaseBackPo po)
    {
        if(po.getNumber() == null || po.getNumber().equals(""))
        {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PURCHASE_BACK_NUM);
            po.setNumber(number);
        }
        ServiceUtilities.addDateInfo(po);
        this.utilities.saveObject(po);
    }

    public void deletePurchaseBackPoForm(PurchaseBackPo po)
    {
        this.utilities.deleteObject(po);
    }

    private PurchaseProductPo findPurchaseProduct( List<PurchaseProductPo> pos, Product product)
    {
        for(PurchaseProductPo p:pos)
        {
            if (p.getProduct().equals(product))
            {
                return p;
            }
        }
        return null;
    }

    public void passPurchaseBackPoForm(PurchaseBackPo po) throws InitialLackedException
    {
        this.accountingService.addPurchaseBackReceivable(po);
        Transaction transaction = this.utilities.beginDaoTransaction();
        //System.out.println("time000:"+System.currentTimeMillis());
        try
        {
            Set<PurchaseBackDetailPo> details = po.getDetails();
            List<Product> products = new ArrayList();
            for(PurchaseBackDetailPo p : details)
            {
                products.add(p.getProduct());
            }
            List<StorageProductPo> storageProducts = this.dao.findStorageProductPos(po.getStorage(), products);
            List<PurchaseProductPo> purchaseProducts = this.dao.findPurchaseProductPos(po.getStorage(), po.getSupplier(), products);
            for(PurchaseBackDetailPo p : details)
            {
                PurchaseProductPo purchaseProduct = this.findPurchaseProduct(purchaseProducts, p.getProduct());
                StorageProductPo storageProduct = this.findStorageProduct(storageProducts, p.getProduct());
                StorageChangeLog storageChangeLog = new StorageChangeLog();
                storageChangeLog.setCompany(po.getCompany());
                storageChangeLog.setStorage(po.getStorage());
                storageChangeLog.setProduct(p.getProduct());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setChangeType("采购退货");
                if(purchaseProduct == null)
                {
                    transaction.rollback();
                    throw new InitialLackedException(p.getProduct().getName(), "此商品尚未从供应商“"+po.getSupplier().getName()+"”采购！");
                }
                if(storageProduct == null)
                {
                    transaction.rollback();
                    throw new InitialLackedException(p.getProduct().getName(), "此商品尚未采购进库，不能退货！");
                }
                storageChangeLog.setCurrentAmount(storageProduct.getAmount());
                storageChangeLog.setCurrentPrice(storageProduct.getPrice());
                //业务部分
                double oldAmount = storageProduct.getAmount();
                double backPurAmount = purchaseProduct.getBackAmount();
                double backTotalPurMoney = purchaseProduct.getBackTotalMoney();
//                if((backPurAmount + p.getAmount()) > purchaseProduct.getAmount())
//                {
//                    transaction.rollback();
//                    throw new InitialLackedException(p.getProduct().getName(), "此商品采购的数额不足！");
//                }
                if(oldAmount < p.getAmount())
                {
                    transaction.rollback();
                    throw new InitialLackedException(p.getProduct().getName(), "此商品库存的数额不足！");
                }
                purchaseProduct.setBackAmount(backPurAmount + p.getAmount());
                purchaseProduct.setBackTotalMoney(backTotalPurMoney + p.getTotalMoney());
                storageProduct.setAmount(oldAmount - p.getAmount());
                storageChangeLog.setChangedAmount(storageProduct.getAmount());
                storageChangeLog.setChangedPrice(storageProduct.getPrice());
                try
                {
                    this.utilities.saveObject(purchaseProduct);
                    this.utilities.saveObject(storageProduct);
                    this.utilities.saveObject(storageChangeLog);
                }
                catch(Exception ex)
                {
                    transaction.rollback();
                    throw new RuntimeException("无法保存!:" + ex.getMessage());
                }
            }
            PurchaseOrderPo orderPo = po.getPurchaseOrderPo();
            if(orderPo != null)
            {
                orderPo.setSystemStatus(1);
                this.utilities.saveObject(orderPo);
            }
            transaction.commit();
        }
        catch(RuntimeException ex)
        {
            transaction.rollback();
            throw ex;
        }
        //System.out.println("time111:"+System.currentTimeMillis());
        po.setStatus(ServiceConstants.FORMAL_STATUS);
      this.utilities.saveObject(this.setDailyReportPo(po));//
        this.utilities.saveObject(po);
    }

    public List<PurchaseBackPo> getAllPurchaseBackPoForms(Company company)
    {
        return this.dao.getAllPurchaseBackPoForms(company);
    }

    public List<PurchaseBackPo> findPurchaseBackPoForms(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseBackPoForms(vo);
    }

    public List<PurchaseBackDetailPo> findPurchaseBackDetails(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseBackDetails(vo);
    }

    public List<PurchaseProductPo> findPurchaseProductForms(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseProducts(vo);
    }

    public List<PurchaseIndentProductPo> findPurchaseIndentProducts(PurchaseQueryVo vo)
    {
        return this.dao.findPurchaseIndentProducts(vo);
    }

    public void passCommonPurchaseForm(Object po) throws InitialLackedException
    {
        if (po instanceof PurchaseIndentPo)
		{
			this.passPurchaseIndentPoForm((PurchaseIndentPo)po);
		}
		else if (po instanceof PurchaseOrderPo)
		{
			this.passPurchaseOrderPoForm((PurchaseOrderPo)po);
		}
        else if (po instanceof PurchaseBackPo)
		{
			this.passPurchaseBackPoForm((PurchaseBackPo)po);
		}
        else if (po instanceof PurchaseChangePo)
		{
			this.passPurchaseChangePoForm((PurchaseChangePo)po);
		}
    }

    public double getPurchaseStockAmount(Customer supplier,Storage storage,Product product)
    {
        return this.dao.getPurchaseStockAmount(supplier, storage, product);
    }

    public PurchaseIndentPo gePurchaseIndentPo(Company company,String number)
    {
        return this.dao.gePurchaseIndentPo(company, number);
    }

    public PurchaseOrderPo gePurchaseOrderPo(Company company,String number)
    {
        return this.dao.gePurchaseOrderPo(company, number);
    }
    ////----临时---////
    public List<PurchaseIndentProductPo> findPurchaseIndentProductStat(PurchaseQueryVo vo)
    {
        List<PurchaseIndentProductPo> pos = this.findPurchaseIndentProducts(vo);
        List<PurchaseIndentProductPo> productPos = new ArrayList<PurchaseIndentProductPo>();
        
        for(PurchaseIndentProductPo po : pos)
        {
            if(this.isIncludeIndentProduct(productPos, po.getProduct().getNumber()))
            {
                PurchaseIndentProductPo productPo = new PurchaseIndentProductPo();
                productPo = this.getExistIndentProduct(pos, po.getProduct().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPo.setPrice((po.getTotalMoney() + productPo.getTotalMoney())/(po.getAmount() + productPo.getAmount()));
                productPos.remove(this.getExistIndentProduct(pos, po.getProduct().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludeIndentProduct(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getProduct().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseIndentProductPo getExistIndentProduct(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getProduct().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseProductPo> findPurchaseProductStat(PurchaseQueryVo vo)
    {
        List<PurchaseProductPo> pos = this.findPurchaseProductForms(vo);
        List<PurchaseProductPo> productPos = new ArrayList<PurchaseProductPo>();

        for(PurchaseProductPo po : pos)
        {
            if(this.isIncludeProduct(productPos, po.getProduct().getNumber()))
            {
                PurchaseProductPo productPo = new PurchaseProductPo();
                productPo = this.getExistProduct(pos, po.getProduct().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPo.setBackAmount(po.getBackAmount() + productPo.getBackAmount());
                productPo.setBackTotalMoney(po.getBackTotalMoney() + productPo.getBackTotalMoney());
                productPo.setPrice((po.getTotalMoney() + productPo.getTotalMoney())/(po.getAmount() + productPo.getAmount()));
                productPos.remove(this.getExistProduct(pos, po.getProduct().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludeProduct(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getProduct().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseProductPo getExistProduct(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getProduct().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseIndentProductPo> findPurchaseIndentSupplierStat(PurchaseQueryVo vo)
    {
        List<PurchaseIndentProductPo> pos = this.findPurchaseIndentProducts(vo);
        List<PurchaseIndentProductPo> productPos = new ArrayList<PurchaseIndentProductPo>();

        for(PurchaseIndentProductPo po : pos)
        {
            if(this.isIncludeIndentSupplier(productPos, po.getSupplier().getNumber()))
            {
                PurchaseIndentProductPo productPo = new PurchaseIndentProductPo();
                productPo = this.getExistIndentSupplier(pos, po.getSupplier().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPos.remove(this.getExistIndentSupplier(pos, po.getSupplier().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludeIndentSupplier(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getSupplier().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseIndentProductPo getExistIndentSupplier(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getSupplier().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseIndentProductPo> findPurchaseIndentPersonStat(PurchaseQueryVo vo)
    {
        List<PurchaseIndentProductPo> pos = this.findPurchaseIndentProducts(vo);
        List<PurchaseIndentProductPo> productPos = new ArrayList<PurchaseIndentProductPo>();

        for(PurchaseIndentProductPo po : pos)
        {
            if(this.isIncludeIndentPerson(productPos, po.getChargePerson().getNumber()))
            {
                PurchaseIndentProductPo productPo = new PurchaseIndentProductPo();
                productPo = this.getExistIndentPerson(pos, po.getChargePerson().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPos.remove(this.getExistIndentPerson(pos, po.getChargePerson().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludeIndentPerson(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getChargePerson().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseIndentProductPo getExistIndentPerson(List<PurchaseIndentProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseIndentProductPo po : pos)
            {
                if(po.getChargePerson().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseProductPo> findPurchaseSupplierStat(PurchaseQueryVo vo)
    {
        List<PurchaseProductPo> pos = this.findPurchaseProductForms(vo);
        List<PurchaseProductPo> productPos = new ArrayList<PurchaseProductPo>();

        for(PurchaseProductPo po : pos)
        {
            if(this.isIncludeSupplier(productPos, po.getSupplier().getNumber()))
            {
                PurchaseProductPo productPo = new PurchaseProductPo();
                productPo = this.getExistSupplier(pos, po.getSupplier().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPos.remove(this.getExistSupplier(pos, po.getSupplier().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludeSupplier(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getSupplier().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseProductPo getExistSupplier(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getSupplier().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseProductPo> findPurchasePersonStat(PurchaseQueryVo vo)
    {
        List<PurchaseProductPo> pos = this.findPurchaseProductForms(vo);
        List<PurchaseProductPo> productPos = new ArrayList<PurchaseProductPo>();

        for(PurchaseProductPo po : pos)
        {
            if(this.isIncludePerson(productPos, po.getChargePerson().getNumber()))
            {
                PurchaseProductPo productPo = new PurchaseProductPo();
                productPo = this.getExistPerson(pos, po.getChargePerson().getNumber());
                productPo.setAmount(po.getAmount() + productPo.getAmount());
                productPo.setTotalMoney(po.getTotalMoney() + productPo.getTotalMoney());
                productPos.remove(this.getExistPerson(pos, po.getChargePerson().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                productPos.add(po);
            }
        }
        return productPos;
    }

    private boolean isIncludePerson(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getChargePerson().getNumber().equals(number))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private PurchaseProductPo getExistPerson(List<PurchaseProductPo> pos,String number)
    {
        int size = pos.size();
        if(size > 0)
        {
            for(PurchaseProductPo po : pos)
            {
                if(po.getChargePerson().getNumber().equals(number))
                {
                    return po;
                }
            }
        }
        return null;
    }

    public List<PurchaseProductPo> findProductPurchasePriceWave(PurchaseQueryVo vo)
    {
        List<PurchaseProductPo> pos = this.findPurchaseProductForms(vo);
        List<PurchaseProductPo> productPos = new ArrayList<PurchaseProductPo>();

        for(PurchaseProductPo po : pos)
        {
            if(this.isIncludeProduct(productPos, po.getProduct().getNumber()))
            {
                PurchaseProductPo productPo = new PurchaseProductPo();
                productPo = this.getExistProduct(pos, po.getProduct().getNumber());
                if(po.getPrice() > productPo.getMaxPrice())
                {
                    productPo.setMaxPrice(po.getPrice());
                }
                if(po.getPrice() < productPo.getMinPrice())
                {
                    productPo.setMinPrice(po.getPrice());
                }
                productPos.remove(this.getExistProduct(pos, po.getProduct().getNumber()));
                productPos.add(productPo);
            }
            else
            {
                po.setMaxPrice(po.getPrice());
                po.setMinPrice(po.getPrice());
                productPos.add(po);
            }
        }
        return productPos;
    }
  public DailyReportPo setDailyReportPo(PurchaseBackPo po){
      DailyReportPo p =new DailyReportPo();
      p.setCompany(po.getCompany());
      p.setCashMoney(accountingService.getCashTotalMoney(po.getCompany()).getCashMoney());

      p.setCost(this.storageService.getDocumentsTotalMoney(po));
      p.setCreateDate(po.getCreateDate());
      p.setCreatedBy(po.getCreatedBy());
      p.setModifyDate(po.getModifyDate());
      p.setCustomer(po.getSupplier());
      p.setFormDate(po.getFormDate());
      p.setFormNumber(po.getNumber());
    
      p.setOperationType("采购退货");
      p.setPurchaseMoney(po.getTotalMoney());
      p.setPreReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getPreReceivableMoney());
      p.setReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getReceivableMoney());
      p.setStockMoney(this.storageService.getStockTotalMoney(po.getCompany()));
//      p.setPurchaseMoney(purchaseMoney);
       return p;
  }
     public DailyReportPo setDailyReportPo(PurchaseOrderPo po){
      DailyReportPo p =new DailyReportPo();
      p.setCompany(po.getCompany());
      p.setCashMoney(accountingService.getCashTotalMoney(po.getCompany()).getCashMoney());
      p.setCreateDate(po.getCreateDate());
      p.setCreatedBy(po.getCreatedBy());
      p.setModifyDate(po.getModifyDate());
      p.setCustomer(po.getSupplier());
      p.setFormDate(po.getFormDate());
      p.setFormNumber(po.getNumber());
      p.setPurchaseMoney(po.getTotalMoney());
      p.setOperationType("采购");
      p.getPayableMoney();
      p.setPreReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getPreReceivableMoney());
      p.setReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getReceivableMoney());
      p.setStockMoney(this.storageService.getStockTotalMoney(po.getCompany()));
//      p.setPurchaseMoney(purchaseMoney);
       return p;
  }
}
