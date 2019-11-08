import java.util.*;
import java.util.concurrent.*;

public class UF2ACT4 {

	// Creem un constructor que el cridarem per a fer els calculs dels num aleatoris
	static class Client implements Callable<String> {
		private int numClient;
		private int numArticles;

		public Client(int operador1, int operador2) {
			this.numClient = operador1;
			this.numArticles = operador2;
		}

		@Override
		public String call() throws Exception {

			String mostrarArticles = " (";

			int[] articles = new int[numArticles];

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
			System.out.println("Creant el client " + numClient + " amb " + numArticles 
					+ " articles " + mostrarArticles + ")");

			System.out.println("Client " + numClient + " passa per la caixa...");

			for (int i = 0; i < articles.length; i++) {

				Thread.sleep(articles[i]*100);
				System.out.println("Client " + numClient + " article " + (i+1) + "/" + articles.length 
						+ " (" + articles[i] + " segons)...");

				if (i == articles.length - 1){
					System.out.println("Client " + numClient + " article " + numArticles + "/" + numArticles + "(" 
							+ articles[articles.length] + " segons)...FINALITZAT");
				}
			}

			return "";		

		}

	}

	public static void main(String[] args) throws
	// Creem un ThreadPoolExecutor que creara 5 fils
	InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		List<Client> llistaTasques= new ArrayList<Client>();

		for (int i = 0; i < 10; i++) {
			Client client = new Client(i + 1, (int)(Math.random()*15) + 1);

			llistaTasques.add(client);

		}
		List <Future<String>> llistaResultats;
		// Guarda el resultat a llistaResultats

		llistaResultats = executor.invokeAll(llistaTasques);

		// no creara mes fils, i els fils que estiguin funcionan no es tancaran
		// fins que finalitzin la tasca
		executor.shutdown();
	}
}