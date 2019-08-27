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

import java.util.Random;

import net.ottleys.dice.Die;
import net.ottleys.dice.MaxMinException;

/**
 * A ten sided die
 */
public class d10 extends Die {

    public enum Base {ZERO,ONE,TEN};
    private Base base = Base.ZERO;

    /**
     * A ten sided die.  Values start at 0 and end at 9
     * @throws MaxMinException
     */
    public d10() throws MaxMinException {
        super(Faces.TEN);
        setMinMax(0, 9);
    }

    /**
     * A ten sided die.  With varaible values
     * @param base The number system used for the 10 sided die. ZERO (default): the sides of the die start at 0 and end at 9; ONE: the sides of the die start at 1 and end at 10; TEN: the sides of the die start at 0 and end at 90. The result of a roll is rounded to the nearest value of 10.
     * @throws MaxMinException
     */
    public d10(Base base) throws MaxMinException {
        super(Faces.TEN);
        this.base = base;

        switch (base) {
            case ZERO: setMinMax(0, 9); break;
            case ONE: setMinMax(1,10); break;
            case TEN: setMinMax(0, 90);
        }
    }

    /**
     * Set the min and max value of the die
     * @param min
     * @param max
     */
    private void setMinMax(int min, int max)
    {
        this.min = min;
        this.max = max;
    }

    /**
     * Roll the die.  The value is also stored in the rolled property
     * @return
     */
    @Override
    public int roll() {
        Random random = new Random();
        int result = random.nextInt((max - min) + 1) + min;

        if (base == Base.TEN)
        {
            result = Math.round(result/10) * 10;
        }

        this.rolled = result;
        
        return result;
    }

}