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
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CNaviPane extends JPanel
{
    private Image backImage;
    private Image image;
    private Image title;
    public CNaviPane()
    {
        try
        {
            if (backImage == null)
            {
                backImage = ImageIO.read(getClass().getResource("/resources/new/naviback.png"));
                image = ImageIO.read(getClass().getResource("/resources/new/company.png"));
                title = ImageIO.read(getClass().getResource("/resources/new/navititle.png"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }


    Color bg = new Color(220, 229, 238);

    protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
//        g.setColor(bg);
//        g.fillRect(0, 0, rect.width, rect.height);//, WIDTH, WIDTH, WIDTH)
        PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), backImage, PaintTools.SCALED);
        //PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, 11), title, PaintTools.Y_SCALED);
        g.drawImage(image, 10, rect.height - image.getHeight(null), null);           
    }

    public void addButton(CNaviButton button)
    {
        this.add(button);
        //button.setParentPane(this);
        buttons.add(button);
    }

    public void setCurrentButton(CNaviButton button)
    {
        for(CNaviButton b:buttons)
        {
            if (b == button)
            {
                continue;
            }
            b.setSelected(false);
        }
        currentButton = button;
        button.setSelected(true);
    }

    public CNaviButton getSelectedButton()
    {
        return this.currentButton;
    }

    private CNaviButton currentButton;



    private Vector<CNaviButton> buttons = new Vector<CNaviButton>();

}
