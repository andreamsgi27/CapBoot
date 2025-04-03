/* eslint-disable @typescript-eslint/no-explicit-any */

import { CommonModule } from '@angular/common';
import { Component, Injectable, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ErrorMessagePipe, LoggerService, NormalizePipe, NotblankValidator, TypeValidator, UppercaseValidator } from '../../lib/my-core';
import { ViewModelService } from '../base';
import { FormButtonsComponent } from '../common-component/form-buttons/form-buttons.component';
import { ActorsDAOService, NavigationService, NotificationService } from '../common-services';
import { FilmsListBodyComponent } from '../films';

@Injectable({
  providedIn: 'root'
})
export class ActorsViewModelService extends ViewModelService<any, number> {
  constructor(dao: ActorsDAOService, notify: NotificationService, out: LoggerService, router: Router, navigation: NavigationService) {
    super(dao, {}, notify, out, router, navigation)
  }
  page = 0;
  totalPages = 0;
  totalRows = 0;
  rowsPerPage = 10;
  load(page: number = -1) {
    if (!page || page < 0) page = this.page;
    (this.dao as ActorsDAOService).page(page, this.rowsPerPage).subscribe({
      next: rslt => {
        this.page = rslt.page;
        this.totalPages = rslt.pages;
        this.totalRows = rslt.rows;
        this.listado = rslt.list;
        this.modo = 'list';
      },
      error: err => this.handleError(err)
    })
  }

  Films: any[] = []

  public override view(key: number): void {
    super.view(key);
    (this.dao as ActorsDAOService).Films(key).subscribe({
      next: data => {
        this.Films = data //.map(item => ({filmId: item.key, title: item.value }));
      },
      error: err => this.handleError(err)
    });
  }
}

@Component({
  selector: 'app-Actors-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [RouterLink, CommonModule, NormalizePipe, ]
})
export class ActorsListComponent implements OnChanges, OnDestroy {
  @Input() page = 0
  constructor(protected vm: ActorsViewModelService) { }

  public get VM(): ActorsViewModelService { return this.vm; }

  ngOnChanges(_changes: SimpleChanges): void {
    this.vm.load(this.page)
  }

  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-Actors-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule, ErrorMessagePipe, NotblankValidator, UppercaseValidator, TypeValidator, FormButtonsComponent]
})
export class ActorsAddComponent implements OnInit {
  constructor(protected vm: ActorsViewModelService) { }
  public get VM(): ActorsViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
  selector: 'app-Actors-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule, ErrorMessagePipe, NotblankValidator, UppercaseValidator, TypeValidator, FormButtonsComponent]
})
export class ActorsEditComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ActorsViewModelService, protected router: Router) { }
  public get VM(): ActorsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.edit(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

@Component({
  selector: 'app-Actors-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [ FormButtonsComponent, FilmsListBodyComponent, ]
})
export class ActorsViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ActorsViewModelService, protected router: Router) { }
  public get VM(): ActorsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}


export const Actors_COMPONENTES = [ActorsListComponent, ActorsAddComponent, ActorsEditComponent, ActorsViewComponent,];
