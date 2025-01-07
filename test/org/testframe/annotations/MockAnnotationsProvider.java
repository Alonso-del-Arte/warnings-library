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
                return MockAnnotation.class;
            }
            
        };
    }
    
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
                return NarrowingConversionWarning.class;
            }
            
        };
    }
    
    static Untested makeUntestedWarning() {
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
