import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationService, NotificationType } from '../common-services';
import { Unsubscribable } from 'rxjs';

@Component({
  selector: 'app-demos',
  imports: [],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css'
})
export class DemosComponent implements OnInit, OnDestroy {
  private suscriptor: Unsubscribable | undefined;
  constructor(public vm: NotificationService) { }
  ngOnInit(): void {
    this.suscriptor = this.vm.Notificacion.subscribe(n => {
    if (n.Type !== NotificationType.error) { return; }
    window.alert(`Suscripción: ${n.Message}`);
    this.vm.remove(this.vm.Listado.length - 1);
    });
    }
    ngOnDestroy(): void {
      if (this.suscriptor) {
      this.suscriptor.unsubscribe();
      }
  }
}
