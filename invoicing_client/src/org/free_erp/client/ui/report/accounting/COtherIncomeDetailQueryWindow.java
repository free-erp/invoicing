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

/**
 * 其它收入明细查询
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class COtherIncomeDetailQueryWindow extends CCashBankDetailQueryWindow
{
    
    public COtherIncomeDetailQueryWindow(String title)
    {
        super(title);
    }
    
    @Override
    protected void select()
    {
        IAccountingService service = Main.getServiceManager().getAccountingService();
        //涉及到现金银行的单据
        List<SubjectDetail> vos = service.findSubjectDetails(this.beginDateField.getDate(), this.endDateField.getDate(), (Subject)this.subjectField.getSelectedItem(), Main.getMainFrame().getCompany(), ServiceConstants.SUBJECT_CATALOG_OTHER_INCOME);
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        if(vos == null || vos.size() == 0)
        {
            this.clearInfo();
        }
        for(SubjectDetail vo:vos)
        {
            dataRows.add(ObjectDataRow.newDataRow(vo, "id", null));
        }
        this.dataTable.getDataSource().clear();
        this.dataTable.getDataSource().insertDataRows(dataRows);
    }

    protected JDataTable getSubjectTable()
    {
        return Main.getMainFrame().getObjectsPool().getOtherIncomeSubjectTable();
    }

    protected String getExcelExportTitle()
    {
        return "其它收入明细查询";
    }

	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("其它收入明细查询报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(Main.getMainFrame(), this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/rCashBankDetailQuery.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
      
}