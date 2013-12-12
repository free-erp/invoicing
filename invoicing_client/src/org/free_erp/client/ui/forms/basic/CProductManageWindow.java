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

package org.free_erp.client.ui.forms.basic;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.forms.CCatalogDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ExcelImporter;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.client.ui.util.RunException;
import com.jdatabeans.beans.data.DefaultDataSource;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.ProductCatalog;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.IProductService;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import jxl.read.biff.BiffException;
/**
 *
 * @Changer liufei
 */
public class CProductManageWindow extends CBaseInfoManageWindow
{
	private CProductInfoDialog dialog;
	public CProductManageWindow(String title)
	{
		super(title);
		this.initTableColumns();
		initDatas();
        this.newButton.setText("新增商品(&N)");
        this.removeButton.setText("删除商品(&D)");
        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改商品信息(&M)");
	}
    
	protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		
		column.setHeaderText("类别编号");
		column.setColumnName("catalog.number");
		column.setWidth(60);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("类别名称");
		column.setColumnName("catalog.name");
		column.setWidth(80);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("编号");
		column.setColumnName("number");
		column.setWidth(60);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("名称");
		column.setColumnName("name");
		column.setWidth(140);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("规格");
		column.setColumnName("spec");
		column.setWidth(80);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

         column = new JDataTableColumn();
		column.setHeaderText("单位");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("smallUnit");
		column.setWidth(40);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("生产厂家");
		column.setColumnName("factory");
        column.setAlignmentX(JLabel.LEFT);
		column.setWidth(120);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("产地");
		column.setColumnName("area");
		column.setWidth(40);
        column.setAlignmentX(JLabel.LEFT);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("停用");
		column.setColumnName("status");
		column.setWidth(40);
		column.setValueType(Boolean.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
        
		column.setHeaderText("采购价");
		column.setColumnName("stockPrice");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
        
        column.setHeaderText("零售价");
		column.setColumnName("retailPrice");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
        column = new JDataTableColumn();

        column = new JDataTableColumn();
		column.setHeaderText("批发价1");
		column.setColumnName("price0");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("批发价2");
		column.setColumnName("price1");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("批发价3");
		column.setColumnName("price2");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);
        column = new JDataTableColumn();
		column.setHeaderText("批发价4");
		column.setColumnName("price3");
		column.setValueType(Currency.class);
		columnModel.addColumn(column);

         column = new JDataTableColumn();
		column.setHeaderText("备注");
        column.setAlignmentX(JLabel.LEFT);
		column.setColumnName("comments");
		column.setWidth(200);
		columnModel.addColumn(column);
//        column = new JDataTableColumn();
//		column.setHeaderText("批发价5");
//		column.setColumnName("price4");
//		column.setValueType(Currency.class);
//		columnModel.addColumn(column);
//
//        column = new JDataTableColumn();
//		column.setHeaderText("批发价6");
//		column.setColumnName("price5");
//		column.setValueType(Currency.class);
//		columnModel.addColumn(column);
		
//        column = new JDataTableColumn();
//		column.setHeaderText("助记码");
//		column.setColumnName("code");
//		column.setWidth(50);
//		columnModel.addColumn(column);
		
	}
    @Override
	protected void initDatas()
	{
//        long t1 = System.currentTimeMillis();
        IDataSource model = this.dataTable.getDataSource();
		List<Product> products = Main.getMainFrame().getObjectsPool().getProducts();
        //
//        long t2 = System.currentTimeMillis();
//        System.out.println("database:" + (t2 - t1));
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Product product:products)
		{
			IDataRow dataRow = new ObjectDataRow(product, "id", dbSupport);
			dataRows.add(dataRow);
		}
        model.insertDataRows(dataRows);
        //Main.getMainFrame().getDataBaseObjectPool().refreshProducts();
	}
	@Override
	protected void doAdd()
	{
        this.dialog = new CProductInfoDialog(this, this, this.dataSource, dbSupport);
        if (this.dialog.newDataRow())
        {
            dialog.setVisible(true);
        }
        this.dataSource.clearTempDataRows();
	}
	@Override
	protected void doEdit()
	{
		// TODO Auto-generated method stub
      
            if (this.dataTable.getSelectedRow() < 0)
            {
                MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
                return;
            }
            this.dialog = new CProductInfoDialog(this, this, this.dataSource,dbSupport);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
	  
    }
    @Override
    protected void updateDatas()
    {
        Main.getMainFrame().getDataBaseObjectPool().refreshProducts();
        Main.getMainFrame().getDataBaseObjectPool().refreshUsableProducts();
    }
	@Override
	protected void doImport()
	{
        Company company = Main.getMainFrame().getCompany();
        IProductService service = Main.getServiceManager().getProductService();
        int column = 0;
        int row = 0;
        String errorMessage = "";
        List<Product> products = new ArrayList();
        try
        {
            JFileChooser chooser = new JFileChooser();
            //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setApproveButtonText("导入");
			chooser.setDialogTitle("选取导入文件");
            chooser.setFileFilter(new FileFilter(){
				@Override
				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(".xls") || f.isDirectory())
					{
						return true;
					}
					return false;
				}
				@Override
				public String getDescription() {
					return "Excel(*.xls)文件";
				}
			}
			);
            int result = chooser.showOpenDialog(Main.getMainFrame());
            String filePath = "";
            if(result == JFileChooser.APPROVE_OPTION)
            {
                filePath = chooser.getSelectedFile().getPath();
                ExcelImporter excelImporter = new ExcelImporter(filePath);
                int i = 1;
                boolean flag = excelImporter.isStop(i);
                //IDataSource model = this.dataTable.getDataSource();
                //HashSet<IDataRow> dataRows = new HashSet<IDataRow>();
                while(flag)
                {
                    row = i;
                    String typeName = excelImporter.getCellText(0,i,"S");
                    if(typeName.length() > 32)
                    {
                        typeName = typeName.substring(0, 32);
                    }
                    String proNumber = excelImporter.getCellText(1, i, "S");
                    if(proNumber.length() > 32)
                    {
                        proNumber = proNumber.substring(0, 32);
                    }
                    String proName = excelImporter.getCellText(2,i,"S");
                    if(proName.length() > 32)
                    {
                        proName = proName.substring(0, 32);
                    }
                    String shortname = excelImporter.getCellText(3,i,"S");
                    if(shortname.length() > 32)
                    {
                        shortname = shortname.substring(0, 32);
                    }
                    String helpcode = excelImporter.getCellText(4,i,"S");
                    if(helpcode.length() > 10)
                    {
                        helpcode = helpcode.substring(0, 10);
                    }
                    String alias = excelImporter.getCellText(5,i,"S");
                    if(alias.length() > 30)
                    {
                        alias = alias.substring(0, 30);
                    }
                    String spec = excelImporter.getCellText(6,i,"S");
                    if(spec.length() > 32)
                    {
                        spec = spec.substring(0, 32);
                    }
                    String barcode = excelImporter.getCellText(7,i,"S");
                    if(barcode.length() > 13)
                    {
                        barcode = barcode.substring(0, 13);
                    }
                    long tax = 0;
                    try
                    {
                        column = 8;
                        errorMessage = "-- 税率：请输入整数！";
                        tax = Integer.valueOf(excelImporter.getCellText(8,i,"L"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }

                    String proArea = excelImporter.getCellText(9,i,"S");
                    if(proArea.length() > 30)
                    {
                        proArea = proArea.substring(0, 30);
                    }
                    String factory = excelImporter.getCellText(10,i,"S");
                    if(factory.length() > 120)
                    {
                        factory = factory.substring(0, 120);
                    }
                    String unit = excelImporter.getCellText(11,i,"S");
                    if(unit.length() > 32)
                    {
                        unit = unit.substring(0, 32);
                    }
                    String comment = excelImporter.getCellText(12,i,"S");
                    if(comment.length() > 255)
                    {
                        comment = comment.substring(0, 255);
                    }
                    double price0 = 0d;
                    try
                    {
                        column = 14;
                        errorMessage = "-- 批发价1：请输入正确数值！";
                        price0 = Double.valueOf(excelImporter.getCellText(13,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double price1 = 0d;
                    try
                    {
                        column = 15;
                        errorMessage = "-- 批发价2：请输入正确数值！";
                        price1 = Double.valueOf(excelImporter.getCellText(14,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double price2 = 0d;
                    try
                    {
                        column = 16;
                        errorMessage = "-- 批发价3：请输入正确数值！";
                        price2 = Double.valueOf(excelImporter.getCellText(15,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double price3 = 0d;
                    try
                    {
                        column = 17;
                        errorMessage = "-- 批发价4：请输入正确数值！";
                        price3 = Double.valueOf(excelImporter.getCellText(16,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double price4 = 0d;
                    try
                    {
                        column = 18;
                        errorMessage = "-- 批发价5：请输入正确数值！";
                        price4 = Double.valueOf(excelImporter.getCellText(17,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double price5 = 0d;
                    try
                    {
                        column = 19;
                        errorMessage = "-- 批发价6：请输入正确数值！";
                        price5 = Double.valueOf(excelImporter.getCellText(18,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double retailPrice = 0d;
                    try
                    {
                        column = 20;
                        errorMessage = "-- 零售价：请输入正确数值！";
                        retailPrice = Double.valueOf(excelImporter.getCellText(19,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }
                    double stockPrice = 0d;
                    try
                    {
                        column = 21;
                        errorMessage = "-- 标准采购价：请输入正确数值！";
                        stockPrice = Double.valueOf(excelImporter.getCellText(20,i,"D"));
                    }catch(Exception ex)
                    {
                        throw new RunException(errorMessage);
                    }

                    ProductCatalog catalog = new ProductCatalog();
                    catalog.setCompany(company);
                    catalog.setName(typeName);
                    catalog.setParentId(-1);
                    Product product = new Product();
                    product.setCompany(company);
                    if(service.getProductCatalog(company, typeName) == null )
                    {
                        product.setCatalog(service.saveProductCatalog(catalog));
                    }
                    else
                    {
                        product.setCatalog(service.getProductCatalog(company, typeName));
                    }
                    if(proNumber != null && !proNumber.trim().equals(""))
                    {
                        product.setNumber(proNumber);
                    }
                    product.setName(proName);
                    product.setShortName(shortname);
                    product.setCode(helpcode);
                    product.setAlias(alias);
                    product.setSpec(spec);
                    product.setBarCode(barcode);
                    product.setTaxRate(tax);
                    product.setArea(proArea);
                    product.setFactory(factory);
                    product.setSmallUnit(unit);
                    product.setComments(comment);
                    product.setPrice0(price0);
                    product.setPrice1(price1);
                    product.setPrice2(price2);
                    product.setPrice3(price3);
                    product.setPrice4(price4);
                    product.setPrice5(price5);
                    product.setRetailPrice(retailPrice);
                    product.setStockPrice(stockPrice);
                    //service.saveProduct(product);
                    products.add(product);
                    //IDataRow dataRow = new ObjectDataRow(product, "id", dbSupport);
			        //dataRows.add(dataRow);
                    if(excelImporter.getCellText(0,i+1,"S").equals(""))
                    {
                        flag = false;
                    }

                    i++;
                }
                //model.insertDataRows(dataRows);
                excelImporter.close();
                if(products != null && products.size() > 0)
                {
                    for(Product p :products)
                    {
                        service.saveProduct(p);
                    }
                }
                else
                {
                    MessageBox.showMessageDialog(Main.getMainFrame(), "0 条数据导入成功！");
                    return;
                }
                Main.getMainFrame().getObjectsPool().refreshProducts();
                Main.getMainFrame().getObjectsPool().refreshUsableProducts();
                Main.getMainFrame().getObjectsPool().refreshProductCatalogs();
                this.refreshDatas();
                MessageBox.showMessageDialog(Main.getMainFrame(), String.valueOf(excelImporter.rowSize - 1) + "条数据导入成功！");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(CProductManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BiffException ex)
        {
            Logger.getLogger(CProductManageWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(RunException ex)
        {
            //ex.printStackTrace();
            MessageBox.showErrorDialog(Main.getMainFrame(), "第 "+ String.valueOf(row)+" 行,第 " + String.valueOf(column) + " 列 数据有误 " + ex.getErrorMessage());
        }
	}
    @Override
    protected void initTypes()
    {
	    List<ProductCatalog> catalogs = Main.getMainFrame().getObjectsPool().getProductCatalogs();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        MutableTreeNode root = new DefaultMutableTreeNode("产品分类");
	    this.treeModel.setRoot(root);
        this.typeDialog = new CCatalogDialog(this, this.typeDataSource, dbSupport, ProductCatalog.class);
    }
    @Override
    protected void doPrint()
    {
        
        Map variables = new HashMap();
        ReportVO vo=new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("商品信息报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/ProductManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}