import { Component, Injectable, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ErrorMessagePipe, LoggerService, NotblankValidator } from '../../lib/my-core';
import { ViewModelService } from '../base';
import { FormButtonsComponent } from '../common-component/form-buttons/form-buttons.component';
import { CategoriesDAOService, NavigationService, NotificationService } from '../common-services';


@Injectable({
  providedIn: 'root'
})
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export class CategoriesViewModelService extends ViewModelService<any, number> {
  constructor(dao: CategoriesDAOService, notify: NotificationService, out: LoggerService,
    router: Router, navigation: NavigationService) {
    super(dao, {}, notify, out, router, navigation)
  }
  public override cancel(): void {
      this.clear()
      this.notify.clear()
      this.list()
  }
}

@Component({
  selector: 'app-Categories',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [ FormsModule, RouterLink, FormButtonsComponent, ErrorMessagePipe, NotblankValidator, ]
})
export class CategoriesComponent implements OnInit {
  constructor(protected vm: CategoriesViewModelService) { }
  public get VM(): CategoriesViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.list();
  }
}

export const Categories_COMPONENTES = [ CategoriesComponent, ];
