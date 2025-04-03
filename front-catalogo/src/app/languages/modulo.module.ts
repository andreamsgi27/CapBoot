import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LanguageComponent } from './componente.component';

export const routes: Routes = [
  { path: '', component: LanguageComponent },
];

@NgModule({
  declarations: [ ],
  exports: [
    RouterModule
  ],
  imports: [
    RouterModule.forChild(routes), LanguageComponent,
  ]
})
export default class LanguagesModule { }
