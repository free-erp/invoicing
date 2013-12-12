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
package org.free_erp.client.ui.forms.accounting;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CTableComboBox;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataMoneyField;
import com.jdatabeans.beans.data.JDataTableComboBox;
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
import org.free_erp.service.entity.accounting.BankCash;
import org.free_erp.service.entity.accounting.BankCashDetail;
import org.free_erp.service.entity.accounting.Subject;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author afa
 */
public class CBankCashDialog extends CBaseAccountingMainDetailDialog {

    private JDataDatePicker formDateField;
    private JDataField commentsField;
    private JDataTableComboBox payAccountField;
    private JDataMoneyField payMoney;
    private JDataMoneyField totailMoney;

    public CBankCashDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource, dbSupport);
        initCs();
    }

    public CBankCashDialog(Frame parent, IDbSupport dbSupport) {
        super(parent, dbSupport);
        initCs();
    }

    private void initCs() {
        this.setTitle("银行转帐单");
    }

    protected void initColumns() {
        ITableColumnModel columnModel = table.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
        column.setHeaderText("收款帐户");
        column.setColumnName("subject");
        column.setWidth(120);
        columnModel.addColumn(column);
        column.setCellEditor(this.getMainEditor());
        column.setEditable(true);
        column.setValueType(Subject.class);

        column = new JDataTableColumn();
        column.setHeaderText("帐户名称");
        column.setColumnName("subject.name");
        column.setWidth(200);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("金额");
        column.setColumnName("totalMoney");
        column.setValueType(Currency.class);
        column.setWidth(120);
        column.setTotalRowVisible(true);
        column.setEditable(true);
        columnModel.addColumn(column);

        column = new JDataTableColumn();
        column.setHeaderText("备注");
        column.setColumnName("comments");
        column.setWidth(150);
        column.setEditable(true);
        columnModel.addColumn(column);
    }

    public CPanel getMainPanel() {
        CPanel topPanel = new CPanel();
        topPanel.setPreferredSize(new Dimension(700, 100));
        topPanel.setLayout(null);
        idField = new JDataField("number", String.class, this.dataSource);
        idField.setUpdateToDataSource(false);
        idField.setEditable(false);
        formDateField = new JDataDatePicker("formDate", this.dataSource);
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

    protected CPanel getBottomPane() {
        CPanel bPanel = new CPanel();
        bPanel.setPreferredSize(new Dimension(700, 50));
        bPanel.setLayout(null);

        payAccountField = new JDataTableComboBox("subject", Subject.class, this.dataSource, Main.getMainFrame().getObjectsPool().getBankSubjectTable(), "name");
        payAccountField.setRequired("付款帐号", true);
        payMoney = new JDataMoneyField("affordMoney", this.dataSource, this.dbSupport);
        totailMoney = new JDataMoneyField("totalMoney", this.dataSource, this.dbSupport);

        int y = 10;
        int x = 80;
        bPanel.addComponent(payAccountField, x, y, 200, 20, "付款帐号", 60);
        bPanel.addComponent(payMoney, x + 270, y, 160, 20, "付款金额", 60);
        bPanel.addComponent(totailMoney, x + 520, y, 160, 20, "金额合计", 60);
        return bPanel;
    }

    @Override
    protected DataTableCellEditor getMainEditor() {
        if (mainCellEditor == null) {
            CTableComboBox comboBox = new CTableComboBox(Main.getMainFrame().getObjectsPool().getClonedBankSubjectTable(), "number");//temp
            mainCellEditor = new DefaultDataTableCellEditor(comboBox);
        }
        return mainCellEditor;
    }

    @Override
    protected Class getDetailClass() {
        return BankCashDetail.class;
    }

    protected String getTableKeyName() {
        return "subject";
    }

    @Override
    public void newDataRow() {
        BankCash po = new BankCash();
        po.setCompany(Main.getMainFrame().getCompany());
        IDataRow dataRow = ObjectDataRow.newDataRow(po, "id", this.dbSupport);
        dataRow.setColumnValue("company", Main.getMainFrame().getCompany());
        this.dataSource.insertDataRow(dataRow);
        newDetailedRow();
    }

    @Override
    protected void updateTotalMoney() {
        IDataSource tableSource = this.table.getDataSource();
        double money = 0d;
        for (IDataRow dataRow : tableSource.getDataRows()) {
            Double rMoney = (Double) dataRow.getColumnValue("totalMoney");
            if (rMoney != null) {
                money += rMoney;
            }
        }
        this.totailMoney.setMoney(money);
        this.payMoney.setMoney(money);
    }
    private DataTableCellEditor mainCellEditor;

    @Override
    protected void doPrint() {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setNumber(idField.getText());
        vo.setFromDate((Date) formDateField.getSelectedItem());
        vo.setChargePerson(employeeField.getText());
        vo.setDepartment(departmentField.getText());
        vo.setComments(commentsField.getText());
        vo.setPayAccount(payAccountField.getText());
        vo.setPayMoney(payMoney.getText());
        vo.setTotalMoney(totailMoney.getText());
        vo.setTitle("现金银行转帐单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/CommonExpenseDetail.jasper"), variables, this.table.getDataSource().getDataRows());
        printDialog.setVisible(true);
    }

}
