<h1> ESHOP ADV PROG </h1>

## Module 1

### Reflection 1
What I've learned from the tutorial is that Java Springboot uses
MVC instead of MVT like Python Django. This makes the view and models
completely seperated. To connect them, we have the controller which is more
compact than Django's MVT. The advantages of MVC are that since
model (backend) and view(frontend) is seperated, it could be clearer
for us to improve/develop each of the parts separately. This method is
a good implementation of separation of concern.

What I can improve from my code is that to be more consistent with the programming.
In my code, the functions that access certain elements partly uses id, partly uses
the product as the passed arguments. One other thing is that the security, I got the
inspiration from a friend that the ID of anything should be more secure, that is by using
UUID, so that it can't be serialized by unauthorized people. 

Another thing unrelated to my code that I can improve is my git habit. From this exercise,
I learned about how tidy I can be by commiting small things into different small branches, so that
if it doesn't work well, you can just let it be without burden, and if you got what you intended,
then it's a small but significant progress which is beneficial. I intend to learn more about
git commands that I don't know and delve deeper into those that I already know.

### Reflection 2
1. I felt relieved after creating the unit and functional tests. This is due to the fact that my program
can run without (most probably) any errors since I've already tested using the positive and negative occasion
of the tests. I've also tested that we can open the home page and create a product and also displaying it.
For the unit tests, I need to make at least 2 tests for each method, since we want to get the positive and negative
occasions. This is due to the fact that I want to test my code coverage if there's any error in the program and what
your program should do if there is any error. I ran my code coverage test, and it says 83% for class, 53% for method,
and 41% for line coverage. This means that I haven't created enough tests for my code, but this doesn't mean I have
many errors, nor have few errors.

2. The code would be fine since it's already clean. However, the functional test procedure would be repetitive
since you've already done a similar procedure with the same setups and initial variables. It would be advised to use the
prior setups and initial variables so that your later code isn't redundant.

   
## Module 2
### Reflection
1. I got code redundancy in the product controller. I have multiple return values that are repetitive, which requires
me to create a constant and static variable regarding that return value (a redirect link). I also got the assertEquals
argument incorrectly, the former should be actual value and the latter should be expected value. There are other 
code quality issues that I encounter which I forgot
2. In my case, the current implementation has met the definition of CI/CD. It met the definition of CI because I
integrated my app by implementing testing tools such as OSSF scorecard, JUnit, JaCoCo, and also SonarCloud. After 
the integration process, my app continuously deploys the current version of the app based on the repository, which met
the definition of CD.

## Module 3
### Reflection
1. I have implemented SRP, DIP, and LSP. 
   - DIP is short for Dependencies Inversion Principle which suggests that
   higher-level modules should not rely on concrete implementations of the lower-level module, rather both of them
   should depend on abstraction. I used this principle on the services to rely on the abstraction of the repository 
   instead of using the concrete implementation
   - SRP (short for Single Responsibility Principle) suggest that a class should only have a single responsibility. In 
   this case, an example would be having 3 controllers, `CarController`, `HomeController`, and `ProductController`, 
   which have 3 different functionalities.
   - LSP (short for Liskov Substitution Principle) suggests that for every child-class, they inherit everything from
   the parent-class without disturbing the functional flow. I implemented it in the `CarController` class since it 
   has different functionality from the parent-class, hence the subclass can't substitute the parent class. Therefore,
   I remove the 'extends' part
   
2. The advantage of SOLID principles lies in creating modular, organized code that is easier to maintain, modify, and 
understand, particularly in team settings.

3. The disadvantages of not adhering to SOLID principles include code that is harder to maintain, prone to bugs, 
difficult to extend or modify, and less understandable, leading to increased technical debt and reduced scalability.
