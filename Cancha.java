package futbolsala;

/**
 * Clase Cancha
 * 
 * Clase que sirve como tuveria, y que comparten los hilos jugador y portero.
 * Esta realiza las acciones de lanzarPortero, ponerManos, levantarPortero (Por
 * parte del portero) y Las acciones de pasat y chutar (Por parte del jugador)
 * Además de asignar en toodo momento quien es el que saca.
 * 
 * @author Jose Morales
 *
 */
public class Cancha {

	/**
	 * Objeto balón a la cual se le asignan la potencia y dirección del mismo.
	 */
	Balon miBalon;
	/**
	 * Objeto cronómetro el cual tiene la función de llevar el tiempo del
	 * entrenamiento en todo momento.
	 */
	Cronometro miCrono;
	/**
	 * Número aleatorio para saber la direccion a la que ira el balón del jugador y
	 * el movimiento del portero.
	 */
	int random = 0;
	/**
	 * Variable boolean que ayuda a saber cuando el portero puede lanzarse a por el
	 * balón y cuando el jugador puede pasar y chutar.
	 */
	Boolean chutar = false;
	/**
	 * Variable que dicta con que fuerza va a poner las manos el portero para parar
	 * el balón.
	 * 
	 */
	int fuerzaPortero = 0;
	/**
	 * Variable dirección del portero, la cual indica la dirección a la que se
	 * lanzará el portero para parar el balón.
	 */
	String direccionPortero = "";

	
	/**
	 * Constructor de la clase
	 * 
	 * @param miBalon (Objeto balon, el cual lleva las funciones del balón)
	 * @param miCrono (Objeto Cronómetro, el cual lleva el tiempo del entrenamiento)
	 */
	public Cancha(Balon miBalon, Cronometro miCrono) {
		this.miBalon = miBalon;
		this.miCrono = miCrono;
	}

	/**
	 * Método lanzarPortero, el cual controla si el portero puede lanzarse a por el
	 * balón o no.
	 * 
	 * @param nombre (Variable nombre, recibe de parámetro el nombre del portero)
	 */
	public synchronized void lanzarPortero(String nombre) {
		
		while (chutar == false) {
			if(miCrono.tiempo != 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				chutar = true;
			}
		}
		if (miCrono.tiempo != 0) {
			fuerzaPortero = (int) (Math.random() * 10); // Dicta con la fuerza con la que va a poner las manos el
													// portero.
		}
		notifyAll();
	}

	/**
	 * Método que realiza el porteto, para saber la fuerza con la que va a poner las
	 * manos y la dirección a la que se va a tirar a parar el balón. además de decir
	 * si ha conseguido parar el balón o si le han metido gol.
	 * 
	 * @param nombre (Sting nombre, nombre del portero)
	 * 
	 */
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
		notifyAll();
	}

	/**
	 * Método que realiza el portero tras parar o recibir un gol, este se levanta y
	 * le devuelve el balón al jugador 1.
	 * 
	 * @param nombre (String nombre, nombre del portero)
	 */
	public synchronized void levantarPortero(String nombre) {
		if (miCrono.tiempo != 0) {
			System.out.println("El " + nombre + " se levanta");
			asignarJugadorQueSaca("Jugador1");
		}
		chutar = false;
		notifyAll();
	}

	/**
	 * Método que realiza el jugaro, el jugador elegido remata a porteria con una
	 * potencia y dirección aleatoria.
	 * 
	 */
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
				chutar = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		notifyAll();
	}

	/**
	 * Método que realiza el jugador, el cual usan los mismo para ir pasándose el
	 * balón los unos a lso otros.
	 * 
	 * @param nombre (String nombre, nombre de los jugadores que van a ir recibiendo
	 *               el balón)
	 * 
	 */
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
		notifyAll();
	}

	/**
	 * Método que asigna después de tirar devolverle la pelota al jugador 1.
	 * 
	 * @param nombre (String nombre, nombre del jugador 1)
	 */
	public synchronized void asignarJugadorQueSaca(String nombre) {
		miBalon.poseedor = nombre;
		notifyAll();

	}
}
