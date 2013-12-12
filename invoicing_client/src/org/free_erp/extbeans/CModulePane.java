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
public class CModulePane   extends JPanel
{

    private Image bImage;
    private Image iconImage;
    public CModulePane()
    {
        try
        {
            bImage = ImageIO.read(getClass().getResource("/resources/moduleback.jpg"));
            iconImage = ImageIO.read(getClass().getResource("/resources/modulepic.png"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
    @Override
    protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        if (bImage != null)
        {
            //g.drawImage(iconImage, 0, rect.height - iconImage.getHeight(null), iconImage.getWidth(null), iconImage.getHeight(null), null);
            PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), bImage, PaintTools.SCALED);
            g.drawImage(iconImage, 0, rect.height - iconImage.getHeight(null), iconImage.getWidth(null), iconImage.getHeight(null), null);
        }

    }

     public void addButton(CNaviButton button)
    {
        this.add(button);
        button.setParentPane(this);
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