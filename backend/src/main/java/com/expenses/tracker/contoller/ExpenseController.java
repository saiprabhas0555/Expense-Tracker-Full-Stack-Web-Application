package com.expenses.tracker.contoller;

import com.expenses.tracker.entity.Expense;
import com.expenses.tracker.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    
    @CrossOrigin(origins = "http://localhost:5174")
    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense) {
    	System.out.println("Received Expense Date: " + expense.getDate());
        return expenseService.addExpense(expense);
    }

    @GetMapping("/getExpenses")
    public ResponseEntity<List<Expense>> getExpenses(@RequestHeader("Authorization") String token) {
        return expenseService.getAllExpenses(token);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

}
