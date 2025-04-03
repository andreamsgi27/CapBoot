import { Component, Injectable, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ErrorMessagePipe, LoggerService, NotblankValidator } from '../../lib/my-core';
import { ViewModelService } from '../base';
import { FormButtonsComponent } from '../common-component/form-buttons/form-buttons.component';
import { LanguagesDAOService, NavigationService, NotificationService } from '../common-services';

@Injectable({
  providedIn: 'root'
})
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export class LanguagesViewModelService extends ViewModelService<any, number> {
  constructor(dao: LanguagesDAOService, notify: NotificationService, out: LoggerService, router: Router, navigation: NavigationService) {
    super(dao, {}, notify, out, router, navigation)
  }
  public override cancel(): void {
      this.clear()
      this.notify.clear()
      this.list()
  }
}

@Component({
  selector: 'app-Languages',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, FormButtonsComponent, ErrorMessagePipe, NotblankValidator,],
})
export class LanguageComponent implements OnInit {
  constructor(protected vm: LanguagesViewModelService) { }
  public get VM(): LanguagesViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.list();
  }
}

export const Languages_COMPONENTES = [ LanguageComponent, ];
