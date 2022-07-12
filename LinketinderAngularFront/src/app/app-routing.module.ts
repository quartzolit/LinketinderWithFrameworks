import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoggedRoutes } from './logged/logged-routing.module';
import { SignupRoutes } from './signup';

const routes: Routes = [
  {
    path:'',
    redirectTo: '/signup',
    pathMatch :'full'
  },
  ...SignupRoutes,
  ...LoggedRoutes
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
