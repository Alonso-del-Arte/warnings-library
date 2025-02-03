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
import java.util.List;

import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

/**
 * Represents a mock type element. This is to be used in the testing of the 
 * {@code process()} function in {@link WarningsProcessor}.
 * @author Alonso del Arte
 */
public class MockTypeElement extends MockElement implements TypeElement {
    
    private final String name;

    @Override
    public NestingKind getNestingKind() {
        throw new UnsupportedOperationException("FOR TESTING PURPOSES ONLY");
    }

    @Override
    public Name getQualifiedName() {
        return new MockName(this.name);
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
    
    /**
     * Simply returns the fully qualified name of the class this instance was 
     * initialized with.
     * @return The fully qualified name of the class passed to the constructor. 
     * For example, "{@code java.util.ArrayList}".
     */
    @Override
    public String toString() {
        return this.name;
    }
    
    public MockTypeElement(Class<?> type) {
        this.name = type.getName();
    }
    
    public MockTypeElement(Class<?> type, Annotation[] annotations) {
        super(annotations);
        this.name = type.getName();
    }
    
}
