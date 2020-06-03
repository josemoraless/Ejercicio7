package futbolsala;

public class Jugador extends Thread{
	Cancha miCancha;
	int random;
	public Jugador(Cancha miCancha) {
		this.miCancha=miCancha;	
	}
	public void run() {
		while(miCancha.miCrono.tiempo > 0) {
			random = (int) (Math.random() * 3);
			miCancha.pasar(this.getName());
			if(random == 1) {
				miCancha.chutar();
			}
		}
	}
}
