// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lancerBatch")
public class LanceurBatch
{

   /** Le gestionnaire de job Batch */
   @Inject
   private GestionnaireJobBatch m_gestionnaireJobBatch;

   @GET
   @Path("test/{job}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response test(@PathParam(value = "job") final String p_nomJob)
   {
      System.out.println("Lancement Batchlet lanceurBatch");
      m_gestionnaireJobBatch.lancerJob(p_nomJob);
      return Response.ok("OK").build();
   }
}
