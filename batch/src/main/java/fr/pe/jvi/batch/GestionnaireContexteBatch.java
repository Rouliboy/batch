// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.Unbound;

@Dependent
public class GestionnaireContexteBatch
{
   @Inject
   private GestionnaireBeansRequestContext m_gestionnaireBeansRequestContext;

   @Inject
   private JobContext m_jobContext;

   @Inject
   @Unbound
   private RequestContext m_contexteRequest;

   /**
    * @param p_classeDemandeur
    */
   public void demarrerContexte(final Class<?> p_classeDemandeur)
   {
      // On ne démarre le contexte que s'il est inactif (i.e il n'a pas encore été démarré ou on est
      // dans un nouveau thread).
      if (false == m_contexteRequest.isActive())
      {
         m_contexteRequest.activate();
         final CleRequestContext cle = new CleRequestContext(p_classeDemandeur, m_jobContext.getExecutionId());
         m_gestionnaireBeansRequestContext.ajouterBean(cle, m_contexteRequest);
      }
   }

   /**
    * @param p_classeDemandeur
    */
   public void arreterContexte(final Class<?> p_classeDemandeur)
   {
      final CleRequestContext cle = new CleRequestContext(p_classeDemandeur, m_jobContext.getExecutionId());
      m_gestionnaireBeansRequestContext.supprimerBean(cle);
   }
}
