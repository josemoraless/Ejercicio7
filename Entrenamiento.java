package futbolsala;

/**
 * Clase Entrenamiento 
 * 
 * Clase que principal del ejercicio, la cual constade un main que ejecuta el programa
 * 
 * @author Jose Morales
 *
 */
public class Entrenamiento {
	/**
	 * Main de la clase, Crea el objeto balón, cronómetro, cancha y ademas los 4 jugadores y el portero que van a participar en el entrenamiento
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Balon miBalon = new Balon();
		Cronometro miCrono = new Cronometro();
		Cancha miCancha = new Cancha(miBalon, miCrono);
		Portero miPortero = new Portero(miCancha);
		miPortero.setName("Portero");
		Jugador miJugador1 = new Jugador(miCancha);
		miJugador1.setName("Jugador1");
		Jugador miJugador2 = new Jugador(miCancha);
		miJugador2.setName("Jugador2");
		Jugador miJugador3 = new Jugador(miCancha);
		miJugador3.setName("Jugador3");
		Jugador miJugador4 = new Jugador(miCancha);
		miJugador4.setName("Jugador4");
		miCancha.asignarJugadorQueSaca(miJugador1.getName());
		miPortero.start();
		miJugador1.start();
		miJugador2.start();
		miJugador3.start();
		miJugador4.start();
		miCrono.start();
		try {
		miCrono.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		System.out.println("fin de entrenamiento");
		}
}
