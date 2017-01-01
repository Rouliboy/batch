package fr.pe.jvi.batch;

import java.util.Properties;

import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.Dependent;

@Dependent
public class MyJob implements Runnable
{
   public void run()
   {
      System.out.println("LANCEMENT PAR MANAGED SCHEDULED EXECUTOR SERVICE");
      BatchRuntime.getJobOperator().start("test-batch-job", new Properties());
   }
}