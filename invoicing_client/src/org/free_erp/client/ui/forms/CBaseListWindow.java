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
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDatePicker;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ExcelUtilities;
import org.free_erp.client.ui.util.PermissionUtilities;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.storage.CheckStoragePo;
import org.free_erp.service.entity.storage.InStoragePo;
import org.free_erp.service.entity.storage.InitialStoragePo;
import org.free_erp.service.entity.storage.LossStoragePo;
import org.free_erp.service.entity.storage.MinMaxStoragePo;
import org.free_erp.service.entity.storage.MoveStoragePo;
import org.free_erp.service.entity.storage.OutStoragePo;
import org.free_erp.service.entity.storage.OutflowStoragePo;
import org.free_erp.service.entity.storage.PriceStoragePo;
import org.free_erp.service.logic.IStorageService;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;
import jxl.write.WriteException;
/*
 * Changer: liufei
 */
public abstract class CBaseListWindow extends CBaseFrame implements ActionListener
{
	protected JToolBar toolBar;
	protected JButton newButton;
    protected JButton chooseButton;
	protected JButton editButton;
	protected JButton removeButton;
	protected JButton printButton;
	protected JButton exportButton;
	protected JCheckBox showSearchPaneButton;
	protected JButton exitButton;
	protected JDataTable dataTable;
	protected JTextField searchIdField;
    protected JTextField includeProduct = new JTextField();
	protected CDatePicker startDateField;
	protected CDatePicker endDateField;
	protected JRadioButton draftStatusButton;
    protected JRadioButton formalStatusButton;
	protected JRadioButton discardStatusButton;
	protected JRadioButton allStatusButton;
	protected CButton searchButton;
	protected CButton clearButton;
	protected CPanel searchPanel;
	protected static IDbSupport dbSupport;
	protected IDataSource dataSource;
	protected CBaseFormDialog formDialog;
    protected CChooseIndentDialog indentDialog;
    protected String removeNumber;
    public SystemLog systemLog;
    protected JMenuItem newMenuItem;
    protected JMenuItem editMenuItem;
    protected JMenuItem removeMenuItem;
    protected JPopupMenu mainPopMenu;
	public CBaseListWindow(String title)
	{
		super(title);
		this.createDbSupport();
		initComponents();
        this.chooseButton.setVisible(false);
        this.startDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
		this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
		getFormDialog();
        this.systemLog = this.getSystemLog();
        
        this.dataTable.setShowTotalRow(true);
        
    }
    

    protected void permission(Integer per) {
        if (Main.getMainFrame().getCompany() != null) {
            if (!PermissionUtilities.hasAddPermission(per)) {
                this.newButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasEditPermission(per)) {
                this.editButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasRemovePermission(per)) {
                this.removeButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasPrintPermission(per)) {
                this.printButton.setEnabled(false);
            }
            if(!PermissionUtilities.hasExportPermission(per)){
            this.exportButton.setEnabled(false);
            }            
        }
    }
	protected void initComponents()
	{
		MainFrame mainFrame = Main.getMainFrame();
		this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false);
        
		newButton = new CButton("开新单(&N)");
		newButton.setIcon(mainFrame.getIcon("new"));
        chooseButton = new CButton("选择订单(&S)");
        chooseButton.setIcon(mainFrame.getIcon("new"));
		editButton = new CButton("修改(&M)");
		editButton.setIcon(mainFrame.getIcon("edit"));
		removeButton = new CButton("删除(&D)");
		removeButton.setIcon(mainFrame.getIcon("remove"));
		printButton = new CButton("打印(&P)");
		printButton.setIcon(mainFrame.getIcon("print"));
		exportButton = new CButton("导出(&X)");
		exportButton.setIcon(mainFrame.getIcon("export"));
		exportButton.addActionListener(this);
		showSearchPaneButton = new JCheckBox("显示查询");
		showSearchPaneButton.setSelected(true);
		this.showSearchPaneButton.addActionListener(this);
		this.exitButton = new CButton("关闭(&C)");
		this.exitButton.setIcon(mainFrame.getIcon("close"));
		this.exitButton.addActionListener(this);
		newButton.addActionListener(this);
        chooseButton.addActionListener(this);
		editButton.addActionListener(this);
		removeButton.addActionListener(this);
		this.printButton.addActionListener(this);
		this.toolBar.add(newButton);
		//this.toolBar.add(editButton);
		//this.toolBar.add(removeButton);
		this.toolBar.add(chooseButton);
		this.toolBar.addSeparator();
		this.toolBar.add(printButton);
		this.toolBar.addSeparator();
		this.toolBar.add(exportButton);
		this.toolBar.addSeparator();
		//this.toolBar.add(showSearchPaneButton);
		this.toolBar.add(new JPanel());
		this.toolBar.add(this.exitButton);
		this.getContentPane().add("South", this.toolBar);
		searchPanel = new CPanel();
		searchPanel.setPreferredSize(new Dimension(600, 50));
		this.getContentPane().add("North", searchPanel);
		this.searchIdField = new JTextField();
		this.draftStatusButton = new JRadioButton(ServiceConstants.DRAFT_STRING);
        this.formalStatusButton = new JRadioButton(ServiceConstants.FORMAL_STRING);
	    this.discardStatusButton = new JRadioButton(ServiceConstants.DISCARD_STRING);
		this.allStatusButton=new JRadioButton("全部");
        ButtonGroup group = new ButtonGroup();
        group.add(this.draftStatusButton);
        //group.add(this.discardStatusButton);
        group.add(allStatusButton);
        group.add(formalStatusButton);
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEtchedBorder());
        p.setLayout(null);
        p.add(this.allStatusButton);
        p.add(this.draftStatusButton);
        p.add(this.formalStatusButton);
        //p.add(this.discardStatusButton);
        this.allStatusButton.setBounds(5, 5, 60, 25);
        this.draftStatusButton.setBounds(75, 5, 100, 25);
        this.formalStatusButton.setBounds(180, 5, 100, 25);
        //this.discardStatusButton.setBounds(225, 5, 70, 25);
        int x = 620;
        this.allStatusButton.setSelected(true);
		this.startDateField = new CDatePicker();
		this.endDateField = new CDatePicker();
		this.searchButton = new CButton("查询(&S)");
		this.searchButton.addActionListener(this);
		this.clearButton = new CButton("清空(&C)");
		this.clearButton.addActionListener(this);
		searchPanel.addComponent(new JLabel("查询条件:"), 5 + x, 5, 60, 20);
        searchPanel.addComponent(new JLabel("状态:"), 30 + x, 25, 40, 20);
        searchPanel.add(p);
        
        p.setBounds(x + 70, 15, 300, 33);
        
        x = 370;
		searchPanel.addComponent(startDateField, 420 - x, 20, 100, 20, "开始日期", 50);//afa 2009-06-05
		searchPanel.addComponent(endDateField, 570 - x , 20, 100, 20, "结束日期", 50);
        searchPanel.addComponent(this.includeProduct, 730 - x, 20, 100, 20, "包含商品", 50);

		searchPanel.addComponent(searchButton, 835 - x, 15, 75, 20);
		searchPanel.addComponent(clearButton, 910 - x, 15, 75, 20);

		
/*		p.setBounds(70, 15, 300, 33);
 * 		searchPanel.addComponent(startDateField, 420, 20, 100, 20, "开始日期", 50);//afa 2009-06-05
		searchPanel.addComponent(endDateField, 570, 20, 100, 20, "结束日期", 50);
		searchPanel.addComponent(searchButton, 675, 15, 75, 25);
		searchPanel.addComponent(clearButton, 755, 15, 75, 25);*/
		dataTable = new JDataTable();
		dataTable.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if (e.getClickCount() >= 2)
				{
					doEdit();
				}
			}
			public void mouseEntered(MouseEvent e)
			{
			// TODO Auto-generated method stub
			}
			public void mouseExited(MouseEvent e)
			{
			// TODO Auto-generated method stub
			}
			public void mousePressed(MouseEvent e)
			{
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    mainPopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
			}
			public void mouseReleased(MouseEvent e)
			{
			// TODO Auto-generated method stub
			}
		});
        dataTable.addKeyListener(new KeyAdapter()
        {
           public void keyPressed(KeyEvent evt)
           {
               if (evt.getKeyCode() == KeyEvent.VK_ENTER)
               {
                   doEdit();
               }
           }
        });
		JScrollPane clientPane = new JScrollPane(dataTable);
		this.getContentPane().add("Center", clientPane);
        this.draftStatusButton.addActionListener(this);
        this.allStatusButton.addActionListener(this);
        this.discardStatusButton.addActionListener(this);
        this.formalStatusButton.addActionListener(this);
        
         newMenuItem = new JMenuItem(newButton.getText());
        editMenuItem = new JMenuItem(editButton.getText());
        removeMenuItem = new JMenuItem(removeButton.getText());
        
        newMenuItem.addActionListener(this);
        editMenuItem.addActionListener(this);
        removeMenuItem.addActionListener(this);
   
        mainPopMenu = new JPopupMenu();
           
        mainPopMenu.add(newMenuItem);
          
           
        mainPopMenu.add(editMenuItem);
           
            
        mainPopMenu.add(removeMenuItem);
          
        this.dataTable.add(mainPopMenu);
	}
	protected void createDbSupport()
	{
		dbSupport = new IDbSupport()
		{
			private IStorageService storageService = Main.getServiceManager().getStorageService();
            ISystemManageService systemManageService = Main.getServiceManager().getSystemManageService();
            public void delete(Object obj)
			{
				if (obj instanceof InStoragePo)
				{
					InStoragePo p = (InStoragePo) obj;
					this.storageService.deleteInStorageForm(p);
				}
				else if(obj instanceof OutflowStoragePo)
				{
				    OutflowStoragePo p=(OutflowStoragePo) obj;
				    this.storageService.deleteOutflowStorageForm(p);
				}
				else if(obj instanceof CheckStoragePo)
				{
				    CheckStoragePo p=(CheckStoragePo) obj;
				    this.storageService.deleteCheckStorageForm(p);
				}
				else if(obj instanceof LossStoragePo)
				{
				    LossStoragePo p=(LossStoragePo) obj;
				    this.storageService.deleteLossStorageForm(p);
				}
				else if(obj instanceof MinMaxStoragePo)
				{
				    MinMaxStoragePo p=(MinMaxStoragePo) obj;
				    this.storageService.deleteMinMaxStorageForm(p);
				}
				else if(obj instanceof MoveStoragePo)
				{
				    MoveStoragePo p=(MoveStoragePo) obj;
				    this.storageService.deleteMoveStorageForm(p);
				}
				else if(obj instanceof OutStoragePo)
				{
				    OutStoragePo p=(OutStoragePo) obj;
				    this.storageService.deleteOutStorageForm(p);
				}
				else if(obj instanceof PriceStoragePo)
				{
				    PriceStoragePo p=(PriceStoragePo) obj;
				    this.storageService.deletePriceStorageForm(p);
				}
				else if(obj instanceof InitialStoragePo)
				{
				    InitialStoragePo p=(InitialStoragePo) obj;
				    this.storageService.deleteInitialStorageForm(p);
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
			}
			public void save(Object obj)
			{
                String logContent = "";
                if(obj instanceof FormPo)
                {
                    FormPo p = (FormPo)obj;
                    if(p.getId() == 0)
                    {
                        logContent = "新增" + p.getName() + ":";
                    }
                    else
                    {
                        logContent = "修改" + p.getName() + ":";
                    }
                }
				if (obj instanceof InStoragePo)
				{
					InStoragePo p = (InStoragePo) obj;
					this.storageService.saveInStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof OutflowStoragePo)
				{
				    OutflowStoragePo p = (OutflowStoragePo) obj;
				    this.storageService.saveOutflowStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof CheckStoragePo)
				{
				    CheckStoragePo p = (CheckStoragePo) obj;
				    this.storageService.saveCheckStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof LossStoragePo)
				{
				    LossStoragePo p = (LossStoragePo) obj;
				    this.storageService.saveLossStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof MinMaxStoragePo)
				{
				    MinMaxStoragePo p = (MinMaxStoragePo) obj;
				    this.storageService.saveMinMaxStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof MoveStoragePo)
				{
				    MoveStoragePo p = (MoveStoragePo) obj;
				    this.storageService.saveMoveStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof OutStoragePo)
				{
				    OutStoragePo p = (OutStoragePo) obj;
				    this.storageService.saveOutStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof PriceStoragePo)
				{
				    PriceStoragePo p = (PriceStoragePo) obj;
				    this.storageService.savePriceStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else if(obj instanceof InitialStoragePo)
				{
				    InitialStoragePo p = (InitialStoragePo) obj;
				    this.storageService.saveInitialStorageForm(p);
                    systemLog.setContent(logContent + p.getNumber());
				}
				else
				{
					throw new IllegalArgumentException("参数类型不符合!传入的类型为" + obj.getClass().getName());
				}
                if(obj instanceof FormPo)
                {
                    systemManageService.saveSystemLog(systemLog);
                }
			}
			public void deleteAll(Collection<Object> objs)
			{
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
			public void saveAll(Collection<Object> objs)
			{
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
	/**
	 * 子类重载此方法!
	 */
	protected void doAdd()
	{
        this.allStatusButton.setSelected(true);
        this.doFilter();
        this.formDialog.newDataRow();
		this.formDialog.setVisible(true);
		this.dataSource.clearTempDataRows();
	}
	protected void doEdit()
	{
       
		if (this.dataTable.getSelectedRow() < 0)
		{
			MessageBox.showErrorDialog(this, "没有任何数据行被选中!");
			return;
		}
        IDataRow dataRow = this.dataTable.getSelectedDataRow();
        this.allStatusButton.setSelected(true);
        this.doFilter();
		this.dataSource.setCurrentDataRow(dataRow);
        this.formDialog = this.getFormDialog();
        this.formDialog.refresh();
		this.formDialog.setVisible(true);
		this.dataSource.clearTempDataRows();
	}
	protected void doRemove()
	{
		if (this.dataTable.getSelectedRow() < 0)
		{
			MessageBox.showErrorDialog(this, "没有数据行被选中!");
			return;
		}
		if (MessageBox.showQuestionDialog(this, "您确信要删除当前记录吗?") == 0)
		{
			IDataRow dataRow = this.dataTable.getSelectedDataRow();
            String number= dataRow.getColumnValue("number").toString();
            this.removeNumber = number;
			try
			{
				this.dataSource.removeDataRow(dataRow);
                this.removeNumberLog();
			}
			catch(Exception ex)
			{
				MessageBox.showErrorDialog(this, "无法删除,请与系统管理员联系!\n" + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	protected void doClear()
	{
		this.startDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
	}
	protected abstract void doSearch();
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == this.newButton || source == newMenuItem)
		{
			this.doAdd();
		}
        else if(source == chooseButton)
        {
            this.doChoose();
        }
        else if (source == this.editButton || source == editMenuItem)
		{
			this.doEdit();
		}
        else if (source == this.removeButton)
		{
			this.doRemove();
		}
        else if (source == this.printButton)
		{
			this.doPrint();
		}
        else if (source == this.exportButton)
		{
			this.doExport();
		}
        else if (source == this.showSearchPaneButton)
		{
			this.searchPanel.setVisible(this.showSearchPaneButton.isSelected());
		}
        else if (source == this.searchButton)
		{
			this.doSearch();
		}
        else if (source == this.clearButton)
		{
			this.doClear();
		} else if (source == this.exitButton)
		{
			doClose();
		}
        else if (source == this.formalStatusButton || source == this.discardStatusButton || source == this.allStatusButton || source == this.draftStatusButton)
        {
            doFilter();
        }
	}
    protected void doFilter()
    {
        int type = 5;
        if (this.draftStatusButton.isSelected())
        {
            type = ServiceConstants.DRAFT_STATUS;
        }
        else if (this.formalStatusButton.isSelected())
        {
            type = ServiceConstants.FORMAL_STATUS;
        }
        else if (this.discardStatusButton.isSelected())
        {
            type = ServiceConstants.DISCARD_STATUS;
        }
        else
        {
            type = ServiceConstants.ALL_STATUS;
        }
        if (type == ServiceConstants.ALL_STATUS)
        {
            this.dataSource.setFiltered(false);
        }
        else
        {
            this.dataSource.setFilter("status==?", new Integer[]{type});
        }
    }
    protected void doChoose()
    {
        indentDialog = this.getChooseIndentDialog();
        indentDialog.setVisible(true);
        this.dataSource.clear();
        this.refreshWindowDatas();
    }
	protected void doPrint()
	{
		JOptionPane.showMessageDialog(this, "尚未实现!");
	}
	protected void doExport()
    {
		try
        {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			chooser.setFileFilter(new FileFilter()
            {
				@Override
				public boolean accept(File f)
                {
                    if (f.getName().toLowerCase().endsWith(".xls") || f.isDirectory())
                    {
                        return true;
                    }
					return false;
				}
				@Override
				public String getDescription()
                {
					return "Excel(*.xls)文件";
				}
			}
			);
			chooser.setApproveButtonText("导出");
			chooser.setDialogTitle("导出文件存储为");
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                FileFilter fileFilter = chooser.getFileFilter();
                File file = chooser.getSelectedFile();
                if(!file.getName().toLowerCase().endsWith(".xls"))
                {
                    file = new File(file.getAbsolutePath() + ".xls");
                }
                if(fileFilter.accept(file))
                {
                    if(file.exists())
                    {
                        if (MessageBox.showQuestionDialog(this, "该文件名已存在，您确信要覆盖吗?") == 1)
                        {
                            this.setVisible(true);
                            return;
                        }
                    }
                }
                
				String fileName = chooser.getSelectedFile().getAbsolutePath();
				if (!fileName.toLowerCase().endsWith(".xls"))
				{
					fileName += ".xls";
				}
                ExcelUtilities.title = this.getExcelExportTitle();
				ExcelUtilities.exportTableToXls(dataTable, fileName);
                MessageBox.showMessageDialog(this, "导出成功!");
			}
		}
        catch (IOException ex)
        {
			MessageBox.showErrorDialog(this, "创建文件失败!" + ex.getMessage());
		}
        catch (WriteException ex)
        {
			MessageBox.showErrorDialog(this, "写文件异常!" + ex.getMessage());
		}
	}
    protected String getExcelExportTitle()
    {
        return "";
    }
	protected void doClose()
	{
		this.dispose();
	}
    protected void clearInfo()
    {
        this.startDateField.setSelectedItem("");
        this.endDateField.setSelectedItem("");
    }
     protected SystemLog getSystemLog()//liufei
    {
        SystemLog systemLog = new SystemLog();
        systemLog.setCompany(Main.getMainFrame().getCompany());
        systemLog.setUser(Main.getMainFrame().getUser());
        systemLog.setFormDate(new Timestamp(System.currentTimeMillis()));
        return systemLog;
    }
    public void refreshWindowDatas()
    {
        
    }
    protected void removeNumberLog()
    {
    }
	protected abstract CBaseFormDialog getFormDialog();
    protected CChooseIndentDialog getChooseIndentDialog()
    {
        return indentDialog;
    }
    //protected abstract CBaseFormDialog newFormDialog();
    public void dispose()
    {
        this.dataSource.dispose();
        if (this.formDialog != null)
        {
            this.formDialog.dispose();
        }
        super.dispose();
    }
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag)
        {
            this.draftStatusButton.doClick();
        }
    }
}
