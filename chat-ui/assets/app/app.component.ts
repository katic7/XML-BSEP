import { Component } from '@angular/core';
import {ChatService} from './chat.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthLoginInfo } from './models/login-info';
import { AuthService } from './services/auth.service';
import { TokenStorageService } from './services/token-storage.service';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers:[ChatService, AuthService, TokenStorageService]
})
export class AppComponent {

    user:String;
    room:String;
    messageText:String;
    messageArray:Array<{user:String,message:String}> = [];
    constructor(private _chatService:ChatService, private httpClient: HttpClient, private authService : AuthService){
        this._chatService.newUserJoined()
        .subscribe(data=> this.messageArray.push(data));


        this._chatService.userLeftRoom()
        .subscribe(data=>this.messageArray.push(data));

        this._chatService.newMessageReceived()
        .subscribe(data=>this.messageArray.push(data));
    }

    join(){
        this._chatService.joinRoom({user:this.user, room:this.room});
    }

    leave(){
        this._chatService.leaveRoom({user:this.user, room:this.room});
    }

    sendMessage()
    {
        this._chatService.sendMessage({user:this.user, room:this.room, message:this.messageText});
    }

    ngOnInit(){
        // let headers = new HttpHeaders({
        //     "Authorization" : "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTYxMzk4Njc5LCJpYXQiOjE1NjEzOTc4MTUsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJHZXRDbGllbnRzIiwiQXBwcm92ZVJlc2VydmF0aW9ucyIsIkNSRUFURSIsIkdldEFkZGl0aW9uYWxTZXJ2aWNlcyIsIlVubG9ja1VzZXIiLCJBZGRDb250ZW50IiwiQWRkQWNjVW5pdCIsIlB1Ymxpc2hDb21tZW50IiwiQmxvY2tVc2VycyIsIkRlbGV0ZUFnZW50cyIsIlJFQUQiLCJHZXRBZ2VudHMiLCJCbG9ja0NvbW1lbnQiLCJEZWxldGVBY2NVbml0IiwiQXBwcm92ZUNvbW1lbnQiLCJNb2RpZnlDb250ZW50IiwiTW9kaWZ5QWNjVW5pdCIsIkxvY2tVc2VyIiwiQWRkUHJpY2UiLCJEZWxldGVVc2VycyIsIkFkZFVzZXJzIiwiQWRkQWdlbnRzIiwiTW9kaWZ5VXNlckNvbnRlbnQiLCJHZXRDb21tZW50cyIsIkdldEFjY1VuaXQiXSwidXNlcm5hbWUiOiJuZW1hbmphQGdtYWlsLmNvbSJ9.MXYEIx1z60YtW-A3FqIvlb9lblsYRwPrhkd1bZXYRaeT6WJU-wMTD-Gw48yoKtgJlEqeFUThzP4orUKOEZrwJg"});
        // let options = { headers: headers };
        // this.httpClient.get('https://localhost:8085/api/auth/getLogged', options).subscribe(data =>{
        //     console.log(data);
        // });
        this.authService.getLogged().subscribe(data =>{
            console.log(data);
        })
    }

}