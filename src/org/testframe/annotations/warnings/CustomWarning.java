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
package org.testframe.annotations.warnings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gives a customized warning. Should only be used when there is no suitable 
 * specific warning in the Java Development Kit (e.g., {@code Deprecated} from 
 * the {@code java.lang} package), the integrated development environment (e.g., 
 * not null annotation in JetBrains IntelliJ IDEA), your project, this library 
 * (e.g., {@link NarrowingConversionWarning}), or any available third party 
 * library or framework. Consider also whether or not the message of the custom 
 * warning would make more sense as a To Do comment.
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, 
    ElementType.TYPE})
public @interface CustomWarning {
    
    /**
     * Gives the custom warning message. This will be passed along to the 
     * processor.
     * @return The custom warning message to pass along to the warnings 
     * processor.
     */
    String value();
    
}
