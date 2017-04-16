// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

public class CleContexteBatch
{
   private final String m_nomClasseDemandeur;

   private final String m_IdThread;

   private final Long m_idJob;

   /**
    * Constructeur
    * @param p_classeDemandeur
    * @param p_jobId
    */
   public CleContexteBatch(final Class<?> p_classeDemandeur, final Long p_jobId)
   {
      m_nomClasseDemandeur = p_classeDemandeur.getCanonicalName();
      m_IdThread = Thread.currentThread().getName() + "_" + Thread.currentThread().getId();
      m_idJob = p_jobId;
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((m_nomClasseDemandeur == null) ? 0 : m_nomClasseDemandeur.hashCode());
      result = prime * result + ((m_idJob == null) ? 0 : m_idJob.hashCode());
      result = prime * result + ((m_IdThread == null) ? 0 : m_IdThread.hashCode());
      return result;
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object p_obj)
   {
      boolean retour = true;

      if (p_obj instanceof CleContexteBatch)
      {
         final CleContexteBatch other = (CleContexteBatch) p_obj;

         if (m_nomClasseDemandeur == null)
         {
            if (other.m_nomClasseDemandeur != null)
            {
               retour = false;
            }
         }
         else if (false == m_nomClasseDemandeur.equals(other.m_nomClasseDemandeur))
         {
            retour = false;
         }

         if (m_idJob == null)
         {
            if (other.m_idJob != null)
            {
               retour = false;
            }
         }
         else if (false == m_idJob.equals(other.m_idJob))
         {
            retour = false;
         }

         if (m_IdThread == null)
         {
            if (other.m_IdThread != null)
            {
               retour = false;
            }
         }
         else if (false == m_IdThread.equals(other.m_IdThread))
         {
            retour = false;
         }
      }
      else
      {
         retour = false;
      }
      return retour;
   }

   /**
    * Renvoie idJob.
    * @return Le idJob.
    */
   public Long getIdJob()
   {
      return m_idJob;
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "CleContexte [m_class=" + m_nomClasseDemandeur + ", m_threadId=" + m_IdThread + ", m_jobId=" + m_idJob + "]";
   }

}
