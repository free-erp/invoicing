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
import java.awt.Frame;
import java.util.Collection;
import javax.swing.JOptionPane;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.beans.data.JDataField;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JToolBar;


public class CRoleManageDialog extends JDataDialog
{
    protected IDbSupport dbSupport;
	private CRoleManageWindow manageWindow;
     protected JButton choose;
     protected JButton saveButton;


	protected JButton closeButton;
	private JDataField name;
    //   MainFrame mainFrame = Main.getMainFrame();
	public CRoleManageDialog(Frame parent, IDataSource dataSource)
	{    
		super(parent, dataSource);

		//removeButton.setIcon(mainFrame.getIcon("delete"));
            
                
		initC();
		createDbSupport();
	}

	private void initC()
	{
            	this.setTitle("角色信息");
		this.setSize(780, 500);
		this.getContentPane().setLayout(new BorderLayout()); 
		MainFrame mainFrame = Main.getMainFrame();

		JToolBar toolBar = new JToolBar();

		this.getContentPane().add("North", toolBar);

		choose = new JButton("打开");
           /*     JMenuItem ss=new JMenuItem("系统管理员");//零时命名
                 JMenuItem ss1=new JMenuItem("输单员");//零时命名
                  JMenuItem ss2=new JMenuItem("统计员");//零时命名
                   JMenuItem ss3=new JMenuItem("经理");//零时命名
                   choose.add(ss);
                    choose.add(ss1); 
                    choose.add(ss2);
                     choose.add(ss3);*/
         //       choose.setSize(80, 20);
		saveButton = new JButton("保存");
		saveButton.setIcon(mainFrame.getIcon("save"));
		closeButton = new CButton("关闭(&C)");
		closeButton.setIcon(mainFrame.getIcon("close"));
		toolBar.add(choose);
		toolBar.add(saveButton);
		toolBar.add(closeButton);
	}
	private void createDbSupport()
	{
		this.dbSupport =  new IDbSupport(){

			public void delete(Object obj)
			{
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
			public void save(Object obj)
			{
				
				JOptionPane.showMessageDialog(null, "暂未实现!");
				
			}
			public void deleteAll(Collection<Object> objs)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
			public void saveAll(Collection<Object> objs)
			{
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "暂未实现!");
			}
		};
	}
	
	public void choose()
	{
            
	}
	public void newDataRow(){
	}

}
