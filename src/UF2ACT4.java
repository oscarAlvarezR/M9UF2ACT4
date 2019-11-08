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
				
				return "Client numero: " + numClient + "\nQuantitat articles: " + numArticles;
			}
			
		}

		public static void main(String[] args) throws
		// Creem un ThreadPoolExecutor que creara 5 fils
		InterruptedException, ExecutionException {
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
			List<Client> llistaTasques= new ArrayList<Client>();
			
			for (int i = 0; i < 50; i++) {
				Client calcula = new Client(i + 1, (int)(Math.random()*30));
				llistaTasques.add(calcula);
				System.out.println(i);
				Thread.sleep(30);
			}
			List <Future<String>> llistaResultats;
			// Guarda el resultat a llistaResultats
			llistaResultats = executor.invokeAll(llistaTasques);

			// no creara mes fils, i els fils que estiguin funcionan no es tancaran
			// fins que finalitzin la tasca
			executor.shutdown();

			// Mostra els resultats
			for (int i = 0; i < llistaResultats.size(); i++) {
				Future<String> resultat = llistaResultats.get(i);
				try {
					System.out.println("Resultat tasca "+i+ " és:" +
							resultat.get());
				} 
				catch (InterruptedException | ExecutionException e)
				{
				}
			}
		}
	}