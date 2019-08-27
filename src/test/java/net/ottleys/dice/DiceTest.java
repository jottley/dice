/**
 * Copyright 2019 ottleys.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ottleys.dice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DiceTest {

    @Test
    public void DieTest() {
        try {
        Die die = new Die(3);
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DieMaxMinTest() {
        try {
        Die die = new Die(-5);
        fail();
        }
        catch(MaxMinException e) {
            assertEquals("Max must be Greater than Min", e.getMessage());
            
        }
    }

}