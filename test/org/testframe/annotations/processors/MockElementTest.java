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

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.MockAnnotation;
import static org.testframe.annotations.processors.MessageRecordTest.RANDOM;

/**
 * Tests of the MockElement class.
 * @author Alonso del Arte
 */
public class MockElementTest {
    
    @Test
    public void testGetAnnotation() {
        System.out.println("getAnnotation");
        int mockID = RANDOM.nextInt();
        Annotation expected = new MockAnnotation() {
            
            @Override
            public int id() {
                return mockID;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
        Annotation[] annotations = {expected};
        Element instance = new MockElement(annotations);
        Annotation actual = instance.getAnnotation(MockAnnotation.class);
        assertEquals(expected, actual);
    }
    
}
