import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChatService } from '../chat.service';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';


@Component({
    selector: 'my-chat',
    templateUrl: './chat.component.html',
    styleUrls: ['./chat.component.css'],
    providers:[ChatService, AuthService, TokenStorageService]
})
export class ChatComponent {

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
        // ne moze da udje u chat ako nije logovan
        this.authService.getLogged().subscribe(data =>{
            this.user = data.name;
            console.log(data);
        })
    }

}