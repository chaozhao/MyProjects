import java.util.Timer;
import java.util.TimerTask;
public class timer_test 
{
	public static void main(String[] args) 
	{
		int delay = 10000; // delay for 5 sec.
	    int period = 1000; // repeat every sec.
	    Timer timer = new Timer();

	    timer.scheduleAtFixedRate
	    (new TimerTask() 
	    {
	      public void run() 
	      {
	        System.out.println("doing"); 
	      }
	    }, 1743, period);
	}

}
