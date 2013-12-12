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

import org.free_erp.extbeans.CImageBackPane;
import com.jdatabeans.util.PaintTools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashWindow extends JWindow
{
    private JLabel label;
	public SplashWindow()
	{
		super();
        initC();
	}
	private void initC()
	{
		this.setSize(500, 282);
        Image image = null;
        try
        {
            image = ImageIO.read(this.getClass().getResource("/resources/flash.png"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        JPanel pane = new CImageBackPane(image, PaintTools.SCALED);
        pane.setBorder(BorderFactory.createLineBorder(Color.black));
        label = new JLabel("正在启动中，请稍候......");
        label.setForeground(Color.darkGray);
        pane.setLayout(null);
        pane.add(label);
        label.setBounds(10, 280, 300, 20);
//        label = new JLabel(new ImageIcon());
//        label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.getContentPane().add("Center", pane);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = this.getSize();
		this.setLocation((dimension.width - size.width) / 2, (dimension.height - size.height) / 2);		
	}
    
    public void dispose()
    {
        label = null;
        super.dispose();
    }
    
}
