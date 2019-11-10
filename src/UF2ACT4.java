import java.util.*;
import java.util.concurrent.*;

public class UF2ACT4 {

	// Creem un constructor que el cridarem per a fer els calculs dels num aleatoris
	static class Client implements Runnable {
		private int numClient;
		private int numArticles;

		public Client(int operador1, int operador2) {
			this.numClient = operador1;
			this.numArticles = operador2;
		}

		// Es el que s'executara al arribar al executor
		@Override
		public void run()  {

			String mostrarArticles = " (";

			// Creem l'array on guardem el temps dels articles
			int[] articles = new int[numArticles];

			// esperem 3 segons per crear cada usuari
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Assignem el temps a cada article i creem el string per a mostrarlo mes tard
			for (int i = 0; i < articles.length; i++) {
				articles[i] = (int)(Math.random() * 8) + 2;
				if (i == 0) {
					mostrarArticles = mostrarArticles.substring(0,mostrarArticles.length()) 
							+ articles[i];
				} else {
					mostrarArticles = mostrarArticles.substring(0,mostrarArticles.length()) 
							+ ", " + articles[i];
				}
			}
			// Mostrem el missatge de client creat un cop tenim totes les dades
			System.out.println("Creant el client " + numClient + " amb " + numArticles 
					+ " articles " + mostrarArticles + ")");
			
			
			// Mostrem el missatge del client passant per caixa
			System.out.println("Client " + numClient + " passa per la caixa...");

			// Processem els articles amb el temps que triga cadascun
			for (int i = 0; i < articles.length; i++) {

				try {
					Thread.sleep(articles[i]*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Si es l'ultim article mostrem missatge final
				if (i + 1 == articles.length) {
					System.out.println("Client " + numClient + " article " + numArticles + "/" + numArticles + " (" 
							+ articles[i] + " segons)...FINALITZAT");
				// Si no mostrem el missatge normal
				} else {
					System.out.println("Client " + numClient + " article " + (i+1) + "/" + articles.length 
							+ " (" + articles[i] + " segons)...");
				}
			}
		}
	}

	public static void main(String[] args) throws
	// Creem un ThreadPoolExecutor que creara 5 fils
	InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		
		// Creem els clients
		for (int i = 0; i < 100; i++) {
			Client client = new Client(i + 1, (int)(Math.random()*30) + 1);

			// Executem les tasques
			executor.submit(client);
		}
		// no creara mes fils, i els fils que estiguin funcionan no es tancaran
		// fins que finalitzin la tasca
		executor.shutdown();
	}
}