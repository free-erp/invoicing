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

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CDialog;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.CPasswordField;
import com.jdatabeans.beans.CTableComboBox;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.JDataTable;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.beans.data.table.ITableColumnModel;
import com.jdatabeans.beans.data.table.JDataTableColumn;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.main.Main;
import org.free_erp.service.clientservice.IServiceManager;
import org.free_erp.service.entity.base.Company;
import org.free_erp.service.entity.base.SystemLog;
import org.free_erp.service.entity.base.User;
import org.free_erp.service.entity.storage.StorageAlertPo;
import org.free_erp.service.logic.ICompanyService;
import org.free_erp.service.logic.ISystemManageService;
import org.free_erp.service.logic.IWSLoginService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CNetLoginDialog extends CDialog
{
	private CTableComboBox companyField;
	private CField nameField;
	private CPasswordField passwordField;

	private JButton okButton;
	private JButton cancelButton;
    private JButton newButton;
	private boolean passed = false;
    private int userID;

	public CNetLoginDialog(Frame frame)
	{
		super(frame);
		initComp();
        //this.companyField.setHorizontalAlignment(JLabel.LEFT);
        String oldCompanyId = Main.getSystemConfig().getOldCompanyId();
        if (oldCompanyId != null)
        {
           try
           {
                 this.companyField.setSelectedItem(Integer.parseInt(oldCompanyId));
           }
           catch(Exception ex)
           {
               ex.printStackTrace();
           }
        }
        String userName = Main.getSystemConfig().getOldUserName();
        if (userName != null)
        {
            this.nameField.setText(userName);
            this.passwordField.requestFocus();
        }
		//this.nameField.setText("admin");
		//this.passwordField.setText("admin");
		//this.companyField.setText("1001");
		this.setDefaultButton(okButton);
		this.nameField.setCaretPosition(this.nameField.getText().length());
        this.newButton.setToolTipText("添加新套帐");

	}

	public boolean isPassed()
	{
		return passed;
	}

	public void setPassed(boolean passed)
	{
		this.passed = passed;
	}

	private void initComp()
	{

		this.getContentPane().setLayout(null);

		this.setTitle("请登陆");
		this.setSize(350, 210);

		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/resources/key.gif")));
		this.addComponent(label, 10, 50, 80, 80);
		addDisplayText("请输入套帐号、用户名和密码:", 20, 10, 200, 20);
		//addDisplayText("请输入用户名和密码:", 70, 20);
        ICompanyService companyService = Main.getServiceManager().getCompanyService();
        JDataTable table = new JDataTable();
        List<Company> companys = companyService.findCompanys();
        List<IDataRow> dataRows = new ArrayList<IDataRow>();
        for(Company comp:companys)
        {
            dataRows.add(ObjectDataRow.newDataRow(comp, "id", null));
        }
        table.getDataSource().insertDataRows(dataRows);
        ITableColumnModel columnModel = table.getTableColumnModel();
        JDataTableColumn column = new JDataTableColumn();
		column.setHeaderText("套帐号");
		column.setColumnName("id");
		column.setWidth(160);
		columnModel.addColumn(column);

        column = new JDataTableColumn();
		column.setHeaderText("企业名称");
		column.setColumnName("name");
		column.setWidth(200);
		columnModel.addColumn(column);
		this.companyField = new CTableComboBox(table, "id");
        this.companyField.setPanelSize(200, 100);

		nameField = new CField();
		passwordField = new CPasswordField();

		okButton = new CButton("确定(&O)");
		cancelButton = new CButton("取消(&C)");
        newButton = new CButton("_");
		super.addComponent(companyField, 170, 40, 120, 20, "套帐号", 40);
        super.addComponent(newButton, 295, 40, 20, 20);


		 super.addComponent(nameField, 170, 70, 120, 20, "用户名", 40);
		 super.addComponent(passwordField, 170, 100, 120, 20, "密  码", 40);
		 this.addComponent(okButton, 130, 140, 80, 25);
		 this.addComponent(cancelButton, 220, 140, 80, 25);
         /*
		super.addComponent(nameField, 170, 55, 120, 20, "姓名", 40);
		super.addComponent(passwordField, 170, 85, 120, 20, "密码", 40);
		this.addComponent(okButton, 130, 125, 80, 25);
		this.addComponent(cancelButton, 220, 125, 80, 25);
          */
		this.setDefaultButton(okButton);


		this.setCancelButton(cancelButton);

		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				doOK();
			}
		});

        newButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				addNewCompany();
			}
		});

		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				setVisible(false);

			}
		});
		this.addWindowListener(new WindowAdapter()
		{
            @Override
			public void windowClosed(WindowEvent e)
			{
				if (!passed)
				{
					System.exit(0);
				}
			}
		});
	}

    private void addNewCompany()
    {
//        CAddCompanyInfoDialog d = new CAddCompanyInfoDialog(this);
//        d.show();
//        if (d.getAddedId() > 0)
//        {
//            this.companyField.setText(String.valueOf(d.getAddedId()));
//        }
        //JOptionPane.showMessageDialog(this, "hello!");
    }

	private void doOK()
	{
		// test!
		try
		{
			int companyId = Integer.parseInt(String.valueOf(this.companyField.getText()));
			String userName = this.nameField.getText();
			String password = this.passwordField.getText();
//			if (Main.isWebServiceStyle())
//			{
//				WebServiceManager wsManager = (WebServiceManager) Main.getServiceManager();
//				IWSLoginService loginService = wsManager.getLoginService();
//                if (loginService.isValidUser(companyId, userName, password))
//				{
//					wsManager.initHandler(companyId, userName, password);
//					ISystemManageService manager = wsManager.getSystemManageService();
//					company = manager.getCompany(companyId);
//                    user = manager.getUser(company, userName);
//                    if (user != null && user.isValid())
//                    {
//                        this.passed = true;
//                        this.dispose();
//                    }
//                    else
//                    {
//                        MessageBox.showErrorDialog(this, "用户名,密码或套帐号不对!");
//                        this.nameField.requestFocusInWindow();
//                    }
//				}
//                else
//				{
//					MessageBox.showErrorDialog(this, "用户名,密码或套帐号不对!");
//					this.passwordField.requestFocusInWindow();
//				}
//			}
//            else
//			{
				IServiceManager manager = Main.getServiceManager();
				ISystemManageService service = manager.getSystemManageService();
				if (service.isValidUser(companyId, userName, password))
				{
					ISystemManageService smanager = manager.getSystemManageService();
					company = smanager.getCompany(companyId);
                    user = smanager.getUser(company, userName);
                     if (user != null && user.isValid())
                    {
                        this.passed = true;
                        SystemLog systemLog = new SystemLog();
                        systemLog.setCompany(company);
                        systemLog.setUser(user);
                        systemLog.setFormDate(new Timestamp(System.currentTimeMillis()));
                        systemLog.setContent("登录本系统");
                        smanager.saveSystemLog(systemLog);
                        this.dispose();
                        this.showStorageAlertDialog(company);
                    }
                    else
                    {
                        MessageBox.showErrorDialog(this, "该用户已经停用，请与管理员联系!");
                        this.nameField.requestFocusInWindow();
                    }

				}
                else
				{
					MessageBox.showErrorDialog(this, "用户名,密码或套帐号不对!");
					this.passwordField.requestFocusInWindow();
				}
			//}
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
			MessageBox.showErrorDialog(this, "服务器没有开启,或网路不通!----原始错误:"+ ex.getMessage());
		}
	}

	private Company company;
	public Company getCompany()
	{
		return company;
	}
        private User user;

	public User getUser()
	{
		return user;
	}

    private void showStorageAlertDialog(Company compay)
    {
        List<StorageAlertPo> pos = Main.getServiceManager().getStorageService().findStorageAlertPos(compay);
        if(pos != null && pos.size() > 0)
        {
            Main.getMainFrame().showStorageAlertQueryDialog(company);
        }
    }
}
