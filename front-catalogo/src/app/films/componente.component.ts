import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FilmsViewModelService } from './servicios.service';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, ExecPipe, NormalizePipe, NotblankValidator, TypeValidator } from '../../lib/my-core';
import { FormButtonsComponent } from '../common-component/form-buttons/form-buttons.component';

@Component({
  selector: 'app-Films-list-body',
  templateUrl: './tmpl-list-body.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [RouterLink, NormalizePipe, CommonModule, ]
})
export class FilmsListBodyComponent {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  @Input({required: true}) Listado: any[] = []
  @Input() urlBase = ''
  @Output() imageError = new EventEmitter<Event>()

  imageErrorHandler(event: Event) {
    this.imageError.emit(event)
  }
}

@Component({
  selector: 'app-Films-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [RouterLink, FilmsListBodyComponent, FormsModule, ErrorMessagePipe ],
})
export class FilmsListComponent implements OnChanges, OnDestroy {
  @Input() page = 0
  @Input() search = ''
  @Input() categoria? : number
  pagina = false
  constructor(protected vm: FilmsViewModelService) { }

  public get VM(): FilmsViewModelService { return this.vm; }

  ngOnChanges(_changes: SimpleChanges): void {
    // if (this.type == 'Categories') {
    //   this.vm.list()
    if (this.categoria) {
      this.vm.porCategories(this.categoria)
    } else if (this.search == 'Categories') {
      this.vm.cargaCategories()
    } else{
      this.pagina = true;
    }
  }

  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-Films-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule, ErrorMessagePipe, NotblankValidator, TypeValidator, FormButtonsComponent, ExecPipe, ]
})
export class FilmsAddComponent implements OnInit {
  constructor(protected vm: FilmsViewModelService) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
  selector: 'app-Films-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule, ErrorMessagePipe, NotblankValidator, TypeValidator, FormButtonsComponent, ExecPipe, ]
})
export class FilmsEditComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: FilmsViewModelService, protected router: Router) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.edit(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

@Component({
  selector: 'app-Films-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [RouterLink, CommonModule, FormButtonsComponent, ]
})
export class FilmsViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: FilmsViewModelService, protected router: Router) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnChanges(_changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}

@Component({
  selector: 'app-Films',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FilmsListComponent, FilmsAddComponent, FilmsEditComponent, FilmsViewComponent, ],
})
export class FilmComponent implements OnInit, OnDestroy {
  constructor(protected vm: FilmsViewModelService, private route: ActivatedRoute) { }
  public get VM(): FilmsViewModelService { return this.vm; }
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      if (this.route.snapshot.url.slice(-1)[0]?.path === 'edit') {
        this.vm.edit(+id);
      } else {
        this.vm.view(+id);
      }
    } else if (this.route.snapshot.url.slice(-1)[0]?.path === 'add') {
      this.vm.add();
    }
  }
  ngOnDestroy(): void { this.vm.clear(); }
}

export const Films_COMPONENTES = [
  FilmComponent, FilmsListComponent, FilmsAddComponent,
  FilmsEditComponent, FilmsViewComponent, FilmsListBodyComponent,
];
