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
package org.free_erp.client.ui.report.manager;

import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;

import org.free_erp.service.entity.accounting.DailyReportPo;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IManagerService;
import org.free_erp.service.logic.IPermissionsService;
import org.free_erp.service.logic.sale.SaleQueryVo;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CManagerDailyQueryWindow extends CBaseQueryWindow
{
    private JDataDatePicker queryDateField;
    private CField beginDateField;
    private CField endDateField;

    public CManagerDailyQueryWindow(String title)
    {
        super(title);
        this.clearButton.setVisible(false);
    }

    

    protected void clearInfo()
    {
    }

    protected void select()
    {
        SaleQueryVo vo = new SaleQueryVo(Main.getMainFrame().getCompany());
        Object obj = null;
        if(!queryDateField.getEditorText().equals(""))
        {
            obj = queryDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDatePo(startDate);
            }
        }
        ITableModel model = this.dataTable.getTableModel();
        IManagerService managerService = Main.getServiceManager().getManagerService();
        this.dataSource.clear();
        List<DailyReportPo> DailyReportPos =managerService.getManagerDailyForm(vo);
        if(DailyReportPos == null || DailyReportPos.size() == 0)
        {
            this.clearInfo();
        }
        for(DailyReportPo po : DailyReportPos)
        {
            IDataRow dataRow = new ObjectDataRow(po, "id", null);
            model.insertDataRow(dataRow);
        }
    }

    private String getStringDate(Date date)
    {
        return String.valueOf(date.getYear() + 1900) + "-" + String.valueOf(date.getMonth() + 1) + "-" + String.valueOf(date.getDate());
    }

    protected CPanel getMainPanel()
    {
        CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,60));
		panel.setLayout(null);
        queryDateField = new JDataDatePicker();
        beginDateField = new CField();
        endDateField = new CField();
        beginDateField.setText(this.getStringDate(new Date()));
        endDateField.setText(this.getStringDate(new Date()));
        beginDateField.setEnabled(false);
        endDateField.setEnabled(false);
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
                            beginDateField.setText(getStringDate(date));
                            endDateField.setText(getStringDate(date));
                        }
                    }
                }
            }
        });
        int x = 60;
        int y = 15;
        panel.addComponent(queryDateField, x , y, 120, 20, "��ѯ����", 50);
        panel.addComponent(beginDateField, x , y + 25, 120, 20, "��ʼ����", 50);
        panel.addComponent(endDateField, x + 170, y + 25, 100, 20, "��������", 50);
        panel.addComponent(selectButton, x + 300, y, 75, 25);

        return panel;
    }

    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("��ˮ��");
        column.setColumnName("id");
        column.setWidth(50);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("��������");
        column.setColumnName("formDate");
        column.setWidth(80);
        column.setValueType(Date.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("ҵ������");
        column.setColumnName("operationType");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("���ݱ��");
        column.setColumnName("formNumber");
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("���̱��");
        column.setColumnName("customer.number");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("��������");
        column.setColumnName("customer.name");
        column.setWidth(100);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("���۽��");
        column.setColumnName("saleMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�տ���");
        column.setColumnName("receiveMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("ë��");
        column.setColumnName("grossProfitMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�ɱ�");
        column.setColumnName("cost");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�ɹ����");
        column.setColumnName("purchaseMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("������");
        column.setColumnName("paymentMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("�ֽ����");
        column.setColumnName("cashMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("����ܶ�");
        column.setColumnName("stockMoney");
        column.setValueType(Currency.class);
        column.setWidth(80);
        columnModel.addColumn(column);
    }

    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("�ܾ����ձ�����ѯ");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rManagerDaily.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    protected String getExcelExportTitle()
    {
        return "�ձ�����ѯ";
    }
}