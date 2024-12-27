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
package org.testframe.model;

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
 * This class is necessary as the return type of {@link 
 * MockElement#getSimpleName()} and {@link MockTypeElement#getQualifiedName()}.
 * @author Alonso del Arte
 */
public class MockName implements Name {
    
    private final String charSeq;
    
    @Override
    public int length() {
        return this.charSeq.length();
    }

    @Override
    public char charAt(int index) {
        return this.charSeq.charAt(index);
    }

    @Override
    public String subSequence(int start, int end) {
        return this.charSeq.substring(start, end);
    }
    
    // TODO: Write tests for this
    @Override
    public boolean contentEquals(CharSequence cs) {
        return false;
    }
    
    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return true;
    }
    
    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public String toString() {
        return this.charSeq;
    }
    
    public MockName(Class<?> type) {
        this.charSeq = type.getName();
    }

    public MockName(String s) {
        this.charSeq = s;
    }

}
