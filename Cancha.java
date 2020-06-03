package futbolsala;

import java.util.ArrayList;

public class Cancha {

	Balon miBalon;
	Cronometro miCrono;
	int random = 0;
	Boolean chutar = false;
	int fuerzaPortero = 0;
	String direccionPortero = "";

	public Cancha(Balon miBalon, Cronometro miCrono) {
		this.miBalon = miBalon;
		this.miCrono = miCrono;
	}

	public synchronized void lanzarPortero(String nombre) {
		while (chutar == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (miCrono.tiempo != 0) {
			fuerzaPortero = (int) (Math.random() * 10);
		}
	}

	public synchronized void ponerManos(String nombre) {
		if (miCrono.tiempo != 0) {
			random = (int) (Math.random() * 4);
			if (random == 1) {
				direccionPortero = "derecha";
			} else if (random == 2) {
				direccionPortero = "centro";
			} else if (random == 3) {
				direccionPortero = "izquierda";
			}

			System.out.println("El " + nombre + " aprieta las manos " + fuerzaPortero);
			System.out.println("El " + nombre + " se tira hacia " + direccionPortero
					+ " y pone las manos con una fuerza de " + fuerzaPortero);

			if (miBalon.direccion == direccionPortero && fuerzaPortero >= miBalon.potencia) {
				System.out.println("GRAN PARADAAAAAAAAAAAAAA!!!!!");
			} else {
				System.out.println("GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL!!!!!");
			}
		}
	}

	public synchronized void levantanrPortero(String nombre) {
		if (miCrono.tiempo != 0) {
			System.out.println("El " + nombre + " se levanta");

		}
		chutar = false;
		asignarJugadorQueSaca("Jugador1");
		notifyAll();
	}

	public synchronized void chutar() {
		if (miCrono.tiempo != 0) {
			try {
				Thread.sleep(500);
				System.out.println("El" + miBalon.poseedor + " va a chutar");
				miBalon.potencia = (int) (Math.random() * 10);
				random = (int) (Math.random() * 4);
				if (random == 1) {
					miBalon.direccion = "derecha";
				} else if (random == 2) {
					miBalon.direccion = "centro";
				} else if (random == 3) {
					miBalon.direccion = "izquierda";
				}
				System.out.println(miBalon.poseedor + " ha chutado con fuerza " + miBalon.potencia + " hacia "
						+ miBalon.direccion);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chutar = true;
		notifyAll();
	}

	public synchronized void pasar(String nombre) {
		while (chutar == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (miCrono.tiempo != 0) {
			try {
				Thread.sleep(500);
				System.out.println(miBalon.poseedor + " está buscando a quien pasar");
				System.out.println(miBalon.poseedor + " pasa " + nombre);
				miBalon.poseedor = nombre;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void asignarJugadorQueSaca(String nombre) {
		miBalon.poseedor = nombre;
	}
}
