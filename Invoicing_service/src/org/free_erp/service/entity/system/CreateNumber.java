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
package org.free_erp.service.entity.system;

import org.free_erp.service.entity.base.Company;

/**
 *
 * @author TengJianfa 13003311398
 */
public class CreateNumber implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private int id;
    private Integer proNum;
    private Integer cusNum;
    private Integer empNum;
    private Integer stoNum;
    private Integer proCatalogNum;
    private Integer cusCatalogNum;
    private Integer empCatalogNum;
    private Integer inStoNum;
    private Integer outStoNum;
    private Integer outFlowNum;
    private Integer lossNum;
    private Integer moveNum;
    private Integer checkNum;
    private Integer changeNum;
    private Integer minMaxNum;
    private Integer initStoNum;
    private Integer purchaseIndentNum;
    private Integer purchaseOrderNum;
    private Integer purchaseBackNum;
    private Integer saleBeforeIncomeNum;
    private Integer gatherBalanceNum;
    private Integer purchaseBeforePayNum;
    private Integer payBalanceNum;
    private Integer keepFareNum;
    private Integer genalFareNum;
    private Integer otherIncomeNum;
    private Integer cashBankNum;
    private Integer subjectCatalogNum;
    private Integer subjectNum;
    private Integer productQuoteNum;
    private Integer saleIndentNum;
    private Integer saleOrderNum;
    private Integer changePriceNum;
    private Integer saleBackNum;
    private Integer subjectInitNum;
    private Integer receiveInitNum;
    private Integer payInitNum;

    private Company company;

    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCusNum() {
        return cusNum;
    }

    public void setCusNum(Integer cusNum) {
        this.cusNum = cusNum;
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }

    public Integer getCusCatalogNum() {
        return cusCatalogNum;
    }

    public void setCusCatalogNum(Integer cusCatalogNum) {
        this.cusCatalogNum = cusCatalogNum;
    }

    public Integer getEmpCatalogNum() {
        return empCatalogNum;
    }

    public void setEmpCatalogNum(Integer empCatalogNum) {
        this.empCatalogNum = empCatalogNum;
    }

    public Integer getProCatalogNum() {
        return proCatalogNum;
    }

    public void setProCatalogNum(Integer proCatalogNum) {
        this.proCatalogNum = proCatalogNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getInStoNum() {
        return inStoNum;
    }

    public void setInStoNum(Integer inStoNum) {
        this.inStoNum = inStoNum;
    }

    public Integer getInitStoNum() {
        return initStoNum;
    }

    public void setInitStoNum(Integer initStoNum) {
        this.initStoNum = initStoNum;
    }

    public Integer getLossNum() {
        return lossNum;
    }

    public void setLossNum(Integer lossNum) {
        this.lossNum = lossNum;
    }

    public Integer getMinMaxNum() {
        return minMaxNum;
    }

    public void setMinMaxNum(Integer minMaxNum) {
        this.minMaxNum = minMaxNum;
    }

    public Integer getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(Integer moveNum) {
        this.moveNum = moveNum;
    }

    public Integer getOutFlowNum() {
        return outFlowNum;
    }

    public void setOutFlowNum(Integer outFlowNum) {
        this.outFlowNum = outFlowNum;
    }

    public Integer getOutStoNum() {
        return outStoNum;
    }

    public void setOutStoNum(Integer outStoNum) {
        this.outStoNum = outStoNum;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    public Integer getStoNum() {
        return stoNum;
    }

    public void setStoNum(Integer stoNum) {
        this.stoNum = stoNum;
    }

    public Integer getPurchaseBackNum() {
        return purchaseBackNum;
    }

    public void setPurchaseBackNum(Integer purchaseBackNum) {
        this.purchaseBackNum = purchaseBackNum;
    }

    public Integer getPurchaseIndentNum() {
        return purchaseIndentNum;
    }

    public void setPurchaseIndentNum(Integer purchaseIndentNum) {
        this.purchaseIndentNum = purchaseIndentNum;
    }

    public Integer getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(Integer purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public Integer getCashBankNum() {
        return cashBankNum;
    }

    public void setCashBankNum(Integer cashBankNum) {
        this.cashBankNum = cashBankNum;
    }

    public Integer getGatherBalanceNum() {
        return gatherBalanceNum;
    }

    public void setGatherBalanceNum(Integer gatherBalanceNum) {
        this.gatherBalanceNum = gatherBalanceNum;
    }

    public Integer getGenalFareNum() {
        return genalFareNum;
    }

    public void setGenalFareNum(Integer genalFareNum) {
        this.genalFareNum = genalFareNum;
    }

    public Integer getKeepFareNum() {
        return keepFareNum;
    }

    public void setKeepFareNum(Integer keepFareNum) {
        this.keepFareNum = keepFareNum;
    }

    public Integer getOtherIncomeNum() {
        return otherIncomeNum;
    }

    public void setOtherIncomeNum(Integer otherIncomeNum) {
        this.otherIncomeNum = otherIncomeNum;
    }

    public Integer getPayBalanceNum() {
        return payBalanceNum;
    }

    public void setPayBalanceNum(Integer payBalanceNum) {
        this.payBalanceNum = payBalanceNum;
    }

    public Integer getPurchaseBeforePayNum() {
        return purchaseBeforePayNum;
    }

    public void setPurchaseBeforePayNum(Integer purchaseBeforePayNum) {
        this.purchaseBeforePayNum = purchaseBeforePayNum;
    }

    public Integer getSaleBeforeIncomeNum() {
        return saleBeforeIncomeNum;
    }

    public void setSaleBeforeIncomeNum(Integer saleBeforeIncomeNum) {
        this.saleBeforeIncomeNum = saleBeforeIncomeNum;
    }

    public Integer getSubjectCatalogNum() {
        return subjectCatalogNum;
    }

    public void setSubjectCatalogNum(Integer subjectCatalogNum) {
        this.subjectCatalogNum = subjectCatalogNum;
    }

    public Integer getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(Integer subjectNum) {
        this.subjectNum = subjectNum;
    }

    public Integer getChangePriceNum() {
        return changePriceNum;
    }

    public void setChangePriceNum(Integer changePriceNum) {
        this.changePriceNum = changePriceNum;
    }

    public Integer getProductQuoteNum() {
        return productQuoteNum;
    }

    public void setProductQuoteNum(Integer productQuoteNum) {
        this.productQuoteNum = productQuoteNum;
    }

    public Integer getSaleBackNum() {
        return saleBackNum;
    }

    public void setSaleBackNum(Integer saleBackNum) {
        this.saleBackNum = saleBackNum;
    }

    public Integer getSaleIndentNum() {
        return saleIndentNum;
    }

    public void setSaleIndentNum(Integer saleIndentNum) {
        this.saleIndentNum = saleIndentNum;
    }

    public Integer getSaleOrderNum() {
        return saleOrderNum;
    }

    public void setSaleOrderNum(Integer saleOrderNum) {
        this.saleOrderNum = saleOrderNum;
    }

    public Integer getPayInitNum() {
        return payInitNum;
    }

    public void setPayInitNum(Integer payInitNum) {
        this.payInitNum = payInitNum;
    }

    public Integer getReceiveInitNum() {
        return receiveInitNum;
    }

    public void setReceiveInitNum(Integer receiveInitNum) {
        this.receiveInitNum = receiveInitNum;
    }

    public Integer getSubjectInitNum() {
        return subjectInitNum;
    }

    public void setSubjectInitNum(Integer subjectInitNum) {
        this.subjectInitNum = subjectInitNum;
    }
    
}
