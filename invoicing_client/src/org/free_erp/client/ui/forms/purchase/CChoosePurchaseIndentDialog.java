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

package org.free_erp.client.ui.forms.purchase;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CChooseIndentDialog;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.util.MessageBox;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.purchase.PurchaseIndentDetailPo;
import org.free_erp.service.entity.purchase.PurchaseIndentPo;
import org.free_erp.service.entity.purchase.PurchaseOrderDetailPo;
import org.free_erp.service.entity.purchase.PurchaseOrderPo;
import org.free_erp.service.logic.purchase.IPurchaseService;
import java.awt.Frame;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CChoosePurchaseIndentDialog extends CChooseIndentDialog
{
    public CChoosePurchaseIndentDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource,dbSupport);
        this.setTitle("选择采购订单");
    }

    @Override
    protected void initData()
    {
        ITableModel model = this.table.getTableModel();
		IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
		List<PurchaseIndentPo> purchaseIndentPos = purchaseService.getPassPurchaseIndentPoForms(Main.getMainFrame().getCompany());
		for(PurchaseIndentPo po:purchaseIndentPos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
			model.insertDataRow(dataRow);
		}
    }

    @Override
    protected void doChoose()
    {
        if (this.table.getSelectedRow() < 0)
		{
			MessageBox.showErrorDialog(Main.getMainFrame(), "没有任何数据行被选中!");
			return;
		}
        IPurchaseService service = Main.getServiceManager().getPurchaseService();
        IDataRow getDataRow = this.table.getSelectedDataRow();
        PurchaseOrderPo po = new PurchaseOrderPo();
        PurchaseIndentPo indentPo = service.gePurchaseIndentPo(Main.getMainFrame().getCompany(), (String)getDataRow.getColumnValue("number"));
        po.setPurchaseIndentPo(indentPo);
		po.setCompany(Main.getMainFrame().getCompany());
        po.setSupplier((Customer)getDataRow.getColumnValue("supplier"));
        po.setChargePerson((Employee)getDataRow.getColumnValue("chargePerson"));
        po.setStorage((Storage)getDataRow.getColumnValue("storage"));
        po.setDepartment((String)getDataRow.getColumnValue("department"));
        po.setPayTerm((Integer)getDataRow.getColumnValue("payTerm"));
        po.setComments((String)getDataRow.getColumnValue("comments"));
        po.setOfferMoney((Double)getDataRow.getColumnValue("offerMoney"));

        Set<PurchaseIndentDetailPo> details = (Set<PurchaseIndentDetailPo>)getDataRow.getColumnValue("details");
        PurchaseIndentDetailPo indentDetailPo = new PurchaseIndentDetailPo();
        Set<PurchaseOrderDetailPo> orderDetails = new HashSet<PurchaseOrderDetailPo>();
        Iterator it = details.iterator();
        while(it.hasNext())
        {
            indentDetailPo = (PurchaseIndentDetailPo)it.next();
            PurchaseOrderDetailPo orderDetailPo = new PurchaseOrderDetailPo();
            orderDetailPo.setMainObject(po);
            orderDetailPo.setProduct(indentDetailPo.getProduct());
            orderDetailPo.setPrice(indentDetailPo.getPrice());
            orderDetailPo.setDiscount(indentDetailPo.getDiscount());
            orderDetailPo.setDisprice(indentDetailPo.getDisprice());
            orderDetailPo.setAmount(indentDetailPo.getAmount());
            orderDetailPo.setTotalMoney(indentDetailPo.getTotalMoney());
            orderDetailPo.setComments(indentDetailPo.getComments());
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
    protected CBaseFormDialog getFormDialog()
    {
        if (formDialog == null)
        {
            formDialog =  new CPurchaseOrderDialog(Main.getMainFrame(), this.dataSource, dbSupport);
        }
        formDialog.unEnbleButton();
        return formDialog;
    }
}
