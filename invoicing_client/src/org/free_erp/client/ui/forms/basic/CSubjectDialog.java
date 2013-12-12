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

package org.free_erp.client.ui.forms.basic;

import com.jdatabeans.beans.CPanel;
import com.jdatabeans.beans.data.IDataRow;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.IDbSupport;
import com.jdatabeans.beans.data.JDataField;
import com.jdatabeans.beans.data.ObjectDataRow;
import com.jdatabeans.print.CPrintDialog;
import com.jdatabeans.util.MessageBox;
import org.free_erp.client.ui.forms.CBaseDetailedDialog;
import org.free_erp.client.ui.main.Main;
import org.free_erp.client.ui.util.ReportUtilities;
import org.free_erp.client.ui.util.ReportVO;
import com.jdatabeans.beans.CPagePane;
import org.free_erp.service.entity.accounting.Subject;
import org.free_erp.service.entity.accounting.SubjectCatalog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CSubjectDialog extends CBaseDetailedDialog
{
	private CSubjectManageWindow manageWindow;
	protected JDataField idField;
    private JDataField nameField;
	private JDataField shortNameField;
	private JDataField helpCodeField;
	private JDataField commentField;
    private SubjectCatalog subjectCatalog;

	public CSubjectDialog(Frame parent, IDataSource dataSource, IDbSupport support)
	{
        super(parent, dataSource, support);
        this.stopButton.setVisible(false);
		this.initC();
	}

    public CSubjectDialog(Frame parent, CSubjectManageWindow manageWindow, IDataSource dataSource, IDbSupport support)
	{
		super(parent, dataSource, support);
        this.toolBar.remove(this.stopButton);
        this.rejiggerTypeButton.setVisible(false);
		this.manageWindow = manageWindow;
		this.initC();
	}

     private void initC()
	{
        this.setTitle("会计科目");
		this.setSize(730, 210);
		idField = new JDataField("number", String.class, dataSource);
        idField.setEnabled(false);

		nameField = new JDataField("name", String.class, dataSource);
        nameField.setRequired("科目名称", true);
        nameField.setMaxLength(30);

		shortNameField = new JDataField("shortName",String.class,dataSource);
        shortNameField.setMaxLength(30);

		helpCodeField = new JDataField("code",String.class,dataSource);
        helpCodeField.setMaxLength(10);

	    
		commentField =  new JDataField("comments",String.class,dataSource);
        commentField.setMaxLength(255);

		//JTabbedPane tabbedPane = new JTabbedPane();
		JPanel p = new JPanel();
		p.setLayout(null);
		
		p.setPreferredSize(new Dimension(730, 180));

                CPagePane panel = new CPagePane();
		this.getContentPane().add("Center", p);
                p.setLayout(null);
                p.add(panel);
                panel.setBounds(20, 5, 690, 120);

		
		int x = 80;
		int y = 10; // row
		panel.addComponent(idField, x, y, 130, 20, "科目编号", 60);
		panel.addComponent(nameField, x + 200, y, 250, 20, "科目名称", 60);

        y += ROW_SPAN;
        x = 80;
		panel.addComponent(shortNameField, x, y, 130, 20, "简称", 60);
		panel.addComponent(helpCodeField, x + 200, y, 250, 20, "助记码", 60);
					
		y += ROW_SPAN;
        x = 80;
		panel.addComponent(commentField, x, y, 450, 20, "备注", 60);
		
		
	}


	public void doSaveAndNew()
	{
		// TODO Auto-generated method stub
		if (this.doSave())
		{
		   this.newDataRow();
		}
		//this.dataSource
	}

	public boolean newDataRow()
	{
        this.saveButton.setEnabled(true);
        this.saveAndNewButton.setEnabled(true);
	    SubjectCatalog catalog = null;
        if(subjectCatalog != null)
        {
            catalog = subjectCatalog;
        }
		if (this.manageWindow != null)
		{
			catalog = (SubjectCatalog)this.manageWindow.getCurrentCatalog();
		}
		if (catalog == null)
		{
		//get the default catalog;
			//temp

			MessageBox.showMessageDialog(this, "您没有选择类别,请先选择类别!"); //看需求如何定义
            return false;
		//	catalog = new CustomerCatalog();
		//	catalog.setId(1);
		}
		Subject subject = new Subject();
		subject.setCatalog(catalog);
        subjectCatalog = catalog;
        subject.setMainSubjectCatalog(catalog.getMainCatalog());
		subject.setCompany(Main.getMainFrame().getCompany());
        subject.setDebitCredit(catalog.getDebitCredit());
		IDataRow dataRow = ObjectDataRow.newDataRow(subject, "id", this.dbSupport);
		this.dataSource.insertDataRow(dataRow);
		this.dataSource.last();
        return true;
	}

    public void doPrint()
    {
        if(!this.doSave())
        {
            return;
        }
        Map variables = new HashMap();
        ReportVO vo = new ReportVO();
        Image image = Main.getMainFrame().getCompanyLogo();
        vo.setImage(image);
        vo.setTitle("合计科目单报表");
        variables = ReportUtilities.creatParameterMap(vo);
        CPrintDialog printDialog = new CPrintDialog(this, this.getClass().getResourceAsStream("/com/e68erp/demo/client/report/jaspers/accounting/SubjectDetail.jasper"),variables,this.dataSource.getCurrentDataRow());
		printDialog.setVisible(true);
    }
}
