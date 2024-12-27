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

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.model.MockElementTest.RANDOM;

/**
 * Tests of the MockName class.
 * @author Alonso del Arte
 */
public class MockNameTest {
    
    @Test
    public void testToString() {
        System.out.println("toString");
        String expected = "EXAMPLE " + RANDOM.nextInt();
        Name instance = new MockName(expected);
        String actual = instance.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        String s = RANDOM.nextInt() + " EXAMPLE";
        Name instance = new MockName(s);
        String msg = "Name \"" + instance.toString() 
                + "\" should be equal to itself";
        assert instance.equals(instance) : msg;
    }
    
    @Test
    public void testNotEqualsNull() {
        String s = "EXAMPLE " + RANDOM.nextInt();
        Name instance = new MockName(s);
        String msg = "Name \"" + instance.toString() 
                + "\" should not equal null";
        Object obj = null;
        assert !instance.equals(obj) : msg;
    }
    
    private static Object passThrough(Object obj) {
        return obj;
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        String s = RANDOM.nextInt() + " EXAMPLE";
        Name instance = new MockName(s);
        String msg = "Although both instances of CharSequence, String \"" + s 
                + "\" and MockName \"" + instance.toString() 
                + "\" should not be equal";
        Object obj = passThrough(s);
        assert !instance.equals(obj) : msg;
    }
    
    @Test
    public void testNotEqualsDiffParamS() {
        String s1 = "EXAMPLE " + RANDOM.nextInt();
        String s2 = s1.toLowerCase();
        Name instanceA = new MockName(s1);
        Name instanceB = new MockName(s2);
        String msg = "Name \"" + instanceA.toString() + "\" should not equal \"" 
                + instanceB.toString() + "\"";
        assert !instanceA.equals(instanceB) : msg;
    }
    
    @Test
    public void testNameFromClassName() {
        Class<?> type = this.getClass();
        Name instance = new MockName(type);
        String expected = type.getName();
        String actual = instance.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testLength() {
        System.out.println("length");
        String s = RANDOM.nextInt() + " EXAMPLE";
        Name instance = new MockName(s);
        int expected = s.length();
        int actual = instance.length();
        String message = "Reckoning length of \"" + s + "\"";
        assertEquals(message, expected, actual);
    }
    
    @Test
    public void testCharAt() {
        System.out.println("charAt");
        String s = "EXAMPLE " + RANDOM.nextInt();
        Name instance = new MockName(s);
        int index = RANDOM.nextInt(s.length());
        char expected = s.charAt(index);
        char actual = instance.charAt(index);
        String message = "Querying for character at position " + index 
                + " in \"" + s + "\", which is '" + expected + "'";
        assertEquals(message, expected, actual);
    }
    
    @Test
    public void testSubSequence() {
        System.out.println("subSequence");
        String s = "EXAMPLE " + RANDOM.nextInt() + " EXAMPLE " 
                + RANDOM.nextInt() + " EXAMPLE";
        Name instance = new MockName(s);
        int len = s.length();
        int start = RANDOM.nextInt(len - 2);
        int end = start + RANDOM.nextInt(len - start - 1) + 1;
        CharSequence expected = s.subSequence(start, end);
        CharSequence actual = instance.subSequence(start, end);
        String message = "Querying subsequence from " + start + " to " + end 
                + " of \"" + s + "\"";
        assertEquals(message, expected, actual);
    }
    
}
