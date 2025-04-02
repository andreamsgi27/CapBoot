import { Component, forwardRef, OnDestroy, OnInit } from '@angular/core';
import { ContactosViewModelService } from './servicios.service';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, TypeValidator } from '../../lib/my-core';

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
})
export class ContactosListComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { this.vm.clear(); }
}

@Component({
  selector: 'app-contactos-add',
  templateUrl: './form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosAddComponent implements OnInit {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { }
}

@Component({
  selector: 'app-contactos-edit',
  templateUrl: './form.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [FormsModule, TypeValidator, ErrorMessagePipe]
})
export class ContactosEditComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
}

@Component({
  selector: 'app-contactos-view',
  templateUrl: './view.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [DatePipe]
})
export class ContactosViewComponent implements OnInit, OnDestroy {
  constructor(protected vm: ContactosViewModelService) { }
  public get VM(): ContactosViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
}

export const CONTACTOS_COMPONENTES = [
  ContactosComponent, ContactosListComponent, ContactosAddComponent,
  ContactosEditComponent, ContactosViewComponent,
];



