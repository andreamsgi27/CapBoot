import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './componente.component';

export const routes: Routes = [
  { path: '', component: CategoriesComponent },
];

@NgModule({
  declarations: [ ],
  exports: [
    RouterModule
  ],
  imports: [
    RouterModule.forChild(routes), CategoriesComponent,
  ]
})
export default class CategoriesModule { }
