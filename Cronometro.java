package futbolsala;

/**
 * Clase Cronometro
 * 
 * Hilo el cual su función es ir llevando el control del tiempo que dura el entrenamiento, en este caso sera de una hora.
 * 
 * @author Jose Morales
 *
 */
public class Cronometro extends Thread{
	/**
	 * Variable del tiempo, en este caso de una hora por petición del ejercicio.
	 */
	static int tiempo = 60;
	
	/**
	 * Run del hilo, realiza la acción de que cada 1 segundo vaya bajando el tiempo.
	 */
	public void run() {
		while(tiempo >= 0) {
			try {
				Thread.sleep(1000);
				System.out.println(tiempo);
				tiempo --;
			} catch (InterruptedException e) {
				
			e.printStackTrace();
			}
		}
		
	}
}
