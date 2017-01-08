// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GestionnaireJobBatch
{
   @Resource
   ManagedExecutorService m_managedExecutorService;

   @PostConstruct
   public void init()
   {
   }

   /**
    * @param p_jobName
    * @return
    */
   public long lancerJob(final String p_jobName) throws Throwable
   {
      return lancerJobAvecProprietes(p_jobName, null);
   }

   /**
    * @param p_jobName
    * @param p_properties
    * @return
    */
   public long lancerJobAvecProprietes(final String p_jobName, final Properties p_properties) throws Throwable
   {
      try
      {
         final Future<Long> retour = m_managedExecutorService.submit(new TacheLancementBatch(p_jobName, p_properties));
         return retour.get();
      }
      catch (final InterruptedException | ExecutionException e)
      {
         e.printStackTrace();
         throw e;
      }
   }
}
