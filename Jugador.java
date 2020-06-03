package futbolsala;

/**
 * Clase Jugador
 * 
 * Hilo que realiza todas las funciones que necesita el jugador como chutar o pasar.
 * 
 * @author Jose Morales
 *
 */
public class Jugador extends Thread{
	/**
	 * Objeto cancha, el cual da valores al objeto balon y objeto cronometro
	 */
	Cancha miCancha;
	/**
	 * Variable random, que dice con que frecuencia un jugador puede chutar a la porteria.
	 */
	int random;
	/**
	 * Constructor de la clase.
	 * 
	 * @param miCancha (Objeto cancha)
	 */
	public Jugador(Cancha miCancha) {
		this.miCancha=miCancha;	
	}
	/**
	 * Run del Jugador que llama a las acciones que va a realizar el mismo.
	 */
	public void run() {
		while(miCancha.miCrono.tiempo > 0) {
			random = (int) (Math.random() * 3);
			miCancha.pasar(this.getName()); // Acción que realiza el jugador para pasar el balón.
			if(random == 1) {
				miCancha.chutar(); // Acción que realiza el jugador para tirar a porteria.
			}
		}
	}
}
