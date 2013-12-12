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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 *
 * @author afa
 */
public class CTitlePane extends JPanel
{
    private Image nameImage;
    private Image b;
    private Image topRightImage;
    private Image mainImage;
    private Image xiChuangSoftImage;


    public CTitlePane()
    {
        try
        {
                this.setPreferredSize(new Dimension(300, 95));
                nameImage = ImageIO.read(getClass().getResource("/resources/title.png"));
                this.setBackground(bg);
                b = ImageIO.read(getClass().getResource("/resources/topback.png"));
                topRightImage = ImageIO.read(getClass().getResource("/resources/topright.png"));
                mainImage = ImageIO.read(getClass().getResource("/resources/main.png"));
                xiChuangSoftImage = ImageIO.read(getClass().getResource("/resources/zfkeji.png"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    Color bg = new Color(173, 216, 230);
    protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        g.drawImage(mainImage, 370, 5, null);
        g.drawImage(topRightImage, rect.width - topRightImage.getWidth(null), 0, null);
        //g.drawImage(b, 0, 0, null);
        PaintTools.fillImageBackground(g, rect, b, PaintTools.X_SCALED);
        g.drawImage(nameImage, 20, 20, null);
        g.drawImage(xiChuangSoftImage, rect.width - xiChuangSoftImage.getWidth(null) - 20, 10, null);


    }

}
