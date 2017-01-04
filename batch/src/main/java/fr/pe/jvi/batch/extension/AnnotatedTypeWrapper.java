package fr.pe.jvi.batch.extension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;

import fr.pe.jvi.intercepteur.IntercepteurContexte;

public class AnnotatedTypeWrapper<T> implements AnnotatedType<T>
{
	private final AnnotatedType<T> wrapped;

	public AnnotatedTypeWrapper(AnnotatedType<T> wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		return wrapped.getAnnotation(annotationType);
	}

	@Override
	public Set<Annotation> getAnnotations() {
		return wrapped.getAnnotations();
	}

	@Override
	public Type getBaseType() {
		return wrapped.getBaseType();
	}

	@Override
	public Set<Type> getTypeClosure() {
		return wrapped.getTypeClosure();
	}

	@Override
	public boolean isAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		return wrapped.isAnnotationPresent(annotationType);
	}

	@Override
	public Set<AnnotatedConstructor<T>> getConstructors() {
		return wrapped.getConstructors();
	}

	@Override
	public Set<AnnotatedField<? super T>> getFields() {
		return wrapped.getFields();
	}

	@Override
	public Class<T> getJavaClass() {
		return wrapped.getJavaClass();
	}

	@Override
	public Set<AnnotatedMethod<? super T>> getMethods() {
		Set<AnnotatedMethod<? super T>> methodes = new HashSet<AnnotatedMethod<? super T>>();
		for (AnnotatedMethod<? super T> methode : wrapped.getMethods())
		{
			if (false == methode.isAnnotationPresent(IntercepteurContexte.class))
			{
				Annotation annotation = new Annotation() {
					@Override
					public Class<? extends Annotation> annotationType() {
						return IntercepteurContexte.class;
					}
				};
				//				AnnotatedMethodWrapper<T> wrapper = new AnnotatedMethodWrapper<T>(
				//						methode, annotation);
				System.out.println("Ajout de l'annotation sur la méthode : " + methode.getJavaMember().getName());
				methode.getAnnotations().add(annotation);
			}
			else
			{

			}
			methodes.add(methode);
		}
		System.out.println("Nombre de méthodes : "+ methodes.size());
		return methodes;
	}

}