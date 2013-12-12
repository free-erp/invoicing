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

import javax.swing.JButton;
import javax.swing.JToolBar;

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.core.MainFrame;
import org.free_erp.client.ui.main.Main;

public  class CBaseTempMainDetailDialog extends JDataDialog implements ActionListener
{
	protected JToolBar toolBar; 	
	protected JButton chooseSavedButton;
	protected CButton saveButton;
	protected CButton checkButton;
	protected CButton discardButton;
	
	protected CButton firstButton;
	protected CButton previousButton;
	protected CButton nextButton;
	protected CButton lastButton;
	protected CButton printButton;
	//protected JCheckBox stopButton;
	
	protected CButton closeButton;
	protected IDbSupport dbSupport;
	
	public CBaseTempMainDetailDialog(Frame parent, IDataSource dataSource)
	{
		super(parent, dataSource);
		//this.dbSupport = dbSupport;
		initComps();
		
	}
	protected void initComps()
	{
		this.getContentPane().setLayout(new BorderLayout());
		MainFrame mainFrame = Main.getMainFrame();
		this.toolBar = new JToolBar();
		this.getContentPane().add("North", this.toolBar);
		chooseSavedButton = new CButton("ѡ��ݸ�(&C)");
		chooseSavedButton.setIcon(mainFrame.getIcon("open"));
		
		saveButton = new CButton("����ݸ�");
		
		checkButton = new CButton("���");
		checkButton.setIcon(mainFrame.getIcon("edittype"));//temp
		
		discardButton = new CButton("����");
		discardButton.setIcon(mainFrame.getIcon("deletetype"));
		
		saveButton.setIcon(mainFrame.getIcon("save"));
		firstButton = new CButton("����");
		firstButton.setIcon(mainFrame.getIcon("first"));
		previousButton = new CButton("ǰһ��");
		previousButton.setIcon(mainFrame.getIcon("previous"));
		nextButton = new CButton("��һ��");
		nextButton.setIcon(mainFrame.getIcon("next"));
		lastButton = new CButton("ĩ��");
		lastButton.setIcon(mainFrame.getIcon("last"));
		printButton = new CButton("��ӡ");
		printButton.setIcon(mainFrame.getIcon("print"));
	
		closeButton = new CButton("�ر�");
		closeButton.setIcon(mainFrame.getIcon("close"));
		
		
		//closeButton.setIcon(mainFrame.getIcon("close"));
		this.toolBar.add(chooseSavedButton);
		this.toolBar.add(saveButton);
		this.toolBar.addSeparator();
		this.toolBar.add(checkButton);
		this.toolBar.add(discardButton);
		this.toolBar.addSeparator();
		this.toolBar.add(firstButton);
		this.toolBar.add(previousButton);
		this.toolBar.add(nextButton);
		this.toolBar.add(lastButton);
		this.toolBar.addSeparator();
		this.toolBar.add(printButton);
		this.toolBar.addSeparator();		
		this.toolBar.add(closeButton);		
		this.closeButton.addActionListener(this);
		this.chooseSavedButton.addActionListener(this);
		this.checkButton.addActionListener(this);
		this.discardButton.addActionListener(this);
		this.saveButton.addActionListener(this);
		this.firstButton.addActionListener(this);
		this.previousButton.addActionListener(this);
		this.nextButton.addActionListener(this);
		this.lastButton.addActionListener(this);
		this.printButton.addActionListener(this);		
		this.closeButton.addActionListener(this);
		
	}
	
	protected void doChoose()
	{
		MessageBox.showMessageDialog(Main.getMainFrame(), "��δʵ��!");
	}
	
	protected void doSave()
	{
		MessageBox.showMessageDialog(Main.getMainFrame(), "��δʵ��!");
	}
	
	protected void doNext()
	{
		this.dataSource.next();
	}
	
	protected void doPrevious()
	{
		this.dataSource.previous();
	}
	
	protected void doLast()
	{
		this.dataSource.last();
	}
	
	protected void doFirst()
	{
		this.dataSource.first();
	}
	
	protected void doClear()
	{
		MessageBox.showMessageDialog(Main.getMainFrame(), "��δʵ��!");
	}
	
	protected void doPrint()
	{
		MessageBox.showMessageDialog(Main.getMainFrame(), "��δʵ��!");
	}
	
	protected void doStop()
	{
		MessageBox.showMessageDialog(Main.getMainFrame(), "��δʵ��!");
	}
	
	protected void doClose()
	{
		this.dispose();
	}
	
	protected void doCheck()
	{
		
	}
	
	protected void doDiscard()
	{
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == this.chooseSavedButton)
		{
			this.doChoose();
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
		else if (source == this.printButton)
		{
			this.doPrint();
		}
		
		else if (source == this.closeButton)
		{
			this.doClose();
		}
		else if (source == this.discardButton)
		{
			this.doDiscard();
		}
		else if (source == this.checkButton)
		{
			this.doCheck();
		}
	}
	
	public void newDataRow()
	{
		
	}
}
