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
package org.testframe.annotations.processors;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.annotations.processors.MessageRecordTest.RANDOM;
import org.testframe.model.MockElement;
import org.testframe.model.MockMirror;
import org.testframe.model.MockValue;

/**
 * Tests of the MockMessager class.
 * @author Alonso del Arte
 */
public class MockMessagerTest {
    
    private static final Kind[] DIAGNOSTIC_KINDS = Kind.values();
    
    private static final int NUMBER_OF_KINDS = DIAGNOSTIC_KINDS.length;    
    
    private static Kind chooseKind() {
        return DIAGNOSTIC_KINDS[RANDOM.nextInt(NUMBER_OF_KINDS)];
    }
    
    private static String makeMessageText() {
        return "FOR TESTING PURPOSES ONLY " + RANDOM.nextInt();
    }
    
    @Test
    public void testPrintMessage() {
        System.out.println("printMessage");
        Kind kind = chooseKind();
        String msg = makeMessageText();
        MessageRecord expected = new MessageRecord(kind, msg);
        MockMessager messager = new MockMessager();
        messager.printMessage(kind, msg);
        MessageRecord actual = messager.getLatestMessage();
        assert actual != null : "Latest message should not be null";
        String message = "Printed message \"" + msg + "\" of kind " 
                + kind.toString();
        assertEquals(message, expected, actual);
    }
    
    @Test
    public void testPrintMessageForElement() {
        Kind kind = chooseKind();
        String msg = makeMessageText();
        Element elem = new MockElement();
        MessageRecord expected = new MessageRecord(kind, msg, elem);
        MockMessager messager = new MockMessager();
        messager.printMessage(kind, msg, elem);
        MessageRecord actual = messager.getLatestMessage();
        assert actual != null : "Latest message should not be null";
        String message = "Printed message \"" + msg + "\" of kind " 
                + kind.toString() + " for element " + elem.toString();
        assertEquals(message, expected, actual);
    }
    
    @Test
    public void testPrintMessageForElementWithMirror() {
        Kind kind = chooseKind();
        String msg = makeMessageText();
        Element elem = new MockElement();
        AnnotationMirror mirror = new MockMirror();
        MessageRecord expected = new MessageRecord(kind, msg, elem, mirror);
        MockMessager messager = new MockMessager();
        messager.printMessage(kind, msg, elem, mirror);
        MessageRecord actual = messager.getLatestMessage();
        assert actual != null : "Latest message should not be null";
        String message = "Printed message \"" + msg + "\" of kind " 
                + kind.toString() + " for element " + elem.toString() 
                + " with mirror " + mirror.toString();
        assertEquals(message, expected, actual);
    }
    
    @Test
    public void testPrintMessageForElementWithMirrorAndValue() {
        Kind kind = chooseKind();
        String msg = makeMessageText();
        Element elem = new MockElement();
        AnnotationMirror mirror = new MockMirror();
        AnnotationValue value = new MockValue();
        MessageRecord expected = new MessageRecord(kind, msg, elem, mirror, 
                value);
        MockMessager messager = new MockMessager();
        messager.printMessage(kind, msg, elem, mirror, value);
        MessageRecord actual = messager.getLatestMessage();
        assert actual != null : "Latest message should not be null";
        String message = "Printed message \"" + msg + "\" of kind " 
                + kind.toString() + " for element " + elem.toString() 
                + " with mirror " + mirror.toString() + " and value " 
                + value.toString();
        assertEquals(message, expected, actual);
    }
    
    private static void printRandomMessage(Messager messager) {
        Kind kind = chooseKind();
        String msg = makeMessageText();
        int selector = RANDOM.nextInt();
        switch (selector % 4) {
            case -3:
            case 1:
                messager.printMessage(kind, msg);
                break;
            case -2:
            case 2:
                messager.printMessage(kind, msg, new MockElement());
                break;
            case -1:
            case 3:
                messager.printMessage(kind, msg, new MockElement(), 
                        new MockMirror());
                break;
            default:
                messager.printMessage(kind, msg, new MockElement(), 
                        new MockMirror(), new MockValue());
        }
    }
    
    @Test
    public void testGetMessages() {
        System.out.println("getMessages");
        int numberOfCalls = RANDOM.nextInt(16) + 4;
        List<MessageRecord> expected = new ArrayList<>(numberOfCalls);
        MockMessager instance = new MockMessager();
        for (int i = 0; i < numberOfCalls; i++) {
            printRandomMessage(instance);
            expected.add(instance.getLatestMessage());
        }
        List<MessageRecord> intermediate = instance.getMessages();
        List<MessageRecord> actual = new ArrayList<>(intermediate);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetMessagesDoesNotLeakFieldReference() {
        int numberOfCalls = RANDOM.nextInt(8) + 2;
        MockMessager instance = new MockMessager();
        for (int i = 0; i < numberOfCalls; i++) {
            printRandomMessage(instance);
        }
        List<MessageRecord> initial = instance.getMessages();
        List<MessageRecord> expected = new ArrayList<>(initial);
        int index = RANDOM.nextInt(numberOfCalls);
        initial.remove(index);
        List<MessageRecord> actual = instance.getMessages();
        assertEquals(expected, actual);
    }
    
}
