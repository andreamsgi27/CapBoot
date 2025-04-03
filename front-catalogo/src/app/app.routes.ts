import { Routes, UrlSegment } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { CategoriesComponent } from './component/categories/categories.component';
import { ActorsComponent } from './component/actors/actors.component';
import { FilmsComponent } from './component/films/films.component';
import { LanguagesComponent } from './component/languages/languages.component';



export function graficoFiles(url: UrlSegment[]) {
    return url.length === 1 && url[0].path.endsWith('.svg') ? ({ consumed: url }) : null;
}

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'inicio', component: HomeComponent, },
    {
        path: 'films', children: [
            { path: '', component: FilmsComponent },
            { path: 'add', component: FilmsComponent },
            { path: ':id/edit', component: FilmsComponent },
            { path: ':id', component: FilmsComponent }
        ]
    },
    {
        path: 'categories', children: [
            { path: '', component: CategoriesComponent },
            { path: 'add', component: CategoriesComponent },
            { path: ':id/edit', component: CategoriesComponent },
            { path: ':id', component: CategoriesComponent }
        ]
    },
    {
        path: 'actors', children: [
            { path: '', component: ActorsComponent },
            { path: 'add', component: ActorsComponent },
            { path: ':id/edit', component: ActorsComponent },
            { path: ':id', component: ActorsComponent }
        ]
    },
    {
        path: 'languages', children: [
            { path: '', component: LanguagesComponent },
            { path: 'add', component: LanguagesComponent },
            { path: ':id/edit', component: LanguagesComponent },
            { path: ':id', component: LanguagesComponent }
        ]
    },
    { path: '404.html', component: PageNotFoundComponent },
    { path: '**', component: PageNotFoundComponent }
];