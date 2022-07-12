import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoggedPageModule } from './logged';
import { SignupModule } from './signup';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    SignupModule,
    LoggedPageModule,
    FontAwesomeModule,
    AppRoutingModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent],

})
export class AppModule {

 }
