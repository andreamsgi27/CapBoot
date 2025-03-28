import { Inject, Injectable, InjectionToken, Optional } from '@angular/core';
export const ERROR_LEVEL = new InjectionToken<String>('ERROR_LEVEL');

@Injectable({
  providedIn: 'root'
})
export class LoggerService {
  private level: number = Number.MAX_VALUE;

  constructor(@Inject(ERROR_LEVEL) @Optional() level? : number) {
    if(level || level === 0)
    this.level = level;
  }

  public log(msg: string) : void {
    if(this.level > 0)
    console.log(msg);
  }

  public warn(msg: string) : void {
    if(this.level > 1)
    console.warn(msg);
  }

  public info(msg: string) : void {
    if(this.level > 2)
      {
        if(console.info)
        console.info(msg);
      else
        console.log(msg);
    }
  }

  public error(msg: string) : void {
    if(this.level > 3)
    console.error(msg);
  }

}
