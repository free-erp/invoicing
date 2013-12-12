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

import org.free_erp.client.ui.main.Main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 *
 * @author afa
 */
public class CWaitWindow extends JWindow
{
    private static CWaitWindow window;
    private JLabel messageLabel;
    public CWaitWindow(JFrame parent)
    {
        super(parent);
        this.setSize(600, 60);
        this.messageLabel = new JLabel("正在执行，请稍等......");
        messageLabel.setFont(new Font("楷体_GB2312", Font.PLAIN, 14));
        this.getContentPane().add("Center", messageLabel);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = this.getSize();
		this.setLocation((dimension.width - size.width) / 2, (dimension.height - size.height) / 2);
    }

    public void setWaitMessage(String message)
    {
        this.messageLabel.setText(message);
    }
    public static void showWaitWindow(final String message)
    {
        if (window == null)
        {
            window = new CWaitWindow(Main.getMainFrame());
        }
        window.setWaitMessage(message);
        window.setVisible(true);
        /*
        Runnable a;
        a = new Runnable(){
        public void run()
        {
            window.setWaitMessage(message);
            window.setVisible(true);
        }
        };
          a.run();
         */
        
    }

    public static void closeWaitWindow()
    {
        if (window != null)
        {
            window.setVisible(false);
        }
    }

}
