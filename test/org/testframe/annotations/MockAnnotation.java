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
package org.testframe.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mock annotation for testing purposes. Although the {@code 
 * SupportedAnnotationTypes} annotation on the processor is supposed to keep 
 * the processor from receiving unsupported annotations, it might still be 
 * necessary to test that the processor can receive and gracefully handle 
 * unsupported annotations. This annotation, which is supposed to only exist in 
 * Test Packages, not Source Packages, may be used for that purpose.
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface MockAnnotation {
    
    /**
     * Specify an identification number for an annotated function or procedure.
     * @return Some integer. May be pseudorandom.
     */
    int id();
    
}
