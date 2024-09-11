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
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic.Kind;

/**
 *
 * @author Alonso del Arte
 */
public class MessageRecord {
    
    private final Kind diagKind;
    
    /**
     * Retrieves the diagnostic kind this message record was constructed as.
     * @return The diagnostic kind passed to the constructor. One of {@code 
     * Kind.ERROR}, {@code Kind.MANDATORY_WARNING}, {@code Kind.NOTE} or {@code 
     * Kind.OTHER}.
     */
    public Kind getDiagnosticKind() {
        return this.diagKind;
    }
    
    // TODO: Write tests for this
    public CharSequence getMessage() {
        return "SORRY, NOT IMPLEMENTED YET";
    }
    
    // TODO: Write tests for this
    public Element getElement() {
        return new Element() {
            
            @Override
            public TypeMirror asType() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public ElementKind getKind() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Set<Modifier> getModifiers() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Name getSimpleName() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Element getEnclosingElement() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public List<? extends Element> getEnclosedElements() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public List<? extends AnnotationMirror> getAnnotationMirrors() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public <R, P> R accept(ElementVisitor<R, P> v, P p) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public <A extends Annotation> A[] 
                    getAnnotationsByType(Class<A> annotationType) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
                    
        };
    }
    
    // TODO: Write tests for this
    public AnnotationMirror getMirror() {
        return new AnnotationMirror() {
            
            @Override
            public DeclaredType getAnnotationType() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Map<? extends ExecutableElement, 
                    ? extends AnnotationValue> getElementValues() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
                    
        };
    }
    
    // TODO: Write tests for this
    public AnnotationValue getValue() {
        return new AnnotationValue() {
            
            @Override
            public Object getValue() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public <R, P> R accept(AnnotationValueVisitor<R, P> v, P p) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            
        };
    }
    
    public MessageRecord(Kind kind, CharSequence msg) {
        this(kind, msg, null);
    }
    
    public MessageRecord(Kind kind, CharSequence msg, Element elem) {
        this(kind, msg, elem, null);
    }
    
    public MessageRecord(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror) {
        this(kind, msg, elem, mirror, null);
    }
    
    public MessageRecord(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror, AnnotationValue value) {
        this.diagKind = kind;
    }
    
}
