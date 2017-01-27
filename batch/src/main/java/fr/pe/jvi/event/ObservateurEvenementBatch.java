package fr.pe.jvi.event;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import fr.pe.jvi.batch.extension.GestionnaireBeansJobScope;

@ApplicationScoped
public class ObservateurEvenementBatch
{

   @Inject
   private GestionnaireBeansJobScope m_gestionnaire;

   public void observateurBatch(@Observes final EvenementSuppressionContexte p_evenementSuppressionBatch)
   {
      System.out.println("Observation d'un evenement de suppression Batch : " + p_evenementSuppressionBatch);
      m_gestionnaire.supprimerBeansParId(p_evenementSuppressionBatch.getBatchId());
   }
}
