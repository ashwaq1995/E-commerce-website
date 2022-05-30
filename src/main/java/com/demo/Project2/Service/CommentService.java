package com.demo.Project2.Service;

import com.demo.Project2.Model.Comment;
import com.demo.Project2.Model.Product;
import com.demo.Project2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {
    private ArrayList<Comment> comments = new ArrayList();

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public boolean addComment(Comment c) {
        return comments.add(c);
    }

}

