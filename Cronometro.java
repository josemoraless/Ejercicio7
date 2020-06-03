package futbolsala;

public class Cronometro extends Thread{
	
	int tiempo = 60;
	public Cronometro() {
		
	}
	public void run() {
		while(tiempo !=0) {
			//try {
				//Thread.sleep(1000);
			System.out.println(tiempo);
				tiempo --;
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}
		
	}
}
