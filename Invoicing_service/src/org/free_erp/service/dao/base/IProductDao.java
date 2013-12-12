package org.free_erp.service.dao.base;

import java.util.Collection;
import java.util.List;

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

public interface IProductDao
{
	public List<Product> findAllProduct(Company company);

    public List<Product> findUsableProducts(Company company);//liufei

	public List<ProductCatalog> findAllProductCatalog(Company company);

	public ProductCatalog findProductCatalog(int id);//liufei
    public Product getProduct(Company company, int id);//tzl

    public int getProductCatalogID(Company company,String number);//liufei

	public Product findProduct(int id);

	public void saveProduct(Product product);

	public void saveProductCatalog(ProductCatalog catalog);

    public ProductCatalog getProductCatalog(Company company,String name);//liufei

    public void insertProductCatalog(Company company,String number,String name);//liufei

	public void saveProductCatalogs(Collection<ProductCatalog> catalogs);

	public void saveProducts(Collection<Product> products);

	public void deleteProduct(Product product);

	public void deleteProducts(Collection<Product> products);

	public void deleteProductCatalog(ProductCatalog catalog);

	public void deleteProductCatalogs(Collection<ProductCatalog> catalogs);

    public boolean isExitProductCatalog(Company company, String catalogNumber);//liufei

    public List<Product> findProducts(DataBaseQueryVO vo);

}
