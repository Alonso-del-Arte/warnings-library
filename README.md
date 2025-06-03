# Warnings Library

WORK IN PROGRESS

A library with some compiler warnings. I started this project because I want a 
narrowing conversion compiler warning for a floating point library I'm working 
on, so that the floating point reference types to somewhat mirror with warnings 
the errors you get with Java's floating point primitives, such as trying to 
assign the value of a `double` (64-bit floating point) to a `float` (32-bit).

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

However, in February 2025, I realized I had bitten off a lot more than I could 
chew with this project. Getting the annotation processor to work like I want it 
will require a lot of understanding of how each IDE works internally. The 
project is on hold indefinitely.

## Available warnings

These warnings will be provided as annotations. It will not be enough to import 
the annotations, one also needs to install the annotation processor, as 
mentioned earlier.

* **Narrowing conversion warning** &mdash; For when a conversion requires a loss 
of precision due to narrowing, e.g., 256-bit floating point to 16-bit floating 
point. You will specify the wide class and the narrow class, but the annotation 
processor will not check whether or not the two specified classes have any 
superclasses in common besides `java.lang.Object` nor whether or not they both 
implement the same interface.
* **Custom warning** &mdash; A warning with a custom message, similar to the one 
in Xcode (which is an IDE for Swift). This should not be used for warnings that 
already have annotations defined in the Java Development Kit or available third 
party libraries, such as `@Deprecated` or `@NarrowingConversionWarning`.
* **Untested annotation** &mdash; Warns that a function or procedure (or 
"method"), or a constructor, has not been tested. The annotation processor will 
not try to detect the `@Test` annotation from the `org.testframe` namespace or 
any other namespace. This is to say that it is up to you to decide when to 
remove the `@Untested` annotation: the annotation processor will not cause an 
error if the annotation meets the criterion for removal, because the criterion 
for removal is up to you.

I thought about an annotation for `String` literals that are not supposed to be 
internationalized, but that's really more for an IDE to deal with.

Although the `@SupportedAnnotationTypes` annotation strongly assures us that the 
processor will only be given annotations it has declared it can process, this is 
not a mathematical certainty.

For that reason, I have programmed the processor to issue a diagnostic note in 
case it receives an annotation it has not been programmed for. Though I expect 
it will only be in the one unit test where this actually happens.

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
