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
package org.testframe.annotations.warnings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Warns that a conversion is a narrowing conversion. The conversion can occur 
 * either in a function having the narrower type as a return type, or maybe a 
 * constructor for the narrower type that takes an instance of the wider type as 
 * a parameter.
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface NarrowingConversionWarning {
    
    /**
     * The source type, which ought to be the wider type. The annotation 
     * processor will not check whether or not this type is in fact wider.
     * @return The source type. Ideally it has a superclass in common with 
     * {@link #targetType()} besides {@code java.lang.Object}. For example, 
     * {@code Float128}, a hypothetical 128-bit floating point type.
     */
    Class<?> sourceType();
    
    /**
     * The target type, which ought to be the narrower type. The annotation 
     * processor will not check whether or not this type is in fact narrower.
     * @return The target type. Ideally it has a superclass in common with 
     * {@link #sourceType()} besides {@code java.lang.Object}. For example, 
     * {@code Float8}, a hypothetical 8-bit floating point type.
     */
    Class<?> targetType();
    
}
