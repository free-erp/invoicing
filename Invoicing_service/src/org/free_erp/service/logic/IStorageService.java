/* * Copyright 2013, TengJianfa , and other individual contributors as indicated * by the @authors tag. See the copyright.txt in the distribution for a * full listing of individual contributors. * * This is free software; you can redistribute it and/or modify it * under the terms of the GNU Lesser General Public License as * published by the Free Software Foundation; either version 2.1 of * the License, or (at your option) any later version. * * This software is distributed in the hope that it will be useful, * but WITHOUT ANY WARRANTY; without even the implied warranty of * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU * Lesser General Public License for more details. * * You should have received a copy of the GNU Lesser General Public * License along with this software; if not, write to the Free * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA * 02110-1301 USA, or see the FSF site: http://www.fsf.org. */package org.free_erp.service.logic;import java.util.List;import org.free_erp.service.entity.base.Company;import org.free_erp.service.entity.base.Product;import org.free_erp.service.entity.base.Storage;import org.free_erp.service.entity.storage.CheckStorageDetailPo;import org.free_erp.service.entity.storage.CheckStoragePo;import org.free_erp.service.entity.storage.InStorageDetailPo;import org.free_erp.service.entity.storage.InStoragePo;import org.free_erp.service.entity.storage.InitialStorageDetailPo;import org.free_erp.service.entity.storage.InitialStoragePo;import org.free_erp.service.entity.storage.LossStorageDetailPo;import org.free_erp.service.entity.storage.LossStoragePo;import org.free_erp.service.entity.storage.MinMaxStorageDetailPo;import org.free_erp.service.entity.storage.MinMaxStoragePo;import org.free_erp.service.entity.storage.MoveStorageDetailPo;import org.free_erp.service.entity.storage.MoveStoragePo;import org.free_erp.service.entity.storage.OutStorageDetailPo;import org.free_erp.service.entity.storage.OutStoragePo;import org.free_erp.service.entity.storage.OutflowStorageDetailPo;import org.free_erp.service.entity.storage.OutflowStoragePo;import org.free_erp.service.entity.storage.PriceStorageDetailPo;import org.free_erp.service.entity.storage.PriceStoragePo;import org.free_erp.service.entity.storage.StorageAlertPo;import org.free_erp.service.entity.storage.StorageChangeLog;import org.free_erp.service.entity.storage.StorageProductPo;import org.free_erp.service.entity.vo.StorageQueryVo;import org.free_erp.service.exception.InitialLackedException;import org.free_erp.service.exception.ProductInitializedException;public interface IStorageService {    //public double getStorageStockPrice(Company company, int s);    public Storage getStorage(int id);    public List<Storage> getStorages(Company company);    public List<Storage> getUsableStorages(Company company);//liufei    public void addStorage(Storage storage);    public void removeStorage(Storage storage);    public void modifiyStorage(Storage storage);    public void saveStorage(Storage storage);    public void saveStorageAlertPo(StorageAlertPo po);    public void saveStorageProductPo(StorageProductPo po);    //instorage    public void saveInStorageForm(InStoragePo po);    public void deleteInStorageForm(InStoragePo po);    public List<InStoragePo> getAllInStorageForms(Company company);    public List<InStoragePo> getAllDraftInStorageForms(Company company);	public List<InStoragePo> findInStorageForms(StorageQueryVo vo);	public List<InStorageDetailPo> findInStorageDetails(StorageQueryVo vo);    //initial Storage    public void saveInitialStorageForm(InitialStoragePo po);    public void deleteInitialStorageForm(InitialStoragePo po);    public List<InitialStoragePo> getAllInitialStorageForms(Company company);    public List<InitialStoragePo> getAllDraftInitialStorageForms(Company company);	public List<InitialStoragePo> findInitialStorageForms(StorageQueryVo vo);	public List<InitialStorageDetailPo> findInitialStorageDetails(StorageQueryVo vo);    //LossStorage     public void saveLossStorageForm(LossStoragePo po);    public void deleteLossStorageForm(LossStoragePo po);    public List<LossStoragePo> getAllLossStorageForms(Company company);    public List<LossStoragePo> getAllDraftLossStorageForms(Company company);	public List<LossStoragePo> findLossStorageForms(StorageQueryVo vo);	public List<LossStorageDetailPo> findLossStorageDetails(StorageQueryVo vo);        //MinMaxStorage    public void saveMinMaxStorageForm(MinMaxStoragePo po);    public void deleteMinMaxStorageForm(MinMaxStoragePo po);    public List<MinMaxStoragePo> getAllMinMaxStorageForms(Company company);    public List<MinMaxStoragePo> getAllDraftMinMaxStorageForms(Company company);	public List<MinMaxStoragePo> findMinMaxStorageForms(StorageQueryVo vo);		public List<MinMaxStorageDetailPo> findMinMaxStorageDetails(StorageQueryVo vo);       //MoveStorage    public void saveMoveStorageForm(MoveStoragePo po);    public void deleteMoveStorageForm(MoveStoragePo po);    public List<MoveStoragePo> getAllMoveStorageForms(Company company);    public List<MoveStoragePo> getAllDraftMoveStorageForms(Company company);	public List<MoveStoragePo> findMoveStorageForms(StorageQueryVo vo);	public List<MoveStorageDetailPo> findMoveStorageDetails(StorageQueryVo vo);	    //OutStorage    public void saveOutStorageForm(OutStoragePo po);    public void deleteOutStorageForm(OutStoragePo po);    public List<OutStoragePo> getAllOutStorageForms(Company company);    public List<OutStoragePo> getAllDraftOutStorageForms(Company company);	public List<OutStoragePo> findOutStorageForms(StorageQueryVo vo);	public List<OutStorageDetailPo> findOutStorageDetails(StorageQueryVo vo);         //OutflowStorage    public void saveOutflowStorageForm(OutflowStoragePo po);    public void deleteOutflowStorageForm(OutflowStoragePo po);    public List<OutflowStoragePo> getAllOutflowStorageForms(Company company);    public List<OutflowStoragePo> getAllDraftOutflowStorageForms(Company company);	public List<OutflowStoragePo> findOutflowStorageForms(StorageQueryVo vo);	public List<OutflowStorageDetailPo> findOutflowStorageDetails(StorageQueryVo vo);    //PriceStorage    public void savePriceStorageForm(PriceStoragePo po);    public void deletePriceStorageForm(PriceStoragePo po);    public List<PriceStoragePo> getAllPriceStorageForms(Company company);    public List<PriceStoragePo> getAllDraftPriceStorageForms(Company company);	public List<PriceStoragePo> findPriceStorageForms(StorageQueryVo vo);		public List<PriceStorageDetailPo> findPriceStorageDetails(StorageQueryVo vo);     //CheckStorage    public void saveCheckStorageForm(CheckStoragePo po);    public void deleteCheckStorageForm(CheckStoragePo po);    public List<CheckStoragePo> getAllCheckStorageForms(Company company);    public List<CheckStoragePo> getAllDraftCheckStorageForms(Company company);	public List<CheckStoragePo> findCheckStorageForms(StorageQueryVo vo);    public List<CheckStorageDetailPo> findCheckStorageDetails(StorageQueryVo vo);	public List<StorageProductPo> findStorageProductForms(StorageQueryVo vo);    public List<StorageChangeLog> findStorageChangeLog(StorageQueryVo vo);    public List<StorageProductPo> findAllStorageProduct(Storage storage);	//logic!	public void passInitialStorageForm(InitialStoragePo po) throws ProductInitializedException;	public void passCheckForm(CheckStoragePo po) throws InitialLackedException;	public void passInStorageForm(InStoragePo po) throws InitialLackedException;	public void passLossStorageForm(LossStoragePo po) throws InitialLackedException;	public void passMinMaxStorageForm(MinMaxStoragePo po) throws InitialLackedException;	public void passMoveStorageForm(MoveStoragePo po) throws InitialLackedException;	public void passOutStorageForm(OutStoragePo po) throws InitialLackedException;	public void passOutflowStorageForm(OutflowStoragePo po) throws InitialLackedException;	public void passPriceStorageForm(PriceStoragePo po) throws InitialLackedException;	public void passCommonStorageForm(Object po) throws InitialLackedException,ProductInitializedException;    public double getStorageProductAmount(Storage storage,Product product);//liufei    public double getStorageStockPrice(Storage storage,Product product);//liufei    public String getStorageStockShelf(Storage storage,Product product);//liufei    public List<StorageAlertPo> findStorageAlertPos(Company company);    public double getStockTotalMoney(Company company);    public double getDocumentsTotalMoney(Object po);}