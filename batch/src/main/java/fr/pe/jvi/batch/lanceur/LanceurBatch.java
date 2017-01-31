// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.lanceur;

import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("lanceur")
public class LanceurBatch
{
   /** Le gestionnaire de job Batch */
   @Inject
   private GestionnaireJobBatch m_gestionnaireJobBatch;

   @GET
   @Path("demarrer/{job}/{env}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response test(@PathParam(value = "job") final String p_nomJob, @PathParam(value = "env") final String p_env)
   {
      final Properties properties = new Properties();
      properties.put("env", p_env);
      m_gestionnaireJobBatch.lancerJobAvecProprietes(p_nomJob, properties);

      System.out.println("Sortie de la méthode Rest");
      return Response.ok("OK").build();
   }
}
