import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class apartat2 {

	// Creem un constructor que el cridarem per a fer els calculs dels num aleatoris
	static class Ordena implements Runnable {
		private int [] numDesordenats;

		public Ordena(int[] operador1) {
			this.numDesordenats = operador1;

		}

		// Es el que s'executara al arribar al executor
		@Override
		public void run()  {

			boolean cambis =true;
			while (cambis == true) {

				cambis = false;
				int numAnterior = Integer.MIN_VALUE;

				for (int i = 0; i < numDesordenats.length; i++) {


					if (numDesordenats[i] < numAnterior) {

						numDesordenats[i-1] = numDesordenats[i];
						numDesordenats[i] = numAnterior;
						cambis = true;
					}
					numAnterior = numDesordenats [i];


					System.out.print("(");
					for (int j = 0; j < numDesordenats.length; j++) {
					
						if (j == numDesordenats.length - 1 && j  == i) {
							System.out.println("[" + numDesordenats[j] + "])");
						} else if (j == numDesordenats.length - 1) {
							System.out.println(numDesordenats[j] + ")");
						} else if (j  == i){
							System.out.print("[" + numDesordenats[j] + "], ");
						} else {
							System.out.print(numDesordenats[j] + ", ");
						}
					}
				}


			}
			
			System.out.print("RESULTAT FINAL = " );
			System.out.print("(");
			for (int j = 0; j < numDesordenats.length; j++) {
			
				if (j == numDesordenats.length - 1) {
					System.out.println(numDesordenats[j] + ")");
				} else {
					System.out.print(numDesordenats[j] + ", ");
				}
			}


		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		// Creem un ThreadPoolExecutor que creara 5 fils

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

		int [] numDesordenats = {2,9,4,8,1,3,5,7,6};

		// Creem els clients

		Ordena intArray = new Ordena(numDesordenats);

		// Executem les tasques
		executor.submit(intArray);

		// no creara mes fils, i els fils que estiguin funcionan no es tancaran
		// fins que finalitzin la tasca
		executor.shutdown();
	}
}
