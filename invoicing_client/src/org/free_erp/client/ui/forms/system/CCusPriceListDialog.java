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
package org.free_erp.client.ui.forms.system;

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.ObjectsPool;
import org.free_erp.client.ui.forms.CBaseListManageDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.system.CustomerPrice;
import org.free_erp.service.logic.IOptionSetService;
import java.awt.Frame;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CCusPriceListDialog extends CBaseListManageDialog
{
    protected CCusPriceAddDialog dialog;
    public CCusPriceListDialog(Frame parent)
    {
        super(parent);
        this.createDbSupport();
        this.initCs();
        this.initDatas();
    }
    private void initCs()
    {
        this.setTitle("默认价格下拉框设置");
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("默认价格");
		column.setColumnName("cusPrice");
		column.setWidth(165);
		columnModel.addColumn(column);
    }

    @Override
    public void initDatas()
    {
        ITableModel model = this.dataTable.getTableModel();
		List<CustomerPrice> customerPrices = service.getCustomerPrices(Main.getMainFrame().getCompany());
		for(CustomerPrice po:customerPrices)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id",dbSupport);
			model.insertDataRow(dataRow);
		}
    }

    @Override
    public void doAdd()
    {
        if (this.dialog == null)
		{
			this.dialog = new CCusPriceAddDialog(this, this.dataSource);
		}
		dialog.newDataRow();
		dialog.setVisible(true);
		this.dataSource.clearTempDataRows();
    }

    @Override
    public void doEdit()
    {
        if (this.dialog == null)
		{
			this.dialog = new CCusPriceAddDialog(this, this.dataSource);
		}
		if (this.dataTable.getSelectedRow() < 0)
		{
			MessageBox.showErrorDialog(Main.getMainFrame(), "没有任何数据行被选中!");
			return;
		}
		this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
		dialog.setVisible(true);
    }

    public void createDbSupport()
    {
		dbSupport = new IDbSupport()
        {
			ObjectsPool pool = Main.getMainFrame().getObjectsPool();
			IOptionSetService optionSetService = Main.getServiceManager().getOptionSetService();

			public void delete(Object obj)
            {
                if (obj instanceof CustomerPrice)
                {
					CustomerPrice po = (CustomerPrice) obj;
					optionSetService.deleteCustomerPrice(po);
					pool.refreshCustomerPrices();
				}
                else
                {
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void save(Object obj)
            {
                if (obj instanceof CustomerPrice)
                {
					CustomerPrice po = (CustomerPrice) obj;
					optionSetService.saveCustomerPrice(po);
					pool.refreshCustomerPrices();
				}
                else
                {
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}

			public void deleteAll(Collection<Object> objs)
            {
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}

			public void saveAll(Collection<Object> objs)
            {
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
}
