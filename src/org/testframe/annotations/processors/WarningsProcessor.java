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
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

/**
 * Processes warnings from the {@code org.testframe.warnings} package.
 * @author Alonso del Arte
 */
@SupportedAnnotationTypes({"org.testframe.warnings.CustomWarning", 
    "org.testframe.warnings.NarrowingConversionWarning", 
    "org.testframe.warnings.Untested"})
public class WarningsProcessor extends AbstractProcessor {
    
    // TODO: Write tests for this
    @org.testframe.annotations.warnings.Untested
    @Override
    public boolean process(Set<? extends TypeElement> annotations, 
            RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            annotations.stream().map((typeElem) 
                    -> roundEnv.getElementsAnnotatedWith(typeElem))
                    .forEachOrdered((elements) -> {
                elements.forEach((element) -> {
                    this.processingEnv.getMessager().printMessage(Kind.NOTE,
                            String.format("%s : HAVEN'T WRITTEN TESTS YET %s",
                                    roundEnv.getRootElements(), element), 
                            element);
                });
            });
        }
        return true;
    }
    
}
