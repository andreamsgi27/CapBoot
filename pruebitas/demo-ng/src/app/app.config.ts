import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { environment } from 'src/environments/environment';
import { ERROR_LEVEL } from '@my/core';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    { provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL }
  ]
};
