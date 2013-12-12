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

import com.jdatabeans.beans.CButton;
import com.jdatabeans.beans.CField;
import com.jdatabeans.beans.data.IDataSource;
import com.jdatabeans.beans.data.JDataComboBox;
import com.jdatabeans.beans.data.JDataDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author TengJianfa mobile:086-13003311398 qq:575633370 www.free-erp.com
 */
public class CRejiggerTypeDialog extends JDataDialog implements ActionListener
{
    protected CField oldType;
    protected JDataComboBox newType;
    protected CButton okButton;
    protected CButton cancelButton;

    public CRejiggerTypeDialog(JDialog parent, IDataSource dataSource)
    {
        super(parent,dataSource);
        this.getCurrentProduct();
        this.initComps();
        this.setTitle("更改类别");
    }

    protected void getCurrentProduct()
    {
    }

    protected void initComps()
    {
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == okButton)
        {
            this.doSave();
        }
        if(source == cancelButton)
        {
            this.doClose();
        }
    }

    protected void doSave()
    {
    }

    public void doClose()
    {
        this.dispose();
    }
}
