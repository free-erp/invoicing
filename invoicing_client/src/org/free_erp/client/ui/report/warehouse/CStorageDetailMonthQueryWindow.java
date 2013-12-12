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

import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.DateUtilities;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CMoneyField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataPanelComboBox;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.util.TypeUtilities;

import org.free_erp.service.entity.base.Product;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.storage.StorageChangeLog;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.entity.vo.StorageQueryVo;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CStorageDetailMonthQueryWindow  extends CBaseQueryWindow
{
    private JDataDatePicker queryDateField;
    private CField beginDateField;
    private JDataPanelComboBox numberField;
    private JDataTableComboBox storageField;
    private CField changedTotalAmountField;
    private CMoneyField changeTotalMoneyField;

    public CStorageDetailMonthQueryWindow(String title)
    {
        super(title);
    }

    

    @Override
    protected void clearInfo()
    {
        //queryDateField.setSelectedItem("");
        //beginDateField.setText("");
        numberField.setEditorText("");
        storageField.setText("");
    }

    @Override
    protected void select()
    {
        StorageQueryVo vo = new StorageQueryVo(Main.getMainFrame().getCompany());
        Object obj = storageField.getSelectedItem();
        if (obj != null && obj instanceof Storage && this.storageField.getText() != null && !this.storageField.getText().equals(""))
        {
            vo.setStorage((Storage)storageField.getSelectedItem());
        }
        else
        {
            vo.setStatus(0);
        }

        if(this.numberField.getText() != null && !this.numberField.getText().trim().equals(""))
        {
            obj = this.numberField.getSelectedItem();
            if (obj instanceof Product)
            {
                vo.setProduct((Product)obj);
            }
        }

        if (!queryDateField.getEditorText().equals("")) {
            obj = queryDateField.getSelectedItem();
            if (obj != null && obj instanceof Date) {
                Date date = (Date) obj;
                Date startDate = new Date();
                startDate.setYear(date.getYear());
                startDate.setMonth(date.getMonth());
                startDate.setDate(1);
                vo.setStartDate(startDate);
                Date endDate = new Date();
                endDate.setYear(date.getYear());
                endDate.setMonth(date.getMonth());
                endDate.setDate(DateUtilities.getMonthLastDate(date));
                vo.setEndDate(endDate);
            }
        }

        ITableModel model = this.dataTable.getTableModel();
        this.dataSource.clear();

        List<StorageChangeLog> logs = Main.getServiceManager().getStorageService().findStorageChangeLog(vo);
        if(logs == null || logs.size() == 0)
        {
            this.clearInfo();
            return;
        }
        for (StorageChangeLog po : logs)
        {
            IDataRow dataRow = new ObjectDataRow(po, "id", null);
            model.insertDataRow(dataRow);
        }
    }

    protected CPanel getTotalPanel()
    {
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(900, 40));
        this.totalAmountField = new CField();
        this.changedTotalAmountField = new CField();
        this.totalMoneyField = new CMoneyField();
        this.totalAmountField.setEditable(false);
        this.changeTotalMoneyField = new CMoneyField();
        bottomPanel.addComponent(totalAmountField, 100, 10, 150, 20, "变动前数量", 60);
        bottomPanel.addComponent(changedTotalAmountField, 310, 10, 150, 20, "变动后数量", 60);
        bottomPanel.addComponent(totalMoneyField, 520, 10, 150, 20, "变动前金额", 60);
        bottomPanel.addComponent(changeTotalMoneyField, 730, 10, 150, 20, "变动后金额", 60);
        return bottomPanel;
    }

    protected void initColumns() {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("仓库编号");
        column.setColumnName("storage.number");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("仓库名称");
        column.setColumnName("storage.name");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单据编号");
        column.setColumnName("formNumber");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("业务类型");
        column.setColumnName("changeType");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单据日期");
        column.setColumnName("formDate");
        column.setWidth(80);
        column.setValueType(Date.class);
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
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("单位");
        column.setColumnName("product.smallUnit");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动前单价");
        column.setColumnName("currentPrice");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动后单价");
        column.setColumnName("changedPrice");
        column.setWidth(80);
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动前数量");
        column.setColumnName("currentAmount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动后数量");
        column.setColumnName("changedAmount");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动前金额");
        column.setColumnName("currentTotalMoney");
        column.setValueType(Currency.class);
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("变动后金额");
        column.setColumnName("changedTotalMoney");
        column.setValueType(Currency.class);
        column.setWidth(100);
        columnModel.addColumn(column);
    }

    public void refreshTotalAmount()
    {
        if (this.totalAmountField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double amount = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("currentAmount"))
            {
                return;
            }
            Double tm = (Double) dataRow.getColumnValue("currentAmount");
			if (tm != null)
            {
				amount += tm;
			}
		}
        this.totalAmountField.setText(TypeUtilities.polishDoubleString(amount));
        this.refreshChangedTotalAmount();
    }

    private void refreshChangedTotalAmount()
    {
        if (this.changedTotalAmountField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double amount = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("changedAmount"))
            {
                return;
            }
            Double tm = (Double) dataRow.getColumnValue("changedAmount");
			if (tm != null)
            {
				amount += tm;
			}
		}
        this.changedTotalAmountField.setText(TypeUtilities.polishDoubleString(amount));
    }

    public void refreshTotalMoney()
    {
        if (this.totalMoneyField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double money = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("currentTotalMoney"))
            {
                return;
            }
			Double tm = (Double) dataRow.getColumnValue("currentTotalMoney");
			if (tm != null)
            {
				money += tm;
			}
		}
        this.totalMoneyField.setMoney(money);

    }

    public void refreshTotailMoney()
    {
        if (this.changeTotalMoneyField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double money = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("changedTotalMoney"))
            {
                return;
            }
			Double tm = (Double) dataRow.getColumnValue("changedTotalMoney");
			if (tm != null)
            {
				money += tm;
			}
		}
        this.changeTotalMoneyField.setMoney(money);

    }

    protected String getExcelExportTitle()
    {
        return "库存商品明细查询";
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel topPanel = new CPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setPreferredSize(new Dimension(700, 80));
        topPanel.setLayout(null);
        queryDateField = new JDataDatePicker();
        queryDateField.addValueChangedListener(new ValueChangedListener()
        {
            public void valueChanged(ValueChangedEvent evt)
            {
                if(evt.getSource() == queryDateField)
                {
                    Object obj = null;
                    if(!queryDateField.getEditorText().equals(""))
                    {
                        obj = queryDateField.getSelectedItem();
                        if(obj != null && obj instanceof Date)
                        {
                            Date date = (Date)obj;
                            beginDateField.setText(DateUtilities.getYearMonthDate(date));
                        }
                    }
                }
            }
        });
        beginDateField = new CField();
        beginDateField.setText(DateUtilities.getYearMonthDate(new Date()));
        beginDateField.setEditable(false);
        numberField = new JDataPanelComboBox("", Product.class, this.dataSource, Main.getMainFrame().getProductSelectPanel(), "number");
        storageField = new JDataTableComboBox("", Storage.class, Main.getMainFrame().getObjectsPool().getStorageTable(), "name");

        int x = 60;
        int y = 15;
        topPanel.addComponent(storageField, x, y, 120, 20, "仓库", 50);
        topPanel.addComponent(numberField, x + 170, y, 120, 20, "商品编号", 50);

        y += 27;
        topPanel.addComponent(queryDateField, x, y, 120, 20, "查询时间", 50);
        topPanel.addComponent(beginDateField, x + 170, y, 120, 20, "开始日期", 50);
        topPanel.addComponent(selectButton, x + 440, y, 75, 25);
        topPanel.addComponent(clearButton, x + 520, y, 75, 25);
        return topPanel;
    }
}
