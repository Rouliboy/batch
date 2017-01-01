package fr.pe.jvi.beans;

import java.lang.annotation.Annotation;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MonContexte {

	public String getAnnotationsContexte()
	{
		String annotations = "";
		if (this.getClass().getAnnotations() != null && this.getClass().getAnnotations().length > 0)
		{
			for(Annotation annotation : this.getClass().getAnnotations())
			{
				annotations += annotation.getClass().getSimpleName();
			}
		}
		return MonContexte.class.getName() + "|" + annotations ;
	}
}
