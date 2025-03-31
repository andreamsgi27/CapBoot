import { Component, computed, OnDestroy, OnInit, signal } from '@angular/core';
import { Unsubscribable } from 'rxjs';
import { NotificationService, NotificationType } from '../common-services';

@Component({
  selector: 'app-demos',
  imports: [],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css',
})

export class DemosComponent implements OnInit, OnDestroy {
  private fecha = new Date('2025-03-01'); //navegador == formato ISO año-mes-dia, el 'date' no lo entiende
  public readonly nombre = signal<string>('mundo')
  public readonly fontSize = signal<number>(30)
  public readonly listado = signal([
    { id: 1, nombre: 'Madrid' },
    { id: 2, nombre: 'oviedo' },
    { id: 3, nombre: 'BARCELONA' },
    { id: 4, nombre: 'ciudad Real' },
  ])
  public readonly idProvincia = signal<number>(2)
//readonly -> protege las variables del cambio, ya que las hemos hecho públicas
  public resultado = signal<string>('')
  public visible = signal<boolean>(true)
  public invisible = computed<boolean>(() => !this.visible())//Esto depende de 'visible' y ya esta manejado automáticamente
  public readonly estetica = signal({ importante : true, urgente : true, error: false})

  
  constructor(public vm: NotificationService) {}

  public get Fecha(): string {
    return this.fecha.toISOString(); //
  }
  public set Fecha(value: string) {
    this.fecha = new Date(value);
  }

saluda(){
  this.resultado.set('Hola ${this.nombre()}')
}

despide(){
  this.resultado.set('Adios ${this.nombre()}')
}

di(algo: string){
  this.resultado.set('Dice ${algo}')
}

cambia(){
  this.visible.update(valor => !valor)
  this.estetica.update(valor => ({...valor, importante: !valor.importante}))
  this.estetica.update(valor => ({...valor, error: !valor.error}))
  /* Actualizo solo las propiedades que me interesan, manteniendo el resto del objeto igual,
  para evitar actualizaciones innecesarias si no hay cambios en esas propiedades específicas.*/
}
add(provincia: string){
  const id = this.listado()[this.listado().length - 1].id + 1; //obtengo el id del último elemento[] y le sumo 1
  this.listado.update(valor => [...valor, { id, nombre: provincia }]) //actualizo el listado con el nuevo elemento (update si tiene que tener en cuenta el elemento anterior, set si no)
  this.idProvincia.set(id) //actualizo el id de la provincia
}

calcula(a: number, b: number): number {
  return a + b;
}


//private suscriptor: Unsubscribable | undefined;
private suscriptor?: Unsubscribable;
  //el ? indica que puede ser undefined, es una forma de definirlo como opcional


  ngOnInit(): void {
    this.suscriptor = this.vm.Notificacion.subscribe({
      next: n => {
        if (n.Type !== NotificationType.error) { return; }
        // window.alert(`Suscripción: ${n.Message}`);
        // this.vm.remove(this.vm.Listado.length - 1);
      },
      complete: () => this.suscriptor?.unsubscribe()
    });
  }

  ngOnDestroy(): void {
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }
}
