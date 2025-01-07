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
package org.testframe.annotations;

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;

/**
 * Tests of the MockAnnotationsProvider class.
 * @author Alonso del Arte
 */
public class MockAnnotationsProviderTest {
    
    @Test
    public void testMakeCustomWarning() {
        System.out.println("makeCustomWarning");
        CustomWarning actual = MockAnnotationsProvider.makeCustomWarning();
        assert actual != null : "Returned object should not be null";
        String value = actual.value();
        assert value != null : "Custom warning value should not be null";
        System.out.println("\"" + value + "\"");
        assertEquals(CustomWarning.class, actual.annotationType());
    }
    
    @Test
    public void testMakeMockAnnotation() {
        System.out.println("makeMockAnnotation");
        MockAnnotation actual = MockAnnotationsProvider.makeMockAnnotation();
        assert actual != null : "Returned object should not be null";
        String key = actual.key();
        assert key != null : "Mock value key should not be null";
        System.out.println("\"" + key + "\"");
        assertEquals(MockAnnotation.class, actual.annotationType());
    }
    
    @Test
    public void testMakeNarrowingWarning() {
        System.out.println("makeNarrowingWarning");
        NarrowingConversionWarning actual 
                = MockAnnotationsProvider.makeNarrowingWarning();
        assert actual != null : "Returned object should not be null";
        assertEquals("Querying source type", 
                MockAnnotationsProvider.WideType.class, actual.sourceType());
        assertEquals("Querying target type", 
                MockAnnotationsProvider.NarrowType.class, actual.targetType());
        assertEquals(NarrowingConversionWarning.class, actual.annotationType());
    }
    
    @Test
    public void testMakeUntestedWarning() {
        System.out.println("makeUntestedWarning");
        Untested actual = MockAnnotationsProvider.makeUntestedWarning();
        assert actual != null : "Returned object should not be null";
        assertEquals(Untested.class, actual.annotationType());
    }
    
    @Test
    public void testMakeDeprecatedWarning() {
        System.out.println("makeDeprecatedWarning");
        Deprecated actual = MockAnnotationsProvider.makeDeprecatedWarning();
        assert actual != null : "Returned object should not be null";
        assertEquals(Deprecated.class, actual.annotationType());
    }
    
    @Test
    public void testMakeFunctionalInterfaceAnnotation() {
        System.out.println("makeFunctionalInterfaceAnnotation");
        FunctionalInterface actual 
                = MockAnnotationsProvider.makeFunctionalInterfaceAnnotation();
        assert actual != null : "Returned object should not be null";
        assertEquals(FunctionalInterface.class, actual.annotationType());
    }
    
    @Test
    public void testMakeOverrideAnnotation() {
        System.out.println("makeOverrideAnnotation");
        Override actual = MockAnnotationsProvider.makeOverrideAnnotation();
        assert actual != null : "Returned object should not be null";
        assertEquals(Override.class, actual.annotationType());
    }
    
}
