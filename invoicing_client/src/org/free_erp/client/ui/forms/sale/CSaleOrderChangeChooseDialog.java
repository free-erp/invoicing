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

import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableModel;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.logic.ISaleService;
import java.awt.Frame;
import java.util.List;

/**
 *
 * @author alin
 */
public class CSaleOrderChangeChooseDialog extends CSaleChooseindentDialog  {

    public CSaleOrderChangeChooseDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
    }



    @Override
    protected void initData() {
        ITableModel model = this.table.getTableModel();
        ISaleService saleService = Main.getServiceManager().getSaleService();
        List<SalePo> SaleOrderPos = saleService.getAllSaleDetailForms(Main.getMainFrame().getCompany(),1);
        for (SalePo po : SaleOrderPos) {
            IDataRow dataRow = new ObjectDataRow(po, "id", dbSupport);
            model.insertDataRow(dataRow);
        }
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
