package RangierbahnhofMVC;

public class BahnhofSimuliert implements Runnable {
	
	private RangierBahnhof bhf;
	
	public BahnhofSimuliert(int gleisanzahl){
		this.bhf = new RangierBahnhof(gleisanzahl);
	}

		public RangierBahnhof getBhf() {
		return bhf;
	}



		@Override
		public void run() {
			while (true) {
				Lokführer lokführer = new Lokführer(bhf);
				lokführer.start();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println(e.getStackTrace());
				}
				
			}
		}
	}


