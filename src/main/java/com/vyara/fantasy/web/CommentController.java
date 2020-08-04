package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.CommentCreateEditModel;
import com.vyara.fantasy.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @ModelAttribute("commentModel")
    public CommentCreateEditModel commentModel (){
        return new CommentCreateEditModel();
    }

    @PostMapping("/comment/book/{id}")
    public String commentBook(@Valid @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id, AbstractBindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return String.format("/comment/book/%d",id );
        }
        this.commentService.addCommentToBook(commentModel, id);
        return String.format("redirect:/explore/book/%s",id);
    }

    @PostMapping("/comment/movie/{id}")
    public String commentMovie(@Valid @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id, AbstractBindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return String.format("/comment/movie/%d",id );
        }
        this.commentService.addCommentToMovie(commentModel, id);
        return String.format("redirect:/explore/movie/%s",id);
    }

    @PostMapping("/comment/story/{id}")
    public String commentStory(@Valid @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id, AbstractBindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return String.format("/comment/story/%d",id );
        }
        this.commentService.addCommentToStory(commentModel, id);
        return String.format("redirect:/explore/story/%s",id);
    }

    @PostMapping("/comment/question/{id}")
    public String commentQuestion(@Valid @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id, AbstractBindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return String.format("/comment/question/%d",id );
        }
        this.commentService.addCommentToQuestion(commentModel, id);
        return String.format("redirect:/explore/question/%s",id);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/comment/edit/{item}/{itemId}/{commentId}")
    public String editComment(@Valid @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long commentId
    ,@PathVariable String item, @PathVariable String itemId, AbstractBindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return String.format("/comment/edit/%s/%s/%d",item, itemId, commentId );
        }
        this.commentService.editComment(commentModel, commentId);
        return String.format("redirect:/explore/%s/%s", item, itemId);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/comment/delete/{item}/{itemId}/{commentId}")
    public String deleteComment(@PathVariable Long commentId  ,@PathVariable String item, @PathVariable String itemId){
        this.commentService.deleteComment(commentId);
        return String.format("redirect:/explore/%s/%s", item, itemId);
    }



}
