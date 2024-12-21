# Warnings Library

WORK IN PROGRESS

A library with some compiler warnings. I started this project because I want a 
narrowing conversion compiler warning for a floating point library I'm working 
on, for the floating point reference types to somewhat mirror with warnings the 
errors you get with Java's floating point primitives, such as trying to assign 
the value of a `double` (64-bit floating point) to a `float` (32-bit).

Early on I decided to put this in the `org.testframe` namespace even though I'm 
going to use JUnit 4 rather than Test Frame for most of the unit testing. The 
reason for this is that I'm thinking I want to submit this to Maven, and it's my 
understanding that Maven requires reverse domain name naming with a domain name 
owned by the submitter.

For the integrated development environment (IDE), I'm using Apache NetBeans 11.2 
running on Java 8, even though the computer I'm using has Java 21 (both the Java 
Development Kit and the Java Runtime Environment).

Note that this warnings library is going to be a source dependency, not a test 
dependency like the Test Frame testing framework.

It is my understanding that it is also necessary to add the warnings processor 
to your IDE for the warnings to actually show as expected. I will include 
instructions on how to do that for the three major IDEs (Eclipse, Apache 
NetBeans, JetBrains IntelliJ IDEA and though maybe starting with Eclipse and 
then the other two later on).

## Available warnings

These warnings will be provided as annotations. It will not be enough to import 
the annotations, one also needs to install the annotation processor, as 
mentioned earlier.

* **Narrowing conversion warning** &mdash; For when a conversion requires a loss 
of precision due to narrowing, e.g., 256-bit floating point to 16-bit floating 
point.
* **Custom warning** &mdash; A warning with a custom message, similar to the one 
in Xcode.
* **Untested annotation** &mdash; Warns that a function or procedure (or 
"method"), or a constructor, has not been tested.

## Instructions for enabling annotation processing

### Eclipse

Annotation processing can be enabled on a per-project basis.

In the Package Explorer (TODO: Doublecheck that's what it's called), right-click 
on the project for which you want to enable annotation processing.

FINISH WRITING

### Apache NetBeans

As with Eclipse, annotation processing in NetBeans can be enabled on a 
per-project basis. In the Projects pane, right-click on the project, then click 
on Properties in the contextual menu. From the dialog box, FINISH WRITING

FINISH WRITING

### JetBrains IntelliJ IDEA

As with Eclipse and NetBeans, annotation processing in NetBeans can be enabled 
on a per-project basis. 

FINISH WRITING

It is also possible to configure IntelliJ so that new projects have annotation 
processing enabled by default.

FINISH WRITING

----

**Note for Hacktoberfest:** This project is closed off to Hacktoberfest.
