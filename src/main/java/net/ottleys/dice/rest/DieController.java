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
package net.ottleys.dice.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ottleys.dice.Die;
import net.ottleys.dice.MaxMinException;
import net.ottleys.dice.standard.d10;
import net.ottleys.dice.standard.d10.Base;

@RestController
@RequestMapping("/dice")
public class DieController {

    /**
     * Roll standard variant die
     * @param variant d2, d4, d6, d8, d12, d20
     * @param rolls the number of rolls of the die
     * @return
     */
    @RequestMapping("/roll/standard/{variant}/{rolls}")
    public List<Die> roll(@PathVariable String variant, @PathVariable int rolls) {
        List<Die> dice = new ArrayList<Die>();

        try {
            Class<?> clazz = Class.forName("net.ottleys.dice.standard." + variant);

            int roll = 0;
            while (roll < rolls) {
                Die die = (Die) clazz.newInstance();
                die.roll();
                dice.add(die);
                roll++;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return dice;
    }

    /**
     * d10 can have special numbering
     * @param rolls the number of rolls of the die
     * @param base ZERO (default): the sides of the die start at 0 and end at 9; ONE: the sides of the die start at 1 and end at 10; TEN: the sides of the die start at 0 and end at 90. The result of a roll is rounded to the nearest value of 10.
     * @return
     */
    @RequestMapping("/roll/standard/d10/{rolls}/{base}")
    public List<Die> roll(@PathVariable int rolls, @PathVariable(required = false) Base base) {

        List<Die> dice = new ArrayList<Die>();

        int roll = 0;
        while (roll < rolls) {
            d10 die;
            try {
                die = (base == null ? new d10() : new d10(base));
                die.roll();
                dice.add(die);
                roll++;
            } catch (MaxMinException e) {
                e.printStackTrace();
            }
        }

        return dice;
    }

    /**
     * Any custom sided die...not matter how unrealistic
     * @param sides the number of sides of the die
     * @param rolls the number of rolls of the die
     * @return
     */
    @RequestMapping("/roll/custom/{sides}/{rolls}")
    public List<Die> roll(@PathVariable int sides, @PathVariable int rolls) {

        List<Die> dice = new ArrayList<Die>();

        int roll = 0;
        while (roll < rolls) {
            Die die;
            try {
                die = new Die(sides);
                die.roll();
                dice.add(die);
                roll++;
            } catch (MaxMinException e) {
                e.printStackTrace();
            }
        }

        return dice;
    }

}