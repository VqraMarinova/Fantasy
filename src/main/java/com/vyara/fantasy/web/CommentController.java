package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.CommentCreateEditModel;
import com.vyara.fantasy.services.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @PostMapping("/comment/book/{id}")
    public String commentBook(@ModelAttribute CommentCreateEditModel commentCreateEditModel, @PathVariable Long id ){
        this.commentService.addCommentToBook(commentCreateEditModel, id);
        return String.format("redirect:/explore/book/%s",id);
    }

    @PostMapping("/comment/movie/{id}")
    public String commentMovie(@Valid @ModelAttribute CommentCreateEditModel commentCreateEditModel, @PathVariable Long id ){
        this.commentService.addCommentToMovie(commentCreateEditModel, id);
        return String.format("redirect:/explore/movie/%s",id);
    }

    @PostMapping("/comment/story/{id}")
    public String commentStory(@Valid @ModelAttribute CommentCreateEditModel commentCreateEditModel, @PathVariable Long id ){
        this.commentService.addCommentToStory(commentCreateEditModel, id);
        return String.format("redirect:/explore/story/%s",id);
    }

    @PostMapping("/comment/question/{id}")
    public String commentQuestion(@Valid @ModelAttribute CommentCreateEditModel commentCreateEditModel, @PathVariable Long id ){
        this.commentService.addCommentToQuestion(commentCreateEditModel, id);
        return String.format("redirect:/explore/question/%s",id);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/comment/edit/{item}/{itemId}/{commentId}}")
    public String editComment(@Valid @ModelAttribute CommentCreateEditModel commentCreateEditModel, @PathVariable Long commentId
    ,@PathVariable String item, @PathVariable String itemId){
        this.commentService.editComment(commentCreateEditModel, commentId);
        return String.format("redirect:/explore/%s/%s", item, itemId);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/comment/delete/{item}/{itemId}/{commentId}")
    public String deleteComment(@PathVariable Long commentId  ,@PathVariable String item, @PathVariable String itemId){
        this.commentService.deleteComment(commentId);
        return String.format("redirect:/explore/%s/%s", item, itemId);
    }



}
