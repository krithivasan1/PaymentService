Payment Service - Homework

Spring initializer – PaymentService
This is on the microservice
Payment service will not store any data
It will call the payment gateway
We will create a micro service using spring initializer
We are going to send a object to PG, So we should be creating a DTO object ( which has Email, phone number ) , order id
We will create Order and send it from Payment service to Payment gateway
for payment gateway, there is no time needed.
When we get the request from Controller, there we will have time
We will be creating controller which is the end point configuration
In the product controller
private and abstract will not go together
After controller as always we need to create payment service
We should first create Interface PaymentService, then the implementation of it will be RazorPayPaymentService and StripePaymentService
In the service interface we have to go for a method PaymentResponse from the method doPayment
In PaymentController We have to auto wire or constructor or setter way of injecting
In controller - reqMapping - Postmapping We have the request body
preferred way of injecting is Constructor
for autowire multi thread will fail some time
if we are going to call third party api, We have the key and secret , We have to store that in the app properties
When ever we do the configuration that must be annotated with configuration class
Instead of hard coding we are having that in the configuration class.
How to get the value from the configuration - Using @Value (we can give the key name )
WE can define the request mapping to the class level and then method level. class level will be common for all the method and then specific to the method it will change
LB is for Inbound and not for Outbound
If we want to split between PG1 and PG2, We can't store that information to the DB. that will be costly call . For every txn it will hit db.
solution - Math.Random , it will generate random number between 1 and 10 , it will be equally distributed between these two. We can write a logic, if it is less than 7, We can route to Razor pay and If we are getting >7, we can route to the stripe
Refer the payment gateway related libraries in to the pom.xml
It should be along with the version
In the configuration class if we have different keys, you have to define that in the app.properties. Else in the mvn install you will have the issue

29.03.2024
Steps

Create PaymentController and write a request body to get the request



Create Interface for PaymentService.
	It should have the abstract method to receive the PaymentResponse Object, This is the one which is sent by Payment Gateway .


Implement the Interface Payment Service and Create a Implementation class RazorPaymentServiceImpl for razor pay payment gateway with the qualifier as Razorpay

Similiarly we have to implement for the StripePaymentGateway  with the qualifer as Stripe

Refer the PaymentService Interface in the Payment Controller class

Create RequestDTO. This is the object from client calling to the PaymentController. . In this case, We have written InitiatePaymentRequestDTO


In the controller , write the request mapping method which takes the InitiatePaymentRequestDTO. Get the object and Call the service method . Return type should be PaymentResponse.

Create configuration for RazorPay which takes the key and secrete . This is needed for connecting to the razor pay service

We need to create another controller where razor pay will hit the payment service which is the call back URL

In the controller inject the implementation of the Payment Service ( Razor and Stripe ) through the constructor injection

In the controller , write a logic to choose the payment gateway depending on the Option. It should be either stripe or Razorpay. Accordingly the service will be called.

Create the strategy class for choosing the payment Gateway


