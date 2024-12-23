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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.api.Asserters.assertContainsSame;

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
        String customWarnName = "org.testframe.warnings.CustomWarning";
        String narConvWarnName 
                = "org.testframe.warnings.NarrowingConversionWarning";
        String untestedWarnName = "org.testframe.warnings.Untested";
        String[] expected = {customWarnName, narConvWarnName, untestedWarnName};
        String[] actual = annotation.value();
        assertContainsSame(expected, actual);
    }
    
    /**
     * Test of process method, of class WarningsProcessor.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
//        Set<? extends TypeElement> annotations = null;
//        RoundEnvironment roundEnv = null;
//        WarningsProcessor instance = new WarningsProcessor();
//        boolean expResult = false;
//        boolean result = instance.process(annotations, roundEnv);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
