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

**Note for Hacktoberfest:** This project is closed off to Hacktoberfest.
