// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.rapport;

public class RapportExecutionImpl implements RapportExecution
{

   private String m_nomJob;

   private String m_environnement;

   /**
    * {@inheritDoc}
    * @see fr.pe.jvi.batch.rapport.RapportExecution#getNomJob()
    */
   @Override
   public String getNomJob()
   {
      return m_nomJob;
   }

   /**
    * Attribue le nomJob.
    * @param p_nomJob Le nomJob
    */
   public void setNomJob(final String p_nomJob)
   {
      m_nomJob = p_nomJob;
   }

/**
 * 
 * {@inheritDoc}
 * @see fr.pe.jvi.batch.rapport.RapportExecution#getEnvironnement()
 */
   @Override
   public String getEnvironnement()
   {
      return m_environnement;
   }

   /**
    * Attribue le environnement.
    * @param p_environnement Le environnement
    */
   public void setEnvironnement(final String p_environnement)
   {
      m_environnement = p_environnement;
   }
}
