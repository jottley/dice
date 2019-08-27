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
package net.ottleys.dice.standard;

public enum Faces {

    TWO(2), FOUR(4), SIX(6), EIGHT(8), TEN(10), TWELVE(12), TWENTY(20);

    private final int face;

    Faces(int face) {
        this.face = face;
    }

    public int getValue() {
        return face;
    }

}