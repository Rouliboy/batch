// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.beans;

import javax.annotation.PreDestroy;

import fr.pe.jvi.batch.annotation.JobScoped;

@JobScoped
public class ContexteSollicitationBatchSLD
{

   private String m_valeur;

   /**
    * Renvoie valeur.
    * @return Le valeur.
    */
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

   @PreDestroy
   protected void destruction()
   {
      System.out.println("Destruction du bean ContexteSollicitationBatchSLD");
   }
}
