/* eslint-disable @typescript-eslint/no-explicit-any */
import { HttpContext, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { RESTDAOService } from '../base';

@Injectable({
  providedIn: 'root'
})
export class FilmsDAOService extends RESTDAOService<any, number> {
  constructor() {
    super('catalogo/Films/v1');
  }
  page(page: number, rows: number = 20): Observable<{ page: number, pages: number, rows: number, list: any[] }> {
    return new Observable(subscriber => {
      const url = `${this.baseUrl}?page=${page}&size=${rows}&sort=title`
      this.http.get<any>(url, this.option).subscribe({
        next: data => subscriber.next({ page: data.page.number, pages: data.page.totalPages, rows: data.page.totalElements, list: data.content }),
        error: err => subscriber.error(err)
      })
    })
  }
  search(filtro: any): Observable<any[]> {
    let params = new HttpParams()
    for(const param in filtro) {
      if (filtro[param] !== undefined && filtro[param] !== null && filtro[param] !== '') {
        params = params.append(param, filtro[param])
      }
    }
    if(params.keys().length === 0) {
      return throwError(() => new HttpErrorResponse({
        status: 400,
        statusText: 'Bad Request',
        url: `${this.baseUrl}/filtro`,
        error: {
          isTrusted: true,
          title: 'Filtro vacío',
        }
      })) as Observable<any[]>
    }
    params = params.append('mode', 'short')
    return this.http.get<any[]>(`${this.baseUrl}/filtro`, Object.assign({}, this.option, { params }));
  }
  // details(id: number): Observable<any> {
  //   return this.http.get<any>(`${this.baseUrl}/${id}?mode=details`, this.option);
  // }
  clasificaciones(): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/clasificaciones`, this.option);
  }
  contenidos(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/contenido-adicional`, this.option);
  }
}

@Injectable({
  providedIn: 'root'
})
export class ActorsDAOService extends RESTDAOService<any, number> {
  constructor() {
    super('catalogo/Actors/v1');
  }
  override query(extras = {}): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}?modo=short`, Object.assign({}, this.option, extras));
  }
  page(page: number, rows: number = 20): Observable<{ page: number, pages: number, rows: number, list: any[] }> {
    return new Observable(subscriber => {
      const url = `${this.baseUrl}?page=${page}&size=${rows}&sort=firstName,lastName`
      this.http.get<any>(url, this.option).subscribe({
        next: data => subscriber.next({ page: data.page.number, pages: data.page.totalPages, rows: data.page.totalElements, list: data.content }),
        error: err => subscriber.error(err)
      })
    })
  }
  Films(id: number): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/${id}/pelis`, this.option);
  }
}

@Injectable({
  providedIn: 'root'
})
export class CategoriesDAOService extends RESTDAOService<any, number> {
  constructor() {
    super('catalogo/Categories/v1');
  }
  Films(id: number): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/${id}/Films`, this.option);
  }
}

@Injectable({
  providedIn: 'root'
})
export class LanguagesDAOService extends RESTDAOService<any, number> {
  constructor() {
    super('catalogo/Languages/v1');
  }
}
