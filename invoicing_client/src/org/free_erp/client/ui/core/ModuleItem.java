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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author afa
 */
public class ModuleItem  extends JComponent implements MouseListener
{
    private boolean isActive;
    private String text;
    private Image image; // 32*32
    private static Image activeImage; //36 * 36
    private final Color activeColor = new Color(0, 153, 51);//Color.GREEN;
    private final Color color = Color.BLACK;
    private final Font font = new Font("ËÎÌו", Font.PLAIN, 13);
    private int count = 0;
    private static Image backImage;

    public ModuleItem(String text, ImageIcon icon)
    {
        this.setSize(80, 120);
        this.text = text;
        this.image = icon.getImage();
        try
        {
            if (activeImage == null)
            {
                activeImage = ImageIO.read(getClass().getResource("/resources/module/bj.png"));
                backImage = ImageIO.read(getClass().getResource("/resources/module/book.png"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //this.activeImage.flush();
        this.addMouseListener(this);
        this.repaint();
    }
    
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.isActive = true;
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
        this.isActive = false;
        this.setCursor(Cursor.getDefaultCursor());
        this.repaint();
}

    public void mousePressed(MouseEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        //debug
        if (count < 1)
        {
            g.drawImage(this.activeImage, 0, 0, 1, 1, null);
            count++;
        }
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();
        Rectangle rect = this.getBounds();
        Color c = this.color;
        int x = (rect.width - 67)/2;
        int y = 5;
        if (this.isActive)
        {
            g.drawImage(this.activeImage, (rect.width - 72)/2, y, null);
        }
        y += 15;
        g.drawImage(backImage, x, y, null);
        g.drawImage(this.image, x + 20, y + 30, null);
        //int textLen = g.getFontMetrics().stringWidth(text);
        x = (rect.width - 67)/2 + 10;
        y += rect.height - 30;
        g.setColor(c);
        g.drawString(text, x, y);
        g.setColor(oldColor);
        g.setFont(oldFont);
    }

}
