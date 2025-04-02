import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CONTACTOS_COMPONENTES } from './componente.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [],
  imports: [
    CONTACTOS_COMPONENTES, FormsModule, CommonModule
  ]
})
export class ContactosModule { }