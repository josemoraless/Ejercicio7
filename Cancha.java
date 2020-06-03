package futbolsala;

import java.util.ArrayList;

public class Cancha {
	Balon miBalon;
	Cronometro miCrono;
	int random = 0;
	Boolean chutar = false;
	Boolean pasar = true;
	public Cancha(Balon miBalon, Cronometro miCrono) {
		this.miBalon = miBalon;
		this.miCrono = miCrono;
	}

	public void lanzarPortero(String nombre) {
		while(chutar = false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void ponerManos() {

	}

	public void levantanrPortero() {

		asignarJugadorQueSaca("Jugador1");
	}

	public synchronized void chutar() {
		if (miCrono.tiempo != 0) {
			try {
				Thread.sleep(500);
				System.out.println("El" + miBalon.poseedor + " va a chutar");
				miBalon.potencia = (int) (Math.random() * 10);
				random = (int) (Math.random() * 3);
				if (random == 1) {
					miBalon.direccion = "derecha";
				} else if (random == 2) {
					miBalon.direccion = "centro";
				} else if (random == 3) {
					miBalon.direccion = "izquierda";
				}
				System.out.println(
						miBalon.poseedor + " ha chutado con fuerza " + miBalon.potencia + " hacia " + miBalon.direccion);
				chutar = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void pasar(String nombre) {
		if (miCrono.tiempo != 0) {
			while(pasar == false) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
