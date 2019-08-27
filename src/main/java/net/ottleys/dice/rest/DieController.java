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