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
package org.free_erp.client.ui.report;
import com.jdatabeans.beans.data.DataRowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CMoneyField;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.DataRowChangedListener;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.util.MessageBox;
import com.jdatabeans.util.TypeUtilities;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.forms.CBaseFrame;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ExcelUtilities;
import org.free_erp.client.ui.util.PermissionUtilities;
import com.jdatabeans.beans.data.IDbSupport;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import jxl.write.WriteException;
/**
 *
 * @author Administrator
 */
public abstract class CBaseQueryWindow extends CBaseFrame implements ActionListener {

	protected JDataTable dataTable;
	private JToolBar toolBar;
        protected static IDbSupport dbSupport;
	//protected JButton newButton;
	protected JButton printButton;
	protected JButton exportButton;
	protected JCheckBox showSearchPaneButton;
	protected JButton exitButton;
	protected CButton searchButton;
	protected CButton clearButton = new CButton("清空(&C)");
	protected CPanel searchPanel;
	protected IDataSource dataSource;
	protected CButton selectButton = new CButton("查询(&Q)");
    protected CPanel showTotalPanel;
    protected CField totalAmountField;
    protected CMoneyField totalMoneyField;
    protected JPopupMenu mainPopMenu;
    protected JMenuItem clearMenuItem;

	public CBaseQueryWindow(String title) {
		super(title);
		initComponents();
		initColumns();
        this.selectButton.addActionListener(this);
		this.clearButton.addActionListener(this);
        
	}
     
    protected void permission(Integer per) {

        if (Main.getMainFrame().getCompany() != null) {
        
            if (!PermissionUtilities.hasPrintPermission(per)) {

                this.printButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasExportPermission(per)) {
                this.exportButton.setEnabled(false);
            }

        }
    }
    
	protected void initComponents() {
		MainFrame mainFrame = Main.getMainFrame();
		this.toolBar = new JToolBar();
		
		//newButton = new CButton("预览");
		//newButton.setIcon(mainFrame.getIcon("new"));
		printButton = new CButton("打印(&P)");
		printButton.setIcon(mainFrame.getIcon("print"));
		exportButton = new CButton("导出(&X)");
		exportButton.setIcon(mainFrame.getIcon("export"));
		showSearchPaneButton = new JCheckBox("显示查询");
		showSearchPaneButton.setSelected(true);
		this.exitButton = new CButton("关闭(&C)");
		this.exitButton.setIcon(mainFrame.getIcon("close"));
		//newButton.addActionListener(this);
		printButton.addActionListener(this);
		exportButton.addActionListener(this);
		showSearchPaneButton.addActionListener(this);
		exitButton.addActionListener(this);
		//this.toolBar.add(newButton);
		//this.toolBar.addSeparator();
		this.toolBar.add(printButton);
		//this.toolBar.addSeparator();
		this.toolBar.add(exportButton);
		//this.toolBar.addSeparator();
		this.toolBar.add(showSearchPaneButton);
        this.toolBar.add(new JPanel());
		this.toolBar.add(this.exitButton);
		this.getContentPane().add("North", this.toolBar);
        this.showTotalPanel = this.getTotalPanel();
        if (this.showTotalPanel != null)
        {
            this.getContentPane().add("South", showTotalPanel);
        }
        
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.getContentPane().add("Center", panel);
		dataTable = new JDataTable();
        dataTable.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() >= 2)
				{
					doShowDetail();
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
                int mods = e.getModifiers();
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    mainPopMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
			}
			public void mouseReleased(MouseEvent e)
			{
			}
		});
		this.dataSource = this.dataTable.getDataSource();
		this.dataSource.setKeyColumnName("id");
		searchPanel = this.getMainPanel();
		panel.add("North", searchPanel);
		JScrollPane tablePane = new JScrollPane(dataTable);
		panel.add("Center", tablePane);
        clearMenuItem = new JMenuItem("清空");
        clearMenuItem.addActionListener(this);
        mainPopMenu = new JPopupMenu();
        mainPopMenu.add(clearMenuItem);
        this.dataTable.add(mainPopMenu);
        this.dataSource.addDataRowChangedListener(new DataRowChangedListener()
        {

            public void rowDeleted(DataRowEvent evt) {
                refreshTotalInfo();
            }

            public void rowInserted(DataRowEvent evt) {
                 refreshTotalInfo();
            }

            public void rowUpdated(DataRowEvent evt) {
                 refreshTotalInfo();
            }

        });
	}

    protected void refreshTotalInfo()
    {
        if (this.totalAmountField != null)
        {
            this.totalAmountField.setText("0d");
        }
        if (this.totalMoneyField != null)
        {
            this.totalMoneyField.setMoney(0d);
        }
        refreshTotalAmount();
        refreshTotalMoney();
        refreshTotailMoney();
    }
    public void refreshTotalAmount()
    {
        if (this.totalAmountField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double amount = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("amount"))
            {
                return;
            }
            Double tm = (Double) dataRow.getColumnValue("amount");
			if (tm != null)
            {
				amount += tm;
			}
		}
        this.totalAmountField.setText(TypeUtilities.polishDoubleString(amount));
    }

    public void refreshTotailMoney()
    {
        if (this.totalMoneyField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double money = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("totailMoney"))
            {
                return;
            }
			Double tm = (Double) dataRow.getColumnValue("totailMoney");
			if (tm != null)
            {
				money += tm;
			}
		}
        this.totalMoneyField.setMoney(money);
        
    }

    public void refreshTotalMoney()
    {
        if (this.totalMoneyField == null)
        {
            return;
        }
        List<IDataRow> dataRows = this.dataTable.getDataSource().getDataRows();
		double money = 0d;
		for (IDataRow dataRow : dataRows)
        {
            if (!dataRow.containsColumn("totalMoney"))
            {
                return;
            }
			Double tm = (Double) dataRow.getColumnValue("totalMoney");
			if (tm != null)
            {
				money += tm;
			}
		}
        this.totalMoneyField.setMoney(money);
       
    }

	public void actionPerformed(ActionEvent e)
    {
		Object source = e.getSource();
		if (source == this.printButton)
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
        else if (source == this.exitButton)
        {
			doClose();
		} 
        else if (source == selectButton)
        {
			this.select();
		}
        else if (source == clearButton)
        {
			clearInfo();
		}
        else if (source == clearMenuItem)
        {
			clearDatas();
		}
	}

    protected void clearDatas()
    {
        this.dataTable.getDataSource().clear();
    }

	protected abstract void clearInfo();
	protected abstract void select();
    protected void doShowDetail()
    {
        
    }
	
	protected void doClear() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	protected void doClose() {
		this.setVisible(false);
        this.dispose();
        //this.dispose();
	}
	protected void doExport() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			chooser.setFileFilter(new FileFilter(){
				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(".xls") || f.isDirectory())
					{
						return true;
					}
					return false;
				}
				public String getDescription() {
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
                        if (MessageBox.showQuestionDialog(Main.getMainFrame(), "该文件名已存在，您确信要覆盖吗?") == 1)
                        {
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
                MessageBox.showMessageDialog(Main.getMainFrame(), "导出成功!");
			}
		} catch (IOException ex) {
			MessageBox.showErrorDialog(Main.getMainFrame(), "创建文件失败!" + ex.getMessage());
		} catch (WriteException ex) {
			MessageBox.showErrorDialog(Main.getMainFrame(), "写文件异常!" + ex.getMessage());
		}
	}

    protected String getExcelExportTitle()
    {
        return "";
    }
	
	protected void doFilter()
    {
		
	}

	protected void doPrint()
    {
		
	}
    
    protected CPanel getTotalPanel()
    {
        return new CPanel();
    }
	protected abstract CPanel getMainPanel();
	protected abstract void initColumns();
}
