package futbolsala;

/**
 * Clase Portero
 * 
 * Hilo del portero que realiza sus respectivas acciones mientras el tiempo se lo permita.
 * 
 * @author Jose Morales
 *
 */
public class Portero extends Thread {
	/**
	 * Objeto cancha, el cual da valores al objeto balon y objeto cronometro
	 */
	Cancha miCancha;
	/**
	 * Constructor de la clase, el cual recibe como par�metro el objeto cancha creado en la clase principal del ejercicio.
	 * 
	 * @param miCancha (Objeto cancha)
	 */
	public Portero(Cancha miCancha) {
		this.miCancha = miCancha;
	}

	/**
	 * Run del Portero, que llama a las acciones que va a realizar el mismo.
	 */
	public void run() {
		while (miCancha.miCrono.tiempo > 0) {
			miCancha.lanzarPortero(this.getName()); // Acci�n que realiza el portero y que dice a donde se va a tirar el portero para parar el bal�n.
			miCancha.ponerManos(this.getName()); // Acci�n que realiza el portero y que dice con cuanta fuerza ha intentado parar el bal�n.
			miCancha.levantarPortero(this.getName()); // Acci�n que realiza el portero tras recibir el tiro.
		}
	}

}
