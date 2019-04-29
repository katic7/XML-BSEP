import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth/auth-interceptor';
import { RegisterComponent } from './register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AttacksComponent } from './attacks/attacks.component';
import { ReservationFormComponent } from './reservation-form/reservation-form.component';
import { MaterialModule } from './modules/material.module';
import { HomepageComponent } from './homepage/homepage.component';
import { NavigationComponent } from './navigation/navigation.component';
import { FooterComponent } from './footer/footer.component';
import { SearchpageComponent } from './searchpage/searchpage.component';
import { SidesearchformComponent } from './sidesearchform/sidesearchform.component';
import { FilterbarComponent } from './sortbar/filterbar.component';
import { SearchobjectComponent } from './searchobject/searchobject.component';
import { FiltersectionComponent } from './filtersection/filtersection.component';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'search', component: SearchpageComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'attack', component: AttacksComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AttacksComponent,
    ReservationFormComponent,
    HomepageComponent,
    NavigationComponent,
    FooterComponent,
    SearchpageComponent,
    SidesearchformComponent,
    FilterbarComponent,
    SearchobjectComponent,
    FiltersectionComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
)
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
