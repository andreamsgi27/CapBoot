import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService} from '@my/core';
import { NotificationComponent } from './main/notification/notification.component';

@Component({
  selector: 'app-root',
  imports:  [CommonModule, RouterOutlet, NotificationComponent,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title : string = 'hola';

  constructor(out: LoggerService) {
    out.log('hola');
    out.warn('uy');
    out.info('bien');
    out.error('mal');
  }
}
//Responsabilidad: escribir