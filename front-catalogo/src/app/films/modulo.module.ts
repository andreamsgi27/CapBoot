import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Films_COMPONENTES, FilmsAddComponent, FilmsEditComponent, FilmsListComponent, FilmsViewComponent } from './componente.component';

export const routes: Routes = [
  { path: '', component: FilmsListComponent },
  {
    path: 'add', component: FilmsAddComponent
  },
  {
    path: ':id/edit', component: FilmsEditComponent
  },
  { path: ':id', component: FilmsViewComponent },
  { path: ':id/:kk', component: FilmsViewComponent },
];

@NgModule({
  declarations: [ ],
  exports: [
    Films_COMPONENTES,
    RouterModule,
  ],
  imports: [
    RouterModule.forChild(routes), Films_COMPONENTES,
  ]
})
export class FilmsModule { }
