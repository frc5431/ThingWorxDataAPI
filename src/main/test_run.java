package main;

public class test_run {
	
	public static void main(String[] args) {
		try {
			RobotData.tick();
			System.out.println(RobotData.getChopperState());
			System.out.println(RobotData.isTeleop());

			//ThingWorx worx = new ThingWorx();
			//worx.put_property("");
			//System.out.println(worx.get_property().toString());
			//Sender send = new Sender("localhost", 5830);
			//System.out.println(send.put_property("xangle", "278"));
		} catch(Exception exc) {
			
		}
	}
}
