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

package org.free_erp.client.ui.forms.basic;

import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseInfoManageWindow;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectCatalog;
import org.free_erp.service.entity.system.Permission;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author afa
 */
public class CSubjectManageWindow extends CBaseInfoManageWindow
{
    private CSubjectDialog dialog;
    public CSubjectManageWindow(String title)
	{
		super(title);
        super.newTypeButton.setVisible(false);
        super.editTypeButton.setVisible(false);
        super.removeTypeButton.setVisible(false);
        super.stopButton.setVisible(false);
        super.activeButton.setVisible(false);
        this.initTableColumns();
		initDatas();
        this.newButton.setText("新增科目(&N)");
        this.removeButton.setText("删除科目(&D)");
        this.importButton.setVisible(false);

        this.newMenuItem.setText(this.newButton.getText());
        this.removeMenuItem.setText(this.removeButton.getText());
        this.editMenuItem.setText("修改科目信息(&M)");
        this.typesTree.removeMouseListener(typeMouseAdapter);
	}
    
   
    protected void initTableColumns()
	{
		ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
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

	}
    @Override
    protected void initTypes()
    {
        List<SubjectCatalog> catalogs = Main.getServiceManager().getAccountingService().getAllSubjectCatalog();
        this.typeDataSource = DefaultDataSource.createDataSource("id", catalogs, dbSupport);
        MutableTreeNode root = new DefaultMutableTreeNode("科目类别");
	    this.treeModel.setRoot(root);
    }

    @Override
	protected void doAdd()
	{
        this.dialog = new CSubjectDialog(this, this, this.dataSource, dbSupport);
        if (this.dialog.newDataRow())
        {
            dialog.setVisible(true);
        }
		this.dataSource.clearTempDataRows();

	}

    @Override
    protected void updateDatas()
    {
        Main.getMainFrame().getDataBaseObjectPool().refreshSubjects();
    }

    @Override
	protected void doEdit()
	{
         
        if (this.dataTable.getSelectedRow() < 0)
            {
                MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
                return;
            }
			this.dialog = new CSubjectDialog(this, this, this.dataSource, this.dbSupport);
            this.dialog.saveAndNewButton.setEnabled(true);
            this.dialog.saveButton.setEnabled(true);
            this.dataSource.setCurrentDataRow(this.dataTable.getSelectedDataRow());
            dialog.setVisible(true);
    }

    @Override
    protected void initDatas()
    {
        IDataSource model = this.dataTable.getDataSource();
		List<Subject> subjects = Main.getMainFrame().getDataBaseObjectPool().getSubjects();
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Subject subject:subjects)
		{
			IDataRow dataRow = new ObjectDataRow(subject, "id", dbSupport);
			dataRows.add(dataRow);
		}
        model.insertDataRows(dataRows);
    }

    @Override
	protected void doPrint()
    {
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("会计科目报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/SubjectManage.jasper"),variables, this.dataSource.getDataRows());
		printDialog.setVisible(true);
    }
}
