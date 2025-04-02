import { Routes, UrlMatchResult, UrlSegment } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { DemosComponent } from './demos/demos.component';
import { FormsComponent } from './forms/forms.component';
import { ContactosListComponent, ContactosAddComponent, ContactosEditComponent, ContactosViewComponent } from './contactos';

export function graphFiles(url: UrlSegment[]): UrlMatchResult | null {
    const path = url.map(segment => segment.path).join('/');
    return path ? { consumed: url } : null;
}

export const routes: Routes = [
    {path: '', component:HomeComponent, pathMatch: 'full'},
    {path: 'inicio', component:HomeComponent, pathMatch: 'full'},
    {path: 'demo', component: DemosComponent, title: 'Demo'},
    {path: 'el-formulario', component: FormsComponent},
    {path: 'personas', component: FormsComponent},
    {path: 'personas/add', component: FormsComponent}, //add antes que el :id porque sino se colaría por ahí dios sabe por qué
    {path: 'personas/:id', component: FormsComponent},
    {path: 'personas/:id/:nombrejemplo', component: FormsComponent},
    {path: 'personas/:id/edit', component: FormsComponent},
//Manera mas eficiente de agregar rutas (cambias libros por cualquiera y ya tienes el crud escrito) ->
    {path: 'ejemploderuta', children: [
        {path: '', component: FormsComponent}, //Get
        {path: 'add', component: FormsComponent}, //Post
        {path: ':id/edit', component: FormsComponent}, //Put
        {path: ':id', component: FormsComponent}, //Delete by Id, Find by id?
        {path: ':id/:nombrejemplo', component: FormsComponent},
    ]},

    { path: 'contactos', children: [
        { path: '', component: ContactosListComponent},
        { path: 'add', component: ContactosAddComponent},
        { path: ':id/edit', component: ContactosEditComponent},
        { path: ':id', component: ContactosViewComponent},
        { path: ':id/:kk', component: ContactosViewComponent},
        ]},

    
    {matcher: graphFiles, loadComponent: () => import('./graph-svg/graph-svg.component') },
    {path: 'config', loadChildren: () => import('./config/config.module').then(m => m.ConfigModule) }, 
    
    
    //el then. no estaba
    {path: '404.html', component: PageNotFoundComponent},
    {path: '**', component: PageNotFoundComponent}
];