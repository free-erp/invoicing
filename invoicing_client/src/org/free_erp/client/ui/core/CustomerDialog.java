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

package org.free_erp.client.ui.core;

import javax.swing.JFrame;

/**
 *
 * @author Afa
 */
public class CustomerDialog extends QuestionDialog
{
    public CustomerDialog(JFrame parent)
    {
        super(parent);
        this.setTitle("软件定制需求");
        super.displayText.setText("简单的需求描述,心理价位:");
    }

    protected void doAct()
    {
        try
        {
            String comp = this.companyName.getText();
            String contact = this.contactPerson.getText();
            String mail = this.mailbox.getText();
            String adds = this.address.getText();
            String q = this.question.getText();
            Runtime.getRuntime().exec("explorer http://www.168erp.com/customize.asp?comp=" + comp + "&contact=" + contact + "&mail=" + mail + "&adds=" + address + "&function=" + q);


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        setVisible(false);
    }
    


}
