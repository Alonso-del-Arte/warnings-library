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

import java.lang.annotation.Annotation;
import java.util.Random;

/**
 *
 * @author Alonso del Arte
 */
public class MockAnnotationsProvider {
    
    private static final Random RANDOM = new Random();
    
    static CustomWarning makeCustomWarning() {
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
    
    static MockAnnotation makeMockAnnotation() {
        return new MockAnnotation() {
                        
            @Override
            public String key() {
                return "EXAMPLE FOR TESTING PURPOSES " + RANDOM.nextInt();
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return org.testframe.annotations.MockAnnotation.class;
            }
            
        };
    }
    
    // TODO: Write tests for this
    @Untested
    static NarrowingConversionWarning makeNarrowingWarning() {
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
                return org.testframe.annotations.MockAnnotation.class;
            }
            
        };
    }
    
    // TODO: Write tests for this
    @Untested
    static Untested makeUntestedWarning() {
        return null;
    }
    
    // TODO: Write tests for this
    @Untested
    static Deprecated makeDeprecatedWarning() {
        return null;
    }
    
    // TODO: Write tests for this
    @Untested
    static FunctionalInterface makeFunctionalInterfaceAnnotation() {
        return null;
    }
    
    // TODO: Write tests for this
    @Untested
    static Override makeOverrideAnnotation() {
        return null;
    }
    
    // TODO: Write tests for this
    @Untested
    static SafeVarargs makeSafeVarargsAnnotation() {
        return null;
    }
    
    // TODO: Write tests for this
    @Untested
    static SuppressWarnings makeSuppressWarningsAnnotation() {
        return null;
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
