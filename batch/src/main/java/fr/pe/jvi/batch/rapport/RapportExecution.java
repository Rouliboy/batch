// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.rapport;

public interface RapportExecution
{
   /**
    * Renvoie nomJob.
    * @return Le nomJob.
    */
   String getNomJob();

   /**
    * Renvoie environnement.
    * @return Le environnement.
    */
   String getEnvironnement();

}
