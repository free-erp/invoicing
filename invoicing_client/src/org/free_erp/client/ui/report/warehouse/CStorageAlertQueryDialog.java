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
package org.free_erp.client.ui.report.warehouse;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.storage.StorageAlertPo;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;
import java.util.List;
import javax.swing.JScrollPane;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageAlertQueryDialog extends CDialog implements ActionListener
{
    protected CButton closeButton;
    protected JDataTable dataTable;
    protected IDataSource dataSource;

    public CStorageAlertQueryDialog(Frame parent)
	{
		super(parent);
        this.initComps();
        this.initColumns();
        this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
        this.initDatas();
        this.setTitle("库存警报查询");
	}
    
    public CStorageAlertQueryDialog(Frame parent,Company company)
	{
		super(parent);
        this.initComps();
        this.initColumns();
        this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
        this.initDatas(company);
        this.setTitle("库存警报查询");
	}

    protected void initComps()
    {
        this.getContentPane().setLayout(null);
        this.setBounds(0, 200, 800, 600);
        CPanel panel = new CPanel();
		panel.setLayout(null);
        panel.setBounds(10, 10, 780, 580);
        this.getContentPane().add(panel);
        this.closeButton = new CButton("关闭(&C)");
        this.closeButton.addActionListener(this);
        this.dataTable = new JDataTable();
		JScrollPane tablePane = new JScrollPane(dataTable);
        panel.addComponent(tablePane, 0, 0, 780, 500);
        panel.addComponent(this.closeButton, 700, 520, 75, 25);
        this.setCancelButton(closeButton);
    }

    protected void initColumns()
    {
        ITableColumnModel columnModel = dataTable.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("仓库编号");
        column.setColumnName("storage.number");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库名称");
        column.setColumnName("storage.name");
        column.setWidth(90);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("商品编号");
		column.setColumnName("product.number");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
	    column.setHeaderText("商品名称");
		column.setColumnName("product.name");
		column.setWidth(140);
		columnModel.addColumn(column);
        
		column = new JDataTableColumn();
		column.setHeaderText("规格");
		column.setColumnName("product.spec");
		column.setWidth(90);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("单位");
		column.setColumnName("product.smallUnit");
		column.setWidth(40);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("库存上限");
		column.setColumnName("maxAmount");
		column.setWidth(80);
		columnModel.addColumn(column);

		column = new JDataTableColumn();
		column.setHeaderText("库存下限");
		column.setColumnName("minAmount");
		column.setWidth(80);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("库存数量");
        column.setColumnName("amount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("库存金额");
        column.setColumnName("totalMoney");
        column.setValueType(Currency.class);
        column.setWidth(90);
        columnModel.addColumn(column);
    }

    public void initDatas()
    {
        ITableModel model = this.dataTable.getTableModel();
		List<StorageAlertPo> pos = Main.getServiceManager().getStorageService().findStorageAlertPos(Main.getMainFrame().getCompany());
		for(StorageAlertPo po : pos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id",null);
			model.insertDataRow(dataRow);
		}
    }

    public void initDatas(Company company)
    {
        ITableModel model = this.dataTable.getTableModel();
		List<StorageAlertPo> pos = Main.getServiceManager().getStorageService().findStorageAlertPos(company);
		for(StorageAlertPo po : pos)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id",null);
			model.insertDataRow(dataRow);
		}
    }

    public void doClose()
    {
        this.dispose();
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == this.closeButton)
        {
            this.doClose();
        }
    }
}
