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
package net.ottleys.dice.standard;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import net.ottleys.dice.MaxMinException;
import net.ottleys.dice.standard.d10.Base;

public class StandardDiceTest {

    @Test
    public void d2Test() {
        try {
        d2 die = new d2();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d4Test() {
        try {
        d4 die = new d4();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d6Test() {
        try {
        d6 die = new d6();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d8Test() {
        try {
        d8 die = new d8();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d10Test() {
        try {
        d10 die = new d10();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d10Test2() {
        try {
            d10 die = new d10(Base.ONE);
                Integer result = die.roll();
                assertTrue((result >= die.min && result <= die.max) ? true : false);
            }
            catch(MaxMinException e) {
                fail(e.getMessage());
            }
    }

    @Test
    public void d10Test3() {
        try {
            d10 die = new d10(Base.TEN);
                Integer result = die.roll();
                assertTrue((result >= die.min && result <= die.max) ? true : false);
            }
            catch(MaxMinException e) {
                fail(e.getMessage());
            }
    }

    @Test
    public void d12Test() {
        try {
        d12 die = new d12();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void d20Test() {
        try {
        d20 die = new d20();
            Integer result = die.roll();
            assertTrue((result >= die.min && result <= die.max) ? true : false);
        }
        catch(MaxMinException e) {
            fail(e.getMessage());
        }
    }

}