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

import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectDetail;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;

/**
 * 日常费用明细查询
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CDailyFeeDetailQueryWindow extends CCashBankDetailQueryWindow
{

    public CDailyFeeDetailQueryWindow(String title)
    {
        super(title);
    }

    @Override
    protected void select()
    {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        //涉及到现金银行的单据
        List<SubjectDetail> vos = service.findSubjectDetails(this.beginDateField.getDate(), this.endDateField.getDate(), (Subject)this.subjectField.getSelectedItem(), Main.getMainFrame().getCompany(), ServiceConstants.SUBJECT_CATALOG_BUSSINESS_EXPENSE);
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        if(vos == null || vos.size() == 0)
        {
            this.clearInfo();
        }
        for(SubjectDetail vo:vos)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }
        vos = service.findSubjectDetails(null, null, null, Main.getMainFrame().getCompany(), ServiceConstants.SUBJECT_CATALOG_COMMON_EXPENSE);
        for(SubjectDetail vo:vos)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(dataRows);
    }

    protected JDataTable getSubjectTable()
    {
        JDataTable table = new JDataTable();
        ITableColumnModel columnModel = table.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
        column.setHeaderText("编号");
        column.setColumnName("number");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("名称");
        column.setColumnName("name");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("简称");
        column.setColumnName("shortName");
        column.setWidth(120);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("助记码");
        column.setColumnName("code");
        column.setWidth(60);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("借贷");
        column.setColumnName("debitCredit");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("财务科目类别");
        column.setColumnName("mainSubjectCatalog.name");
        column.setWidth(80);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("全称");
        column.setColumnName("fullName");
        column.setWidth(180);
        column.setAlignmentX(JLabel.LEFT);
        columnModel.addColumn(column);
        List<Subject> subjects = Main.getMainFrame().getObjectsPool().getBussinessExpenseSubjects();
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Subject vo:subjects)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }    
        subjects = Main.getMainFrame().getObjectsPool().getCommonExpenseSubjects();
        for(Subject vo:subjects)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }
        table.getDataSource().insertDataRows(dataRows);
        return table;
        //return Main.getMainFrame().getObjectsPool().getBussinessExpenseSubjectTable();
    }

    protected String getExcelExportTitle()
    {
        return "日常费用明细查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("日常费用明细查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rCashBankDetailQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
    
}
