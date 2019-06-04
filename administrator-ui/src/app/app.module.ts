import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './components/auth/auth-interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouteService } from './components/routeservice/RouteService';
import { UsersComponent } from './components/users/users.component';
import { AgentsComponent } from './components/agents/agents.component';
import { CommentsComponent } from './components/comments/comments.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { MatInputModule, MatFormFieldModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule} from '@angular/material';

const appRoutes: Routes = [
  { path: 'home', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UsersComponent },
  { path: 'agents', component: AgentsComponent },
  { path: 'comments', component: CommentsComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsersComponent,
    AgentsComponent,
    CommentsComponent,
    HomepageComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    )
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },RouteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
