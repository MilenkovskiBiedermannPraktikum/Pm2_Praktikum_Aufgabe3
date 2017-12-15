package RangierbahnhofMVC;

import java.util.Observable;
	import java.util.Observer;
	
	
public class BahnhofSimuliert implements Runnable, Observer {
	
		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub

		}

		@Override
		public void run() {
			RangierBahnhof bahnhof = new RangierBahnhof(20);
			while (true) {
				Lokführer lokführer = new Lokführer(bahnhof);
				lokführer.start();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println(e.getStackTrace());
				}
			}
		}
	}


