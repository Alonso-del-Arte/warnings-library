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
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.MockAnnotation;
import org.testframe.annotations.MockAnnotationsProvider;
import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;
import static org.testframe.api.Asserters.assertContainsSame;
import org.testframe.model.MockElement;
import org.testframe.model.MockTypeElement;

/**
 * Tests of the WarningsProcessor class.
 * @author Alonso del Arte
 */
public class WarningsProcessorTest {
    
    @Test
    public void testSupportedAnnotationTypes() throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        String name = "org.testframe.annotations.processors.WarningsProcessor";
        Class<?> type = loader.loadClass(name);
        SupportedAnnotationTypes annotation 
                = type.getAnnotation(SupportedAnnotationTypes.class);
        assert annotation != null : "Annotation should not be null";
        String customWarnName 
                = "org.testframe.annotations.warnings.CustomWarning";
        String narConvWarnName 
                = "org.testframe.annotations.warnings." 
                + "NarrowingConversionWarning";
        String untestedWarnName = "org.testframe.annotations.warnings.Untested";
        String[] expected = {customWarnName, narConvWarnName, untestedWarnName};
        String[] actual = annotation.value();
        assertContainsSame(expected, actual);
    }
    
    private static WarningsProcessor makeInstance() {
        Messager messager = new MockMessager();
        ProcessingEnvironment env = new MockProcEnv(messager);
        WarningsProcessor processor = new WarningsProcessor();
        processor.init(env);
        return processor;
    }
    
    @Test
    public void testNoProcessingIfRoundOver() {
        WarningsProcessor instance = makeInstance();
        Set<Element> elements = MockRoundEnvTest.makeElemSet();
        MockRoundEnv roundEnv = new MockRoundEnv(elements);
        roundEnv.endProcessing();
        Set<TypeElement> set = new HashSet<>();
        set.add(new MockTypeElement(CustomWarning.class));
        instance.process(set, roundEnv);
        String msg = "With round over, there should be no calls to roundEnv";
        assertEquals(msg, 0, roundEnv.overriddenCallCount());
    }
    
    public Set<? extends TypeElement> wrapUpAnnotations() {
        Set<TypeElement> set = new HashSet<>();
        set.add(new MockTypeElement(CustomWarning.class));
        set.add(new MockTypeElement(NarrowingConversionWarning.class));
        set.add(new MockTypeElement(Untested.class));
        return set;
    }
    
    /**
     * Test of the process function, of the WarningsProcessor class.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        WarningsProcessor instance = makeInstance();
        Set<Element> elements = MockRoundEnvTest.makeElemSet();
        MockRoundEnv roundEnv = new MockRoundEnv(elements);
        Set<? extends TypeElement> annotations = wrapUpAnnotations();
        boolean opResult = instance.process(annotations, roundEnv);
        String msg = "Annotations should have been processed";
        assert opResult : msg;
    }
    
    @Test
    public void testProcessCustomWarning() {
        CustomWarning warning = MockAnnotationsProvider.makeCustomWarning();
        String expected = warning.value();
        Annotation[] anns = {warning};
        Element element = new MockElement(anns);
        Set<Element> elemSet = new HashSet<>();
        elemSet.add(element);
        RoundEnvironment roundEnv = new MockRoundEnv(elemSet);
        TypeElement typeElem = new MockTypeElement(CustomWarning.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(typeElem);
        WarningsProcessor instance = new WarningsProcessor();
        MockMessager messager = new MockMessager();
        ProcessingEnvironment proc = new MockProcEnv(messager);
        instance.init(proc);
        instance.process(annotations, roundEnv);
        MessageRecord record = messager.getLatestMessage();
        assert record != null : "Latest message should not be null";
        String kindMsg = "Message should be of type " + Kind.WARNING.toString();
        assertEquals(kindMsg, Kind.WARNING, record.getDiagnosticKind());
        CharSequence actual = record.getMessage();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testProcessNarrowingWarning() {
        NarrowingConversionWarning warning 
                = MockAnnotationsProvider.makeNarrowingWarning();
        String expected = "Narrowing conversion from " 
                + warning.sourceType().getSimpleName() + " to " 
                + warning.targetType().getSimpleName();
        Annotation[] anns = {warning};
        Element element = new MockElement(anns);
        Set<Element> elemSet = new HashSet<>();
        elemSet.add(element);
        RoundEnvironment roundEnv = new MockRoundEnv(elemSet);
        TypeElement typeElem 
                = new MockTypeElement(NarrowingConversionWarning.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(typeElem);
        WarningsProcessor instance = new WarningsProcessor();
        MockMessager messager = new MockMessager();
        ProcessingEnvironment proc = new MockProcEnv(messager);
        instance.init(proc);
        instance.process(annotations, roundEnv);
        MessageRecord record = messager.getLatestMessage();
        assert record != null : "Latest message should not be null";
        String kindMsg = "Message should be of type " + Kind.WARNING.toString();
        assertEquals(kindMsg, Kind.WARNING, record.getDiagnosticKind());
        CharSequence actual = record.getMessage();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testProcessUntestedWarning() {
        Untested warning = MockAnnotationsProvider.makeUntestedWarning();
        String expected = "The called function has not been tested";
        Annotation[] anns = {warning};
        Element element = new MockElement(anns);
        Set<Element> elemSet = new HashSet<>();
        elemSet.add(element);
        RoundEnvironment roundEnv = new MockRoundEnv(elemSet);
        TypeElement typeElem = new MockTypeElement(Untested.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(typeElem);
        WarningsProcessor instance = new WarningsProcessor();
        MockMessager messager = new MockMessager();
        ProcessingEnvironment proc = new MockProcEnv(messager);
        instance.init(proc);
        instance.process(annotations, roundEnv);
        MessageRecord record = messager.getLatestMessage();
        assert record != null : "Latest message should not be null";
        String kindMsg = "Message should be of type " + Kind.WARNING.toString();
        assertEquals(kindMsg, Kind.WARNING, record.getDiagnosticKind());
        CharSequence actual = record.getMessage();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testProcessProcessesMultipleAnnotationsForOneElement() {
        CustomWarning custom = MockAnnotationsProvider.makeCustomWarning();
        NarrowingConversionWarning narrowing 
                = MockAnnotationsProvider.makeNarrowingWarning();
        Untested untested = MockAnnotationsProvider.makeUntestedWarning();
        Annotation[] anns = {custom, narrowing, untested};
        Element element = new MockElement(anns);
        Set<Element> elemSet = new HashSet<>();
        elemSet.add(element);
        RoundEnvironment roundEnv = new MockRoundEnv(elemSet);
        TypeElement typeElemCustom = new MockTypeElement(CustomWarning.class);
        TypeElement typeElemNarrow 
                = new MockTypeElement(NarrowingConversionWarning.class);
        TypeElement typeElemUntested = new MockTypeElement(Untested.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(typeElemCustom);
        annotations.add(typeElemNarrow);
        annotations.add(typeElemUntested);
        Set<Kind> expectedKinds = new HashSet<>();
        expectedKinds.add(Kind.WARNING);
        String messageFromCustom = custom.value();
        String messageFromNarrowing = "Narrowing conversion from " 
                + narrowing.sourceType().getSimpleName() + " to " 
                + narrowing.targetType().getSimpleName();
        String messageFromUntested = "The called function has not been tested";
        Set<CharSequence> expectedMessages = new HashSet<>();
        expectedMessages.add(messageFromCustom);
        expectedMessages.add(messageFromNarrowing);
        expectedMessages.add(messageFromUntested);
        Set<Kind> actualKinds = new HashSet<>();
        Set<CharSequence> actualMessages = new HashSet<>();
        WarningsProcessor instance = new WarningsProcessor();
        MockMessager messager = new MockMessager();
        ProcessingEnvironment proc = new MockProcEnv(messager);
        instance.init(proc);
        instance.process(annotations, roundEnv);
        List<MessageRecord> records = messager.getMessages();
        for (MessageRecord record : records) {
            actualKinds.add(record.getDiagnosticKind());
            actualMessages.add(record.getMessage());
        }
        assertEquals(expectedKinds, actualKinds);
        assertEquals(expectedMessages, actualMessages);
    }
    
    @Test
    public void testProcessNotesUnknownAnnotation() {
        MockAnnotation mock = MockAnnotationsProvider.makeMockAnnotation();
        Annotation[] anns = {mock};
        Element element = new MockElement(anns);
        Set<Element> elemSet = new HashSet<>();
        elemSet.add(element);
        RoundEnvironment roundEnv = new MockRoundEnv(elemSet);
        TypeElement typeElem = new MockTypeElement(MockAnnotation.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(typeElem);
        WarningsProcessor instance = new WarningsProcessor();
        MockMessager messager = new MockMessager();
        ProcessingEnvironment proc = new MockProcEnv(messager);
        instance.init(proc);
        instance.process(annotations, roundEnv);
        MessageRecord record = messager.getLatestMessage();
        assert record != null : "Message record should not be null";
        String kindMsg = "Message kind should be " + Kind.NOTE.toString();
        assertEquals(kindMsg, Kind.NOTE, record.getDiagnosticKind());
        String expected = "Annotation " + typeElem.getQualifiedName().toString() 
                + " was not expected by " + WarningsProcessor.class.getName();
        CharSequence actual = record.getMessage();
        assertEquals(expected, actual);
    }
    
}
