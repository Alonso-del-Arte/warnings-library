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

import javax.annotation.processing.Messager;
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
    public void testConstructorFillsNullElementIfUnspecified() {
        MessageRecord instance = new MessageRecord(Kind.OTHER, DEFAULT_MESSAGE);
        Element element = instance.getElement();
        String message = "Since element was not specified, it should be null";
        assertNull(message, element);
    }
    
}
