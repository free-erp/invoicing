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
import javax.swing.JPanel;

/**
 *
 * @author afa
 */
public class CGroupPane  extends JPanel
{
    private static final Color color = new Color(65, 71, 101);
    private String title;
    private static Image bgImage;
    	public CGroupPane(String title)
		{
			this.title = title;
            if (bgImage == null)
            {
                try
                {
                    bgImage = ImageIO.read(getClass().getResource("/resources/groupback.png"));
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }

		}
        protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        g.setColor(lineColor);
        int fontLen = g.getFontMetrics().stringWidth(title);
        int fontHeight = g.getFont().getSize();
        PaintTools.fillImageBackground(g, new Rectangle(0, fontHeight, rect.width, rect.height), bgImage, PaintTools.X_SCALED);
        g.drawLine(0, fontHeight, 0, rect.height - 1);
        g.drawLine(0, fontHeight, 10, fontHeight);
        g.drawLine(20 + fontLen, fontHeight, rect.width - 1, fontHeight);

        //g.drawLine(0, 0, rect.width - 1, 0);
        g.drawLine(rect.width - 1, fontHeight, rect.width - 1, rect.height - 1);
        g.drawLine(0, rect.height - 1, rect.width - 1, rect.height - 1);

        g.setColor(color);
        g.drawString(title, 15, fontHeight + 5);

    }


        private static final Color lineColor = new Color(230, 232, 241);
}
