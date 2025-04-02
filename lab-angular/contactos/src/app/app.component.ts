import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NotificationComponent } from './main/notification/notification.component';
import { DemosComponent } from './demos/demos.component';
import { NotificationModalComponent } from './main/notification-modal/notification-modal.component';
import { HomeComponent } from './main/home/home.component';
import { FormsComponent } from './forms/forms.component';
import { AjaxWaitComponent, ajaxWaitInterceptor } from './main/ajax-wait';
import { HeaderComponent } from "./main/header/header.component";
import { FooterComponent } from "./main/footer/footer.component";
import { LoggerService } from '../lib/my-core/services/logger.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, NotificationComponent, AjaxWaitComponent, FooterComponent],
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