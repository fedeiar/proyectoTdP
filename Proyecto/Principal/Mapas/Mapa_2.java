package Mapas;

import EnemigosConcretos.EnemigoConArma;
import EnemigosConcretos.EnemigoKamikazeDirigido;
import Logica.Logica;
import ObjetoGeneral.Objeto;
import TDALista.ListaDE;
import TDALista.PositionList;

public class Mapa_2 extends Mapa {

	//atributos
	
	 
	 
	//constructor
		 
	 public Mapa_2(Logica l) {
		super(l);
		mapa_siguiente = new Mapa_3(log);
	 }
		 
	 //metodos
		

	public PositionList<Objeto> obtenerObjetosIniciales(){
		PositionList<Objeto> L = new ListaDE<Objeto>();
		 
		
		for(int i=0 ; i<5 ; i++) {
			EnemigoConArma ene = new EnemigoConArma( log, 400 + i*100 , 100  , 2 , powerups[ i % CANT_MAX_PREMIOS].clone());
			L.addLast(ene);
			cantEnemigos++;
		}
		
		for(int i=0 ; i<5 ; i++) {
			EnemigoKamikazeDirigido ene = new EnemigoKamikazeDirigido( log, 300 + i*100 , 200  , 1 , powerups[ i % CANT_MAX_PREMIOS].clone());
			L.addLast(ene);
			cantEnemigos++;
		}
		 
		return L;
	 }
		 
}
