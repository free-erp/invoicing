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
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CImageBackPane  extends JPanel
{

    private Image bImage;
    private int type;
    public CImageBackPane(Image image)
    {
        type = PaintTools.X_SCALED;
        bImage = image;        
    }
    public CImageBackPane(Image image, int type)
    {
        this.type = type;
        bImage = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        if (bImage != null)
        {
            PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), bImage, type);
        }
    }

}