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
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;

/**
 * Processes warnings from the {@code org.testframe.warnings} package.
 * @author Alonso del Arte
 */
@SupportedAnnotationTypes({"org.testframe.warnings.CustomWarning", 
    "org.testframe.warnings.NarrowingConversionWarning", 
    "org.testframe.warnings.Untested"})
public class WarningsProcessor extends AbstractProcessor {
    
    // TODO: Write test multiple annotations on one element are acknowledged
    @Override
    public boolean process(Set<? extends TypeElement> annotations, 
            RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            Messager messager = this.processingEnv.getMessager();
            annotations.stream().map((typeElem) 
                    -> roundEnv.getElementsAnnotatedWith(typeElem))
                    .forEachOrdered((elements) -> {
                elements.forEach((element) -> {
                    CustomWarning custom 
                            = element.getAnnotation(CustomWarning.class);
                    if (custom != null) {
                        String msg = custom.value();
                        messager.printMessage(Kind.WARNING, msg, element);
                    } else {
                        NarrowingConversionWarning narrowing = element
                                .getAnnotation(NarrowingConversionWarning
                                        .class);
                        if (narrowing != null) {
                            String msg = "Narrowing conversion from " 
                                    + narrowing.sourceType().getSimpleName() 
                                    + " to " + narrowing.targetType()
                                            .getSimpleName();
                            messager.printMessage(Kind.WARNING, msg, element);
                        } else {
                            Untested untested 
                                    = element.getAnnotation(Untested.class);
                            if (untested != null) {
                                String msg 
                                        = "The called function has not been tested";
                                messager.printMessage(Kind.WARNING, msg, 
                                        element);
                            }
                        }
                    }
                });
            });
        }
        return true;
    }
    
}
