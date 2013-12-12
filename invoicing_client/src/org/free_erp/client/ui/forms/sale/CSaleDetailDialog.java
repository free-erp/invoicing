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

package org.free_erp.client.ui.forms.sale;

/**
 *
 * @author Administrator
 */
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.SaleReportVo;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 销售单
 * @author tengzhuolin
 */
public class CSaleDetailDialog extends CBaseSaleMainDetailDialog {
    protected JDataDatePicker inDateField;
    protected JDataField commentField;
    protected JDataComboBox clearingField;
    protected JDataComboBox payField;
   
    protected JDataField consigneeField;
    protected JDataField deliveryField;
    protected JDataField receivingPhoneField ;
    protected JDataField receivingField;
    protected JDataField receivingCodeField;
    protected JDataField receivingAddressField;
    protected JDataField idsField;
    public CSaleDetailDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
        this.setTitle("销售单");
    }
    
    public CSaleDetailDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, dbSupport);
		this.setTitle("销售单");
	}
    
   
    @Override
    public void newDataRow() {
        SalePo po = new SalePo();
        po.setCompany(Main.getMainFrame().getCompany());
        IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
        dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
    }

    @Override
    protected CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(700, 180));
        topPanel.setLayout(null);
        inDateField = new JDataDatePicker("formDate", this.dataSource);
        commentField = new JDataField("comments", String.class, this.dataSource);
        payField= new JDataComboBox("pay", String.class, this.dataSource);
        deliveryField= new JDataField("delivery", String.class, this.dataSource);
        payTimeField=new JDataNumberField("payTerm", Integer.class, this.dataSource);
        payTimeField.addValueChangedListener(this);
        consigneeField=new JDataField("consignee", String.class, this.dataSource);
        receivingPhoneField=new JDataField("receivingPhone", String.class, this.dataSource);
        receivingField=new JDataField("receiving", String.class, this.dataSource);
        receivingCodeField=new JDataField("receivingCode", String.class, this.dataSource);
        receivingAddressField=new JDataField("receivingAddress", String.class, this.dataSource);
        idsField = new JDataField("number",String.class,this.dataSource);
        commentField.setMaxLength(255);

        int y = 10;
        int x = 80;

        topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        topPanel.addComponent(supplierField, x + 220, y, 180, 20, "客户", 60);
        topPanel.addComponent(inDateField, x + 490, y, 100, 20, "发生日期", 60);

        y += ROW_SPAN;
        topPanel.addComponent(adminField, x, y, 150, 20, "业务员", 60);
        topPanel.addComponent(departmentField, x + 220, y, 180, 20, "部门", 60);
        topPanel.addComponent(storageField, x + 490, y, 180, 20, "仓库", 60);
//        y += ROW_SPAN;
//        topPanel.addComponent(balanceFashionField, x, y, 150, 20, "结算方式", 60);
//        topPanel.addComponent(payFashionField, x + 220, y, 180, 20, "付款方式", 60);
//        topPanel.addComponent(payTimeField, x + 490, y, 180, 20, "付款期限", 60);
        y += ROW_SPAN;
        topPanel.addComponent(receivingField, x, y, 150, 20, "收货人", 60);
        topPanel.addComponent(deliveryField, x + 220, y, 180, 20, "发货方式", 60);
        topPanel.addComponent(receivingPhoneField, x + 490, y, 180, 20, "收货电话", 60);
        y += ROW_SPAN;
       topPanel.addComponent(receivingCodeField, x, y, 150, 20, "收货邮编", 60);
         topPanel.addComponent(payTimeField, x+220, y, 50, 20, "付款期限", 60);
        topPanel.addComponent(receivingAddressField, x + 340, y, 330, 20, "收货地址", 60);
        y += ROW_SPAN;
        topPanel.addComponent(commentField, x, y, 670, 20, "备注", 60);
   
        return topPanel;
    }

    @Override
    public CBaseProductSelectDialog getNewProductSelectDialog()
    {
        return new CSaleProductDialog(this, this.table.getDataSource(), this.getStorage());
    }
    @Override
    protected void initColumns() {

        ITableColumnModel columnModel = table.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
        column.setHeaderText("商品编号");
        column.setColumnName("product.number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("商品名称");
        column.setColumnName("product.name");
        column.setWidth(150);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("规格型号");
        column.setColumnName("product.spec");
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(60);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("单价");
        column.setColumnName("price");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
       
        column = new JDataTableColumn();
        column.setHeaderText("数量");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
      column.setHeaderText("总金额");
		column.setColumnName("totalMoney");
        column.setWidth(80);
         column.setValueType(Currency.class);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("货位");
        column.setColumnName("shelf");
        column.setWidth(80);
        columnModel.addColumn(column);
        column = new JDataTableColumn();
        column.setHeaderText("备注");
        column.setColumnName("comments");
        column.setWidth(150);
        columnModel.addColumn(column);
    }


    @Override
	protected boolean doSave()
    {
		try
        {
			//this.saveChildren();
			if (this.table.getTableModel().getRowCount() <= 0)
            {
				MessageBox.showMessageDialog(this, "没有添加任何商品信息,请先添加商品信息!");
				return false;
			}
			this.save();
            return true;
		}
        catch (FieldRequiredException ex)
        {
			MessageBox.showMessageDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"不能为空!");
            return false;
		}
	}
    @Override
    protected Class getDetailClass() {
        return SaleDetailPo.class;
    }

      @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        SaleReportVo vo=new SaleReportVo();
       vo.setNumber(idField.getText());
        vo.setFromDate((Date)inDateField.getSelectedItem());
        vo.setEmployee(supplierField.getText());
     vo.setCustomer(adminField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentField.getText());
        vo.setStorageName(storageField.getText());
        vo.setPayTerm(payTimeField.getText());
        vo.setReceiving(this.receivingField.getText());
        vo.setReceivingAddress(this.receivingAddressField.getText());
        vo.setReceivingCode(this.receivingField.getText());
        vo.setReceivingPhone(this.receivingCodeField.getText());
        vo.setDelivery(this.deliveryField.getText());
        vo.setOffersMoney(this.offersMoneyField.getText());
          vo.setTotalMoney(this.sumField.getText());
        vo.setStrTotalMoney(this.chineseMoneyField.getText());
        vo.setTitle("销售单");
           Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/sale/SaleDetailReport.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
}


