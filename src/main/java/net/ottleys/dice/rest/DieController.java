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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import net.ottleys.dice.Die;
import net.ottleys.dice.MaxMinException;
import net.ottleys.dice.standard.d10;
import net.ottleys.dice.standard.d10.Base;

@RestController
@RequestMapping("/dice")
public class DieController {

    /**
     * Roll standard variant die
     * 
     * @param variant d2, d4, d6, d8, d12, d20
     * @param rolls   the number of rolls of the die
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws Exception
     */
    @RequestMapping("/roll/standard/{variant}/{rolls}")
    public ResponseEntity<List<Die>> roll(@PathVariable String variant, @PathVariable int rolls) throws Exception {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }

        return new ResponseEntity<>(dice, HttpStatus.OK);
    }

    /**
     * d10 can have special numbering
     * 
     * @param rolls the number of rolls of the die
     * @param base  ZERO (default): the sides of the die start at 0 and end at 9;
     *              ONE: the sides of the die start at 1 and end at 10; TEN: the
     *              sides of the die start at 0 and end at 90. The result of a roll
     *              is rounded to the nearest value of 10.
     * @return
     * @throws MaxMinException
     */
    @RequestMapping("/roll/standard/d10/{rolls}/{base}")
    public ResponseEntity<List<Die>> roll(@PathVariable int rolls, @PathVariable(required = false) Base base)
            throws MaxMinException {

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
                throw e;
            }
        }

        return new ResponseEntity<>(dice, HttpStatus.OK);
    }

    /**
     * Any custom sided die...not matter how unrealistic...well almost...sides must
     * be 2 or greater
     * 
     * @param sides the number of sides of the die
     * @param rolls the number of rolls of the die
     * @return
     * @throws MaxMinException
     */
    @RequestMapping("/roll/custom/{sides}/{rolls}")
    public ResponseEntity<List<Die>> roll(@PathVariable int sides, @PathVariable int rolls) throws MaxMinException {

        List<Die> dice = new ArrayList<Die>();
        if (sides > 1) {
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
                    throw e;
                }
            }
        } else {
            throw new MaxMinException("Sides must be greater than 1. (" + sides + ")");
        }

        return new ResponseEntity<>(dice, HttpStatus.OK);
    }

    // Exception handling for this controller
    @ExceptionHandler(ClassNotFoundException.class)
    public final ResponseEntity<ApiError> handleException(ClassNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST, "Unknown die variant: " + getVariantName(ex.getMessage()),
                        ex.getClass().getName() + ": " + ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InstantiationException.class, IllegalAccessException.class })
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {

        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create standard die",
                ex.getClass().getName() + ": " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxMinException.class)
    public final ResponseEntity<ApiError> handleException(MaxMinException ex, WebRequest request) {

        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getName() + ": " + ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    private String getVariantName(String canonicalName) {
        String variant = null;

        if (!StringUtils.isEmpty(canonicalName)) {
            String[] tokenizedName = StringUtils.tokenizeToStringArray(canonicalName, ".");
            variant = tokenizedName[tokenizedName.length - 1];
        }

        return variant;
    }

}