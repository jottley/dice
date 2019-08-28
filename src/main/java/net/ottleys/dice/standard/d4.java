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

import net.ottleys.dice.Die;
import net.ottleys.dice.MaxMinException;

/**
 * A four sided die
 */
public class d4 extends Die {

    public d4() throws MaxMinException {
        super(Faces.FOUR);
    }

}