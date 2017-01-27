// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GestionnaireJobBatch
{

   @PostConstruct
   public void init()
   {
   }

   /**
    * @param p_jobName
    * @return
    */
   public void lancerJob(final String p_jobName)
   {
      lancerJobAvecProprietes(p_jobName, new Properties());
   }

   /**
    * @param p_jobName
    * @param p_properties
    * @return
    */
   public void lancerJobAvecProprietes(final String p_jobName, final Properties p_properties)
   {
      List<Long> jobDemarres = new ArrayList<Long>();

      try
      {
         jobDemarres = BatchRuntime.getJobOperator().getRunningExecutions("simple-batchlet-job");
      }
      catch (final NoSuchJobException e)
      {
         // Rien
      }
      // System.out.println("jobDemarres = " + jobDemarres);

      final long jobId = BatchRuntime.getJobOperator().start(p_jobName, p_properties);

      final JobExecution jobexec = BatchRuntime.getJobOperator().getJobExecution(jobId);
      // System.out.println("jobName = " + jobexec.getJobName());
   }
}
