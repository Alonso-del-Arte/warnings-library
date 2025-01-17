/*
 * Copyright (C) 2025 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.testframe.model;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

/**
 * Provides a mock {@code Element} for use in tests.
 * @author Alonso del Arte
 */
public class MockElement implements Element {
    
    final Annotation[] heldAnnotations;
    
    @org.testframe.annotations.warnings.Untested
    @Override
    public TypeMirror asType() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public ElementKind getKind() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public Set<Modifier> getModifiers() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public Name getSimpleName() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public Element getEnclosingElement() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public List<? extends Element> getEnclosedElements() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    // TODO: Write tests for this
    @org.testframe.annotations.warnings.Untested
    @Override
    public List<? extends AnnotationMirror> getAnnotationMirrors() {
        return new ArrayList<>();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        int index = 0;
        while (index < this.heldAnnotations.length) {
            if (annotationType.equals(this.heldAnnotations[index]
                    .annotationType())) {
                return (A) this.heldAnnotations[index];
            } else {
                index++;
            }
        }
        return null;
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public <R, P> R accept(ElementVisitor<R, P> v, P p) {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @org.testframe.annotations.warnings.Untested
    @Override
    public <A extends Annotation> A[] 
            getAnnotationsByType(Class<A> annotationType) {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }
    
    public static Class<? extends Annotation>[] 
            annotationTypes(Annotation[] annotations) {
        int len = annotations.length;
        Class<? extends Annotation>[] array = new Class[len];
        for (int i = 0; i < len; i++) {
            array[i] = annotations[i].annotationType();
        }
        return array;
    }
            
    @Override
    public String toString() {
        return "Mock element annotated with " 
                + Arrays.toString(annotationTypes(this.heldAnnotations));
    }
    
    /**
     * Represents a mock element with no annotations.
     */
    public MockElement() {
        this(new Annotation[0]);
    }
    
    /**
     * Represents a mock element with a number of annotations.
     * @param annotations The annotations. For example, {@code @Override} and 
     * {@code SuppressWarnings}. May be an empty array.
     */
    public MockElement(Annotation[] annotations) {
        this.heldAnnotations = annotations;
    }
            
}
