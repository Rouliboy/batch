// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import java.util.Objects;

public class CleContexteBatch
{
   /** La classe demandeur */
   private final String m_nomClasseDemandeur;

   /** L'identifiant du thread courant */
   private final String m_IdThread;

   /** L'id du job */
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
      result = prime * result + Objects.hashCode(m_nomClasseDemandeur);
      result = prime * result + Objects.hashCode(m_IdThread);
      result = prime * result + Objects.hashCode(m_idJob);
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

         retour = retour && Objects.equals(m_nomClasseDemandeur, other.m_nomClasseDemandeur);
         retour = retour && Objects.equals(m_IdThread, other.m_IdThread);
         retour = retour && Objects.equals(m_idJob, other.m_idJob);
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
