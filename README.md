<h2> Reflection </h2>

What I've learned from the tutorial is that Java Springboot uses
MVC instead of MVT like Python Django. This makes the view and models
completely seperated. To connect them, we have the controller which is more
compact than Django's MVT. The advantages of MVC is that since
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