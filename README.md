# dice

This project is a learning experiment for my son and I.  We are learning to build a simple REST applicaiton to role dice.

## Project plan

Our base object is a die. The important properties we discussed during our design session were the minimum and maximum values.  We decided that things like the shape of the die had little value for our base object. Color or symbols instead of numeric values could be important but should be things that are implemented in the client application. Another thing that the client application could find useful but that we didn't need to worry about would be persisting the values of previous roles.

We would focus on the standard variants of dice: 4, 6, 8, 10, 12, 20. We would build our classes in a way that would allow for the variants to be extended to rarer variant types.

Our endpoint will accept the parameters variant and number.  Variant is the type of die that should be rolled.  Number is the number of dices of the variant that should be rolled.

Our endpoint will return, in json, the variant used, the number of dice used, and the values for each die rolled.

## Requirements

We are using the following:

* Java 8
* Spring Boot 2.0.3
* Maven 3

