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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests of the MockProcEnv class.
 * @author Alonso del Arte
 */
public class MockProcEnvTest {
    
    @Test
    public void testNoLocaleInEffect() {
        ProcessingEnvironment instance = new MockProcEnv(new MockMessager());
        Locale locale = instance.getLocale();
        String message = "No locale should be in effect";
        assertNull(message, locale);
    }
    
    @Test
    public void testGetMessager() {
        System.out.println("getMessager");
        Messager expected = new MockMessager();
        ProcessingEnvironment instance = new MockProcEnv(expected);
        Messager actual = instance.getMessager();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetSourceVersion() {
        System.out.println("getSourceVersion");
        ProcessingEnvironment instance = new MockProcEnv(new MockMessager());
        SourceVersion expected = SourceVersion.RELEASE_8;
        SourceVersion actual = instance.getSourceVersion();
        assertEquals(expected, actual);
    }
    
}
