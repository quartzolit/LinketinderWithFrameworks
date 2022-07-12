import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogoutComponent } from './components';
import { SkillsComponent } from './components';
import { VacancyComponent } from './components';
import { CardsComponent } from './components';
import { RouterModule } from '@angular/router';
import { CardsService, LogoutService, SkillsService, VacancyService } from './services';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    LogoutComponent,
    SkillsComponent,
    VacancyComponent,
    CardsComponent,

  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  providers:[
    LogoutService,
    SkillsService,
    VacancyService,
    CardsService
  ],
})
export class LoggedPageModule { }
