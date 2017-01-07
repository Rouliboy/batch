package fr.pe.jvi.batch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

import fr.pe.jvi.batch.intercepteur.IntercepteurContexte;

@Inherited
@InterceptorBinding
@IntercepteurContexte
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BatchletSLD
{
}
