package Personajes;
import Visitors.*;
import Logica.*;
import NivelPersonajes.*;
import ObjetoGeneral.Objeto;

import java.awt.Color;

import javax.swing.*;

import Disparos.DisparoJugador;
public class Jugador extends Personajes {
	
	//--------------ATRIBUTOS----------------
	
	protected int vidas; //al perder toda su HP, el jugador pierde una vida
	
	//jugador tiene como atributos estos datos ya que ademas de los provistos por nivel, pueden ser incrementados por algun powerup
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int fuerzaDisparo;
	
	protected NivelJugador_1 miNivel; //redefine el atributo miNivel de Personajes
	
	
	
	//--------------CONSTRUCTOR--------------
	
	public Jugador (Logica l,int x , int y ) { 
		
		//---parte logica del jugador---
		super(l,x,y);
		//vis = new VisitorJugador(l);
		miNivel = new NivelJugador_1();
		
		
		
		HP = miNivel.getHP();
		velocidadMovimiento = miNivel.getVelocidadMovimiento();
		velocidadDisparo = miNivel.getVelocidadDisparo();
		fuerzaDisparo = miNivel.getFuerzaDisparo();
		vidas = 3;
		
		//---parte grafica del jugador---
		imagen = new ImageIcon("Sprites/Frisk.png");
		etiqueta = new JLabel();
		etiqueta.setBounds(rec);
		etiqueta.setIcon(imagen);
		
		
	}
	
	//----------------METODOS----------------
	
	//NIVELES
	
	public NivelPersonaje getNivel() {
		return miNivel;
	}
	
	public void setNivel(int n) {
		if(n==1)
			miNivel = new NivelJugador_1();
		else {
			//mas adelante agregar proximos niveles
		}
		
		etiqueta.setIcon(miNivel.getImagen());
	}
	
	//VELOCIDAD de movimiento y disparo
	
	public int getFuerzaDisparo() {
		return miNivel.getFuerzaDisparo();
	}
	
	public int getVelocidadDisparo() {
		return velocidadDisparo;
	}
	
	public int getVelocidadMovimiento() {
		return velocidadMovimiento;
	}
	
	public void setFuerzaDisparo(int n) {
		fuerzaDisparo = n;
	}
	
	public void setVelocidadDisparo(int n) {
		velocidadDisparo = n;
	}
	
	public void setVelocidadMovimiento(int n) {
		velocidadMovimiento = n;
	}
	
	//DISPARO
	
	public void disparar() {
		DisparoJugador disparoJ = new DisparoJugador(log, velocidadDisparo , fuerzaDisparo ,
				 getX() + this.ANCHO / 2 , getY() );
		log.agregarObjeto(disparoJ);
	}
	
	//Vidas y HP
	
	public int getHP() {
		return HP;
	}
	
	public int getVidas() {
		return vidas;
	}
		
	public void quitarHP(int n) {
		if ( HP - n > 0)
			HP -= n;
		else {
			vidas--;
			HP = miNivel.getHP();
		}
		if(vidas<0) {
			morir();
		}
		
	}
	
	//-----MOVIMIENTO------
		
	
	public void mover(int n) {
		if(n==0) {
			moverIzquierda();
		}
		else
			if(n==1)
				moverDerecha();
	}
	
	private void moverIzquierda() {
		if(rec.x - velocidadMovimiento > 0) { 
			setX(rec.x - velocidadMovimiento); //  y aca *
		}
		else {
			long aux = Math.round(rec.getWidth());
			setX(AnchoMapa - (int)aux); //si bien un long es un int pero mas largo, hace falta casting
			
		}
	}
	
	private void moverDerecha() {
		if(rec.x + velocidadMovimiento < AnchoMapa - rec.getWidth()) { //ver rec.getWidth() para ver si entra bien
			setX(rec.x + velocidadMovimiento); // aca *
		}
		else
			setX(0);
	}
	
	//----VISITOR----
	
	public void serVisitado(Visitor v) {
		v.afectarJugador(this);
	}
	
}
