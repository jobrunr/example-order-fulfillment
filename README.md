# JobRunr example

This repository shows a simple example on how you can integrate JobRunr with [spring.io](https://spring.io/). Other examples
using [spring.io](https://spring.io/) can be found [here](https://github.com/jobrunr/example-spring) and [here](https://github.com/jobrunr/example-java-mag).

In this example, we simulate an [order fulfillment](https://en.wikipedia.org/wiki/Order_fulfillment) system. For this example, 
the fulfillment process starts after the customer completed the payment. This triggers the three following tasks:
- Send the order confirmation to the customer
- Notify the warehouse of the arrival of the order so work on the packaging can be swiftly done
- Initiate shipment by making a call to the carrier's booking API

Other tasks such as creating a sales report or resupplying the inventory are re-occurring and are best handled as RecurringJobs.

> This example is focused on JobRunr side of things, we leave the implementation of object such as the Inventory, the Order, the Product, etc. 
> as an exercise. Also the running of tasks is simulated by Thread.sleep(...) which implies explicit handling of `InterruptedException`, you 
> probably won't need those in an actual application.

## About this project
This project has 4 packages:

- **org.jobrunr.examples.config**: this package register the [OrderFulfilmentTasksFilter](src/main/java/org/jobrunr/example/tasks/filters/OrderFulfilmentTasksFilter.java)
- **org.jobrunr.examples.controllers**: this package contains a simple `RestController` called [OrderFulfillmentController](src/main/java/org/jobrunr/example/controllers/OrderFulfillmentController.java), 
which declares an endpoint to trigger the order fulfillment workflow
- **org.jobrunr.examples.services**: this package contains [OrderFulfillmentService](src/main/java/org/jobrunr/example/services/OrderFulfillmentService.java), a simple spring service with example order fulfillment methods.
- **org.jobrunr.examples.tasks**: this package contains [OrderFulfillmentTasks](src/main/java/org/jobrunr/example/tasks/OrderFulfillmentTasks.java) which declares the Jobs for JobRunr to process using the handy annotations
    - Note that the method annotated with `@Recurring` will be automatically picked up by JobRunr

## How to run this project:
- clone the project and open it in your favorite IDE that supports gradle
- First, run the main method from
  the [ExampleOrderFulfillmentApplication](src/main/java/org/jobrunr/example/ExampleOrderFulfillmentApplication.java)
- Open your favorite browser:
    - Navigate to the JobRunr dashboard located at http://localhost:8000/dashboard.
    - To trigger the order fulfillment workflow, open a new tab and go to http://localhost:8080/confirm-order.
    - Visit the dashboard again and see the jobs being processed!
      - Visit http://localhost:8000/jobs to see which states the jobs are in
    - Visit http://localhost:8000/reccuring-jobs to see the recurring jobs. Have fun triggering them!