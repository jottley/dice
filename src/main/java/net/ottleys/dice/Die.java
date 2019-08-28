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

import java.util.Random;

import net.ottleys.dice.standard.Faces;

public class Die {

    /** The default number of sides of the die */
    public int sides = 0;
    /** The default starting number */
    public int min = 1;
    /** The default ending number.  Typically equal to the number od sides */
    public int max;
    /** The value rolled of the die.  Defaults to -1 if it has never been rolled */
    public int rolled = -1;

    /**
     * @param sides The numbder of sides of the die
     * @throws MaxMinException
     */
    public Die(int sides) throws MaxMinException {
        this.sides = sides;
        this.max = this.sides;
        validateMax();
    }

    /**
     * @param faces The standard faces/sides of a die
     * @throws MaxMinException
     */
    public Die(Faces faces) throws MaxMinException {
        this.sides = faces.getValue();
        this.max = this.sides;
        validateMax();
    }

    /**
     * @return Roll the die.  The result is returned and stored in the rolled property
     */
    public int roll() {
        Random random = new Random();
        this.rolled = random.nextInt((max - min) + 1) + min;

        return rolled;
    }

    /**
     * validate the max value of the die is greater than the minimum value
     * @throws MaxMinException
     */
    private void validateMax() throws MaxMinException {
        if (max <= min) {
            throw new MaxMinException("Max must be Greater than Min");
        }
    }

}