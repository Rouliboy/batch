// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import java.util.Properties;
import java.util.concurrent.Callable;

import javax.batch.runtime.BatchRuntime;

/**
 * @author julien
 */
public class TacheLancementBatch implements Callable<Long>
{

   /** Le nom du job */
   private final String m_jobName;

   /** Les proprietes */
   private final Properties m_proprietes;

   /**
    * Constructeur
    * @param p_jobName
    * @param p_proprietes
    */
   public TacheLancementBatch(final String p_jobName, final Properties p_proprietes)
   {
      m_jobName = p_jobName;
      m_proprietes = p_proprietes;
   }

   /**
    * {@inheritDoc}
    * @see java.util.concurrent.Callable#call()
    */
   @Override
   public Long call() throws Exception
   {
      return BatchRuntime.getJobOperator().start(m_jobName, m_proprietes);
   }
}
