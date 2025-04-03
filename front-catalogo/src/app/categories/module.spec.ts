/* eslint-disable @typescript-eslint/no-explicit-any */
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, inject, TestBed, tick } from '@angular/core/testing';
import { environment } from '../../../src/environments/environment';
import { LoggerService } from '../../lib/my-core';
import { DAOServiceMock } from '../base';
import { CategoriesDAOService, NavigationService, NotificationService } from '../common-services';

import { provideLocationMocks } from '@angular/common/testing';
import { NO_ERRORS_SCHEMA, Type } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { provideRouter } from '@angular/router';
import { Categories_COMPONENTES, CategoriesViewModelService } from './componente.component';

interface Categoria {
  id: number
  categoria: string
}

describe('Modulo Categories', () => {
  const apiURL = environment.apiUrl + 'catalogo/Categories/v1'
  const dataMock = [
    { "id": 1, "categoria": "Drama"},
    { "id": 2, "categoria": "Comedia"},
    { "id": 3, "categoria": "Terror"},
    { "id": 4, "categoria": "Infantil"},
  ];
  const dataAddMock: Record<string, any> = { id: 0, categoria: "Accion" }
  const dataEditMock: Record<string, any> = { id: 1, categoria: "Accion" }
  const dataBadMock: Record<string, any> = { id: -1 }
  const empty = {}

  describe('DAOService', () => {
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [],
        providers: [CategoriesDAOService, provideHttpClient(), provideHttpClientTesting()],
      });
    });

    it('query', inject([CategoriesDAOService, HttpTestingController], (dao: CategoriesDAOService, httpMock: HttpTestingController) => {
      dao.query().subscribe({
          next: data => {
            expect(data.length).toEqual(dataMock.length);
          },
          error: () => { fail('has executed "error" callback'); }
        });
      const req = httpMock.expectOne(apiURL);
      expect(req.request.method).toEqual('GET');
      req.flush([...dataMock]);
      httpMock.verify();
    }));

    it('get', inject([CategoriesDAOService, HttpTestingController], (dao: CategoriesDAOService, httpMock: HttpTestingController) => {
      dao.get(1).subscribe({
          next: data => {
            expect(data).toEqual(dataMock[0]);
          },
          error: () => { fail('has executed "error" callback'); }
        });
      const req = httpMock.expectOne(`${apiURL}/1`);
      expect(req.request.method).toEqual('GET');
      req.flush({ ...dataMock[0] });
      httpMock.verify();
    }));

    it('add', inject([CategoriesDAOService, HttpTestingController], (dao: CategoriesDAOService, httpMock: HttpTestingController) => {
      const item = {...dataAddMock} as Categoria;
      dao.add(item).subscribe();
      const req = httpMock.expectOne(`${apiURL}`);
      expect(req.request.method).toEqual('POST');
      for (const key in dataEditMock) {
        if (Object.prototype.hasOwnProperty.call(dataAddMock, key)) {
          expect(req.request.body[key]).toEqual(dataAddMock[key]);
        }
      }
      httpMock.verify();
    }));

    it('change', inject([CategoriesDAOService, HttpTestingController], (dao: CategoriesDAOService, httpMock: HttpTestingController) => {
      const item = { ...dataEditMock } as Categoria;
      dao.change(1, item).subscribe();
      const req = httpMock.expectOne(`${apiURL}/1`);
      expect(req.request.method).toEqual('PUT');
      for (const key in dataEditMock) {
        if (Object.prototype.hasOwnProperty.call(dataEditMock, key)) {
          expect(req.request.body[key]).toEqual(dataEditMock[key]);
        }
      }
      httpMock.verify();
    }));

    it('delete', inject([CategoriesDAOService, HttpTestingController], (dao: CategoriesDAOService, httpMock: HttpTestingController) => {
      dao.remove(1).subscribe();
      const req = httpMock.expectOne(`${apiURL}/1`);
      expect(req.request.method).toEqual('DELETE');
      httpMock.verify();
    }));

  });
  describe('ViewModelService', () => {
    let service: CategoriesViewModelService;
    let dao: CategoriesDAOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [],
        providers: [NotificationService, LoggerService,
          provideHttpClient(), provideHttpClientTesting(),
          provideRouter([]), provideLocationMocks(),
          { provide: CategoriesDAOService, useFactory: () => new DAOServiceMock<Categoria, number>([...dataMock]) }
        ],
      });
      service = TestBed.inject(CategoriesViewModelService);
      dao = TestBed.inject(CategoriesDAOService);
    });

    it('should be created', () => {
      expect(service).toBeTruthy();
    });

    describe('mode', () => {
      it('list', fakeAsync(() => {
        service.list()
        tick()
        expect(service.Listado.length).withContext('Verify Listado length').toBe(dataMock.length)
        expect(service.Modo).withContext('Verify Modo is ').toBe('list')
      }))

      it('add', () => {
        service.add()
        expect(service.Elemento).withContext('Verify Elemento').toEqual(empty)
        expect(service.Modo).withContext('Verify Modo is add').toBe('add')
      })

      describe('edit', () => {
        it(' OK', fakeAsync(() => {
          service.edit(3)
          tick()

          expect(service.Elemento).withContext('Verify Elemento').toEqual(dataMock[2])
          expect(service.Modo).withContext('Verify Modo is edit').toBe('edit')
        }))

        it('KO', fakeAsync(() => {
          const notify = TestBed.inject(NotificationService);
          spyOn(notify, 'add')

          service.edit(dataMock.length + 1)
          tick()

          expect(notify.add).withContext('notify error').toHaveBeenCalled()
        }))
      })

      describe('view', () => {
        it(' OK', fakeAsync(() => {
          service.view(1)
          tick()

          expect(service.Elemento).withContext('Verify Elemento').toEqual(dataMock[0])
          expect(service.Modo).withContext('Verify Modo is view').toBe('view')
        }))

        it('KO', fakeAsync(() => {
          const notify = TestBed.inject(NotificationService);
          spyOn(notify, 'add')

          service.view(dataMock.length + 1)
          tick()

          expect(notify.add).withContext('notify error').toHaveBeenCalled()
        }))
      })

      describe('delete', () => {
        it('accept confirm', fakeAsync(() => {
          spyOn(window, 'confirm').and.returnValue(true)
          service.delete(3)
          tick()
          expect(service.Listado.length).withContext('Verify Listado length').toBe(dataMock.length - 1)
          expect(service.Modo).withContext('Verify Modo is list').toBe('list')
        }))

        xit('reject confirm', fakeAsync(() => {
          spyOn(window, 'confirm').and.returnValue(false)
          service.delete(+ 1)
          tick()
          expect((dao as Record<string, any>)['listado'].length).withContext('Verify Listado length').toBe(dataMock.length)
        }))

        it('KO', fakeAsync(() => {
          spyOn(window, 'confirm').and.returnValue(true)
          const notify = TestBed.inject(NotificationService);
          spyOn(notify, 'add')

          service.delete(dataMock.length + 1)
          tick()

          expect(notify.add).withContext('notify error').toHaveBeenCalled()
        }))
      })
    })

    it('cancel', fakeAsync(() => {
      const navigation = TestBed.inject(NavigationService);
      spyOn(navigation, 'back')
      service.edit(2)
      tick()
      expect(service.Elemento).withContext('Verifica fase de preparación').toBeDefined()
      service.cancel()
      expect(service.Elemento).withContext('Verify Elemento').toEqual(empty)
      // expect(navigation.back).toHaveBeenCalled()
    }))

    describe('send', () => {
      describe('add', () => {
        it('OK', fakeAsync(() => {
          spyOn(service, 'cancel')
          service.add()
          tick()
          expect(service.Elemento).toBeDefined()
          const ele = {...empty} as any;
          for (const key in dataAddMock) {
            service.Elemento![key] = dataAddMock[key];
            ele[key] = dataAddMock[key];
          }
          service.send()
          tick()
          const listado = (dao as Record<string, any>)['listado']
          expect(listado.length).toBe(dataMock.length + 1)
          expect(listado[listado.length - 1]).toEqual(ele)
          expect(service.cancel).withContext('Verify init ViewModel').toHaveBeenCalled()
        }))
        it('KO', fakeAsync(() => {
          const notify = TestBed.inject(NotificationService);
          spyOn(notify, 'add')
          service.add()
          tick()
          expect(service.Elemento).toBeDefined()
          for (const key in dataBadMock) {
            service.Elemento![key] = dataBadMock[key];
          }
          service.send()
          tick()
          expect(notify.add).withContext('notify error').toHaveBeenCalled()
        }))
      })

      describe('edit', () => {
        it('OK', fakeAsync(() => {
          spyOn(service, 'cancel')
          service.edit(1)
          tick()
          expect(service.Elemento).toBeDefined()
          for (const key in dataEditMock) {
            service.Elemento![key] = dataEditMock[key];
          }
          service.send()
          tick()
          const listado = (dao as Record<string, any>)['listado']
          expect(listado.length).withContext('Verify Listado length').toBe(dataMock.length)
          expect(listado[0]).withContext('Verify Elemento').toEqual(service.Elemento)
          expect(service.cancel).withContext('Verify init ViewModel').toHaveBeenCalled()
        }))
        it('KO', fakeAsync(() => {
          const notify = TestBed.inject(NotificationService);
          spyOn(notify, 'add')
          service.edit(1)
          tick()
          expect(service.Elemento).toBeDefined()
          for (const key in dataBadMock) {
            service.Elemento![key] = dataBadMock[key];
          }
          (dao as Record<string, any>)['listado'].splice(0)
          service.send()
          tick()
          expect(notify.add).withContext('notify error').toHaveBeenCalled()
        }))
      })
    })

  });
  describe('Componentes', () => {
    Categories_COMPONENTES.forEach(componente => {
      describe(componente.name, () => {
        let component: any;
        let fixture: ComponentFixture<any>;

        beforeEach(async () => {
          await TestBed.configureTestingModule({
                providers: [NotificationService, LoggerService, CategoriesViewModelService,
                  provideHttpClient(), provideHttpClientTesting(),
                  provideRouter([]), provideLocationMocks()],
                imports: [FormsModule, componente],
                schemas: [NO_ERRORS_SCHEMA]
            })
            .compileComponents();
        });

        beforeEach(() => {
          const vm = TestBed.inject(CategoriesViewModelService)
          vm.add()
          fixture = TestBed.createComponent(componente as Type<any>);
          component = fixture.componentInstance;
          fixture.detectChanges();
        });

        it('should create', () => {
          expect(component).toBeTruthy();
        });
      });

    })
  })
});
