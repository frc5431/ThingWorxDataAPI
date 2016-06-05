package main;

import json.Json;
import json.JsonObject;

public class test_run {
	
	public static void main(String[] args) {
		try {
			//RobotData.tick();
			//System.out.println(RobotData.getChopperState());
			//System.out.println(RobotData.isTeleop());
			//RobotData.updateByDB(1);
			//System.out.println(RobotData.getRobotGyro()[0]);
			//RobotData.closeDB();

			//ThingWorx worx = new ThingWorx();
			//worx.put_property("");
			//System.out.println(worx.get_property().toString());
			Sender send = new Sender("localhost", 5830);
			JsonObject toSend = Json.object()
					.add("xangle", "5")
					.add("yangle", "6")
					.add(name, value)
					
					;
			
			System.out.println(send.put_property("", toSend.toString()));
		} catch(Exception exc) {
			
		}
	}
}
