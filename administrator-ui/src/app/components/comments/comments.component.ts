import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Comment } from 'src/app/models/Comment';
import { CommentService } from '../services/comment.service';
import { PublishComment } from 'src/app/models/PublishComment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  constructor(private router: Router, private commentService : CommentService) { }

  comments : Array<Comment> = new Array<Comment>();

  Publish(comment : Comment){
    /*
    this.new_comment = new Comment(comment.id,comment.userID,comment.accommodationUnitID,comment.text,comment.commentDate,true);
    this.comments.push(this.new_comment);*/
    let obj : PublishComment = new PublishComment();
    obj.flag = !comment.published;
    obj.id = comment.id;
    console.log(comment);
    console.log(obj);
    this.commentService.setCommentVisible(obj).subscribe(data =>{
      
    });
    comment.published = !comment.published;
  }

  ngOnInit() {

    this.commentService.getAllComments().subscribe(data =>{
      this.comments = data;
    });

  }

}
