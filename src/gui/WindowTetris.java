/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	WindowTetris.java 	okno standalone hry						
 	
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
package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import tetris.Tetris;


public class WindowTetris extends JFrame
{

	private Tetris jContentPane = null;


	/**	main
	 *	@param args
	 *	@author Ondrej Balaz */
	public static void main(String[] args)
	{
		WindowTetris window = new WindowTetris();
	}


	/**     vychozi konstruktor
	 **	@author Ondrej Balaz */
	public WindowTetris()
	{
		super();
		initialize();
	}


	/**	initialize() inicializuje this	
	 **	@author Ondrej Balaz */
	private void initialize()
	{
		this.setSize(272, 348);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Tetris");
		this.setVisible(true);
	}


	/**	getJContentPane inicializuje contentpane
	 **	@return
	 **	@author Ondrej Balaz */
	private Tetris getJContentPane()
	{
		if(jContentPane == null)
		{
			jContentPane = new Tetris();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setSize(new java.awt.Dimension(265,321));
		}
		return jContentPane;
	}

}