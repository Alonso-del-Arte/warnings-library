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
import java.util.Random;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.api.Asserters.assertDoesNotThrow;
import static org.testframe.api.Asserters.assertThrows;

/**
 * Tests of the MessageRecord class.
 * @author Alonso del Arte
 */
public class MessageRecordTest {
    
    private static final Kind[] DIAGNOSTIC_KINDS = Kind.values();
    
    private static final int NUMBER_OF_KINDS = DIAGNOSTIC_KINDS.length;
    
    private static final String DEFAULT_MESSAGE = "FOR TESTING PURPOSES ONLY";
    
    private static final Random RANDOM = new Random();
    
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
    
    private static Object provideNull() {
        return null;
    }
    
    @Test
    public void testNotEqualsNull() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        Object obj = provideNull();
        String msg = instance.toString() + " should not equal null";
        assert !instance.equals(obj) : msg;
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        Object[] objects = {Kind.NOTE, DEFAULT_MESSAGE, new MockElement(), 
            new MockMirror(), new MockValue()};
        String msgPart = instance.toString() + " should not equal ";
        for (Object obj : objects) {
            String msg = msgPart + obj.toString();
            assertNotEquals(msg, instance, obj);
        }
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        Element elem = new MockElement();
        AnnotationMirror mirror = new MockMirror();
        AnnotationValue value = new MockValue();
        MessageRecord someRecord = new MessageRecord(Kind.NOTE, DEFAULT_MESSAGE, 
                elem, mirror, value);
        MessageRecord sameRecord = new MessageRecord(Kind.NOTE, DEFAULT_MESSAGE, 
                elem, mirror, value);
        assertEquals(someRecord, sameRecord);
    }
    
    private static Kind chooseKindOtherThan(Kind kind) {
        Kind diffKind = kind;
        do {
            diffKind = DIAGNOSTIC_KINDS[RANDOM.nextInt(NUMBER_OF_KINDS)];
        } while (diffKind == kind);
        return diffKind;
    }
    
    @Test
    public void testNotEqualsMessageRecordWithDiffKind() {
        for (Kind kind : DIAGNOSTIC_KINDS) {
            MessageRecord recordA = new MessageRecord(kind, DEFAULT_MESSAGE);
            Kind diffKind = chooseKindOtherThan(kind);
            MessageRecord recordB = new MessageRecord(diffKind, 
                    DEFAULT_MESSAGE);
            String message = "Record of diagnostic kind " + kind.toString() 
                    + " should not equal record of diagnostic kind " 
                    + diffKind.toString();
            assertNotEquals(message, recordA, recordB);
        }
    }
    
    @Test
    public void testNotEqualsMessageRecordWithDifferentMessage() {
        MessageRecord recordA = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        MessageRecord recordB = new MessageRecord(Kind.OTHER, 
                LocalDateTime.now().toString());
        String message = "Record with message \"" + recordA.getMessage() 
                + "\" should not equal record with message \"" 
                + recordB.getMessage() + "\"";
        assertNotEquals(message, recordA, recordB);
    }
    
    @Test
    public void testNotEqualsMessageRecordWithDifferentElement() {
        Element elemA = new MockElement();
        Element elemB = new MockElement();
        MessageRecord recordA = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                elemA);
        MessageRecord recordB = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                elemB);
        String message = "Record with element " + elemA.toString() 
                + " should not equal record with element " + elemB.toString();
        assertNotEquals(message, recordA, recordB);
    }
    
    @Test
    public void testEqualsWithNullElementDoesNotCauseNPE() {
        MessageRecord recordA = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                null);
        MessageRecord recordB = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE, 
                new MockElement());
        String msg = "Record A having null element should not cause NPE";
        assertDoesNotThrow(() -> {
            assert !recordA.equals(recordB);
        }, msg);
    }
    
    @Test
    public void testConstructorRejectsNullDiagnosticKind() {
        String msg = "Null diagnostic kind should cause NPE";
        Throwable t = assertThrows(() -> {
            MessageRecord badRecord = new MessageRecord(null, DEFAULT_MESSAGE);
            System.out.println(msg + ", not given instance " 
                    + badRecord.toString());
        }, NullPointerException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testConstructorRejectsNullMessage() {
        String msg = "Null message should cause NPE";
        Throwable t = assertThrows(() -> {
            MessageRecord badRecord = new MessageRecord(Kind.OTHER, null);
            System.out.println(msg + ", not given instance " 
                    + badRecord.toString());
        }, NullPointerException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
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
