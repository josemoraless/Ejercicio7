package futbolsala;

public class Cronometro extends Thread{
	static int tiempo = 60;
	public Cronometro() {
		
	}
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
