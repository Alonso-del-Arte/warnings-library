/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Alonso del Arte
 */
@SupportedAnnotationTypes("org.testframe.warnings.NarrowingConversionWarning")
public class WarningsProcessor extends AbstractProcessor {
    
    // TODO: Write tests for this
    @Override
    public boolean process(Set<? extends TypeElement> annotations, 
            RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            annotations.stream().map((typeElem) 
                    -> roundEnv.getElementsAnnotatedWith(typeElem))
                    .forEachOrdered((elements) -> {
                elements.forEach((element) -> {
                    this.processingEnv.getMessager().printMessage(Kind.WARNING,
                            String.format("%s : HAVEN'T WRITTEN TESTS YET %s",
                                    roundEnv.getRootElements(), element), 
                            element);
                });
            });
        }
        return true;
    }
    
}
