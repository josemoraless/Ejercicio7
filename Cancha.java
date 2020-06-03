package futbolsala;

/**
 * Clase Cancha
 * 
 * Clase que sirve como tuveria, y que comparten los hilos jugador y portero.
 * Esta realiza las acciones de lanzarPortero, ponerManos, levantarPortero (Por
 * parte del portero) y Las acciones de pasat y chutar (Por parte del jugador)
 * Adem�s de asignar en toodo momento quien es el que saca.
 * 
 * @author Jose Morales
 *
 */
public class Cancha {

	/**
	 * Objeto bal�n a la cual se le asignan la potencia y direcci�n del mismo.
	 */
	Balon miBalon;
	/**
	 * Objeto cron�metro el cual tiene la funci�n de llevar el tiempo del
	 * entrenamiento en todo momento.
	 */
	Cronometro miCrono;
	/**
	 * N�mero aleatorio para saber la direccion a la que ira el bal�n del jugador y
	 * el movimiento del portero.
	 */
	int random = 0;
	/**
	 * Variable boolean que ayuda a saber cuando el portero puede lanzarse a por el
	 * bal�n y cuando el jugador puede pasar y chutar.
	 */
	Boolean chutar = false;
	/**
	 * Variable que dicta con que fuerza va a poner las manos el portero para parar
	 * el bal�n.
	 * 
	 */
	int fuerzaPortero = 0;
	/**
	 * Variable direcci�n del portero, la cual indica la direcci�n a la que se
	 * lanzar� el portero para parar el bal�n.
	 */
	String direccionPortero = "";

	
	/**
	 * Constructor de la clase
	 * 
	 * @param miBalon (Objeto balon, el cual lleva las funciones del bal�n)
	 * @param miCrono (Objeto Cron�metro, el cual lleva el tiempo del entrenamiento)
	 */
	public Cancha(Balon miBalon, Cronometro miCrono) {
		this.miBalon = miBalon;
		this.miCrono = miCrono;
	}

	/**
	 * M�todo lanzarPortero, el cual controla si el portero puede lanzarse a por el
	 * bal�n o no.
	 * 
	 * @param nombre (Variable nombre, recibe de par�metro el nombre del portero)
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
	 * M�todo que realiza el porteto, para saber la fuerza con la que va a poner las
	 * manos y la direcci�n a la que se va a tirar a parar el bal�n. adem�s de decir
	 * si ha conseguido parar el bal�n o si le han metido gol.
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
	 * M�todo que realiza el portero tras parar o recibir un gol, este se levanta y
	 * le devuelve el bal�n al jugador 1.
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
	 * M�todo que realiza el jugaro, el jugador elegido remata a porteria con una
	 * potencia y direcci�n aleatoria.
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
	 * M�todo que realiza el jugador, el cual usan los mismo para ir pas�ndose el
	 * bal�n los unos a lso otros.
	 * 
	 * @param nombre (String nombre, nombre de los jugadores que van a ir recibiendo
	 *               el bal�n)
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
				System.out.println(miBalon.poseedor + " est� buscando a quien pasar");
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
	 * M�todo que asigna despu�s de tirar devolverle la pelota al jugador 1.
	 * 
	 * @param nombre (String nombre, nombre del jugador 1)
	 */
	public synchronized void asignarJugadorQueSaca(String nombre) {
		miBalon.poseedor = nombre;
		notifyAll();

	}
}
