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
 * Provides a way to test messages are correctly assembled in annotation 
 * processing. This is a mock messager only in the sense that it's not meant for 
 * use in a production context. It should only be used for testing.
 * @author Alonso del Arte
 */
public class MockMessager implements Messager {
    
    private MessageRecord mostRecentMessage = null;
    
    MessageRecord getLatestMessage() {
        return this.mostRecentMessage;
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg) {
        MessageRecord record = new MessageRecord(kind, msg);
        this.mostRecentMessage = record;
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem) {
        MessageRecord record = new MessageRecord(kind, msg, elem);
        this.mostRecentMessage = record;
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror) {
        MessageRecord record = new MessageRecord(kind, msg, elem, mirror);
        this.mostRecentMessage = record;
    }

    @Override
    public void printMessage(Kind kind, CharSequence msg, Element elem, 
            AnnotationMirror mirror, AnnotationValue value) {
        // TODO: Write tests for this
    }
    
}
