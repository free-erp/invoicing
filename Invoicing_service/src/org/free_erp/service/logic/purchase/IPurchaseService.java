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
import org.free_erp.service.exception.InitialLackedException;

/**
 *
 * @author TengJianfa 13003311398
 */
public interface IPurchaseService
{
    public void savePurchaseIndentProductPo(PurchaseIndentProductPo po);

    public void savePurchaseProductPo(PurchaseProductPo po);

    public void savePurchaseIndentPoForm(PurchaseIndentPo po);

    public void savePurchaseChangePoForm(PurchaseChangePo po);

    public void deletePurchaseIndentPoForm(PurchaseIndentPo po);

    public void deletePurchaseChangePoForm(PurchaseChangePo po);

    public void passPurchaseIndentPoForm(PurchaseIndentPo po) throws InitialLackedException;

    public void passPurchaseChangePoForm(PurchaseChangePo po) throws InitialLackedException;

    public List<PurchaseIndentPo> getAllPurchaseIndentPoForms(Company company);

    public List<PurchaseIndentPo> getPassPurchaseIndentPoForms(Company company);

    public List<PurchaseChangePo> getAllPurchaseChangePoForms(Company company);

    public List<PurchaseChangePo> findPurchaseChangePoForms(PurchaseQueryVo vo);

    public List<PurchaseIndentPo> findPurchaseIndentPoForms(PurchaseQueryVo vo);

    public List<PurchaseIndentDetailPo> findPurchaseIndentDetails(PurchaseQueryVo vo);

    public List<PurchaseChangeDetailPo> findPurchaseChangeDetails(PurchaseQueryVo vo);

    public void savePurchaseOrderPoForm(PurchaseOrderPo po);

    public void deletePurchaseOrderPoForm(PurchaseOrderPo po);

    public void passPurchaseOrderPoForm(PurchaseOrderPo po) throws InitialLackedException;

    public List<PurchaseOrderPo> getAllPurchaseOrderPoForms(Company company);

    public List<PurchaseOrderPo> getPassPurchaseOrderPoForms(Company company);

    public List<PurchaseOrderPo> findPurchaseOrderPoForms(PurchaseQueryVo vo);

    public List<PurchaseOrderDetailPo> findPurchaseOrderDetails(PurchaseQueryVo vo);

    public void savePurchaseBackPoForm(PurchaseBackPo po);

    public void deletePurchaseBackPoForm(PurchaseBackPo po);

    public void passPurchaseBackPoForm(PurchaseBackPo po) throws InitialLackedException;

    public List<PurchaseBackPo> getAllPurchaseBackPoForms(Company company);

    public List<PurchaseBackPo> findPurchaseBackPoForms(PurchaseQueryVo vo);

    public List<PurchaseBackDetailPo> findPurchaseBackDetails(PurchaseQueryVo vo);

    public void passCommonPurchaseForm(Object po) throws InitialLackedException;

    public double getPurchaseStockAmount(Customer supplier,Storage storage,Product product);

    public List<PurchaseProductPo> findPurchaseProductForms(PurchaseQueryVo vo);

    public List<PurchaseIndentProductPo> findPurchaseIndentProducts(PurchaseQueryVo vo);

    public PurchaseIndentPo gePurchaseIndentPo(Company company,String number);
    
    public PurchaseOrderPo gePurchaseOrderPo(Company company,String number);
    ////----¡Ÿ ±---////
    public List<PurchaseIndentProductPo> findPurchaseIndentProductStat(PurchaseQueryVo vo);

    public List<PurchaseProductPo> findPurchaseProductStat(PurchaseQueryVo vo);

    public List<PurchaseIndentProductPo> findPurchaseIndentSupplierStat(PurchaseQueryVo vo);

    public List<PurchaseIndentProductPo> findPurchaseIndentPersonStat(PurchaseQueryVo vo);

    public List<PurchaseProductPo> findPurchaseSupplierStat(PurchaseQueryVo vo);

    public List<PurchaseProductPo> findPurchasePersonStat(PurchaseQueryVo vo);

    public List<PurchaseProductPo> findProductPurchasePriceWave(PurchaseQueryVo vo);
}
