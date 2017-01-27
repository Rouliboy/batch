// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import java.util.Properties;

import javax.batch.api.listener.JobListener;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.Unbound;

import fr.pe.jvi.beans.ContexteSollicitationBatchSLD;
import fr.pe.jvi.beans.MonContexteRequestScoped;
import fr.pe.jvi.event.EvenementSuppressionContexte;

@Named("ListenerIntegrationSI")
@Dependent
public class ListenerIntegrationSI implements JobListener
{

   @Inject
   @Unbound
   private RequestContext m_contexteRequest;

   @Inject
   private JobContext jobContext;

   @Inject
   MonContexteRequestScoped contexteRequestScoped;

   @Inject
   private Event<EvenementSuppressionContexte> m_evenement;

   @Inject
   private ContexteSollicitationBatchSLD m_contexteBatch;

   @Override
   public void beforeJob() throws Exception
   {
      m_contexteRequest.activate();
      System.out.println("IntegrationSIJobListener before job - préparation contenu contexte");
      final JobOperator jobOperator = BatchRuntime.getJobOperator();
      final Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
      final String environnement = jobParameters.getProperty("env");
      // System.out.println("IntegrationSIJobListener environnement = " + environnement);
      // System.out.println("IntegrationSIJobListener contexte= " + contexte);

      System.out.println("IntegrationSIJobListener contexteRequestScoped = " + contexteRequestScoped);

      m_contexteBatch.setValeur(Thread.currentThread().getName());
   }

   @Override
   public void afterJob() throws Exception
   {
      System.out.println("IntegrationSIJobListener after job");
      m_contexteRequest.invalidate();
      m_contexteRequest.deactivate();

      m_evenement.fire(new EvenementSuppressionContexte(jobContext.getExecutionId()));

      // System.out.println("jobContext.getBatchStatus() = " + jobContext.getBatchStatus());
      // System.out.println("jobContext.getExitStatus() = " + jobContext.getExitStatus());
   }
}