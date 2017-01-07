// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("lancerBatch")
public class LanceurBatch
{
   @Path("test")
   @GET
   public String test()
   {
      return "OK";
   }
}
