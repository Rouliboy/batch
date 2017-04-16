// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.weld.context.RequestContext;

@ApplicationScoped
public class GestionnaireBeansRequestContext
{

   /** */
   private final ConcurrentMap<CleContexteBatch, RequestContext> mapBeansRequestContext = new ConcurrentHashMap<CleContexteBatch, RequestContext>();

   /**
    * @param p_cleContexteBatch
    * @param p_beanRequestContext
    */
   public void ajouterBean(final CleContexteBatch p_cleContexteBatch, final RequestContext p_beanRequestContext)
   {
      final RequestContext ancien = mapBeansRequestContext.put(p_cleContexteBatch, p_beanRequestContext);
      if (null != ancien)
      {
         ancien.invalidate();
         ancien.deactivate();
      }
   }

   /**
    * @param p_cleContexteBatch
    */
   public void supprimerBean(final CleContexteBatch p_cleContexteBatch)
   {
      final RequestContext beanSupprime = mapBeansRequestContext.remove(p_cleContexteBatch);
      if (null != beanSupprime)
      {
         beanSupprime.invalidate();
         beanSupprime.deactivate();
      }
   }

   /**
    * @param p_idJob
    */
   public void supprimerBeanParIdJob(final Long p_idJob)
   {
      if (null != p_idJob)
      {
         final Iterator<Entry<CleContexteBatch, RequestContext>> it = mapBeansRequestContext.entrySet().iterator();
         while (it.hasNext())
         {
            final Entry<CleContexteBatch, RequestContext> entree = it.next();
            if (p_idJob.equals(entree.getKey().getIdJob()))
            {
               it.remove();
            }
         }
      }
   }
}
