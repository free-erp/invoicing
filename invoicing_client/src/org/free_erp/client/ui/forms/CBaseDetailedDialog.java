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
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.forms.basic.CRejiggerTypeDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.exception.LogicException;
import org.free_erp.service.logic.ISystemManageService;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @Changer liufei
 */
public class CBaseDetailedDialog extends JDataDialog implements ActionListener {
    protected IDbSupport dbSupport;
    protected JToolBar toolBar;
    public JButton saveAndNewButton;
    public JButton saveButton;
    protected JButton firstButton;
    protected JButton previousButton;
    protected JButton nextButton;
    protected JButton lastButton;
    protected JButton rejiggerTypeButton;
    protected JButton printButton;
    protected JCheckBox stopButton;
    protected JButton closeButton;
    protected Company company;
    protected ISystemManageService service;
    protected CRejiggerTypeDialog rejiggerTypeDialog;
    public CBaseDetailedDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport)
    {
        super(parent, dataSource);
        this.setSize(850, 400);
        this.company = Main.getMainFrame().getCompany();
        this.service = Main.getServiceManager().getSystemManageService();
        initComps();
        this.dbSupport = dbSupport;
    }
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        Object source = e.getSource();
        if (source == this.saveAndNewButton)
        {
            this.doSaveAndNew();
        }
        else if (source == this.saveButton)
        {
            this.doSave();
        }
        else if (source == this.firstButton)
        {
            this.doFirst();
        }
        else if (source == this.previousButton)
        {
            this.doPrevious();
        }
        else if (source == this.nextButton)
        {
            this.doNext();
        }
        else if (source == this.lastButton)
        {
            this.doLast();
        }
        else if (source == this.rejiggerTypeButton)
        {
            this.doRejiggerType();
        }
        else if (source == this.printButton)
        {
            this.doPrint();
        }
        else if (source == this.stopButton)
        {
            this.doStop();
        }
        else if (source == this.closeButton)
        {
            this.doClose();
        }
    }
    protected void doRejiggerType()
    {
    }
    public CBaseDetailedDialog(Frame parent, IDataSource dataSource)
    {
        super(parent, dataSource);
        initComps();
    }
    protected void initComps()
    {
        this.getContentPane().setLayout(new BorderLayout());
        MainFrame mainFrame = Main.getMainFrame();
        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false);
        this.getContentPane().add("North", this.toolBar);
        saveAndNewButton = new CButton("保存新建(&A)");
        saveAndNewButton.setIcon(mainFrame.getIcon("saveandnew"));
        saveButton = new CButton("保存(&S)");
        saveButton.setIcon(mainFrame.getIcon("save"));
        firstButton = new CButton("首条(&F)");
        firstButton.setIcon(mainFrame.getIcon("first"));
        previousButton = new CButton("上一条(&R)");
        previousButton.setIcon(mainFrame.getIcon("previous"));
        nextButton = new CButton("下一条(&N)");
        nextButton.setIcon(mainFrame.getIcon("next"));
        lastButton = new CButton("末条(&L)");
        lastButton.setIcon(mainFrame.getIcon("last"));
        rejiggerTypeButton = new CButton("更改类别");
        rejiggerTypeButton.setIcon(mainFrame.getIcon("edittype"));
        printButton = new CButton("打印(&P)");
        printButton.setIcon(mainFrame.getIcon("print"));
        stopButton = new JCheckBox("停用");
        closeButton = new CButton("关闭(&C)");
        closeButton.setIcon(mainFrame.getIcon("close"));
        //closeButton.setIcon(mainFrame.getIcon("close"));
        this.toolBar.add(saveAndNewButton);
        this.toolBar.add(saveButton);
        this.toolBar.addSeparator();
        this.toolBar.add(firstButton);
        this.toolBar.add(previousButton);
        this.toolBar.add(nextButton);
        this.toolBar.add(lastButton);
        this.toolBar.addSeparator();
        //this.toolBar.add(rejiggerTypeButton);
        this.toolBar.add(printButton);
        this.toolBar.addSeparator();
        //this.toolBar.add(stopButton);
        this.toolBar.add(new JPanel());
        this.toolBar.add(closeButton);
        this.closeButton.addActionListener(this);
        this.saveAndNewButton.addActionListener(this);
        this.saveButton.addActionListener(this);
        this.firstButton.addActionListener(this);
        this.previousButton.addActionListener(this);
        this.nextButton.addActionListener(this);
        this.lastButton.addActionListener(this);
        this.rejiggerTypeButton.addActionListener(this);
        this.printButton.addActionListener(this);
        this.stopButton.addActionListener(this);       
    }
    public void doSaveAndNew()
    {
        MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
    }
    public boolean doCheckField()
    {
        return true;
    }
    public boolean doSave()
    {
         
        if (this.dataSource == null || this.dataSource.getCurrentDataRow() == null)
        {
            this.modified = false;
            return true;
        }
        if(!this.doCheckField())
        {
            return false;
        }
        try {
            this.save();
            //MessageBox.showMessageDialog(this, "保存成功!");
            this.modified = false;
            //this.saveButton.setEnabled(false);//afa 2009-06-05
            //this.saveAndNewButton.setEnabled(false);
            return true;
        } catch (FieldRequiredException ex) {
            MessageBox.showErrorDialog(this, "\"" + ex.getFieldDisplayName() + "\"项不能为空!");
        } catch (NumberFormatException ex) {
            MessageBox.showErrorDialog(this, "输入的数字格式不合法,请检查!\n" + ex.getMessage());
        }
        catch(LogicException ex)
        {
            MessageBox.showErrorDialog(Main.getMainFrame(), ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception ex) {
            MessageBox.showErrorDialog(this, "系统故障无法保存,请与系统管理员联系!\n" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    public void doNext()
    {
        if (!checkSave())
        {
            return;
        }
        this.dataSource.next();
    }
    public void doPrevious()
    {
        if (!checkSave())
        {
            return;
        }
        this.dataSource.previous();
    }
    public void doLast()
    {
        if (!checkSave())
        {
            return;
        }
        this.dataSource.last();
    }
    public void doFirst()
    {
        if (!checkSave())
        {
            return;
        }
        this.dataSource.first();
    }
    public void doClear()
    {
        MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
    }
    public void doPrint()
    {
        MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
    }
    public void doStop()
    {
        //MessageBox.showMessageDialog(Main.getMainFrame(), "尚未实现!");
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null)
        {
            if (dataRow.containsColumn("status"))
            {
                dataRow.setColumnValue("status", this.stopButton.isSelected() ? 1 : 0);//应该在check事件里,这里是action事件故是反的
                this.modified = true;
            }
        }
    }
    public void doClose()
    {
        if (this.checkSave())
        {
            this.dispose();
        }
    }
    @Override
    protected void refresh()
    {
        this.refreshStop();
    }
    protected void refreshStop()
    {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null)
        {
            if (dataRow.containsColumn("status"))
            {
                this.stopButton.setVisible(true);
                if (dataRow.getColumnValue("status") == null)
                {
                    dataRow.setColumnValue("status", 0);
                    dataRow.setModified(false);
                }
                this.stopButton.setSelected((Integer) dataRow.getColumnValue("status") != 0);
            }
            else
            {
                this.stopButton.setVisible(false);
            }
        }
    }
    /**
     *
     * @return
     */
    private boolean checkSave()
    {
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
        return true;
    }
    @Override
    public void closeDialog(WindowEvent e) {
        
        this.doClose();
    }
}
