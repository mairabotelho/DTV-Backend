package com.zipcode.controllers;

import com.zipcode.exceptions.WorkOrderNotFoundException;
import com.zipcode.models.WorkOrder;
import com.zipcode.models.WorkOrderStatus.WorkOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.zipcode.services.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workorders")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @PostMapping("/workorders/create")
    public ResponseEntity createWorkOrder(@RequestBody WorkOrder workOrder)  {
        workOrderService.createWorkOrder(workOrder);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<WorkOrder>> displayAllWorkOrders(){
        return new ResponseEntity<>(workOrderService.displayAllWorkOrders(), HttpStatus.OK);
    }

    @GetMapping("/{workordersId}")
    public ResponseEntity<WorkOrder> displayOneWorkOrder (@PathVariable Long workOrderId){
        return new ResponseEntity<>(workOrderService.findWorkOrderById(workOrderId), HttpStatus.OK);
    }
    
    @PutMapping("/workorder")
    public ResponseEntity<WorkOrder> updateWorkOrderStatus(Long  workOrderId, WorkOrderStatus workOrderStatus)    {
        WorkOrder workOrder = workOrderService.findWorkOrderById(workOrderId);
        if(workOrder == null)   {
            throw new WorkOrderNotFoundException();
        }
        workOrderService.updateWorkOrderStatus(workOrder, workOrderStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/workorder")
    public ResponseEntity<WorkOrder> updateWorkOrderDescription(Long workOrderId, String workOrderDescription)   {
        WorkOrder workOrder = workOrderService.findWorkOrderById(workOrderId);
        if(workOrder == null)   {
            throw new WorkOrderNotFoundException();
        }
        workOrderService.updateWorkOrderDescription(workOrder, workOrderDescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/workorderstatus")
    public ResponseEntity <Iterable<WorkOrder>> findWorkOrdersByStatus(WorkOrderStatus workOrderStatus)   {
        Iterable<WorkOrder> workOrders = workOrderService.findWorkOrdersByStatus(workOrderStatus);
        if(workOrders == null)  {
            throw new WorkOrderNotFoundException();
        }
        return new ResponseEntity<>(workOrders, HttpStatus.OK);
    }

}
