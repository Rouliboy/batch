// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.rapport;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MagasinDeRapportRAM
{
   /** La map des rapports */
   final ConcurrentMap<Long, RapportExecution> rapports;

   public MagasinDeRapportRAM()
   {
      rapports = new ConcurrentHashMap<Long, RapportExecution>();
   }
}
