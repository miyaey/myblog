package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.CommentCreateRequest;
import org.example.springbootdeveloper.service.BlogService;
import org.example.springbootdeveloper.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    @PostMapping("/{articleId}")
    public String addComments(@PathVariable Long articleId, @ModelAttribute CommentCreateRequest req, Authentication auth, Model model){
        commentService.writeComment(articleId, req, auth.getName());

        model.addAttribute("message", "댓글이 추가되었습니다");
        return "printMessage";
    }

    @PostMapping("/{commentId}/edit")
    public String editComments(@PathVariable Long commentId, @ModelAttribute CommentCreateRequest req, Authentication auth, Model model){
        Long articleId = commentService.editComment(commentId, req.getContent(), auth.getName());

        model.addAttribute("message", articleId == null? "잘못된 요청입니다" : "댓글이 수정되었습니다");
        return "printMessage";
    }

    @GetMapping("/{commentId}/delete")
    public String deleteComments(@PathVariable Long commentId, Authentication auth, Model model){
        Long articleId = commentService.deleteComment(commentId, auth.getName());

        model.addAttribute("message", articleId == null? "작성자만 삭제 가능합니다" : "댓글이 삭제되었습니다");
        return "printMessage";
    }

}
