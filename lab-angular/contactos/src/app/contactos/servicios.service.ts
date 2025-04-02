import { HttpContextToken, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { LoggerService } from '../../lib/my-core/services/logger.service';
import { NotificationService } from '../common-services/notification.service';



export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';

export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);


export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiUrl;
  protected http: HttpClient = inject(HttpClient)
  constructor(entidad: string, protected option = {}) {
    this.baseUrl += entidad;
  }
  query(): Observable<Array<T>> {
    return this.http.get<Array<T>>(this.baseUrl, this.option);
  }
  get(id: K): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${id}`, this.option);
  }
  add(item: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, item, this.option);
  }
  change(id: K, item: T): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${id}`, item, this.option);
  }
  remove(id: K): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${id}`, this.option);
  }
}

@Injectable({
  providedIn: 'root'
})


export class ContactosDAOService extends RESTDAOService<any, any> {
  constructor() {
    super('contactos');
  }
}

@Injectable({
  providedIn: 'root'
})


export class ContactosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;

  constructor(protected notify: NotificationService, protected out: LoggerService, protected dao: ContactosDAOService) {
  }
  public get Modo(): ModoCRUD { return this.modo; }
  public get Listado(): Array<any> { return this.listado; }
  public get Elemento(): any { return this.elemento; }

  public list(): void {
    this.dao.query().subscribe({
      next: data => {
        this.listado = data;
        this.modo = 'list';
      },
      error: err => this.handleError(err)
    });
  }

  public add(): void {
    this.elemento = {};
    this.modo = 'add';
  }
  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.elemento = data;
        this.idOriginal = key;
        this.modo = 'edit';
      },
      error: err => this.handleError(err)
    });
  }

  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: err => this.handleError(err)
    });
  }
  public delete(key: any): void {
    if (!window.confirm('¿Seguro?')) { return; }
    this.dao.remove(key).subscribe({
      next: () => this.list(),
      error: err => this.handleError(err)
    });
  }

  public cancel(): void {
    this.elemento = {};
    this.idOriginal = null;
    this.list();
  }
  public send(): void {
    switch (this.modo) {
      case 'add':
        this.dao.add(this.elemento).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'edit':
        this.dao.change(this.idOriginal, this.elemento).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }

  clear() {
    this.elemento = {};
    this.idOriginal = undefined;
    this.listado = [];
  }

  handleError(err: HttpErrorResponse) {
    let msg = ''
    switch (err.status) {
      case 0: msg = err.message; break;
      case 404: msg = `ERROR ${err.status}: ${err.statusText}`; break;
      default:
        msg = `ERROR ${err.status}: ${err.error?.['title'] ??
          err.statusText}.${err.error?.['detail'] ? ` Detalles: ${err.error['detail']}` : ''}`
        break;
    }
    this.notify.add(msg)
  }






}

