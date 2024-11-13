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
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

/**
 *
 * @author Alonso del Arte
 */
//TODO: Determine if this class is necessary for testing
public class MockTypeElement extends MockElement implements TypeElement {

    @Override
    public NestingKind getNestingKind() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }

    @Override
    public Name getQualifiedName() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }

    @Override
    public TypeMirror getSuperclass() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }

    @Override
    public List<? extends TypeMirror> getInterfaces() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }

    @Override
    public List<? extends TypeParameterElement> getTypeParameters() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }
    
}
