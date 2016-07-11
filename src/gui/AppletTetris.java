/*
 	Tetris
 	Jednoducha pocitacova hra naprogramovana v jazyku Java
 	Copyright(c) 2006 Ondrej Balaz <ondra@blami.net>
 	
 	AppletTetris.java 	panel appletu hry						
 	
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
import javax.swing.JApplet;                                                                                                              
import tetris.Tetris;                                                                                                                    
                                                                                                                                         
public class AppletTetris extends JApplet                                                                                                
{                                                                                                                                        
        private Tetris jContentPane = null;                                                                                              

        /**     Vychozi konstruktor                                                                                                          
         **     @author Ondrej Balaz */                                                                                                  
        public AppletTetris()                                                                                                            
        {                                                                                                                                
                super();                                                                                                                 
        }                                                                                                                                

        /**     init() inicializuje applet                                                                                                   
         **     @see java.applet.Applet#init() */                                                                                        
        public void init()                                                                                                               
        {                                                                                                                                
                this.setSize(265, 321);                                                                                                  
                this.setContentPane(getJContentPane());                                                                                  
        }                                                                                                                                

        /**     getJContentPane() inicializuje contentpane                                                                               
         **     @return Tetris                                                                                                           
         **     @author Ondrej Balaz */                                                                                                  
        private Tetris getJContentPane()                                                                                                 
        {                                                                                                                                
                if(jContentPane == null)                                                                                                 
                {                                                                                                                        
                        jContentPane = new Tetris();                                                                                     
                        jContentPane.setLayout(new BorderLayout());                                                                      
                }                                                                                                                        
                return jContentPane;                                                                                                     
        }
}