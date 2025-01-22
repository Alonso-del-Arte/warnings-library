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

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.MockAnnotation;
import org.testframe.annotations.MockAnnotationsProvider;
import static org.testframe.annotations.processors.MessageRecordTest.RANDOM;
import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;
import org.testframe.model.MockElement;
import org.testframe.model.MockTypeElement;

/**
 * Tests of the MockRoundEnv class.
 * @author Alonso del Arte
 */
public class MockRoundEnvTest {
    
    private static final Set<Element> DEFAULT_ELEMENT_SET = new HashSet<>();
    
    static {
        DEFAULT_ELEMENT_SET.add(new MockElement());
    }
    
    @Test
    public void testNoErrorRaisedAtBeginning() {
        RoundEnvironment instance = new MockRoundEnv(DEFAULT_ELEMENT_SET);
        String msg = "Instance should not have error raised from the beginning";
        assert !instance.errorRaised() : msg;
    }
    
    @Test
    public void testErrorRaised() {
        System.out.println("errorRaised");
        MockRoundEnv instance = new MockRoundEnv(DEFAULT_ELEMENT_SET);
        instance.raiseError();
        String msg = "Instance should have error raised after raise call";
        assert instance.errorRaised() : msg;
    }
    
    @Test
    public void testProcessingNotOverAtTheBeginning() {
        RoundEnvironment instance = new MockRoundEnv(DEFAULT_ELEMENT_SET);
        String msg = "Processing should not be over from the beginning";
        assert !instance.processingOver() : msg;
    }
    
    @Test
    public void testProcessingOver() {
        System.out.println("processingOver");
        MockRoundEnv instance = new MockRoundEnv(DEFAULT_ELEMENT_SET);
        instance.endProcessing();
        String msg = "Processing should be over after call to end processing";
        assert instance.processingOver() : msg;
    }
    
    @Test
    public void testGetRootElements() {
        System.out.println("getRootElements");
        int capacity = RANDOM.nextInt(8) + 2;
        Set<Element> elements = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            elements.add(new MockElement());
        }
        RoundEnvironment instance = new MockRoundEnv(elements);
        Set<? extends Element>  intermediate = instance.getRootElements();
        Set<Element> expected = new HashSet<>(elements);
        Set<Element> actual = new HashSet<>(intermediate);
        assertEquals(expected, actual);
    }
    
    private static Set<Element> makeElemSet() {
        int capacity = RANDOM.nextInt(16) + 4;
        Set<Element> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            Annotation[] annotations 
                    = MockAnnotationsProvider.chooseAnnotations(RANDOM
                            .nextInt(MockAnnotationsProvider
                                    .NUMBER_OF_AVAILABLE_ANNOTATION_TYPES));
            Element element = new MockElement(annotations);
            set.add(element);
        }
        return set;
    }
    
    private static <A extends Annotation> Set<Element> 
            pickOutFrom(Set<Element> elems, Class<A> annotationType) {
        return elems.stream().filter(
                elem -> elem.getAnnotation(annotationType) != null
        ).collect(Collectors.toSet());
    }
    
    @Test
    public void testGetElementsAnnotatedWith() {
        System.out.println("getElementsAnnotatedWith");
        Set<Element> elements = makeElemSet();
        RoundEnvironment instance = new MockRoundEnv(elements);
        Set<Class<? extends Annotation>> annotationTypes = new HashSet<>(4);
        annotationTypes.add(CustomWarning.class);
        annotationTypes.add(MockAnnotation.class);
        annotationTypes.add(NarrowingConversionWarning.class);
        annotationTypes.add(Untested.class);
        annotationTypes.forEach((annotationType) -> {
            Set<? extends Element> expected 
                    = pickOutFrom(elements, annotationType);
            TypeElement typeElem = new MockTypeElement(annotationType);
            Set<? extends Element> actual 
                    = instance.getElementsAnnotatedWith(typeElem);
            String message = "Gathering elements annotated with " 
                    + annotationType.getName();
            assertEquals(message, expected, actual);
        });
    }
    
    @Test
    public void testOverriddenCallCountZeroAtFirst() {
        Set<Element> elements = makeElemSet();
        String message = "Overridden call count should be 0 at first";
        MockRoundEnv instance = new MockRoundEnv(elements);
        assertEquals(message, 0, instance.overriddenCallCount());
    }
    
    public void testOverriddenCallCount() {
        System.out.println("overriddenCallCount");
        fail("RESUME WORK HERE");
    }
    
}
