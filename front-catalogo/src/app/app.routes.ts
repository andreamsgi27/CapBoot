import { Routes, UrlSegment } from '@angular/router';
import { HomeComponent, PageNotFoundComponent } from './main';
import { CategoriesComponent } from './categories';
import { FilmComponent } from './films';
import { ActorsViewComponent } from './actors';
import { LanguageComponent } from './languages';



export function graficoFiles(url: UrlSegment[]) {
    return url.length === 1 && url[0].path.endsWith('.svg') ? ({ consumed: url }) : null;
}

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'inicio', component: HomeComponent, },
    {
        path: 'films', children: [
            { path: '', component: FilmComponent },
            { path: 'add', component: FilmComponent },
            { path: ':id/edit', component: FilmComponent },
            { path: ':id', component: FilmComponent }
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
            { path: '', component: ActorsViewComponent },
            { path: 'add', component: ActorsViewComponent },
            { path: ':id/edit', component: ActorsViewComponent },
            { path: ':id', component: ActorsViewComponent }
        ]
    },
    {
        path: 'languages', children: [
            { path: '', component: LanguageComponent },
            { path: 'add', component: LanguageComponent },
            { path: ':id/edit', component: LanguageComponent },
            { path: ':id', component: LanguageComponent }
        ]
    },
    { path: '404.html', component: PageNotFoundComponent },
    { path: '**', component: PageNotFoundComponent }
];