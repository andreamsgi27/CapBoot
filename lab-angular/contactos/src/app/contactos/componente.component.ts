import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { ContactosViewModelService } from './servicios.service';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, TypeValidator } from '../../lib/my-core';
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-contactos',
  templateUrl: './anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
    forwardRef(() => ContactosAddComponent),
    forwardRef(() => ContactosEditComponent),
    forwardRef(() => ContactosViewComponent),
    forwardRef(() => ContactosListComponent),
  ],
})

export class ContactosComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-contactos-list',
  templateUrl: './list.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [RouterLink]
})
export class ContactosListComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-contactos-add',
  templateUrl: './form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
})
export class ContactosAddComponent implements OnInit {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.add();
  }
}

@Component({
  selector: 'app-contactos-edit',
  templateUrl: './form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
})
export class ContactosEditComponent implements OnInit, OnDestroy {
  private obs$?: Subscription;
  constructor(protected vm: ContactosViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const id = parseInt(params?.get('id') ?? '');
        if (id) {
          this.vm.edit(id);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$!.unsubscribe();
  }
}


@Component({
  selector: 'app-contactos-view',
  templateUrl: './view.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [DatePipe],
})
export class ContactosViewComponent implements OnChanges {
  @Input() id?: string;
  constructor(protected vm: ContactosViewModelService, protected router: Router) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnChanges(changes: SimpleChanges): void {
    if (this.id) {
      this.vm.view(+this.id);
    } else {
      this.router.navigate(['/404.html']);
    }
  }
}


export const CONTACTOS_COMPONENTES = [
  ContactosComponent, ContactosListComponent, ContactosAddComponent,
  ContactosEditComponent, ContactosViewComponent,
];



