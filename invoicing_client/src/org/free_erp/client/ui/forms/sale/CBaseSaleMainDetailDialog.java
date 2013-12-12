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

package org.free_erp.client.ui.forms.sale;
import com.jdatabeans.beans.data.DataRowEvent;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.ValueChangedEvent;
import com.jdatabeans.beans.ValueChangedListener;
import com.jdatabeans.beans.data.DefaultDataSource;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDatePicker;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataDisplayMoneyField;
import com.jdatabeans.beans.data.JDataMoneyField;
import com.jdatabeans.beans.data.JDataNumberField;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableModel;
import com.jdatabeans.beans.data.table.JDataTableModel;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseFormDialog;
import org.free_erp.client.ui.forms.CBaseProductSelectDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.accounting.AffordType;
import org.free_erp.service.entity.accounting.ClearingType;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.Customer;
import org.free_erp.service.entity.base.Employee;
import org.free_erp.service.entity.base.Storage;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.sale.SalePo;
import org.free_erp.service.exception.InitialLackedException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 *
 * @author tzl
 */
public abstract class CBaseSaleMainDetailDialog extends CBaseFormDialog implements ItemListener,ValueChangedListener
{
	//protected JCheckBox stopButton;
	protected JDataMoneyField sumField;
	protected JDataTable table;
	protected CButton addButton;
	protected CButton modifyButton;
	protected CButton removeButton;
    protected Company company;
    protected Storage storage;
   protected JPopupMenu mainPopMenu;

    //for quick
    protected JDataField idField;
    protected JDataTableComboBox supplierField;
    protected JDataTableComboBox adminField;
	protected JDataField departmentField;
    protected JDataTableComboBox storageField;
    protected JDataDisplayMoneyField chineseMoneyField;
    protected JDataDatePicker inDateField;
    protected JDataTableComboBox payFashionField;
    protected JDataTableComboBox balanceFashionField;
    protected CBaseProductSelectDialog productSelectDialog;
    protected JDataNumberField offersMoneyField;
    protected JDataNumberField payTimeField;
	//protected CBaseProductSelectDialog dialog;
	public CBaseSaleMainDetailDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
		super(parent, dataSource, dbSupport);

		initComps();
		this.setModal(true);
		this.setResizable(true);
		this.initColumns();
		this.table.getDataSource().setKeyColumnName("id");
		this.refreshTableDataSource();
        this.company = Main.getMainFrame().getCompany();
        
	}
	
	public CBaseSaleMainDetailDialog(Frame parent, IDbSupport dbSupport)
    {
		super(parent, new DefaultDataSource("id"), dbSupport); 
		initComps();
		this.setModal(true);
		this.setResizable(true);
		this.initColumns();
		this.table.getDataSource().setKeyColumnName("id");
		this.refreshTableDataSource();
        this.company = Main.getMainFrame().getCompany();
        
        this.nextButton.setVisible(false);
        this.previousButton.setVisible(false);
        this.firstButton.setVisible(false);
        this.lastButton.setVisible(false);
	}
    
    
   

 

	protected void initComps()
      {

        table = new JDataTable();
        table.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() >= 2)
				{
                    if(modifyButton.isEnabled())
                    {
                        doEdit();
                    }
				}
			}
			public void mouseEntered(MouseEvent e)
			{
			}
			public void mouseExited(MouseEvent e)
			{
			}
			public void mousePressed(MouseEvent e)
			{
			}
			public void mouseReleased(MouseEvent e)
			{
			}
		});
		JScrollPane tablePane = new JScrollPane(table);
                pagePane.add(tablePane);
                tablePane.setBounds(CPagePane.START_X , CPagePane.Y + 70, 860, 280);
                tablePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		CPanel bPanel = new CPanel();
		bPanel.setPreferredSize(new Dimension(500, 40));
		bPanel.setLayout(null);

		this.addButton = new CButton("添加商品(&A)");
		this.modifyButton = new CButton("修改商品(&M)");
		this.removeButton = new CButton("删除商品(&D)");
          
		bPanel.addComponent(this.addButton, 860- 320, 10, 100, 22);
		bPanel.addComponent(this.modifyButton, 860 -210, 10, 100, 22);
                bPanel.addComponent(this.removeButton, 860- 100, 10, 100, 22);

		pagePane.add(bPanel);
                bPanel.setBounds(CPagePane.START_X, CPagePane.Y + 350, 860, 80);

        Font font = new Font("宋体", Font.PLAIN, 14);

        offersMoneyField = new JDataNumberField("offersMoney", Double.class, this.dataSource);
        offersMoneyField.setForeground(Color.red);
        offersMoneyField.setHorizontalAlignment(JLabel.RIGHT);
        offersMoneyField.setFont(font);
        offersMoneyField.addValueChangedListener(this);
        int x = 80;
		bPanel.addComponent(offersMoneyField, x, 40, 140, 25, "优惠金额", 60);
        sumField = new JDataMoneyField("totalMoney", this.dataSource, this.dbSupport);
        sumField.setForeground(Color.red);
        sumField.setHorizontalAlignment(JLabel.RIGHT);
        sumField.setFont(font);
		bPanel.addComponent(sumField, x + 260, 40, 160, 25, "元  合计金额", 100);
        chineseMoneyField = new JDataDisplayMoneyField(JDataDisplayMoneyField.CHINESE_MONEY, "totalMoney", this.dataSource);
        chineseMoneyField.setBorder(sumField.getBorder());
        chineseMoneyField.setForeground(Color.red);
        chineseMoneyField.setFont(font);
        bPanel.addComponent(chineseMoneyField, x + 490, 40, 190, 25, "大写:", 50);

		//closeButton.setIcon(mainFrame.getIcon("close"));
		//this.toolBar.add(chooseSavedButton);
		this.addButton.addActionListener(this);
		this.modifyButton.addActionListener(this);
		this.removeButton.addActionListener(this);
          payTimeField.addValueChangedListener(this);

          this.table.setShowTotalRow(true);

	}


    @Override
	protected void doChoose()
    {
		MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
	}

    @Override
	protected boolean doSave()
    {
		try
        {
			//this.saveChildren();
			if (this.table.getTableModel().getRowCount() <= 0)
            {
				MessageBox.showMessageDialog(this, "没有添加任何商品信息,请先添加商品信息!");
				return false;
			}
			this.save();
            return true;
		}
        catch (FieldRequiredException ex)
        {
			MessageBox.showMessageDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"不能为空!");
            return false;
		}
	}

	protected void saveChildren() {
		/*
		ObjectDataRow mainDataRow = (ObjectDataRow) this.dataSource.getCurrentDataRow();
		IDataSource detailDataSource = this.table.getDataSource();
		List<IDataRow> dataRows = detailDataSource.getDataRows();
		Set details = (Set) mainDataRow.getColumnValue("details");
		for (IDataRow dataRow : dataRows) {
		dataRow.setColumnValue("mainObject", mainDataRow.getUserObject());
		details.add(((ObjectDataRow) dataRow).getUserObject());
		}
		 */
	}

    @Override
	protected void doNext()
    {
		if (!checkSave())
        {
			return;
		}
		this.dataSource.next();
		this.refreshTableDataSource();
	}

    @Override
	protected void doPrevious()
    {
		if (!checkSave())
        {
			return;
		}
		this.dataSource.previous();
		this.refreshTableDataSource();
	}

    @Override
	protected void doLast()
    {
		if (!checkSave())
        {
			return;
		}
		this.dataSource.last();
		this.refreshTableDataSource();
	}

    @Override
	protected void doFirst()
    {
		if (!checkSave())
        {
			return;
		}
		this.dataSource.first();
		this.refreshTableDataSource();
	}

    @Override
	protected void doClear()
    {
		MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
	}

    @Override
	protected void doPrint()
    {
		MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
	}

    @Override
	protected void doStop()
    {
		MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
	}

    @Override
	protected void doClose()
    {
		if (checkSave()) {
			this.dispose();
		}
	}

    @Override
	protected void doCheck()
    {
        if (Main.getMainFrame().getUser().getCheckPermission() != 1)
        {
            MessageBox.showErrorDialog(this, "您不具备过单审核权限，请与管理员联系！");
            return;
        }
		if (MessageBox.showQuestionDialog(this, "您确信审核通过当前单据?") == JOptionPane.YES_OPTION)
        {
			this.save();
			Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
			try
            {
                  if (po instanceof SalePo) {
                       SalePo p = (SalePo) po;
                       if(Main.getServiceManager().getSaleService().commonSale(p)){
                		MessageBox.showMessageDialog(this, "库存数量不能少于销售数量！");
                        return;
                       }
                       }
                Main.getServiceManager().getSaleService().passCommonSaleForm(po);
                FormPo p = (FormPo)po;
                this.logContent = "审核";
                this.writeSystmLog(p);
				((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("statusString");
                ((ObjectDataRow) this.dataSource.getCurrentDataRow()).refreshCacheProperty("status");
				this.getDataSource().refresh();
				this.refresh();
                MessageBox.showMessageDialog(Main.getMainFrame(), "审核通过!");
			}
            catch (InitialLackedException ex)
            {
                MessageBox.showMessageDialog(this, "“" + ex.getProduct() + "”" + ex.getMessage());
			}
            catch (RuntimeException ex)
            {
				MessageBox.showMessageDialog(this, "库存数量不能少于销售数量！");
				ex.printStackTrace();
			}
		}
	}

    @Override
	protected void doDiscard()
    {
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

    @Override
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
	/*
	 *统一命名为 details
	 */
	protected void refreshTableDataSource() {
		IDataRow dataRow = this.dataSource.getCurrentDataRow();
		if (dataRow == null) {
			return;
		}
		Object d = dataRow.getColumnValue("details");
		if (d != null && d instanceof Set) {

			Set details = (Set) d;
			ArrayList<IDataRow> dataRows = new ArrayList<IDataRow>();
			for (Object obj : details) {
				dataRows.add(new ObjectDataRow(obj.getClass(), obj, "id"));
			}
			//IDataSource detailDataSource = DefaultDataSource.createDataSource("id", details, null);
			IDataSource oldDataSource = this.table.getDataSource();
			oldDataSource.removeDataRowChangedListener(this);
			ITableModel model = new JDataTableModel(dataRows);
			this.table.setModel(model);
			this.table.getDataSource().setKeyColumnName("id");
			model.addDataRowChangedListener(this);
		} else {
			this.table.setDataSource(null);
		}
		this.refreshTotailMoney();

	}

    @Override
	public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
		Object source = e.getSource();
        if (source == this.addButton)
        {
		    this.doAdd();
		}
        else if (source == this.modifyButton)
        {
			this.doEdit();
		}
        else if (source == this.removeButton)
        {
			this.doRemove();
		}
	}

    public Storage getStorage()
    {
        return (Storage)this.storageField.getSelectedItem();
    }

    public CBaseProductSelectDialog getNewProductSelectDialog()
    {
        return new CSaleProductDialog(this, this.table.getDataSource(),null);
    }

    public boolean checkStorage()
    {
        Object obj = this.storageField.getSelectedItem();
        if (obj == null || !(obj instanceof Storage))
        {
            return false;
        }
        return true;
    }

    public boolean checkSupplier()
    {
        Object obj = this.supplierField.getSelectedItem();
        if (obj == null || !(obj instanceof Customer))
        {
            return false;
        }
        return true;
    }

    public boolean checkChargePerson()
    {
        Object obj = this.adminField.getSelectedItem();
        if (obj == null || !(obj instanceof Employee))
        {
            return false;
        }
        return true;
    }
    
	protected void doAdd()
    {
        if (!checkStorage())
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "请先选择仓库!");
            return;
        }
        if (!checkSupplier())
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "请先选择客户!");
            return;
        }
        if (!checkChargePerson())
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "请先选择业务员!");
            return;
        }
		CBaseProductSelectDialog dialog = this.getNewProductSelectDialog();
        dialog.setIsEditing(false);
		try {
			Class clz = this.getDetailClass();
			Object obj = clz.newInstance();
			IDataRow dataRow = ObjectDataRow.newDataRow(obj, "id", null);
			dataRow.setColumnValue("mainObject", ((ObjectDataRow) this.dataSource.getCurrentDataRow()).getUserObject());
			this.table.getDataSource().insertDataRow(dataRow);
			//this.table.getDataSource().last();
			dataRow.setTempDataRow(true);
            dialog.refreshArgs();//afa debug 2009-06-04
			dialog.setVisible(true);
			if (!dialog.isSaved())
            {
				this.table.getDataSource().clearTempDataRows();
			}
            else
            {
                ObjectDataRow objRow = (ObjectDataRow) this.getDataSource().getCurrentDataRow();
				Set details = (Set) objRow.getColumnValue("details");
				if (details != null)
                {
					details.add(((ObjectDataRow) dataRow).getUserObject());
				}
				this.refreshTotailMoney();
				this.modified = true;
                this.table.getDataSource().saveTempDataRows();
			}
			this.table.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

    protected SystemLog getSystemLog()//liufei
    {
        SystemLog systemlog = new SystemLog();
        systemlog.setCompany(Main.getMainFrame().getCompany());
        systemlog.setUser(Main.getMainFrame().getUser());
        systemlog.setFormDate(new Timestamp(System.currentTimeMillis()));
        return systemlog;
    }
	protected void doEdit()
    {
        if (!checkStorage())
        {
            MessageBox.showMessageDialog(Main.getMainFrame(), "请先选择仓库!");
            return;
        }
		if (this.table.getSelectedRow() < 0)
        {
			MessageBox.showMessageDialog(Main.getMainFrame(), "没有数据行被选中!");
			return;
		}
		CBaseProductSelectDialog dialog = this.getNewProductSelectDialog();//new CBaseProductSelectDialog(this, this.table.getDataSource());
        this.table.getDataSource().moveTo(this.table.getSelectedRow());
        dialog.setIsEditing(true);
        dialog.setTitle("修改商品");
		dialog.setVisible(true);
		if (dialog.isSaved())
        {
			//修改是对DataRow对象进行修改,所以不需要在此处做什么额外的事情
			this.modified = true;
            this.table.repaint();
			this.refreshTotailMoney();
		}
        dialog.setIsEditing(false);
	}
	protected void doRemove()
    {
		if (this.table.getSelectedRow() < 0)
        {
			MessageBox.showMessageDialog(Main.getMainFrame(), "没有数据行被选中!");
			return;
		}
		if (MessageBox.showQuestionDialog(Main.getMainFrame(), "您确信要删除当前记录吗?") == 0)
        {
			IDataRow dataRow = this.table.getSelectedDataRow();
			ObjectDataRow objRow = (ObjectDataRow) this.getDataSource().getCurrentDataRow();
			Set details = (Set) objRow.getColumnValue("details");
			details.remove(((ObjectDataRow) dataRow).getUserObject());
            try
            {
                this.save();
            }
            catch(Exception ex)
            {
                dataRow.setColumnValue("state", 0);
                return;
            }

			this.table.getTableModel().removeDataRow(this.table.getSelectedRow());
			this.modified = true;
			this.refreshTotailMoney();
		}
	}
	public void rowDeleted(DataRowEvent evt)
    {
		this.refreshTotailMoney();
		this.modified = true;
	}
	public void rowInserted(DataRowEvent evt)
    {
		this.refreshTotailMoney();
		this.modified = true;
	}
	public void rowUpdated(DataRowEvent evt)
    {
		this.refreshTotailMoney();
		this.modified = true;
	}
	/**
	 *
	 * @return
	 */
	protected boolean checkSave()
    {
		try {
			if (this.dataSource == null || this.dataSource.getCurrentDataRow() == null)
            {
				return true;
			}

			if (this.isModified() || this.dataSource.getCurrentDataRow().isModified())
            {
				int type = MessageBox.showQuestionDialog(this, "尚未存盘,您要保存吗?");
				if (type == 0)
                {
					return this.doSave();
				}
                else if (type == 1)
                {
                    this.modified = false;
					this.dataSource.clearTempDataRows();
				}
                else
                {
					//should never come here!
				}
			}

		}
        catch (Exception ex)
        {
			MessageBox.showErrorDialog(this, "存盘失败，原因:" + ex.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void closeDialog(WindowEvent e)
    {
        this.doClose();
	}
	protected void refreshTotailMoney()
    {
		List<IDataRow> dataRows = this.table.getDataSource().getDataRows();
		double money = 0d;
		for (IDataRow dataRow : dataRows)
        {
			Double tm = (Double) dataRow.getColumnValue("totalMoney");
			if (tm != null)
            {
				money += tm;
			}
		}

    String offersMoney   = offersMoneyField.getText();
     Double  offers  =0.0;
    if(offersMoney != null && !offersMoney.trim().equals("")){
     offers  =Double.parseDouble(offersMoney);
         money = money - offers;
    }
		this.sumField.setMoney(money);
        this.chineseMoneyField.setMoney(money);
	}
	public abstract void newDataRow();
	protected abstract CPanel getMainPanel();
	protected abstract void initColumns();
	protected abstract Class getDetailClass();

    @Override
	protected void refresh()
    {
		IDataRow dataRow = this.dataSource.getCurrentDataRow();
		Object status = dataRow.getColumnValue("status");
		if (status != null)
        {
			int s = (Integer) status;
			if (s == ServiceConstants.FORMAL_STATUS)
            {
				this.discardButton.setEnabled(false);
				this.checkButton.setEnabled(false);
				this.addButton.setEnabled(false);
				this.saveButton.setEnabled(false);
				this.modifyButton.setEnabled(false);
				this.removeButton.setEnabled(false);
				setBindingComponentsEnable(false);
				this.table.setEnabled(false);
				this.activeButton.setEnabled(false);
				return;
			}
            else if (s == ServiceConstants.DISCARD_STATUS)
            {
				this.discardButton.setEnabled(false);
				this.checkButton.setEnabled(false);
				this.addButton.setEnabled(false);
				this.saveButton.setEnabled(false);
				this.modifyButton.setEnabled(false);
				this.removeButton.setEnabled(false);
				setBindingComponentsEnable(false);
				this.table.setEnabled(false);
				this.activeButton.setEnabled(true);
				return;
			}
            
			this.discardButton.setEnabled(true);
            this.checkButton.setEnabled(true);
            this.activeButton.setEnabled(false);
			this.addButton.setEnabled(true);
			this.saveButton.setEnabled(true);
			this.modifyButton.setEnabled(true);
			this.removeButton.setEnabled(true);
			setBindingComponentsEnable(true);
			this.table.setEnabled(true);
		}
	}
    public void valueChanged(ValueChangedEvent evt)
    {
        if(evt.getSource() == payTimeField){

            DecimalFormat df = new DecimalFormat("##");
           String  s = payTimeField.getText();
           Double ss= Double.parseDouble(s);
          String a =df.format(ss).toString();
          payTimeField.setText(a);
        }
        if (evt.getSource() == this.adminField)
        {
            this.refreshDepartment();
        }
           if (evt.getSource() == this.offersMoneyField)
        {
               try{
              if(Double.parseDouble(offersMoneyField.getText())>sumField.getMoney())
              {
                  MessageBox.showErrorDialog(this, "优惠金额不能大于总金额！");
                  offersMoneyField.setText("");
                   return;
              }
            this.refreshTotailMoney();
        } catch (Exception ex)
        {
			MessageBox.showErrorDialog(this, "请输入正确的格式！");
			offersMoneyField.setText("");
            return;
		}
    }
    }

    private void refreshDepartment()
    {
        Object v = this.adminField.getSelectedItem();
        if (v instanceof Employee)
        {
            Employee employee = (Employee)v;
            if (employee != null)
            {
                 this.departmentField.setText(employee.getCatalog().getName());
            }
            else
            {
                this.departmentField.setText("");
            }
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.adminField)
        {
            this.refreshDepartment();
        }
    }

    @Override
    public void setVisible(boolean visible)
    {
        //新建才会用
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow == null || dataRow.getColumnValue("id") == null)//判断是否新建
        {
            if (idField.getText() == null || idField.getText().equals(""))
            {
                idField.setText("自动编号");
            }
//            Employee employee = Main.getServiceManager().getEmployeeService().getEmployee(Main.getMainFrame().getCompany(), Main.getMainFrame().getUser().getName());
//            if (employee != null )
//            {
//                adminField.setSelectedItem(employee);
//                refreshDepartment();
//            }
//            else
//            {
//                adminField.setSelectedItem(Main.getMainFrame().getUser().getName());
//            }
            //add other info
            //暂时的，以后改成xml文件，保存用户最后使用的仓库,当前临时用第一个
//            if (this.storageField.getModel().getSize() > 0)
//            {
//                MutableComboBoxModel model = (MutableComboBoxModel)this.storageField.getModel();
//                for(int i = 0; i < model.getSize(); i++)
//                {
//                    if (model.getElementAt(i) != null)
//                    {
//                        this.storageField.setSelectedIndex(i);
//                        break;
//                    }
//                }
//            }
        }

        if (visible) {
			this.refreshTableDataSource();
			this.refresh();
		}
        super.setVisible(visible);
    }

    @Override
    public void dispose()
    {
        //this.table.getDataSource().dispose();
        super.dispose();
    }

    @Override
    protected void beforeInitComponent()
    {
         //added by afa 2009-06-03
        idField = new JDataField("number",String.class,this.dataSource);
        idField.setUpdateToDataSource(false);
        idField.setEditable(false);
        JDataTable storageTable = Main.getMainFrame().getObjectsPool().getStorageTable();
       storageField = new JDataTableComboBox("storage", Storage.class, this.dataSource, storageTable, "name");
        storageField.setRequired("仓库", true);
        JDataTable supplierTable = Main.getMainFrame().getObjectsPool().getCustomerTable();
        JDataTable chargePersonTable = Main.getMainFrame().getObjectsPool().getEmployeeTable();
          supplierField = new JDataTableComboBox("customer", Customer.class,this.dataSource,supplierTable , "name");
        supplierField.setRequired("客户", true);
        //supplierField.setEditable(false);
         adminField = new JDataTableComboBox("employee", Employee.class,this.dataSource,chargePersonTable , "name");;
        adminField.setRequired("业务员", true);
         adminField.addValueChangedListener(this);
        departmentField = new JDataField("department", String.class,this.dataSource);
        departmentField.setEnabled(false);
         payFashionField = new JDataTableComboBox("payFashion", AffordType.class,this.dataSource,Main.getMainFrame().getObjectsPool().getAffordTypeTable() , "name");
        payFashionField.setRequired("付款方式", true);
        balanceFashionField = new JDataTableComboBox("balanceFashion", ClearingType.class,this.dataSource,Main.getMainFrame().getObjectsPool().getClearingTypeTable() , "name");
        balanceFashionField.setRequired("结算方式", true);
        inDateField = new JDataDatePicker("formDate", this.dataSource);
        //storageField.addItemListener(this);
        //adminField.addItemListener(this);
    }
}






