package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import json.JsonObject;

public class RobotData {
	
	static double
		xangle = 0.0,
		yangle = 0.0,
		zangle = 0.0,
		leftrpm = 0.0,
		rightrpm = 0.0,
		ldistance = 0.0,
		leftdrivepower = 0.0,
		rdistance = 0.0,
		rightdrivepower = 0.0,
		xaccel = 0.0,
		yaccel = 0.0,
		zaccel = 0.0,
		choppers = 0.0,
		auton = 0.0,
		teleop = 0.0,
		enabled = 0.0;
		
	static String
		timestamp = null,
		laststamp = " ";
	
	static boolean updating = false;

	static ThingWorx worx = null;
	
	public static double toDigit(String value) {
		return Double.valueOf(value.replaceAll("[^\\d.]", ""));
	}
	
	public static double JD(JsonObject got, String toGrab) {
		return toDigit(got.get(toGrab).asString());
	}
	
	public static void tick() {
		try {
			if(worx == null) worx = new ThingWorx();
			JsonObject got = worx.get_property();
			timestamp = got.get("timestamp").asString();
			updating = (timestamp != laststamp);
			laststamp = timestamp;
			xangle = JD(got, "xangle");
			yangle = JD(got, "yangle");
			zangle = JD(got, "zangle");
			xaccel = JD(got, "xaccel");
			yaccel = JD(got, "yaccel");
			zaccel = JD(got, "zaccel");
			leftrpm = JD(got, "leftrpm");
			rightrpm = JD(got, "rightrpm");
			ldistance = JD(got, "ldistance");
			rdistance = JD(got, "rdistance");
			leftdrivepower = JD(got, "leftdrivepower");
			rightdrivepower = JD(got, "rightdrivepower");
			choppers = JD(got, "choppers");
			auton = JD(got, "auton");
			teleop = JD(got, "teleop");
			enabled = JD(got, "enabled");
		} catch(Exception fail) {
			System.out.println("Failed to update values...");
			fail.printStackTrace();
		}
	}
	
	public static boolean isUpdating() {
		return updating;
	}
	
	public static Date getTimeStamp() {
		try {
			DateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ENGLISH);
			Date date = format.parse(timestamp);
			return date;
		} catch(Exception err) {
			return null;
		}
 	}
	
	public static double[] getRobotGyro() {
		final double[] current = {xangle, yangle, zangle};
		return current;
	}
	
	public static double[] getRobotAccel() {
		final double[] current = {xaccel, yaccel, zaccel};
		return current;
	}
	
	public static double[] getRobotDistance() {
		final double[] current = {ldistance, rdistance, ((ldistance + rdistance) / 2)};
		return current;
	}
	
	public static double[] getRobotFlyWheel() {
		final double[] current = {leftrpm, rightrpm};
		return current;
	}
	
	public static double[] getRobotDrive() {
		final double[] current = {leftdrivepower, rightdrivepower};
		return current;
	}
	
	public static boolean getChopperState() {
		return (choppers > 0.9);
	}
	
	public static boolean isAuton() {
		return (auton > 0.9);
	}
	
	public static boolean isTeleop() {
		return (teleop > 0.9);
	}
	
	public static boolean isEnabled() {
		return (enabled > 0.9);
	}
}
