package fr.pe.jvi.event;

import javax.enterprise.event.Observes;

public class ObservateurEvenementBatch
{
	public void observateurBatch(@Observes EvenementBatch p_evenementBatch)
	{
		System.out.println("Observation d'un evenement Batch : " + p_evenementBatch);
	}
}
