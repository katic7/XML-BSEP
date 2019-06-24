import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule } from '@angular/forms';
import { AppComponent } from "./app.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ChatComponent } from './chat/chat.component';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'chat', component: ChatComponent },
  ];

@NgModule({
    declarations: [AppComponent, LoginComponent, ChatComponent],
    imports: [BrowserModule, FormsModule, HttpClientModule,RouterModule.forRoot(
        appRoutes,
        {enableTracing: true}
  )],
    bootstrap: [AppComponent]
})
export class AppModule {

}