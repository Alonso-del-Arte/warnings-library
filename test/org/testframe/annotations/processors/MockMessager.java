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

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

/**
 *
 * @author Alonso del Arte
 */
public class MockMessager implements Messager {

    @Override
    public void printMessage(Kind kind, CharSequence msg) {
        // TODO: Write tests for this
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem) {
        // TODO: Write tests for this
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror) {
        // TODO: Write tests for this
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror, AnnotationValue value) {
        // TODO: Write tests for this
    }
    
}
