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

package org.free_erp.client.ui.report.accounting;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CTableComboBox;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectAccount;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

/**
 *
 * @author afa
 */
public class CSubjectQueryWindow extends CBaseQueryWindow
{
     protected CTableComboBox subjectField;
    protected JDataDatePicker beginDateField;
    protected JDataDatePicker endDateField;
    public CSubjectQueryWindow(String title) {
        super(title);
    }

    protected void clearInfo() {
        this.subjectField.setEditorText("");
    }

    protected void initColumns() {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("财务科目编号");
        column.setColumnName("subject.number");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("财务科目名称");
        column.setColumnName("subject.name");
        column.setWidth(150);
        columnModel.addColumn(column);

	
        column = new JDataTableColumn();
        column.setHeaderText("期初");
        column.setColumnName("initialMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("借方");
        column.setColumnName("debitMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("贷方");
        column.setColumnName("creditMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("余额");
        column.setColumnName("remainMoney");
        column.setValueType(Currency.class);
        columnModel.addColumn(column);
    }

    public void select() {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        if (this.subjectField.getSelectedItem() != null)
        {
            SubjectAccount account = service.findSubjectAccount((Subject)this.subjectField.getSelectedItem());
            this.dataTable.getDataSource().clear();
            if (account != null)
            {
                this.dataTable.getDataSource().insertDataRow(ObjectDataRow.newDataRow(account, "id", null));
            }
            return;
        }
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        List<Subject> subjects = service.getAllSubject(Main.getMainFrame().getCompany());
        if(subjects == null || subjects.size() == 0)
        {
            this.clearInfo();
        }
        for(Subject subject:subjects)
        {
            SubjectAccount account = service.findSubjectAccount(subject);
            if(account != null)
            {
                dataRows.add(ObjectDataRow.newDataRow(account, "id", null));
            }
        }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(dataRows);
    }

   
    protected CPanel getMainPanel() {
       CPanel panel = new CPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(700,40));
		panel.setLayout(null);
        beginDateField = new JDataDatePicker();
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        beginDateField.setSelectedItem("");
        JDataTable table = Main.getMainFrame().getObjectsPool().getBankSubjectTable();
        table.getDataSource().clear();
        List<Subject> subjects = Main.getServiceManager().getAccountingService().getAllSubject(Main.getMainFrame().getCompany());
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Subject po:subjects)
        {
            dataRows.add(ObjectDataRow.newDataRow(po, "id", null));
        }
        table.getDataSource().insertDataRows(dataRows);
        subjectField =  new JDataTableComboBox("",Subject.class,table, "name");
        int x = 60;
        int y = 10;
        panel.addComponent(subjectField, x , y, 120, 20, "财务科目", 50);
        panel.addComponent(selectButton, x + 405, y, 75, 25);
        panel.addComponent(clearButton, x + 485, y, 75, 25);

        return panel;
    }

    protected String getExcelExportTitle()
    {
        return "科目余额查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("科目余额查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rSubjectQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }

    
}