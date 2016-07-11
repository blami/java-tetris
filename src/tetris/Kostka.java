/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	Kostka.java             implementuje kosticku (jako tvar)
 	
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

import java.util.Random;

public class Kostka extends HraciPlocha
{
	int x;				/* x-ova pozice cele kostky */
	int y;				/* y-ova pozice cele kostky */
	HraciPlocha plocha;             /* hraci plocha kam je kostka umistovana */
	
	/* jednotlive vyskytujici se druhy kostek (standart tetris) */
	static int kostky[][][] =
	{
		/* typ 0 */
		{
			{0,0,0,0,0},
			{0,0,0,0,0},
			{1,1,1,1,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
		},
		/* typ 1 */
		{
			{0,0,0,0,0},
			{0,1,1,0,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
		},
		/* typ 2 */
		{
			{0,0,0,0,0},
			{0,1,1,1,0},
			{0,0,0,1,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
		},
		/* typ 3 */
		{
			{0,0,0,0,0},
			{0,0,0,1,0},
			{0,1,1,1,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
		},
		/* typ 4 */
		{
			{0,0,0,0,0},
			{0,1,1,0,0},
			{0,0,1,1,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
		},
		/* typ 5 */
		{
			{0,0,0,0,0},
			{0,0,1,1,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}
		},
		/* typ 6 */
		{
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,1,1,1,0},
			{0,0,1,0,0},
			{0,0,0,0,0}
		}
	};
	/* konec definice kostek */
	
	/**     Vychozi konstruktor
	 **     @param plocha 
	 **	hraci plocha
	 **	@author Ondrej Balaz */
	public Kostka(HraciPlocha plocha)
	{
		super(5,5);
		x = 0;
		y = 0;
		this.plocha = plocha;
		
		/* vyberu nahodny typ kostky */
		Random r = new Random();
		ZvolTyp(r.nextInt(kostky.length));
	}
        
	/**	ZvolTyp() urci typ kostky a zkopiruje prislusna data do interni struktury
	 **	@param typ 
	 **     @author Ondrej Balaz */
	public void ZvolTyp(int typ)
	{
		/* zreplikuji obraz typu do teto konkretni kostky */
		for(int x=0; x<sirka; x++)
			for(int y=0; y<vyska; y++)
				pole[y][x] = kostky[typ][y][x];
	}
	
	/**	Poloz() prekopiruje kosticku do hraci plochy
	 **	@author Ondrej Balaz */
	public void Poloz()
	{
		if(plocha == null) 
			return;
		
		for(int y=0; y<pole.length; y++)
			for(int x=0; x<pole[y].length; x++)
				if(pole[y][x] != 0) plocha.ObsadPole(x+this.x,y+this.y);
	}
		
	/**	Vyndej() vymaze kosticku z hraci plochy (tohle+Poloz() je vzdy jeden krok posuvu)
	 **	@author Ondrej Balaz */
	public void Vyndej()
	{
		if(plocha == null)
			return;
		
		for(int y=0; y<pole.length; y++)
			for(int x=0; x<pole[y].length; x++)
				if(pole[y][x] != 0) plocha.VymazPole(x+this.x,y+this.y);		
	}
	
	/**	JdePolozit() zjisti jestli lze kosticku prekopirovat na novex,novey
	 **	@param novex	nova x-ova souradnice kosticky
	 **	@param novey	nova y-ova souradnice kosticky
	 **	@return boolean 
	 **     @author Ondrej Balaz */
	public boolean JdePolozit(int novex, int novey)
	{
		if(plocha == null)
			return false;
		
		for(int y=0; y<pole.length; y++)
			for(int x=0; x<pole[y].length; x++)
				if(pole[y][x] != 0 && plocha.ZjistiPole(novex+x, novey+y) != 0) 
					return false;
		
		return true;
	}
	
	/**	Otoc() otoci kosticku o 90 stupnu
	 **	@author Ondrej Balaz */
	public void Otoc()
	{
		int pom[][] = new int[5][5]; /* pomocne pole pro rotaci kostky */

		for(int y=0; y<pole.length; y++)
			for	(int x=0; x<pole[y].length; x++)
				pom[4-x][y] = pole[y][x];
		
		for(int y=0; y<pole.length; y++)
			for(int x=0; x<pole[y].length; x++)
				pole[y][x] = pom[y][x];
	}
	
	
	/**	setHraciPlocha() nastavi hraci plochu kostky (dalsi -> tato ...)
	 **	@param plocha	je objekt plochy
	 **     @author Ondrej Balaz */
	public void setHraciPlocha(HraciPlocha plocha)
	{
		this.plocha = plocha;
	}
}