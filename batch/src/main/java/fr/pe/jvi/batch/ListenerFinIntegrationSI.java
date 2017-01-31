// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import fr.pe.jvi.event.EvenementSuppressionContexte;

@Named("ListenerFinIntegrationSI")
@Dependent
public class ListenerFinIntegrationSI extends AbstractJobListener
{

   @Inject
   private JobContext jobContext;

   @Inject
   private Event<EvenementSuppressionContexte> m_evenement;

   @Override
   public void afterJob() throws Exception
   {
      System.out.println("IntegrationSIJobListener after job");

      m_evenement.fire(new EvenementSuppressionContexte(jobContext.getExecutionId()));
   }
}