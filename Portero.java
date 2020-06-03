package futbolsala;

public class Portero extends Thread {
	Cancha miCancha;

	public Portero(Cancha miCancha) {
		this.miCancha = miCancha;
	}

	public void run() {
		while (miCancha.miCrono.tiempo > 0) {
			miCancha.lanzarPortero(this.getName());
			miCancha.ponerManos(this.getName());
			miCancha.levantanrPortero(this.getName());
		}
	}

}
