package fr.pe.jvi.batch.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import fr.pe.jvi.batch.annotation.BatchletSLD;


public class BatchExtension implements Extension {

	public <T> void processAnnotatedType(
			@Observes ProcessAnnotatedType<T> processAnnotatedType) {

		AnnotatedType<T> annotatedType = processAnnotatedType
				.getAnnotatedType();

		if (annotatedType.isAnnotationPresent(BatchletSLD.class))
		{
			System.out.println("Annotation BatchletSLD presente");
			AnnotatedTypeWrapper<T> wrapper = new AnnotatedTypeWrapper<T>(
					annotatedType);
			processAnnotatedType.setAnnotatedType(wrapper);
		}




		//		if (annotatedType.getJavaClass().equals(TestBean.class)) {
		//
		//			Annotation auditAnnotation = new Annotation() {
		//				@Override
		//				public Class<? extends Annotation> annotationType() {
		//					return Audit.class;
		//				}
		//			};
		//
		//			AnnotatedTypeWrapper<T> wrapper = new AnnotatedTypeWrapper<T>(
		//					annotatedType, annotatedType.getAnnotations());
		//			wrapper.addAnnotation(auditAnnotation);
		//
		//			processAnnotatedType.setAnnotatedType(wrapper);
		//		}

	}

}