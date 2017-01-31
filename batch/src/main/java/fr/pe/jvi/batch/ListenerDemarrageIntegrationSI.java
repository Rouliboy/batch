// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import java.util.Properties;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import fr.pe.jvi.beans.ContexteSollicitationBatchSLDImpl;

@Named("ListenerDemarrageIntegrationSI")
@Dependent
public class ListenerDemarrageIntegrationSI extends AbstractJobListener
{

   @Inject
   private JobContext jobContext;

   @Inject
   private ContexteSollicitationBatchSLDImpl m_contexteBatch;

   @Override
   public void beforeJob() throws Exception
   {
      // TODO:
      // Alimentation du contexte
      // Démarrage du rapport
      System.out.println("IntegrationSIJobListener before job - préparation contenu contexte");

      final JobOperator jobOperator = BatchRuntime.getJobOperator();
      final Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());

      m_contexteBatch.setEnvironnement(jobParameters.getProperty("env"));
      m_contexteBatch.setValeur(Thread.currentThread().getName());
   }
}