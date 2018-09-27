package Personajes;
import Visitors.*;
import Inteligencias.*;
import Logica.*;
import NivelPersonajes.NivelEnemigo_1;
import ObjetoGeneral.Objeto;
import Premios.*;
public abstract class Enemigo extends Personajes {

	//atributos
	
	
	protected Inteligencia inteligencia;
	protected NivelEnemigo_1 miNivel; // redefine el atributo de personaje
	
	//constructor
	
	public Enemigo(Logica l,int x, int y,  int nivel) { 
		super(l,x,y);
		vis = new VisitorEnemigo();
		setNivel(nivel);
		
		/*
			no inicializamos los demas atributos porque estan en miNivel.
			y como los enemigos no agarran powerups, van a permanecer constantes.
			el nivel de un enemigo determina la dificultad del nivel.
		*/
	}
	
	
	private void setNivel(int n) {
		if (n==1)
			miNivel = new NivelEnemigo_1();
		else {
			//mas adelante cuando esten implementados los otros nivelEnemigo_, iran aqui
		}
	}
	
	//metodos
	
	
	public Inteligencia getInteligencia() {
		return inteligencia;
	}
	
	public int getPuntaje() {
		return miNivel.getPuntaje();
	}
	
	public int getVelocidadMovimiento() {
		return miNivel.getVelocidadMovimiento();
	}
	
	public int getVelocidadDisparo() {
		return miNivel.getVelocidadDisparo();
	}
	
	public int getHP() {
		return miNivel.getHP();
	}
	
	
	public void setInteligencia(Inteligencia i) {
		inteligencia = i;
	}

	public void quitarHP(int n) {
		if( HP - n > 0)
			HP -= n;
		else {
			
			morir(); //probar y preguntar, morir esta dentro de este metodo
		}
	}
	
	
	//----movimiento comun a todos los enemigos----
	
	public void accionar() {
		inteligencia.mover(AnchoMapa);
	}
	
	//----forma de morir comun a todos los enemigos----
	
	public void morir() {
		int puntaje = miNivel.getPuntaje();
		log.setPuntaje(log.getPuntaje() + puntaje);
		log.setCantEnemigos(log.cantEnemigos() - 1);
		log.eliminarObjeto(this);
		
		//al morir un enemigo dropea un powerup, �que powerup? esto puede verse con PROTOYPE
		
		log.lanzarPremio(this.getX(), this.getY());
	}
	
	
	//------------COLISION-------------
	
	public void serVisitado(Visitor v) { // ser�a el "aceptar(Visitor v)"
		v.afectarEnemigo(this);
		
	}
	
	
	
	
}
