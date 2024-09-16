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

import java.time.LocalDateTime;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the MessageRecord class.
 * @author Alonso del Arte
 */
public class MessageRecordTest {
    
    private static final String DEFAULT_MESSAGE = "FOR TESTING PURPOSES ONLY";
    
    @Test
    public void testGetDiagnosticKind() {
        System.out.println("getDiagnosticKind");
        Kind[] kinds = Kind.values();
        for (Kind expected : kinds) {
            MessageRecord instance = new MessageRecord(expected, 
                    DEFAULT_MESSAGE);
            Kind actual = instance.getDiagnosticKind();
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        String expected = "For testing purposes as of " 
                + LocalDateTime.now().toString();
        MessageRecord instance = new MessageRecord(Kind.OTHER, expected);
        CharSequence actual = instance.getMessage();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        Element expected = new MockElement();
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                expected);
        Element actual = instance.getElement();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetMirror() {
        System.out.println("getMirror");
        Element elem = new MockElement();
        AnnotationMirror expected = new MockMirror();
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                elem, expected);
        AnnotationMirror actual = instance.getMirror();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Element elem = new MockElement();
        AnnotationMirror mirror = new MockMirror();
        AnnotationValue expected = new MockValue();
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                elem, mirror, expected);
        AnnotationValue actual = instance.getValue();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        assertEquals(instance, instance);
    }
    
    @Test
    public void testConstructorFillsNullElementIfUnspecified() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        Element element = instance.getElement();
        String message = "Since element was not specified, it should be null";
        assertNull(message, element);
    }
    
    @Test
    public void testConstructorFillsNullMirrorIfUnspecified() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                new MockElement());
        AnnotationMirror mirror = instance.getMirror();
        String message = "Since mirror was not specified, it should be null";
        assertNull(message, mirror);
    }
    
    @Test
    public void testConstructorFillsNullValueIfUnspecified() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                new MockElement(), new MockMirror());
        AnnotationValue value = instance.getValue();
        String message = "Since value was not specified, it should be null";
        assertNull(message, value);
    }
    
}
