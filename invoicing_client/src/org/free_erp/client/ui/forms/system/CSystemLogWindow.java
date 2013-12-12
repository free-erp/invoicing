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

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.report.CBaseQueryWindow;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.vo.SystemLogQueryVo;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CSystemLogWindow extends CBaseQueryWindow implements ActionListener
{
    private JDataDatePicker beginDateField;
    private JDataDatePicker endDateField;
    private JDataComboBox usernameField;
    private CButton clearAllButton;

    public CSystemLogWindow(String title)
    {
        super(title);
        this.printButton.setVisible(false);
        usernameField.setSelectedItem("");
    }

    @Override
    protected void clearInfo()
    {
         beginDateField.setSelectedItem("");
         endDateField.setSelectedItem("");
         usernameField.setSelectedItem("");
    }

    @Override
    protected void select()
    {
        ITableModel model = this.dataTable.getTableModel();
        Company company = Main.getMainFrame().getCompany();
        SystemLogQueryVo vo = new SystemLogQueryVo(company);
        Object obj = null;
        if(!beginDateField.getEditorText().equals(""))
        {
            obj = beginDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date startDate = (Date)obj;
                vo.setStartDate(startDate);
            }
        }

        if(!endDateField.getEditorText().equals(""))
        {
            obj = endDateField.getSelectedItem();
            if(obj != null && obj instanceof Date)
            {
                Date endDate = (Date)obj;
                vo.setEndDate(endDate);
            }
        }
        obj = usernameField.getSelectedItem();
        if(obj != null && obj instanceof String)
        {
            String username = (String)obj;
            vo.setUserName(username);
        }

        this.dataSource.clear();
        ISystemManageService service = Main.getServiceManager().getSystemManageService();

        List<SystemLog> systemLogs = service.findSystemLogs(vo);
        if(systemLogs == null || systemLogs.size() == 0)
        {
            this.clearInfo();
        }
        for(SystemLog po:systemLogs)
		{
			IDataRow dataRow = new ObjectDataRow(po, "id", null);
			model.insertDataRow(dataRow);
		}
    }

    @Override
    protected CPanel getMainPanel()
    {
        CPanel topPanel = new CPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setPreferredSize(new Dimension(700, 40));
		topPanel.setLayout(null);
        beginDateField = new JDataDatePicker();
        beginDateField.setSelectedItem("");
        endDateField = new JDataDatePicker();
        endDateField.setSelectedItem("");
        clearAllButton = new CButton("删除所有日志(&D)");
        clearAllButton.addActionListener(this);
        usernameField = new JDataComboBox(String.class, Main.getMainFrame().getObjectsPool().getEmployees());

        int x = 60;
        int y = 10;
        topPanel.addComponent(beginDateField, x, y, 100, 20, "查询时间", 50);
        topPanel.addComponent(endDateField, x + 120, y, 100, 20, "至", 20);
        topPanel.addComponent(usernameField, x + 270, y, 100, 20, "用户名", 50);

        topPanel.addComponent(selectButton, x + 375, y, 75, 25);
        topPanel.addComponent(clearButton, x + 455, y, 75, 25);
        topPanel.addComponent(clearAllButton, x + 535, y, 125, 25);
		return topPanel;
    }

    @Override
    protected void initColumns()
    {
        ITableColumnModel columnModel = this.dataTable.getTableColumnModel();
	    JDataTableColumn column = new JDataTableColumn();
	    column.setHeaderText("用户名称");
	    column.setColumnName("user.name");
	    column.setWidth(80);
	    columnModel.addColumn(column);

	    column = new JDataTableColumn();
	    column.setHeaderText("时间");
	    column.setColumnName("formDate");
	    column.setWidth(160);
        column.setValueType(Timestamp.class);
	    columnModel.addColumn(column);

        column = new JDataTableColumn();
	    column.setHeaderText("操作内容");
	    column.setColumnName("content");
	    column.setWidth(260);
	    columnModel.addColumn(column);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {

		Object source = e.getSource();
		if(source == selectButton) {
			this.select();
		} else if (source == clearButton) {
			clearInfo();
		}
        else if (source == clearAllButton) {
			deleteAllLog();
		}
        else
        {
            super.actionPerformed(e);
        }
    }

    public void deleteAllLog()
    {
        if (MessageBox.showQuestionDialog(this, "您确信要删除所有的日志吗?") == 0)
        {
            ISystemManageService service = Main.getServiceManager().getSystemManageService();
            service.deleteSystemLogs(Main.getMainFrame().getCompany());
            this.select();
        }
    }

   
}
