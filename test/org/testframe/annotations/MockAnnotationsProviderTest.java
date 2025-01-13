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

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;
import static org.testframe.api.Asserters.assertContainsSame;

/**
 * Tests of the MockAnnotationsProvider class.
 * @author Alonso del Arte
 */
public class MockAnnotationsProviderTest {
    
    /**
     * The total number of distinct available annotation types from the provider  
     * should be 9. Namely:
     * <ol>
     * <li>{@code CustomWarning.class}, use {@link 
     * MockAnnotationsProvider#makeCustomWarning()}</li>
     * <li>{@code Deprecated.class}, use {@link 
     * MockAnnotationsProvider#makeDeprecatedWarning()}</li>
     * <li>{@code FunctionalInterface.class}, use {@link 
     * MockAnnotationsProvider#makeFunctionalInterfaceAnnotation()}</li>
     * <li>{@code MockAnnotation.class}, use {@link 
     * MockAnnotationsProvider#makeMockAnnotation()}</li>
     * <li>{@code NarrowingConversionWarning.class}, use {@link 
     * MockAnnotationsProvider#makeNarrowingWarning()}</li>
     * <li>{@code Override.class}, use {@link 
     * MockAnnotationsProvider#makeOverrideAnnotation()}</li>
     * <li>{@code SafeVarargs.class}, use {@link 
     * MockAnnotationsProvider#makeSafeVarargsAnnotation()}</li>
     * <li>{@code SuppressWarnings.class}, use {@link 
     * MockAnnotationsProvider#makeSuppressWarningsAnnotation()}</li>
     * <li>{@code Untested.class}, use {@link 
     * MockAnnotationsProvider#makeUntestedWarning()}</li>
     * </ol>
     */
    @Test
    public void testNumberOfAvailableAnnotationTypes() {
        int expected = 9;
        int actual = MockAnnotationsProvider
                .NUMBER_OF_AVAILABLE_ANNOTATION_TYPES;
        assertEquals(expected, actual);
    }
    
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
    
    @Test
    public void testMakeSafeVarargsAnnotation() {
        System.out.println("makeSafeVarargsAnnotation");
        SafeVarargs actual 
                = MockAnnotationsProvider.makeSafeVarargsAnnotation();
        assert actual != null : "Returned object should not be null";
        assertEquals(SafeVarargs.class, actual.annotationType());
    }
    
    @Test
    public void testMakeSuppressWarningsAnnotation() {
        System.out.println("makeSuppressWarningsAnnotation");
        SuppressWarnings actual 
                = MockAnnotationsProvider.makeSuppressWarningsAnnotation();
        assert actual != null : "Returned object should not be null";
        assertEquals(SuppressWarnings.class, actual.annotationType());
        String[] value = actual.value();
        assert value != null : "Value array should not be null";
        int len = value.length;
        String msg = "Value array should have at least one element, found " 
                + len;
        assert len > 0 : msg;
        System.out.println(Arrays.toString(value));
    }
    
    @Test
    public void testChooseAnnotation() {
        System.out.println("chooseAnnotation");
        int totalNumberOfCalls 
                = MockAnnotationsProvider.NUMBER_OF_AVAILABLE_ANNOTATION_TYPES 
                * MockAnnotationsProvider.NUMBER_OF_AVAILABLE_ANNOTATION_TYPES;
        Set<Class<? extends Annotation>> expected = new HashSet<>();
        expected.add(CustomWarning.class);
        expected.add(Deprecated.class);
        expected.add(FunctionalInterface.class);
        expected.add(MockAnnotation.class);
        expected.add(NarrowingConversionWarning.class);
        expected.add(Override.class);
        expected.add(SafeVarargs.class);
        expected.add(SuppressWarnings.class);
        expected.add(Untested.class);
        Set<Class<? extends Annotation>> actual = new HashSet<>();
        for (int i = 0; i < totalNumberOfCalls; i++) {
            Annotation annotation = MockAnnotationsProvider.chooseAnnotation();
            actual.add(annotation.annotationType());
        }
        assertContainsSame(expected, actual);
    }
    
}
