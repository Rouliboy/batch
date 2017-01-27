// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.extension;

import javax.enterprise.context.spi.CreationalContext;

/**
 * @author julien
 * @param <T>
 */
public class JobScopeInstance<T>
{
   /** L'objet créé */
   private final T m_objet;

   /** Le contexte associé à l'objet */
   private final CreationalContext<T> m_contexte;

   public JobScopeInstance(final T p_objet, final CreationalContext<T> p_contexte)
   {
      m_objet = p_objet;
      m_contexte = p_contexte;
   }

   /**
    * Renvoie objet.
    * @return Le objet.
    */
   public T getObjet()
   {
      return m_objet;
   }

   /**
    * Renvoie le contexte.
    * @return Le contexte.
    */
   public CreationalContext<T> getContexte()
   {
      return m_contexte;
   }
}