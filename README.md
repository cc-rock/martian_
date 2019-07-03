Martian Robots
==============

Instructions for running:
-------------------------

With IntelliJ Idea: 
Open the project as a gradle project, then run the Main.kt class.
You can pass the name of the file to read as a single argument, otherwise the input.txt file 
(in the resources folder) will be read.

From the command line: 
Make sure you have a valid Java 8 JDK installation with environment variables set correctly to run Gradle.
from the project's root directory, you can launch the following commands:
- To run without arguments (default input file in "resources"): `./gradlew run`
- To run with arguments (name of file to read): `./gradlew run --args <path_to_input_file>`
- To run unit tests: `./gradlew test`

Improvements / next steps:
--------------------------

- Write a unit test for InputParser, checking that the example input parses correctly, 
and that empty lines and extra tabs/spaces do not affect the result
- Write more test cases in the RobotsProcessor unit test
- the InputParser implementation could possibly be rewritten in a more elegant way, using less state variables,
but it was late so I left it as a point of improvement.
 
