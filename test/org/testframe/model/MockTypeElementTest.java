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
package org.testframe.model;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.testframe.model.MockElementTest.RANDOM;

/**
 * Tests of the MockTypeElement class.
 * @author Alonso del Arte
 */
public class MockTypeElementTest {
    
    private static List<Object> makeList() {
        int size = RANDOM.nextInt(64) + 16;
        List<Object> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            switch (i % 5) {
                case 0:
                    list.add(LocalDate.now().minusDays(i));
                    break;
                case 1:
                    list.add(LocalDateTime.now().minusMinutes(i));
                    break;
                case 2:
                    list.add(new MockElementTest());
                    break;
                case 3:
                    list.add(new MockNameTest());
                    break;
                default:
                    list.add(new Object());
            }
        }
        return list;
    }
    
    @Test
    public void testGetQualifiedName() {
        System.out.println("getQualifiedName");
        List<Object> list = makeList();
        Collections.shuffle(list);
        list.add(this);
        list.stream().map((obj) -> obj.getClass()).forEachOrdered((type) -> {
            TypeElement instance = new MockTypeElement(type);
            Name expected = new MockName(type);
            Name actual = instance.getQualifiedName();
            assertEquals(expected, actual);
        });
    }
    
}
