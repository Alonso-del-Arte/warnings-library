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

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

/**
 * Represents a message sent to a {@code javax.annotation.processing.Messager} 
 * instance. For all or almost all testing in this project, that instance will 
 * be a {@link MockMessager}.
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
    
    // TODO: Refactor to fewer lines
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        MessageRecord other = (MessageRecord) obj;
        if (this.diagKind != other.diagKind) {
            return false;
        }
        if (!this.message.equals(other.message)) {
            return false;
        }
        if (this.element == null) {
            if (other.element != null) {
                return false;
            }
        } else {
            if (!this.element.equals(other.element)) {
                return false;
            }
        }
        if (this.annMirror == null) {
            if (other.annMirror != null) {
                return false;
            }
        } else {
            if (!this.annMirror.equals(other.annMirror)) {
                return false;
            }
        }
        if (this.annVal == null) {
            return other.annVal == null;
        }
        return this.annVal.equals(other.annVal);
    }
    
    @Override
    public int hashCode() {
        int hash = this.diagKind.hashCode() << 3;
        hash += this.message.hashCode();
        if (this.element != null) {
            hash <<= 13;
            hash += this.element.hashCode();
        }
        if (this.annMirror != null) {
            hash <<= 8;
            hash += this.annMirror.hashCode();
        }
        if (this.annVal != null) {
            hash <<= 8;
            hash += this.annVal.hashCode();
        }
        return hash;
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
        if (kind == null || msg == null) {
            String excMsg = "Diagnostic kind, message should not be null";
            throw new NullPointerException(excMsg);
        }
        this.diagKind = kind;
        this.message = msg;
        this.element = elem;
        this.annMirror = mirror;
        this.annVal = value;
    }
    
}
