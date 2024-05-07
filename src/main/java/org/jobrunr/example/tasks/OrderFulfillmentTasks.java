package org.jobrunr.example.tasks;

import org.jobrunr.example.services.OrderFulfillmentService;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderFulfillmentTasks {

    private final OrderFulfillmentService orderFulfillmentService;

    public OrderFulfillmentTasks(OrderFulfillmentService orderFulfillmentService) {
        this.orderFulfillmentService = orderFulfillmentService;
    }

    @Job(name = "order-%0-confirmation")
    public void sendOrderConfirmation(UUID orderId) throws InterruptedException {
        orderFulfillmentService.sendOrderConfirmation(orderId);
    }

    @Job(name = "order-%0-warehouse-notification")
    public void notifyWarehouse(UUID orderId) throws InterruptedException {
        orderFulfillmentService.notifyWarehouse(orderId);
    }

    @Job(name = "order-%0-shipment-initiation")
    public void initiateShipment(UUID orderId) throws InterruptedException {
        orderFulfillmentService.initiateShipment(orderId);
    }

    @Recurring(id = "daily-sales-report", cron = "0 0 * * *", zoneId = "${daily-sales-report.zone-id}")
    public void generateDailyReport() throws InterruptedException {
        orderFulfillmentService.generateDailyReport();
    }

    @Recurring(id = "daily-resupply", cron = "0 0 * * *", zoneId = "${daily-resupply.zone-id}")
    public void resupply() throws InterruptedException {
        orderFulfillmentService.resupply();
    }

}
