package fr.pe.jvi.intercepteur;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.Unbound;

@Interceptor
@IntercepteurContexte
//@Priority(value = 100)
public class MonIntercepteurContexte {

	@Inject
	@Unbound
	private RequestContext m_contexteRequest;

	@AroundInvoke
	public Object intercepter(InvocationContext ctx) throws Exception
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
