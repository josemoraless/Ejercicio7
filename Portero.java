package futbolsala;

public class Portero extends Thread{
	Cancha miCancha;
	public Portero(Cancha miCancha) {
		this.miCancha=miCancha;
	}

}
