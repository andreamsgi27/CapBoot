/* eslint-disable @typescript-eslint/no-explicit-any */
import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoggerService } from '../../lib/my-core';
import { ViewModelService } from '../base';
import { ActorsDAOService, CategoriesDAOService, FilmsDAOService, LanguagesDAOService, NavigationService, NotificationService } from '../common-services';

@Injectable({
  providedIn: 'root'
})
export class FilmsViewModelService extends ViewModelService<any, number> {

  constructor(notify: NotificationService, out: LoggerService, router: Router, navigation: NavigationService,
    dao: FilmsDAOService, protected daoLanguages: LanguagesDAOService, protected daoCategories: CategoriesDAOService, protected daoActors: ActorsDAOService
  ) {
    super(dao, { rating: 'G' }, notify, out, router, navigation)
    // Soluciona el problema de las clases JavaScript por el cual los métodos pierden la referencia a this cuando se referencian por nombre (ExecPipe)
    this.dameActor = this.dameActor.bind(this)
    this.dameCategoria = this.dameCategoria.bind(this)
  }

  public override view(key: any): void {
    this.dao.get(key, { params: new HttpParams().set('mode', 'details') }).subscribe({
      next: data => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: err => this.handleError(err)
    });
  }

  private filtro: any = { title: '', minlength: '', maxlength: '', rating: '' }
  public get Filtro(): any { return this.filtro; }
  public get HasFiltro(): boolean {
    return this.filtro.title !== '' || this.filtro.minlength !== '' || this.filtro.maxlength !== '' || this.filtro.rating !== ''
  }
  search() {
    (this.dao as FilmsDAOService).search(this.filtro).subscribe({
      next: data => {
        this.listado = data;
        this.modo = 'list';
      },
      error: err => this.handleError(err)
    })
  }

  // Formularios

  public Languages: any[] = [];
  public clasificaciones: any[] = [];
  public contenidos: any[] = [];
  private actorsData: any[] = [];
  private categoriesData: any[] = [];

  public get Actors(): any { return this.actorsData.filter(item => !this.elemento?.actors?.includes(item.id)); }
  public get Categories(): any { return this.categoriesData.filter(item => !this.elemento?.categories?.includes(item.id)); }
  public get Contenidos(): any { return this.contenidos.filter(item => !this.elemento?.specialFeatures?.includes(item)); }
  public get Clasificaciones(): any {
    this.cargaClasificaciones();
    return this.clasificaciones;
  }

  public cargaCategories() {
    this.daoCategories.query().subscribe({
      next: data => {
        this.categoriesData = data;
        this.dameCategoria = this.dameCategoria.bind(this)
      },
      error: err => this.handleError(err)
    });
  }

  override cargaListas() {
    this.cargaClasificaciones();
    if (this.contenidos.length === 0)
      (this.dao as FilmsDAOService).contenidos().subscribe({
        next: data => {
          this.contenidos = data;
        },
        error: err => this.handleError(err)
      });
    this.cargaCategories();
    this.daoActors.query().subscribe({
      next: data => {
        this.actorsData = data;
        this.dameActor = this.dameActor.bind(this)
      },
      error: err => this.handleError(err)
    });
    this.daoLanguages.query().subscribe({
      next: data => {
        this.Languages = data;
      },
      error: err => this.handleError(err)
    });
  }


  private cargaClasificaciones() {
    if (this.clasificaciones.length === 0) {
      this.clasificaciones = [{id:'', categoria: ''}];
      (this.dao as FilmsDAOService).clasificaciones().subscribe({
        next: data => {
          this.clasificaciones = data;
        },
        error: err => this.handleError(err)
      });
    }
  }

  dameActor(id: number) {
    if (!this?.Actors || this.Actors.length === 0) return '(sin cargar)'
    return this.Actors.find((item: { id: number; }) => item.id === id)?.nombre ?? 'error'
  }
  addActor(id: number) {
    if (!this.elemento.actors) {
      this.elemento.actors = []
    } else if (this.elemento.actors.includes(id)) {
      this.notify.add('Ya tiene la categoría')
      return
    }
    this.elemento.actors.push(id)
  }
  removeActor(index: number) {
    this.elemento.actors.splice(index, 1)
  }

  dameCategoria(id: number) {
    if (!this?.Categories || this.Categories.length === 0) return '(sin cargar)'
    return this.Categories.find((item: { id: number; }) => item.id === id)?.categoria ?? 'error'
  }
  addCategoria(id: number) {
    if (!this.elemento.categories) {
      this.elemento.categories = []
    } else if (this.elemento.categories.includes(id)) {
      this.notify.add('Ya tiene la categoría')
      return
    }
    this.elemento.categories.push(id)
  }
  removeCategoria(index: number) {
    this.elemento.categories.splice(index, 1)
  }

  public porCategories(id: number) {
    this.cargaCategories();
    this.daoCategories.Films(id).subscribe({
      next: data => {
        this.listado = data;
      },
      error: err => this.handleError(err)
    });
  }

  addContenido(item: string) {
    if (!this.elemento.specialFeatures) {
      this.elemento.specialFeatures = []
    } else if (this.elemento.specialFeatures.includes(item)) {
      this.notify.add('Ya tiene el contenido')
      return
    }
    this.elemento.specialFeatures.push(item)
  }
  removeContenido(index: number) {
    this.elemento.specialFeatures.splice(index, 1)
  }

}
