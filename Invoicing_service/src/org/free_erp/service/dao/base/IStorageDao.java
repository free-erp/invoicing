package org.free_erp.service.dao.base;import java.util.List;import org.free_erp.service.entity.base.Company;import org.free_erp.service.entity.base.Product;import org.free_erp.service.entity.base.Storage;import org.free_erp.service.entity.storage.CheckStorageDetailPo;import org.free_erp.service.entity.storage.CheckStoragePo;import org.free_erp.service.entity.storage.InStorageDetailPo;import org.free_erp.service.entity.storage.InStoragePo;import org.free_erp.service.entity.storage.InitialStorageDetailPo;import org.free_erp.service.entity.storage.InitialStoragePo;import org.free_erp.service.entity.storage.LossStorageDetailPo;import org.free_erp.service.entity.storage.LossStoragePo;import org.free_erp.service.entity.storage.MinMaxStorageDetailPo;import org.free_erp.service.entity.storage.MinMaxStoragePo;import org.free_erp.service.entity.storage.MoveStorageDetailPo;import org.free_erp.service.entity.storage.MoveStoragePo;import org.free_erp.service.entity.storage.OutStorageDetailPo;import org.free_erp.service.entity.storage.OutStoragePo;import org.free_erp.service.entity.storage.OutflowStorageDetailPo;import org.free_erp.service.entity.storage.OutflowStoragePo;import org.free_erp.service.entity.storage.PriceStorageDetailPo;import org.free_erp.service.entity.storage.PriceStoragePo;import org.free_erp.service.entity.storage.StorageAlertPo;import org.free_erp.service.entity.storage.StorageChangeLog;import org.free_erp.service.entity.storage.StorageProductPo;import org.free_erp.service.entity.vo.StorageQueryVo;import org.free_erp.service.exception.InitialLackedException;import org.free_erp.service.exception.ProductInitializedException;public interface IStorageDao{    public Storage findStorage(int id);	public List<Storage> findStorages(Company company);    public List<Storage> findUsableStorages(Company company);//liufei    public void saveStorageAlertPo(StorageAlertPo po);    public void saveStorageProductPo(StorageProductPo po);	public void saveStorage(Storage storage);	public void deleteStorage(Storage storage);	public List<InStoragePo> findInStorages(Company company);//instorage    public void saveInStorageForm(InStoragePo po);    public void deleteInStorageForm(InStoragePo po);    public List<InStoragePo> getAllInStorageForms(Company company);    //initial Storage    public void saveInitialStorageForm(InitialStoragePo po);    public void deleteInitialStorageForm(InitialStoragePo po);    public List<InitialStoragePo> getAllInitialStorageForms(Company company);    //LossStorage     public void saveLossStorageForm(LossStoragePo po);    public void deleteLossStorageForm(LossStoragePo po);    public List<LossStoragePo> getAllLossStorageForms(Company company);    //MinMaxStorage    public void saveMinMaxStorageForm(MinMaxStoragePo po);    public void deleteMinMaxStorageForm(MinMaxStoragePo po);    public List<MinMaxStoragePo> getAllMinMaxStorageForms(Company company);     //MoveStorage    public void saveMoveStorageForm(MoveStoragePo po);    public void deleteMoveStorageForm(MoveStoragePo po);    public List<MoveStoragePo> getAllMoveStorageForms(Company company);     //OutStorage    public void saveOutStorageForm(OutStoragePo po);    public void deleteOutStorageForm(OutStoragePo po);    public List<OutStoragePo> getAllOutStorageForms(Company company);     //OutflowStorage    public void saveOutflowStorageForm(OutflowStoragePo po);    public void deleteOutflowStorageForm(OutflowStoragePo po);    public List<OutflowStoragePo> getAllOutflowStorageForms(Company company);      //PriceStorage    public void savePriceStorageForm(PriceStoragePo po);    public void deletePriceStorageForm(PriceStoragePo po);    public List<PriceStoragePo> getAllPriceStorageForms(Company company);      //CheckStorage    public void saveCheckStorage(CheckStoragePo po);    public void deleteCheckStorage(CheckStoragePo po);    public List<CheckStoragePo> getAllCheckStorages(Company company);	public StorageProductPo getStorageProduct(Company company, String productNo);	//public StorageProductPo getStorageProduct(Integer id);    public double getDocumentsTotalMoney(Product product,Company company);	public List<StorageProductPo> getStorageProducts(Company company);	public List<StorageProductPo> getStorageProducts(Company company, String name);	public StorageProductPo findStorageProduct(Storage storage, Product product);    public StorageAlertPo findStorageAlert(Storage storage,Product product);//liufei	public void saveStorageProduct(StorageProductPo po);    public void saveStorageAlert(StorageAlertPo po);//liufei	public void passInitialStorageForm(InitialStoragePo po) throws ProductInitializedException;	public void passCheckForm(CheckStoragePo po) throws InitialLackedException;	public void passInStorageForm(InStoragePo po) throws InitialLackedException;	public void passLossStorageForm(LossStoragePo po) throws InitialLackedException;	public void passMinMaxStorageForm(MinMaxStoragePo po) throws InitialLackedException;	public void passMoveStorageForm(MoveStoragePo po) throws InitialLackedException;	public void passOutStorageForm(OutStoragePo po) throws InitialLackedException;	public void passOutflowStorageForm(OutflowStoragePo po) throws InitialLackedException;	public void passPriceStorageForm(PriceStoragePo po) throws InitialLackedException;	public List<CheckStoragePo> findCheckStorageForms(StorageQueryVo vo);	public List<InStoragePo> findInStorageForms(StorageQueryVo vo);		public List<InitialStoragePo> findInitialStorageForms(StorageQueryVo vo);	public List<LossStoragePo> findLossStorageForms(StorageQueryVo vo);	public List<MinMaxStoragePo> findMinMaxStorageForms(StorageQueryVo vo);	public List<MoveStoragePo> findMoveStorageForms(StorageQueryVo vo);	public List<OutStoragePo> findOutStorageForms(StorageQueryVo vo);	public List<OutflowStoragePo> findOutflowStorageForms(StorageQueryVo vo);	public List<PriceStoragePo> findPriceStorageForms(StorageQueryVo vo);		public List<StorageProductPo> findStorageProducts(StorageQueryVo vo);    public List<StorageChangeLog> findStorageChangeLog(StorageQueryVo vo);	public List<InStorageDetailPo> findInStorageDetails(StorageQueryVo vo);	public List<InitialStorageDetailPo> findInitialStorageDetails(StorageQueryVo vo);	public List<LossStorageDetailPo> findLossStorageDetails(StorageQueryVo vo);	public List<MinMaxStorageDetailPo> findMinMaxStorageDetails(StorageQueryVo vo);	public List<MoveStorageDetailPo> findMoveStorageDetails(StorageQueryVo vo);	public List<OutStorageDetailPo> findOutStorageDetails(StorageQueryVo vo);    public List<CheckStorageDetailPo> findCheckStorageDetails(StorageQueryVo vo);	public List<OutflowStorageDetailPo> findOutflowStorageDetails(StorageQueryVo vo);    	public List<PriceStorageDetailPo> findPriceStorageDetails(StorageQueryVo vo);    public LossStorageDetailPo findLossStorageDetail(Storage storage, int productId);//liufei    public OutflowStorageDetailPo findOutflowStorageDetail(Storage storage, int productId);//liufei    public CheckStorageDetailPo findCheckStorageDetail(Storage storage, int productId);//liufei    public void saveLossStorageDetail(LossStorageDetailPo lossStorageDetailPo);//liufei    public void saveOutflowStorageDetail(OutflowStorageDetailPo outflowStorageDetailPo);//liufei    public void saveCheckStorageDetail(CheckStorageDetailPo checkStorageDetailPo);//liufei        public List<StorageProductPo> findAllStorageProduct(Storage storage);    public List<StorageAlertPo> findStorageAlertPos(Company company);    public List<StorageProductPo> findAllStorageProduct(Company company);}