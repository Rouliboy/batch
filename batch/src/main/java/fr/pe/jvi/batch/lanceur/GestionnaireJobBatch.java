// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.batch.runtime.BatchRuntime;
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
      lancerJobAvecProprietes(p_jobName, null);
   }

   /**
    * @param p_jobName
    * @param p_properties
    * @return
    */
   public void lancerJobAvecProprietes(final String p_jobName, final Properties p_properties)
   {
      BatchRuntime.getJobOperator().start(p_jobName, p_properties);
   }
}
