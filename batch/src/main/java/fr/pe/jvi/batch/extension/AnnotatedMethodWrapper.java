package fr.pe.jvi.batch.extension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;

public class AnnotatedMethodWrapper<T> implements AnnotatedMethod<T>
{
	private final AnnotatedMethod<T> wrapped;

	private final Set<Annotation> annotations;

	public AnnotatedMethodWrapper(AnnotatedMethod<T> wrapped,
			Annotation annotation) {
		this.wrapped = wrapped;
		this.annotations = new HashSet<Annotation>(wrapped.getAnnotations());
		this.annotations.add(annotation);
	}

	@Override
	public List<AnnotatedParameter<T>> getParameters() {
		// TODO Auto-generated method stub
		return wrapped.getParameters();
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return wrapped.isStatic();
	}

	@Override
	public AnnotatedType<T> getDeclaringType() {
		// TODO Auto-generated method stub
		return wrapped.getDeclaringType();
	}

	@Override
	public Type getBaseType() {
		// TODO Auto-generated method stub
		return wrapped.getBaseType();
	}

	@Override
	public Set<Type> getTypeClosure() {
		// TODO Auto-generated method stub
		return wrapped.getTypeClosure();
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return wrapped.getAnnotation(annotationType);
	}

	@Override
	public Set<Annotation> getAnnotations() {
		return annotations;
	}

	@Override
	public boolean isAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		for (Annotation annotation : annotations) {
			if (annotationType.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Method getJavaMember() {
		// TODO Auto-generated method stub
		return wrapped.getJavaMember();
	}

}