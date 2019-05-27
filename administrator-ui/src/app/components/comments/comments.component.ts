import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Comment } from 'src/app/models/Comment';
import { CommentService } from '../services/comment.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor(private router: Router, private commentService : CommentService) { }

  comments : Array<Comment> = new Array<Comment>();
  new_comment : Comment;
  comment_temp : Comment; //brisi kad bude bekend

  Publish(comment){
    this.new_comment = new Comment(comment.id,comment.userID,comment.accommodationUnitID,comment.text,comment.commentDate,true);
    this.comments.push(this.new_comment);

    /*this.commentService.setCommentVisible(this.new_comment).subscribe(data =>{
      comment.visible = data.visible;
      console.table(data);
    });*/
  }

  ngOnInit() {
    this.comment_temp = new Comment(1,1,1,"komentar","1/1/2019",false);
    this.comments.push(this.comment_temp);

    /*this.commentService.getAllComments().subscribe(data =>{
      this.comments = data;  
    });*/
  }

}
