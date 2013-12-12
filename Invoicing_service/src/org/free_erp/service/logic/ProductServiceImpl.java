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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.free_erp.service.constants.NumberConstants;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.dao.base.IProductDao;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.vo.DataBaseQueryVO;
import org.free_erp.service.exception.LogicException;

public class ProductServiceImpl implements IProductService
{
	public List<ProductCatalog> getCatalogs(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findAllProductCatalog(company);
	}
	private IProductDao dao;
	private ICompanyService companyService;
    private ISystemManageService systemManageService;
	public ICompanyService getCompanyService()
	{
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService)
	{
		this.companyService = companyService;
	}

    public ISystemManageService getSystemManageService() {
        return systemManageService;
    }

    public void setSystemManageService(ISystemManageService systemManageService) {
        this.systemManageService = systemManageService;
    }
    
	public void deleteCatalog(ProductCatalog catalog)
	{
		// TODO Auto-generated method stub
		dao.deleteProductCatalog(catalog);
	}
	public void deleteProduct(Product product)
	{
        try
        {
            this.dao.deleteProduct(product);
        }
        catch(Exception ex)
        {
            throw new LogicException("该商品存在于其它单据中，不能被删除!");
        }
	}
	public void saveCatalog(ProductCatalog catalog)
	{
		// TODO Auto-generated method stub
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.PRO_CATALOG_NUM) + systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.PRO_CATALOG_NUM);
            catalog.setNumber(number);
        }
		dao.saveProductCatalog(catalog);
	}

    public ProductCatalog getProductCatalog(Company company,String name)//liufei
    {
        return this.dao.getProductCatalog(company, name);
    }

    public ProductCatalog saveProductCatalog(ProductCatalog catalog)//liufei
    {
        if(catalog.getNumber() == null || catalog.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(catalog.getCompany(), NumberConstants.PRO_CATALOG_NUM) + systemManageService.getAutoNumber(catalog.getCompany(), NumberConstants.PRO_CATALOG_NUM);
            catalog.setNumber(number);
        }
		dao.saveProductCatalog(catalog);
        return catalog;
    }

    public void insertProductCatalog(Company company,String number,String name)//liufei
    {
        this.dao.insertProductCatalog(company, number, name);
    }

	public void saveProduct(Product product)
	{
		// TODO Auto-generated method stub
		if (product.getCompany() == null)
		{
			throw new RuntimeException("web service中请使用带companyId的方法");
		}
//        ProductCatalog catalog = product.getCatalog();
//        if(!this.isExitProductCatalog(product.getCompany(), catalog!=null?product.getCatalog().getNumber():null))
//        {
//            throw new LogicException("该类别不存在或已被删除，请先刷新基础信息，再重试！");
//        }
        if(product.getNumber() == null || product.getNumber().equals(""))
        {
            String number = systemManageService.getOptionType(product.getCompany(), NumberConstants.PRO_NUM) + systemManageService.getAutoNumber(product.getCompany(), NumberConstants.PRO_NUM);
            product.setNumber(number);
        }
		dao.saveProduct(product);
	}

	public void saveProduct(int companyId, Product product)
	{
		if (product.getCompany() == null)
		{
			product.setCompany(this.companyService.getCompany(companyId));
		}
		this.saveProduct(product);
	}

	public List<Product> getProducts(Company company)
	{
		// TODO Auto-generated method stub
		return this.dao.findAllProduct(company);
	}

    public List<Product> getUsableProducts(Company company)//liufei
    {
        return this.dao.findUsableProducts(company);
    }

	public IProductDao getDao()
	{
		return dao;
	}
	public void setDao(IProductDao dao)
	{
		this.dao = dao;
	}
	public void deleteCatalogs(Collection<ProductCatalog> catalogs)
	{
		// TODO Auto-generated method stub
		this.dao.deleteProductCatalogs(catalogs);
	}
	public void deleteProducts(Collection<Product> products)
	{
		// TODO Auto-generated method stub
		this.dao.deleteProducts(products);
	}
	public void saveCatalogs(Collection<ProductCatalog> catalogs)
	{
		// TODO Auto-generated method stub
		this.dao.saveProductCatalogs(catalogs);
	}
	public void saveProducts(Collection<Product> products)
	{
		// TODO Auto-generated method stub
		this.dao.saveProducts(products);
	}

	public String getString()
	{
		return "test!";
	}
	public ProductCatalog getDefaultCatalog(Company company)
	{
		// TODO Auto-generated method stub
		ProductCatalog catalog = this.dao.findProductCatalog(ServiceConstants.DEFAULT_PRODUCT_CATALOG);
		if (catalog == null)
		{
			catalog = new ProductCatalog();
			catalog.setId(ServiceConstants.DEFAULT_PRODUCT_CATALOG);
			catalog.setCompany(companyService.getReservedCompany());
			//Main.getServiceManager().getProductService().saveCatalog(catalog);
		}
		return catalog;
	}

    public ProductCatalog findProductCatalog(int id)//liufei
    {
        return this.dao.findProductCatalog(id);
    }

    public int getProductCatalogID(Company company,String number)//liufei
    {
        return this.dao.getProductCatalogID(company,number);
    }

	public List<ProductCatalog> getStrings(Company id)
	{
		// TODO Auto-generated method stub
		ArrayList<ProductCatalog> a = new ArrayList<ProductCatalog>();
		ProductCatalog catalog = new ProductCatalog();
		catalog.setId(1001);
		a.add(catalog);
		return a;
	}

	public void saveCatalog(int companyId, ProductCatalog catalog)
	{
		// TODO Auto-generated method stub
		if (catalog.getCompany() == null)
		{
			catalog.setCompany(this.companyService.getCompany(companyId));
		}
		this.saveCatalog(catalog);
	}

	public void saveCatalogs(int companyId, Collection<ProductCatalog> catalogs)
	{
		// TODO Auto-generated method stub
		Company company = this.getCompanyService().getCompany(companyId);
		for(ProductCatalog c:catalogs)
		{
			if (c.getCompany() == null)
			{
				c.setCompany(company);
			}
		}
		this.saveCatalogs(catalogs);
	}

	public void saveProducts(int companyId, Collection<Product> products)
	{
		// TODO Auto-generated method stub
		Company company = this.getCompanyService().getCompany(companyId);
		for(Product p:products)
		{
			if (p.getCompany() == null)
			{
				p.setCompany(company);
			}
		}
		this.saveProducts(products);
	}

    public boolean isExitProductCatalog(Company company, String catalogNumber)//liufei
    {
        return this.dao.isExitProductCatalog(company, catalogNumber);
    }
   

    public Product getProduct(Company company, Product product) {
      int id= product.getId();
             return this.dao.getProduct(company,id);
    }

    public Product getProduct(Company company, int productID)
    {
        return this.dao.getProduct(company,productID);
    }

    public List<Product> findProducts(DataBaseQueryVO vo)
    {
        return this.dao.findProducts(vo);
    }

    public Product getProduct(int id)
    {
        return this.dao.findProduct(id);
    }
}
