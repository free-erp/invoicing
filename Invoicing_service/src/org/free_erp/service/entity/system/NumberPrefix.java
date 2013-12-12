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
public class NumberPrefix implements java.io.Serializable
{
    private static final long serialVersionUID = 1l;
    private int id;
    private String proNum;
    private String cusNum;
    private String empNum;
    private String stoNum;
    private String proCatalogNum;
    private String cusCatalogNum;
    private String empCatalogNum;
    private String inStoNum;
    private String outStoNum;
    private String outFlowNum;
    private String lossNum;
    private String moveNum;
    private String checkNum;
    private String changeNum;
    private String minMaxNum;
    private String initStoNum;
    private String purchaseIndentNum;
    private String purchaseOrderNum;
    private String purchaseBackNum;
    private String saleBeforeIncomeNum;
    private String gatherBalanceNum;
    private String purchaseBeforePayNum;
    private String payBalanceNum;
    private String keepFareNum;
    private String genalFareNum;
    private String otherIncomeNum;
    private String cashBankNum;
    private String subjectCatalogNum;
    private String subjectNum;
    private String productQuoteNum;
    private String saleIndentNum;
    private String saleOrderNum;
    private String changePriceNum;
    private String saleBackNum;
    private String subjectInitNum;
    private String receiveInitNum;
    private String payInitNum;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(String changeNum) {
        this.changeNum = changeNum;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getCusNum() {
        return cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getCusCatalogNum() {
        return cusCatalogNum;
    }

    public void setCusCatalogNum(String cusCatalogNum) {
        this.cusCatalogNum = cusCatalogNum;
    }

    public String getEmpCatalogNum() {
        return empCatalogNum;
    }

    public void setEmpCatalogNum(String empCatalogNum) {
        this.empCatalogNum = empCatalogNum;
    }

    public String getProCatalogNum() {
        return proCatalogNum;
    }

    public void setProCatalogNum(String proCatalogNum) {
        this.proCatalogNum = proCatalogNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInStoNum() {
        return inStoNum;
    }

    public void setInStoNum(String inStoNum) {
        this.inStoNum = inStoNum;
    }

    public String getInitStoNum() {
        return initStoNum;
    }

    public void setInitStoNum(String initStoNum) {
        this.initStoNum = initStoNum;
    }

    public String getLossNum() {
        return lossNum;
    }

    public void setLossNum(String lossNum) {
        this.lossNum = lossNum;
    }

    public String getMinMaxNum() {
        return minMaxNum;
    }

    public void setMinMaxNum(String minMaxNum) {
        this.minMaxNum = minMaxNum;
    }

    public String getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(String moveNum) {
        this.moveNum = moveNum;
    }

    public String getOutFlowNum() {
        return outFlowNum;
    }

    public void setOutFlowNum(String outFlowNum) {
        this.outFlowNum = outFlowNum;
    }

    public String getOutStoNum() {
        return outStoNum;
    }

    public void setOutStoNum(String outStoNum) {
        this.outStoNum = outStoNum;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getStoNum() {
        return stoNum;
    }

    public void setStoNum(String stoNum) {
        this.stoNum = stoNum;
    }

    public String getPurchaseBackNum() {
        return purchaseBackNum;
    }

    public void setPurchaseBackNum(String purchaseBackNum) {
        this.purchaseBackNum = purchaseBackNum;
    }

    public String getPurchaseIndentNum() {
        return purchaseIndentNum;
    }

    public void setPurchaseIndentNum(String purchaseIndentNum) {
        this.purchaseIndentNum = purchaseIndentNum;
    }

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public String getCashBankNum() {
        return cashBankNum;
    }

    public void setCashBankNum(String cashBankNum) {
        this.cashBankNum = cashBankNum;
    }

    public String getGatherBalanceNum() {
        return gatherBalanceNum;
    }

    public void setGatherBalanceNum(String gatherBalanceNum) {
        this.gatherBalanceNum = gatherBalanceNum;
    }

    public String getGenalFareNum() {
        return genalFareNum;
    }

    public void setGenalFareNum(String genalFareNum) {
        this.genalFareNum = genalFareNum;
    }

    public String getKeepFareNum() {
        return keepFareNum;
    }

    public void setKeepFareNum(String keepFareNum) {
        this.keepFareNum = keepFareNum;
    }

    public String getOtherIncomeNum() {
        return otherIncomeNum;
    }

    public void setOtherIncomeNum(String otherIncomeNum) {
        this.otherIncomeNum = otherIncomeNum;
    }

    public String getPayBalanceNum() {
        return payBalanceNum;
    }

    public void setPayBalanceNum(String payBalanceNum) {
        this.payBalanceNum = payBalanceNum;
    }

    public String getPurchaseBeforePayNum() {
        return purchaseBeforePayNum;
    }

    public void setPurchaseBeforePayNum(String purchaseBeforePayNum) {
        this.purchaseBeforePayNum = purchaseBeforePayNum;
    }

    public String getSaleBeforeIncomeNum() {
        return saleBeforeIncomeNum;
    }

    public void setSaleBeforeIncomeNum(String saleBeforeIncomeNum) {
        this.saleBeforeIncomeNum = saleBeforeIncomeNum;
    }

    public String getSubjectCatalogNum() {
        return subjectCatalogNum;
    }

    public void setSubjectCatalogNum(String subjectCatalogNum) {
        this.subjectCatalogNum = subjectCatalogNum;
    }

    public String getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(String subjectNum) {
        this.subjectNum = subjectNum;
    }

    public String getChangePriceNum() {
        return changePriceNum;
    }

    public void setChangePriceNum(String changePriceNum) {
        this.changePriceNum = changePriceNum;
    }

    public String getProductQuoteNum() {
        return productQuoteNum;
    }

    public void setProductQuoteNum(String productQuoteNum) {
        this.productQuoteNum = productQuoteNum;
    }

    public String getSaleBackNum() {
        return saleBackNum;
    }

    public void setSaleBackNum(String saleBackNum) {
        this.saleBackNum = saleBackNum;
    }

    public String getSaleIndentNum() {
        return saleIndentNum;
    }

    public void setSaleIndentNum(String saleIndentNum) {
        this.saleIndentNum = saleIndentNum;
    }

    public String getSaleOrderNum() {
        return saleOrderNum;
    }

    public void setSaleOrderNum(String saleOrderNum) {
        this.saleOrderNum = saleOrderNum;
    }

    public String getPayInitNum() {
        return payInitNum;
    }

    public void setPayInitNum(String payInitNum) {
        this.payInitNum = payInitNum;
    }

    public String getReceiveInitNum() {
        return receiveInitNum;
    }

    public void setReceiveInitNum(String receiveInitNum) {
        this.receiveInitNum = receiveInitNum;
    }

    public String getSubjectInitNum() {
        return subjectInitNum;
    }

    public void setSubjectInitNum(String subjectInitNum) {
        this.subjectInitNum = subjectInitNum;
    }

}
