# ecommerce-spring-data-jpa

Ecommerce Spring data jpa project

FAQ on SimpleJpaRepository
Question:
How does Spring Data JPA implement the ProductRepository interface and its methods when SimpleJpaRepository directly
implements JpaRepository and not ProductRepository? According to OOP principles, a class instance can be assigned to a
parent interface variable only if the class implements that interface.

Answer:
You're right: an instance of SimpleJpaRepository cannot be directly assigned to a variable of type ProductRepository if
SimpleJpaRepository doesn't implement ProductRepository.

However, what's happening in Spring Data JPA is that you're not working directly with instances of SimpleJpaRepository.
You're working with proxy objects that implement your ProductRepository interface and delegate the actual operations to
an instance of SimpleJpaRepository or another implementation.

Let me explain the complete workflow to you.

1. When your application starts, Spring Data JPA inspects your domain and repository interfaces. For each repository
   interface you have, it creates a proxy object. This proxy knows how to perform the operations your repository
   interface defines (like save, findAll, etc.), by delegating to an instance of SimpleJpaRepository (or a custom
   implementation if you've provided one).


2. When you inject a ProductRepository bean somewhere (e.g., via @Autowired), you're not injecting a raw
   SimpleJpaRepository. Instead, you're injecting the proxy object that Spring created for you. This proxy matches the
   interface of ProductRepository.


3. When you call a method on the injected ProductRepository, you're calling a method on the proxy. The proxy then
   delegates to the SimpleJpaRepository or whatever the appropriate target is.

This is a simplification, and there's a lot more going on under the hood with Spring Data JPA, but I hope this clears up
your confusion.
