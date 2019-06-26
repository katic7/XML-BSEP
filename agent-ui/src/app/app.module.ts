import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './components/auth/auth-interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouteService } from './components/routeservice/RouteService';
import { AgentsComponent} from './components/agents/agents.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './modules/material.module';
import { DatePipe } from '@angular/common';
import { CreateObjectComponent } from './components/create-object/create-object.component';
import { MatSelectModule } from '@angular/material';
import { ReservationsComponent } from './reservations/reservations.component';
import { NewreservationComponent } from './newreservation/newreservation.component';


const appRoutes: Routes = [
  { path: 'home', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'agents', component: AgentsComponent },
  { path: 'newObject', component: CreateObjectComponent},
  { path: 'reservations', component: ReservationsComponent },
  { path: 'newreservation', component: NewreservationComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AgentsComponent,
    HomepageComponent,
    NavigationComponent,
    CreateObjectComponent,
    ReservationsComponent,
    NewreservationComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
)
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },
  RouteService,
  DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }