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
import java.util.Objects;
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
    
    private final CharSequence message;
    
    private final Element element;
    
    private final AnnotationMirror annMirror;
    
    private final AnnotationValue annVal;
    
    /**
     * Retrieves the diagnostic kind this message record was constructed as.
     * @return The diagnostic kind passed to the constructor. One of {@code 
     * Kind.ERROR}, {@code Kind.MANDATORY_WARNING}, {@code Kind.NOTE} or {@code 
     * Kind.OTHER}.
     */
    public Kind getDiagnosticKind() {
        return this.diagKind;
    }
    
    /**
     * Retrieves the message this message record was constructed as.
     * @return The message that was passed to the constructor.
     */
    public CharSequence getMessage() {
        return this.message;
    }
    
    /**
     * Retrieves the element this message record was constructed with.
     * @return The element that was passed to the constructor. Might be null.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Retrieves the annotation mirror this message record was constructed with.
     * @return The annotation mirror that was passed to the constructor. Might 
     * be null.
     */
    public AnnotationMirror getMirror() {
        return this.annMirror;
    }
    
    /**
     * Retrieves the annotation value this message record was constructed with.
     * @return The annotation value that was passed to the constructor. Might be 
     * null.
     */
    public AnnotationValue getValue() {
        return this.annVal;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null;
    }

    @Override
    public int hashCode() {
        return Integer.MIN_VALUE;
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
        if (kind == null) {
            String excMsg = "Diagnostic kind should not be null";
            throw new NullPointerException(excMsg);
        }
        this.diagKind = kind;
        this.message = msg;
        this.element = elem;
        this.annMirror = mirror;
        this.annVal = value;
    }
    
}
