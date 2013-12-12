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
public class CNaviButton extends JComponent implements MouseListener
{
    private boolean isActive;
    private String text;
    private Image image; // 32*32
    private static final Color color = new Color(65, 71, 101);//Color.GREEN;
    private static Image buttonImage = null;
    private static final Font font = new Font("ËÎÌå", Font.PLAIN, 14);
    private int count = 0;

    public CNaviButton(String text, ImageIcon icon)
    {
        this.text = text;
        this.image = icon.getImage();
        try
        {
            buttonImage = ImageIO.read(getClass().getResource("/resources/buttonback.png"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //this.activeImage.flush();
        this.addMouseListener(this);
        this.setOpaque(false);
    }
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.isActive = true;
        this.repaint();

        //this.repaint();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
        this.isActive = false;
        this.setCursor(Cursor.getDefaultCursor());

        //this.repaint();

        this.repaint();


}

    public void mousePressed(MouseEvent e) {
        if (this.parentPane != null)
        {
            this.parentPane.setCurrentButton(this);
        }

        this.repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        Rectangle rect = this.getBounds();
        //g.clearRect(0, 0, rect.width, rect.height);
        g.setColor(color);
        int x = 20;//(rect.width - 4)/2;
        int y = 5;
        if (this.isSelected)
        {
            PaintTools.fillImageBackground(g, new Rectangle(0, 0, rect.width, rect.height), buttonImage, PaintTools.SCALED);
        }
        g.drawImage(this.image, x, y, null);
        g.setFont(font);
        String t = text;
        if (this.isSelected)
        {
           t += "  >>>";

        }

        g.drawString(t, x + 55, 30);
    }

    public void setParentPane(CModulePane pane)
    {
        this.parentPane = pane;
    }

    public void setSelected(boolean b)
    {
        isSelected = b;
        if (this.parentPane != null)
        {
            //this.parentPane.repaint();
        }
        this.repaint();
    }

    public void repaint()
    {
        super.repaint();
        //this.parentPane.repaint();

//        this.parentPane.repaint();
//        super.repaint();
//        this.parentPane.repaint();
//        super.repaint();
//        this.parentPane.repaint();
//        super.repaint();
//        this.parentPane.repaint();
//        super.repaint();
//        this.parentPane.repaint();
//        super.repaint();
//        this.parentPane.repaint();
//        super.repaint();

    }

    private boolean isSelected = false;

    private CModulePane parentPane;
}
