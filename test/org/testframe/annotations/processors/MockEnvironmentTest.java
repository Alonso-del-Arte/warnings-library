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
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.annotations.processors.MessageRecordTest.RANDOM;

/**
 * Tests of the MockEnvironment class.
 * @author Alonso del Arte
 */
public class MockEnvironmentTest {
    
    private static final Set<Element> DEFAULT_ELEMENT_SET = new HashSet<>();
    
    static {
        DEFAULT_ELEMENT_SET.add(new MockElement());
    }
    
    @Test
    public void testNoErrorRaisedAtBeginning() {
        MockEnvironment instance = new MockEnvironment(DEFAULT_ELEMENT_SET);
        String msg = "Instance should not have error raised from the beginning";
        assert !instance.errorRaised() : msg;
    }
    
    @Test
    public void testErrorRaised() {
        System.out.println("errorRaised");
        MockEnvironment instance = new MockEnvironment(DEFAULT_ELEMENT_SET);
        instance.raiseError();
        String msg = "Instance should have error raised after raise call";
        assert instance.errorRaised() : msg;
    }
    
    @Test
    public void testProcessingNotOverAtTheBeginning() {
        MockEnvironment instance = new MockEnvironment(DEFAULT_ELEMENT_SET);
        String msg = "Processing should not be over from the beginning";
        assert !instance.processingOver() : msg;
    }
    
    @Test
    public void testProcessingOver() {
        System.out.println("processingOver");
        MockEnvironment instance = new MockEnvironment(DEFAULT_ELEMENT_SET);
        instance.endProcessing();
        String msg = "Processing should be over after call to end processing";
        assert instance.processingOver() : msg;
    }
    
}
