import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Actors_COMPONENTES, ActorsAddComponent, ActorsEditComponent, ActorsListComponent, ActorsViewComponent } from './componente.component';

export const routes: Routes = [
  { path: '', component: ActorsListComponent },
  { path: 'add', component: ActorsAddComponent },
  { path: ':id/edit', component: ActorsEditComponent },
  { path: ':id', component: ActorsViewComponent },
  { path: ':id/:kk', component: ActorsViewComponent },
];

@NgModule({
  declarations: [],
  exports: [
    RouterModule
  ],
  imports: [
    RouterModule.forChild(routes), Actors_COMPONENTES,
  ]
})
export default class ActorsModule { }
