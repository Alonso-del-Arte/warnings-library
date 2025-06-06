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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.testframe.annotations.warnings.CustomWarning;
import org.testframe.annotations.warnings.NarrowingConversionWarning;
import org.testframe.annotations.warnings.Untested;

/**
 *
 * @author Alonso del Arte
 */
public class MockAnnotationsProvider {
    
    private static final Random RANDOM = new Random();
    
    private static final String[] STANDARD_WARNING_NAMES = {"all", "boxing", 
        "cast", "dep-ann", "deprecation", "fallthrough", "finally", "hiding", 
        "incomplete-switch", "javadoc", "nls", "null", "rawtypes", "resource", 
        "restriction", "serial", "static-access", "super", "sync-override", 
        "synthetic-access", "unchecked", "unqualified-field-access", "unused"};
    
    private static final int NUMBER_OF_WARNING_NAMES 
            = STANDARD_WARNING_NAMES.length;
    
    private static final int HALF_NUMBER_OF_WARNING_NAMES 
            = NUMBER_OF_WARNING_NAMES / 2;
    
    /**
     * The total number of distinct available annotation types from this 
     * provider. They are:
     * <ol>
     * <li>{@code CustomWarning.class}, use {@link #makeCustomWarning()}</li>
     * <li>{@code Deprecated.class}, use {@link #makeDeprecatedWarning()}</li>
     * <li>{@code FunctionalInterface.class}, use {@link 
     * #makeFunctionalInterfaceAnnotation()}</li>
     * <li>{@code MockAnnotation.class}, use {@link #makeMockAnnotation()}</li>
     * <li>{@code NarrowingConversionWarning.class}, use {@link 
     * #makeNarrowingWarning()}</li>
     * <li>{@code Override.class}, use {@link #makeOverrideAnnotation()}</li>
     * <li>{@code SafeVarargs.class}, use {@link 
     * #makeSafeVarargsAnnotation()}</li>
     * <li>{@code SuppressWarnings.class}, use {@link 
     * #makeSuppressWarningsAnnotation()}</li>
     * <li>{@code Untested.class}, use {@link #makeUntestedWarning()}</li>
     * </ol>
     */
    public static final int NUMBER_OF_AVAILABLE_ANNOTATION_TYPES = 9;
    
    public static CustomWarning makeCustomWarning() {
        return new CustomWarning() {
            
            private final int exampleNumber = RANDOM.nextInt();
            
            @Override
            public String value() {
                return "EXAMPLE FOR TESTING PURPOSES " + this.exampleNumber;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return CustomWarning.class;
            }
            
        };
    }
    
    public static MockAnnotation makeMockAnnotation() {
        return new MockAnnotation() {
                        
            @Override
            public String key() {
                return "EXAMPLE FOR TESTING PURPOSES " + RANDOM.nextInt();
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MockAnnotation.class;
            }
            
        };
    }
    
    public static NarrowingConversionWarning makeNarrowingWarning() {
        Class<?> narrow, wide;
        int selector = RANDOM.nextInt();
        switch (selector % 3) {
            case -2:
            case 1:
                wide = WideTypeA.class;
                narrow = NarrowTypeA.class;
                break;
            case -1:
            case 2:
                wide = WideTypeB.class;
                narrow = NarrowTypeB.class;
                break;
            default:
                wide = WideType.class;
                narrow = NarrowType.class;
        }
        return new NarrowingConversionWarning() {
            
            @Override
            public Class<?> sourceType() {
                return wide;
            }
            
            @Override
            public Class<?> targetType() {
                return narrow;
            }
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return NarrowingConversionWarning.class;
            }
            
        };
    }
    
    @SuppressWarnings("Convert2Lambda")
    public static Untested makeUntestedWarning() {
        return new Untested() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Untested.class;
            }
            
        };
    }
    
    @SuppressWarnings("Convert2Lambda")
    static Deprecated makeDeprecatedWarning() {
        return new Deprecated() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Deprecated.class;
            }
            
        };
    }
    
    @SuppressWarnings("Convert2Lambda")
    static FunctionalInterface makeFunctionalInterfaceAnnotation() {
        return new FunctionalInterface() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return FunctionalInterface.class;
            }
            
        };
    }
    
    @SuppressWarnings("Convert2Lambda")
    static Override makeOverrideAnnotation() {
        return new Override() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Override.class;
            }
            
        };
    }
    
    @SuppressWarnings("Convert2Lambda")
    static SafeVarargs makeSafeVarargsAnnotation() {
        return new SafeVarargs() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return SafeVarargs.class;
            }
            
        };
    }
    
    private static String[] chooseNames() {
        int len = RANDOM.nextInt(HALF_NUMBER_OF_WARNING_NAMES) + 1;
        String[] array = new String[len];
        for (int i = 0; i < len; i++) {
            array[i] = STANDARD_WARNING_NAMES[RANDOM
                    .nextInt(NUMBER_OF_WARNING_NAMES)];
        }
        return array;
    }
    
    static SuppressWarnings makeSuppressWarningsAnnotation() {
        return new SuppressWarnings() {
            
            private final String[] warningNames = chooseNames();
            
            @Override
            public String[] value() {
                return this.warningNames;
            }
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return SuppressWarnings.class;
            }
            
        };
    }
    
    private static Annotation giveAnnotationByIndex(int index) {
        switch (index) {
            case 0:
                return makeCustomWarning();
            case 1:
                return makeDeprecatedWarning();
            case 2:
                return makeFunctionalInterfaceAnnotation();
            case 3:
                return makeMockAnnotation();
            case 4:
                return makeNarrowingWarning();
            case 5:
                return makeOverrideAnnotation();
            case 6:
                return makeSafeVarargsAnnotation();
            case 7:
                return makeSuppressWarningsAnnotation();
            default:
                return makeUntestedWarning();
        }
    }
    
    static Annotation chooseAnnotation() {
        int index = RANDOM.nextInt(NUMBER_OF_AVAILABLE_ANNOTATION_TYPES);
        return giveAnnotationByIndex(index);
    }
    
    public static Annotation[] chooseAnnotations(int len) {
        if (len < 0) {
            String excMsg = "Length " + len 
                    + " is not valid, should be positive";
            throw new NegativeArraySizeException(excMsg);
        }
        if (len > NUMBER_OF_AVAILABLE_ANNOTATION_TYPES) {
            String excMsg = "Length " + len 
                    + " is not valid, as it is greater than maximum " 
                    + NUMBER_OF_AVAILABLE_ANNOTATION_TYPES;
            throw new IllegalArgumentException(excMsg);
        }
        Set<Integer> indices = new HashSet<>(len);
        while (indices.size() < len) {
            indices.add(RANDOM.nextInt(NUMBER_OF_AVAILABLE_ANNOTATION_TYPES));
        }
        Annotation[] array = new Annotation[len];
        Iterator<Integer> iterator = indices.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            int index = iterator.next();
            array[count] = giveAnnotationByIndex(index);
            count++;
        }
        return array;
    }
    
    public static class NarrowType {
        
        private NarrowType() {
            //
        }
        
    }
    
    public static class WideType {
        
        private WideType() {
            //
        }
        
    }
    
    public static class NarrowTypeA {
        
        private NarrowTypeA() {
            //
        }
        
    }
    
    public static class WideTypeA {
        
        private WideTypeA() {
            //
        }
        
    }
    
    public static class NarrowTypeB {
        
        private NarrowTypeB() {
            //
        }
        
    }
    
    public static class WideTypeB {
        
        private WideTypeB() {
            //
        }
        
    }
    
}
