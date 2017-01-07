package fr.pe.jvi.batch.batchlet;

import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import fr.pe.jvi.batch.annotation.BatchletSLD;
import fr.pe.jvi.beans.MonContexteRequestScoped;
import fr.pe.jvi.event.EvenementBatch;

/**
 * Batchlet de test
 * @author julien
 */
@Named("HelloWorldBatchlet")
@Dependent
@BatchletSLD
public class HelloWorldBatchlet implements javax.batch.api.Batchlet
{

   @Inject
   JobContext jobContext;

   @Inject
   StepContext stepContext;

   @Inject
   MonContexteRequestScoped contexte;

   @Inject
   Event<EvenementBatch> p_evenementBatch;

   /**
    * {@inheritDoc}
    * @see javax.batch.api.Batchlet#process()
    */
   @Override
   public String process() throws Exception
   {
      // here I could copy a file as a precursor to processing it
      final long executionId = jobContext.getExecutionId();
      System.out.println("executionId=" + executionId);
      System.out.println("contexte @RequestScoped=" + contexte.getLabel());

      p_evenementBatch.fire(new EvenementBatch(jobContext.getJobName(), jobContext.getExecutionId()));
      return "SUCCESS";
   }

   /**
    * {@inheritDoc}
    * @see javax.batch.api.Batchlet#stop()
    */
   @Override
   public void stop() throws Exception
   {
   }

}