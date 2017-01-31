// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.beans;

import javax.annotation.PreDestroy;

import fr.pe.jvi.batch.annotation.JobScoped;

@JobScoped
public class ContexteSollicitationBatchSLDImpl implements ContexteSollicitationBatchSLD
{

   private String m_valeur;

   private String m_environnement;

   /**
    * Renvoie valeur.
    * @return Le valeur.
    */
   @Override
   public String getValeur()
   {
      return m_valeur;
   }

   /**
    * Attribue le valeur.
    * @param p_valeur Le valeur
    */
   public void setValeur(final String p_valeur)
   {
      m_valeur = p_valeur;
   }

   /**
    * {@inheritDoc}
    * @see fr.pe.jvi.beans.ContexteSollicitationBatchSLD#getEnvironnement()
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

   /**
    * {@inheritDoc}
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "ContexteSollicitationBatchSLDImpl [m_valeur=" + m_valeur + ", m_environnement=" + m_environnement + "]";
   }

   @PreDestroy
   protected void destruction()
   {
      System.out.println("Destruction du bean ContexteSollicitationBatchSLD");
   }

}
