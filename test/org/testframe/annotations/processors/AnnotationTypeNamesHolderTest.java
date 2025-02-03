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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the AnnotationTypeNamesHolder class.
 * @author Alonso del Arte
 */
public class AnnotationTypeNamesHolderTest {

    @Test
    public void testCustomWarningName() {
        String expected = "org.testframe.annotations.warnings.CustomWarning";
        String actual = AnnotationTypeNamesHolder.CUSTOM_WARNING_NAME;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNarrowingWarningName() {
        String expected = 
                "org.testframe.annotations.warnings.NarrowingConversionWarning";
        String actual = AnnotationTypeNamesHolder.NARROWING_WARNING_NAME;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testUntestedWarningName() {
        String expected = "org.testframe.annotations.warnings.Untested";
        String actual = AnnotationTypeNamesHolder.UNTESTED_WARNING_NAME;
        assertEquals(expected, actual);
    }
    
}
