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
import com.jdatabeans.beans.data.IDbSupport;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.CPasswordField;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.JDataTableComboBox;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.Md5;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.logic.IPermissionsService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CUserManageDialog extends JDataDialog implements ActionListener{

    private JButton okButton;
    private JButton cancelButton;
    protected JDataField number;
    protected JDataTableComboBox stafferNField;
    protected JDataField usernameField;
    protected CPasswordField enterPswField;
    protected CPasswordField validatePswField;
    protected JCheckBox stopButton;
    private IDbSupport dbSupport;

    private JCheckBox isPurchaseButton = new JCheckBox("采购员角色");
    private JCheckBox isSaleButton = new JCheckBox("销售员角色");
    private JCheckBox isStorageButton = new JCheckBox("仓管员角色");
    private JCheckBox isFinanceButton = new JCheckBox("财务人员角色");

    private JCheckBox hasCheckButton = new JCheckBox("具备审核权限");
    private JCheckBox hasPriceButton = new JCheckBox("查看价格权限");

    public CUserManageDialog(Frame parent, IDataSource dataSource, User user) {
        super(parent, dataSource);
       this.createDbSupport();
        initComps();
        
    }

    public CUserManageDialog(Frame parent, IDataSource dataSource) {
        super(parent, dataSource);
        this.createDbSupport();
        initComps();
        
    }

    

    

    private void createDbSupport() {
        dbSupport = new IDbSupport() {

            IPermissionsService service = Main.getServiceManager().getPermissionsService();

            public void save(Object obj) {
                if (obj instanceof User) {
                    User p = (User) obj;
                    this.service.saveUser(p);
                }

            }

            public void delete(Object obj) {
                if (obj instanceof User) {
                    User p = (User) obj;
                    this.service.removeUser(p);
                }
            }

            public void saveAll(Collection<Object> objs) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void deleteAll(Collection<Object> objs) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JCheckBox box = null;
        if (source instanceof JCheckBox) {
            box = (JCheckBox) source;
            this.modified = true;
        }
        if (source == this.okButton) {
            //this.doSave();
            this.doAdd();
        }


        if (source == this.cancelButton) {
            if (this.checkModified()) {
                if (MessageBox.showQuestionDialog(this, "记录没有保存，您需要保存吗?") == 0) {
                    if (!this.doSave()) {
                        return;
                    }
                }
            //添加存盘判断!/////

            }
            this.setVisible(false);

            doStop();
        }
    }

    private void refreshComps() {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null) {
            Object value = dataRow.getColumnValue("status");
            if (value != null) {
                this.stopButton.setSelected((Integer) value != 0);
            } else {
                this.stopButton.setSelected(false);
            }
            String password = (String) dataRow.getColumnValue("password");
            if (password != null) {
                this.enterPswField.setText("******");
                this.validatePswField.setText("******");
         
        }
    }
    }

    private void doStop() {
        boolean stop = this.stopButton.isSelected();
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null) {
            dataRow.setColumnValue("status", stop ? 1 : 0);
        }
        this.modified = true;
    }

    private boolean checkModified() {
        return this.isModified();// || this.moduleTree.isModified();
    }

    protected void initComps() {

        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("用户管理");

        this.setSize(400, 320);
        this.okButton = new CButton("确定(&O)");
        this.cancelButton = new CButton("取消(&C)");
        CPanel bottomPanel = new CPanel();
        bottomPanel.setPreferredSize(new Dimension(500, 30));
        this.getContentPane().add("South", bottomPanel);
        bottomPanel.setLayout(null);
        bottomPanel.addComponent(okButton, 200, 0, 90, 25);
        bottomPanel.addComponent(cancelButton, 300, 0, 90, 25);

        CPanel panel = new CPanel();
        panel.setLayout(null);
        stafferNField = new JDataTableComboBox("name", String.class, this.dataSource, Main.getMainFrame().getObjectsPool().getEmployeeTable(), "name");
        usernameField = new JDataField("userName", String.class, this.dataSource);
        this.stopButton = new JCheckBox("帐号停用");

        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEtchedBorder());
        p.setLayout(null);
        p.add(isPurchaseButton);
        p.add(isSaleButton);
        p.add(isStorageButton);
        p.add(isFinanceButton);
        
        isPurchaseButton.setBounds(5, 10, 110, 30);
        isSaleButton.setBounds(115, 10, 110, 30);
        isStorageButton.setBounds(5, 30, 110, 30);
        isFinanceButton.setBounds(115, 30, 110, 30);


        enterPswField = new CPasswordField();
        validatePswField = new CPasswordField();
        panel.addComponent(usernameField, 100, 10, 250, 20, "用户名称:", 60);
        panel.addComponent(stafferNField, 100, 40, 250, 20, "职员名称:", 60);
        panel.addComponent(enterPswField, 100, 70, 250, 20, "登录密码:", 60);
        panel.addComponent(validatePswField, 100, 100, 250, 20, "验证密码:", 60);
        
        panel.addComponent(p, 100, 130, 250, 60, "用户角色:", 60);
        panel.addComponent(hasCheckButton, 100, 200, 130, 30, "用户权限:", 60);
        panel.addComponent(hasPriceButton, 230, 200, 250, 30);

        panel.addComponent(stopButton, 100, 230, 250, 20, "用户状态:", 60);
        this.getContentPane().add("Center", panel);
        this.okButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        this.setDefaultButton(okButton);
        this.setCancelButton(cancelButton);

        this.stopButton.addActionListener(this);
        
//       this.isFinanceButton.setSelected(true);
//            this.isPurchaseButton.setSelected(true);
//            this.isSaleButton.setSelected(true);
//            this.isStorageButton.setSelected(true);
//            this.hasCheckButton.setSelected(true);
//            this.hasPriceButton.setSelected(true);
    }

    private void savePasswordToDataRow() {
//        String password
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null) {
            String password = this.enterPswField.getText();
     if(password.equals("******"))
     {
          password= (String) dataRow.getColumnValue("password");
     }else{
            password = Md5.md5Encode(password);
            dataRow.setColumnValue("password", password);
        }
}
    }



   private void saveStatusValues() {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null) {
           dataRow.setColumnValue("financeRole", this.isFinanceButton.isSelected()?1:0);
           dataRow.setColumnValue("purchaseRole", this.isPurchaseButton.isSelected()?1:0);
           dataRow.setColumnValue("saleRole", this.isSaleButton.isSelected()?1:0);
           dataRow.setColumnValue("storageRole", this.isStorageButton.isSelected()?1:0);
           dataRow.setColumnValue("checkPermission", this.hasCheckButton.isSelected()?1:0);
           dataRow.setColumnValue("pricePermission", this.hasPriceButton.isSelected()?1:0);
           dataRow.setColumnValue("status", this.stopButton.isSelected()?1:0);

        }
    }

    public boolean doSave() {
        String name = usernameField.getText();
        if (name.equals("")) {
            MessageBox.showMessageDialog(this, "用户名不能为空!");
            return false;
        } else if (name.length() > 30) {
            MessageBox.showMessageDialog(this, "用户名长度不能超过30!");
            return false;
        }
        if (!this.enterPswField.getText().equals(this.validatePswField.getText())) {
            MessageBox.showMessageDialog(this, "登录密码与验证密码不一致!");
            return false;
        }
        if (this.usernameField.isEnabled() && this.dataSource.containsColumnValue("userName", name)) {
            MessageBox.showMessageDialog(this, "用户名已经存在，请重新取个名字!");
            return false;
        }
        try {
            savePasswordToDataRow();
            saveStatusValues();
            this.save();
        } catch (Exception ex) {
            MessageBox.showMessageDialog(this, "保存失败:" + ex.getMessage());
            return false;
        }
        return true;
    }

   
    public void doAdd() {
        if (this.dataSource == null || this.dataSource.getCurrentDataRow() == null) {
            this.modified = false;
        }
        if (doSave()) {
            this.modified = false;
            setVisible(false);
            this.dispose();
        }


    }

    public void newDataRow() {
        User user = new User();
        user.setCompany(Main.getMainFrame().getCompany());
        IDataRow dataRow = ObjectDataRow.newDataRow(user, "id", this.dbSupport);
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
    }

    public void newDataRow(User user) {
        IDataRow dataRow = ObjectDataRow.newDataRow(user, "id", this.dbSupport);
        this.dataSource.insertDataRow(dataRow);
        this.dataSource.last();
        refreshStatus();
    }

    private void refreshStatus()
    {
        IDataRow dataRow = this.dataSource.getCurrentDataRow();
        if (dataRow != null) {
            Integer v = (Integer)dataRow.getColumnValue("financeRole");
            if (v != null)
            {
                this.isFinanceButton.setSelected(v==1?true:false);
            }
            else
            {
                this.isFinanceButton.setSelected(true);
            }
            v = (Integer)dataRow.getColumnValue("purchaseRole");
            if (v != null)
            {
                this.isPurchaseButton.setSelected(v==1?true:false);
            }
            else
            {
                this.isPurchaseButton.setSelected(true);
            }
            v = (Integer)dataRow.getColumnValue("saleRole");
            if (v != null)
            {
                this.isSaleButton.setSelected(v==1?true:false);
            }
            else
            {
                this.isSaleButton.setSelected(true);
            }
            v = (Integer)dataRow.getColumnValue("storageRole");
            if (v != null)
            {
                this.isStorageButton.setSelected(v==1?true:false);
            }
            else
            {
                this.isStorageButton.setSelected(true);
            }
             v = (Integer)dataRow.getColumnValue("checkPermission");
            if (v != null)
            {
                this.hasCheckButton.setSelected(v==1?true:false);
            }
            else
            {
                this.hasCheckButton.setSelected(true);
            }
            v = (Integer)dataRow.getColumnValue("pricePermission");
            if (v != null)
            {
                this.hasPriceButton.setSelected(v==1?true:false);
            }
            else
            {
                this.hasPriceButton.setSelected(true);
            }
            v = (Integer)dataRow.getColumnValue("status");
            if (v != null)
            {
                this.stopButton.setSelected(v==0?false:true);
            }
            else
            {
                this.stopButton.setSelected(false);
            }
            
        }
        else
        {
            this.isFinanceButton.setSelected(true);
            this.isPurchaseButton.setSelected(true);
            this.isSaleButton.setSelected(true);
            this.isStorageButton.setSelected(true);
            this.hasCheckButton.setSelected(true);
            this.hasPriceButton.setSelected(true);
        }

    }

    public void closeDialog(WindowEvent e) {
        this.setVisible(false);
    }
    public void setVisible(boolean visible) {
        refreshStatus();
        super.setVisible(visible);
    }

}
