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

package org.free_erp.client.ui.forms;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.DataRowChangedListener;
import com.jdatabeans.beans.data.DataRowEvent;
import com.jdatabeans.beans.data.DataRowRolledEvent;
import com.jdatabeans.beans.data.DataRowRolledListener;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.DataTableCellEditor;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.exception.LogicException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author afa
 */
public abstract class CBaseAccountingMainDetailDialog extends CBaseFormDialog
{
    protected JDataTable table;
    private Handler handler;
    protected JDataField idField;
    protected JDataTableComboBox employeeField;
    protected JDataField departmentField;
    

    public CBaseAccountingMainDetailDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
		super(parent, dataSource, dbSupport);
        initComps();
    }
    public CBaseAccountingMainDetailDialog(Frame parent, IDbSupport dbSupport)
    {
		super(parent, new DefaultDataSource("id"), dbSupport); 
		initComps();
        this.nextButton.setVisible(false);
        this.previousButton.setVisible(false);
        this.firstButton.setVisible(false);
        this.lastButton.setVisible(false);
	}
    private void initComps()
    {
        //
       
        //
        table = new JDataTable();
        table.setEditable(true);
        table.setShowSelectedRow(false);
		JScrollPane tablePane = new JScrollPane(table);
                pagePane.add(tablePane);
                tablePane.setBounds(CPagePane.START_X , CPagePane.Y + 70, 860, 300);
                tablePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel bPanel = this.getBottomPane();
                pagePane.add(bPanel);
                bPanel.setBounds(CPagePane.START_X, CPagePane.Y + 380, 860, 50);

        initColumns();
        this.installListeners();
        this.table.setShowTotalRow(true);
        table.setRowHeaderWidth(40);

    }
    private void installListeners()
    {
        handler = new Handler();
        table.addMouseListener(handler);
        table.addKeyListener(handler);
        table.getDataSource().addDataRowChangedListener(handler);
        this.dataSource.addDataRowRolledListener(handler);
        this.employeeField.addValueChangedListener(handler);
        DataTableCellEditor editor = getMainEditor();
        if (editor != null)
        {
            editor.addCellEditorListener(handler);
        }
    }
    private void deleteTableCurrentRow()
    {
        if (this.table.getSelectedRow() >= 0 && this.table.getDataSource().getRowCount() > 1)
        {
            if (MessageBox.showQuestionDialog(this, "您确定要删除当前行吗？") == 0)
            {
                this.table.getDataSource().removeDataRow(this.table.getSelectedDataRow());
            }
        }
    }

    class Handler implements MouseListener, KeyListener, CellEditorListener, DataRowRolledListener, DataRowChangedListener, ValueChangedListener
    {

        
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() >= 2)
            {
                if (table.getSelectedDataRow() == null)
                {
                    //addNewDetailedRow();
                }
            }
        }

        
        public void mouseEntered(MouseEvent e) {

        }

        
        public void mouseExited(MouseEvent e) {

        }

        
        public void mousePressed(MouseEvent e) {

        }

        
        public void mouseReleased(MouseEvent e) {

        }

        
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DELETE)
            {
                deleteTableCurrentRow();
            }
            else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                addNewDetailedRow();
            }
        }

        
        public void keyReleased(KeyEvent e) {          
            
        }

        
        public void keyTyped(KeyEvent e) {
            
        }

        
        public void editingCanceled(ChangeEvent e) {

        }

        
        public void editingStopped(ChangeEvent e) {
            addNewDetailedRow();
        }

        
        public void rowRolled(DataRowRolledEvent evt) {
            refreshDatas();
        }

        
        public void rowDeleted(DataRowEvent evt) {
            updateTotalMoney();
        }

        
        public void rowInserted(DataRowEvent evt) {
            updateTotalMoney();
        }

        
        public void rowUpdated(DataRowEvent evt) {
            updateTotalMoney();
        }

        
        public void valueChanged(ValueChangedEvent evt) {
            if (evt.getSource() == employeeField)
            {
                Object v = employeeField.getSelectedItem();
                if (v instanceof Employee)
                {
                    Employee employee = (Employee)v;
                    if (employee != null)
                    {
                        departmentField.setText(employee.getCatalog().getName());
                    }
                }
            }
        }


    }

    protected void updateTotalMoney()
    {
        
    }

    protected void refreshDatas()
    {
         IDataRow mainDataRow = this.dataSource.getCurrentDataRow();
         if (mainDataRow != null)
         {
             table.getDataSource().clear();
             Set details = (Set)mainDataRow.getColumnValue("details");
             List<IDataRow> dataRows = new ArrayList<IDataRow>();
             for(Object obj:details)
             {
                 IDataRow dataRow = ObjectDataRow.newDataRow(obj, "id", null);
                 dataRows.add(dataRow);
             }
             table.getDataSource().insertDataRows(dataRows);
         }
    }
    
    protected boolean doSave()
    {
		try {
			if (this.table.getTableModel().getRowCount() <= 0) {
				MessageBox.showMessageDialog(this, "没有添加任何子信息!");
				return false;
			}
            IDataRow mainDataRow = this.dataSource.getCurrentDataRow();
            Set details = (Set)mainDataRow.getColumnValue("details");
            List<IDataRow> tableDataRows =  table.getDataSource().getDataRows();
            details.clear();
            for(IDataRow dataRow:tableDataRows)
            {

               
                if (dataRow.getColumnValue(getTableKeyName()) != null)
                {
                    if (dataRow.getColumnValue("mainObject") == null)
                    {
                        dataRow.setColumnValue("mainObject", ((ObjectDataRow)mainDataRow).getUserObject());
                    }
                    Object obj = ((ObjectDataRow)dataRow).getUserObject();
                    details.add(obj);                    
                }
            }
            
            if (details.size() == 0)
            {
                MessageBox.showMessageDialog(this, "没有添加任何子信息!");
				return false;
            }
           	this.save();
            return true;
		} catch (FieldRequiredException ex) {
			MessageBox.showMessageDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"不能为空，或者所填写的内容在数据库中不存在！");
            return false;
		}
        catch(LogicException ex){
        MessageBox.showMessageDialog(this, "付款账号为空!");
            return false;
        }
	}
    
    protected void doNext() {
		if (!checkSave()) {
			return;
		}
		this.dataSource.next();	

	}
	protected void doPrevious() {
		if (!checkSave()) {
			return;
		}
		this.dataSource.previous();
		
	}
	protected void doLast() {
		if (!checkSave()) {
			return;
		}
		this.dataSource.last();
		

	}
	protected void doFirst() {
		if (!checkSave()) {
			return;
		}
		this.dataSource.first();

		
	}

    protected void doCheck()
    {
        if (Main.getMainFrame().getUser().getCheckPermission() != 1)
        {
            MessageBox.showErrorDialog(this, "您不具备过单审核权限，请与管理员联系！");
            return;
        }
        if (MessageBox.showQuestionDialog(this, "您确信审核通过当前单据?") == JOptionPane.YES_OPTION) {
			this.save();
			Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
			try {
                Main.getServiceManager().getAccountingService().passAccountingForm(po);
                FormPo p = (FormPo)po;
                this.logContent = "审核";
                this.writeSystmLog(p);
                ((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("statusString");
                ((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("status");
				this.getDataSource().refresh();
				this.refresh();
                MessageBox.showMessageDialog(this, "审核通过!");
			} catch (RuntimeException ex) {
				MessageBox.showMessageDialog(this, ex.getMessage());
                //ex.printStackTrace();
				//ex.printStackTrace();
			}
		}
    }
    /**
	 *
	 * @return
	 */
	protected boolean checkSave() {
		try {
			if (this.dataSource == null || this.dataSource.getCurrentDataRow() == null) {
				return true;
			}

			if (this.isModified() || this.dataSource.getCurrentDataRow().isModified()) {
				int type = MessageBox.showQuestionDialog(this, "尚未存盘,您要保存吗?");
				if (type == 0) {
					return this.doSave();
				} else if (type == 1) {
                    this.modified = false;
					this.dataSource.clearTempDataRows();

				} else {
					//should never come here!
				}
			}
		} catch (Exception ex) {
			MessageBox.showErrorDialog(this, "存盘失败，原因:" + ex.getMessage());
			return false;
		}
		return true;
	}

    protected void doDiscard() {
		if (MessageBox.showQuestionDialog(this, "您确信作废当前单据?") == JOptionPane.YES_OPTION) {
			IDataRow dataRow = this.dataSource.getCurrentDataRow();
			dataRow.setColumnValue("status", ServiceConstants.DISCARD_STATUS);
			this.save();
			this.refresh();
            Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
            FormPo p = (FormPo)po;
            this.logContent = "作废";
            this.writeSystmLog(p);
		}
	}
	protected void doActive()
	{
		IDataRow dataRow = this.dataSource.getCurrentDataRow();
		dataRow.setColumnValue("status", ServiceConstants.DRAFT_STATUS);
		this.save();
		this.refresh();
        Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
        FormPo p = (FormPo)po;
        this.logContent = "激活";
        this.writeSystmLog(p);
	}

    protected void doClose() {
		if (checkSave()) {
			this.dispose();
		}
	}


	
	public void closeDialog(WindowEvent e) {
        this.doClose();
	}

    protected void addNewDetailedRow()
    {
        IDataRow dataRow = table.getDataSource().getCurrentDataRow();
        if (dataRow != null)
        {
//            if (dataRow.getColumnValue("name") != null) //temp写死都依number来判断
//            {
                if (!hasEmptyLine())
                {
                    newDetailedRow();
                }
            //}
        }

    }
    protected void newDetailedRow()
    {
         try
         {
             Class cls = getDetailClass();
             Object instance = cls.newInstance();
             IDataRow dataRow = ObjectDataRow.newDataRow(instance, "id", null);
             table.getDataSource().insertDataRow(dataRow);
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
    }
    protected DataTableCellEditor getMainEditor()
    {
        return null;
    }
    protected boolean hasEmptyLine()
    {
        IDataSource tableSource = table.getDataSource();
        int count = 0;
        for(IDataRow dataRow:tableSource.getDataRows())
        {
            if (dataRow.getColumnValue(getTableKeyName()) == null)
            {
                count++;
            }
        }
        if (count > 0)
        {
            return true;
        }
        return false;
    }
    protected String getTableKeyName()
    {
        return "name";
    }
    public void setVisible(boolean visible)
    {
        if (visible && (idField.getText() == null || idField.getText().trim().equals("")))
        {
            idField.setText("自动编号");
        }
        updateTotalMoney();
        super.setVisible(visible);
    }

    
	protected void refresh() {
        super.refresh();
		IDataRow dataRow = this.dataSource.getCurrentDataRow();
		Object status = dataRow.getColumnValue("status");
        if (status != null) {
			int s = (Integer) status;
			if (s == ServiceConstants.FORMAL_STATUS) {
				this.table.setEnabled(false);
				return;
			} else if (s == ServiceConstants.DISCARD_STATUS) {
				this.table.setEnabled(false);
				return;
			}
            this.table.setEnabled(true);
		}
	}
    protected void beforeInitComponent()
    {
        employeeField = new JDataTableComboBox("employee", Employee.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
        employeeField.setRequired("经手人", true);
        departmentField = new JDataField("department", String.class, this.dataSource);
        departmentField.setEditable(false);
    }

    protected abstract CPanel getBottomPane();
    protected abstract void initColumns();
	protected abstract Class getDetailClass();

}
