// -----------------------------------------------------------------------------
// Projet : SLD NG
// Client : Pôle Emploi
// Auteur : Bull S.A.S.
// -----------------------------------------------------------------------------
package fr.pe.jvi.batch.extension;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

@ApplicationScoped
public class GestionnaireBeansJobScope
{
   /** La Map d'objets liés au {@link JobContext} */
   private final ConcurrentMap<Long, ConcurrentMap<Contextual<?>, JobScopeInstance<?>>> m_mapBeansJobScope =
      new ConcurrentHashMap<Long, ConcurrentMap<Contextual<?>, JobScopeInstance<?>>>();

   @PostConstruct
   public void init()
   {
      System.out.println("init @Singleton");
   }

   /**
    * {@inheritDoc}
    * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual)
    */
   @SuppressWarnings("unchecked")
   public <T> T recupererBeanParId(final Contextual<T> p_contextual, final Long p_jobExecutionId)
   {
      T retour = null;

      final ConcurrentMap<Contextual<?>, JobScopeInstance<?>> mapJobCourant = m_mapBeansJobScope.get(p_jobExecutionId);

      if (null != mapJobCourant)
      {
         final JobScopeInstance<?> jobInstance = mapJobCourant.get(p_contextual);
         if (null != jobInstance)
         {
            retour = (T) jobInstance.getObjet();
         }
      }
      return retour;
   }

   @SuppressWarnings("unchecked")
   public <T> T creerOuRecupererBeanParId(final Contextual<T> p_contextual, final CreationalContext<T> p_creationalContext, final Long p_jobExecutionId)
   {
      final ConcurrentMap<Contextual<?>, JobScopeInstance<?>> mapJobCourant = creerouRecupererMapCourante(p_jobExecutionId);

      JobScopeInstance<T> instance = (JobScopeInstance<T>) mapJobCourant.get(p_contextual);
      if (null == instance)
      {
         // On est obligé de synchroniser l'ensemble du bloc sinon on aura créé des objets
         // "contextuels" qui ne seront jamais libérés
         synchronized (this)
         {
            instance = (JobScopeInstance<T>) mapJobCourant.get(p_contextual);
            if (null == instance)
            {
               final T objet = p_contextual.create(p_creationalContext);
               instance = new JobScopeInstance<T>(objet, p_creationalContext);
               mapJobCourant.putIfAbsent(p_contextual, instance);
            }
         }

      }
      return instance.getObjet();
   }

   @SuppressWarnings("unchecked")
   public void supprimerBeansParId(final Long p_jobExecutionId)
   {
      final ConcurrentMap<Contextual<?>, JobScopeInstance<?>> mapASupprimer = m_mapBeansJobScope.remove(p_jobExecutionId);
      if (null != mapASupprimer)
      {
         for (final Entry<Contextual<?>, JobScopeInstance<?>> entree : mapASupprimer.entrySet())
         {
            Contextual.class.cast(entree.getKey()).destroy(entree.getValue().getObjet(), entree.getValue().getContexte());
         }
         mapASupprimer.clear();
      }
   }

   /**
    * @param p_jobExecutionId
    * @return
    */
   private ConcurrentMap<Contextual<?>, JobScopeInstance<?>> creerouRecupererMapCourante(final Long p_jobExecutionId)
   {
      ConcurrentMap<Contextual<?>, JobScopeInstance<?>> retour = m_mapBeansJobScope.get(p_jobExecutionId);

      if (null == retour)
      {
         // Gestion classique de la concurrence sur l'ajout du nouvel objet dans la Map.
         // le retour de la méthode putIfAbsent nous permet de savoir si un Thread a déjà ajouté une
         // valeur dans la map entre les 2 executions.
         retour = new ConcurrentHashMap<Contextual<?>, JobScopeInstance<?>>();
         final ConcurrentMap<Contextual<?>, JobScopeInstance<?>> existante = m_mapBeansJobScope.putIfAbsent(p_jobExecutionId, retour);
         if (null != existante)
         {
            retour = existante;
         }
      }

      return retour;
   }
}
