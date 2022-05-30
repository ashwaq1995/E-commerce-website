package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.User;
import com.demo.Project2.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ArrayList<User>> getUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUserAdded = userService.addUser(user);
        if (!isUserAdded) {
            return ResponseEntity.status(500).body(new Api("Error User is Invalid Added", 400));
        }
        return ResponseEntity.status(201).body(new Api("User is Added", 201));
    }

    @PutMapping("/{index}")
    public ResponseEntity<Api> updateUser(@PathVariable Integer index, @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isUserEdit = userService.updateUser(index, user);
        if (!isUserEdit) {
            return ResponseEntity.status(500).body(new Api("Error User is Invalid Edited", 400));
        }
        return ResponseEntity.status(201).body(new Api("User is Updated", 201));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeUser(@RequestParam String id) {
        boolean isUserDeleted = userService.removeUser(id);
        if (!isUserDeleted) {
            return ResponseEntity.status(500).body(new Api("Error User is Invalid Deleted", 400));
        }
        return ResponseEntity.status(200).body(new Api("Deleted!", 200));
    }
}
