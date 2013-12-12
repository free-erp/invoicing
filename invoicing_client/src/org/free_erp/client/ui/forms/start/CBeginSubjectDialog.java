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
package org.free_erp.client.ui.forms.start;
import java.awt.Dimension;
import java.awt.Frame;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CTableComboBox;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataDisplayMoneyField;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataMoneyField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.DataTableCellEditor;
import com.jdatabeans.beans.data.table.DefaultDataTableCellEditor;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import org.free_erp.client.ui.forms.CBaseAccountingMainDetailDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.InitialSubject;
import org.free_erp.service.entity.accounting.InitialSubjectDetail;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectAccount;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IAccountingService;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
public class CBeginSubjectDialog extends CBaseAccountingMainDetailDialog
{
    private JDataDatePicker formDateField;
    private JDataField commentsField;
    private JDataDisplayMoneyField totailMoney;
	public CBeginSubjectDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
	{
		super(parent, dataSource, dbSupport);
        initCs();
        table.setRowSelectionAllowed(false);
        table.setRowHeight(22);
	}
	public CBeginSubjectDialog(Frame parent, IDbSupport dbSupport)
	{
		super(parent, new DefaultDataSource("id"), dbSupport);
		initCs();
        table.setRowSelectionAllowed(false);
        table.setRowHeight(22);  
	}
	
	private void initCs()
	{
		this.setTitle("科目期初单");
	}
	protected void initColumns()
	{
		ITableColumnModel columnModel = table.getTableColumnModel();
		JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("项目");
		column.setColumnName("subject");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("项目名称");
		column.setColumnName("subject.name");
		column.setWidth(120);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("帐面余额");
		column.setColumnName("accountMoney");
		column.setWidth(80);
		columnModel.addColumn(column);
        column.setValueType(Currency.class);
		column = new JDataTableColumn();
		column.setHeaderText("余额");
		column.setColumnName("remainMoney");
		column.setWidth(150);
        column.setValueType(Double.class);
        column.setAlignmentX(JLabel.RIGHT);
        column.setEditable(true);
		columnModel.addColumn(column);	
		column = new JDataTableColumn();
		column.setHeaderText("余额差额");
		column.setColumnName("balanceMoney");
		column.setWidth(50);
        column.setValueType(Currency.class);
		columnModel.addColumn(column);
		column = new JDataTableColumn();
		column.setHeaderText("备注");
		column.setColumnName("comments");
		column.setWidth(150);
        column.setEditable(true);
		columnModel.addColumn(column);
	}
    
	public CPanel getMainPanel()
	{
		CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(700, 100));
		topPanel.setLayout(null);
        idField = new JDataField("number",String.class,this.dataSource);
        idField.setUpdateToDataSource(false);
        idField.setEditable(false);
		formDateField = new JDataDatePicker("formDate", this.dataSource);
//        employeeField = new JDataTableComboBox("employee", Employee.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
//        employeeField.setRequired("经手人", true);
//        departmentField = new JDataComboBox("department", String.class, this.dataSource);
		commentsField = new JDataField("comments", String.class, this.dataSource);
        commentsField.setMaxLength(255);
		int y = 10;
		int x = 80;
        topPanel.addComponent(idField, x, y, 150, 20, "单据编号", 60);
        topPanel.addComponent(formDateField, x + 220, y, 100, 20, "发生日期", 60);
        y += ROW_SPAN;
        topPanel.addComponent(employeeField, x, y, 150, 20, "经手人", 60);
        topPanel.addComponent(departmentField, x + 220, y, 150, 20, "部门", 60);
        y += ROW_SPAN;
		topPanel.addComponent(commentsField, x, y, 670, 20, "备注", 60);
		return topPanel;
	}
    protected CPanel getBottomPane()
    {
        CPanel bPanel = new CPanel();
        bPanel.setPreferredSize(new Dimension(700, 50));
		bPanel.setLayout(null);
        totailMoney = new JDataMoneyField("totalMoney", this.dataSource, this.dbSupport);
		int y = 10;
		int x = 80;
         bPanel.addComponent(totailMoney, x + 560, y, 200, 20, "金额合计", 60);
        return bPanel;
    }
   @Override
    protected DataTableCellEditor getMainEditor() {
        if(mainCellEditor == null)
        {
            CTableComboBox comboBox = new CTableComboBox(Main.getMainFrame().getObjectsPool().getBankSubjectTable(), "name");//temp
            mainCellEditor = new DefaultDataTableCellEditor(comboBox);
        }
        return mainCellEditor;
    }
    @Override
    protected Class getDetailClass() {
        return InitialSubjectDetail.class;
    }
    protected String getTableKeyName()
    {
        return "subject";
    }
    @Override
	public void newDataRow()
	{
        InitialSubject po = new InitialSubject();
		po.setCompany(Main.getMainFrame().getCompany());
		IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
		dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
		this.dataSource.insertDataRow(dataRow);
        findDetailedRow();
        
	}
    protected void findDetailedRow()
    {
        IDataRow mainDataRow = this.dataSource.getCurrentDataRow();
        IAccountingService service = Main.getServiceManager().getAccountingService();
        List<Subject> subjects = service.getAllSubject(Main.getMainFrame().getCompany());
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Subject subject:subjects)
        {
            SubjectAccount account = service.findSubjectAccount(subject);
            if (account == null)
            {
                account = new SubjectAccount();
                account.setSubject(subject);
                account.setCompany(subject.getCompany());
                service.saveSubjectAccount(account);
            }
            InitialSubjectDetail detail = new InitialSubjectDetail();
            detail.setSubject(subject);
            detail.setMainObject((InitialSubject)((ObjectDataRow)mainDataRow).getUserObject());
            detail.setAccountMoney(account.getRemainMoney());
            detail.setRemainMoney(account.getRemainMoney());//??????????
            IDataRow dr = ObjectDataRow.newDataRow(detail, "id", null);
            dataRows.add(dr);
        }
        if (dataRows.size() > 0)
        {
            this.table.getDataSource().insertDataRows(dataRows);
        }
        updateTotalMoney();
    }
    @Override
    protected void updateTotalMoney() {
        IDataSource tableSource = this.table.getDataSource();
        double money=0d;
        for(IDataRow dataRow:tableSource.getDataRows())
        {
            Double rMoney = (Double)dataRow.getColumnValue("remainMoney");
            if (rMoney != null)
            {
                money += rMoney;
            }
        }
        this.totailMoney.setMoney(money);
    }
    private DataTableCellEditor  mainCellEditor;
    @Override
    protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setNumber(idField.getText());
        vo.setFromDate((Date)formDateField.getSelectedItem());
        vo.setChargePerson(employeeField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentsField.getText());
        vo.setTotalMoney(totailMoney.getText());
        vo.setTitle("科目余额期初单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/BeginSubjectDetail.jasper"),variables, this.table.getDataSource().getDataRows());
		printDialog.setVisible(true);
    }
   
}