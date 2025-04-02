import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe } from '../../lib/my-core';


@Component({
  selector: 'app-forms',
  imports: [FormsModule, ErrorMessagePipe],
  templateUrl: './forms.component.html',
  styleUrl: './forms.component.css'
})
export class FormsComponent {
  public elemento:any = { id: 1, nif: '12345678A', nombre: 'Juan', correo: 'juan@example.com', fAlta: '2025-01-01', edad: 25, activo: true };
  public modo: 'add' | 'edit' = 'add';

  add(){
    this.elemento = {}
    this.modo = 'add'
  }

  edit(key:number){
    this.elemento = {id: key, nif: '12345678A', nombre: 'Juan', correo: 'juan@example.com', fAlta: '2025-01-01', edad: 25, activo: true}
    this.modo = 'edit'
  }

  cancel(){

  }
  send(){
    switch(this.modo){
      case 'add':
        alert(`POST ${JSON.stringify(this.elemento)}`);
        this.cancel();
        break;
      case 'edit':
          alert(`PUT ${JSON.stringify(this.elemento)}`);
          this.cancel();
          break;
    }
  }
}
