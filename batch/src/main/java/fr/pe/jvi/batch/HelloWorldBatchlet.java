package fr.pe.jvi.batch;

import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import fr.pe.jvi.batch.annotation.BatchletSLD;
import fr.pe.jvi.beans.MonContexte;
import fr.pe.jvi.event.EvenementBatch;
/**
 * Batchlet de test
 * @author julien
 *
 */
@Named("HelloWorldBatchlet")
@Dependent
@BatchletSLD
public class HelloWorldBatchlet implements javax.batch.api.Batchlet {

	@Inject
	JobContext jobContext;

	@Inject
	StepContext stepContext;

	@Inject
	MonContexte contexte;

	@Inject
	Event<EvenementBatch> p_evenementBatch;

	@Override
	public String process() throws Exception {

		// here I could copy a file as a precursor to processing it
		long executionId = jobContext.getExecutionId();
		System.out.println("executionId=" + executionId);
		System.out.println("contexte @RequestScoped=" + contexte.getAnnotationsContexte());

		p_evenementBatch.fire(new EvenementBatch(jobContext.getJobName(), jobContext.getExecutionId()));
		return "SUCCESS";
	}

	@Override
	public void stop() throws Exception {
	}

}