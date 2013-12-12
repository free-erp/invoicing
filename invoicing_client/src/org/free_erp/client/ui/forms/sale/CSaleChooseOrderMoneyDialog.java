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

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.sale.SaleDetailPo;
import org.free_erp.service.entity.sale.SaleOrderDetailPo;
import org.free_erp.service.entity.sale.SaleOrderPo;
import org.free_erp.service.entity.sale.SaleQuoteDetailPo;
import org.free_erp.service.entity.sale.SaleQuotePo;
import org.free_erp.service.logic.ISaleService;
import java.awt.Frame;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author afa
 */
public class CSaleChooseOrderMoneyDialog extends CSaleChooseindentDialog {

    public CSaleChooseOrderMoneyDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
    }

    @Override
    protected void initData() {
        ITableModel model = this.table.getTableModel();
        ISaleService saleService = Main.getServiceManager().getSaleService();
        List<SaleQuotePo> SaleOrderPos = saleService.getAllQuoteForms(Main.getMainFrame().getCompany());
        for (SaleQuotePo po : SaleOrderPos) {
            IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
    }

    @Override
    protected void doChoose() {
        if (this.table.getSelectedRow() < 0) {
            MessageBox.showErrorDialog(Main.getMainFrame(), "没有任何数据行被选中!");
            return;
        }
        ISaleService saleService = Main.getServiceManager().getSaleService();
        IDataRow getDataRow = this.table.getSelectedDataRow();
//          Set<SaleOrderPo> saleOrderPo=getDataRow.getColumnValue(columnName);
        SaleOrderPo po = new SaleOrderPo();
        po.setBalanceFashion((ClearingType) getDataRow.getColumnValue("balanceFashion"));
        po.setClearing((String) getDataRow.getColumnValue("clearing"));
        po.setCompany((Company) getDataRow.getColumnValue("company"));
        po.setCreateDate((Date) getDataRow.getColumnValue("createDate"));
        po.setCreatedBy((String) getDataRow.getColumnValue("createdBy"));
        po.setCustomer((Customer) getDataRow.getColumnValue("customer"));
        po.setDelivery((String) getDataRow.getColumnValue("delivery"));
        po.setDelivery((String) getDataRow.getColumnValue("delivery"));
        po.setEmployee((Employee) getDataRow.getColumnValue("employee"));
        po.setModifyDate((Date) getDataRow.getColumnValue("modifyDate"));
        po.setFormDate((Date) getDataRow.getColumnValue("formDate"));
        po.setPay((String) getDataRow.getColumnValue("pay"));
        po.setPayFashion((AffordType) getDataRow.getColumnValue("payFashion"));
        po.setReceiving((String) getDataRow.getColumnValue("receiving"));
        po.setReceivingAddress((String) getDataRow.getColumnValue("receivingAddress"));
        po.setReceivingCode((String) getDataRow.getColumnValue("receivingCode"));
        po.setReceivingPhone((String) getDataRow.getColumnValue("receivingPhone"));
         po.setDepartment((String) getDataRow.getColumnValue("department"));
         po.setOffersMoney((Double) getDataRow.getColumnValue("offersMoney"));
        po.setTotalMoney((Double) getDataRow.getColumnValue("totalMoney"));
         po.setStorage((Storage) getDataRow.getColumnValue("storage"));
//         po.setOrder((SaleOrderPo) getDataRow);
        Set<SaleQuoteDetailPo> details = (Set<SaleQuoteDetailPo>) getDataRow.getColumnValue("details");
        SaleQuoteDetailPo indentDetailPo = new SaleQuoteDetailPo();
        Set<SaleOrderDetailPo> orderDetails = new HashSet<SaleOrderDetailPo>();
        Iterator it = details.iterator();
        while (it.hasNext()) {
            indentDetailPo = (SaleQuoteDetailPo) it.next();
          SaleOrderDetailPo orderDetailPo = new SaleOrderDetailPo();
            orderDetailPo.setProduct(indentDetailPo.getProduct());
            orderDetailPo.setPrice(indentDetailPo.getPrice());
            orderDetailPo.setDiscount(indentDetailPo.getDiscount());
            orderDetailPo.setRate(indentDetailPo.getRate());
            orderDetailPo.setTaxMoney(indentDetailPo.getTaxMoney());
            orderDetailPo.setTaxPrice(indentDetailPo.getTaxPrice());
            orderDetailPo.setTaxprice(indentDetailPo.getTaxprice());
            orderDetailPo.setTaxPriceMoney(indentDetailPo.getTaxPriceMoney());
            orderDetailPo.setModifyDate(indentDetailPo.getModifyDate());
            orderDetailPo.setCreateDate(indentDetailPo.getCreateDate());
            orderDetailPo.setCreatedBy(indentDetailPo.getCreatedBy());
            orderDetailPo.setAmount(indentDetailPo.getAmount());
            orderDetailPo.setTotalMoney(indentDetailPo.getTotalMoney());
            orderDetailPo.setComments(indentDetailPo.getComments());
            orderDetailPo.setMainObject(po);
            orderDetails.add(orderDetailPo);
         
        }
        po.setDetails(orderDetails);
        IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
        this.dispose();
        this.formDialog = this.getFormDialog();
        this.formDialog.setVisible(true);
    }

    @Override
    protected CBaseFormDialog getFormDialog() {
        if (formDialog == null) {
            formDialog = new CSaleOrderDialog(Main.getMainFrame(), this.dataSource, dbSupport);
        }
        formDialog.unEnbleButton();
        return formDialog;
    }
}

