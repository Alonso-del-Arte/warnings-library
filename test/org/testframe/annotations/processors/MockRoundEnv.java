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

/**
 *
 * @author Alonso del Arte
 */
public class MockRoundEnv implements RoundEnvironment {
    
    private boolean hasErrors = false;
    
    private boolean processingDone = false;
    
    private final Set<? extends Element> rootElems;
    
    void raiseError() {
        this.hasErrors = true;
    }

    void endProcessing() {
        this.processingDone = true;
    }

    @Override
    public boolean processingOver() {
        return this.processingDone;
    }

    @Override
    public boolean errorRaised() {
        return this.hasErrors;
    }

    @Override
    public Set<? extends Element> getRootElements() {
        return this.rootElems;
    }

    @Override
    public Set<? extends Element> getElementsAnnotatedWith(TypeElement elem) {
        String qualName = elem.getQualifiedName().toString();
        return this.rootElems.stream().filter((e) -> 
            e.toString().contains(qualName)
        ).collect(Collectors.toSet());
    }

    // TODO: Write tests for this
    @org.testframe.annotations.warnings.Untested
    @Override
    public Set<? extends Element> getElementsAnnotatedWith(Class<? 
            extends Annotation> ann) {
        return new HashSet<>();
    }
    
    public MockRoundEnv(Set<? extends Element> elements) {
        this.rootElems = elements;
    }
    
}
