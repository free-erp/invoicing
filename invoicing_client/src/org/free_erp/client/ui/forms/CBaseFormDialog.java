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

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.DataRowChangedListener;
import com.jdatabeans.beans.data.DataRowEvent;
import com.jdatabeans.beans.data.FieldRequiredException;
import com.jdatabeans.beans.data.IBinding;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.PermissionUtilities;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.constants.ServiceConstants;
import org.free_erp.service.entity.FormPo;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.logic.ISystemManageService;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author afa
 */
public abstract class CBaseFormDialog extends JDataDialog implements ActionListener, DataRowChangedListener {

    protected JToolBar toolBar;
    protected JButton chooseSavedButton;
    protected CButton saveButton;
    protected CButton checkButton;
    protected CButton discardButton;
    protected CButton activeButton;
    protected CButton firstButton;
    protected CButton previousButton;
    protected CButton nextButton;
    protected CButton lastButton;
    protected CButton printButton;
    protected CButton closeButton;
    protected IDbSupport dbSupport;
    protected JPanel panel;
    protected String logInfo;
    protected JPanel titlePane;
    protected JPanel northPanel;
    protected CPagePane pagePane;
    public static final Font titleFont = new Font("楷体_GB2312", Font.BOLD, 24);
    protected SystemLog systemLog;
    protected ISystemManageService service;
    protected String logContent = "";

    public CBaseFormDialog(Frame parent, IDataSource dataSource, IDbSupport dbSupport) {
        super(parent, dataSource);
        this.setSize(930, 600);
        this.dbSupport = dbSupport;
        beforeInitComponent();
        this.setResizable(false);
        initComps();
        this.setModal(true);
        this.setResizable(false);
        this.systemLog = this.getSystemLog();
        this.service = Main.getServiceManager().getSystemManageService();

    }

//    public void setVisible(boolean visible)
//    {
//        if (visible)
//        {
//            this.refresh();
//        }
//        super.setVisible(visible);
//    }
    public void unEnbleButton() {
        this.firstButton.setEnabled(false);
        this.previousButton.setEnabled(false);
        this.nextButton.setEnabled(false);
        this.lastButton.setEnabled(false);
    }

    protected void permission(Integer per) {
        if (Main.getMainFrame().getCompany() != null) {

            if (!PermissionUtilities.hasCheckPermission(per));
            {
                this.checkButton.setEnabled(false);
            }
            if (!PermissionUtilities.hasDraftPermission(per)) {
                this.discardButton.setEnabled(false);
            }
        }
    }

    private void initComps() {
        this.getContentPane().setLayout(new BorderLayout());
        MainFrame mainFrame = Main.getMainFrame();
        this.toolBar = new JToolBar();
        titlePane = new JPanel();
        titlePane.setLayout(new BorderLayout());
        titlePane.add("North", this.toolBar);
        this.getContentPane().add("North", titlePane);
        pagePane = new CPagePane();		//chooseSavedButton = new CButton("选择草稿(&C)");
        //chooseSavedButton.setIcon(mainFrame.getIcon("open"));
        saveButton = new CButton("保存草稿(&S)");
        checkButton = new CButton("单据过帐(&C)");
        checkButton.setIcon(mainFrame.getIcon("edittype"));//temp
        discardButton = new CButton("废弃(&D)");
        discardButton.setIcon(mainFrame.getIcon("deletetype"));
        this.activeButton = new CButton("激活(&A)");
        activeButton.setIcon(mainFrame.getIcon("edittype"));
        saveButton.setIcon(mainFrame.getIcon("save"));
        firstButton = new CButton("首单(&F)");
        firstButton.setIcon(mainFrame.getIcon("first"));
        previousButton = new CButton("前一单(&R)");
        previousButton.setIcon(mainFrame.getIcon("previous"));
        nextButton = new CButton("下一单(&N)");
        nextButton.setIcon(mainFrame.getIcon("next"));
        lastButton = new CButton("末单(&L)");
        lastButton.setIcon(mainFrame.getIcon("last"));
        printButton = new CButton("打印(&P)");
        printButton.setIcon(mainFrame.getIcon("print"));
        closeButton = new CButton("关闭(&C)");
        closeButton.setIcon(mainFrame.getIcon("close"));
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.getContentPane().add("Center", panel);

        panel.setLayout(null);
        panel.add(this.pagePane);
        pagePane.setLocation(10, 5);
        northPanel = this.getMainPanel();
        pagePane.add(northPanel);
        northPanel.setBounds(CPagePane.X, 5, 750, 128);

        CPanel bPanel = new CPanel();
        bPanel.setPreferredSize(new Dimension(500, 40));
        bPanel.setLayout(null);
        panel.add("South", bPanel);
        this.toolBar.add(saveButton);
        this.toolBar.add(checkButton);
        this.toolBar.add(discardButton);
        //this.toolBar.add(activeButton);
        this.toolBar.add(firstButton);
        this.toolBar.add(previousButton);
        this.toolBar.add(nextButton);
        this.toolBar.add(lastButton);
        this.toolBar.add(printButton);
        this.toolBar.add(new JPanel());
        this.toolBar.add(closeButton);
        this.toolBar.setFloatable(false);

        this.checkButton.addActionListener(this);
        this.discardButton.addActionListener(this);
        this.saveButton.addActionListener(this);
        this.firstButton.addActionListener(this);
        this.previousButton.addActionListener(this);
        this.nextButton.addActionListener(this);
        this.lastButton.addActionListener(this);
        this.printButton.addActionListener(this);
        this.closeButton.addActionListener(this);

        this.activeButton.addActionListener(this);

    }

    public boolean isNewForm() {
        if (this.dataSource != null && this.dataSource.getCurrentDataRow() != null) {
            Object v = this.dataSource.getCurrentDataRow().getColumnValue("number");
            return v == null;
        }
        return true;
    }

    protected void doChoose() {
        MessageBox.showMessageDialog(this, "尚未实现!");
    }

    protected void doClear() {

        MessageBox.showMessageDialog(this, "尚未实现!");

    }

    protected void doPrint() {

        MessageBox.showMessageDialog(this, "尚未实现!");

    }

    protected void doStop() {

        MessageBox.showMessageDialog(this, "尚未实现!");

    }

    protected void doCheck() {
        MessageBox.showMessageDialog(this, "Not supported yet!");

    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == this.saveButton) {
            this.doSave();

        } else if (source == this.firstButton) {

            this.doFirst();

        } else if (source == this.previousButton) {

            this.doPrevious();

        } else if (source == this.nextButton) {

            this.doNext();

        } else if (source == this.lastButton) {

            this.doLast();

        } else if (source == this.printButton) {

            this.doPrint();

        } else if (source == this.closeButton) {
            this.doClose();

        } else if (source == this.discardButton) {
            if (this.doSave()) {
                this.doDiscard();
            }

        } else if (source == this.checkButton) {
            if (this.doSave()) {
                this.doCheck();
            }
        } else if (source == this.activeButton) {
            this.doActive();
        }
    }

    public void rowDeleted(DataRowEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rowInserted(DataRowEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rowUpdated(DataRowEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closeDialog(WindowEvent e) {
        this.doClose();
    }

    protected abstract CPanel getMainPanel();

    public abstract void newDataRow();

    protected void beforeInitComponent() {

    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public void setTitle(String title) {
        super.setTitle(title);
        this.pagePane.setTitle(title);
    }

    protected boolean doSave() {
        try {
            this.save();
            return true;
        } catch (FieldRequiredException ex) {
            MessageBox.showMessageDialog(this, "必填项\"" + ex.getFieldDisplayName() + "\"不能为空，或者所填写的内容在数据库中不存在！");
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
            ex.printStackTrace();
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
            FormPo p = (FormPo) po;
            this.logContent = "作废";
            this.writeSystmLog(p);
        }
    }

    protected void doActive() {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow == null) {
            return;
        }
        dataRow.setColumnValue("status", ServiceConstants.DRAFT_STATUS);
        this.save();
        this.refresh();
        Object po = ((ObjectDataRow) this.getDataSource().getCurrentDataRow()).getUserObject();
        FormPo p = (FormPo) po;
        this.logContent = "激活";
        this.writeSystmLog(p);
    }

    protected void doClose() {
        if (checkSave()) {
            this.dispose();
        }
    }

    protected void refresh() {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        Object status = dataRow.getColumnValue("status");
        if (status != null) {
            int s = (Integer) status;
            if (s == ServiceConstants.FORMAL_STATUS) {
                this.discardButton.setEnabled(false);
                this.checkButton.setEnabled(false);
                this.saveButton.setEnabled(false);
                setBindingComponentsEnable(false);
                this.activeButton.setEnabled(false);
                return;
            } else if (s == ServiceConstants.DISCARD_STATUS) {
                this.discardButton.setEnabled(false);
                this.checkButton.setEnabled(false);
                this.saveButton.setEnabled(false);
                setBindingComponentsEnable(false);

                this.activeButton.setEnabled(true);
                return;
            }
            this.activeButton.setEnabled(false);
            this.discardButton.setEnabled(true);
            this.checkButton.setEnabled(true);
            this.saveButton.setEnabled(true);
            setBindingComponentsEnable(true);

        }
    }

    protected void setBindingComponentsEnable(boolean enable) {
        updateComponentsEnable(this.getContentPane(), enable);
    }

    protected void updateComponentsEnable(Component comp, boolean enable) {
        if (comp instanceof Container) {
            Component comps[] = ((Container) comp).getComponents();
            for (int i = 0; i < comps.length; i++) {
                if (comps[i] instanceof IBinding) {
                    comps[i].setEnabled(enable);
                }
                updateComponentsEnable(comps[i], enable);
            }
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

    protected void writeSystmLog(FormPo p) {
        if (p.getId() != 0) {
            logContent += p.getName() + ":";
        }
        systemLog.setContent(logContent + p.getNumber());
        service.saveSystemLog(systemLog);
    }
}
