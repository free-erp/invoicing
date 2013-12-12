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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.transaction.SystemException;
import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.base.DaoUtilities;
import org.free_erp.service.dao.system.ISaleDao;
import org.free_erp.service.entity.accounting.DailyReportPo;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
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
import org.free_erp.service.entity.storage.StorageChangeLog;
import org.free_erp.service.entity.storage.StorageProductPo;
import org.free_erp.service.exception.InitialLackedException;
import org.free_erp.service.logic.purchase.IPurchaseService;
import org.free_erp.service.logic.sale.SaleQueryVo;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class SaleServiceImpl implements ISaleService {

    private ISaleDao dao;
    private ISaleService saleService;
    private ISystemManageService systemManageService;
    private ICompanyService companyService;
    private IAccountingService accountingService;
    private IStorageService storageService;
    private IProductService productService;
    private DaoUtilities utilities;
    private IPurchaseService purchaseService;

    public IPurchaseService getPurchaseService() {
        return purchaseService;
    }

    public void setPurchaseService(IPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public DaoUtilities getUtilities() {
        return utilities;
    }

    public void setUtilities(DaoUtilities utilities) {
        this.utilities = utilities;
    }

    public IStorageService getStorageService() {
        return storageService;
    }

    public void setStorageService(IStorageService storageService) {
        this.storageService = storageService;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public IAccountingService getAccountingService() {
        return accountingService;
    }

    public void setAccountingService(IAccountingService accountingService) {
        this.accountingService = accountingService;
    }

    public ICompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(ICompanyService companyService) {
        this.companyService = companyService;
    }

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }

    public ISaleDao getDao() {
        return dao;
    }

    public void setSaleService(ISaleService saleService) {
        this.saleService = saleService;
    }

    public void setDao(ISaleDao dao) {
        this.dao = dao;
    }

    public ISaleService getSaleService() {
        return saleService;
    }

    public List<SalePo> getAllSaleDetailForms(Company company) {
        return this.dao.getAllSaleDetailForms(company);
    }

    public List<SalePo> findSaleDetailForms(SaleQueryVo vo) {
        return this.dao.findSaleDetailForms(vo);
    }

    public List<SaleOrderDetailPo> findSaleBalance(SaleQueryVo vo) {
        List<SaleOrderDetailPo> list = new ArrayList();

        List<SaleDetailPo> sale = this.findSaleDetailDetails(vo);
        for (SaleDetailPo t : sale) {
            List<SaleOrderDetailPo> sales = dao.getAllDraftSaleOrderForms(t.getProduct());
            for (SaleOrderDetailPo sa : sales) {
                if (sa.getAmount() > t.getAmount()) {
                    sa.setAmount(sa.getAmount() - t.getAmount());
                    list.add(sa);
                }
            }
        }
        return list;
    }

    public void saveSaleDetailForm(SalePo po) {
        if (po.getNumber() == null || po.getNumber().equals("")) {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SALE_ORDER_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void saveSaleProduct(Object po) {
        if (po instanceof SalePo) {
            SalePo p = (SalePo) po;
            Set<SaleDetailPo> saleDetailPo = p.getDetails();
            SaleDetailPo detailPo = new SaleDetailPo();
            Iterator it = saleDetailPo.iterator();
            while (it.hasNext()) {
                detailPo = (SaleDetailPo) it.next();
                List<SaleProductPo> sPo = dao.getSaleProductForms(p.getCompany(), detailPo.getProduct().getId());
                if (sPo.size() <= 0) {
                } else {
                    SaleProductPo saleProductPo = sPo.get(0);
                    Double account = saleProductPo.getClinchAccount();
                    account += detailPo.getAmount();
                    saleProductPo.setClinchAccount(account);
                    saleProductPo.setAmount(saleProductPo.getAmount() - saleProductPo.getAmount());
                    Double totalMoney = saleProductPo.getClinchTotalMoney();
                    totalMoney += detailPo.getTotalMoney();
                    saleProductPo.setClinchTotalMoney(totalMoney);
                    saleProductPo.setNotTotalMoney(saleProductPo.getTotalMoney() - saleProductPo.getClinchTotalMoney());

                    this.utilities.saveObject(saleProductPo);
                }
            }
        }
        if (po instanceof SaleOrderPo) {
            SaleOrderPo p = (SaleOrderPo) po;
            Set<SaleOrderDetailPo> saleDetailPo = p.getDetails();
            SaleOrderDetailPo detailPo = new SaleOrderDetailPo();
            Iterator it = saleDetailPo.iterator();
            while (it.hasNext()) {
                detailPo = (SaleOrderDetailPo) it.next();
                List<SaleProductPo> sPo = dao.getSaleProductForms(p.getCompany(), detailPo.getProduct().getId());
                if (sPo.size() <= 0) {
                    SaleProductPo saleProductPo = new SaleProductPo();
                    saleProductPo.setAmount(detailPo.getAmount());
                    saleProductPo.setCatalogName(detailPo.getProduct().getCatalog().getName());
                    saleProductPo.setCompany(p.getCompany());
                    saleProductPo.setNotAccount(detailPo.getAmount() - saleProductPo.getClinchAccount());
                    saleProductPo.setNotTotalMoney(detailPo.getTotalMoney() - saleProductPo.getClinchTotalMoney());
                    saleProductPo.setName(detailPo.getProduct().getName());
                    saleProductPo.setNumber(detailPo.getProduct().getNumber());
                    saleProductPo.setSpec(detailPo.getProduct().getSpec());
                    saleProductPo.setProductId(detailPo.getProduct().getId());
                    saleProductPo.setTotalMoney(detailPo.getTotalMoney());
                    saleProductPo.setUnit(detailPo.getProduct().getBigUnit());

                    this.utilities.saveObject(saleProductPo);
                } else {
                    SaleProductPo saleProductPo = sPo.get(0);
                    Double account = saleProductPo.getAmount();
                    account += detailPo.getAmount();
                    saleProductPo.setAmount(account);
                    saleProductPo.setNotAccount(account - saleProductPo.getClinchAccount());
                    Double totalMoney = saleProductPo.getTotalMoney();
                    totalMoney += detailPo.getTotalMoney();
                    saleProductPo.setTotalMoney(totalMoney);
                    saleProductPo.setNotTotalMoney(totalMoney - saleProductPo.getClinchTotalMoney());

                    this.utilities.saveObject(saleProductPo);
                }
            }
        }
    }

    public void deleteSaleDetailForm(SalePo po) {

        this.utilities.deleteObject(po);
    }

    public List<SaleDetailPo> getAllDraftSaleDetailForms(Company company) {
        return this.dao.getAllDraftSaleDetailForms(company);
    }

    public List<SaleDetailPo> findSaleDetailDetails(SaleQueryVo vo) {
        return this.dao.findSaleDetailDetails(vo);
    }

    public List<SaleChangeMoneyPo> getAllSaleChangeMoneyForms(Company company) {
        return this.dao.getAllSaleChangeMoneyForms(company);
    }

    public List<SaleChangeMoneyPo> findSaleChangeMoneyForms(SaleQueryVo vo) {
        return this.dao.findSaleChangeMoneyForms(vo);
    }

    public List<SaleChangeMoneyDetailPo> getAllDraftSaleChangeMoneyForms(Company company) {
        return this.dao.getAllDraftSaleChangeMoneyForms(company);
    }

    public List<SaleChangeMoneyDetailPo> findSaleChangeMoneyDetails(SaleQueryVo vo) {
        return this.dao.findSaleChangeMoneyDetails(vo);
    }

    public void saveSaleChangeMoneyForm(SaleChangeMoneyPo po) {
        if (po.getNumber() == null || po.getNumber().equals("")) {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.CHANGE_PRICE_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void deleteSaleChangeMoneyForm(SaleChangeMoneyPo po) {
        this.utilities.deleteObject(po);
    }

    public void saveSaleChangeOrderForm(SalechaseOrderPo po) {
        this.utilities.saveObject(po);
    }

    public void deleteChangeOrderForm(SalechaseOrderPo po) {
        this.utilities.saveObject(po);
    }

    public List<SalechaseOrderPo> getAllSaleChangeOrderForms(Company company) {
        return this.dao.getAllSaleChangeOrderForms(company);
    }

    public List<SalechaseOrderPo> findSaleChangeOrderForms(SaleQueryVo vo) {
        return this.dao.findSaleChangeOrderForms(vo);
    }

    public List<SalechaseOrderDetailPo> getAllDraftSaleChangeOrderyForms(Company company) {
        return this.dao.getAllDraftSaleChangeOrderyForms(company);
    }

    public List<SalechaseOrderDetailPo> findSaleChangeOrderDetails(SaleQueryVo vo) {
        return this.dao.findSaleChangeOrderDetails(vo);
    }

    public List<SaleOrderBackPo> findSaleOrderBackForms(SaleQueryVo vo) {
        return dao.findSaleOrderBackForms(vo);
    }

    public List<SaleOrderBackPo> getAllSaleOrderBackForms(Company company) {
        return this.dao.getAllSaleOrderBackForms(company);
    }

    public List<SaleOrderBackDetailPo> findSaleOrderBackDetails(SaleQueryVo vo) {
        return this.dao.findSaleOrderBackDetails(vo);
    }

    public List<SaleOrderBackDetailPo> getAllDraftSaleOrderBackForms(Company company) {
        return this.dao.getAllDraftSaleOrderBackForms(company);
    }

    public void saveSaleOrderForm(SaleOrderPo po) {
        if (po.getNumber() == null || po.getNumber().equals("")) {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SALE_INDENT_NUM);
            po.setNumber(number);
        }
//        dao.saveSaleOrderForm(po);
        this.utilities.saveObject(po);
    }

    public void deleteOrderForm(SaleOrderPo po) {
        this.utilities.deleteObject(po);
    }

    public List<SaleOrderPo> findSaleOrderForms(SaleQueryVo vo) {
        return this.dao.findSaleOrderForms(vo);
    }

    public List<SaleOrderPo> getAllSaleOrderForms(Company company) {
        return this.dao.getAllSaleOrderForms(company);
    }

    public List<SaleOrderDetailPo> findSaleOrderDetails(SaleQueryVo vo) {
        return this.dao.findSaleOrderDetails(vo);
    }

    private SaleOrderDetailPo getExistIndentPerson(List<SaleOrderDetailPo> pos, String number) {
        int size = pos.size();
        if (size > 0) {

            for (SaleOrderDetailPo po : pos) {
                if (po.getProduct().getNumber().equals(number)) {
                    return po;
                }

            }
        }
        return null;
    }

    private SaleDetailPo getExistIndentPersonFromSaleDetailPo(List<SaleDetailPo> pos, String number) {
        int size = pos.size();
        if (size > 0) {
            for (SaleDetailPo po : pos) {
                if (po.getProduct().getNumber().equals(number)) {
                    return po;
                }
            }
        }
        return null;
    }

    private Object getExistIndentPerson(List<SaleOrderDetailPo> pos, Customer customer) {
        int size = pos.size();
        if (size > 0) {
            for (SaleOrderDetailPo po : pos) {
                if (po.getCustomer().equals(customer)) {
                    return po;
                }
            }
        }
        return null;
    }

    private SaleDetailPo getExistIndentPersonFromSaleDetailPo(List<SaleDetailPo> pos, Customer customer) {
        int size = pos.size();
        if (size > 0) {
            for (SaleDetailPo po : pos) {
                if (po.getCustomer().equals(customer)) {
                    return po;
                }
            }
        }
        return null;
    }

    private SaleOrderDetailPo getExistIndentPerson(List<SaleOrderDetailPo> pos, Employee employee) {
        int size = pos.size();
        if (size > 0) {
            for (SaleOrderDetailPo po : pos) {
                if (po.getEmployee().equals(employee)) {
                    return po;
                }
            }
        }
        return null;
    }

    private SaleDetailPo getExistIndentPersonfromSaleDetailPo(List<SaleDetailPo> pos, Employee employee) {
        int size = pos.size();
        if (size > 0) {
            for (SaleDetailPo po : pos) {
                if (po.getEmployee().equals(employee)) {
                    return po;
                }
            }
        }
        return null;
    }

    private boolean isIncludeOrderIndentProduct(List<SaleOrderDetailPo> pos, Customer customer) {
        int size = pos.size();
        if (size > 0) {
            for (SaleOrderDetailPo po : pos) {
                if (po.getCustomer().equals(customer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeSaleIndentProduct(List<SaleDetailPo> pos, Customer customer) {
        int size = pos.size();
        if (size > 0) {
            for (SaleDetailPo po : pos) {
                if (po.getCustomer().equals(customer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeSaleIndentProduct(List<SaleDetailPo> pos, Employee employee) {
        int size = pos.size();
        if (size > 0) {

            for (SaleDetailPo po : pos) {
                if (po.getEmployee().equals(employee)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeSalederIndentProduct(List<SaleOrderDetailPo> pos, Employee employee) {
        int size = pos.size();
        if (size > 0) {
            for (SaleOrderDetailPo po : pos) {
                if (po.getEmployee().equals(employee)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeOrderIndentProduct(List<SaleOrderDetailPo> pos, String number) {
        int size = pos.size();
        if (size > 0) {
            for (SaleOrderDetailPo po : pos) {
                if (po.getProduct().getNumber().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeSaleIndentProduct(List<SaleDetailPo> pos, String number) {
        int size = pos.size();
        if (size > 0) {
            for (SaleDetailPo po : pos) {
                if (po.getProduct().getNumber().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<SaleOrderDetailPo> getAllDraftSaleOrderForms(Company company) {
        return this.dao.getAllDraftSaleOrderForms(company);
    }

    public void saveSaleOrderBackForm(SaleOrderBackPo po) {
        if (po.getNumber() == null || po.getNumber().equals("")) {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SALE_BACK_NUM);
            po.setNumber(number);

        }
        this.utilities.saveObject(po);
    }

    public void deleteSaleOrderBackForm(SaleOrderBackPo po) {//liufei
        this.utilities.deleteObject(po);
    }

    public void saveSaleQuoteForm(SaleQuotePo po) {
        if (po.getNumber() == null || po.getNumber().equals("")) {
            String number = systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.PRODUCT_QUOTE_NUM);
            po.setNumber(number);
        }
        this.utilities.saveObject(po);
    }

    public void deleteQuoteForm(SaleQuotePo po) {
        this.utilities.deleteObject(po);
    }

    public List<SaleQuotePo> findSaleQuoteForms(SaleQueryVo vo) {
        return this.dao.findSaleQuoteForms(vo);
    }

    public List<SaleQuotePo> getAllQuoteForms(Company company) {
        return this.dao.getAllQuoteForms(company);
    }

    public List<SaleQuoteDetailPo> findQuoteDetails(SaleQueryVo vo) {
        return this.dao.findQuoteDetails(vo);
    }

    public List<SaleQuoteDetailPo> getAllDraftQuotederForms(Company company) {
        return this.dao.getAllDraftQuotederForms(company);
    }

    public void saveSaleDiscountForm(SaleQuotePo po) {
        this.utilities.saveObject(po);
    }

    public void deleteSaleDiscountForm(SaleQuotePo po) {
        this.utilities.deleteObject(po);
    }

    public List<SaleQuotePo> findSaleDiscountForms(SaleQueryVo vo) {
        return this.dao.findSaleDiscountForms(vo);
    }

    public List<SaleQuotePo> getAllSaleDiscountForms(Company company) {
        return this.dao.getAllSaleDiscountForms(company);
    }

    public List<SaleQuoteDetailPo> findSaleDiscountDetails(SaleQueryVo vo) {
        return dao.findSaleDiscountDetails(vo);
    }

    public List<SaleProductPo> getSaleProductForms(SaleQueryVo vo) {
        return dao.getSaleProductForms(vo);
    }

    public List<SaleQuoteDetailPo> getAllSaleDiscountDetailPoForms(Company company) {
        return dao.getAllSaleDiscountDetailPoForms(company);
    }

    public void passCommonSaleForm(Object po) throws InitialLackedException {
        if (po instanceof SalePo) {
            SalePo p = (SalePo) po;
            this.accountingService.addSaleReceivable(p);
            p.setStatus(ServiceConstants.FORMAL_STATUS);

            this.passSaleForm(p);
        } else if (po instanceof SaleOrderPo) {

            SaleOrderPo p = (SaleOrderPo) po;
            p.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);

        } else if (po instanceof SaleOrderBackPo) {
            SaleOrderBackPo p = (SaleOrderBackPo) po;
            this.accountingService.addSaleBackPayable(p);
            p.setStatus(ServiceConstants.FORMAL_STATUS);
            p.setWay(0);
            this.passSaleBackForm(p);
        } else if (po instanceof SaleChangeMoneyPo) {
            SaleChangeMoneyPo p = (SaleChangeMoneyPo) po;
            p.setStatus(ServiceConstants.FORMAL_STATUS);
            this.passSaleChangeMoneyrForm(p);
        } else if (po instanceof SalechaseOrderPo) {
            SalechaseOrderPo p = (SalechaseOrderPo) po;
            p.setStatus(ServiceConstants.FORMAL_STATUS);
            this.utilities.saveObject(po);
        } else if (po instanceof SaleQuotePo) {
            SaleQuotePo p = (SaleQuotePo) po;
            p.setStatus(ServiceConstants.FORMAL_STATUS);
            this.passSaleQuoteForm(p);
        }

    }

    public void passSaleOrderForm(SaleOrderPo po) throws IllegalStateException, SystemException {
        Transaction transaction = this.utilities.beginDaoTransaction();
        try {
            Set<SaleOrderDetailPo> details = po.getDetails();
            SaleOrderDetailPo detailPo = new SaleOrderDetailPo();
            SalePo salePo = new SalePo();
            salePo.setOrder(po);
            salePo.setClearing(po.getClearing());
            salePo.setNumber(systemManageService.getAutomaticNumber(po.getCompany(), NumberConstants.SALE_ORDER_NUM));
            salePo.setComments(po.getComments());
            salePo.setCompany(po.getCompany());
            salePo.setCreateDate(po.getCreateDate());
            salePo.setCreatedBy(po.getCreatedBy());
            salePo.setCustomer(po.getCustomer());
            salePo.setDelivery(po.getDelivery());
            salePo.setDepartment(po.getDepartment());
            salePo.setEmployee(po.getEmployee());
            salePo.setFormDate(po.getFormDate());
            salePo.setModifyDate(po.getModifyDate());
            salePo.setPay(po.getPay());
            salePo.setPayTerm(po.getPayTerm());
            salePo.setReceiving(po.getReceiving());
            salePo.setReceivingAddress(po.getReceivingAddress());
            salePo.setReceivingCode(po.getReceivingCode());
            salePo.setReceivingPhone(po.getReceivingPhone());
            salePo.setStatus(ServiceConstants.DRAFT_STATUS);
            salePo.setStorage(po.getStorage());
            salePo.setTotalMoney(po.getTotalMoney());
            salePo.setBalanceFashion(po.getBalanceFashion());
            salePo.setPayFashion(po.getPayFashion());
            salePo.setOrder(po);
            Set<SaleDetailPo> saleDetails = new HashSet<SaleDetailPo>();
            Iterator it = details.iterator();
            while (it.hasNext()) {
                detailPo = (SaleOrderDetailPo) it.next();
                SaleDetailPo saleDetailPo = new SaleDetailPo();
                saleDetailPo.setAmount(detailPo.getAmount());
                saleDetailPo.setComments(detailPo.getComments());
                saleDetailPo.setCreateDate(detailPo.getCreateDate());
                saleDetailPo.setCreatedBy(detailPo.getCreatedBy());
                saleDetailPo.setDiscount(detailPo.getDiscount());
                saleDetailPo.setMainObject(salePo);
                saleDetailPo.setModifyDate(detailPo.getModifyDate());
                saleDetailPo.setPrice(detailPo.getPrice());
                saleDetailPo.setPricetax(detailPo.getPriceTax());
                saleDetailPo.setRate(detailPo.getRate());
                saleDetailPo.setTax(detailPo.getTax());
                saleDetailPo.setTaxMoney(detailPo.getTaxMoney());
                saleDetailPo.setTaxprice(detailPo.getTaxprice());
                saleDetailPo.setTotalMoney(detailPo.getTotalMoney());
                saleDetailPo.setProduct(detailPo.getProduct());

                saleDetails.add(saleDetailPo);
            }
            salePo.setDetails(saleDetails);
            this.utilities.saveObject(salePo);
            this.utilities.saveObject(po);

        } catch (RuntimeException ex) {
            transaction.rollback();
            throw ex;
        }
        po.setStatus(ServiceConstants.FORMAL_STATUS);
        this.utilities.saveObject(po);
    }

    public void passSaleForm(SalePo po) {
        Set<SaleDetailPo> details = po.getDetails();
        SaleDetailPo detailPo = new SaleDetailPo();
        Iterator it = details.iterator();
        while (it.hasNext()) {
            detailPo = (SaleDetailPo) it.next();
            {
                StorageProductPo storageProduct = this.dao.findStorageProduct(po.getStorage(), detailPo.getProduct());
                //liufei changed <--
                StorageChangeLog storageChangeLog = new StorageChangeLog();
                storageChangeLog.setCompany(po.getCompany());
                storageChangeLog.setStorage(po.getStorage());
                storageChangeLog.setProduct(detailPo.getProduct());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setChangeType("销售单");
                storageChangeLog.setCurrentAmount(storageProduct.getAmount());
                storageChangeLog.setCurrentPrice(storageProduct.getPrice());
                //// -->
                //业务部
                //System.out.println(storageProduct.getAmount());
                double oldAmount = storageProduct.getAmount();
                //价格的计算方法,当前用平均法
                storageProduct.setAmount(oldAmount - detailPo.getAmount());
                storageChangeLog.setChangedAmount(storageProduct.getAmount());//liufei
                storageChangeLog.setChangedPrice(storageProduct.getPrice());//liufei
                this.utilities.saveObject(storageProduct);
                this.utilities.saveObject(storageChangeLog);//liufei
            }
        }
        this.utilities.saveObject(this.setDailyReportPo(po));
        this.utilities.saveObject(po);
    }

    public boolean commonSale(SalePo po) {
        Set<SaleDetailPo> details = po.getDetails();
        SaleDetailPo detailPo = new SaleDetailPo();
        Iterator it = details.iterator();
        while (it.hasNext()) {
            detailPo = (SaleDetailPo) it.next();
            {
                StorageProductPo storageProduct = this.dao.findStorageProduct(po.getStorage(), detailPo.getProduct());
                //业务部
                //System.out.println(storageProduct.getAmount());
                double oldAmount = storageProduct.getAmount();
                //价格的计算方法,当前用平均法
                if (oldAmount + 0.000001 > detailPo.getAmount()) {
                    return false;
                }
            }
        }
        return true;
    }

    public DailyReportPo setDailyReportPo(SalePo po) {
        DailyReportPo p = new DailyReportPo();
        p.setCompany(po.getCompany());
        p.setCashMoney(accountingService.getCashTotalMoney(po.getCompany()).getCashMoney());
        p.setSaleMoney(po.getTotalMoney());
        p.setCost(this.storageService.getDocumentsTotalMoney(po));
        p.setCreateDate(po.getCreateDate());
        p.setCreatedBy(po.getCreatedBy());
        p.setModifyDate(po.getModifyDate());
        p.setCustomer(po.getCustomer());
        p.setFormDate(po.getFormDate());
        p.setFormNumber(po.getNumber());
        p.setGrossProfitMoney(po.getTotalMoney() - this.storageService.getDocumentsTotalMoney(po));
        p.setOperationType("销售");
        p.getPayableMoney();
        p.setPreReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getPreReceivableMoney());
        p.setReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getReceivableMoney());
        p.setStockMoney(this.storageService.getStockTotalMoney(po.getCompany()));
//      p.setPurchaseMoney(purchaseMoney);
        return p;
    }

    public DailyReportPo setDailyReportPo(SaleOrderBackPo po) {
        DailyReportPo p = new DailyReportPo();
        p.setCompany(po.getCompany());
        p.setCashMoney(accountingService.getCashTotalMoney(po.getCompany()).getCashMoney());
        p.setSaleMoney(po.getTotalMoney());
        p.setCost(this.storageService.getDocumentsTotalMoney(po));
        p.setCreateDate(po.getCreateDate());
        p.setCreatedBy(po.getCreatedBy());
        p.setModifyDate(po.getModifyDate());
        p.setCustomer(po.getCustomer());
        p.setFormDate(po.getFormDate());
        p.setFormNumber(po.getNumber());
        p.setGrossProfitMoney(po.getTotalMoney() - this.storageService.getDocumentsTotalMoney(po));
        p.setOperationType("销售退货");
        p.getPayableMoney();
        p.setPreReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getPreReceivableMoney());
        p.setReceivableMoney(accountingService.getCashTotalMoney(po.getCompany()).getReceivableMoney());
        p.setStockMoney(this.storageService.getStockTotalMoney(po.getCompany()));
//      p.setPurchaseMoney(purchaseMoney);
        return p;
    }

    public void passSaleBackForm(SaleOrderBackPo po) {
        Set<SaleOrderBackDetailPo> details = po.getDetails();
        SaleOrderBackDetailPo detailPo = new SaleOrderBackDetailPo();
        Iterator it = details.iterator();
        while (it.hasNext()) {
            detailPo = (SaleOrderBackDetailPo) it.next();
            {
                StorageProductPo storageProduct = this.dao.findStorageProduct(po.getStorage(), detailPo.getProduct());
                //liufei changed <--
                StorageChangeLog storageChangeLog = new StorageChangeLog();
                storageChangeLog.setCompany(po.getCompany());
                storageChangeLog.setStorage(po.getStorage());
                storageChangeLog.setProduct(detailPo.getProduct());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setFormDate(po.getFormDate());
                storageChangeLog.setFormNumber(po.getNumber());
                storageChangeLog.setChangeType("销售单");
                storageChangeLog.setCurrentAmount(storageProduct.getAmount());
                storageChangeLog.setCurrentPrice(storageProduct.getPrice());
                //// -->
                double oldAmount = storageProduct.getAmount();
                //价格的计算方法,当前用平均法
                storageProduct.setAmount(oldAmount + detailPo.getAmount());
                storageChangeLog.setChangedAmount(storageProduct.getAmount());//liufei
                storageChangeLog.setChangedPrice(storageProduct.getPrice());//liufei
                this.utilities.saveObject(storageProduct);
                this.utilities.saveObject(storageChangeLog);//liufei
            }
        }

        this.utilities.saveObject(this.setDailyReportPo(po));
        this.utilities.saveObject(po);
    }

    public void passSalechaseOrderForm(SalechaseOrderPo po) {
        this.utilities.saveObject(po);
    }

    public void passSaleQuoteForm(SaleQuotePo po) {
        this.utilities.saveObject(po);
    }

    private void passSaleChangeMoneyrForm(SaleChangeMoneyPo po) {
        Product product = new Product();
        Set<SaleChangeMoneyDetailPo> details = po.getDetails();
        SaleChangeMoneyDetailPo detailPo = new SaleChangeMoneyDetailPo();
        Iterator it = details.iterator();
        ProductCatalog pg = new ProductCatalog();
        while (it.hasNext()) {
            detailPo = (SaleChangeMoneyDetailPo) it.next();
            Product pd = productService.getProduct(po.getCompany(), detailPo.getProduct());
            product.setId(pd.getId());
            product.setAlias(pd.getAlias());
            product.setArea(pd.getArea());
            product.setBarCode(pd.getBarCode());
            product.setBigUnit(pd.getBigUnit());
            product.setCode(pd.getCode());
            product.setComments(pd.getComments());
            product.setCreateDate(pd.getCreateDate());
            product.setCreatedBy(pd.getCreatedBy());
            product.setFactory(pd.getFactory());
            product.setMiddleUnit(pd.getMiddleUnit());
            product.setModifyDate(pd.getModifyDate());
            product.setName(pd.getName());
            product.setShortName(pd.getShortName());
            product.setSmallUnit(pd.getSmallUnit());
            product.setSpec(pd.getSpec());
            product.setStatus(pd.getStatus());
            product.setTaxRate(pd.getTaxRate());
//            product.setId(detailPo.getProductId());
            product.setNumber(detailPo.getNumber());
            product.setCompany(po.getCompany());
            product.setPrice0(detailPo.getWhlesale());
            product.setPrice1(detailPo.getWhlesale1());
            product.setPrice2(detailPo.getWebsite2());
            product.setPrice3(detailPo.getWebsite3());
            product.setPrice4(detailPo.getWebsite4());
            product.setPrice5(detailPo.getWebsite5());
            product.setRetailPrice(detailPo.getPurchaseprice());
            product.setStockPrice(detailPo.getPrice());
            product.setCatalog(detailPo.getProduct().getCatalog());
            productService.saveProduct(product);

        }
        this.utilities.saveObject(po);
    }

    public List<SaleProductStatPo> findSaleProductBalanceDaetails(SaleQueryVo vo) {
        List<SaleOrderDetailPo> saleOrderDetail = this.findSaleBalance(vo);
        SaleOrderDetailPo detailPo = new SaleOrderDetailPo();
        SaleProductStatPo ps = new SaleProductStatPo();
        List<SaleProductStatPo> s = new ArrayList();
        for (SaleOrderDetailPo saleOrder : saleOrderDetail) {
            try {
                int id = ps.getProduct().getId();
            } catch (Exception ex) {
                ps.setAmount(saleOrder.getAmount());
                ps.setTotalMoney(saleOrder.getTotalMoney());
                ps.setCustomer(saleOrder.getMainObject().getCustomer());
                ps.setProduct(saleOrder.getProduct());
                s.add(ps);
            }
            if (saleOrder.getProduct().getId() == (ps.getProduct().getId())) {
                Double amount = ps.getAmount();
                amount += saleOrder.getAmount();
                ps.setAmount(amount);
                Double totalMoney = ps.getTotalMoney();
                totalMoney += saleOrder.getTotalMoney();
                ps.setTotalMoney(totalMoney);
            } else {
                ps = new SaleProductStatPo();
                ps.setAmount(saleOrder.getAmount());
                ps.setTotalMoney(saleOrder.getTotalMoney());
                ps.setCustomer(saleOrder.getMainObject().getCustomer());
                ps.setProduct(saleOrder.getProduct());
                s.add(ps);
            }
        }
        return s;
    }

    public List<SaleOrderPo> getSaleOrderNotSaleForms(Company company) {
        List<SaleOrderPo> s = dao.getAllSaleOrderForms(company, 1);
        List<SaleOrderPo> list = new ArrayList();
        for (SaleOrderPo saleOrder : s) {
//            System
            if (dao.getAllSaleDetailForms(company, saleOrder).size() <= 0) {
                list.add(saleOrder);
            }
        }
        return list;
    }

    public List<SalePo> getAllSaleDetailForms(Company company, int status) {
        List<SalePo> sale = this.dao.getAllSaleDetailForms(company, status);

        List<SalePo> s = new ArrayList();
        for (SalePo saleOrder : sale) {
            if (this.dao.getAllSaleOrderBackForms(company, saleOrder).size() <= 0) {
                s.add(saleOrder);
            }
        }

        return s;
    }

    public List<SaleOrderDetailPo> findSaleOrderProductDaetails(SaleQueryVo vo) {
//        vo.setStatus(1);
        List<SaleOrderPo> saleDetail = this.findSaleOrderForms(vo);
        SaleOrderDetailPo ps = new SaleOrderDetailPo();
        List<SaleOrderDetailPo> s = new ArrayList();
        for (SaleOrderPo po : saleDetail) {
            Set<SaleOrderDetailPo> pe = po.getDetails();
            for (SaleOrderDetailPo p : pe) {
                if (this.isIncludeOrderIndentProduct(s, p.getProduct().getNumber())) {
                    SaleOrderDetailPo sales = new SaleOrderDetailPo();
                    sales = (SaleOrderDetailPo) this.getExistIndentPerson(s, p.getProduct().getNumber());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;
    }

    public List<SaleOrderDetailPo> findSaleOrderEmployeeProductDaetails(SaleQueryVo vo) {
        List<SaleOrderPo> saleOrderDetail = this.findSaleOrderForms(vo);
        SaleOrderDetailPo ps = new SaleOrderDetailPo();
        List<SaleOrderDetailPo> s = new ArrayList();
        for (SaleOrderPo po : saleOrderDetail) {
            Set<SaleOrderDetailPo> pe = po.getDetails();
            for (SaleOrderDetailPo p : pe) {
                if (this.isIncludeSalederIndentProduct(s, p.getEmployee())) {
                    SaleOrderDetailPo sales = new SaleOrderDetailPo();
                    sales = (SaleOrderDetailPo) this.getExistIndentPerson(s, p.getEmployee());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;
    }

    public List<SaleOrderDetailPo> findSaleOrderCustomerProductDaetails(SaleQueryVo vo) {
        List<SaleOrderPo> saleOrderDetail = this.findSaleOrderForms(vo);
        SaleOrderDetailPo ps = new SaleOrderDetailPo();
        List<SaleOrderDetailPo> s = new ArrayList();
        for (SaleOrderPo po : saleOrderDetail) {
            Set<SaleOrderDetailPo> pe = po.getDetails();
            for (SaleOrderDetailPo p : pe) {
                if (this.isIncludeOrderIndentProduct(s, p.getCustomer())) {
                    SaleOrderDetailPo sales = new SaleOrderDetailPo();
                    sales = (SaleOrderDetailPo) this.getExistIndentPerson(s, p.getCustomer());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;
    }

    public List<SaleDetailPo> findSaleProductDaetails(SaleQueryVo vo) {
        List<SalePo> saleOrderDetail = this.findSaleDetailForms(vo);
        List<SaleDetailPo> s = new ArrayList();
        for (SalePo po : saleOrderDetail) {
            Set<SaleDetailPo> pe = po.getDetails();

            for (SaleDetailPo p : pe) {
                if (this.isIncludeSaleIndentProduct(s, p.getProduct().getNumber())) {
                    SaleDetailPo sales = new SaleDetailPo();
                    sales = (SaleDetailPo) this.getExistIndentPersonFromSaleDetailPo(s, p.getProduct().getNumber());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;
    }

    public List<SaleDetailPo> findSaleEmployeeProductDaetails(SaleQueryVo vo) {
        List<SalePo> saleOrderDetail = this.findSaleDetailForms(vo);
        List<SaleDetailPo> s = new ArrayList();
        for (SalePo po : saleOrderDetail) {
            Set<SaleDetailPo> pe = po.getDetails();

            for (SaleDetailPo p : pe) {
                if (this.isIncludeSaleIndentProduct(s, p.getEmployee())) {
                    SaleDetailPo sales = new SaleDetailPo();
                    sales = (SaleDetailPo) this.getExistIndentPersonfromSaleDetailPo(s, p.getEmployee());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;

    }

    public List<SaleDetailPo> findSaleCustomerProductDaetails(SaleQueryVo vo) {
        List<SalePo> saleOrderDetail = this.findSaleDetailForms(vo);
        List<SaleDetailPo> s = new ArrayList();
        for (SalePo po : saleOrderDetail) {
            Set<SaleDetailPo> pe = po.getDetails();

            for (SaleDetailPo p : pe) {
                if (this.isIncludeSaleIndentProduct(s, p.getCustomer())) {
                    SaleDetailPo sales = new SaleDetailPo();
                    sales = (SaleDetailPo) this.getExistIndentPersonFromSaleDetailPo(s, p.getCustomer());
                    sales.setAmount(sales.getAmount() + p.getAmount());
                    sales.setTotalMoney(sales.getTotalMoney() + p.getTotalMoney());
                    s.remove(sales);
                    s.add(sales);
                } else {
                    s.add(p);
                }
            }
        }
        return s;
    }

 public Double getSalePriceDaetails(Product product, Customer customer, Company company)
 {
        Double price = 0d;
      
        List<SalePo> sale = dao.getSalePriceDaetails(customer, company);
        for(SalePo s:sale){
        Set<SaleDetailPo> details = s.getDetails();
        SaleDetailPo detailpo = new SaleDetailPo();
        Iterator it = details.iterator();
        while (it.hasNext()) {
            detailpo = (SaleDetailPo) it.next();
            if (detailpo.getProduct().equals(product)) {
                price = detailpo.getPrice();
                break;
            }
        }
        }
 
          return price;
    }
}
