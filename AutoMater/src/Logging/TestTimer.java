package Logging;

public class TestTimer
{
	private static long startTime;
	private static long endTime;
	
	public static void Start()
	{
		startTime = System.currentTimeMillis();
	}
	
	public static String Result()
	{
		endTime = System.currentTimeMillis();
		String timerMessage = System.getProperty("line.separator") + "-- Test completed in " + (endTime-startTime)/1000 + " seconds --";
		
		return timerMessage;
	}
}