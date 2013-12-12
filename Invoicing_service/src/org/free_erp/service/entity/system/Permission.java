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

import org.free_erp.service.entity.base.User;

/**
 *
 * @author Administrator
 */
public class Permission implements java.io.Serializable {

    private static final long serialVersionUID = 1l;
    private Integer permissionId;
    private User user;
    private Integer storageCpck = 223;
    private Integer storageKcby = 223;
    private Integer storageKcbs = 223;
    private Integer storageYkgl = 223;
    private Integer storagePdgl = 223;
    private Integer storageKcbj = 223;
    private Integer storageSxxz = 223;
    private Integer storageSprk = 223;
    private Integer systemGsxx = 0;
    private Integer systemJsxx = 0;
    private Integer systemxlkgl = 0;
    private Integer systemYhxx = 0;
    private Integer systemXgmm = 1;
    private Integer systemXtrz =  0;//145;
    private Integer baseProduct = 3999;
    private Integer baseCustomer = 3999;
    private Integer baseEmployee = 3999;
    private Integer baseStorage = 3999;
     private Integer baseSupplier= 3999;
    private Integer initKcqc = 223;
    private Integer reportKcspye = 145;
    private Integer reportKcrkmx = 145;
    private Integer reportKcckmx = 145;
    private Integer reportKccprk = 145;
    private Integer reportKccpck = 145;
    private Integer reportKccpsxx = 145;
    private Integer reportKcyk = 145;
    private Integer reportKcpd = 145;
    private Integer reportKcby = 145;
    private Integer reportKcbs = 145;
    private Integer reportKcpdmx = 145;
    private Integer reportKcykmx = 145;
    private Integer reportKcbymx = 145;
    private Integer reportKcbsmx = 145;
    private Integer reportKcbjmx = 145;
    private Integer xsbj = 223;
    private Integer xsdd = 223;
    private Integer xsd = 223;
    private Integer xssptj = 223;
    private Integer xsth = 223;
    private Integer cgdd = 223;
    private Integer cgd = 223;
    private Integer cgth = 223;
    private Integer cwjyfy = 223;
    private Integer cwybfy = 223;
    private Integer cwqtsr = 223;
    private Integer cwxjyh = 223;
    private Integer cwxsyf = 223;
    private Integer cwskjs = 223;
    private Integer cwcgyf = 223;
    private Integer cwfkjs = 223;
    private Integer cwkmyeqc = 223;
    private Integer cwysqc = 223;
    private Integer cwyfqc = 223;
   private Integer cwkjkm =223;
    private Integer reportXdcx = 145;
    private Integer reportXdsptj = 145;
    private Integer reportXdspcx = 145;
    private Integer reportXdyecx = 145;
    private Integer reportXspyetj = 145;
    private Integer reportXdspyemx = 145;
    private Integer reportXdkstj = 145;
    private Integer reportXdywytj = 145;
    private Integer reportXstj = 145;
    private Integer reportXsmx = 145;
    private Integer reportXskstj = 145;
    private Integer reportXsksmx = 145;
    private Integer reportXsywytj = 145;
    private Integer reportXywymx = 145;
    private Integer reportCdcx = 145;
    private Integer reportCdsptj = 145;
    private Integer reportCdspcx = 145;
    private Integer reportCdyecx = 145;
    private Integer reportCdspyetj = 145;
    private Integer reportCdspyecx = 145;
    private Integer reportCdywytj = 145;
    private Integer reportCdkstj = 145;
    private Integer reportCgtj = 145;
    private Integer reportCgkstj = 145;
    private Integer reportCgmx = 145;
    private Integer reportCgksmx = 145;
    private Integer reportCgywytj = 145;
    private Integer reportCgywymx = 145;
    private Integer reportCgsphz = 145;
    private Integer reportCgjgbd = 145;
    private Integer reportCwkmye = 145;
    private Integer reportCwxjyhybb = 145;
    private Integer reportCwxjyhmx = 145;
    private Integer reportCwrcfybb = 145;
    private Integer reportCwrcfymx = 145;
    private Integer reportCwqtsrybb = 145;
    private Integer reportCwqtsrmx = 145;
    private Integer reportJsgxsyfye = 145;
    private Integer reportJsgxswjmx = 145;
    private Integer reportJsgksysye = 145;
    private Integer reportJsgkswfmx = 145;
         private Integer reportRbb = 145;
       private Integer reportYbb = 145;
        private Integer reportNbb = 145;
      private Integer reportKmye = 145;
        private Integer reportZhkcye = 145;
       private Integer reportYf = 145;
       private Integer reportYs = 145;
    private Integer  price=0;

    public Integer getReportKmye() {
        return reportKmye;
    }

    public void setReportKmye(Integer reportKmye) {
        this.reportKmye = reportKmye;
    }

    public Integer getReportNbb() {
        return reportNbb;
    }

    public void setReportNbb(Integer reportNbb) {
        this.reportNbb = reportNbb;
    }

    public Integer getReportRbb() {
        return reportRbb;
    }

    public void setReportRbb(Integer reportRbb) {
        this.reportRbb = reportRbb;
    }

    public Integer getReportYbb() {
        return reportYbb;
    }

    public void setReportYbb(Integer reportYbb) {
        this.reportYbb = reportYbb;
    }

    public Integer getReportYf() {
        return reportYf;
    }

    public void setReportYf(Integer reportYf) {
        this.reportYf = reportYf;
    }

    public Integer getReportYs() {
        return reportYs;
    }

    public void setReportYs(Integer reportYs) {
        this.reportYs = reportYs;
    }

    public Integer getReportZhkcye() {
        return reportZhkcye;
    }

    public void setReportZhkcye(Integer reportZhkcye) {
        this.reportZhkcye = reportZhkcye;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
//    private Integer billMaker = 0;//liufei
//    private Integer assessor = 0;//liufei
//    private Integer administrator = 0;//liufei
//
//    public Integer getAdministrator() {
//        return administrator;
//    }
//
//    public void setAdministrator(Integer administrator) {
//        this.administrator = administrator;
//    }
//
//    public Integer getAssessor() {
//        return assessor;
//    }
//
//    public void setAssessor(Integer assessor) {
//        this.assessor = assessor;
//    }
//
//    public Integer getBillMaker() {
//        return billMaker;
//    }
//
//    public void setBillMaker(Integer billMaker) {
//        this.billMaker = billMaker;
//    }

    public Integer getCwkjkm() {
        return cwkjkm;
    }

    public void setCwkjkm(Integer cwkjkm) {
        this.cwkjkm = cwkjkm;
    }

    public Integer getCwkmyeqc() {
        return cwkmyeqc;
    }

    public void setCwkmyeqc(Integer cwkmyeqc) {
        this.cwkmyeqc = cwkmyeqc;
    }

    public Integer getBaseSupplier() {
        return baseSupplier;
    }

    public void setBaseSupplier(Integer baseSupplier) {
        this.baseSupplier = baseSupplier;
    }

    public Integer getCwyfqc() {
        return cwyfqc;
    }

    public void setCwyfqc(Integer cwyfqc) {
        this.cwyfqc = cwyfqc;
    }
    public Integer getCwysqc() {
        return cwysqc;
    }

    public void setCwysqc(Integer cwysqc) {
        this.cwysqc = cwysqc;
    }

    public Integer getReportCwkmye() {
        return reportCwkmye;
    }

    public void setReportCwkmye(Integer reportCwkmye) {
        this.reportCwkmye = reportCwkmye;
    }

    public Integer getReportCwqtsrmx() {
        return reportCwqtsrmx;
    }

    public void setReportCwqtsrmx(Integer reportCwqtsrmx) {
        this.reportCwqtsrmx = reportCwqtsrmx;
    }

    public Integer getReportCwqtsrybb() {
        return reportCwqtsrybb;
    }

    public void setReportCwqtsrybb(Integer reportCwqtsrybb) {
        this.reportCwqtsrybb = reportCwqtsrybb;
    }

    public Integer getReportCwrcfybb() {
        return reportCwrcfybb;
    }

    public void setReportCwrcfybb(Integer reportCwrcfybb) {
        this.reportCwrcfybb = reportCwrcfybb;
    }

    public Integer getReportCwrcfymx() {
        return reportCwrcfymx;
    }

    public void setReportCwrcfymx(Integer reportCwrcfymx) {
        this.reportCwrcfymx = reportCwrcfymx;
    }

    public Integer getReportCwxjyhmx() {
        return reportCwxjyhmx;
    }

    public void setReportCwxjyhmx(Integer reportCwxjyhmx) {
        this.reportCwxjyhmx = reportCwxjyhmx;
    }

    public Integer getReportCwxjyhybb() {
        return reportCwxjyhybb;
    }

    public void setReportCwxjyhybb(Integer reportCwxjyhybb) {
        this.reportCwxjyhybb = reportCwxjyhybb;
    }

    public Integer getReportJsgkswfmx() {
        return reportJsgkswfmx;
    }

    public void setReportJsgkswfmx(Integer reportJsgkswfmx) {
        this.reportJsgkswfmx = reportJsgkswfmx;
    }

    public Integer getReportJsgksysye() {
        return reportJsgksysye;
    }

    public void setReportJsgksysye(Integer reportJsgksysye) {
        this.reportJsgksysye = reportJsgksysye;
    }

    public Integer getReportJsgxswjmx() {
        return reportJsgxswjmx;
    }

    public void setReportJsgxswjmx(Integer reportJsgxswjmx) {
        this.reportJsgxswjmx = reportJsgxswjmx;
    }

    public Integer getReportJsgxsyfye() {
        return reportJsgxsyfye;
    }

    public void setReportJsgxsyfye(Integer reportJsgxsyfye) {
        this.reportJsgxsyfye = reportJsgxsyfye;
    }

    public Integer getReportCdcx() {
        return reportCdcx;
    }

    public void setReportCdcx(Integer reportCdcx) {
        this.reportCdcx = reportCdcx;
    }

    public Integer getReportCdkstj() {
        return reportCdkstj;
    }

    public void setReportCdkstj(Integer reportCdkstj) {
        this.reportCdkstj = reportCdkstj;
    }

    public Integer getReportCdspcx() {
        return reportCdspcx;
    }

    public void setReportCdspcx(Integer reportCdspcx) {
        this.reportCdspcx = reportCdspcx;
    }

    public Integer getReportCdsptj() {
        return reportCdsptj;
    }

    public void setReportCdsptj(Integer reportCdsptj) {
        this.reportCdsptj = reportCdsptj;
    }

    public Integer getReportCdspyecx() {
        return reportCdspyecx;
    }

    public void setReportCdspyecx(Integer reportCdspyecx) {
        this.reportCdspyecx = reportCdspyecx;
    }

    public Integer getReportCdspyetj() {
        return reportCdspyetj;
    }

    public void setReportCdspyetj(Integer reportCdspyetj) {
        this.reportCdspyetj = reportCdspyetj;
    }

    public Integer getReportCdyecx() {
        return reportCdyecx;
    }

    public void setReportCdyecx(Integer reportCdyecx) {
        this.reportCdyecx = reportCdyecx;
    }

    public Integer getReportCdywytj() {
        return reportCdywytj;
    }

    public void setReportCdywytj(Integer reportCdywytj) {
        this.reportCdywytj = reportCdywytj;
    }

    public Integer getReportCgjgbd() {
        return reportCgjgbd;
    }

    public void setReportCgjgbd(Integer reportCgjgbd) {
        this.reportCgjgbd = reportCgjgbd;
    }

    public Integer getReportCgksmx() {
        return reportCgksmx;
    }

    public void setReportCgksmx(Integer reportCgksmx) {
        this.reportCgksmx = reportCgksmx;
    }

    public Integer getReportCgkstj() {
        return reportCgkstj;
    }

    public void setReportCgkstj(Integer reportCgkstj) {
        this.reportCgkstj = reportCgkstj;
    }

    public Integer getReportCgmx() {
        return reportCgmx;
    }

    public void setReportCgmx(Integer reportCgmx) {
        this.reportCgmx = reportCgmx;
    }

    public Integer getReportCgsphz() {
        return reportCgsphz;
    }

    public void setReportCgsphz(Integer reportCgsphz) {
        this.reportCgsphz = reportCgsphz;
    }

    public Integer getReportCgtj() {
        return reportCgtj;
    }

    public void setReportCgtj(Integer reportCgtj) {
        this.reportCgtj = reportCgtj;
    }

    public Integer getReportCgywymx() {
        return reportCgywymx;
    }

    public void setReportCgywymx(Integer reportCgywymx) {
        this.reportCgywymx = reportCgywymx;
    }

    public Integer getReportCgywytj() {
        return reportCgywytj;
    }

    public void setReportCgywytj(Integer reportCgywytj) {
        this.reportCgywytj = reportCgywytj;
    }

    public Integer getReportXdkstj() {
        return reportXdkstj;
    }

    public void setReportXdkstj(Integer reportXdkstj) {
        this.reportXdkstj = reportXdkstj;
    }

    public Integer getReportXdspcx() {
        return reportXdspcx;
    }

    public void setReportXdspcx(Integer reportXdspcx) {
        this.reportXdspcx = reportXdspcx;
    }

    public Integer getReportXdsptj() {
        return reportXdsptj;
    }

    public void setReportXdsptj(Integer reportXdsptj) {
        this.reportXdsptj = reportXdsptj;
    }

    public Integer getReportXdspyemx() {
        return reportXdspyemx;
    }

    public void setReportXdspyemx(Integer reportXdspyemx) {
        this.reportXdspyemx = reportXdspyemx;
    }

    public Integer getReportXdyecx() {
        return reportXdyecx;
    }

    public void setReportXdyecx(Integer reportXdyecx) {
        this.reportXdyecx = reportXdyecx;
    }

    public Integer getReportXdywytj() {
        return reportXdywytj;
    }

    public void setReportXdywytj(Integer reportXdywytj) {
        this.reportXdywytj = reportXdywytj;
    }

    public Integer getReportXdcx() {
        return reportXdcx;
    }

    public void setReportXdcx(Integer reportXdcx) {
        this.reportXdcx = reportXdcx;
    }

    public Integer getReportXsksmx() {
        return reportXsksmx;
    }

    public void setReportXsksmx(Integer reportXsksmx) {
        this.reportXsksmx = reportXsksmx;
    }

    public Integer getReportXskstj() {
        return reportXskstj;
    }

    public void setReportXskstj(Integer reportXskstj) {
        this.reportXskstj = reportXskstj;
    }

    public Integer getReportXsmx() {
        return reportXsmx;
    }

    public void setReportXsmx(Integer reportXsmx) {
        this.reportXsmx = reportXsmx;
    }

    public Integer getReportXspyetj() {
        return reportXspyetj;
    }

    public void setReportXspyetj(Integer reportXspyetj) {
        this.reportXspyetj = reportXspyetj;
    }

    public Integer getReportXstj() {
        return reportXstj;
    }

    public void setReportXstj(Integer reportXstj) {
        this.reportXstj = reportXstj;
    }

    public Integer getReportXsywytj() {
        return reportXsywytj;
    }

    public void setReportXsywytj(Integer reportXsywytj) {
        this.reportXsywytj = reportXsywytj;
    }

    public Integer getReportXywymx() {
        return reportXywymx;
    }

    public void setReportXywymx(Integer reportXywymx) {
        this.reportXywymx = reportXywymx;
    }

    public Integer getCwcgyf() {
        return cwcgyf;
    }

    public void setCwcgyf(Integer cwcgyf) {
        this.cwcgyf = cwcgyf;
    }

    public Integer getCwfkjs() {
        return cwfkjs;
    }

    public void setCwfkjs(Integer cwfkjs) {
        this.cwfkjs = cwfkjs;
    }

    public Integer getCwjyfy() {
        return cwjyfy;
    }

    public void setCwjyfy(Integer cwjyfy) {
        this.cwjyfy = cwjyfy;
    }

    public Integer getCwqtsr() {
        return cwqtsr;
    }

    public void setCwqtsr(Integer cwqtsr) {
        this.cwqtsr = cwqtsr;
    }

    public Integer getCwskjs() {
        return cwskjs;
    }

    public void setCwskjs(Integer cwskjs) {
        this.cwskjs = cwskjs;
    }

    public Integer getCwxjyh() {
        return cwxjyh;
    }

    public void setCwxjyh(Integer cwxjyh) {
        this.cwxjyh = cwxjyh;
    }

    public Integer getCwxsyf() {
        return cwxsyf;
    }

    public void setCwxsyf(Integer cwxsyf) {
        this.cwxsyf = cwxsyf;
    }

    public Integer getCwybfy() {
        return cwybfy;
    }

    public void setCwybfy(Integer cwybfy) {
        this.cwybfy = cwybfy;
    }

    public Integer getBaseCustomer() {
        return baseCustomer;
    }

    public void setBaseCustomer(Integer baseCustomer) {
        this.baseCustomer = baseCustomer;
    }

    public Integer getBaseEmployee() {
        return baseEmployee;
    }

    public void setBaseEmployee(Integer baseEmployee) {
        this.baseEmployee = baseEmployee;
    }

    public Integer getBaseProduct() {
        return baseProduct;
    }

    public void setBaseProduct(Integer baseProduct) {
        this.baseProduct = baseProduct;
    }

    public Integer getBaseStorage() {
        return baseStorage;
    }

    public void setBaseStorage(Integer baseStorage) {
        this.baseStorage = baseStorage;
    }

    public Integer getCgd() {
        return cgd;
    }

    public void setCgd(Integer cgd) {
        this.cgd = cgd;
    }

    public Integer getCgdd() {
        return cgdd;
    }

    public void setCgdd(Integer cgdd) {
        this.cgdd = cgdd;
    }

    public Integer getCgth() {
        return cgth;
    }

    public void setCgth(Integer cgth) {
        this.cgth = cgth;
    }

    public Integer getInitKcqc() {
        return initKcqc;
    }

    public void setInitKcqc(Integer initKcqc) {
        this.initKcqc = initKcqc;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getReportKcbjmx() {
        return reportKcbjmx;
    }

    public void setReportKcbjmx(Integer reportKcbjmx) {
        this.reportKcbjmx = reportKcbjmx;
    }

    public Integer getReportKcbs() {
        return reportKcbs;
    }

    public void setReportKcbs(Integer reportKcbs) {
        this.reportKcbs = reportKcbs;
    }

    public Integer getReportKcbsmx() {
        return reportKcbsmx;
    }

    public void setReportKcbsmx(Integer reportKcbsmx) {
        this.reportKcbsmx = reportKcbsmx;
    }

    public Integer getReportKcby() {
        return reportKcby;
    }

    public void setReportKcby(Integer reportKcby) {
        this.reportKcby = reportKcby;
    }

    public Integer getReportKcbymx() {
        return reportKcbymx;
    }

    public void setReportKcbymx(Integer reportKcbymx) {
        this.reportKcbymx = reportKcbymx;
    }

    public Integer getReportKcckmx() {
        return reportKcckmx;
    }

    public void setReportKcckmx(Integer reportKcckmx) {
        this.reportKcckmx = reportKcckmx;
    }

    public Integer getReportKccpck() {
        return reportKccpck;
    }

    public void setReportKccpck(Integer reportKccpck) {
        this.reportKccpck = reportKccpck;
    }

    public Integer getReportKccprk() {
        return reportKccprk;
    }

    public void setReportKccprk(Integer reportKccprk) {
        this.reportKccprk = reportKccprk;
    }

    public Integer getReportKccpsxx() {
        return reportKccpsxx;
    }

    public void setReportKccpsxx(Integer reportKccpsxx) {
        this.reportKccpsxx = reportKccpsxx;
    }

    public Integer getReportKcpd() {
        return reportKcpd;
    }

    public void setReportKcpd(Integer reportKcpd) {
        this.reportKcpd = reportKcpd;
    }

    public Integer getReportKcpdmx() {
        return reportKcpdmx;
    }

    public void setReportKcpdmx(Integer reportKcpdmx) {
        this.reportKcpdmx = reportKcpdmx;
    }

    public Integer getReportKcrkmx() {
        return reportKcrkmx;
    }

    public void setReportKcrkmx(Integer reportKcrkmx) {
        this.reportKcrkmx = reportKcrkmx;
    }

    public Integer getReportKcspye() {
        return reportKcspye;
    }

    public void setReportKcspye(Integer reportKcspye) {
        this.reportKcspye = reportKcspye;
    }

    public Integer getReportKcyk() {
        return reportKcyk;
    }

    public void setReportKcyk(Integer reportKcyk) {
        this.reportKcyk = reportKcyk;
    }

    public Integer getReportKcykmx() {
        return reportKcykmx;
    }

    public void setReportKcykmx(Integer reportKcykmx) {
        this.reportKcykmx = reportKcykmx;
    }

    public Integer getStorageCpck() {
        return storageCpck;
    }

    public void setStorageCpck(Integer storageCpck) {
        this.storageCpck = storageCpck;
    }

    public Integer getStorageKcbj() {
        return storageKcbj;
    }

    public void setStorageKcbj(Integer storageKcbj) {
        this.storageKcbj = storageKcbj;
    }

    public Integer getStorageKcbs() {
        return storageKcbs;
    }

    public void setStorageKcbs(Integer storageKcbs) {
        this.storageKcbs = storageKcbs;
    }

    public Integer getStorageKcby() {
        return storageKcby;
    }

    public void setStorageKcby(Integer storageKcby) {
        this.storageKcby = storageKcby;
    }

    public Integer getStoragePdgl() {
        return storagePdgl;
    }

    public void setStoragePdgl(Integer storagePdgl) {
        this.storagePdgl = storagePdgl;
    }

    public Integer getStorageSprk() {
        return storageSprk;
    }

    public void setStorageSprk(Integer storageSprk) {
        this.storageSprk = storageSprk;
    }

    public Integer getStorageSxxz() {
        return storageSxxz;
    }

    public void setStorageSxxz(Integer storageSxxz) {
        this.storageSxxz = storageSxxz;
    }

    public Integer getStorageYkgl() {
        return storageYkgl;
    }

    public void setStorageYkgl(Integer storageYkgl) {
        this.storageYkgl = storageYkgl;
    }

    public Integer getSystemGsxx() {
        return systemGsxx;
    }

    public void setSystemGsxx(Integer systemGsxx) {
        this.systemGsxx = systemGsxx;
    }

    public Integer getSystemJsxx() {
        return systemJsxx;
    }

    public void setSystemJsxx(Integer systemJsxx) {
        this.systemJsxx = systemJsxx;
    }

    public Integer getSystemXgmm() {
        return systemXgmm;
    }

    public void setSystemXgmm(Integer systemXgmm) {
        this.systemXgmm = systemXgmm;
    }

    public Integer getSystemXtrz() {
        return systemXtrz;
    }

    public void setSystemXtrz(Integer systemXtrz) {
        this.systemXtrz = systemXtrz;
    }

    public Integer getSystemYhxx() {
        return systemYhxx;
    }

    public void setSystemYhxx(Integer systemYhxx) {
        this.systemYhxx = systemYhxx;
    }

    public Integer getSystemxlkgl() {
        return systemxlkgl;
    }

    public void setSystemxlkgl(Integer systemxlkgl) {
        this.systemxlkgl = systemxlkgl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getXsbj() {
        return xsbj;
    }

    public void setXsbj(Integer xsbj) {
        this.xsbj = xsbj;
    }

    public Integer getXsd() {
        return xsd;
    }

    public void setXsd(Integer xsd) {
        this.xsd = xsd;
    }

    public Integer getXsdd() {
        return xsdd;
    }

    public void setXsdd(Integer xsdd) {
        this.xsdd = xsdd;
    }

    public Integer getXssptj() {
        return xssptj;
    }

    public void setXssptj(Integer xssptj) {
        this.xssptj = xssptj;
    }

    public Integer getXsth() {
        return xsth;
    }

    public void setXsth(Integer xsth) {
        this.xsth = xsth;
    }
    //提供给程序用
}
