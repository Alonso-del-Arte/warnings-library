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
import java.util.Random;

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
    
    public static CustomWarning makeCustomWarning() {
        return new CustomWarning() {
            
            @Override
            public String value() {
                return "EXAMPLE FOR TESTING PURPOSES " + RANDOM.nextInt();
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
        return new NarrowingConversionWarning() {
            
            @Override
            public Class<?> sourceType() {
                return WideType.class;
            }
            
            @Override
            public Class<?> targetType() {
                return NarrowType.class;
            }
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return NarrowingConversionWarning.class;
            }
            
        };
    }
    
    public static Untested makeUntestedWarning() {
        return new Untested() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Untested.class;
            }
            
        };
    }
    
    static Deprecated makeDeprecatedWarning() {
        return new Deprecated() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Deprecated.class;
            }
            
        };
    }
    
    static FunctionalInterface makeFunctionalInterfaceAnnotation() {
        return new FunctionalInterface() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return FunctionalInterface.class;
            }
            
        };
    }
    
    static Override makeOverrideAnnotation() {
        return new Override() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Override.class;
            }
            
        };
    }
    
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
    
}
