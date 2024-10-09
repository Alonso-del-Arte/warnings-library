/*
 * Copyright (C) 2024 Alonso del Arte
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
package org.testframe.annotations.processors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

import org.testframe.annotations.MockAnnotation;

/**
 * Provides a mock {@code Element} for use in tests.
 * @author Alonso del Arte
 */
public class MockElement implements Element {
    
    @Override
    public TypeMirror asType() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public ElementKind getKind() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public Set<Modifier> getModifiers() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public Name getSimpleName() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public Element getEnclosingElement() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public List<? extends Element> getEnclosedElements() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    // TODO: Write tests for this
    @Override
    public List<? extends AnnotationMirror> getAnnotationMirrors() {
        return new ArrayList<>();
    }

    // TODO: Write tests for this
    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return null;
    }

    @Override
    public <R, P> R accept(ElementVisitor<R, P> v, P p) {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }

    @Override
    public <A extends Annotation> A[] 
            getAnnotationsByType(Class<A> annotationType) {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES");
    }
    
    public MockElement() {
        this(null);
    }
            
    public MockElement(Annotation annotation) {
    }
            
}
