package fr.pe.jvi.batch;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import fr.pe.jvi.event.EvenementBatch;

@Startup
@Singleton
public class BatchJobStartScheduler
{

	@Resource
	ManagedScheduledExecutorService executor;

	@Inject
	Instance<MyJob> m_fabriqueJobs;
	
	@Inject
	Event<EvenementBatch> p_evenementBatch;

	@PostConstruct
	public void runJob()
	{
		p_evenementBatch.fire(new EvenementBatch("Demarrage", 0));
		executor.scheduleWithFixedDelay(m_fabriqueJobs.get(), 10, 20, TimeUnit.SECONDS);
	}
}