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

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import static org.testframe.annotations.processors.AnnotationTypeNamesHolder.*;
import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;

/**
 * Processes warnings from the {@code org.testframe.warnings} package. The 
 * following annotations are processed:
 * <ul>
 * <li>{@link CustomWarning} &mdash; Gives a custom message provided through the 
 * annotation.</li>
 * <li>{@link NarrowingConversionWarning} &mdash; Gives a message warning of a 
 * narrowing conversion from a specified wide type to a specified narrow 
 * type.</li>
 * <li>{@link Untested} &mdash; Gives the message "The called function has not 
 * been tested".</li>
 * </ul>
 * @author Alonso del Arte
 */
@SupportedAnnotationTypes({CUSTOM_WARNING_NAME, NARROWING_WARNING_NAME, 
    UNTESTED_WARNING_NAME})
public class WarningsProcessor extends AbstractProcessor {
    
    /**
     * Processes annotations. Messages are written to the messager of the 
     * supplied processing environment.
     * @param annotations The annotations to process. For example, {@link 
     * CustomWarning} and {@link NarrowingConversionWarning}.
     * @param roundEnv The round environment.
     * @return True because presumably this is only called with supported 
     * annotations.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, 
            RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            Messager messager = this.processingEnv.getMessager();
        }
        return true;
    }
    
}
