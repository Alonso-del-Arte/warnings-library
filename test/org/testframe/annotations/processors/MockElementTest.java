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
package org.testframe.annotations.processors;

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;

import org.junit.Test;
import static org.junit.Assert.*;

import org.testframe.annotations.MockAnnotation;
import static org.testframe.annotations.processors.MessageRecordTest.RANDOM;
import static org.testframe.api.Asserters.assertDoesNotThrow;

/**
 * Tests of the MockElement class.
 * @author Alonso del Arte
 */
public class MockElementTest {
    
    @Test
    public void testGetAnnotation() {
        System.out.println("getAnnotation");
        int mockID = RANDOM.nextInt();
        Annotation expected = new MockAnnotation() {
            
            @Override
            public int id() {
                return mockID;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
        Annotation[] annotations = {expected};
        Element instance = new MockElement(annotations);
        Annotation actual = instance.getAnnotation(MockAnnotation.class);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testChooseAnnotationOfRightType() {
        int mockID = RANDOM.nextInt();
        Annotation expected = new MockAnnotation() {
            
            @Override
            public int id() {
                return mockID;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
        Annotation other = new Test() {
            
            @Override
            public Class<? extends Throwable> expected() {
                throw new UnsupportedOperationException("TEST PURPOSES");
            }

            @Override
            public long timeout() {
                throw new UnsupportedOperationException("TEST PURPOSES");
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Test.class;
            }

        };
        Annotation[] annotations = {other, expected};
        Element instance = new MockElement(annotations);
        Annotation actual = instance.getAnnotation(MockAnnotation.class);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetAnnotationReturnsNullIfDesiredTypeAbsent() {
        int mockID = RANDOM.nextInt();
        Annotation mockA = new MockAnnotation() {
            
            @Override
            public int id() {
                return mockID;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
        Annotation mockB = new MockAnnotation() {
            
            @Override
            public int id() {
                return mockID;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
        Annotation[] annotations = {mockA, mockB};
        Element instance = new MockElement(annotations);
        String msgPart = "Asking for absent annotation type should return null";
        String msg = msgPart + ", not cause exception";
        assertDoesNotThrow(() -> {
            Annotation actual = instance.getAnnotation(Test.class);
            assert actual == null : msgPart;
        }, msg);
    }
    
}
