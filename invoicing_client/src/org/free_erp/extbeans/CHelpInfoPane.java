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

package org.free_erp.extbeans;

import com.jdatabeans.util.PaintTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CHelpInfoPane extends JPanel
{
    private String text;
    private Image backImage;

    public CHelpInfoPane()
    {
       try
       {
                backImage = ImageIO.read(getClass().getResource("/resources/helppane.png"));
                this.setSize(750, 50);
                JLabel helpIcon = new JLabel(new ImageIcon(getClass().getResource("/resources/help.png")));
                this.setLayout(null);
                this.add(helpIcon);
                helpIcon.setBounds(10, 10, 30, 30);
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
    }

    public void setHelpInfo(String text)
    {
        this.text = text;
        this.repaint();
    }

    static final Color bg = new Color(41, 57, 57);
     protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
//        g.setColor(bg);
//        g.fillRect(0, 0, rect.width, rect.height);//, WIDTH, WIDTH, WIDTH)
        PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), backImage, PaintTools.SCALED);
        //PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, 11), title, PaintTools.Y_SCALED);
        g.setColor(bg);
        if (this.text != null)
        {
            g.drawString(text, 40, 30);
        }
    }


}
