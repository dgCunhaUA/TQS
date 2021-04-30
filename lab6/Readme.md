1 e)
Yes my P2Euromilions project has passed the defined quality gate.

![screenshot](file:///home/cunha/Pictures/Screenshot%20from%202021-04-27%2010-07-20.png)


1 f)

**Bug** - Save and re-use this "Random"

Description:
    
    Creating a new Random object each time a random value is needed is inefficient and may produce numbers which are not random depending on the JDK. For better efficiency and randomness, create a single Random, then store, and reuse it.

    The Random() constructor tries to set the seed with a distinct value every time. However there is no guarantee that the seed will be random or even uniformly distributed. Some JDK will use the current time as seed, which makes the generated numbers not random at all.

    This rule finds cases where a new Random is created each time a method is invoked and assigned to a local random variable.


Solution:
    
    private Random rand = SecureRandom.getInstanceStrong();  // SecureRandom is preferred to Random

    public void doSomethingCommon() {
    int rValue = this.rand.nextInt();
    //...

**Vulnerabilities** - No vulnerabilities found.

**Code Smells** - Refactor the code in order to not assign to this loop counter from within the loop body.

Description: 

    A for loop stop condition should test the loop counter against an invariant value (i.e. one that is true at both the beginning and ending of every loop iteration). Ideally, this means that the stop condition is set to a local variable just before the loop begins.

    Stop conditions that are not invariant are slightly less efficient, as well as being difficult to understand and maintain, and likely lead to the introduction of errors in the future.

    This rule tracks three types of non-invariant stop conditions:

    When the loop counters are updated in the body of the for loop
    When the stop condition depend upon a method call
    When the stop condition depends on an object property, since such properties could change during the execution of the loop.

Solution:

    for (int i = 0; i < 10; i++) {...}


**Code Smells** - Replace this use of System.out or System.err by a logger.

Description:

    When logging a message there are several important requirements which must be fulfilled:

        The user must be able to easily retrieve the logs
        The format of all logged message must be uniform to allow the user to easily read the log
        Logged data must actually be recorded
        Sensitive data must only be logged securely

    If a program directly writes to the standard outputs, there is absolutely no way to comply with those requirements. That's why defining and using a dedicated logger is highly recommended.

Solution:

    logger.log("My Message");




**Task 2: Resolve technical debt**


My debt value for the euromillions project is 2h 31min.
The debt value gives the developer the estimated time that is needed to fix all the issues found in the sonarqube.


2 d)
How many lines are “uncovered”? R: 12 Lines.

And how many conditions? R: 0.