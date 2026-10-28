package com.jobportal.exception;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<Map<String,String>> notFound(ResourceNotFoundException ex){
  Map<String,String> body=new HashMap<>(); body.put("error",ex.getMessage()); return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
 }
 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException ex){
  Map<String,String> body=new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(e->body.put(e.getField(),e.getDefaultMessage())); return ResponseEntity.badRequest().body(body);
 }
 @ExceptionHandler(DataIntegrityViolationException.class)
 public ResponseEntity<Map<String,String>> conflict(DataIntegrityViolationException ex){
  Map<String,String> body=new HashMap<>(); body.put("error","This applicant has already applied for this job, or the data conflicts with a database constraint."); return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
 }
 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException ex){
  Map<String,String> body=new HashMap<>(); body.put("error",ex.getMessage()); return ResponseEntity.badRequest().body(body);
 }
}