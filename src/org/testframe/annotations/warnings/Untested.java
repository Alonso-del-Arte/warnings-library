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
 * Warns that a function or procedure (or "method"), or a constructor, has not 
 * been tested. Generally this annotation should only be used when the 
 * developers expect a long lag between first declaring the untested item and 
 * writing the very first test for that item. Still, it's preferable to using a 
 * {@link CustomWarning} with the message that the annotated item has not been 
 * tested. However, this annotation is not available at the class level. My 
 * thinking is that it might be much easier to forget to remove this annotation 
 * when it no longer applies at the class level (which could be considered to be 
 * as soon as anything it contains has a test written).
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Untested {
    
}
