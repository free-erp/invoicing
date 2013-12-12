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

import java.util.Collection;
import java.util.List;

import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;

public interface IProductService
{
	public List<Product> getProducts(Company company);

    public List<Product> getUsableProducts(Company company);//liufei

	public ProductCatalog getDefaultCatalog(Company company);

    public ProductCatalog findProductCatalog(int id);//liufei

    public int getProductCatalogID(Company company,String number);//liufei

    public ProductCatalog getProductCatalog(Company company,String name);//liufei


    public Product getProduct(Company company,Product product);
    public List<ProductCatalog> getCatalogs(Company company);

	public void saveProduct(Product product);

	public void saveProducts(Collection<Product> products);

	public void saveCatalog(ProductCatalog catalog);

    public ProductCatalog saveProductCatalog(ProductCatalog catalog);

    public void insertProductCatalog(Company company,String number,String name);//liufei

	public void saveCatalogs(Collection<ProductCatalog> catalogs);

	public void deleteProduct(Product product);

	public void deleteProducts(Collection<Product> product);

	public void deleteCatalog(ProductCatalog catalog);

	public void deleteCatalogs(Collection<ProductCatalog> catalogs);

	public String getString();

	public List<ProductCatalog> getStrings(Company id);

	// 针对webservice,所有的save方法都加上companyid,
	// 因为Company属性在配置中是延迟加载;传id,而不传具体的对象是为了网络流量的考虑
	public void saveProduct(int companyId, Product product);

	public void saveProducts(int companyId, Collection<Product> products);

	public void saveCatalog(int companyId, ProductCatalog catalog);

	public void saveCatalogs(int companyId, Collection<ProductCatalog> catalogs);

    public boolean isExitProductCatalog(Company company, String catalogNumber);//liufei

    public Product getProduct(Company company, int productID);

    public Product getProduct(int id);

    public List<Product> findProducts(DataBaseQueryVO vo);
}
