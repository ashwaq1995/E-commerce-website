package com.demo.Project2.Controller;

import com.demo.Project2.Model.Api;
import com.demo.Project2.Model.Comment;
import com.demo.Project2.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<ArrayList<Comment>> getComments() {
        return ResponseEntity.status(200).body(commentService.getComments());
    }


    @PostMapping
    public ResponseEntity<Api> addComment(@RequestBody @Valid Comment comment, Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(error.getFieldError().getDefaultMessage(),400));
        }
        boolean isCoAdded = commentService.addComment(comment);
        if (!isCoAdded) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Request not Accepted", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Request Accepted",200));

    }


    //Create endpoint where user can get all the comments for a product
    //this endpoint should accept product id and it should be valid or return bad request



    //Create endpoint where user can post comment on product
    //this endpoint should accept user id , product id , comment object
    //verify that the user bought the product before or return bad request


}
