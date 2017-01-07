package fr.pe.jvi.batch.intercepteur;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.Unbound;

@Interceptor
@IntercepteurContexte
@Priority(value = 1000)
public class MonIntercepteurContexte
{

   @Inject
   @Unbound
   private RequestContext m_contexteRequest;

   /**
    * @param ctx
    * @return
    * @throws Exception
    */
   @AroundInvoke
   public Object intercepter(final InvocationContext ctx) throws Exception
   {
      try
      {
         System.out.println("Interception de la méthode :" + ctx.getMethod().getName());
         m_contexteRequest.activate();
         return ctx.proceed();
      }
      finally
      {
         m_contexteRequest.deactivate();
      }
   }

}
