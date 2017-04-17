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
   private final ConcurrentMap<CleRequestContext, RequestContext> mapBeansRequestContext = new ConcurrentHashMap<CleRequestContext, RequestContext>();

   /**
    * @param p_cleContexteBatch
    * @param p_beanRequestContext
    */
   public void ajouterBean(final CleRequestContext p_cleContexteBatch, final RequestContext p_beanRequestContext)
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
   public void supprimerBean(final CleRequestContext p_cleContexteBatch)
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
         final Iterator<Entry<CleRequestContext, RequestContext>> it = mapBeansRequestContext.entrySet().iterator();
         while (it.hasNext())
         {
            final Entry<CleRequestContext, RequestContext> entree = it.next();
            if (p_idJob.equals(entree.getKey().getIdJob()))
            {
               it.remove();
            }
         }
      }
   }
}
