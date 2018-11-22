package ipchecker;


//Imports

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

//Class

public class IpChecker {
    
    //Loop and delay between each new IP check and email sent in milliseconds. Example: 600 * 1000 = 10 minutes.

    long delay = 600 * 1000;
    LoopTask task = new LoopTask();
    Timer timer = new Timer("TaskName");

    public void start() {
        timer.cancel();
        timer = new Timer("TaskName");
        Date executionDate = new Date(); // no params = now
        timer.scheduleAtFixedRate(task, executionDate, delay);
    }
    
    private class LoopTask extends TimerTask {
        @Override
        public void run() {
            try {
                TimedExecution.getIp();
            } catch (Exception ex) {
                Logger.getLogger(IpChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Email sent. The program will check your IP again in 10 minutes.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
            IpChecker executingTask = new IpChecker();
            executingTask.start();
            SendMail.main(args);
        }
    }