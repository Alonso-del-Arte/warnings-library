# Warnings Library

WORK IN PROGRESS

A library with some compiler warnings. I started this project because I want a 
narrowing conversion compiler warning for a floating point library I'm working 
on, for the floating point reference types to mirror the errors you get with 
Java's floating point primitives, such as trying to assign the value of a 
`double` (64-bit floating point) to a `float` (32-bit).

Early on I decided to put this in the `org.testframe` namespace even though I'm 
going to use JUnit 4 rather than Test Frame for most of the unit testing. The 
reason for this is that I'm thinking I want to submit this to Maven, and it's my 
understanding that Maven requires reverse domain name naming.

For the integrated development environment (IDE), I'm using Apache NetBeans 11.2 
running on Java 8, even though the computer I'm using has Java 21 (both the Java 
Development Kit and the Java Runtime Environment).

Note that this warnings library is going to be a source dependency, not a test 
dependency like the Test Frame testing framework.

It is my understanding that it is also necessary to add the warnings processor 
to your IDE for the warnings to actually show as expected. I will include 
instructions on how to do that for the three major IDEs (Eclipse, JetBrains 
IntelliJ IDEA and Apache NetBeans, though maybe starting with Eclipse and then 
the other two later on).

TODO: Check if two consecutive dashes -- become em dash &mdash; ?

## Available warnings

* **Narrowing conversion warning** &mdash; For when a conversion requires a loss 
of precision due to narrowing, e.g., 256-bit floating point to 16-bit floating 
point.
* **Custom warning** &mdash; A warning with a custom message, similar to the one 
in Xcode.

----

**Note for Hacktoberfest:** This project is closed off to Hacktoberfest.
