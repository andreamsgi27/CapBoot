import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoggerService} from '@my/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
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