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

import org.junit.Test;
import static org.junit.Assert.*;

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
    }
    
}
