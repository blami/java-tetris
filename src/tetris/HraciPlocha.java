/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	WindowTetris.java 	implementuje hraci plochu						
 	
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

public class HraciPlocha
{
	int sirka;		/* sirka hraci plochy v kostickach */
	int vyska;		/* vyska hraci plochy v kostickach */
	int pole[][];           /* stavy jednotlivych poli (sviti/nesviti)*/
	
	
	/**     Vychozi konstruktor
	 **	@param sirka	sirka hraci plochy  
	 **	@param vyska	vyska hraci plochy 
	 **	@author Ondrej Balaz */
	public HraciPlocha(int sirka, int vyska)
	{
		this.sirka = sirka;
		this.vyska = vyska;
		
		pole = new int[vyska][sirka];
		/* vymazeme vsechna policka */
	}
	
	/**	getSirka() vrati sirku herni plochy
	 **	@return int */
	public int getSirka()	{ return sirka; }
  
	/**	getVyska() vrati vysku herni plochy
	 **	@return int*/
	public int getVyska()	{ return vyska;	 }
		
	/**	VymazPlochu() vymaze vsechna policka tak aby byla volna
	 **	@author Ondrej Balaz */
	public void VymazPlochu()
	{
		for(int x=0; x<sirka; x++)
			for(int y=0; y<vyska; y++)
				pole[y][x] = 0;	
	}
	
	/**	ObsadPole() nastavi policko jako obsazene
	 **	@param x		x-ova souradnice pole 
	 **	@param y 		y-ova souradnice pole 
	 **	@author Ondrej Balaz */
	public void ObsadPole(int x, int y)
	{
		if(x >= 0 && x < sirka && y >= 0 && y < vyska)
			pole[y][x] = 1;
	}
	
	/**	VymazPole() nastavi policko jako neobsazene
	 **	@param x		x-ova souradnice pole 
	 **	@param y 		y-ova souradnice pole 
	 **	@author Ondrej Balaz */
	public void VymazPole(int x, int y)
	{
		if(x >= 0 && x < sirka && y >= 0 && y < vyska)
			pole[y][x] = 0;
	}
	
	/**	ZjistiPole() zjisti zdali je policko obsazene
	 **	@param x		x-ova souradnice pole 
	 **	@param y		y-ova souradnice pole 
	 **	@return int 
	 **     @author Ondrej Balaz */
	public int ZjistiPole(int x, int y)
	{
		if(x >= 0 && x < sirka && y >= 0 && y < vyska)
			return pole[y][x];
		else
			return -1;
	}
                
	/**	JePlnaRada() zjisti zdali je y-ta rada plna
	 **	@param y		y-ova souradnice zkoumane rady
	 **	@return boolean
	 **     @author Ondrej Balaz */
	public boolean JePlnaRada(int y)
	{
		for(int x=0; x<sirka; x++)
		{
			if(pole[y][x] == 0)
				return false;
		}
		
		return true;
	}
	
	/**	KopirujRadu() zkopiruje zdrojovou radu do cilove
	 **	@param zdrojy	y-ova souradnice kopirovane rady
	 **	@param cily	y-ova souradnice destinace
	 **	@author Ondrej Balaz */
	public void KopirujRadu(int zdrojy, int cily)
	{
		for(int x=0; x<sirka; x++)
			pole[cily][x] = pole[zdrojy][x];
	}
	
	/**	SmazRadu() smaze radu na y-te souradnici
	 **	@param y		y-ova souradnice mazane rady */
	public void SmazRadu(int y)
	{
		for(int ry=y; ry>0; ry--)
			KopirujRadu(ry-1, ry);
		
		for(int x=0; x<sirka; x++)
			pole[0][x] = 0;
	}
	
	/**	SmazPlneRady() smaze vsechny plne rady a vrati jejich pocet
	 **	@return int*/
	public int SmazPlneRady()
	{
		int rad = 0;
		for(int y=vyska - 1; y>0; y--)
		{
			if(JePlnaRada(y))
			{
				SmazRadu(y);
				rad++;
				y++; /* postrcim se o ten prekopirovany index */
			}
		}
		
		return rad;
	}
}