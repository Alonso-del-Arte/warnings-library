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

/**
 * Mocks a {@code ProcessingEnvironment}. This is to be used for the testing of 
 * {@link WarningsProcessor}.
 * @author Alonso del Arte
 */
public class MockProcEnv implements ProcessingEnvironment {
    
    // TODO: Determine need to make MockElements
    @Override
    public Elements getElementUtils() {
        return null;
    }
    
    // TODO: Determine need to make MockFiler
    @Override
    public Filer getFiler() {
        return null;
    }
    
    @Override
    public Locale getLocale() {
        return null;
    }
    
    // TODO: Write tests for this
    @Override
    public Messager getMessager() {
        return new MockMessager();
    }
    
    // TODO: Determine need for this
    @Override
    public Map<String, String> getOptions() {
        Map<String, String> map = new HashMap<>();
        map.put("HUH?", "IS THIS NECESSARY?");
        return map;
    }
    
    // TODO: Write tests for this
    @Override
    public SourceVersion getSourceVersion() {
        return SourceVersion.RELEASE_0;
    }
    
    // TODO: Determine need to make MockTypes
    @Override
    public Types getTypeUtils() {
        return null;
    }
    
    public MockProcEnv(Messager messager) {
        // TODO: Write tests for this
    }
        
}
