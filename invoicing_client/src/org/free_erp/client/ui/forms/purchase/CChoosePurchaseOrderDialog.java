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
import org.free_erp.service.entity.purchase.PurchaseBackDetailPo;
import org.free_erp.service.entity.purchase.PurchaseBackPo;
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
public class CChoosePurchaseOrderDialog extends CChooseIndentDialog
{
    public CChoosePurchaseOrderDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource,dbSupport);
        this.setTitle("选择采购单");
    }

    @Override
    protected void initData()
    {
        ITableModel model = this.table.getTableModel();
		IPurchaseService purchaseService = Main.getServiceManager().getPurchaseService();
		List<PurchaseOrderPo> purchaseOrderPos = purchaseService.getPassPurchaseOrderPoForms(Main.getMainFrame().getCompany());
		for(PurchaseOrderPo po:purchaseOrderPos)
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
        PurchaseBackPo po = new PurchaseBackPo();
        PurchaseOrderPo orderPo = service.gePurchaseOrderPo(Main.getMainFrame().getCompany(), (String)getDataRow.getColumnValue("number"));
        po.setPurchaseOrderPo(orderPo);
		po.setCompany(Main.getMainFrame().getCompany());
        po.setSupplier((Customer)getDataRow.getColumnValue("supplier"));
        po.setChargePerson((Employee)getDataRow.getColumnValue("chargePerson"));
        po.setStorage((Storage)getDataRow.getColumnValue("storage"));
        po.setDepartment((String)getDataRow.getColumnValue("department"));
        po.setPayTerm((Integer)getDataRow.getColumnValue("payTerm"));
        po.setComments((String)getDataRow.getColumnValue("comments"));
        po.setOfferMoney((Double)getDataRow.getColumnValue("offerMoney"));

        Set<PurchaseOrderDetailPo> details = (Set<PurchaseOrderDetailPo>)getDataRow.getColumnValue("details");
        PurchaseOrderDetailPo orderDetailPo = new PurchaseOrderDetailPo();
        Set<PurchaseBackDetailPo> backDetails = new HashSet<PurchaseBackDetailPo>();
        Iterator it = details.iterator();
        while(it.hasNext())
        {
            orderDetailPo = (PurchaseOrderDetailPo)it.next();
            PurchaseBackDetailPo backDetailPo = new PurchaseBackDetailPo();
            backDetailPo.setMainObject(po);
            backDetailPo.setProduct(orderDetailPo.getProduct());
            backDetailPo.setPrice(orderDetailPo.getPrice());
            backDetailPo.setDiscount(orderDetailPo.getDiscount());
            backDetailPo.setDisprice(orderDetailPo.getDisprice());
            backDetailPo.setAmount(orderDetailPo.getAmount());
            backDetailPo.setTotalMoney(orderDetailPo.getTotalMoney());
            backDetailPo.setComments(orderDetailPo.getComments());
            backDetails.add(backDetailPo);
        }
        po.setDetails(backDetails);
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
            formDialog =  new CPurchaseOrderBackDialog(Main.getMainFrame(), this.dataSource, dbSupport);
        }
        formDialog.unEnbleButton();
        return formDialog;
    }
}
