/**
 * Copyright 2019 Jared Ottley
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

    public int sides = 0;
    public int min = 1;
    public int max;

    public Die(int sides) throws MaxMinException {
        this.sides = sides;
        this.max = this.sides;
        validateMax();
    }

    public Die(Faces faces) throws MaxMinException {
        this.sides = faces.getValue();
        this.max = this.sides;
        validateMax();
    }

    public int roll() {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void validateMax() throws MaxMinException {
        if (max <= min) {
            throw new MaxMinException("Max must be Greater than Min");
        }
    }

}