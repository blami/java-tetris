/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	Tetris.java         hraci plocha + herni logika jako java bean						
 	
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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;

public class Tetris extends JPanel implements Runnable
{
	private HraciPlochaPanel pnlHraciPlocha = null;
	private HraciPlochaPanel pnlDalsiKostka = null;
	
	private JLabel lblDalsi = null;
	private JLabel lblLevel = null;
	private JLabel lblSkore = null;
	private JLabel lblLevelOut = null;
	private JLabel lblSkoreOut = null;
	private JLabel lblStav = null;
	
	private Thread HerniVlakno;	
	
	/* herni promenne */
	int level;
	int skore;
	int kostek;
	Kostka tatoKostka;
	Kostka dalsiKostka;
	boolean hrajese;
	HraciPlocha plocha;

	/**     run() je behove telo vlakna
	 **	@see java.lang.Runnable#run() 
	 **     @author Ondrej Balaz */
	public void run()
	{
		/* cinna cast herniho vlakna */
		while(HerniVlakno == Thread.currentThread())
		{
			try {
				Thread.sleep(70 + 700/level);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if(hrajese)
				PosunDolu();
		}
		
	}

	/**     Vychozi konstruktor
	 **	@author Ondrej Balaz */
	public Tetris()
	{
		super();
		initialize();
	}

	/**	initialize() inicializuje this
	 **	@author Ondrej Balaz */
	private void initialize()
	{
		lblStav = new JLabel();
		lblStav.setBounds(new java.awt.Rectangle(201,299,58,16));
		lblStav.setText("");
		lblSkoreOut = new JLabel();
		lblSkoreOut.setBounds(new java.awt.Rectangle(201,145,56,16));
		lblSkoreOut.setText("JLabel");
		lblLevelOut = new JLabel();
		lblLevelOut.setBounds(new java.awt.Rectangle(201,105,56,16));
		lblLevelOut.setText("JLabel");
		lblSkore = new JLabel();
		lblSkore.setBounds(new java.awt.Rectangle(201,128,56,14));
		lblSkore.setText("Skore:");
		lblSkore.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblLevel = new JLabel();
		lblLevel.setBounds(new java.awt.Rectangle(201,88,56,14));
		lblLevel.setText("Uroven:");
		lblLevel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
		lblDalsi = new JLabel();
		lblDalsi.setBounds(new java.awt.Rectangle(200,5,62,16));
		lblDalsi.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
		lblDalsi.setText("Dalsi kostka:");
		this.setLayout(null);
		this.setSize(265, 321);
		this.add(getPnlHraciPlocha(), null);
		this.add(getPnlDalsiKostka(), null);
		this.setVisible(true);
		this.add(lblDalsi, null);
		this.add(lblLevel, null);
		this.add(lblSkore, null);
		this.add(lblLevelOut, null);
		this.add(lblSkoreOut, null);
		this.add(lblStav, null);
		
		/* initialize game */
		this.addKeyListener(new KeyAdapter() 
			{
				public void keyPressed(KeyEvent e)
				{
					Tetris.this.keyPressed(e);
				}
			});

		this.setFocusable(true);
		
		plocha = new HraciPlocha(16,26);
		pnlHraciPlocha.setHraciPlocha(plocha);
		
		tatoKostka = new Kostka(plocha);
		dalsiKostka = new Kostka(null);
		
		kostek = 0;
		setLevel(1);
		setSkore(0);
		
		hrajese = false;
	}

	/**	NovaHra() zinicializuje novou hru
	 **	@author Ondrej Balaz */
	public void NovaHra()
	{
		kostek = 0;
		
		setLevel(1);
		setSkore(0);
		
		pnlHraciPlocha.getHraciPlocha().VymazPlochu();
		
		NovaKostka();
		
		hrajese = true;
	
		/* spustim vlakno */
		HerniVlakno = new Thread(this);
		HerniVlakno.start();
	}
                
        /**	Pauza() zapauzuje hru
	 **	@author Ondrej Balaz */	
	public void Pauza()
	{
                hrajese = false;
                HerniVlakno = null;

                switch(JOptionPane.showConfirmDialog(this, "Pauza" +
                        "\nChces ukoncit tuto hru ?"))
                {
                    case JOptionPane.YES_OPTION:
                        KonecHry();
                        pnlHraciPlocha.getHraciPlocha().VymazPlochu();
                        break;
                        
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CANCEL_OPTION:                        
        		/* spustim vlakno */
                        hrajese = true;
                	HerniVlakno = new Thread(this);
        		HerniVlakno.start();                
                        break;
                }                
        }                
        
	/**	KonecHry() ukonci dosavadni hru
	 **	@author Ondrej Balaz */
	public void KonecHry()
	{
		HerniVlakno = null;
		hrajese = false;
		
		System.out.println("Konec");
	}

	/**	setLevel() nastavi level (ovlivnuje rychlost kostek)
	 **	@param level 
	 **     @author Ondrej Balaz */
	private void setLevel(int level)
	{
		this.level = level;
		lblLevelOut.setText(new Integer(level).toString());
	}
	
	/**	setSkore() nastavi skore
	 **	@param skore 
	 **     @author Ondrej Balaz */
	private void setSkore(int skore)
	{
		this.skore = skore;
		lblSkoreOut.setText(new Integer(skore).toString());
	}
	
	/**	keyPressed() metoda ktera se vola na stisk klavesy nad oknem programu
	 **	@param e 
	 **     @author Ondrej Balaz */
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(hrajese)
                            Pauza();
                        else
                            NovaHra();
		}
		

		if(!hrajese)
			return;
		
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			PosunVlevo();
			Prekresli();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			PosunVpravo();
			Prekresli();
		}		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			PosunDolu();
			//setSkore(skore+1);
			Prekresli();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			Otocit();
			Prekresli();
		}
	
	}
	
	/**	NovaKostka() vytvori novou kostku
	 **	@return boolean 
	 **     @author Ondrej Balaz */
	public boolean NovaKostka()
	{
		kostek++;
		if(kostek>=10+2*level)
		{
			setLevel(level+1);
			kostek=0;
		}
		
		/* z dalsi udelam tuto kostku */
		tatoKostka = dalsiKostka;
		tatoKostka.setHraciPlocha(plocha);
		tatoKostka.x = 5;
		tatoKostka.y = -1;
		
		/* pripravim si jednu dalsi do foroty */
		dalsiKostka = new Kostka(null);
		pnlDalsiKostka.setHraciPlocha(dalsiKostka);
		pnlDalsiKostka.repaint();
		
		/* kdyz jde umistit tato kostka, udelam to */
		if(tatoKostka.JdePolozit(tatoKostka.x, tatoKostka.y))
		{
			tatoKostka.Poloz();
			return true;
		} 
		else 
			return false;
	}
	
	/**	Prekresli() prekresli okno hraci plochy
	 **	@author Ondrej Balaz */
	public void Prekresli()
	{
		pnlHraciPlocha.repaint();
	}

        /**	PosunVlevo() posune kosticku vlevo
	 **	@author Ondrej Balaz */
	public void PosunVlevo()
	{
		tatoKostka.Vyndej();
		
		if(tatoKostka.JdePolozit(tatoKostka.x-1, tatoKostka.y))
			tatoKostka.x--;
		
		tatoKostka.Poloz();
	}

        /**	PosunVpravo() posune kosticku vpravo
	 **	@author Ondrej Balaz */
	public void PosunVpravo()
	{
		tatoKostka.Vyndej();
		
		if(tatoKostka.JdePolozit(tatoKostka.x+1, tatoKostka.y))
			tatoKostka.x++;
		
		tatoKostka.Poloz();
	}
	
        /**	PosunDolu() posune dolu
	 **	@author Ondrej Balaz */        
	public synchronized boolean PosunDolu()
	{
		tatoKostka.Vyndej();
		
		boolean jdepolozit =  tatoKostka.JdePolozit(tatoKostka.x, tatoKostka.y+1);
		if(jdepolozit)
		{
			tatoKostka.y+=1;
		}
		
		tatoKostka.Poloz();
		
		if(!jdepolozit)
		{
			int rad = plocha.SmazPlneRady();
			setSkore(skore + rad*10);
			
			if(!NovaKostka())
			{
				KonecHry();
				/* zobrazit dialog */
				JOptionPane.showMessageDialog(this, "Prohral jsi!");
			};
		}
		
		Prekresli();
		return jdepolozit;
	}
        
        /**	Otocit() otoci kosticku
	 **	@author Ondrej Balaz */	
	public void Otocit()
	{
		tatoKostka.Vyndej();
		tatoKostka.Otoc();
		if(!tatoKostka.JdePolozit(tatoKostka.x, tatoKostka.y))
		{
			for(int i=0; i<3; i++)
				tatoKostka.Otoc();
		}
		
		tatoKostka.Poloz();
	}
	
	/*      ECLIPSE Visual Editor 
	 * 	autogenerated code	 */	
	private HraciPlochaPanel getPnlHraciPlocha()
	{
		if(pnlHraciPlocha == null)
		{
			pnlHraciPlocha = new HraciPlochaPanel();
			pnlHraciPlocha.setLocation(new java.awt.Point(4,4));
			pnlHraciPlocha.setSize(new java.awt.Dimension(192,312));
		}
		return pnlHraciPlocha;
	}

	private HraciPlochaPanel getPnlDalsiKostka()
	{
		if(pnlDalsiKostka == null)
		{
			pnlDalsiKostka = new HraciPlochaPanel();
			pnlDalsiKostka.setLocation(new java.awt.Point(200,24));
			pnlDalsiKostka.setSize(new java.awt.Dimension(60,60));
		}
		return pnlDalsiKostka;
	}
}
