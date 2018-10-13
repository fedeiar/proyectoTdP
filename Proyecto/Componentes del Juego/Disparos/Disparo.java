package Disparos;

import java.awt.*;
import Logica.*;
import javax.swing.*;

import Inteligencias.InteligenciaDisparo;
import ObjetoGeneral.Objeto;
import Visitors.Visitor;

public abstract class Disparo extends Objeto {

	//atributos
	public final static int ANCHO = 4;
	public final static int ALTO = 20;
	
	protected double velocidadMovimiento;
	protected double direccion; //direccion del disparo, medida en grados. (entre 0 y 360)
	protected int fuerzaDeImpacto;
	
	
	//constuctor
	
	public Disparo (Logica l,double v ,int f, double x , double y , double direc) {
		
		//----PARTE LOGICA DEL DISPARO----
		
		super(l);
		
		velocidadMovimiento = v;
		fuerzaDeImpacto = f;
		
		inteligencia = new InteligenciaDisparo(this , direc);
		
		
		//----PARTE GRAFICA DEL DISPARO----
		
		rec = crear_rectangulo(x, y, ANCHO, ALTO);
		
		etiqueta = new JLabel();
		
		etiqueta.setBounds(rec);
		etiqueta.setOpaque(true);
		etiqueta.setBackground(Color.WHITE);
		
	}
	
	//metodos
	
	public double getVelocidad() {
		return velocidadMovimiento;
	}
	
	public int getFuerzaImpacto() {
		return fuerzaDeImpacto;
	}
	
	
	
	//---forma de morir---
	
	public abstract void morir(); //se lo deja abstracto para que cada disparo pueda hacer algo diferente al morir
	
	//----VISITOR----
	
	public void serVisitado(Visitor v) {
		//cuerpo vacio ya que no tiene efecto que un disparo seaVisitado por alguien
	}
	
}
