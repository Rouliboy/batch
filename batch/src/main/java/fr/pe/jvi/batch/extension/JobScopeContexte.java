package fr.pe.jvi.batch.extension;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pe.jvi.batch.annotation.JobScoped;

/**
 * @author julien
 */
public class JobScopeContexte implements Context
{

   /** Le logger */
   private static final Logger LOGGER = LoggerFactory.getLogger(JobScopeContexte.class);

   /** Le BeanManager */
   private final BeanManager m_beanManager;

   /**
    * Constructeur
    * @param p_beanManager Le {@link BeanManager}
    */
   public JobScopeContexte(final BeanManager p_beanManager)
   {
      LOGGER.info("Init");
      m_beanManager = p_beanManager;
   }

   /**
    * {@inheritDoc}
    * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual)
    */
   @Override
   public <T> T get(final Contextual<T> p_contextual)
   {
      final Long jobExecutionId = recupererJobExecutionId();

      final GestionnaireBeansJobScope gestionnaireBeansJobScope = recupererGestionnaireBeansJobScope();
      return gestionnaireBeansJobScope.recupererBeanParId(p_contextual, jobExecutionId);
   }

   /**
    * {@inheritDoc}
    * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual,
    *      javax.enterprise.context.spi.CreationalContext)
    */
   @Override
   public <T> T get(final Contextual<T> p_contextual, final CreationalContext<T> p_creationalContext)
   {
      final Long jobExecutionId = recupererJobExecutionId();

      final GestionnaireBeansJobScope gestionnaireBeansJobScope = recupererGestionnaireBeansJobScope();
      return gestionnaireBeansJobScope.creerOuRecupererBeanParId(p_contextual, p_creationalContext, jobExecutionId);
   }

   /**
    * {@inheritDoc}
    * @see javax.enterprise.context.spi.Context#getScope()
    */
   @Override
   public Class<? extends Annotation> getScope()
   {
      return JobScoped.class;
   }

   /**
    * {@inheritDoc}
    * @see javax.enterprise.context.spi.Context#isActive()
    */
   @Override
   public boolean isActive()
   {
      return true;
   }

   /**
    * @return
    */
   @SuppressWarnings("unchecked")
   private Long recupererJobExecutionId()
   {
      final Set<Bean<?>> beans = m_beanManager.getBeans(JobContext.class);
      final Bean<JobContext> bean = (Bean<JobContext>) m_beanManager.resolve(beans);
      final CreationalContext<JobContext> contexte = m_beanManager.createCreationalContext(null);
      final JobContext jobContexte = (JobContext) m_beanManager.getReference(bean, JobContext.class, contexte);

      final Long jobExecutionId;
      if (null == jobContexte)
      {
         // contexte.release();
         throw new ContextNotActiveException("Le contexte CDI annoté @" + getScope().getName() + " n'est pas actif");
      }
      else
      {
         // On récupère l'id du job et on release le bean JobContext créé.
         jobExecutionId = jobContexte.getExecutionId();
         bean.destroy(jobContexte, contexte);
      }

      return jobExecutionId;
   }

   /**
    * @return
    */
   private GestionnaireBeansJobScope recupererGestionnaireBeansJobScope()
   {
      final Set<Bean<?>> beans = m_beanManager.getBeans(GestionnaireBeansJobScope.class);
      final Bean<?> bean = m_beanManager.resolve(beans);
      return (GestionnaireBeansJobScope) m_beanManager.getReference(bean, GestionnaireBeansJobScope.class, m_beanManager.createCreationalContext(null));
   }
}