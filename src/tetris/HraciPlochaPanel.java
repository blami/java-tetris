/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	HraciPlochaPanel.java   je bean ve kterem se da hrat tetris, neobsahuje
                                veci jako casovani, vlakna ...
 	
        This program is free software; you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation; either version 2 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program; if not, write to the Free Software
        Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class HraciPlochaPanel extends JPanel
{
	private HraciPlocha plocha;

        /**     Vychozi konstruktor
	 **	@author Ondrej Balaz */
	public HraciPlochaPanel()
	{
		super();
		initialize();
	}
	
	/**	initialize() inicializuje herni panel
	 **	@author Ondrej Balaz */
	private void initialize()
	{
		this.setSize(120,240);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.setPreferredSize(new Dimension(120,240));
		this.setBackground(new Color(0,0,0));
	}
        
	/**     paintComponent() prekresluje komponentu
	 **	@see javax.swing.JComponent#paintComponent(java.awt.Graphics) */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0, getWidth(), getHeight());
		if(plocha != null)
		{
			int sirka = plocha.getSirka();
			int vyska = plocha.getVyska();
			
			for(int y=0; y<vyska; y++)
				for(int x=0; x<sirka; x++)
					if(plocha.ZjistiPole(x,y) != 0)
					{
						g.setColor(Color.YELLOW);
						g.fillRect(x*12, y*12, 10, 10);
					}
		}
	}
	
	/**	setHraciPlocha() nastavuje hraci plochu
	 **	@param plocha
	 **     @author Ondrej Balaz */
	public void setHraciPlocha(HraciPlocha plocha)
	{
		this.plocha = plocha;
	}	
	
	/**	getHraciPlocha() vrati hraci plochu
	 **	@param plocha 
	 **     @author Ondrej Balaz */
	public HraciPlocha getHraciPlocha()
	{
		return this.plocha;
	}
}