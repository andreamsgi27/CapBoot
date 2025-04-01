import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService} from '@my/core';
import { NotificationComponent } from './main/notification/notification.component';
import { DemosComponent } from './demos/demos.component';
import { NotificationModalComponent } from './main/notification-modal/notification-modal.component';
import { HomeComponent } from './main/home/home.component';
import { FormsComponent } from './forms/forms.component';
import { AjaxWaitComponent, ajaxWaitInterceptor } from './main/ajax-wait';

@Component({
  selector: 'app-root',
  imports:  [/* CommonModule, */ RouterOutlet, NotificationComponent /*, NotificationModalComponent */, FormsComponent, /* HomeComponent */ AjaxWaitComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

/*   constructor(out: LoggerService) {
    out.log('hola');
    out.warn('uy');
    out.info('info');
    out.error('mal');
  } */
}
//Responsabilidad: escribir